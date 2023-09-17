package br.com.mba.engenharia.de.software.dto;

import br.com.mba.engenharia.de.software.entity.contas.Conta;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.math.RoundingMode;

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
        return new Conta(this.banco, this.tipo, this.saldo, agencia, conta, this.usuario, this.id);
    }
}
