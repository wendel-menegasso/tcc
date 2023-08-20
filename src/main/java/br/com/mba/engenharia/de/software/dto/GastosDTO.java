package br.com.mba.engenharia.de.software.dto;

import br.com.mba.engenharia.de.software.entity.despesas.Gastos;
import lombok.Getter;

@Getter
public class GastosDTO {
    private String data;
    private String nome;
    private Integer tipo;
    private Integer usuario;
    private Integer origem;
    private Double valor;

    public Gastos parseGastosToDTOGastos(){
        return new Gastos(nome, valor, data, tipo, origem);
    }

}
