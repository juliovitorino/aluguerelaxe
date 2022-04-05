
package br.com.jcv.aluguerelaxe.client.administracao.login;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("loginrpc")
public interface LoginRPC extends RemoteService {
	
	SessaoVO login(String email, String senha) throws AlugueRelaxeFrontException;
	SessaoVO validarSessao(String sid) ;
	VOPadrao solicitarNovoCodigoAcesso(NovoCodigoAcessoVO dto) throws AlugueRelaxeFrontException;
	VOPadrao encerrarSessao() throws AlugueRelaxeFrontException;
	
	public static class Util {

		public static LoginRPCAsync getInstance() {
			return GWT.create(LoginRPC.class);
		}
	}
	


}

