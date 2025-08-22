package refactoring.dto.veiculos;

import refactoring.entity.veiculos.Veiculos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VeiculosDTO {
    private int id;
    private String placa;
    private String marca;
    private String modelo;
    private int ano;
    private int usuario;
    public Veiculos parseGastosToDTOGastos() {
        Veiculos veiculos = new Veiculos(this);
        return veiculos;
    }
}
