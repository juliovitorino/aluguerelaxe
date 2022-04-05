package br.com.jcv.aluguerelaxe.client.componente.toolbar;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class AdicionarRemoverToolbar extends AbstractToolbar<AdicionarRemoverToolbarListener> {

	private static final String PATH_IMAGENS = "images/24x24/";
	public AdicionarRemoverToolbar() {
		super();
	}
	
	@Override
	public Widget render() {
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(montaIconeAdicionar());
		hp.add(montaIconeRemover());
		return hp;
	}
	
	private Widget montaIconeAdicionar() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "add.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (AdicionarRemoverToolbarListener lstnr : listeners) {
						lstnr.onAdicionarClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Adicionar");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (AdicionarRemoverToolbarListener lstnr : listeners) {
						lstnr.onAdicionarClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}
	
	private Widget montaIconeRemover() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "delete.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (AdicionarRemoverToolbarListener lstnr : listeners) {
						lstnr.onRemoverClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Remover");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (AdicionarRemoverToolbarListener lstnr : listeners) {
						lstnr.onRemoverClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}
}
