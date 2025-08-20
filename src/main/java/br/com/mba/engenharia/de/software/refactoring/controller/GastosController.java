package br.com.mba.engenharia.de.software.refactoring.controller;

import br.com.mba.engenharia.de.software.refactoring.dto.contas.ContaDTOAlterar;
import br.com.mba.engenharia.de.software.refactoring.dto.contas.ContaDTOAlterarFull;
import br.com.mba.engenharia.de.software.refactoring.dto.contas.ContaDTORetorno;
import br.com.mba.engenharia.de.software.refactoring.dto.gastos.GastosDTO;
import br.com.mba.engenharia.de.software.refactoring.dto.gastos.GastosDTOFull;
import br.com.mba.engenharia.de.software.refactoring.dto.gastos.GastosRespostaDTO;
import br.com.mba.engenharia.de.software.refactoring.entity.despesas.Gastos;
import br.com.mba.engenharia.de.software.refactoring.entity.rendas.Renda;
import br.com.mba.engenharia.de.software.refactoring.repository.gastos.GastosRepository;
import br.com.mba.engenharia.de.software.refactoring.service.GenericService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
public class GastosController {

    private static final Logger logger = LoggerFactory.getLogger(Gastos.class);

    @Autowired
    GastosRepository repository;

    @Autowired
    GenericService servicao;

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody GastosDTO gastosDTO) {
        Gastos gastos = gastosDTO.parseGastosToDTOGastos();

        gastos.setId(repository.count());
        Gastos gastosRetorno = repository.save(gastos);
        GastosRespostaDTO gastosRespostaDTO = new GastosRespostaDTO(gastosRetorno);

        ContaDTOAlterar contaDTOAlterar = new ContaDTOAlterar(String.valueOf(gastosRetorno.getOrigem()), gastosRetorno.getUsuario());
        ContaDTORetorno contaSelecionada = servicao.pegaConta(contaDTOAlterar);

        Double valorRenda = Double.parseDouble(gastosRetorno.getValor());
        Double valorConta = Double.parseDouble(contaSelecionada.getSaldo());

        Double valorAtualizado = valorConta - valorRenda;

        contaSelecionada.setSaldo(String.valueOf(valorAtualizado));

        ContaDTOAlterarFull contaDTOAlterarFull = new ContaDTOAlterarFull(contaSelecionada);
        ContaDTORetorno contaAlterada = servicao.atualizaConta(contaDTOAlterarFull);

        if (contaAlterada != null) {
            logger.info(String.format("Despesa cadastrada corretamente"));
            return ResponseEntity.ok(gastosRespostaDTO);
        } else {
            logger.info(String.format("Despesa cadastrada incorretamente"));
            return ResponseEntity.ok(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<GastosRespostaDTO>> listarGasto(@PathVariable("id") String idUsuario) {
        return ResponseEntity.ok(repository.findAll(Integer.parseInt(idUsuario))
                .stream()
                .map(GastosRespostaDTO::new)
                .collect(Collectors.toList()));
    }

    @PostMapping("/gerarRelatorioGasto")
    public ResponseEntity<FileSystemResource> gerarRelatorioGasto(@RequestBody GastosDTOFull gastosDTOFull) throws IOException {
        String filename = "gastos.csv";

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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarGasto(@PathVariable("id") String id) {
        Renda renda = new Renda();
        renda.setId(Integer.parseInt(id));
        Gastos gastoRetorno = repository.findById(renda.getId());
        GastosRespostaDTO gastosRespostaDTO = gastoRetorno.parseGastosToGastosRespostaDTO();
        if (repository.delete(renda.getId()) > 0) {
            ContaDTOAlterar contaDTOAlterar = new ContaDTOAlterar(String.valueOf(gastoRetorno.getOrigem()), gastoRetorno.getUsuario());
            ContaDTORetorno contaSelecionada = servicao.pegaConta(contaDTOAlterar);

            Double valorGasto = Double.parseDouble(gastoRetorno.getValor());
            Double valorConta = Double.parseDouble(contaSelecionada.getSaldo());

            Double valorAtualizado = valorConta + valorGasto;

            contaSelecionada.setSaldo(String.valueOf(valorAtualizado));

            ContaDTOAlterarFull contaDTOAlterarFull = new ContaDTOAlterarFull(contaSelecionada);
            ContaDTORetorno contaAlterada = servicao.atualizaConta(contaDTOAlterarFull);

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
    public ResponseEntity<?> recebeDadosAlterarGasto(@RequestBody Gastos gastos) {
        return ResponseEntity.ok(repository.findById(gastos.getId()));
    }

    @PutMapping("alterarGasto")
    public ResponseEntity<?> alterarGasto(@RequestBody GastosDTOFull gastosDTOFull) {
        Gastos gastoAlterado = gastosDTOFull.parseGastosToDTOFullToGastos();
        Gastos gastoSemAlteracao = repository.findById(gastoAlterado.getId());


        Double gastoAtualizado = Double.parseDouble(gastoAlterado.getValor()) - Double.parseDouble(gastoSemAlteracao.getValor());

        int retorno = repository.updateGastos(gastoAlterado.getNome(), gastoAlterado.getTipo(), gastoAlterado.getValor(),
                gastoAlterado.getData(), gastoAlterado.getId());
        GastosRespostaDTO gastosRespostaDTO = new GastosRespostaDTO(gastoAlterado);
        if (retorno == 1) {

            ContaDTOAlterar contaDTOAlterar = new ContaDTOAlterar(String.valueOf(gastoSemAlteracao.getOrigem()), gastoSemAlteracao.getUsuario());
            ContaDTORetorno contaSelecionada = servicao.pegaConta(contaDTOAlterar);

            Double valorConta = Double.parseDouble(contaSelecionada.getSaldo());

            Double valorAtualizado = valorConta - gastoAtualizado;

            contaSelecionada.setSaldo(String.valueOf(valorAtualizado));

            ContaDTOAlterarFull contaDTOAlterarFull = new ContaDTOAlterarFull(contaSelecionada);
            ContaDTORetorno contaAlterada = servicao.atualizaConta(contaDTOAlterarFull);

            logger.info(String.format("Gasto alterado com sucesso"));
            return new ResponseEntity<>(gastosRespostaDTO, HttpStatus.OK);
        } else {
            logger.info(String.format("Gasto não alterado"));
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
