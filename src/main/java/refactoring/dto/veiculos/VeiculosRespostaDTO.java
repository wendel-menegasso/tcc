package refactoring.dto.veiculos;

import refactoring.entity.veiculos.Veiculos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VeiculosRespostaDTO {
    private long id;
    private String placa;
    private String marca;
    private String modelo;
    private int ano;
    private int usuario;
    public VeiculosRespostaDTO(Veiculos veiculosRetorno) {
        this.id = veiculosRetorno.getId();
        this.placa = veiculosRetorno.getPlaca();
        this.marca = veiculosRetorno.getMarca();
        this.modelo = veiculosRetorno.getModelo();
        this.ano = veiculosRetorno.getAno();
        this.usuario = veiculosRetorno.getUsuario();
    }
}
