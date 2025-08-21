package refactoring.controller;

import refactoring.dto.contas.*;
import refactoring.entity.contas.Conta;
import refactoring.repository.contas.ContaRepository;
import refactoring.service.GenericService;
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
public class ContaController {
    private static final Logger logger = LoggerFactory.getLogger(ContaController.class);

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private GenericService fileService;

    @PostMapping("/criarConta")
    public ResponseEntity<?> salvar(@RequestBody ContaDTO contaDTO) {
        Conta conta = contaDTO.parseContaDTOToConta();
        Conta contaRetorno = contaRepository.save(conta);
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
    public ResponseEntity<?> listarConta(@PathVariable("usuario") String user) {
        Integer usuario = Integer.parseInt(user);
        List<Conta> contaList = contaRepository.findAll(usuario);
        return (ResponseEntity<?>) contaList
                .stream()
                .map(ContaDTORetorno::new)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/deletarConta/{id}")
    public ResponseEntity<?> deletarConta(@PathVariable("id") String id) {
        Conta contaRetorno = contaRepository.findById(Integer.parseInt(id));
        ContaDTORetorno contaDTORetorno = contaRetorno.parseContaToContaDTORetorno();
        try {
            Integer retorno = contaRepository.delete(Integer.parseInt(id));
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
        Conta conta = contaDTOAlterar.parseContaDTOAlterarToConta();
        Conta contaRetorno = contaRepository.findById(conta.getId());
        ContaDTORetorno contaDTORetorno = contaRetorno.parseContaToContaDTORetorno();
        return ResponseEntity.ok(contaDTORetorno);
    }

    @PutMapping("alterarConta")
    public ResponseEntity<?> alterarConta(@RequestBody ContaDTOAlterarFull contaDTOAlterarFull) {
        Conta conta = contaDTOAlterarFull.parseContaDTOToConta();
        Conta contaRetorno = contaRepository.findById(conta.getId());
        ContaDTORetorno contaDTORetorno = contaRetorno.parseContaToContaDTORetorno();
        Integer numeroDeRegistrosAlterados = contaRepository.updateConta(conta.getBanco(), conta.getTipo(), conta.getSaldo(), conta.getAgencia(), conta.getConta(), conta.getId(), conta.getUsuario());
        if (numeroDeRegistrosAlterados > 0) {
            return ResponseEntity.ok(contaDTORetorno);
        } else {
            return ResponseEntity.ok(null);
        }
    }
}
