
package br.com.jcv.aluguerelaxe.client.componente.paginacao;

import java.util.List;
import java.util.Map;

import br.com.jcv.aluguerelaxe.client.componente.datagrid.CondicaoVO;
import br.com.jcv.aluguerelaxe.client.componente.datagrid.EnvelopePaginacaoDataGridVO;
import br.com.jcv.aluguerelaxe.client.imovel.EnvelopePaginacaoVO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PaginacaoRPCAsync {

	void buscarPagina(Map<String, String> param, int indice, int qtdePorPagina,
			AsyncCallback<EnvelopePaginacaoVO> callback);
			
	void buscarPagina(String tabelaView, 
			List<String> campos, 
			Map<String,String> param,  
			List<CondicaoVO> condicao,
			List<String> lstOrderBy, 
			int indice, 
			int qtdePorPagina, AsyncCallback<EnvelopePaginacaoDataGridVO> callback);
}

