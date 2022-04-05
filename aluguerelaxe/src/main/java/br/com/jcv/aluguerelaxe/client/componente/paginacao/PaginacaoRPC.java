
package br.com.jcv.aluguerelaxe.client.componente.paginacao;

import java.util.List;
import java.util.Map;

import br.com.jcv.aluguerelaxe.client.componente.datagrid.CondicaoVO;
import br.com.jcv.aluguerelaxe.client.componente.datagrid.EnvelopePaginacaoDataGridVO;
import br.com.jcv.aluguerelaxe.client.imovel.EnvelopePaginacaoVO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Interface com métodos para busca paginada
 *
 * @author Julio Vitorino
 *
 * @param <V>
 */
@RemoteServiceRelativePath("paginacaorpc")
public interface PaginacaoRPC extends RemoteService {

	EnvelopePaginacaoVO buscarPagina(Map<String,String> param, int indice, int qtdePorPagina);
	EnvelopePaginacaoDataGridVO buscarPagina(String tabelaView, 
												List<String> campos, 
												Map<String,String> param, 
												List<CondicaoVO> condicao, 
												List<String> lstOrderBy, 
												int indice, 
												int qtdePorPagina);
	
	public static class Util {
		@SuppressWarnings("unchecked")
		public static PaginacaoRPCAsync getInstance() {
			return GWT.create(PaginacaoRPC.class);
		}
	}

}

