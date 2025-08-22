package refactoring.dto.imoveis;

import refactoring.entity.imoveis.Imoveis;
import lombok.Getter;

@Getter
public class ImoveisDTOFull {
    private int id;
    private String cep;
    private String logradouro;
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String pais;
    private int usuario;

    public Imoveis parseImoveisDTOFullToImovel() {
        return new Imoveis(this);
    }
}
