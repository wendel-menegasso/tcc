package br.com.mba.engenharia.de.software.negocio.veiculos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "modelo")
public class Modelo {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "fabricante")
    private Integer fabricante;

    @Column(name = "descr", length = 30)
    private String descr;

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Integer getFabricante() {
        return fabricante;
    }

    public void setFabricante(Integer fabricante) {
        this.fabricante = fabricante;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}