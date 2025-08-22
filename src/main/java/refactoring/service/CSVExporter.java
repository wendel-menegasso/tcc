package refactoring.service;

import refactoring.config.EntitiesFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public interface CSVExporter<T> {
    ByteArrayInputStream export(String filename, int usuario) throws IOException;
    void setRepository(EntitiesFactory<T> repo);
}
