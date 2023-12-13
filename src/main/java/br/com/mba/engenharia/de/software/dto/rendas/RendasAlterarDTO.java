package br.com.mba.engenharia.de.software.dto.rendas;

import br.com.mba.engenharia.de.software.entity.rendas.Renda;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
public class RendasAlterarDTO {
    @NotNull
    private Integer id;
    private String data;
    private String nome;
    private Integer tipo;
    private Integer usuario;
    private Integer origem;
    private String valor;

    public Renda parseRendasDTOToRenda(){
        String valorAtualizado = parseSaldoToCurrencyValue(this.valor);
        return new Renda(nome, valorAtualizado, data, tipo,id, usuario, origem);
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

}
