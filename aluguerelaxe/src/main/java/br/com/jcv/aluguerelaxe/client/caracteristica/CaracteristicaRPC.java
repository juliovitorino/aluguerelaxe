package br.com.jcv.aluguerelaxe.client.caracteristica;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("caracteristicarpc")
public interface CaracteristicaRPC extends RemoteService {

	/**
	 * Listar todas as caracteristicas de um determinado tipo
	 *
	 * @param tipo - Tipo de caracteristica desejada. Os valores validos sao dados pelas constantes:<br/>
	 * <code>CaracteristicaService.IMOVEL</code> e <code>CaracteristicaService.CONDOMINIO</code> 
	 * @return List
	 * @throws AlugueRelaxeFrontException Comentar aqui.
	 */
	List<CaracteristicaVO> listarCaracteristicas() throws AlugueRelaxeFrontException;
	
	public static class Util {
		public static CaracteristicaRPCAsync getInstance() {
			return GWT.create(CaracteristicaRPC.class);
		}
	}

}

