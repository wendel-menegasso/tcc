package br.com.mba.engenharia.de.software.controller;

import br.com.mba.engenharia.de.software.entity.contas.Conta;
import br.com.mba.engenharia.de.software.entity.usuarios.Usuario;
import br.com.mba.engenharia.de.software.repository.contas.ContaRepositoryNovo;
import br.com.mba.engenharia.de.software.service.contas.ContaService;
import br.com.mba.engenharia.de.software.service.usuarios.UserService;

import java.util.List;

public class Control{
    private Usuario usuario;
    private ContaRepositoryNovo contasRepository;

    public void setController(Usuario usuario){
        this.usuario = usuario;
    }

    public void setContasRepository(ContaRepositoryNovo contasRepository){
        this.contasRepository = contasRepository;
    }

    public void setToken(String token){
        UserService userService = new UserService();
        userService.gerarToken(usuario);

    }

    public boolean cadastrarUsuario(){
        UserService userService = new UserService();
        return userService.salvarUsuario(usuario);
    }

    public List<Usuario> consultarUsuario(){
        UserService userService = new UserService();
        List<Usuario> userList = userService.listarUsuario(usuario);
        return userList;
    }

    public List<Usuario> consultarTodosUsuarios(){
        UserService userService = new UserService();
        return userService.listarTodosUsuarios();
    }

    public boolean cadastrarConta(Conta conta){
        ContaService contaService = new ContaService(contasRepository);
        return contaService.salvarConta(conta);
    }

    public List<Conta> consultarConta(Conta conta){
        ContaService contaService = new ContaService(contasRepository);
        return contaService.listarConta(conta);
    }

    public List<Conta> listarTodasContas(){
        ContaService contaService = new ContaService(contasRepository);
        return contaService.listarTodasContas();
    }

    public boolean desbloquearUsuario(Usuario user) throws InstantiationException, IllegalAccessException {
        UserService userService = new UserService();
        return userService.habilitarUsuario(user);
    }

}
