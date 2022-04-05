package br.com.jcv.aluguerelaxe.client.enquete;

import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface EnqueteModoPublicidadeRPCAsync {

	void incrementarModoPublicidadeVisita(long idModoPublicidade,
			AsyncCallback<VOPadrao> callback);

}
