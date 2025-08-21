package br.com.mba.engenharia.de.software.refactoring.dto.veiculos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VeiculosDTOAlterar {
    private long id;
    private String placa;
    private String marca;
    private String modelo;
    private int ano;
    private int usuario;
}
