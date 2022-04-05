package br.com.jcv.aluguerelaxe.client.administracao.console.faturamento;

import br.com.jcv.aluguerelaxe.client.componente.toolbar.AbstractToolbar;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class LiquidarToolbar extends AbstractToolbar<LiquidarToolbarListener> {

	private static final String PATH_IMAGENS = "images/32x32/";

	public LiquidarToolbar() {
		super();
	}
	
	@Override
	public Widget render() {
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(montaIconeLiquidarFatura());
		return hp;
	}

	private Widget montaIconeLiquidarFatura() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "money.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (LiquidarToolbarListener lstnr : listeners) {
						lstnr.onLiquidarClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Liquidar");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (LiquidarToolbarListener lstnr : listeners) {
						lstnr.onLiquidarClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}

}
