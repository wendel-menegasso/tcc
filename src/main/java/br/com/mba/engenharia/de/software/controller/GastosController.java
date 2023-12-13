package br.com.mba.engenharia.de.software.controller;

import br.com.mba.engenharia.de.software.dto.contas.ContaDTOAlterar;
import br.com.mba.engenharia.de.software.dto.contas.ContaDTOAlterarFull;
import br.com.mba.engenharia.de.software.dto.contas.ContaDTORetorno;
import br.com.mba.engenharia.de.software.dto.gastos.GastosDTO;
import br.com.mba.engenharia.de.software.dto.gastos.GastosDTOFull;
import br.com.mba.engenharia.de.software.dto.gastos.GastosRespostaDTO;
import br.com.mba.engenharia.de.software.entity.despesas.Gastos;
import br.com.mba.engenharia.de.software.entity.rendas.Renda;
import br.com.mba.engenharia.de.software.repository.contas.ContaRepository;
import br.com.mba.engenharia.de.software.repository.gastos.GastosRepository;
import br.com.mba.engenharia.de.software.service.gastos.CSVGastosService;
import br.com.mba.engenharia.de.software.service.gastos.GastosManager;
import br.com.mba.engenharia.de.software.service.gastos.GastosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class GastosController {

    private static final Logger logger = LoggerFactory.getLogger(Gastos.class);

    @Autowired
    GastosRepository repository;

    @Autowired
    ContaRepository contaRepository;

    CSVGastosService fileService;

    GastosService gastosService;

    @Bean
    public GastosService gastosService(){
        return new GastosManager(repository);
    }

    @Bean
    public CSVGastosService csvGastosService() {
        this.fileService = new CSVGastosService();
        return fileService;
    }

    public GastosController() {
        this.gastosService = gastosService();
        this.fileService = csvGastosService();
    }

    @PostMapping("/criarGasto")
    public ResponseEntity<?> salvar(@RequestBody GastosDTO gastosDTO){
        gastosService.setGastosRepository(repository);
        Gastos gastos = gastosDTO.parseGastosToDTOGastos();

        gastos.setId(gastosService.count());
        Gastos gastosRetorno = gastosService.save(gastos);
        GastosRespostaDTO gastosRespostaDTO = new GastosRespostaDTO(gastosRetorno);

        if (gastosRespostaDTO != null){
            ContaController controller = new ContaController(contaRepository);
            ContaDTOAlterar contaDTOAlterar = new ContaDTOAlterar(String.valueOf(gastosRetorno.getOrigem()), gastosRetorno.getUsuario());
            ContaDTORetorno contaSelecionada = controller.pegaConta(contaDTOAlterar);

            Double valorRenda = Double.parseDouble(gastosRetorno.getValor());
            Double valorConta = Double.parseDouble(contaSelecionada.getSaldo());

            Double valorAtualizado = valorConta - valorRenda;

            contaSelecionada.setSaldo(String.valueOf(valorAtualizado));

            ContaDTOAlterarFull contaDTOAlterarFull = new ContaDTOAlterarFull(contaSelecionada);
            ContaDTORetorno contaAlterada = controller.atualizaConta(contaDTOAlterarFull);

            if (contaAlterada != null) {
                logger.info(String.format("Despesa cadastrada corretamente"));
                return ResponseEntity.ok(gastosRespostaDTO);
            } else {
                logger.info(String.format("Despesa cadastrada incorretamente"));
                return ResponseEntity.ok(null);
            }
        }
        logger.info(String.format("Despesa cadastrada incorretamente"));
        return ResponseEntity.ok(null);
    }

    @PostMapping("/listarGasto")
    public ResponseEntity<List<GastosRespostaDTO>> listarGasto(@RequestBody String idUsuario) {
        gastosService.setGastosRepository(repository);
        List<Gastos> gastosList = gastosService.findAll(Integer.parseInt(idUsuario));
        List<GastosRespostaDTO> gastosRespostaDTOList = new ArrayList<>();
        for (Gastos gasto : gastosList) {
            GastosRespostaDTO gastosRespostaDTO = gasto.parseGastosToGastosRespostaDTO();
            gastosRespostaDTOList.add(gastosRespostaDTO);
        }
        return ResponseEntity.ok(gastosRespostaDTOList);
    }

    @PostMapping("/gerarRelatorioGasto")
    public ResponseEntity<FileSystemResource> gerarRelatorioGasto(@RequestBody GastosDTOFull gastosDTOFull) throws IOException {
        fileService.setUsuario(Integer.parseInt(String.valueOf(gastosDTOFull.getUsuario())));
        String filename = "gastos.csv";
        InputStreamResource file = new InputStreamResource(fileService.load(filename));

        FileSystemResource fileSystemResource = new FileSystemResource(new File(filename));

        // Configura os cabeçalhos da resposta
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileSystemResource.getFilename());
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        headers.add(HttpHeaders.CONTENT_LENGTH, String.valueOf(fileSystemResource.contentLength()));

        // Cria o ResponseEntity com o objeto FileSystemResource e os cabeçalhos
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(fileSystemResource);
    }

    @DeleteMapping("/deletarGasto/{id}")
    public ResponseEntity<?> deletarGasto(@PathVariable("id") String id){
        gastosService.setGastosRepository(repository);
        Renda renda = new Renda();
        renda.setId(Integer.parseInt(id));
        Gastos gastoRetorno = gastosService.findById(renda.getId());
        GastosRespostaDTO gastosRespostaDTO = gastoRetorno.parseGastosToGastosRespostaDTO();
        if (repository.delete(renda.getId()) > 0){
            ContaController controller = new ContaController(contaRepository);
            ContaDTOAlterar contaDTOAlterar = new ContaDTOAlterar(String.valueOf(gastoRetorno.getOrigem()), gastoRetorno.getUsuario());
            ContaDTORetorno contaSelecionada = controller.pegaConta(contaDTOAlterar);

            Double valorGasto = Double.parseDouble(gastoRetorno.getValor());
            Double valorConta = Double.parseDouble(contaSelecionada.getSaldo());

            Double valorAtualizado = valorConta + valorGasto;

            contaSelecionada.setSaldo(String.valueOf(valorAtualizado));

            ContaDTOAlterarFull contaDTOAlterarFull = new ContaDTOAlterarFull(contaSelecionada);
            ContaDTORetorno contaAlterada = controller.atualizaConta(contaDTOAlterarFull);

            if (contaAlterada != null) {
                logger.info(String.format("Despesa deletada com sucesso"));
                return ResponseEntity.ok(gastosRespostaDTO);
            } else {
                logger.info(String.format("Falha na exclusão"));
                return ResponseEntity.ok(null);
            }
        }
        logger.info(String.format("Falha na exclusão"));
        return ResponseEntity.ok(null);
    }

    @PostMapping("/recebeDadosAlterarGasto")
    public ResponseEntity<?> recebeDadosAlterarGasto(@RequestBody Gastos gastos){
        gastosService.setGastosRepository(repository);
        return ResponseEntity.ok(gastosService.findById(gastos.getId()));
    }

    @PutMapping("alterarGasto")
    public ResponseEntity<?> alterarGasto(@RequestBody GastosDTOFull gastosDTOFull){
        gastosService.setGastosRepository(repository);
        Gastos gastoAlterado = gastosDTOFull.parseGastosToDTOFullToGastos();
        Gastos gastoSemAlteracao = gastosService.findById(gastoAlterado.getId());


        Double gastoAtualizado = Double.parseDouble(gastoAlterado.getValor()) - Double.parseDouble(gastoSemAlteracao.getValor());

        int retorno = gastosService.updateGastos(gastoAlterado.getNome(), gastoAlterado.getTipo(), gastoAlterado.getValor(),
                gastoAlterado.getData(), gastoAlterado.getId());
        GastosRespostaDTO gastosRespostaDTO = new GastosRespostaDTO(gastoAlterado);
        if (retorno == 1){

            ContaController controller = new ContaController(contaRepository);
            ContaDTOAlterar contaDTOAlterar = new ContaDTOAlterar(String.valueOf(gastoSemAlteracao.getOrigem()), gastoSemAlteracao.getUsuario());
            ContaDTORetorno contaSelecionada = controller.pegaConta(contaDTOAlterar);

            Double valorConta = Double.parseDouble(contaSelecionada.getSaldo());

            Double valorAtualizado = valorConta - gastoAtualizado;

            contaSelecionada.setSaldo(String.valueOf(valorAtualizado));

            ContaDTOAlterarFull contaDTOAlterarFull = new ContaDTOAlterarFull(contaSelecionada);
            ContaDTORetorno contaAlterada = controller.atualizaConta(contaDTOAlterarFull);

            logger.info(String.format("Gasto alterado com sucesso"));
            return new ResponseEntity<>(gastosRespostaDTO, HttpStatus.OK);
        }
        else {
            logger.info(String.format("Gasto não alterado"));
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
