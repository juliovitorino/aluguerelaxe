package br.com.jcv.aluguerelaxe.client.mobile.ficha;

import br.com.jcv.aluguerelaxe.client.mobile.vo.EnvelopeFichaVO;
import br.com.jcv.aluguerelaxe.client.mobile.vo.ImovelFichaCompletaVO;

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
@RemoteServiceRelativePath("fichaimovelrpc")
public interface FichaImovelRPC extends RemoteService {
	/**
	 * <h2>pesquisarFichaCompletaImovel</h2>
	 * <p>Recupera a ficha completa do imovel no backend</p>
	 * 
	 * @param long
	 * @return ImovelFichaCompletaVO
	 */
	public ImovelFichaCompletaVO pesquisarFichaCompletaImovel(long codigoImovel);
	
	/**
	 * <h2>apresentarFichaCompletaImovel</h2>
	 * <p>Recupera a ficha completa do imovel no backend</p>
	 * 
	 * @param long
	 * @return ImovelFichaCompletaVO
	 */
	public EnvelopeFichaVO apresentarFichaCompletaImovel(long codigoImovel, String origemAcesso);
	
	public static class Util {

		public static FichaImovelRPCAsync getInstance() {

			return GWT.create(FichaImovelRPC.class);
		}
	}
	

}
