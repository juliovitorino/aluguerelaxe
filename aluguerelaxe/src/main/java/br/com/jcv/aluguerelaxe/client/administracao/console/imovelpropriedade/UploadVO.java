package br.com.jcv.aluguerelaxe.client.administracao.console.imovelpropriedade;

import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.user.client.rpc.IsSerializable;

public class UploadVO extends VOPadrao implements IsSerializable {
	public long IdImovel;
	public long IdCliente;
	public String IdSessao;
}
