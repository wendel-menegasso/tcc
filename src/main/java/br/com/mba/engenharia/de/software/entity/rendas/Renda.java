package br.com.mba.engenharia.de.software.entity.rendas;

import br.com.mba.engenharia.de.software.dto.rendas.RendasRetornoDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rendas")
@Getter
@NoArgsConstructor
public class Renda {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nome", length = 50)
    private String nome;

    @Column(name = "valor")
    private String valor;

    @Column(name = "data")
    private String data;

    @Column(name = "tipo")
    private Integer tipo;

    @Column(name = "usuario")
    private Integer usuario;

    @Column(name = "origem", nullable = false)
    private Integer origem;

    public Renda(String nome, String valor, String data, Integer tipo, Integer usuario, Integer origem){
        this.nome = nome;
        this.valor = valor;
        this.data = data;
        this.tipo = tipo;
        this.usuario = usuario;
        this.origem = origem;
    }

    public Renda(String nome, String valor, String data, Integer tipo, Integer id, Integer usuario, Integer origem){
        this.nome = nome;
        this.valor = valor;
        this.data = data;
        this.tipo = tipo;
        this.id = id;
        this.usuario = usuario;
        this.origem = origem;
    }

    public Renda(Integer id, Integer usuario){
        this.id = id;
        this.usuario = usuario;
    }

    public RendasRetornoDTO parseRendaToRendasRetornoDTO(){
        return new RendasRetornoDTO(this);
    }

    public void setId(Integer id) {
        this.id = id;
    }
}