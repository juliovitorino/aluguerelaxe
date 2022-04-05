package br.com.jcv.aluguerelaxe.client.componente.indicadorgauge;

import com.google.gwt.user.client.rpc.IsSerializable;

public class LimitesGauge implements IsSerializable {
	
	public int inicio;
	public int fim;
	
	public LimitesGauge(int inicio, int fim) {
		this.inicio = inicio;
		this.fim = fim;
	}
}
