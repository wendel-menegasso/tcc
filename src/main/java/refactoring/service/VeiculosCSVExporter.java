package refactoring.service;

import lombok.Getter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;
import refactoring.config.EntitiesFactory;
import refactoring.entity.contas.Conta;
import refactoring.entity.veiculos.Veiculos;

import java.io.*;
import java.util.Arrays;
import java.util.List;

@Getter
public class VeiculosCSVExporter<T> implements CSVExporter<T> {

    private EntitiesFactory<T> repository;

    @Override
    public ByteArrayInputStream export(String filename, int usuario) throws IOException {
        return veiculosToCSV(filename, usuario);
    }

    @Override
    public void setRepository(EntitiesFactory<T> repo) {
        this.repository = repo;
    }


    private ByteArrayInputStream veiculosToCSV(String filename, int usuario) throws IOException {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);
        List<Veiculos> veiculosList = repository.veiculosRepository().findAll(usuario);
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(filename), format);) {
            for (Veiculos veiculo : veiculosList) {
                List<? extends Serializable> data = Arrays.asList(
                        String.valueOf(veiculo.getId()),
                        veiculo.getMarca(),
                        veiculo.getPlaca(),
                        veiculo.getModelo(),
                        veiculo.getAno(),
                        veiculo.getUsuario()
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
