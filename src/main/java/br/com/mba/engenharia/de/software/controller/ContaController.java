package br.com.mba.engenharia.de.software.controller;

import br.com.mba.engenharia.de.software.dto.contas.*;
import br.com.mba.engenharia.de.software.entity.contas.Conta;
import br.com.mba.engenharia.de.software.repository.contas.ContaRepository;
import br.com.mba.engenharia.de.software.service.contas.CSVContasService;
import br.com.mba.engenharia.de.software.service.contas.ContaManager;
import br.com.mba.engenharia.de.software.service.contas.ContaService;
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
public class ContaController {
    private static final Logger logger = LoggerFactory.getLogger(Conta.class);

    @Autowired
    ContaRepository contaRepository;


    @Autowired
    CSVContasService fileService;

    ContaService contaService;

    @Bean
    public ContaService contaService() {
        ContaManager contaManager = new ContaManager(contaRepository);
        return contaManager;
    }

    @Bean
    public CSVContasService csvContasService() {
        fileService = new CSVContasService();
        return fileService;
    }

    public ContaController(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
        this.contaService = contaService();
        this.fileService = csvContasService();
    }

    public ContaController(){
        this.contaService = contaService();
        this.fileService = csvContasService();
    }

    @PostMapping("/criarConta")
    public ResponseEntity<?> salvar(@RequestBody ContaDTO contaDTO) {
        Conta conta = contaDTO.parseContaDTOToConta();
        contaService.setContaRepository(contaRepository);
        conta.setId(contaService.count());
        Conta contaRetorno = contaService.save(conta);
        ContaDTORetorno contaDTORetorno = contaRetorno.parseContaToContaDTORetorno();
        if (contaDTORetorno != null) {
            logger.info(String.format("Usuário cadastrado corretamente"));
            return new ResponseEntity<>(contaDTORetorno, HttpStatus.CREATED);
        } else {
            logger.info(String.format("Falha ao cadastrar"));
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

    }

    @PostMapping("/gerarRelatorioConta")
    public ResponseEntity<FileSystemResource> gerarRelatorioConta(@RequestBody ContaDTOFull contaDTOFull) throws IOException {
        fileService.setUsuario(Integer.parseInt(contaDTOFull.getUsuario()));
        String filename = "contas.csv";
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

    @PostMapping("/listarConta")
    public ResponseEntity<?> listarConta(@RequestBody ContaDTOFull contaDTOFull) {
        contaService.setContaRepository(contaRepository);
        Integer usuario = Integer.parseInt(contaDTOFull.getUsuario());
        List<Conta> contaList = contaService.findAll(usuario);
        List<ContaDTORetorno> contaDTORetornoList = new ArrayList<>();
        if (contaList.size() > 0) {
            for (Conta conta : contaList) {
                contaDTORetornoList.add(conta.parseContaToContaDTORetorno());
            }
            return ResponseEntity.ok(contaDTORetornoList);
        } else {
            return ResponseEntity.ok(null);
        }
    }

    @DeleteMapping("/deletarConta/{id}")
    public ResponseEntity<?> deletarConta(@PathVariable("id") String id) {
        contaService.setContaRepository(contaRepository);
        Conta contaRetorno = contaService.findById(Integer.parseInt(id));
        ContaDTORetorno contaDTORetorno = contaRetorno.parseContaToContaDTORetorno();
        try {
            Integer retorno = contaService.delete(Integer.parseInt(id));
            if (retorno > 0) {
                logger.info(String.format("Usuário deletado com sucesso"));
                return new ResponseEntity<>(contaDTORetorno, HttpStatus.OK);
            } else {
                logger.info(String.format("Falha na exclusão"));
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
        } catch (RuntimeException exception) {
            logger.info(exception.getMessage());
            return ResponseEntity.ok(null);
        }
    }

    @PostMapping("/recebeDadosAlterarConta")
    public ResponseEntity<?> recebeDadosAlterarConta(@RequestBody ContaDTOAlterar contaDTOAlterar) {
        contaService.setContaRepository(contaRepository);
        Conta conta = contaDTOAlterar.parseContaDTOAlterarToConta();
        Conta contaRetorno = contaService.findById(conta.getId());
        ContaDTORetorno contaDTORetorno = contaRetorno.parseContaToContaDTORetorno();
        return ResponseEntity.ok(contaDTORetorno);
    }

    @PutMapping("alterarConta")
    public ResponseEntity<?> alterarConta(@RequestBody ContaDTOAlterarFull contaDTOAlterarFull) {
        contaService.setContaRepository(contaRepository);
        Conta conta = contaDTOAlterarFull.parseContaDTOToConta();
        Conta contaRetorno = contaService.findById(conta.getId());
        ContaDTORetorno contaDTORetorno = contaRetorno.parseContaToContaDTORetorno();
        Integer numeroDeRegistrosAlterados = contaRepository.updateConta(conta.getBanco(), conta.getTipo(), conta.getSaldo(), conta.getAgencia(), conta.getConta(), conta.getId(), conta.getUsuario());
        if (numeroDeRegistrosAlterados > 0) {
            return ResponseEntity.ok(contaDTORetorno);
        } else {
            return ResponseEntity.ok(null);
        }
    }

    public ContaDTORetorno pegaConta(ContaDTOAlterar contaDTOAlterar) {
        contaService.setContaRepository(contaRepository);
        Conta conta = contaDTOAlterar.parseContaDTOAlterarToConta();
        Conta contaRetorno = contaService.findById(conta.getId());
        ContaDTORetorno contaDTORetorno = contaRetorno.parseContaToContaDTORetorno();
        return contaDTORetorno;
    }

    public ContaDTORetorno atualizaConta(ContaDTOAlterarFull contaDTOAlterarFull) {
        contaService.setContaRepository(contaRepository);
        Conta conta = contaDTOAlterarFull.parseContaDTOToConta();
        Conta contaRetorno = contaService.findById(conta.getId());
        ContaDTORetorno contaDTORetorno = contaRetorno.parseContaToContaDTORetorno();
        Integer numeroDeRegistrosAlterados = contaRepository.updateConta(conta.getBanco(), conta.getTipo(), conta.getSaldo(), conta.getAgencia(), conta.getConta(), conta.getId(), conta.getUsuario());
        if (numeroDeRegistrosAlterados > 0) {
            return contaDTORetorno;
        } else {
            return null;
        }
    }

}
