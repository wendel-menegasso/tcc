package br.com.mba.engenharia.de.software.output;

import br.com.mba.engenharia.de.software.entity.contas.Conta;
import br.com.mba.engenharia.de.software.entity.despesas.Gastos;
import br.com.mba.engenharia.de.software.entity.imoveis.Imoveis;
import br.com.mba.engenharia.de.software.entity.rendas.Renda;
import br.com.mba.engenharia.de.software.entity.veiculos.Veiculos;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class CSVHelper {

    public static ByteArrayInputStream contaToCSV(List<Conta> contaList, String filename) throws IOException {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

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

    public static ByteArrayInputStream rendaToCSV(List<Renda> rendasList, String filename) throws IOException {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(filename), format);) {
            for (Renda renda : rendasList) {
                List<? extends Serializable> data = Arrays.asList(
                        String.valueOf(renda.getId()),
                        renda.getData(),
                        renda.getNome(),
                        renda.getValor(),
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

    public static ByteArrayInputStream gastoToCSV(List<Gastos> gastosList, String filename) throws IOException {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(filename), format);) {
            for (Gastos gasto : gastosList) {
                List<? extends Serializable> data = Arrays.asList(
                        String.valueOf(gasto.getId()),
                        gasto.getData(),
                        gasto.getNome(),
                        gasto.getValor(),
                        gasto.getOrigem(),
                        gasto.getTipo(),
                        gasto.getUsuario()
                );

                csvPrinter.printRecord(data);
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream veiculoToCSV(List<Veiculos> veiculosList, String filename) throws IOException {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(filename), format);) {
            for (Veiculos veiculos : veiculosList) {
                List<? extends Serializable> data = Arrays.asList(
                        String.valueOf(veiculos.getId()),
                        veiculos.getPlaca(),
                        veiculos.getModelo(),
                        veiculos.getMarca(),
                        veiculos.getAno()
                );

                csvPrinter.printRecord(data);
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream imovelToCSV(List<Imoveis> imoveisList, String filename) throws IOException {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(filename), format);) {
            for (Imoveis imovel : imoveisList) {
                List<? extends Serializable> data = Arrays.asList(
                        String.valueOf(imovel.getId()),
                        imovel.getCep(),
                        imovel.getLogradouro(),
                        imovel.getRua(),
                        imovel.getNumero(),
                        imovel.getBairro(),
                        imovel.getCidade(),
                        imovel.getEstado(),
                        imovel.getPais(),
                        imovel.getTipoImovel()
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