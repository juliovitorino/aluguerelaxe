package br.com.jcv.aluguerelaxe.client.mobile.vo;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ImovelCaracteristicaVO implements IsSerializable {
	public long id;
	public String indicadorCondominio;
	public CaracteristicaVO caracteristica;
	public ImovelVO imovel;

}
