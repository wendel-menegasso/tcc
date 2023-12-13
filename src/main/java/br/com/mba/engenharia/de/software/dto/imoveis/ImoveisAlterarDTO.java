package br.com.mba.engenharia.de.software.dto.imoveis;

import br.com.mba.engenharia.de.software.entity.imoveis.Imoveis;
import lombok.Getter;

@Getter
public class ImoveisAlterarDTO {
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

    public Imoveis parseImoveisAlterarDTOToImovel() {
        return new Imoveis(this);
    }

}
