package br.com.mba.engenharia.de.software.service.imoveis;

import br.com.mba.engenharia.de.software.entity.imoveis.Imoveis;
import br.com.mba.engenharia.de.software.output.CSVHelper;
import br.com.mba.engenharia.de.software.repository.imoveis.ImoveisRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

public class CSVImoveisService {

    @Autowired
    ImoveisRepository repository;

    private int usuario;

    public void setUsuario(int i) {
        this.usuario = usuario;
    }

    public ByteArrayInputStream load(String filename) throws IOException {
        List<Imoveis> imoveis = repository.findAll(usuario);

        ByteArrayInputStream in = CSVHelper.imovelToCSV(imoveis,filename);
        return in;
    }
}
