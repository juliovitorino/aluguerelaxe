package br.com.jcv.aluguerelaxe.client.modopublicidade;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ModoPublicidadeRPCAsync {

	void listarModoPublicidade(AsyncCallback<List<ModoPublicidadeVO>> callback);

}
