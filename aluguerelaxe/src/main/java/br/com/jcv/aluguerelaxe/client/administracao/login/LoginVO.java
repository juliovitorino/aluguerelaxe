package br.com.jcv.aluguerelaxe.client.administracao.login;

import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.user.client.rpc.IsSerializable;

public class LoginVO extends VOPadrao implements IsSerializable {
	public String email;
	public String senha;
}

