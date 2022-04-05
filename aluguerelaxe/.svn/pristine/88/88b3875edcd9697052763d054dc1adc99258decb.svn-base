package br.com.jcv.aluguerelaxe.client.administracao.console;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import br.com.jcv.aluguerelaxe.client.componente.toolbar.AbstractToolbar;

public class ToolbarFinanceiro extends
		AbstractToolbar<ToolbarFinanceiroListener> {

	private static final String PATH_IMAGENS = "images/32x32/";

	public ToolbarFinanceiro() {
		super();
	}
	
	@Override
	public Widget render() {
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(montaIconeMigrarPlano());
		hp.add(montaIconePagarPlano());
		hp.add(montaIconeRenovarPlano());
		return hp;
	}
	
	private Widget montaIconeRenovarPlano() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "scroll_replace.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarFinanceiroListener lstnr : listeners) {
						lstnr.onRenovarPlanoClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Renovar Plano");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarFinanceiroListener lstnr : listeners) {
						lstnr.onRenovarPlanoClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}
	
	private Widget montaIconeMigrarPlano() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "scroll_replace.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarFinanceiroListener lstnr : listeners) {
						lstnr.onMigrarPlanoClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Migrar Plano");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarFinanceiroListener lstnr : listeners) {
						lstnr.onMigrarPlanoClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}

	
	private Widget montaIconePagarPlano() {
		VerticalPanel vp = new VerticalPanel();
		
		Image imagem = new Image(PATH_IMAGENS + "money_envelope_edit.png");
		imagem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarFinanceiroListener lstnr : listeners) {
						lstnr.onPagarPlanoClick();
					}
				}
			}
		});
		
		Label lbl = new Label("Pagar Plano");
		lbl.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarFinanceiroListener lstnr : listeners) {
						lstnr.onPagarPlanoClick();
					}
				}
			}
		});
		vp.add(imagem);
		vp.add(lbl);
		
		return vp;
	}
	
}
