package br.com.jcv.aluguerelaxe.client.imovel.listagem.uf;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;
import br.com.jcv.aluguerelaxe.client.vo.CidadeUFVO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * <h1>FichaImovelRPC</h1>
 * <p>Interface para os métodos que estarão expostos para
 * chamadas RPC para o painel de publicidade de destaque. 
 * </p>
 * @author julio
 *
 */
@RemoteServiceRelativePath("listaimovelufrpc")
public interface ListaImovelUfRPC extends RemoteService {
	
	List<ImovelFichaCompletaVO> buscaListaImovelUf(String uf, int pagina);
	List<CidadeUFVO> listarQtdeImoveisCidadeUF();
	
	
	public static class Util {
		public static ListaImovelUfRPCAsync getInstance() {
			return GWT.create(ListaImovelUfRPC.class);
		}
	}
}
