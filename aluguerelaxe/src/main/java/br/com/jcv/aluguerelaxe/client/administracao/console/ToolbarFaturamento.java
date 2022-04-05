package br.com.jcv.aluguerelaxe.client.administracao.console;

import br.com.jcv.aluguerelaxe.client.componente.toolbar.AbstractToolbar;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ToolbarFaturamento extends AbstractToolbar<ToolbarFaturamentoListener> {

	private static final String PATH_IMAGENS = "images/32x32/";

	public ToolbarFaturamento() {
		super();
	}
	
	@Override
	public Widget render() {
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(montaIconeReceberPlano());
		hp.add(montaIconeReceberPublicidade());
		return hp;
	}

	private Widget montaIconeReceberPlano() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "cashier.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarFaturamentoListener lstnr : listeners) {
						lstnr.onReceberFaturaClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Planos");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarFaturamentoListener lstnr : listeners) {
						lstnr.onReceberFaturaClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}

	private Widget montaIconeReceberPublicidade() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "cashier.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarFaturamentoListener lstnr : listeners) {
						lstnr.onReceberPublicidadeClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Publicidade");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarFaturamentoListener lstnr : listeners) {
						lstnr.onReceberPublicidadeClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}



}
