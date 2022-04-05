package br.com.jcv.aluguerelaxe.client.publicidade;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * <h1>PublicidadeRPC</h1>
 * <p>Interface para os m�todos que estar�o expostos para
 * chamadas RPC para o painel de publicidade na
 * primeira p�gina do site. 
 * </p>
 * @author julio
 *
 */
@RemoteServiceRelativePath("publicidaderpc")
public interface PublicidadeRPC extends RemoteService {

	/**
	 * <h2>getPainelPublicidade</h2>
	 * <p>M�todo respons�vel por recuperar as publicidades do painel principal
	 * da primeira p�gina.
	 * </p>
	 * @return List - Lista contendo objetos <code>PublicidadeVO</code>
	 */
	public List<ImovelPublicidadeVO> getPainelPublicidade();
	
	
	public static class Util {

		public static PublicidadeRPCAsync getInstance() {

			return GWT.create(PublicidadeRPC.class);
		}
	}
}
