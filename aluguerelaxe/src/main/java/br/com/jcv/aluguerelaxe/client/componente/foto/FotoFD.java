package br.com.jcv.aluguerelaxe.client.componente.foto;

import br.com.jcv.aluguerelaxe.client.imovel.imagem.ImovelImagemVideoVO;

public class FotoFD extends AbstractFoto {

	public FotoFD(ImovelImagemVideoVO iivvo) {
		super(iivvo);
		this.getImg().setWidth("400px");
		this.getImg().setHeight("300px");
		this.setStylePrimaryName("gwt-FotoFD");
	}

}
