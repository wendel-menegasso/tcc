package refactoring.entity.contas;

import refactoring.dto.contas.ContaDTORetorno;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "conta")
@Data
@Component
@Getter
@NoArgsConstructor
public class Conta{
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "banco", nullable = false)
    private Integer banco;

    @Column(name = "tipo", nullable = false)
    private Integer tipo;

    @Column(name = "saldo", nullable = false)
    private String saldo;

    @Column(name = "agencia", length = 12, nullable = false)
    private String agencia;

    @Column(name = "conta", length = 12, nullable = false)
    private String conta;

    @Column(name = "usuario", nullable = false)
    private Integer usuario;

    public void setId(Integer id) {
        this.id = id;
    }

    public Conta(Integer banco, Integer tipo, String saldo, String agencia, String conta, Integer usuario){
        this.banco = banco;
        this.tipo = tipo;
        this.saldo = saldo;
        this.agencia = agencia;
        this.conta = conta;
        this.usuario = usuario;
    }

    public Conta(Integer banco, Integer tipo, String saldo, String agencia, String conta, Integer usuario, Integer id){
        this.banco = banco;
        this.tipo = tipo;
        this.saldo = saldo;
        this.agencia = agencia;
        this.conta = conta;
        this.usuario = usuario;
        this.id = id;
    }

    public Conta(Integer id, Integer usuario){
        this.id = id;
        this.usuario = usuario;
    }

    public ContaDTORetorno parseContaToContaDTORetorno(){
        return new ContaDTORetorno(this);
    }
}