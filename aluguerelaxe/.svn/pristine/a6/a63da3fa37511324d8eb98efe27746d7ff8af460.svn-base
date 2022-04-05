package br.com.jcv.aluguerelaxe.client.centralreserva;

import br.com.jcv.aluguerelaxe.client.componente.toolbar.AbstractToolbar;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ToolbarCentralReserva extends AbstractToolbar<ToolbarCentralReservaListener> {

	private static final String PATH_IMAGENS = "images/32x32/";

	public ToolbarCentralReserva() {
		super();
	}
	
	@Override
	public Widget render() {
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(montaIconeAndamento());
		hp.add(montaIconeCancelar());
		hp.add(montaIconeAvaliar());
		return hp;
	}
	
	private Widget montaIconeAndamento() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "find.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarCentralReservaListener lstnr : listeners) {
						lstnr.onAndamentoClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Andamento");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarCentralReservaListener lstnr : listeners) {
						lstnr.onAndamentoClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}
	
	private Widget montaIconeCancelar() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "error.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarCentralReservaListener lstnr : listeners) {
						lstnr.onCancelarReservaClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Cancelamento");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarCentralReservaListener lstnr : listeners) {
						lstnr.onCancelarReservaClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}
	
	private Widget montaIconeAvaliar() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "user1.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarCentralReservaListener lstnr : listeners) {
						lstnr.onAvaliarReservaClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Avalia\u00e7\u00e3o");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarCentralReservaListener lstnr : listeners) {
						lstnr.onAvaliarReservaClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}
}
