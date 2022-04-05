package br.com.jcv.aluguerelaxe.client.imovel.thread;

import br.com.jcv.aluguerelaxe.client.componente.toolbar.AbstractToolbar;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class ToolbarImovelThread extends AbstractToolbar<ToolbarImovelThreadListener> {

	private static final String PATH_IMAGENS = "images/botoes/";

	@Override
	public Widget render() {
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(montaIconeFazerNovaPergunta());
		return hp;
	}

	private Widget montaIconeFazerNovaPergunta() {
		Image imagem = new Image(PATH_IMAGENS + "novapergunta.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarImovelThreadListener lstnr : listeners) {
						lstnr.onFazerNovaPerguntaClick();
					}
				}
			}
		});
		
		return imagem;
	}

}
