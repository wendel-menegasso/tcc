package br.com.mba.engenharia.de.software.negocio.imoveis;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "imoveis")
public class Imovei {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "tipo")
    private Integer tipo;

    @Column(name = "valor")
    private Double valor;

    @Column(name = "cep")
    private Integer cep;

    @Column(name = "estado")
    private Integer estado;

    @Column(name = "cidade")
    private Integer cidade;

    @Column(name = "bairro", length = 50)
    private String bairro;

    @Column(name = "rua", length = 60)
    private String rua;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "complemento", length = 50)
    private String complemento;

    @Column(name = "usuario")
    private Integer usuario;

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Integer getCidade() {
        return cidade;
    }

    public void setCidade(Integer cidade) {
        this.cidade = cidade;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getCep() {
        return cep;
    }

    public void setCep(Integer cep) {
        this.cep = cep;
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