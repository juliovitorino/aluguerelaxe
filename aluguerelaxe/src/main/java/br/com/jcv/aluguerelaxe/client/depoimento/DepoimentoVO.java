package br.com.jcv.aluguerelaxe.client.depoimento;

import com.google.gwt.user.client.rpc.IsSerializable;

public class DepoimentoVO implements IsSerializable {

	public Long id;
	public String depoimento;
	public String nome;
	public String dataDepoimento;
}
