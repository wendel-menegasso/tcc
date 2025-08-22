package refactoring.service;

import com.sun.org.apache.xerces.internal.xs.XSValue;
import refactoring.entity.contas.Conta;
import refactoring.entity.despesas.Gastos;
import refactoring.entity.imoveis.Imoveis;
import refactoring.entity.rendas.Renda;
import refactoring.entity.veiculos.Veiculos;

import java.util.HashMap;
import java.util.Map;

public abstract class ExportCSVFactory<T> {
        public static <T> CSVExporter<T> getExport(Class<T> clazz) {
        Map<Class<?>, CSVExporter<T>> exporterMap = new HashMap<>();
        exporterMap.put(Conta.class, new ContaCSVExporter<>());
        exporterMap.put(Gastos.class, new GastoCSVExporter<>());
        exporterMap.put(Imoveis.class, new ImoveisCSVExporter<>());
        exporterMap.put(Renda.class, new RendasCSVExporter<>());
        exporterMap.put(Veiculos.class, new VeiculosCSVExporter<>());
        return exporterMap.get(clazz);
    }
}
