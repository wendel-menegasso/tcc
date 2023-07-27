package br.com.mba.engenharia.de.software.controller;

import br.com.mba.engenharia.de.software.entity.contas.Conta;
import br.com.mba.engenharia.de.software.entity.usuarios.Usuario;
import br.com.mba.engenharia.de.software.security.GerarToken;
import br.com.mba.engenharia.de.software.service.contas.ContaManager;
import br.com.mba.engenharia.de.software.service.contas.ContaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class ContaController{
    private static final Logger logger = LoggerFactory.getLogger(Conta.class);

    ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping("/criarConta")
    public ResponseEntity<?> salvar(@RequestBody Conta conta) throws IOException {
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
        contaService.save(conta);
        logger.info(String.format("Usuário cadastrado corretamente"));
        return ResponseEntity.ok(conta);
    }
    @PostMapping("/listarConta")
    public ResponseEntity<?> listarConta() throws IOException {
        return ResponseEntity.ok(contaService.lastRegister());
    }
}
