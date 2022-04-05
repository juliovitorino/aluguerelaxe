package br.com.jcv.aluguerelaxe.client.componente.chart;

import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.DataTable;

/**
 * <h2>AbstractChartVisualization</h2>
 * <p>Abstracao para controle comportamental do ChartVisualization</p>
 * @author Julio Vitorino
 * @since 2011
 */
public abstract class AbstractChartVisualization<O> {
	
	private DataTable dt = null;
	protected O opt;
	private Widget chart = null;
	
	public abstract Widget drawChart();
	
	/**
	 * <p>Contrutor para ChartVisualization</p>
	 * <p>Apos encapsular a <code>DataTable</code> e o <code>Options</code> que e representada pelo generics O, invoca o metodo 
	 * <code>drawChart()</code> que e implementado pelas classes concretas para criar o Chart desejado.</p>
	 */
	public AbstractChartVisualization(DataTable dt, O opt) {
		this.dt = dt;
		this.opt = opt;
		this.chart = drawChart();
	}
	
	/**
	 * <p>Devolve uma instancia de <code>DataTable</code> encapsulada.</p>
	 */
	public DataTable getDataTable() {
		return this.dt;
	}
	
	/**
	 * <p>Devolve uma instancia do generics <code>O</code> que foi previsamente encapsulada.</p>
	 */
	public O getOptions() {
		return this.opt;
	}
	
	/**
	 * <p>Devolve uma instancia do chart</p>
	 */
	public Widget getChart() {
		return this.chart;
	}
	
}