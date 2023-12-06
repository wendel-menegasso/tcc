package br.com.mba.engenharia.de.software.dto;

import br.com.mba.engenharia.de.software.entity.veiculos.Veiculos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VeiculosDTOFull {
    private int id;
    private String placa;
    private String marca;
    private String modelo;
    private int ano;
    private int usuario;
    public Veiculos parseGastosToDTOFullToGastos() {
        Veiculos veiculos = new Veiculos(this);
        return veiculos;
    }
}
