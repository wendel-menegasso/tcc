package br.com.mba.engenharia.de.software.dto;

import br.com.mba.engenharia.de.software.entity.rendas.Renda;
import lombok.Getter;
import javax.validation.constraints.Size;

@Getter
public class RendasDTO {
    private String data;
    private String nome;
    @Size(min=1,max=2)
    private Integer tipo;
    private Integer usuario;
    private Integer origem;
    private Double valor;

    public Renda parseRendasDTOToRenda(){
        return new Renda(nome, valor, data, tipo, usuario);
    }
}
