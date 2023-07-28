package br.com.mba.engenharia.de.software.entity.contas;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "conta")
@Data
@Component
public class Conta{
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "banco", nullable = false)
    private Integer banco;

    @Column(name = "tipo", nullable = false)
    private Integer tipo;

    @Column(name = "saldo", nullable = false)
    private Double saldo;

    @Column(name = "agencia", length = 12, nullable = false)
    private String agencia;

    @Column(name = "conta", length = 12, nullable = false)
    private String conta;

    @Column(name = "usuario", nullable = false)
    private Integer usuario;

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getBanco() {
        return banco;
    }

    public void setBanco(Integer banco) {
        this.banco = banco;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}