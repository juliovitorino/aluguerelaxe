package br.com.jcv.aluguerelaxe.client.plano;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;


public interface PlanosRPCAsync {
	void listarPlanosAtivos(AsyncCallback<List<PlanoVO>> callback);
	void listarPlanosPorTipo(String tipo, AsyncCallback<List<PlanoVO>> callback);
	void pesquisarPlano(long idPlano, AsyncCallback<PlanoVO> callback);
}

