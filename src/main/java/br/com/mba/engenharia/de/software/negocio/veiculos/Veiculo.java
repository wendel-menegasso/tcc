package br.com.mba.engenharia.de.software.negocio.veiculos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "veiculos")
public class Veiculo {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "tipo")
    private Integer tipo;

    @Column(name = "valor")
    private Double valor;

    @Column(name = "fabricante")
    private Integer fabricante;

    @Column(name = "modelo")
    private Integer modelo;

    @Column(name = "ano")
    private Integer ano;

    @Column(name = "placa", length = 8)
    private String placa;

    @Column(name = "usuario")
    private Integer usuario;

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getModelo() {
        return modelo;
    }

    public void setModelo(Integer modelo) {
        this.modelo = modelo;
    }

    public Integer getFabricante() {
        return fabricante;
    }

    public void setFabricante(Integer fabricante) {
        this.fabricante = fabricante;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}