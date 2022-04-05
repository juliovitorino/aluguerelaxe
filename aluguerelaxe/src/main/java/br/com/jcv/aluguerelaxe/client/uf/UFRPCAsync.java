package br.com.jcv.aluguerelaxe.client.uf;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.charter.CharterVO;
import br.com.jcv.aluguerelaxe.client.vo.CidadeVO;

import com.google.gwt.user.client.rpc.AsyncCallback;


public interface UFRPCAsync {

	void listarCidadesDaUF(String uf, AsyncCallback<List<CidadeVO>> callback);
	void listarCidadesDaUFComImoveis(String uf, AsyncCallback<List<CidadeVO>> callback);
	void charterSumarizadoImoveisUF(AsyncCallback<CharterVO> callback);

	

}

