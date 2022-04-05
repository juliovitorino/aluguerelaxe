package br.com.jcv.aluguerelaxe.client.novaconta;

import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.user.client.rpc.IsSerializable;

public class NovaSenhaVO extends VOPadrao implements IsSerializable {
	public String senha;
	public String contrasenha;
}
