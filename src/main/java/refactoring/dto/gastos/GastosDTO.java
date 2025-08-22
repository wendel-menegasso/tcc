package refactoring.dto.gastos;

import refactoring.entity.despesas.Gastos;
import lombok.Getter;

import javax.validation.constraints.NotNull;

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
