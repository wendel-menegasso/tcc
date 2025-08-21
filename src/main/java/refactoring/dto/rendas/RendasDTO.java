package refactoring.dto.rendas;

import refactoring.entity.rendas.Renda;
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
    private String valor;

    public Renda parseRendasDTOToRenda(){
        return new Renda(nome, this.valor, data, tipo, usuario, origem);
    }
}
