package br.com.mba.engenharia.de.software.dto.gastos;

import br.com.mba.engenharia.de.software.entity.despesas.Gastos;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
public class GastosDTOFull {
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
        @NotNull
        private Integer id;

        public Gastos parseGastosToDTOFullToGastos(){
            return new Gastos(nome, this.valor, data, tipo, origem, id, usuario);
        }
}
