package br.com.jcv.aluguerelaxe.client.faleconosco;

import com.google.gwt.user.client.rpc.IsSerializable;

import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

public class FaleConoscoVO extends VOPadrao implements IsSerializable {
	public String nome;
	public String email;
	public String assunto;
	public String topico;
	public String mensagem;
}
