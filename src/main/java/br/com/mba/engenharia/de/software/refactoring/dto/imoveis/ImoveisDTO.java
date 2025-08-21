package br.com.mba.engenharia.de.software.refactoring.dto.imoveis;

import br.com.mba.engenharia.de.software.refactoring.entity.imoveis.Imoveis;
import lombok.Getter;

@Getter
public class ImoveisDTO {
    private String cep;
    private String logradouro;
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String pais;
    private int usuario;

    public Imoveis parseImoveisDTOToImovel() {
        return new Imoveis(this);
    }
}
