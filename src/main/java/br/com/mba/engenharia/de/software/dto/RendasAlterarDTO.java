package br.com.mba.engenharia.de.software.dto;

import br.com.mba.engenharia.de.software.entity.rendas.Renda;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class RendasAlterarDTO {
    @NotNull
    private Integer id;
    private String data;
    private String nome;
    private Integer tipo;
    private Integer usuario;
    private Integer origem;
    private Double valor;

    public Renda parseRendasDTOToRenda(){
        return new Renda(nome, valor, data, tipo, usuario, id);
    }
}
