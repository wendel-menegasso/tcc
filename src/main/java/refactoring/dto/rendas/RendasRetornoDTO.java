package refactoring.dto.rendas;

import refactoring.entity.rendas.Renda;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class RendasRetornoDTO {

    private Integer id;
    private String data;
    private String nome;
    private Integer tipo;
    private Integer usuario;
    private String valor;
    private Integer origem;

    public RendasRetornoDTO(Renda renda){
        this.id = renda.getId();
        this.data = renda.getData();
        this.nome = renda.getNome();
        this.tipo = renda.getTipo();
        this.usuario = renda.getUsuario();
        this.valor = renda.getValor();
        this.origem = renda.getOrigem();
    }
}
