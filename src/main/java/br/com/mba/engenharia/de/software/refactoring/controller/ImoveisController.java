package br.com.mba.engenharia.de.software.refactoring.controller;

import br.com.mba.engenharia.de.software.refactoring.dto.imoveis.ImoveisAlterarDTO;
import br.com.mba.engenharia.de.software.refactoring.dto.imoveis.ImoveisDTO;
import br.com.mba.engenharia.de.software.refactoring.dto.imoveis.ImoveisDTOFull;
import br.com.mba.engenharia.de.software.refactoring.dto.imoveis.ImoveisRetornoDTO;
import br.com.mba.engenharia.de.software.refactoring.entity.imoveis.Imoveis;
import br.com.mba.engenharia.de.software.repository.imoveis.ImoveisRepository;
import br.com.mba.engenharia.de.software.service.imoveis.CSVImoveisService;
import br.com.mba.engenharia.de.software.service.imoveis.ImoveisManager;
import br.com.mba.engenharia.de.software.service.imoveis.ImoveisService;
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
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ImoveisController{
    private static final Logger logger = LoggerFactory.getLogger(Imoveis.class);

    @Autowired
    ImoveisRepository repository;

    CSVImoveisService fileService;

    ImoveisService imoveisService;

    @Bean
    public ImoveisService imoveisService(){
        return new ImoveisManager(repository);
    }

    @Bean
    public CSVImoveisService csvImoveisService() {
        this.fileService = new CSVImoveisService();
        return fileService;
    }

    public ImoveisController() {
        this.imoveisService = imoveisService();
        this.fileService = csvImoveisService();
    }

    @PostMapping("/criarImovel")
    public ResponseEntity<Imoveis> salvar(@RequestBody ImoveisDTO imoveisDTO){
        imoveisService.setRepository(repository);
        Imoveis imoveis = imoveisDTO.parseImoveisDTOToImovel();

        imoveis.setId(imoveisService.count() + 1);
        Imoveis imoveisRetorno = imoveisService.save(imoveis);
        ImoveisRetornoDTO imoveisRetornoDTO = new ImoveisRetornoDTO(imoveisRetorno);


        logger.info(String.format("Imóvel cadastrado corretamente"));
        return ResponseEntity.ok(imoveisRetorno);

    }

    @PostMapping("/listarImovel")
    public ResponseEntity<List<Imoveis>> listarImoveis(@RequestBody String req){
        imoveisService.setRepository(repository);
        List<Imoveis> imoveisList = imoveisService.findAll(Integer.parseInt(req));
        return ResponseEntity.ok(imoveisList);
    }

    @PostMapping("/gerarRelatorioImovel")
    public ResponseEntity<FileSystemResource> gerarRelatorioImoveis(@RequestBody ImoveisDTOFull imoveisDTOFull) throws IOException {
        fileService.setUsuario(Integer.parseInt(String.valueOf(imoveisDTOFull.getUsuario())));
        String filename = "rendas.csv";
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

    @DeleteMapping("/deletarImovel/{id}")
    public ResponseEntity<?> deletarImovel(@PathVariable("id") String id){
        imoveisService.setRepository(repository);
        Imoveis imoveis = new Imoveis();
        imoveis.setId(Integer.parseInt(id));
        Imoveis imoveisRetorno = imoveisService.findById(imoveis.getId());
        ImoveisRetornoDTO imoveisRetornoDTO = imoveisRetorno.parseImoveisToImoveisRetornoDTO();
        if (imoveisService.delete(imoveis.getId()) > 0) {
            logger.info(String.format("Imovel excluído com sucesso"));
            return ResponseEntity.ok(imoveisRetorno);
        }
        logger.info(String.format("Falha na exclusão"));
        return ResponseEntity.ok(null);
    }
    @PostMapping("/recebeDadosAlterarImovel")
    public ResponseEntity<?> recebeDadosAlterarRenda(@RequestBody ImoveisDTOFull imoveisDTOFull){
        Imoveis imoveis = imoveisDTOFull.parseImoveisDTOFullToImovel();
        imoveisService.setRepository(repository);
        Imoveis imoveisRetorno  = imoveisService.findById(imoveis.getId());
        ImoveisRetornoDTO imoveisRetornoDTO = imoveisRetorno.parseImoveisToImoveisRetornoDTO();
        if (imoveisRetornoDTO != null){
            return ResponseEntity.ok(imoveisRetorno);
        }
        else{
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("alterarImovel")
    public ResponseEntity<?> alterarRenda(@RequestBody ImoveisAlterarDTO imoveisAlterarDTO){
        Imoveis imoveis = imoveisAlterarDTO.parseImoveisAlterarDTOToImovel();
        imoveisService.setRepository(repository);
        Imoveis imoveisRetorno = imoveisService.findById(imoveis.getId());

        ImoveisRetornoDTO imoveisRetornoDTO = imoveisRetorno.parseImoveisToImoveisRetornoDTO();
        Integer retorno = repository.updateImoveis(imoveis.getCep(), imoveis.getLogradouro(), imoveis.getRua(),
                imoveis.getNumero(), imoveis.getBairro(), imoveis.getCidade(),imoveis.getEstado(), imoveis.getPais(),
                imoveis.getUsuario(), imoveis.getId());
        if (retorno > 0){
            logger.info(String.format("Imovel alterado com sucesso!"));
            return ResponseEntity.ok(imoveisRetorno);
        }
        else{
            logger.info(String.format("Não foi possivel alterar a imoveis"));
            return ResponseEntity.ok(null);
        }
    }

}

