package br.com.jcv.aluguerelaxe.client.destaques;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.publicidade.ImovelPublicidadeVO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * <h1>DestaquesRPC</h1>
 * <p>Interface para os métodos que estarão expostos para
 * chamadas RPC para o painel de publicidade de destaque. 
 * </p>
 * @author julio
 *
 */
@RemoteServiceRelativePath("destaquesrpc")
public interface DestaquesRPC extends RemoteService {
	/**
	 * <h2>getPainelDestaques</h2>
	 * <p>Método responsável por recuperar as publicidades de destaque.
	 * </p>
	 * @return List - Lista contendo objetos <code>ImovelPublicidadeVO</code>
	 */
	public List<ImovelPublicidadeVO> getPainelDestaques();
	
	
	public static class Util {

		public static DestaquesRPCAsync getInstance() {

			return GWT.create(DestaquesRPC.class);
		}
	}
	

}
