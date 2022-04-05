package br.com.jcv.aluguerelaxe.client.imovel.local;

import java.util.Date;

import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ClassificacaoVO extends VOPadrao implements IsSerializable {
	public long id;
	public String descricao;
	public Date dataCadastro;
}
