package br.com.mba.engenharia.de.software.dto;

import br.com.mba.engenharia.de.software.entity.usuarios.Usuario;
import lombok.Getter;

@Getter
public class UsuarioDTORetorno {
    private Integer id;
    private String username;
    private String senha;
    private String email;
    private String token;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String status;

    public UsuarioDTORetorno(Usuario usuario) {
        this.id = usuario.getId();
        this.username = usuario.getUsername();
        this.senha = usuario.getSenha();
        this.email = usuario.getEmail();
        this.token = usuario.getToken();
        this.nome = usuario.getNome();
        this.sobrenome = usuario.getSobrenome();
        this.cpf = usuario.getCpf();
        this.status = usuario.getStatus();
    }

}
