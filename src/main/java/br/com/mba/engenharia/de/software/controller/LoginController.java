package br.com.mba.engenharia.de.software.controller;

import br.com.mba.engenharia.de.software.entity.Users;
import br.com.mba.engenharia.de.software.model.login.Login;
import br.com.mba.engenharia.de.software.negocio.usuarios.Usuario;
import br.com.mba.engenharia.de.software.security.Criptrografia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static br.com.mba.engenharia.de.software.controller.UserLinks.AUTHENTICATE;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController{
    private static final Logger logger = LoggerFactory.getLogger(Login.class);
    @GetMapping(path = UserLinks.LIST_USERS)
    ResponseEntity<?> listUsers() {
        Controller controller = new Controller();
        return ResponseEntity.ok(controller.consultarTodosUsuarios());
    }
    @GetMapping("/redirectLogout")
    public String logout() {
        return "logout";
    }
    @PostMapping(path = AUTHENTICATE)
    public ResponseEntity<?> authenticate(@RequestBody Users user) throws IOException {
        Usuario usuario = new Usuario();
        usuario.setUsername(user.getUsername());
        Criptrografia criptrografia = new Criptrografia();
        usuario.setSenha(criptrografia.criptografar(user.getPassword()));
        Controller controller = new Controller();
        controller.setController(usuario);
        if(user.getUsername().equals("professor@gmail.com") && criptrografia.criptrografia(user.getPassword())){
            return ResponseEntity.ok(usuario);
        }
        else if (controller.consultarUsuario()){
            return ResponseEntity.ok(usuario);
        }
        else {
            return ResponseEntity.ok(null);
        }
    }
}
