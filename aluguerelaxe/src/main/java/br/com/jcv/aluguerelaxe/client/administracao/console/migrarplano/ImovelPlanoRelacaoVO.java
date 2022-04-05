package br.com.jcv.aluguerelaxe.client.administracao.console.migrarplano;

import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ImovelPlanoRelacaoVO extends VOPadrao implements IsSerializable {
	public long idImovel;
	public long idPlano;
}
