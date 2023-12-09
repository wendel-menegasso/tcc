package br.com.mba.engenharia.de.software.entity.imoveis;

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
    private int logradouro;
    @Column(name = "rua")
    private String rua;
    @Column(name = "numero")
    private int numero;
    @Column(name = "tipoImovel")
    private int tipoImovel;
    @Column(name = "bairro")
    private String bairro;
    @Column(name = "cidade")
    private String cidade;
    @Column(name = "estado")
    private String estado;
    @Column(name = "pais")
    private String pais;
    public void setId(Integer id){
        this.id = id;
    }
}
