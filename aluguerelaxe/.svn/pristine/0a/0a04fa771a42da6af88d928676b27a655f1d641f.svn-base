package br.com.jcv.aluguerelaxe.client.componente.toolbar;

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
public class ManterToolbar extends AbstractToolbar<ManterToolbarListener> {
	
	private static final String PATH_IMAGENS = "images/32x32/";

	@Override
	public Widget render() {
		
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(montaIconeSalvar());
		hp.add(montaIconeRefresh());
		hp.add(montaIconeRemover());
		hp.add(montaIconeSair());

		return hp;
	}
	private Widget montaIconeRefresh() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "refresh.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ManterToolbarListener lstnr : listeners) {
						lstnr.onRefreshClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Recarregar");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ManterToolbarListener lstnr : listeners) {
						lstnr.onRefreshClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}

	private Widget montaIconeSair() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "exit.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ManterToolbarListener lstnr : listeners) {
						lstnr.onSairClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Sair");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ManterToolbarListener lstnr : listeners) {
						lstnr.onSairClick();
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
		
		Image imagem = new Image(PATH_IMAGENS + "garbage.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ManterToolbarListener lstnr : listeners) {
						lstnr.onRemoverClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Remover");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ManterToolbarListener lstnr : listeners) {
						lstnr.onRemoverClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}
	

	private Widget montaIconeSalvar() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "disk_blue.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ManterToolbarListener lstnr : listeners) {
						lstnr.onSalvarClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Salvar");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ManterToolbarListener lstnr : listeners) {
						lstnr.onSalvarClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}

}
