package br.com.mba.engenharia.de.software.dto;

import br.com.mba.engenharia.de.software.entity.despesas.Gastos;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class GastosRespostaDTO {
    private Integer id;
    private String data;
    private String nome;
    private Integer tipo;
    private Integer usuario;
    private Integer origem;
    private String valor;

    public Gastos parseGastosRespostaDTOToGastos(){
        return new Gastos(nome, this.valor, data, tipo, origem, id, usuario);
    }

    public GastosRespostaDTO(Gastos gastos){
        this.id = gastos.getId();
        this.data = gastos.getData();
        this.nome = gastos.getNome();
        this.tipo = gastos.getTipo();
        this.usuario = gastos.getUsuario();
        this.origem = gastos.getOrigem();
        this.valor = gastos.getValor();
    }

}
