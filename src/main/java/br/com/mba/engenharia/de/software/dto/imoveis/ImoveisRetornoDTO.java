package br.com.mba.engenharia.de.software.dto.imoveis;

import br.com.mba.engenharia.de.software.entity.imoveis.Imoveis;

public class ImoveisRetornoDTO {
    private int id;
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

    public ImoveisRetornoDTO(Imoveis imoveisRetorno) {
        this.id = imoveisRetorno.getId();
        this.cep = imoveisRetorno.getCep();
        this.logradouro = imoveisRetorno.getLogradouro();
        this.rua = imoveisRetorno.getRua();
        this.numero = imoveisRetorno.getNumero();
        this.tipoImovel = imoveisRetorno.getTipoImovel();
        this.bairro = imoveisRetorno.getBairro();
        this.cidade = imoveisRetorno.getCidade();
        this.estado = imoveisRetorno.getEstado();
        this.pais = imoveisRetorno.getPais();
        this.usuario = imoveisRetorno.getUsuario();
    }
}
