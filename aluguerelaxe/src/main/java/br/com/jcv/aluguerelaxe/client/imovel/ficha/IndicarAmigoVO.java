package br.com.jcv.aluguerelaxe.client.imovel.ficha;

import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.user.client.rpc.IsSerializable;

public class IndicarAmigoVO extends VOPadrao implements IsSerializable {

	public String seunome;
	public String seuemail;
	public String nomeamigo;
	public String emailamigo;
	public String mensagem;
}
