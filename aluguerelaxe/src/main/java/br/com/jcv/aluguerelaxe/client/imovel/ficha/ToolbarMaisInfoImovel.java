package br.com.jcv.aluguerelaxe.client.imovel.ficha;

import br.com.jcv.aluguerelaxe.client.componente.toolbar.AbstractToolbar;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ToolbarMaisInfoImovel extends
		AbstractToolbar<ToolbarMaisInfoImovelListener> {

	private static final String PATH_IMAGENS = "images/32x32/";

	public ToolbarMaisInfoImovel() {
		super();
		//this.setStylePrimaryName("gwt-ToolbarMaisInfoImovel");
	}
	
	@Override
	public Widget render() {
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(montaIconeMaisInfoDadosImovel());
		hp.add(montaIconeLocalizacao());
		hp.add(montaIconeGaleriaFotos());
		hp.add(montaIconeCaracteristicasImovel());
		hp.add(montaIconeCaracteristicasCondominio());
		hp.add(montaIconeTarifas());
		hp.add(montaIconeContatoAnunciante());
		//hp.add(montaIconeDisponibilidadeImovel());
		hp.add(montaIconeVideoImovel());
		hp.add(montaIconeObservacao());
		hp.add(montaIconeIndicarAmigo());
		return hp;
	}

	private Widget montaIconeVideoImovel() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "movie.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarMaisInfoImovelListener lstnr : listeners) {
						lstnr.onVideoImovelClick();
					}
				}
			}
		});
		
		Label lbl = new Label("V\u00eddeo");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarMaisInfoImovelListener lstnr : listeners) {
						lstnr.onVideoImovelClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}	

	private Widget montaIconeIndicarAmigo() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "indicar_amigo.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarMaisInfoImovelListener lstnr : listeners) {
						lstnr.onIndicarAmigoClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Indicar amigo");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarMaisInfoImovelListener lstnr : listeners) {
						lstnr.onIndicarAmigoClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}	
	private Widget montaIconeObservacao() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "contract.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarMaisInfoImovelListener lstnr : listeners) {
						lstnr.onObservacoesGeraisClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Observa\u00e7\u00f5es");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarMaisInfoImovelListener lstnr : listeners) {
						lstnr.onObservacoesGeraisClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}	

	private Widget montaIconeDisponibilidadeImovel() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "calendar.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarMaisInfoImovelListener lstnr : listeners) {
						lstnr.onDisponibilidadeClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Ocupa\u00e7\u00e3o");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarMaisInfoImovelListener lstnr : listeners) {
						lstnr.onDisponibilidadeClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}	
	
	private Widget montaIconeContatoAnunciante() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "mail.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarMaisInfoImovelListener lstnr : listeners) {
						lstnr.onContatoAnuncianteClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Contato");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarMaisInfoImovelListener lstnr : listeners) {
						lstnr.onContatoAnuncianteClick();
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
		
		Image imagem = new Image(PATH_IMAGENS + "money.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarMaisInfoImovelListener lstnr : listeners) {
						lstnr.onTarifasClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Tarifas");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarMaisInfoImovelListener lstnr : listeners) {
						lstnr.onTarifasClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}	

	
	private Widget montaIconeCaracteristicasImovel() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "houses.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarMaisInfoImovelListener lstnr : listeners) {
						lstnr.onCaracteristicasImovelClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Detalhes");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarMaisInfoImovelListener lstnr : listeners) {
						lstnr.onCaracteristicasImovelClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}	

	private Widget montaIconeCaracteristicasCondominio() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "houses.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarMaisInfoImovelListener lstnr : listeners) {
						lstnr.onCaracteristicasCondominioClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Condom\u00ednio");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarMaisInfoImovelListener lstnr : listeners) {
						lstnr.onCaracteristicasCondominioClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}	

	
	private Widget montaIconeGaleriaFotos() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "camera.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarMaisInfoImovelListener lstnr : listeners) {
						lstnr.onGaleriaFotosClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Fotos");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarMaisInfoImovelListener lstnr : listeners) {
						lstnr.onGaleriaFotosClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}	

	
	private Widget montaIconeLocalizacao() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "earth_location.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarMaisInfoImovelListener lstnr : listeners) {
						lstnr.onLocalizacaoClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Localiza\u00e7\u00e3o");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarMaisInfoImovelListener lstnr : listeners) {
						lstnr.onLocalizacaoClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}	
	
	private Widget montaIconeMaisInfoDadosImovel() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "info.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarMaisInfoImovelListener lstnr : listeners) {
						lstnr.onMaisDadosImovelClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Mais Info");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarMaisInfoImovelListener lstnr : listeners) {
						lstnr.onMaisDadosImovelClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}	

}
