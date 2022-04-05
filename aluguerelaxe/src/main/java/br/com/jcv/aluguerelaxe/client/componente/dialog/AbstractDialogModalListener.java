package br.com.jcv.aluguerelaxe.client.componente.dialog;

import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

public interface AbstractDialogModalListener<V extends VOPadrao> {
	void onExecute(V vo);
}
