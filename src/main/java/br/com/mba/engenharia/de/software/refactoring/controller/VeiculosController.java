package br.com.mba.engenharia.de.software.refactoring.controller;

import br.com.mba.engenharia.de.software.refactoring.dto.veiculos.VeiculosDTO;
import br.com.mba.engenharia.de.software.refactoring.dto.veiculos.VeiculosDTOFull;
import br.com.mba.engenharia.de.software.refactoring.dto.veiculos.VeiculosRespostaDTO;
import br.com.mba.engenharia.de.software.refactoring.entity.veiculos.Veiculos;
import br.com.mba.engenharia.de.software.refactoring.service.GenericService;
import br.com.mba.engenharia.de.software.repository.veiculos.VeiculosRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    private static final Logger logger = LoggerFactory.getLogger(VeiculosController.class);

    @Autowired
    private VeiculosRepository veiculosRepository;

    @PostMapping("/criarVeiculo")
    public ResponseEntity<?> salvar(@RequestBody VeiculosDTO veiculosDTO){

        Veiculos veiculos = veiculosDTO.parseGastosToDTOGastos();
        veiculos.setId(veiculosRepository.count()+1);

        Veiculos veiculosRetorno = veiculosRepository.save(veiculos);
        VeiculosRespostaDTO veiculosRespostaDTO = new VeiculosRespostaDTO(veiculosRetorno);

        logger.info(String.format("Veículo cadastrado corretamente"));
        return ResponseEntity.ok(veiculosRespostaDTO);
    }

    @PostMapping("/listarVeiculo")
    public ResponseEntity<List<VeiculosRespostaDTO>> listarVeiculo(@RequestBody String idUsuario) {
        List<Veiculos> veiculosList = veiculosRepository.findAll(Integer.parseInt(idUsuario));
        List<VeiculosRespostaDTO> veiculosRespostaDTOList = new ArrayList<>();
        for (Veiculos veiculos : veiculosList) {
            VeiculosRespostaDTO gastosRespostaDTO = veiculos.parseGastosToGastosRespostaDTO();
            veiculosRespostaDTOList.add(gastosRespostaDTO);
        }
        return ResponseEntity.ok(veiculosRespostaDTOList);
    }

    @PostMapping("/gerarRelatorioVeiculo")
    public ResponseEntity<FileSystemResource> gerarRelatorioVeiculo(@RequestBody VeiculosDTOFull veiculosDTOFull) throws IOException {
        String filename = "veiculos.csv";
        GenericService genericService = new GenericService();
        InputStreamResource file = new InputStreamResource(genericService.load(filename));
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

        Veiculos veiculoRetorno = veiculosRepository.findById(Integer.parseInt(id));
        VeiculosRespostaDTO veiculosRespostaDTO = veiculoRetorno.parseGastosToGastosRespostaDTO();

        veiculosRepository.delete(Integer.parseInt(id));

        logger.info(String.format("Veículo deletado com sucesso"));
        return ResponseEntity.ok(veiculosRespostaDTO);

    }

    @PostMapping("/recebeDadosAlterarVeiculo")
    public ResponseEntity<?> recebeDadosAlterarVeiculo(@RequestBody Veiculos veiculos){
        return ResponseEntity.ok(veiculosRepository.findById(veiculos.getId()));
    }

    @PutMapping("alterarVeiculo")
    public ResponseEntity<?> alterarVeiculo(@RequestBody VeiculosDTOFull veiculosDTOFull){
        Veiculos veiculosAlterado = veiculosDTOFull.parseGastosToDTOFullToGastos();
        Veiculos veiculosSemAlteracao = veiculosRepository.findById(veiculosAlterado.getId());

        VeiculosRespostaDTO veiculosRespostaDTO = new VeiculosRespostaDTO(veiculosAlterado);
        veiculosRepository.updateVeiculos(veiculosAlterado.getPlaca(), veiculosAlterado.getModelo(), veiculosAlterado.getMarca(),
                veiculosAlterado.getAno(), veiculosAlterado.getId(), veiculosAlterado.getUsuario());

        logger.info(String.format("Veículo alterado com sucesso"));
        return new ResponseEntity<>(veiculosRespostaDTO, HttpStatus.OK);
    }

}

