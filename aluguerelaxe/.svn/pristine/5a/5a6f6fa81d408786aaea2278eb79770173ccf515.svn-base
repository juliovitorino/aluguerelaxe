package br.com.jcv.aluguerelaxe.client.componente.datagrid;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;

public abstract class AbstractButtonFormPesquisa<F extends AbstractFormPesquisa<?,?>> extends Image {
	
	private static final String STYLE = "gwt-AbstractButtonFormPesquisa";

	public abstract F getInstance();
	public abstract String getImageUrl();
	
	private PopupPanel dialog;
	private F afp;
	
	public AbstractButtonFormPesquisa() {
		super();
		init();
		this.setUrl(getImageUrl());
		this.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				AbstractButtonFormPesquisa.this.dialog = new PopupPanel();
				AbstractButtonFormPesquisa.this.dialog.setGlassEnabled(false);
				AbstractButtonFormPesquisa.this.dialog.center();
				AbstractButtonFormPesquisa.this.dialog.setWidget(AbstractButtonFormPesquisa.this.afp);
			}
		});
		this.setStyleName(STYLE);
		
	}
	
	private void init(){
		this.afp = this.getInstance();
	}
	
	public PopupPanel getDialog(){
		return dialog;
	}
	
	public F getFormPesquisa(){
		return this.afp;
	}
	
	/**
	 *<h2>addListener</h2>
	 *<p>Inscreve a instância da classe que implementa @link{FormPesquisaListener} na lista de ouvintes.
	 *</p>
	 * @param FormPesquisaListener
	 */
	public void addListener(AbstractFormPesquisaListener fl) {
		this.afp.addListener(fl);
	}
	
	
}