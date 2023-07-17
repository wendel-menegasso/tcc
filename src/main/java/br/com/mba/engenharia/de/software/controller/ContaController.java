package br.com.mba.engenharia.de.software.controller;

import br.com.mba.engenharia.de.software.entity.Users;
import br.com.mba.engenharia.de.software.negocio.contas.*;
import br.com.mba.engenharia.de.software.negocio.usuarios.Usuario;
import br.com.mba.engenharia.de.software.negocio.usuarios.UsuarioRepository;
import br.com.mba.engenharia.de.software.security.GerarToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ContaController{
    private static final Logger logger = LoggerFactory.getLogger(Conta.class);

    @PostMapping("/criarConta")
    public ResponseEntity<?> save(@RequestBody Conta conta) throws IOException {
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
        Controller controller = new Controller();
        controller.setController(usuario);

        if (controller.cadastrarConta(conta)){
            logger.info(String.format("Usuário cadastrado corretamente"));
            return ResponseEntity.ok(conta);
        }
        else {
            logger.info(String.format("Erro na conexao"));
            return ResponseEntity.ok(null);
        }
    }
    @GetMapping("/listarConta")
    public ResponseEntity<?> listarConta() throws IOException {
        Controller controller = new Controller();
        return ResponseEntity.ok(controller.listarTodasContas());
    }
}
