package br.com.mba.engenharia.de.software.refactoring.service;

import br.com.mba.engenharia.de.software.refactoring.config.EntitiesFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

public interface CSVExporter<T> {
    ByteArrayInputStream export(String filename, int usuario) throws IOException;
    void setRepository(EntitiesFactory<T> repo);
}
