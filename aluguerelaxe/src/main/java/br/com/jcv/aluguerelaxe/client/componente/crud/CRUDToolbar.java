package br.com.jcv.aluguerelaxe.client.componente.crud;



import br.com.jcv.aluguerelaxe.client.componente.toolbar.AbstractToolbar;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class CRUDToolbar extends AbstractToolbar<CRUDToolbarListener> {

	private static final String PATH_IMAGENS = "images/botoes/";

	public CRUDToolbar() {
		super();
	}
	
	@Override
	public Widget render() {
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(montaIconeNovo());
		hp.add(montaIconeAlterar());
		hp.add(montaIconeFechar());
		return hp;
	}

	private Widget montaIconeFechar() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "fechar.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (CRUDToolbarListener lstnr : listeners) {
						lstnr.onFecharClick();
					}
				}
			}
		});
		
		vp.add(imagem);
		
		return vp;
	}

	private Widget montaIconeAlterar() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "alterar.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (CRUDToolbarListener lstnr : listeners) {
						lstnr.onAlterarClick();
					}
				}
			}
		});
		
		vp.add(imagem);
		
		return vp;
	}

	private Widget montaIconeNovo() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "novo.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (CRUDToolbarListener lstnr : listeners) {
						lstnr.onNovoClick();
					}
				}
			}
		});
		
		vp.add(imagem);
		
		return vp;
	}

}
