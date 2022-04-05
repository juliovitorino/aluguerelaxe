package br.com.jcv.aluguerelaxe.client.destino;

import br.com.jcv.aluguerelaxe.client.componente.toolbar.AbstractToolbar;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ToolbarDestino extends AbstractToolbar<ToolbarDestinoListener> {

	private static final String PATH_IMAGENS = "images/24x24/";

	@Override
	public Widget render() {
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(montaIconeViajar());
		return hp;
	}

	private Widget montaIconeViajar() {
		HorizontalPanel vp = new HorizontalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "airplane.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarDestinoListener lstnr : listeners) {
						lstnr.onViajarClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Selecione abaixo o destino da sua temporada e depois clique aqui para conhecer as oportunidades");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarDestinoListener lstnr : listeners) {
						lstnr.onViajarClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}

}
