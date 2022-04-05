package br.com.jcv.aluguerelaxe.client.administracao.console.reserva.aprovacao;

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
public class AprovacaoPreReservaToolbar extends AbstractToolbar<AprovacaoPreReservaToolbarListener> {
	
	private static final String PATH_IMAGENS = "images/32x32/";
	
	public AprovacaoPreReservaToolbar() {
		super();
	}

	@Override
	public Widget render() {
		
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(montaIconeNovoTracking());
		hp.add(montaIconeAprovar());
		hp.add(montaIconeReprovar());

		return hp;
	}

	private Widget montaIconeReprovar() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "error.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (AprovacaoPreReservaToolbarListener lstnr : listeners) {
						lstnr.onReprovarClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Reprovar");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (AprovacaoPreReservaToolbarListener lstnr : listeners) {
						lstnr.onReprovarClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}

	private Widget montaIconeAprovar() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "checkbox.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (AprovacaoPreReservaToolbarListener lstnr : listeners) {
						lstnr.onAprovarClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Aprovar");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (AprovacaoPreReservaToolbarListener lstnr : listeners) {
						lstnr.onAprovarClick();
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
					for (AprovacaoPreReservaToolbarListener lstnr : listeners) {
						lstnr.onPesquisarNovoTrackingClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Tracking");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (AprovacaoPreReservaToolbarListener lstnr : listeners) {
						lstnr.onPesquisarNovoTrackingClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}

}
