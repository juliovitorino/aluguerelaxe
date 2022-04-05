package br.com.jcv.aluguerelaxe.client.componente.foto;

import br.com.jcv.aluguerelaxe.client.imovel.imagem.ImovelImagemVideoVO;

public class FotoMI extends AbstractFoto {

	public FotoMI(ImovelImagemVideoVO iivvo) {
		super(iivvo);
		this.getImg().setWidth("155px");
		this.getImg().setHeight("103px");
		
		//height: 103px;
		//width: 155px;
	}

}
