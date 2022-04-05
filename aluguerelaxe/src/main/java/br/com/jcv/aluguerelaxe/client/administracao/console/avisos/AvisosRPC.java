package br.com.jcv.aluguerelaxe.client.administracao.console.avisos;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.componente.chat.ChatVO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("avisosrpc")
public interface AvisosRPC extends RemoteService {

	ChatVO getChatAtivo(String sessao) throws AlugueRelaxeFrontException;
	ChatVO getChatPrivado(String sessao, long idCliente) throws AlugueRelaxeFrontException;
	
	public static class Util {

		public static AvisosRPCAsync getInstance() {

			return GWT.create(AvisosRPC.class);
		}
	}
	

}
