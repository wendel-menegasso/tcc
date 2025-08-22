package refactoring.service;

import lombok.Getter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;
import refactoring.config.EntitiesFactory;
import refactoring.entity.contas.Conta;
import refactoring.entity.rendas.Renda;

import java.io.*;
import java.util.Arrays;
import java.util.List;

@Getter
public class RendasCSVExporter<T> implements CSVExporter<T> {

    private EntitiesFactory<T> repository;

    @Override
    public ByteArrayInputStream export(String filename, int usuario) throws IOException {
        return rendasToCSV(filename, usuario);
    }

    @Override
    public void setRepository(EntitiesFactory<T> repo) {
        this.repository = repo;
    }


    private ByteArrayInputStream rendasToCSV(String filename, int usuario) throws IOException {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);
        List<Renda> rendasList = repository.rendasRepository().findAll(usuario);
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(filename), format);) {
            for (Renda renda : rendasList) {
                List<? extends Serializable> data = Arrays.asList(
                        String.valueOf(renda.getId()),
                        renda.getData(),
                        renda.getValor(),
                        renda.getNome(),
                        renda.getOrigem(),
                        renda.getTipo(),
                        renda.getUsuario()
                );

                csvPrinter.printRecord(data);
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }
}
