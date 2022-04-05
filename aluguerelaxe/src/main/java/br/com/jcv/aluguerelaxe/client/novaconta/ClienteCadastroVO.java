package br.com.jcv.aluguerelaxe.client.novaconta;

import br.com.jcv.aluguerelaxe.client.cliente.ClienteVO;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ClienteCadastroVO extends ClienteVO implements IsSerializable {

	public String senha;
	public String senhaConfirmacao;
}
