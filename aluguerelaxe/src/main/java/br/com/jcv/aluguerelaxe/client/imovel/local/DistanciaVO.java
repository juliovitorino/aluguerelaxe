package br.com.jcv.aluguerelaxe.client.imovel.local;

import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.user.client.rpc.IsSerializable;

public class DistanciaVO extends VOPadrao implements IsSerializable {
	public String distanciaMt;
	public String distanciaKm;
	public String tempoGastoCarro;
	public String tempoGastoPe;
}
