package br.com.mba.engenharia.de.software.controller;

import br.com.mba.engenharia.de.software.negocio.usuarios.Usuario;
import br.com.mba.engenharia.de.software.output.SenderMail;
import br.com.mba.engenharia.de.software.security.ComplexidadeSenha;
import br.com.mba.engenharia.de.software.security.Criptrografia;
import br.com.mba.engenharia.de.software.security.GerarToken;
import br.com.mba.engenharia.de.software.utils.ValidadorCPF;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class UsuarioController{
    private static final Logger logger = LoggerFactory.getLogger(Usuario.class);

    @GetMapping("/redirectCadastrarUsuario")
    public String cadastrarUsuario(HttpServletResponse response) throws IOException {
            response.sendRedirect("/cadastrarUsuario");
            return null;
    }

    @GetMapping("/enviarCadastro")
    public String enviarCadastro(@RequestParam(name="username") String username, @RequestParam(name="password") String password,
                                 @RequestParam(name="nome") String nome, @RequestParam(name="sobrenome") String sobrenome,
                                 @RequestParam(name="email") String email, @RequestParam(name="cpf") String cpf,
                                 HttpServletResponse response) throws IOException {
        GerarToken gerarToken = new GerarToken();
        Usuario usuario = new Usuario();
        ValidadorCPF validadorCPF = new ValidadorCPF();
        if (validadorCPF.isValid(cpf)){
            usuario.setCpf(cpf);
        }
        else {
            return "CPF inválido";
        }
        usuario.setUsername(username);
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSobrenome(sobrenome);
        Criptrografia criptrografia = new Criptrografia();
        ComplexidadeSenha  complexidadeSenha = new ComplexidadeSenha();
        if (complexidadeSenha.isStronger(password)){
            usuario.setSenha(criptrografia.criptografar(password));
        }
        else {
            return "Senha fraca";
        }
        usuario.setToken(gerarToken.gerarToken());
        Controller controller = new Controller();
        controller.setController(usuario);
        if (controller.cadastrarUsuario()){
            if (SenderMail.sendEmail(usuario)){
                logger.info(String.format("Usuário cadastrado corretamente!"));
                logger.info(String.format("Token enviado com sucesso!"));
                response.sendRedirect("/habilitar");
            }
            else {
                logger.error(String.format("Erro no envio do e-mail"));
                return "Erro no envio do email";
            }
        }
        else {
            logger.error(String.format("Erro na conexao"));
            return "Erro na conexao";
        }
        return null;
    }

    @GetMapping("/habilitarUsuario")
    public String habiblitarUsuario(@RequestParam(name="username") String usuario, @RequestParam(name="password") String senha,
                                    @RequestParam(name="token") String token, @RequestParam(name="cpf") String cpf,
                                    HttpServletResponse response) throws IOException, InstantiationException, IllegalAccessException {
        Usuario user = new Usuario();
        user.setCpf(cpf);
        Criptrografia criptrografia = new Criptrografia();
        user.setSenha(criptrografia.criptografar(senha));
        user.setToken(token);
        user.setUsername(usuario);
        Controller controller = new Controller();
        controller.setController(user);
        if (controller.desbloquearUsuario()){
            response.sendRedirect("/usuarioHabilitado");
        }
        else{
            response.sendRedirect("/usuarioDesabilitado");
        }
        return null;
    }
}

