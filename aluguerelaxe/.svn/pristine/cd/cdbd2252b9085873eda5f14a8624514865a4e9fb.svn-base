package br.com.jcv.aluguerelaxe.client.componente.labs;

import br.com.jcv.aluguerelaxe.client.componente.tracker.Fase;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class FaseSemaforo implements EntryPoint {

	private Fase preReserva;
	private Fase validaReserva;
	private Fase validaPagto;

	private Button btnpreReserva;
	private Button btnvalidaReserva;
	private Button btnvalidaPagto;
	
	public void onModuleLoad() {
		init();
		RootPanel.get("gwt-fase").add(render());
	}
	
	private void init(){
		this.preReserva = new Fase("Pre-reserva");
		this.validaReserva = new Fase("Validacao");
		this.validaPagto = new Fase("Pagamento");

		this.btnpreReserva = new Button("Next");
		this.btnpreReserva.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				//FaseSemaforo.this.preReserva.trocarStatusFase();
			}
		});
		
		this.btnvalidaReserva = new Button("Next");
		this.btnvalidaReserva.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				//FaseSemaforo.this.validaReserva.trocarStatusFase();
			}
		});

		this.btnvalidaPagto = new Button("Next");
		this.btnvalidaPagto.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				//FaseSemaforo.this.validaPagto.trocarStatusFase();
			}
		});
	}
	
	public Widget render() {
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(renderpreReserva());
		hp.add(rendervalidaReserva());
		hp.add(rendervalidaPagto());
		return hp;
	}

	private Widget rendervalidaPagto() {
		VerticalPanel vp = new VerticalPanel();
		vp.add(validaPagto);
		vp.add(btnvalidaPagto);
		return vp;
	}

	private Widget rendervalidaReserva() {
		VerticalPanel vp = new VerticalPanel();
		vp.add(validaReserva);
		vp.add(btnvalidaReserva);
		return vp;
	}

	private Widget renderpreReserva() {
		VerticalPanel vp = new VerticalPanel();
		vp.add(preReserva);
		vp.add(btnpreReserva);
		return vp;
	}
	

}
