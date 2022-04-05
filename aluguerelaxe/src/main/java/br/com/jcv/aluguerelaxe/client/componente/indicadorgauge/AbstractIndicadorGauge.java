package br.com.jcv.aluguerelaxe.client.componente.indicadorgauge;

import java.util.ArrayList;
import java.util.List;

import br.com.jcv.aluguerelaxe.client.componente.chart.AbstractChartVisualization;
import br.com.jcv.aluguerelaxe.client.componente.chart.ChartVisualizationFactory;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.visualizations.Gauge;

/**
 * <p>Classe abstrata para o controle comportamental do medidor gauge</p>
 * <p>css: gwt-AbstractGauge</p>
 *
 * @author Julio Vitorino
 */

public abstract class AbstractIndicadorGauge<V> extends Composite {

	private static final String GWT_STYLE = "gwt-AbstractGauge";
	
	private VerticalPanel vpgauge = new VerticalPanel();
	private int limiteMaximo = 100;
	private long valor = 0;
	private String titulo = "";
	private int width;
	private int height;
	
	protected List<LimitesGauge> lstLimiteGauge = null;
	
	// Metodos que a classe concreta devera implementar
	public abstract void init();
	public abstract Widget render();
	public abstract void update(V vo);
	

	public AbstractIndicadorGauge() {
		this.width = 288;
		this.height = 216;
		this.lstLimiteGauge = new ArrayList<LimitesGauge>();
		//this.setStylePrimaryName(GWT_STYLE);
		
		init();
		initWidget(renderGauge());
	}
	
	private Widget renderGauge() {
		DockPanel dp = new DockPanel();
		
		dp.add(criaGauge(), DockPanel.CENTER);
		dp.add(render(), DockPanel.SOUTH);
		
		return dp;
	}
	
	protected void setLimiteMaximo(int limiteMaximo) {
		this.limiteMaximo = limiteMaximo;
	}
	
	protected void setValor(long valor){
		this.valor = valor;
	}
	
	protected long getValor() {
		return this.valor;
	}
	
	protected void setTitulo(String titulo){
		this.titulo = titulo;
	}
	
	protected void setWidth(int width){
		this.width = width;
	}

	protected void setHeight(int height){
		this.height = height;
	}
	
	protected Widget criaGauge() {
		// Carrega a API do Visualization
		VisualizationUtils.loadVisualizationApi(new Runnable() {
			
			public void run() {
			    DataTable data = DataTable.create();
			    data.addColumn(ColumnType.STRING, "faixa");
			    data.addColumn(ColumnType.NUMBER, "valor");
			    data.addRows(1);
			    data.setValue(0, 0, AbstractIndicadorGauge.this.titulo);
			    data.setValue(0, 1, AbstractIndicadorGauge.this.valor);
					
			    Gauge.Options options = Gauge.Options.create();
			    options.setWidth(AbstractIndicadorGauge.this.width);
			    options.setHeight(AbstractIndicadorGauge.this.height);
			    
			    // Identifica o limite maximo do indicador
			    options.setGaugeRange(0, AbstractIndicadorGauge.this.limiteMaximo);
			    
			    // cria as faixas coloridas
			    int cont = 0;
			    for (LimitesGauge lim : lstLimiteGauge) {
			    	switch(cont){
			    	case 0:
			    		options.setRedRange(lim.inicio, lim.fim);
			    		break;
			    	case 1:
			    		options.setYellowRange(lim.inicio, lim.fim);
			    		break;
			    	case 2:
			    		options.setGreenRange(lim.inicio, lim.fim);
			    		break;
			    	}
			    	cont++;
					
				}
					
				AbstractChartVisualization<Gauge.Options> acv = ChartVisualizationFactory.getInstance(data, options);
				vpgauge.clear();
				vpgauge.add(acv.getChart());
			}
		}, Gauge.PACKAGE);
		
		
		return vpgauge;
	}
	
	
}
