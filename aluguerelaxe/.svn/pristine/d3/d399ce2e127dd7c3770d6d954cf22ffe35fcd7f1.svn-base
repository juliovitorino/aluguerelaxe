package br.com.jcv.aluguerelaxe.client.componente.chart;

import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.visualizations.BarChart;
import com.google.gwt.visualization.client.visualizations.BarChart.Options;

/**
 * <h2>BarChartVisualization</h2>
 * <p>Classe para geracao de um chart do tipo BarChart</p>
 * @author Julio Vitorino
 * @since 2011
 */
public class BarChartVisualization extends AbstractChartVisualization<BarChart.Options> {
	
	public BarChartVisualization(DataTable dt, Options opt) {
		super(dt, opt);
	}

	@Override
	public Widget drawChart() {
		return new BarChart(getDataTable(), getOptions());
	}
	
}