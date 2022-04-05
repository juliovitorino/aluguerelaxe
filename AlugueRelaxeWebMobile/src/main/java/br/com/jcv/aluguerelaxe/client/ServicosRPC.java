package br.com.jcv.aluguerelaxe.client;

import br.com.jcv.aluguerelaxe.client.mobile.componente.paginacao.PaginacaoRPC;
import br.com.jcv.aluguerelaxe.client.mobile.componente.paginacao.PaginacaoRPCAsync;
import br.com.jcv.aluguerelaxe.client.mobile.ficha.FichaImovelRPC;
import br.com.jcv.aluguerelaxe.client.mobile.ficha.FichaImovelRPCAsync;
import br.com.jcv.aluguerelaxe.client.mobile.uf.UFRPC;
import br.com.jcv.aluguerelaxe.client.mobile.uf.UFRPCAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

/**
 * <h1>ServicosRPC</h1>
 * <p>Classe helper respons√°vel por disponibilizar m√©todos de recupera√ß√£o
 * das interfaces ass√≠ncronas para as chamadas RPC.
 * </p>
 * @author julio
 *
 */
public class ServicosRPC {

	/**
	 * <h2>UF_RPC</h2>
	 * <p>identificaÁ„o da chamada RPC</p>
	 */
	private static final String UF_RPC = "ufrpc";
	/**
	 * <h2>PAGINACAO_RPC</h2>
	 * <p>identificaÁ„o da chamada RPC</p>
	 */
	private static final String PAGINACAO_RPC = "paginacaorpc";
	/**
	 * <h2>FICHAIMOVEL_RPC</h2>
	 * <p>identificaÁ„o da chamada RPC</p>
	 */
	private static final String FICHA_IMOVEL_RPC = "fichaimovelrpc";
	
	/**
	 * <h2>getFichaImovelRPC</h2>
	 * <p>Metodo responsavel por retornar uma interface para 
	 * FichaImovelRPCAsync</p>
	 */
	public static FichaImovelRPCAsync getFichaImovelRPC() {
		FichaImovelRPCAsync newsservice = FichaImovelRPC.Util.getInstance();
		ServiceDefTarget endpoint = (ServiceDefTarget) newsservice;
		String moduleRelativeURL = GWT.getModuleBaseURL() + FICHA_IMOVEL_RPC; 
		endpoint.setServiceEntryPoint(moduleRelativeURL);
		return newsservice;
	}
	/**
	 * <h2>getUFRPC</h2>
	 * <p>Metodo responsavel por retornar uma interface para 
	 * LoginRPCAsync</p>
	 */
	public static UFRPCAsync getUFRPC() {
		UFRPCAsync newsservice = UFRPC.Util.getInstance();
		ServiceDefTarget endpoint = (ServiceDefTarget) newsservice;
		String moduleRelativeURL = GWT.getModuleBaseURL() + UF_RPC; 
		endpoint.setServiceEntryPoint(moduleRelativeURL);
		return newsservice;
	}
	/**
	 * <h2>getPaginacaoRPC</h2>
	 * <p>Metodo responsavel por retornar uma interface para 
	 * PaginacaoRPCAsync</p>
	 */
	@SuppressWarnings("unchecked")
	public static PaginacaoRPCAsync getPaginacaoRPC() {
		PaginacaoRPCAsync newsservice = PaginacaoRPC.Util.getInstance();
		ServiceDefTarget endpoint = (ServiceDefTarget) newsservice;
		String moduleRelativeURL = GWT.getModuleBaseURL() + PAGINACAO_RPC; 
		endpoint.setServiceEntryPoint(moduleRelativeURL);
		return newsservice;
	}
	
}
