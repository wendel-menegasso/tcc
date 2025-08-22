package refactoring.service;

import lombok.Getter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;
import refactoring.config.EntitiesFactory;
import refactoring.entity.contas.Conta;
import refactoring.entity.imoveis.Imoveis;

import java.io.*;
import java.util.Arrays;
import java.util.List;

@Getter
public class ImoveisCSVExporter<T> implements CSVExporter<T> {

    private EntitiesFactory<T> repository;

    @Override
    public ByteArrayInputStream export(String filename, int usuario) throws IOException {
        return contaToCSV(filename, usuario);
    }

    @Override
    public void setRepository(EntitiesFactory<T> repo) {
        this.repository = repo;
    }


    private ByteArrayInputStream contaToCSV(String filename, int usuario) throws IOException {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);
        List<Imoveis> imoveisList = repository.imoveisRepository().findAll(usuario);
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(filename), format);) {
            for (Imoveis imovel : imoveisList) {
                List<? extends Serializable> data = Arrays.asList(
                        String.valueOf(imovel.getId()),
                        imovel.getLogradouro(),
                        imovel.getRua(),
                        imovel.getNumero(),
                        imovel.getCep(),
                        imovel.getBairro(),
                        imovel.getCidade(),
                        imovel.getEstado(),
                        imovel.getPais(),
                        imovel.getUsuario()
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
