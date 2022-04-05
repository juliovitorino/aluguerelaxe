package br.com.jcv.aluguerelaxe.client.administracao.console;

import br.com.jcv.aluguerelaxe.client.componente.toolbar.AbstractToolbar;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ToolbarReserva extends AbstractToolbar<ToolbarReservaListener> {

	private static final String PATH_IMAGENS = "images/32x32/";

	public ToolbarReserva() {
		super();
	}
	
	@Override
	public Widget render() {
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(montaIconeTarefasPendentes());
		hp.add(montaIconePreReserva());
		hp.add(montaIconeAprovarPreReserva());
		hp.add(montaIconeConfirmarDeposito());
		hp.add(montaIconeLiberarTransfDeposito());
		return hp;
	}

	private Widget montaIconeTarefasPendentes() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "scroll_edit.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarReservaListener lstnr : listeners) {
						lstnr.onTarefasPendentesClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Pend\u00eancias");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarReservaListener lstnr : listeners) {
						lstnr.onTarefasPendentesClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}


	private Widget montaIconePreReserva() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "calendar_31.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarReservaListener lstnr : listeners) {
						lstnr.onCriarPreReservaClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Pr\u00e9-Reserva");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarReservaListener lstnr : listeners) {
						lstnr.onCriarPreReservaClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}


	private Widget montaIconeLiberarTransfDeposito() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "money2_edit.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarReservaListener lstnr : listeners) {
						lstnr.onLiberarTransferenciaDeposito();
					}
				}
			}
		});
		
		Label lbl = new Label("Transferir Dep\u00f3sito");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarReservaListener lstnr : listeners) {
						lstnr.onLiberarTransferenciaDeposito();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}


	private Widget montaIconeAprovarPreReserva() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "note_ok.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarReservaListener lstnr : listeners) {
						lstnr.onAprovarPreReservaClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Aprovar");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarReservaListener lstnr : listeners) {
						lstnr.onAprovarPreReservaClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}
	
	private Widget montaIconeConfirmarDeposito() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "moneybag_dollar.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarReservaListener lstnr : listeners) {
						lstnr.onConfirmarReservaClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Confirmar Dep\u00f3sito");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarReservaListener lstnr : listeners) {
						lstnr.onConfirmarReservaClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}
}
