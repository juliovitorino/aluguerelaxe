package br.com.jcv.aluguerelaxe.client.componente.toolbar;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class RemoverToolbar extends AbstractToolbar<RemoverToolbarListener> {

	public RemoverToolbar() {
		super();
	}
	
	@Override
	public Widget render() {
		HorizontalPanel hp = new HorizontalPanel();
		Image imgRemover = criarBotaoRemover();
		
		hp.add(imgRemover);
		
		return hp;
	}

	private Image criarBotaoRemover() {
		Image img = new Image("images/24x24/delete.png");
		img.addClickHandler(new ClickHandler() {
			/**
			 * Envia um evento <code>onRemoverClick</code> para a classe
			 * que implementa a interface {@link ToolbarListener}
			 * @see com.google.gwt.event.dom.client.ClickHandler#onClick(com.google.gwt.event.dom.client.ClickEvent)
			 */
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (RemoverToolbarListener lstnr : listeners) {
						lstnr.onRemoverClick();
					}
				}
			}
		});
		return img;
	}

}
