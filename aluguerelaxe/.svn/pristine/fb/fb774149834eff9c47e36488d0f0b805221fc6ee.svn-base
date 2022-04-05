package br.com.jcv.aluguerelaxe.client.administracao.console;

import br.com.jcv.aluguerelaxe.client.componente.toolbar.AbstractToolbar;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ToolbarMinhaConta extends AbstractToolbar<ToolbarMinhaContaListener> {

	private static final String PATH_IMAGENS = "images/32x32/";

	public ToolbarMinhaConta() {
		super();
	}
	
	@Override
	public Widget render() {
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(montaIconeChat());
		hp.add(montaIconeMeuDesktop());
		hp.add(montaIconeMinhaConta());
		hp.add(montaIconeTrocaSenha());
		hp.add(montaIconeAssistenteNovoImovel());
		hp.add(montaIconeAssistenteGeoLocalizacao());
		hp.add(montaIconeAssistenteUpload());
		hp.add(montaIconeTarifas());
		hp.add(montaIconeMeusImoveis());
		hp.add(montaIconeVideos());
		return hp;
	}
	
	private Widget montaIconeChat() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "message_warning.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarMinhaContaListener lstnr : listeners) {
						lstnr.onChatClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Avisos");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarMinhaContaListener lstnr : listeners) {
						lstnr.onChatClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}
	
	
	private Widget montaIconeTarifas() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "currency_dollar.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarMinhaContaListener lstnr : listeners) {
						lstnr.onTarifasClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Tarifas");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarMinhaContaListener lstnr : listeners) {
						lstnr.onTarifasClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}
	
	
	private Widget montaIconeMeuDesktop() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "desktop.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarMinhaContaListener lstnr : listeners) {
						lstnr.onDesktopClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Meu Desktop");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarMinhaContaListener lstnr : listeners) {
						lstnr.onDesktopClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}
	
	private Widget montaIconeTrocaSenha() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "key1_edit.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarMinhaContaListener lstnr : listeners) {
						lstnr.onTrocaSenhaClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Troca Senha");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarMinhaContaListener lstnr : listeners) {
						lstnr.onTrocaSenhaClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}

	private Widget montaIconeAssistenteNovoImovel() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "wizard.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarMinhaContaListener lstnr : listeners) {
						lstnr.onAssistenteNovoImovelClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Novo Im\u00f3vel");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarMinhaContaListener lstnr : listeners) {
						lstnr.onAssistenteNovoImovelClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}
	private Widget montaIconeAssistenteUpload() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "wizard.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarMinhaContaListener lstnr : listeners) {
						lstnr.onAssistenteUploadClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Imagens");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarMinhaContaListener lstnr : listeners) {
						lstnr.onAssistenteUploadClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}
	private Widget montaIconeAssistenteGeoLocalizacao() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "wizard.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarMinhaContaListener lstnr : listeners) {
						lstnr.onAssistenteGeoLocalizacaoClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Geo-Localiza\u00e7\u00e3o");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarMinhaContaListener lstnr : listeners) {
						lstnr.onAssistenteGeoLocalizacaoClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}

	private Widget montaIconeVideos() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "movie.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarMinhaContaListener lstnr : listeners) {
						lstnr.onVideoClick();
					}
				}
			}
		});
		
		Label lbl = new Label("V\u00eddeos");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarMinhaContaListener lstnr : listeners) {
						lstnr.onVideoClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}
	
	private Widget montaIconeMeusImoveis() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "houses.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarMinhaContaListener lstnr : listeners) {
						lstnr.onMeusImoveisClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Meus Im\u00f3veis");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarMinhaContaListener lstnr : listeners) {
						lstnr.onMeusImoveisClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}
	

	private Widget montaIconeMinhaConta() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "user1.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarMinhaContaListener lstnr : listeners) {
						lstnr.onMeusDadosClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Meus Dados");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarMinhaContaListener lstnr : listeners) {
						lstnr.onMeusDadosClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}
}
