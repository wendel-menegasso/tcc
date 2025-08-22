package br.com.mba.engenharia.de.software.service.imoveis;

import br.com.mba.engenharia.de.software.entity.imoveis.Imoveis;
import br.com.mba.engenharia.de.software.output.CSVHelper;
import refactoring.config.EntitiesFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

public class CSVImoveisService {

    @Autowired
    EntitiesFactory<Imoveis> repository;

    private int usuario;

    public void setUsuario(int i) {
        this.usuario = usuario;
    }

    public ByteArrayInputStream load(String filename) throws IOException {
        List<Imoveis> imoveis = repository.imoveisRepository().findAll(usuario);

        ByteArrayInputStream in = CSVHelper.imovelToCSV(imoveis,filename);
        return in;
    }
}
