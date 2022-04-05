package br.com.jcv.aluguerelaxe.client.administracao.console.liberarpgto;

import br.com.jcv.aluguerelaxe.client.componente.toolbar.AbstractToolbar;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Classe responsavel por montar uma toolbar básica para formularios com
 * ações de manutenção
 * 
 * @author julio
 *
 */
public class LiberarPgtoToolbar extends AbstractToolbar<LiberarPgtoToolbarListener> {
	
	private static final String PATH_IMAGENS = "images/32x32/";
	
	public LiberarPgtoToolbar() {
		super();
	}

	@Override
	public Widget render() {
		
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(montaIconeNovoTracking());
		hp.add(montaIconeConfirmarDeposito());

		return hp;
	}

	private Widget montaIconeConfirmarDeposito() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "money_envelope_add.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (LiberarPgtoToolbarListener lstnr : listeners) {
						lstnr.onLiberarPgtoClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Liberar");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (LiberarPgtoToolbarListener lstnr : listeners) {
						lstnr.onLiberarPgtoClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}

	private Widget montaIconeNovoTracking() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "find.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (LiberarPgtoToolbarListener lstnr : listeners) {
						lstnr.onPesquisarNovoTracking();
					}
				}
			}
		});
		
		Label lbl = new Label("Tracking");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (LiberarPgtoToolbarListener lstnr : listeners) {
						lstnr.onPesquisarNovoTracking();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}

}
