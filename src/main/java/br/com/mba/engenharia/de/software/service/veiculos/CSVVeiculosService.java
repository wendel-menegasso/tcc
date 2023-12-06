package br.com.mba.engenharia.de.software.service.veiculos;

import br.com.mba.engenharia.de.software.entity.rendas.Renda;
import br.com.mba.engenharia.de.software.entity.veiculos.Veiculos;
import br.com.mba.engenharia.de.software.output.CSVHelper;
import br.com.mba.engenharia.de.software.repository.rendas.RendasRepository;
import br.com.mba.engenharia.de.software.repository.veiculos.VeiculosRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

public class CSVVeiculosService {
    @Autowired
    VeiculosRepository repository;

    private int usuario;

    public ByteArrayInputStream load(String filename) throws IOException {
        List<Veiculos> veiculos = repository.findAll(usuario);

        ByteArrayInputStream in = CSVHelper.veiculoToCSV(veiculos,filename);
        return in;
    }

    public void setUsuario(int usuario){
        this.usuario = usuario;
    }
}
