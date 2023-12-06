package br.com.mba.engenharia.de.software.entity.veiculos;

import br.com.mba.engenharia.de.software.dto.VeiculosDTO;
import br.com.mba.engenharia.de.software.dto.VeiculosDTOFull;
import br.com.mba.engenharia.de.software.dto.VeiculosRespostaDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "veiculos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Veiculos {
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "placa", nullable = false)
    private String placa;
    @Column(name = "marca", nullable = false)
    private String marca;
    @Column(name = "modelo", nullable = false)
    private String modelo;
    @Column(name = "ano", nullable = false)
    private int ano;
    @Column(name = "usuario", nullable = false)
    private int usuario;

    public Veiculos(VeiculosDTOFull veiculosDTOFull) {
        this.id = veiculosDTOFull.getId();
        this.placa = veiculosDTOFull.getPlaca();
        this.marca = veiculosDTOFull.getMarca();
        this.modelo = veiculosDTOFull.getModelo();
        this.ano = veiculosDTOFull.getAno();
        this.usuario = veiculosDTOFull.getUsuario();
    }

    public Veiculos(VeiculosDTO veiculosDTO) {
        this.id = veiculosDTO.getId();
        this.placa = veiculosDTO.getPlaca();
        this.marca = veiculosDTO.getMarca();
        this.modelo = veiculosDTO.getModelo();
        this.ano = veiculosDTO.getAno();
        this.usuario = veiculosDTO.getUsuario();
    }

    public void setId(int id) {
        this.id = id;
    }

    public VeiculosRespostaDTO parseGastosToGastosRespostaDTO() {
        VeiculosRespostaDTO veiculosRespostaDTO = new VeiculosRespostaDTO(this);
        return  veiculosRespostaDTO;
    }
}
