package br.com.jcv.aluguerelaxe.client.componente.chart;

import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.visualizations.ColumnChart;

/**
 * <h2>ColumnChartVisualization</h2>
 * <p>Classe para geracao de um chart do tipo coluna</p>
 * @author Julio Vitorino
 * @since 2011
 */
public class ColumnChartVisualization extends AbstractChartVisualization<ColumnChart.Options> {
	
	public ColumnChartVisualization(DataTable dt, ColumnChart.Options opt) {
		super(dt, opt);
	}

	@Override
	public Widget drawChart() {
		return new ColumnChart(getDataTable(), getOptions());
	}
	
}