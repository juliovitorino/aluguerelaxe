package br.com.jcv.aluguerelaxe.client.destino;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.vo.CidadeUFVO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * <h1>DestinoRPC</h1>
 * <p>Interface para os métodos que estarão expostos para
 * chamadas RPC para a combo Destino na primeira página do site. 
 * </p>
 * @author julio
 *
 */
@RemoteServiceRelativePath("destinorpc")
public interface DestinoRPC extends RemoteService {
	
	/**
	 * <h1>listDestinos</h1>
	 * <p>Busca todas as UFs disponíveis na base de dados do site.</p>
	 * @author julio
	 */
	public List<DestinoVO> listDestinos();
	
	public CidadeUFVO ProcurarUFCidadeItem(long idUfCidadeItem) throws AlugueRelaxeFrontException;


	public static class Util {

		public static DestinoRPCAsync getInstance() {

			return GWT.create(DestinoRPC.class);
		}
	}

}
