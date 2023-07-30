package br.com.mba.engenharia.de.software.controller;

import br.com.mba.engenharia.de.software.entity.contas.Conta;
import br.com.mba.engenharia.de.software.entity.usuarios.Usuario;
import br.com.mba.engenharia.de.software.repository.contas.ContaRepository;
import br.com.mba.engenharia.de.software.security.GerarToken;
import br.com.mba.engenharia.de.software.service.contas.ContaManager;
import br.com.mba.engenharia.de.software.service.contas.ContaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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
    public ResponseEntity<?> salvar(@RequestBody Conta conta){
        GerarToken gerarToken = new GerarToken();
        Usuario usuario = new Usuario();
        usuario.setCpf("11111111111");
        usuario.setUsername("wmene");
        usuario.setNome("Willian");
        usuario.setEmail("wmene@gmail.com");
        usuario.setSobrenome("Menezes");
        usuario.setSenha("123456");
        usuario.setToken(gerarToken.gerarToken());
        usuario.setId(1);
        conta.setUsuario(usuario.getId());
        contaService.setContaRepository(contaRepository);
        conta.setId(contaService.count());
        contaService.save(conta);
        logger.info(String.format("Usuário cadastrado corretamente"));
        return ResponseEntity.ok(conta);
    }

    @GetMapping("/listarConta")
    public ResponseEntity<?> listarConta(){
        contaService.setContaRepository(contaRepository);
        return ResponseEntity.ok(contaService.findAll());
    }

    @DeleteMapping("/deletarConta/{id}")
    public ResponseEntity<?> deletarConta(@PathVariable("id") String id){
        contaService.setContaRepository(contaRepository);
        Conta conta = new Conta();
        conta.setId(Integer.parseInt(id));
        if (contaRepository.delete(conta.getId()) == 1){
            logger.info(String.format("Usuário deletado com sucesso"));
            return ResponseEntity.ok(conta);
        }
        else{
            logger.info(String.format("Falha na exclusão"));
            return null;
        }
    }
}
