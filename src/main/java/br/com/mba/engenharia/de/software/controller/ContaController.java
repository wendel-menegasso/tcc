package br.com.mba.engenharia.de.software.controller;

import br.com.mba.engenharia.de.software.dto.ContaDTO;
import br.com.mba.engenharia.de.software.dto.ContaDTORetorno;
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
@CrossOrigin(origins = "http://localhost:4200")
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

    @GetMapping("/listarConta")
    public ResponseEntity<?> listarConta(){
        contaService.setContaRepository(contaRepository);

        List<Conta> contaList = contaService.findAll();
        List<ContaDTORetorno> contaDTORetornoList = new ArrayList<>();
        for (Conta conta : contaList){
            contaDTORetornoList.add(conta.parseContaToContaDTORetorno());
        }
        return new ResponseEntity<>(contaDTORetornoList, HttpStatus.OK);
    }

    @DeleteMapping("/deletarConta/{id}")
    public ResponseEntity<?> deletarConta(@PathVariable("id") String id){
        contaService.setContaRepository(contaRepository);
        Conta conta = contaRepository.delete(Integer.parseInt(id));
        ContaDTORetorno contaDTORetorno = conta.parseContaToContaDTORetorno();
        if (contaDTORetorno != null){
            logger.info(String.format("Usuário deletado com sucesso"));
            return new ResponseEntity<>(contaDTORetorno, HttpStatus.OK);
        }
        else{
            logger.info(String.format("Falha na exclusão"));
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
    @PostMapping("/recebeDadosAlterarConta")
    public ResponseEntity<?> recebeDadosAlterarConta(@RequestBody ContaDTO contaDTO){
        contaService.setContaRepository(contaRepository);
        Conta conta = contaDTO.parseContaDTOToConta();
        Conta contaRetorno = contaService.findById(conta.getId());
        ContaDTORetorno contaDTORetorno = contaRetorno.parseContaToContaDTORetorno();
        return new ResponseEntity<>(contaDTORetorno, HttpStatus.OK);
    }

    @PutMapping("alterarConta")
    public ResponseEntity<?> alterarConta(@RequestBody ContaDTO contaDTO){
        contaService.setContaRepository(contaRepository);
        Conta conta = contaDTO.parseContaDTOToConta();
        Conta contaRetorno = contaRepository.updateConta(conta.getBanco(), conta.getTipo(), conta.getSaldo(), conta.getAgencia(), conta.getConta(), conta.getId());
        ContaDTORetorno contaDTORetorno = contaRetorno.parseContaToContaDTORetorno();
        if (contaDTORetorno != null){
            return new ResponseEntity<>(contaDTORetorno, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

}
