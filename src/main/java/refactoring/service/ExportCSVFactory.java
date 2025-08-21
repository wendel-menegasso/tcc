package refactoring.service;

import refactoring.entity.contas.Conta;

public abstract class ExportCSVFactory<T> {

    public static <T> CSVExporter<T> getExporter(Class<T> clazz) {
        if (clazz.equals(Conta.class)) {
            ContaCSVExporter<T> contaCSVExporter = new ContaCSVExporter<T>();
            return contaCSVExporter();
        }
        // Adicione outros tipos aqui
        throw new IllegalArgumentException("Exportador não disponível para: " + clazz.getSimpleName());
    }

    private static <T> CSVExporter<T> contaCSVExporter() {
        return new ContaCSVExporter<T>();
    }

}
