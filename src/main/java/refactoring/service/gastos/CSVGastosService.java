package refactoring.service.gastos;

import refactoring.entity.despesas.Gastos;
import refactoring.output.CSVHelper;
import refactoring.config.EntitiesFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

public class CSVGastosService {
    @Autowired
    EntitiesFactory<Gastos> repository;

    private int usuario;

    public ByteArrayInputStream load(String filename) throws IOException {
        List<Gastos> gastos = repository.gastosRepository().findAll(usuario);

        ByteArrayInputStream in = CSVHelper.gastoToCSV(gastos,filename);
        return in;
    }

    public void setUsuario(int usuario){
        this.usuario = usuario;
    }
}
