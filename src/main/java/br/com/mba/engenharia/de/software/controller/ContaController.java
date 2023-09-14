package br.com.mba.engenharia.de.software.controller;

import br.com.mba.engenharia.de.software.dto.*;
import br.com.mba.engenharia.de.software.entity.contas.Conta;
import br.com.mba.engenharia.de.software.repository.contas.ContaRepository;
import br.com.mba.engenharia.de.software.service.contas.ContaManager;
import br.com.mba.engenharia.de.software.service.contas.ContaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ContaController{
    private static final Logger logger = LoggerFactory.getLogger(Conta.class);

    @Autowired
    ContaRepository contaRepository;

    ContaService contaService;

    @Bean
    public ContaService contaService(){
        ContaManager contaManager = new ContaManager(contaRepository);
        return contaManager;
    }

    public ContaController() {
        this.contaService = contaService();
    }

    @PostMapping("/criarConta")
    public ResponseEntity<?> salvar(@RequestBody ContaDTO contaDTO){
        Conta conta = contaDTO.parseContaDTOToConta();
        contaService.setContaRepository(contaRepository);
        conta.setId(contaService.count());
        Conta contaRetorno = contaService.save(conta);
        ContaDTORetorno contaDTORetorno = contaRetorno.parseContaToContaDTORetorno();
        if (contaDTORetorno != null){
            logger.info(String.format("Usuário cadastrado corretamente"));
            return new ResponseEntity<>(contaDTORetorno, HttpStatus.CREATED);
        }
        else{
            logger.info(String.format("Falha ao cadastrar"));
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

    }

    @PostMapping("/listarConta")
    public ResponseEntity<?> listarConta(@RequestBody ContaDTOFull contaDTOFull){
        logger.info(String.format("Entrou no método listarConta"));
        contaService.setContaRepository(contaRepository);
        Integer usuario = Integer.parseInt(contaDTOFull.getUsuario());
        List<Conta> contaList = contaService.findAll(usuario);
        List<ContaDTORetorno> contaDTORetornoList = new ArrayList<>();
        if (contaList.size() > 0){
            for (Conta conta : contaList){
                contaDTORetornoList.add(conta.parseContaToContaDTORetorno());
                logger.info(String.format("---"+conta.getConta()));
            }
            return ResponseEntity.ok(contaDTORetornoList);
        }
        else{
            return  ResponseEntity.ok(null);
        }
    }

    @DeleteMapping("/deletarConta/{id}")
    public ResponseEntity<?> deletarConta(@PathVariable("id") String id){
        contaService.setContaRepository(contaRepository);
        Conta contaRetorno = contaService.findById(Integer.parseInt(id));
        ContaDTORetorno contaDTORetorno = contaRetorno.parseContaToContaDTORetorno();
        Integer retorno = contaService.delete(Integer.parseInt(id));
        if (retorno > 0){
            logger.info(String.format("Usuário deletado com sucesso"));
            return new ResponseEntity<>(contaDTORetorno, HttpStatus.OK);
        }
        else{
            logger.info(String.format("Falha na exclusão"));
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
    @PostMapping("/recebeDadosAlterarConta")
    public ResponseEntity<?> recebeDadosAlterarConta(@RequestBody ContaDTOAlterar contaDTOAlterar){
        contaService.setContaRepository(contaRepository);
        Conta conta = contaDTOAlterar.parseContaDTOAlterarToConta();
        Conta contaRetorno = contaService.findById(conta.getId());
        ContaDTORetorno contaDTORetorno = contaRetorno.parseContaToContaDTORetorno();
        return new ResponseEntity<>(contaDTORetorno, HttpStatus.OK);
    }

    @PutMapping("alterarConta")
    public ResponseEntity<?> alterarConta(@RequestBody ContaDTOAlterarFull contaDTOAlterarFull){
        contaService.setContaRepository(contaRepository);
        Conta conta = contaDTOAlterarFull.parseContaDTOToConta();
        Conta contaRetorno = contaService.findById(conta.getId());
        ContaDTORetorno contaDTORetorno = contaRetorno.parseContaToContaDTORetorno();
        Integer numeroDeRegistrosAlterados = contaRepository.updateConta(conta.getBanco(), conta.getTipo(), conta.getSaldo(), conta.getAgencia(), conta.getConta(), conta.getId(), conta.getUsuario());
        if (numeroDeRegistrosAlterados > 0){
            return new ResponseEntity<>(contaDTORetorno, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

}
