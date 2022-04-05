package br.com.jcv.aluguerelaxe.client.administracao.console.liberarpgto;

import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.user.client.rpc.IsSerializable;

public class LiberarPgtoVO extends VOPadrao implements IsSerializable {
	public String banco;
	public String agencia;
	public String contacorrente;
	public String favorecido;

}
