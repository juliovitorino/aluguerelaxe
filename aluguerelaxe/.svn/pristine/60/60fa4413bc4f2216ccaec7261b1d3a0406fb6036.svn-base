package br.com.jcv.aluguerelaxe.client.componente.chart;

import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.visualizations.Gauge;

/**
 * <h2>GaugeVisualization</h2>
 * <p>Classe para geracao de um chart do tipo Gauge</p>
 * @author Julio Vitorino
 * @since 2011
 */
public class GaugeVisualization extends AbstractChartVisualization<Gauge.Options> {
	
	public GaugeVisualization(DataTable dt, Gauge.Options opt) {
		super(dt, opt);
	}

	@Override
	public Widget drawChart() {
		return new Gauge(getDataTable(), getOptions());
	}
	
}