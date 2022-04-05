package br.com.jcv.aluguerelaxe.client.publicidade;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;


public interface PublicidadeRPCAsync {
	public void getPainelPublicidade(AsyncCallback<List<ImovelPublicidadeVO>> callback);

}
