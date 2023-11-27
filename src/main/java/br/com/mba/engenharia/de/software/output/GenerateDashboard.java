package br.com.mba.engenharia.de.software.output;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.IOException;

public class GenerateDashboard {
    public String generatePlot() throws IOException {
        int[] dados = {10, 20, 30, 40, 50};

        // Cria um conjunto de dados para o gráfico
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int i = 0; i < dados.length; i++) {
            dataset.addValue(dados[i], "Categoria", "Item " + (i + 1));
        }

        // Cria o gráfico de barras
        JFreeChart chart = ChartFactory.createBarChart(
                "Meu Gráfico de Barras", // Título do gráfico
                "Itens", // Rótulo do eixo X
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
