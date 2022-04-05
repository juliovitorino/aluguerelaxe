package br.com.jcv.aluguerelaxe.client.componente.chart;

import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.visualizations.LineChart;

/**
 * <h2>LineChartVisualization</h2>
 * <p>Classe para geracao de um chart do tipo linha</p>
 * @author Julio Vitorino
 * @since 2011
 */
public class LineChartVisualization extends AbstractChartVisualization<LineChart.Options> {
	
	public LineChartVisualization(DataTable dt, LineChart.Options opt) {
		super(dt, opt);
	}

	@Override
	public Widget drawChart() {
		return new LineChart(getDataTable(), getOptions());
	}
	
}