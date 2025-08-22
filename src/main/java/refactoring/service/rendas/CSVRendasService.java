package refactoring.service.rendas;

import refactoring.entity.rendas.Renda;
import refactoring.output.CSVHelper;
import refactoring.repository.rendas.RendasRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

public class CSVRendasService {
    @Autowired
    RendasRepository repository;

    private int usuario;

    public ByteArrayInputStream load(String filename) throws IOException {
        List<Renda> rendas = repository.findAll(usuario);

        ByteArrayInputStream in = CSVHelper.rendaToCSV(rendas,filename);
        return in;
    }

    public void setUsuario(int usuario){
        this.usuario = usuario;
    }
}
