package br.com.jcv.aluguerelaxe.client.imovel.local;

import java.util.Date;

import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.user.client.rpc.IsSerializable;

public class LocalItemVO extends VOPadrao implements IsSerializable {
	public long id;
	public long idLocal;
	public long idUfCidadeItem;
	public String uf;
	public String cidade;
	public String classificacao;
	public String descricaoBase;
	public String descricao;
	public double latitude;
	public double longitude;
	public String urlRef_1;
	public String urlRef_2;
	public String urlRef_3;
	public String urlRef_4;
	public String urlRef_5;
	public Date dataCadastro;
	public String urlIconeLocal;
	public String urlIconeLocalItem;
	public DistanciaVO distancia;
}
