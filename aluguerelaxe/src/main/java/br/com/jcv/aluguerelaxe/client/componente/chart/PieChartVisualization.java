package br.com.jcv.aluguerelaxe.client.componente.chart;

import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.visualizations.PieChart;

/**
 * <h2>PieChartVisualization</h2>
 * <p>Classe para geracao de um chart do tipo Pizza</p>
 * @author Julio Vitorino
 * @since 2011
 */
public class PieChartVisualization extends AbstractChartVisualization<PieChart.Options> {
	
	public PieChartVisualization(DataTable dt, PieChart.Options opt) {
		super(dt, opt);
	}

	@Override
	public Widget drawChart() {
		return new PieChart(getDataTable(), getOptions());
	}
	
}