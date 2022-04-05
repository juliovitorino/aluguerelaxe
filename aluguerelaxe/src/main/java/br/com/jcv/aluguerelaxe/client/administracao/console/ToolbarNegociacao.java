package br.com.jcv.aluguerelaxe.client.administracao.console;

import br.com.jcv.aluguerelaxe.client.componente.toolbar.AbstractToolbar;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ToolbarNegociacao extends AbstractToolbar<ToolbarNegociacaoListener> {

	private static final String PATH_IMAGENS = "images/32x32/";

	public ToolbarNegociacao() {
		super();
	}
	
	@Override
	public Widget render() {
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(montaIconeAndamento());
		hp.add(montaIconeLiberarVoucher());
		return hp;
	}

	private Widget montaIconeLiberarVoucher() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "money_envelope_edit.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarNegociacaoListener lstnr : listeners) {
						lstnr.onLiberarPgtoClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Liberar Pgto");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarNegociacaoListener lstnr : listeners) {
						lstnr.onLiberarPgtoClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}

	private Widget montaIconeAndamento() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "find.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarNegociacaoListener lstnr : listeners) {
						lstnr.onAndamentoReservaClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Andamento");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarNegociacaoListener lstnr : listeners) {
						lstnr.onAndamentoReservaClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}
	
}
