package br.com.mba.engenharia.de.software.entity.despesas;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "despesas")
@Getter
@NoArgsConstructor
public class Gastos {

    public Gastos(String nome, Double valor, String data, Integer tipo, Integer origem){
        this.nome = nome;
        this.valor = valor;
        this.data = data;
        this.tipo = tipo;
        this.origem = origem;
    }

    public Gastos(String nome, Double valor, String data, Integer tipo, Integer origem, Integer id, Integer usuario){
        this.nome = nome;
        this.valor = valor;
        this.data = data;
        this.tipo = tipo;
        this.origem = origem;
        this.id = id;
        this.usuario = usuario;
    }


    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nome", length = 50)
    private String nome;

    @Column(name = "valor")
    private Double valor;

    @Column(name = "data")
    private String data;

    @Column(name = "tipo")
    private Integer tipo;

    @Column(name = "repeticao")
    private Integer repeticao;

    @Column(name = "origem")
    private Integer origem;

    @Column(name = "usuario")
    private Integer usuario;

    public void setId(Integer id){
        this.id = id;
    }

}