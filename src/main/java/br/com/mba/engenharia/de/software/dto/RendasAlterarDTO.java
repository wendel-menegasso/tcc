package br.com.mba.engenharia.de.software.dto;

import br.com.mba.engenharia.de.software.entity.rendas.Renda;
import lombok.Getter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
public class RendasAlterarDTO {
    @NotNull
    private Integer id;
    @Size(min=8,max=8)
    @Pattern(regexp = "(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[012])-(19|20)\\d{2}")
    private String data;
    private String nome;
    @Size(min=1,max=2)
    private Integer tipo;
    private Integer usuario;
    private Integer origem;
    @Digits(integer = 10, fraction = 1/100)
    private Double valor;

    public Renda parseRendasDTOToRenda(){
        return new Renda(nome, valor, data, tipo, usuario, id);
    }
}
