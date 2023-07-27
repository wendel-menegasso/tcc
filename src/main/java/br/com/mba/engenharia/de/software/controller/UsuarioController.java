package br.com.mba.engenharia.de.software.controller;

import br.com.mba.engenharia.de.software.entity.usuarios.Usuario;
import br.com.mba.engenharia.de.software.output.SenderMail;
import br.com.mba.engenharia.de.software.security.ComplexidadeSenha;
import br.com.mba.engenharia.de.software.security.Criptrografia;
import br.com.mba.engenharia.de.software.security.GerarToken;
import br.com.mba.engenharia.de.software.service.usuarios.UserManager;
import br.com.mba.engenharia.de.software.utils.ValidadorCPF;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController{
    private static final Logger logger = LoggerFactory.getLogger(Usuario.class);

    private UserManager userManager;

    public UsuarioController(UserManager userManager) {
        this.userManager = userManager;
    }

    @PostMapping("/enviarCadastro")
    public ResponseEntity<?> enviarCadastro(@RequestBody Usuario user) throws IOException {
        GerarToken gerarToken = new GerarToken();
        Usuario usuario = new Usuario();
        ValidadorCPF validadorCPF = new ValidadorCPF();
        if (validadorCPF.isValid(user.getCpf())){
            usuario.setCpf(user.getCpf());
        }
        else {
            return ResponseEntity.badRequest().build();
        }
        usuario.setUsername(user.getUsername());
        usuario.setNome(user.getNome());
        usuario.setEmail(user.getEmail());
        usuario.setSobrenome(user.getSobrenome());
        Criptrografia criptrografia = new Criptrografia();
        ComplexidadeSenha  complexidadeSenha = new ComplexidadeSenha();
        if (complexidadeSenha.isStronger(user.getSenha())){
            usuario.setSenha(criptrografia.criptografar(user.getSenha()));
        }
        else {
            return ResponseEntity.badRequest().build();
        }
        usuario.setToken(gerarToken.gerarToken());

            if (SenderMail.sendEmail(usuario)){
                logger.info(String.format("Usuário cadastrado corretamente!"));
                logger.info(String.format("Token enviado com sucesso!"));
                return ResponseEntity.ok(Usuario.class);

            }
            else {
                logger.error(String.format("Erro no envio do e-mail"));
                return ResponseEntity.badRequest().build();
            }
    }

    @PostMapping("/habilitarUsuario")
    public ResponseEntity<?>  habiblitarUsuario(@RequestBody Usuario usuario) throws IOException, InstantiationException, IllegalAccessException {
        Usuario user = new Usuario();
        user.setCpf(usuario.getCpf());
        Criptrografia criptrografia = new Criptrografia();
        user.setSenha(criptrografia.criptografar(usuario.getSenha()));
        user.setToken(usuario.getToken());
        user.setUsername(usuario.getUsername());
        if (userManager.habilitarUsuario(user)){
            return ResponseEntity.ok(Usuario.class);
        }
        else{
            return ResponseEntity.ok(null);
        }
    }
}

