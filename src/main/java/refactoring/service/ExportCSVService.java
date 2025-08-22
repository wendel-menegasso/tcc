package refactoring.service;

import refactoring.config.EntitiesFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExportCSVService<T> extends ExportCSVFactory<T>{

    private int usuario;
    @Autowired
    private final EntitiesFactory<T> repository;

    private final Map<Class<?>, Object> repositoryMap = new HashMap<>();

    // Supondo que você tenha um repositório genérico

    public ExportCSVService(int usuario, EntitiesFactory<T> repository) {
        this.usuario = usuario;
        this.repository = repository;
    }

    public ByteArrayInputStream load(Class<T> clazz, String filename) throws IOException {
        CSVExporter<T> exporter = ExportCSVFactory.getExport(clazz);
        exporter.setRepository(repository);
        return exporter.export(filename, this.usuario);
    }
}
