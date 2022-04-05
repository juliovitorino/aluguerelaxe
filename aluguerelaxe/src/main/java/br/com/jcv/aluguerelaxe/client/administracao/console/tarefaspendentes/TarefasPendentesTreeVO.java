package br.com.jcv.aluguerelaxe.client.administracao.console.tarefaspendentes;

import br.com.jcv.aluguerelaxe.client.administracao.login.SessaoVO;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.user.client.rpc.IsSerializable;

public class TarefasPendentesTreeVO extends VOPadrao implements IsSerializable {
	public String codigoTracking;
	public String secaoTreeview;
	public SessaoVO sessaovo;
}