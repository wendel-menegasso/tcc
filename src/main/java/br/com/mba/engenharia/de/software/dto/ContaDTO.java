package br.com.mba.engenharia.de.software.dto;

import br.com.mba.engenharia.de.software.entity.contas.Conta;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ContaDTO {
    private Integer banco;
    private Integer tipo;
    private Double saldo;
    private String agencia;
    private String conta;
    private Integer usuario;

    public Conta parseContaDTOToConta(){
        return new Conta(this.banco, this.tipo, this.saldo, this.agencia, this.conta, this.usuario);
    }

}
