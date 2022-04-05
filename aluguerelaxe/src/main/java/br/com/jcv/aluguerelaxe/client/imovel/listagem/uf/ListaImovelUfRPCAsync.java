package br.com.jcv.aluguerelaxe.client.imovel.listagem.uf;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;
import br.com.jcv.aluguerelaxe.client.vo.CidadeUFVO;

import com.google.gwt.user.client.rpc.AsyncCallback;


public interface ListaImovelUfRPCAsync {

	void buscaListaImovelUf(String uf, int pagina,
			AsyncCallback<List<ImovelFichaCompletaVO>> callback);

	void listarQtdeImoveisCidadeUF(AsyncCallback<List<CidadeUFVO>> callback);

}
