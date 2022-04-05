package br.com.jcv.aluguerelaxe.client.administracao.console.proprietario;

import br.com.jcv.aluguerelaxe.client.componente.foto.AbstractFoto;
import br.com.jcv.aluguerelaxe.client.imovel.imagem.ImovelImagemVideoVO;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class FotoPerfil extends AbstractFoto {

	private static final String GWT_STYLE = "gwt-FotoPerfil";

	public FotoPerfil(ImovelImagemVideoVO iivvo) {
		super(iivvo);
		this.setStyleName(GWT_STYLE);
		this.getImg().setWidth("160px");
		this.getImg().setHeight("120px");
	}
	
	protected Panel montaTitulo() {
		return new VerticalPanel();
	}
	
	protected Panel montaRodape() {
		return new VerticalPanel();
	}

}
