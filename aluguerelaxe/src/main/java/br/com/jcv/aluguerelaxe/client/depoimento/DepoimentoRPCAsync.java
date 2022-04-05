package br.com.jcv.aluguerelaxe.client.depoimento;


import java.util.List;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DepoimentoRPCAsync {

	void criarNovoDepoimento(DepoimentoVO dvo, AsyncCallback<Void> callback) throws AlugueRelaxeFrontException;
	void ListarPaginaDepoimentos(AsyncCallback<List<DepoimentoVO>> callback) throws AlugueRelaxeFrontException;	
	
	@Deprecated
	void getProximoDepoimento(long id, AsyncCallback<DepoimentoVO> callback);
	
	@Deprecated
	void getPrevDepoimento(long id, AsyncCallback<DepoimentoVO> callback);

}
