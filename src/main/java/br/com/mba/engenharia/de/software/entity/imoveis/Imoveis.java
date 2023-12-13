package br.com.mba.engenharia.de.software.entity.imoveis;

import br.com.mba.engenharia.de.software.dto.imoveis.ImoveisAlterarDTO;
import br.com.mba.engenharia.de.software.dto.imoveis.ImoveisDTO;
import br.com.mba.engenharia.de.software.dto.imoveis.ImoveisDTOFull;
import br.com.mba.engenharia.de.software.dto.imoveis.ImoveisRetornoDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "imoveis")
@Getter
@NoArgsConstructor
public class Imoveis {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "cep")
    private String cep;
    @Column(name = "logradouro")
    private String logradouro;
    @Column(name = "rua")
    private String rua;
    @Column(name = "numero")
    private String numero;
    @Column(name = "bairro")
    private String bairro;
    @Column(name = "cidade")
    private String cidade;
    @Column(name = "estado")
    private String estado;
    @Column(name = "pais")
    private String pais;
    @Column(name = "usuario")
    private int usuario;

    public Imoveis(ImoveisDTOFull imoveisDTOFull) {
        this.cep = imoveisDTOFull.getCep();
        this.cidade = imoveisDTOFull.getCidade();
        this.estado = imoveisDTOFull.getEstado();
        this.bairro = imoveisDTOFull.getBairro();
        this.logradouro = imoveisDTOFull.getLogradouro();
        this.numero = imoveisDTOFull.getNumero();
        this.pais = imoveisDTOFull.getPais();
        this.rua = imoveisDTOFull.getRua();
        this.usuario = imoveisDTOFull.getUsuario();
        this.id = imoveisDTOFull.getId();
    }

    public Imoveis(ImoveisAlterarDTO imoveisAlterarDTO) {
        this.cep = imoveisAlterarDTO.getCep();
        this.cidade = imoveisAlterarDTO.getCidade();
        this.estado = imoveisAlterarDTO.getEstado();
        this.bairro = imoveisAlterarDTO.getBairro();
        this.logradouro = imoveisAlterarDTO.getLogradouro();
        this.numero = imoveisAlterarDTO.getNumero();
        this.pais = imoveisAlterarDTO.getPais();
        this.rua = imoveisAlterarDTO.getRua();
        this.usuario = imoveisAlterarDTO.getUsuario();
        this.id = imoveisAlterarDTO.getId();
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Imoveis(ImoveisDTO imoveisDTO){
        this.cep = imoveisDTO.getCep();
        this.cidade = imoveisDTO.getCidade();
        this.estado = imoveisDTO.getEstado();
        this.bairro = imoveisDTO.getBairro();
        this.logradouro = imoveisDTO.getLogradouro();
        this.numero = imoveisDTO.getNumero();
        this.pais = imoveisDTO.getPais();
        this.rua = imoveisDTO.getRua();
        this.usuario = imoveisDTO.getUsuario();
    }

    public ImoveisRetornoDTO parseImoveisToImoveisRetornoDTO() {
        return new ImoveisRetornoDTO(this);
    }
}
