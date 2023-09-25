package br.com.mba.engenharia.de.software.dto;

import br.com.mba.engenharia.de.software.entity.contas.Conta;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class ContaDTORetorno {
    private Integer id;
    private Integer banco;
    private Integer tipo;
    private String saldo;
    private String agencia;
    private String conta;
    private Integer usuario;

    public ContaDTORetorno(Conta conta){
        this.id = conta.getId();
        this.banco = conta.getBanco();
        this.tipo = conta.getTipo();
        this.saldo = conta.getSaldo();
        this.agencia =conta.getAgencia();
        this.conta = conta.getConta();
        this.usuario = conta.getUsuario();
    }

    public void setSaldo(String saldo){
        this.saldo = saldo;
    }

}
