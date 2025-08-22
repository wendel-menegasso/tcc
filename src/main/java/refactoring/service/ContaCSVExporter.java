package refactoring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import refactoring.config.EntitiesFactory;
import refactoring.entity.contas.Conta;
import lombok.Getter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;

import java.io.*;
import java.util.Arrays;
import java.util.List;

@Getter
@Service
public class ContaCSVExporter<T> implements CSVExporter<T> {

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
        List<Conta> contaList = repository.contasRepository().findAll(usuario);
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(filename), format);) {
            for (Conta conta : contaList) {
                List<? extends Serializable> data = Arrays.asList(
                        String.valueOf(conta.getId()),
                        conta.getConta(),
                        conta.getAgencia(),
                        conta.getSaldo(),
                        conta.getBanco(),
                        conta.getTipo(),
                        conta.getUsuario()
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
