package br.com.mba.engenharia.de.software.dto;

import br.com.mba.engenharia.de.software.entity.usuarios.Usuario;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class UsuarioDTO {
    @NotNull
    private String username;
    @NotNull
    private String senha;
    @NotNull
    private String email;
    @NotNull
    private String token;
    private String nome;
    private String sobrenome;
    @NotNull
    private String cpf;
    private String status;

    public Usuario parseUsuarioDTOToUsuario(){
        return new Usuario(this.username, this.email, this.nome, this.sobrenome);
    }

    public Usuario parseUsuarioDTOToUsuarioFromDesbloqueio(){
        return new Usuario(this.username, this.token);
    }

}
