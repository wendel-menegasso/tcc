package br.com.mba.engenharia.de.software.refactoring.service;

import br.com.mba.engenharia.de.software.refactoring.entity.contas.Conta;
public class ExportCSVFactory<T> {

    public static <T> CSVExporter<T> getExporter(Class<T> clazz) {
        if (clazz.equals(Conta.class)) {
            return (CSVExporter<T>) new ContaCSVExporter();
        }
        // Adicione outros tipos aqui
        throw new IllegalArgumentException("Exportador não disponível para: " + clazz.getSimpleName());
    }

}
