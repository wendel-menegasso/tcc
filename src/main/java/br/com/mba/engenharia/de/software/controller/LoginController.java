package br.com.mba.engenharia.de.software.controller;

import br.com.mba.engenharia.de.software.entity.usuarios.Users;
import br.com.mba.engenharia.de.software.model.login.Login;
import br.com.mba.engenharia.de.software.entity.usuarios.Usuario;
import br.com.mba.engenharia.de.software.security.Criptrografia;
import br.com.mba.engenharia.de.software.security.GerarToken;
import br.com.mba.engenharia.de.software.service.usuarios.UserManager;
import br.com.mba.engenharia.de.software.service.usuarios.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static br.com.mba.engenharia.de.software.controller.UserLinks.AUTHENTICATE;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController{

    private static final Logger logger = LoggerFactory.getLogger(Login.class);

    UserManager userService;

    @Autowired
    LoginController(UserManager userService){
        this.userService = userService;
    }

    @GetMapping(path = UserLinks.LIST_USERS)
    ResponseEntity<?> listUsers() {
        return ResponseEntity.ok(userService.listarTodosUsuarios());
    }
    @GetMapping(path = "logout")
    public String logout() {
        return "logout";
    }
    @PostMapping(path = AUTHENTICATE)
    public ResponseEntity<?> authenticate(@RequestBody Users user) throws IOException {
        Usuario usuario = new Usuario();
        usuario.setUsername(user.getUsername());
        Criptrografia criptrografia = new Criptrografia();
        usuario.setSenha(criptrografia.criptografar(user.getPassword()));

        List<Usuario> usuarioList = userService.procuraRegistro(usuario);
        if(user.getUsername().equals("professor@gmail.com") && criptrografia.criptrografia(user.getPassword())){
            user.setPassword("");
            return ResponseEntity.ok(user);
        }
        else if (usuarioList.size() == 1){
            user.setPassword("");
            user.setUsername("");
            GerarToken gerarToken = new GerarToken();
            usuario.setToken(gerarToken.gerarToken());
            usuario.setId(usuarioList.get(0).getId());
            user.setToken(usuario.getToken());
            user.setId(usuarioList.get(0).getId());
            user.setName(usuarioList.get(0).getNome());
            return ResponseEntity.ok(user);
        }
        else {
            return ResponseEntity.ok(null);
        }
    }
}
