package br.com.mba.engenharia.de.software.refactoring.service.contas;

import br.com.mba.engenharia.de.software.entity.contas.Conta;
import br.com.mba.engenharia.de.software.output.CSVHelper;
import br.com.mba.engenharia.de.software.refactoring.repository.contas.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

public class CSVContasService {
    @Autowired
    ContaRepository repository;

    private int usuario;

    public ByteArrayInputStream load(String filename) throws IOException {
        List<Conta> contas = repository.findAll(usuario);

        ByteArrayInputStream in = CSVHelper.contaToCSV(contas,filename);
        return in;
    }

    public void setUsuario(int usuario){
        this.usuario = usuario;
    }
}
