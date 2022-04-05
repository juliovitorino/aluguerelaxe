package br.com.jcv.aluguerelaxe.client.administracao.console.uploadwizard;

import br.com.jcv.aluguerelaxe.client.componente.toolbar.AbstractToolbar;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class GaleriaToolbar extends AbstractToolbar<GaleriaToolbarListener> {

	private static final String PATH_IMAGENS = "images/24x24/";
	public GaleriaToolbar() {
		super();
	}
	
	@Override
	public Widget render() {
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(montaIconeEnviarLixeira());
		return hp;
	}
	
	private Widget montaIconeEnviarLixeira() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "garbage.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					try {
						for (GaleriaToolbarListener lstnr : listeners) {
							lstnr.onEnviarLixeiraClick();
						}
					} catch (ClassCastException e) {
						// TODO: handle exception
					}
				}
			}
		});
		
		Label lbl = new Label("Lixeira");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					try {
						for (GaleriaToolbarListener lstnr : listeners) {
							lstnr.onEnviarLixeiraClick();
						}
					} catch (ClassCastException e) {
						// TODO: handle exception
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}
}
