package br.com.mba.engenharia.de.software.refactoring.dto.usuarios;

import br.com.mba.engenharia.de.software.refactoring.entity.usuarios.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
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
    private String retorno;

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

    public void setRetorno(String retorno){
        this.retorno = retorno;
    }

}
