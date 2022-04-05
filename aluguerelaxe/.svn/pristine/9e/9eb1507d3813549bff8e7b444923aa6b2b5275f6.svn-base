
package br.com.jcv.aluguerelaxe.client.imovel.caracteristica;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("imovelcaracteristicarpc")
public interface ImovelCaracteristicaRPC extends RemoteService {

	List<ImovelCaracteristicaVO> listarCaracteristicas(long imovelId, String tipo) throws AlugueRelaxeFrontException;
	
	public static class Util {
		public static ImovelCaracteristicaRPCAsync getInstance() {
			return GWT.create(ImovelCaracteristicaRPC.class);
		}
	}
}

