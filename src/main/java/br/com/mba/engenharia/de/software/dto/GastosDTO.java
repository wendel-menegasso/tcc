package br.com.mba.engenharia.de.software.dto;

import br.com.mba.engenharia.de.software.entity.despesas.Gastos;
import lombok.Getter;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
public class GastosDTO {
    @NotNull
    private String data;
    @NotNull
    private String nome;
    @NotNull
    private Integer tipo;
    @NotNull
    private Integer usuario;
    @NotNull
    private Integer origem;
    @NotNull
    private String valor;

    public Gastos parseGastosToDTOGastos()
    {
        return new Gastos(nome, this.valor, data, tipo, origem, usuario);
    }

}
