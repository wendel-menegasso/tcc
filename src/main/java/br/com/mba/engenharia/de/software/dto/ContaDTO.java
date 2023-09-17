package br.com.mba.engenharia.de.software.dto;

import br.com.mba.engenharia.de.software.entity.contas.Conta;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class ContaDTO {
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

    public Conta parseContaDTOToConta(){
        String conta = this.conta.replaceAll("-", "");
        String agencia = this.agencia.replaceAll("-", "");
        return new Conta(this.banco, this.tipo, this.saldo, agencia, conta, this.usuario);
    }
}
