package br.com.jcv.aluguerelaxe.client.mobile.vo;

import br.com.jcv.ui.client.vo.VOPadrao;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ComandoListaImovelDTO extends VOPadrao implements IsSerializable {
	public int cmd ;
	public String uf;
	public int cidade;
}
