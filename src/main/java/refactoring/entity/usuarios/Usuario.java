package refactoring.entity.usuarios;

import refactoring.dto.usuarios.UsuarioDTORetorno;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "usuarios")
@Component
@Data
@Getter
@NoArgsConstructor
public class Usuario{
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "username", nullable = false, length = 40)
    private String username;

    @Column(name = "senha", nullable = false, length = 300)
    private String senha;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "token", nullable = false, length = 300)
    private String token;

    @Column(name = "nome", nullable = false, length = 30)
    private String nome;

    @Column(name = "sobrenome", nullable = false, length = 50)
    private String sobrenome;

    @Column(name = "cpf", nullable = false, length = 11)
    private String cpf;

    @Column(name = "status", nullable = false, length = 1)
    private String status;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public void setCPF(String cpf){
        this.cpf = cpf;
    }

    public void setToken(String token){
        this.token = token;
    }

    public void setSenha(String senha){
        this.senha = senha;
    }


    public Usuario(String username, String email, String nome, String sobrenome){
        this.username = username;
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
    }

    public Usuario(String username, String token){
        this.username = username;
        this.token = token;
    }

    public UsuarioDTORetorno parseUsuarioToUsuarioDTORetorno(){
        return new UsuarioDTORetorno(this);
    }

}