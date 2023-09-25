package br.com.mba.engenharia.de.software.dto;

import br.com.mba.engenharia.de.software.entity.contas.Conta;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class ContaDTOAlterarFull {
    @NotNull
    private Integer banco;
    @NotNull
    private Integer tipo;
    @NotNull
    private String saldo;
    @NotNull
    private String agencia;
    @NotNull
    private String conta;
    @NotNull
    private Integer usuario;
    @NotNull
    private Integer id;

    public Conta parseContaDTOToConta(){
        String conta = this.conta.replaceAll("-", "");
        String agencia = this.agencia.replaceAll("-", "");
        String saldoAtual = parseSaldoToCurrencyValue(this.saldo);
        return new Conta(this.banco, this.tipo, saldoAtual, agencia, conta, this.usuario, this.id);
    }

    public String parseSaldoToCurrencyValue(String saldoAntigo){
        if (saldoAntigo.indexOf(".") == saldoAntigo.length() -3) {
            return saldoAntigo;
        }
        if (saldoAntigo.indexOf(".") == saldoAntigo.length() -2){
            return saldoAntigo + "0";
        }
        return saldoAntigo + ".00";
    }

    public ContaDTOAlterarFull(ContaDTORetorno contaDTORetorno){
        this.agencia = contaDTORetorno.getAgencia();
        this.conta = contaDTORetorno.getConta();
        this.banco = contaDTORetorno.getBanco();
        this.id = contaDTORetorno.getId();
        this.tipo = contaDTORetorno.getTipo();
        this.saldo = contaDTORetorno.getSaldo();
        this.usuario = contaDTORetorno.getUsuario();
    }

}
