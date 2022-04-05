package br.com.jcv.aluguerelaxe.client.administracao.console.reserva.prereserva;

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
public class ReservaToolbar extends AbstractToolbar<ReservaToolbarListener> {
	
	private static final String PATH_IMAGENS = "images/32x32/";
	
	public ReservaToolbar() {
		super();
	}

	@Override
	public Widget render() {
		
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(montaIconeConfirmarDeposito());

		return hp;
	}
	private Widget montaIconeConfirmarDeposito() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "money_envelope_add.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ReservaToolbarListener lstnr : listeners) {
						lstnr.onCriarReservaClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Criar");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ReservaToolbarListener lstnr : listeners) {
						lstnr.onCriarReservaClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}


}
