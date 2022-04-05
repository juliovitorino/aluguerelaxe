package br.com.jcv.aluguerelaxe.client.administracao.console;

import br.com.jcv.aluguerelaxe.client.componente.toolbar.AbstractToolbar;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ToolbarPublicidade extends AbstractToolbar<ToolbarPublicidadeListener> {

	private static final String PATH_IMAGENS = "images/32x32/";

	public ToolbarPublicidade() {
		super();
	}
	
	@Override
	public Widget render() {
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(montaIconeMinhasPublicidades());
		hp.add(montaIconePatrocinio());
		hp.add(montaIconeFuraFila());
		hp.add(montaIconePrimeiraPagina());
		hp.add(montaIconePainelDestaque());
		hp.add(montaIconeBanner());
		hp.add(montaIconeSMS());
		hp.add(montaIconeEmail());
		hp.add(montaIconeSuperBanner());
		hp.add(montaIconeFacebook());
		return hp;
	}

	private Widget montaIconeFacebook() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "wizard.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarPublicidadeListener lstnr : listeners) {
						lstnr.onFacebookClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Facebook");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarPublicidadeListener lstnr : listeners) {
						lstnr.onFacebookClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}


	private Widget montaIconeMinhasPublicidades() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "mydesk.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarPublicidadeListener lstnr : listeners) {
						lstnr.onMeuDesktopPublicidadeClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Meu Desktop");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarPublicidadeListener lstnr : listeners) {
						lstnr.onMeuDesktopPublicidadeClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}


	private Widget montaIconeSuperBanner() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "superbanner.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarPublicidadeListener lstnr : listeners) {
						lstnr.onSuperBannerClick();
					}
				}
			}
		});
		
		Label lbl = new Label("SuperBanner");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarPublicidadeListener lstnr : listeners) {
						lstnr.onSuperBannerClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}

	private Widget montaIconeEmail() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "at.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarPublicidadeListener lstnr : listeners) {
						lstnr.onEmailClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Email");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarPublicidadeListener lstnr : listeners) {
						lstnr.onEmailClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}

	private Widget montaIconeSMS() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "sms.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarPublicidadeListener lstnr : listeners) {
						lstnr.onSMSClick();
					}
				}
			}
		});
		
		Label lbl = new Label("SMS");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarPublicidadeListener lstnr : listeners) {
						lstnr.onSMSClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}

	private Widget montaIconeBanner() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "banner.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarPublicidadeListener lstnr : listeners) {
						lstnr.onBannerClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Banner");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarPublicidadeListener lstnr : listeners) {
						lstnr.onBannerClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}

	private Widget montaIconePainelDestaque() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "paineldestaque.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarPublicidadeListener lstnr : listeners) {
						lstnr.onPainelDestaqueClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Painel Destaque");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarPublicidadeListener lstnr : listeners) {
						lstnr.onPainelDestaqueClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}
	
	private Widget montaIconePrimeiraPagina() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "1pagina.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarPublicidadeListener lstnr : listeners) {
						lstnr.onPrimeiraPaginaClick();
					}
				}
			}
		});
		
		Label lbl = new Label("1a P\u00e1gina");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarPublicidadeListener lstnr : listeners) {
						lstnr.onPrimeiraPaginaClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}
	
	private Widget montaIconePatrocinio() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "patrocinio.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarPublicidadeListener lstnr : listeners) {
						lstnr.onPatrocinioClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Patroc\u00ednio");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarPublicidadeListener lstnr : listeners) {
						lstnr.onPatrocinioClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}

	private Widget montaIconeFuraFila() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "furafila.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarPublicidadeListener lstnr : listeners) {
						lstnr.onFuraFilaClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Fura-Fila");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarPublicidadeListener lstnr : listeners) {
						lstnr.onFuraFilaClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}

}
