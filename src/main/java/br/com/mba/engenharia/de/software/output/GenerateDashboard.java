package br.com.mba.engenharia.de.software.output;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.IOException;

public class GenerateDashboard {
    public String generatePlot1(double totalRendas, double totalGastos) throws IOException {

        // Cria um conjunto de dados para o gráfico
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(totalRendas, "Diferença entre ganhos e gastos", "Total Rendas");
        dataset.addValue(totalGastos, "Diferença entre ganhos e gastos", "Total Gastos");

        // Cria o gráfico de barras
        JFreeChart chart = ChartFactory.createBarChart(
                "Diferença Gastos e Ganhos", // Título do gráfico
                "Gastos e Ganhos", // Rótulo do eixo X
                "Valores", // Rótulo do eixo Y
                dataset, // Conjunto de dados
                PlotOrientation.VERTICAL, // Orientação do gráfico
                true, // Incluir legenda
                true, // Usar dicas de ferramentas
                false // URLs interativas
        );

        // Salva o gráfico como uma imagem (opcional)
        ChartUtils.saveChartAsPNG(new java.io.File("grafico.png"), chart, 400, 300);
        return "grafico.png";
    }
}
