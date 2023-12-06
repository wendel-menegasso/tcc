package br.com.mba.engenharia.de.software.controller;

import br.com.mba.engenharia.de.software.dto.VeiculosDTO;
import br.com.mba.engenharia.de.software.dto.VeiculosDTOFull;
import br.com.mba.engenharia.de.software.dto.VeiculosRespostaDTO;
import br.com.mba.engenharia.de.software.entity.veiculos.Veiculos;
import br.com.mba.engenharia.de.software.repository.veiculos.VeiculosRepository;
import br.com.mba.engenharia.de.software.service.veiculos.CSVVeiculosService;
import br.com.mba.engenharia.de.software.service.veiculos.VeiculosManager;
import br.com.mba.engenharia.de.software.service.veiculos.VeiculosService;
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
public class VeiculosController {

    private static final Logger logger = LoggerFactory.getLogger(Veiculos.class);

    @Autowired
    VeiculosRepository veiculosRepository;

    @Autowired
    CSVVeiculosService fileService;

    VeiculosService veiculosService;

    @Bean
    public VeiculosService veiculosService(){
        VeiculosManager veiculosManager = new VeiculosManager();
        veiculosManager.setVeiculosRepository(veiculosRepository);
        return veiculosManager;
    }

    @Bean
    public CSVVeiculosService csvGastosService() {
        fileService = new CSVVeiculosService();
        return fileService;
    }

    public VeiculosController() {
        this.veiculosService = veiculosService();
        this.fileService = csvGastosService();
    }

    @PostMapping("/criarVeiculo")
    public ResponseEntity<?> salvar(@RequestBody VeiculosDTO veiculosDTO){
        veiculosService.setVeiculosRepository(veiculosRepository);

        Veiculos veiculos = veiculosDTO.parseGastosToDTOGastos();
        veiculos.setId(veiculosService.count()+1);

        Veiculos veiculosRetorno = veiculosService.save(veiculos);
        VeiculosRespostaDTO veiculosRespostaDTO = new VeiculosRespostaDTO(veiculosRetorno);

        logger.info(String.format("Veículo cadastrado corretamente"));
        return ResponseEntity.ok(veiculosRespostaDTO);
    }

    @PostMapping("/listarVeiculo")
    public ResponseEntity<List<VeiculosRespostaDTO>> listarVeiculo(@RequestBody String idUsuario) {
        veiculosService.setVeiculosRepository(veiculosRepository);
        List<Veiculos> veiculosList = veiculosService.findAll(Integer.parseInt(idUsuario));
        List<VeiculosRespostaDTO> veiculosRespostaDTOList = new ArrayList<>();
        for (Veiculos veiculos : veiculosList) {
            VeiculosRespostaDTO gastosRespostaDTO = veiculos.parseGastosToGastosRespostaDTO();
            veiculosRespostaDTOList.add(gastosRespostaDTO);
        }
        return ResponseEntity.ok(veiculosRespostaDTOList);
    }

    @PostMapping("/gerarRelatorioVeiculo")
    public ResponseEntity<FileSystemResource> gerarRelatorioVeiculo(@RequestBody VeiculosDTOFull veiculosDTOFull) throws IOException {
        fileService.setUsuario(Integer.parseInt(String.valueOf(veiculosDTOFull.getUsuario())));
        String filename = "veiculos.csv";
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

    @DeleteMapping("/deletarVeiculo/{id}")
    public ResponseEntity<?> deletarVeiculo(@PathVariable("id") String id){
        veiculosService.setVeiculosRepository(veiculosRepository);

        Veiculos veiculoRetorno = veiculosService.findById(Integer.parseInt(id));
        VeiculosRespostaDTO veiculosRespostaDTO = veiculoRetorno.parseGastosToGastosRespostaDTO();

        veiculosService.delete(Integer.parseInt(id));

        logger.info(String.format("Veículo deletado com sucesso"));
        return ResponseEntity.ok(veiculosRespostaDTO);

    }

    @PostMapping("/recebeDadosAlterarVeiculo")
    public ResponseEntity<?> recebeDadosAlterarVeiculo(@RequestBody Veiculos veiculos){
        veiculosService.setVeiculosRepository(veiculosRepository);
        return ResponseEntity.ok(veiculosService.findById(veiculos.getId()));
    }

    @PutMapping("alterarVeiculo")
    public ResponseEntity<?> alterarVeiculo(@RequestBody VeiculosDTOFull veiculosDTOFull){
        veiculosService.setVeiculosRepository(veiculosRepository);
        Veiculos veiculosAlterado = veiculosDTOFull.parseGastosToDTOFullToGastos();
        Veiculos veiculosSemAlteracao = veiculosService.findById(veiculosAlterado.getId());

        VeiculosRespostaDTO veiculosRespostaDTO = new VeiculosRespostaDTO(veiculosAlterado);
        veiculosService.updateVeiculos(veiculosAlterado.getPlaca(), veiculosAlterado.getModelo(), veiculosAlterado.getMarca(),
                veiculosAlterado.getAno(), veiculosAlterado.getId(), veiculosAlterado.getUsuario());

        logger.info(String.format("Veículo alterado com sucesso"));
        return new ResponseEntity<>(veiculosRespostaDTO, HttpStatus.OK);
    }

}

