package br.com.mba.engenharia.de.software.controller;

import br.com.mba.engenharia.de.software.entity.usuarios.Usuario;
import br.com.mba.engenharia.de.software.output.SenderMail;
import br.com.mba.engenharia.de.software.repository.usuario.UsuarioRepositoryNovo;
import br.com.mba.engenharia.de.software.security.ComplexidadeSenha;
import br.com.mba.engenharia.de.software.security.Criptrografia;
import br.com.mba.engenharia.de.software.security.GerarToken;
import br.com.mba.engenharia.de.software.service.usuarios.UserManager;
import br.com.mba.engenharia.de.software.service.usuarios.UserService;
import br.com.mba.engenharia.de.software.utils.ValidadorCPF;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController{
    private static final Logger logger = LoggerFactory.getLogger(Usuario.class);

    @Autowired
    UsuarioRepositoryNovo usuarioRepositoryNovo;

    UserService userService = userService();

    @Bean
    public UserService userService(){
        UserManager userManager = new UserManager(usuarioRepositoryNovo);
        return userManager;
    }

    public UsuarioController() {
        this.userService = userService();
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
        usuario.setStatus("0");
        userService.setRepository(usuarioRepositoryNovo);
        usuario.setId(userService.count());
        userService.save(usuario);
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
        userService.setRepository(usuarioRepositoryNovo);
        if (userService.findByTokenUsernameSenhaAndStatusAndUpdateStatus(user.getToken(),
                user.getUsername(), user.getSenha(), "0") == 1){
            return ResponseEntity.ok(Usuario.class);
        }
        else{
            return ResponseEntity.ok(null);
        }
    }
}

