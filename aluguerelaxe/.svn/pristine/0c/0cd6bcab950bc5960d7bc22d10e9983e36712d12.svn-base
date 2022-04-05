package br.com.jcv.aluguerelaxe.client.depoimento;


import java.util.List;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * <h1>DepoimentoRPC</h1>
 * <p>Metodos de acesso remoto para depoimento 
 * </p>
 * @author julio
 *
 */
@RemoteServiceRelativePath("depoimentorpc")
public interface DepoimentoRPC extends RemoteService {

	/**
	 * Criar um novo depoimento do usuário
	 */ 
	void criarNovoDepoimento(DepoimentoVO dvo) throws AlugueRelaxeFrontException;

	/**
	 * Busca no backend services uma pagina com um certo numero
	 * definido por uma variavel configurada
	 */ 
	List<DepoimentoVO> ListarPaginaDepoimentos() throws AlugueRelaxeFrontException;
	
	@Deprecated
	DepoimentoVO getProximoDepoimento(long id);

	@Deprecated
	DepoimentoVO getPrevDepoimento(long id);

	
	
	public static class Util {
		public static DepoimentoRPCAsync getInstance() {
			return GWT.create(DepoimentoRPC.class);
		}
	}
}
