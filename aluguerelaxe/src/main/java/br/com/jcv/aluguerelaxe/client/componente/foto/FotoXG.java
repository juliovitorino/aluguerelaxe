package br.com.jcv.aluguerelaxe.client.componente.foto;

import br.com.jcv.aluguerelaxe.client.imovel.imagem.ImovelImagemVideoVO;

public class FotoXG extends AbstractFoto {

	public FotoXG(ImovelImagemVideoVO iivvo) {
		super(iivvo);
		this.getImg().setWidth("640px");
		this.getImg().setHeight("480px");
	}

}
