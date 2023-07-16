package br.com.mba.engenharia.de.software.controller;

import br.com.mba.engenharia.de.software.negocio.contas.*;
import br.com.mba.engenharia.de.software.negocio.usuarios.Usuario;
import br.com.mba.engenharia.de.software.negocio.usuarios.UsuarioRepository;
import br.com.mba.engenharia.de.software.security.GerarToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ContaController{
    private static final Logger logger = LoggerFactory.getLogger(Conta.class);

    @PostMapping("/criarConta")
    public String testConta(@RequestParam(name="conta") String numeroConta, @RequestParam(name="agencia") String agencia,
                            @RequestParam(name="tipoConta") String tipoConta, @RequestParam(name="banco") String bancoValue,
                            @RequestParam(name="saldo") String valor, HttpServletResponse response) throws IOException {
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
        Conta conta = new Conta();
        conta.setConta(numeroConta);
        conta.setAgencia(agencia);
        conta.setSaldo(Double.parseDouble(valor));
        Tipoconta tipoconta = TipoContaEnum.CC.getTipoconta(Integer.parseInt(tipoConta));
        conta.setTipo(tipoconta.getId());
        conta.setUsuario(usuario.getId());
        Banco banco = BancoEnum.BANCO_PAN.getBanco(Integer.parseInt(bancoValue));
        if (banco == null){
            return  null;
        }
        conta.setBanco(banco.getId());
        Controller controller = new Controller();
        controller.setController(usuario);

        if (controller.cadastrarConta(conta)){
            logger.info(String.format("Usuário cadastrado corretamente"));
            return "Cadastrado com sucesso<input type=hidden name=idUser>";
        }
        else {
            logger.info(String.format("Erro na conexao"));
            return "Erro na conexao";
        }
    }
    @PostMapping("/listarConta")
    public void listarConta(@RequestParam(name="conta") String numeroConta, @RequestParam(name="agencia") String agencia,
                            @RequestParam(name="tipoConta") String tipoConta, @RequestParam(name="banco") String bancoValue,
                            HttpServletResponse response) throws IOException {
        Conta conta = new Conta();
        if (numeroConta != null){
            conta.setConta(numeroConta);
        }
        if (agencia != null){
            conta.setAgencia(agencia);
        }
        if (tipoConta != null){
            Tipoconta tipoconta = TipoContaEnum.CC.getTipoconta(Integer.parseInt(tipoConta));
            conta.setTipo(tipoconta.getId());
        }
        if (bancoValue != null){
            Banco banco = BancoEnum.BANCO_PAN.getBanco(Integer.parseInt(bancoValue));
            conta.setBanco(banco.getId());
        }
        StringBuilder print = new StringBuilder();
        Controller controller =  new Controller();
        print.append("<div style=width: 400px;>");
        print.append("<p>Resultado da busca:</p>");
        print.append("<table border=1px width=400px name='table'>");
        print.append("<thread>");
        print.append("<tr>");
        print.append("<th>id</th>");
        print.append("<th>usuario</th>");
        print.append("<th>agencia</th>");
        print.append("<th>conta</th>");
        print.append("<th>saldo</th>");
        print.append("<th>banco</th>");
        print.append("<th>tipo</th>");
        print.append("</tr>");
        print.append("</thread>");
        for (Conta results : controller.consultarConta(conta)) {
            print.append("<thread>");
            print.append("<tr>");
            print.append("<th>"+results.getId() +"</th>");
            print.append("<th>"+results.getUsuario()+"</th>");
            print.append("<th>"+results.getAgencia()+"</th>");
            print.append("<th>"+results.getConta()+"</th>");
            print.append("<th>"+results.getSaldo()+"</th>");
            print.append("<th>"+results.getBanco() +"</th>");
            print.append("<th>"+results.getTipo()+" </th>");
            print.append("</tr>");
            print.append("</thread>");
        }
        print.append("</table>");
        print.append("</div>");
        response.getWriter().println(print);
    }
}
