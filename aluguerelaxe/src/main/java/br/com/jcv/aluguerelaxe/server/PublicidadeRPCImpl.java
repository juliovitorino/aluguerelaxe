package br.com.jcv.aluguerelaxe.server;

import java.util.List;

import org.apache.log4j.Logger;

import br.com.jcv.aluguerelaxe.client.publicidade.ImovelPublicidadeVO;
import br.com.jcv.aluguerelaxe.client.publicidade.PublicidadeRPC;
import br.com.jcv.aluguerelaxe.server.cache.PublicidadeCache;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * <h1>PublicidadeRPCImpl</h1>
 * <p>Classe responsável por realizar as implementações de RPC da
 * interface PublicidadeRPC.
 * <p>
 * @author julio
 *
 */
public class PublicidadeRPCImpl extends RemoteServiceServlet implements PublicidadeRPC {

	/**
	 * <h2>logger</h2>
	 * <p>logger estático para log4j</p>
	 */
	private static final Logger logger = Logger.getLogger(PublicidadeRPCImpl.class.getName());
	
	/**
	 * <h2>serialVersionUID</h2>
	 * <p>Serial Version UID para serialização.</p>
	 */
	private static final long serialVersionUID = 7707490979139653662L;
	
	
	/**
	 * <h2>getPainelPublicidade</h2>
	 * <p>Busca publicidades de primeira pagina no cache</p>
	 * @return List
	 * @see br.com.jcv.aluguerelaxe.client.publicidade.PublicidadeRPC#getPainelPublicidade()
	 */
	public List<ImovelPublicidadeVO> getPainelPublicidade() {
		return PublicidadeCache.getInstance(this.getServletContext()).getPublicidadeCache();
	}


}
