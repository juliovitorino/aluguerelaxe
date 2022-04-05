package br.com.jcv.aluguerelaxe.client.componente.chavevalor;

import com.google.gwt.user.client.ui.Widget;

public class ChaveValor {
	private String descricao;
	private String chave;
	private Widget widgetui;
	
	public ChaveValor(String chave, Widget widgetui, String descricao ) {
		this.chave = chave;
		this.widgetui = widgetui;
		this.descricao = descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public void setWidgetui(Widget widgetui) {
		this.widgetui = widgetui;
	}

	public String getDescricao() {
		return this.descricao;
	}
	
	public String getChave() {
		return this.chave;
	}
	
	public Widget getWidgetUi() {
		return this.widgetui;
	}
	
}