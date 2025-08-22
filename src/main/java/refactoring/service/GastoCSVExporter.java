package refactoring.service;

import lombok.Getter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;
import refactoring.config.EntitiesFactory;
import refactoring.entity.contas.Conta;
import refactoring.entity.despesas.Gastos;

import java.io.*;
import java.util.Arrays;
import java.util.List;

@Getter
public class GastoCSVExporter<T> implements CSVExporter<T> {

    private EntitiesFactory<T> repository;

    @Override
    public ByteArrayInputStream export(String filename, int usuario) throws IOException {
        return gastoToCSV(filename, usuario);
    }

    @Override
    public void setRepository(EntitiesFactory<T> repo) {
        this.repository = repo;
    }


    private ByteArrayInputStream gastoToCSV(String filename, int usuario) throws IOException {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);
        List<Gastos> gastosList = repository.gastosRepository().findAll(usuario);
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(filename), format);) {
            for (Gastos gastos : gastosList) {
                List<? extends Serializable> data = Arrays.asList(
                        String.valueOf(gastos.getId()),
                        gastos.getData(),
                        gastos.getNome(),
                        gastos.getValor(),
                        gastos.getOrigem(),
                        gastos.getTipo(),
                        gastos.getUsuario(),
                        gastos.getRepeticao(),
                        gastos.getUsuario()
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
