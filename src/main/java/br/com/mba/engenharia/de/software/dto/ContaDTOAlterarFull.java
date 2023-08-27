package br.com.mba.engenharia.de.software.dto;

import br.com.mba.engenharia.de.software.entity.contas.Conta;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class ContaDTOAlterarFull {
    @NotNull
    private Integer banco;
    @NotNull
    private Integer tipo;
    @NotNull
    private Double saldo;
    @NotNull
    private String agencia;
    @NotNull
    @Pattern(regexp = "[0-9-]")
    private String conta;
    @NotNull
    private Integer usuario;
    @NotNull
    private Integer id;

    public Conta parseContaDTOToConta(){
        return new Conta(this.banco, this.tipo, this.saldo, this.agencia, this.conta, this.usuario, this.id);
    }
}
