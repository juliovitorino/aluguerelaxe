package br.com.jcv.aluguerelaxe.client.administracao.console;

import br.com.jcv.aluguerelaxe.client.componente.toolbar.AbstractToolbar;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ToolbarMinhaSessao extends AbstractToolbar<ToolbarMinhaSessaoListener> {

	private static final String PATH_IMAGENS = "images/32x32/";

	public ToolbarMinhaSessao() {
		super();
	}
	
	@Override
	public Widget render() {
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(montaIconeEncerrarSessao());
		hp.add(montaIconeInfo());
		return hp;
	}

	private Widget montaIconeInfo() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "info.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarMinhaSessaoListener lstnr : listeners) {
						lstnr.onInfoSessaoClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Info");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarMinhaSessaoListener lstnr : listeners) {
						lstnr.onInfoSessaoClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}
	
	private Widget montaIconeEncerrarSessao() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "alarmclock_stop.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarMinhaSessaoListener lstnr : listeners) {
						lstnr.onEncerrarSessaoClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Encerrar");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarMinhaSessaoListener lstnr : listeners) {
						lstnr.onEncerrarSessaoClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}
}
