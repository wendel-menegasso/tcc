package br.com.mba.engenharia.de.software.dto.imoveis;

import br.com.mba.engenharia.de.software.entity.imoveis.Imoveis;
import lombok.Getter;

@Getter
public class ImoveisDTO {
    private String cep;
    private int logradouro;
    private String rua;
    private int numero;
    private int tipoImovel;
    private String bairro;
    private String cidade;
    private String estado;
    private String pais;
    private int usuario;

    public Imoveis parseImoveisDTOToImovel() {
        return new Imoveis(this);
    }
}
