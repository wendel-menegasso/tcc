package br.com.mba.engenharia.de.software.controller;

import br.com.mba.engenharia.de.software.model.login.Login;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class MenuPrincipalController {
    private static final Logger logger = LoggerFactory.getLogger(Login.class);
    @GetMapping("/redirectMenuConta")
    String redirectMenuConta(HttpServletResponse response) throws IOException {
        response.sendRedirect("/menuConta");
        return null;
    }
    @GetMapping("/redirectCadastrarConta")
    String redirectCadastrarConta(HttpServletResponse response) throws IOException {
        response.sendRedirect("/cadastrarConta");
        return null;
    }
    @GetMapping("/redirectConsultarConta")
    String redirectConsultarConta(HttpServletResponse response) throws IOException {
        response.sendRedirect("/consultarConta");
        return null;
    }
}
