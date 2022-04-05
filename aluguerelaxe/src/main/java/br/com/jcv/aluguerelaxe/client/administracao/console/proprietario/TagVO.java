package br.com.jcv.aluguerelaxe.client.administracao.console.proprietario;

import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;

public class TagVO extends VOPadrao implements IsSerializable {
	public int idtag;
	public Image img;
	public HTML texto;
}