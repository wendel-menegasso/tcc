package br.com.mba.engenharia.de.software.negocio.imoveis;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cidade")
public class Cidade {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "estado")
    private Integer estado;

    @Column(name = "descr", length = 40)
    private String descr;

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}