package br.com.jcv.aluguerelaxe.client.componente.chart;

import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.visualizations.BarChart;
import com.google.gwt.visualization.client.visualizations.ColumnChart;
import com.google.gwt.visualization.client.visualizations.Gauge;
import com.google.gwt.visualization.client.visualizations.LineChart;
import com.google.gwt.visualization.client.visualizations.PieChart;


/**
 * <h2>ChartVisualizationFactory</h2>
 * <p>Factory para criar instancia de visualizacao do grafico ou chart</p>
 * @author Julio Vitorino
 * @since 2011
 */
public class ChartVisualizationFactory {

	// Construtor sem acesso
	private ChartVisualizationFactory() {}
	
	public static AbstractChartVisualization<Gauge.Options> getInstance(DataTable dt, Gauge.Options opt) {
		return new GaugeVisualization(dt, opt);
	}

	public static AbstractChartVisualization<PieChart.Options> getInstance(DataTable dt, PieChart.Options opt) {
		return new PieChartVisualization(dt, opt);
	}

	public static AbstractChartVisualization<ColumnChart.Options> getInstance(DataTable dt, ColumnChart.Options opt) {
		return new ColumnChartVisualization(dt, opt);
	}
	
	public static AbstractChartVisualization<LineChart.Options> getInstance(DataTable dt, LineChart.Options opt) {
		return new LineChartVisualization(dt, opt);
	}
	
	public static AbstractChartVisualization<BarChart.Options> getInstance(DataTable dt, BarChart.Options opt) {
		return new BarChartVisualization(dt, opt);
	}
	
	
	
}
