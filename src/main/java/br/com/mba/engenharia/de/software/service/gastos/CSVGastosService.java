package br.com.mba.engenharia.de.software.service.gastos;

import br.com.mba.engenharia.de.software.entity.despesas.Gastos;
import br.com.mba.engenharia.de.software.output.CSVHelper;
import br.com.mba.engenharia.de.software.repository.gastos.GastosRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

public class CSVGastosService {
    @Autowired
    GastosRepository repository;

    private int usuario;

    public ByteArrayInputStream load(String filename) throws IOException {
        List<Gastos> gastos = repository.findAll(usuario);

        ByteArrayInputStream in = CSVHelper.gastoToCSV(gastos,filename);
        return in;
    }

    public void setUsuario(int usuario){
        this.usuario = usuario;
    }
}
