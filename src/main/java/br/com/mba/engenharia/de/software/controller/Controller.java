package br.com.mba.engenharia.de.software.controller;

import br.com.mba.engenharia.de.software.negocio.contas.Conta;
import br.com.mba.engenharia.de.software.negocio.usuarios.Usuario;
import br.com.mba.engenharia.de.software.service.ContaService;
import br.com.mba.engenharia.de.software.service.UserService;

import java.util.List;

public class Controller{
    private Usuario usuario;

    public void setController(Usuario usuario){
        this.usuario = usuario;
    }

    public Controller(){
    }

    public boolean cadastrarUsuario(){
        UserService userService = new UserService();
        return userService.salvarUsuario(usuario);
    }

    public boolean consultarUsuario(){
        UserService userService = new UserService();
        List<Usuario> userList = userService.listarUsuario(usuario);
        return !userList.isEmpty();
    }

    public List<Usuario> consultarTodosUsuarios(){
        UserService userService = new UserService();
        return userService.listarTodosUsuarios();
    }

    public boolean cadastrarConta(Conta conta){
        ContaService contaService = new ContaService();
        return contaService.salvarConta(conta);
    }

    public List<Conta> consultarConta(Conta conta){
        ContaService contaService = new ContaService();
        return contaService.listarConta(conta);
    }

    public List<Conta> listarTodasContas(){
        ContaService contaService = new ContaService();
        return contaService.listarTodasContas();
    }

    public boolean desbloquearUsuario() throws InstantiationException, IllegalAccessException {
        UserService userService = new UserService();
        return userService.habilitarUsuario(usuario);
    }

}
