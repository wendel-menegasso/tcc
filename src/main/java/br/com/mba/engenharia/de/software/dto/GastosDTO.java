package br.com.mba.engenharia.de.software.dto;

import br.com.mba.engenharia.de.software.entity.despesas.Gastos;
import lombok.Getter;
import javax.validation.constraints.*;

@Getter
public class GastosDTO {
    private String data;
    private String nome;
    @Size(min=1,max=2)
    private Integer tipo;
    private Integer usuario;
    private Integer origem;
    @Digits(integer = 10, fraction = 1/100)
    private Double valor;

    public Gastos parseGastosToDTOGastos(){
        return new Gastos(nome, valor, data, tipo, origem, usuario);
    }

}
