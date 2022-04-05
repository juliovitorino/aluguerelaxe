package br.com.jcv.aluguerelaxe.client.componente.tracker;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

/**
 * <h2>Tracker</h2>
 * <p>Sinalizador de fases</p>
 * @author Julio
 *
 */
public class Tracker extends Composite {
	
	private static final String GWT_STYLE = "gwt-Tracker";

	private static final String PATH_IMAGEM = "images/48x48/";
	private static final String GWT_TRACKER = "gwt-tracker";

	//Variaveis de apoio a classe
	List<Fase> lstfases;
	
	// variaveis de camada de visao
	HorizontalPanel hpTracker;
	

	public Tracker() {
		init();
		initWidget(render());
		this.setStyleName(GWT_STYLE);
	}
	
	private void init() {
		// Inicializa variaveis
		this.lstfases = new ArrayList<Fase>();
	
		// Inicializa componentes visuais
		hpTracker = new HorizontalPanel();
		hpTracker.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
	}
	
	private Widget render() {
		return hpTracker;
	}
	
	public void add(Fase fase) {
		if (fase != null) {
			if(this.lstfases.size() > 0) {
				Image setaimg = new Image(PATH_IMAGEM + "seta.png");
				setaimg.setStyleName("gwt-seta");
				hpTracker.add(setaimg);
			}
			this.lstfases.add(fase);
			hpTracker.add(fase);
		}
	}
	
	public Fase getFase(int index) {
		Fase fase = null;
		if(this.lstfases.size() > 0) {
			if ( (index > -1) && (index < this.lstfases.size()) ) {
				fase = this.lstfases.get(index);
			}
		}
		return fase;
	}
}