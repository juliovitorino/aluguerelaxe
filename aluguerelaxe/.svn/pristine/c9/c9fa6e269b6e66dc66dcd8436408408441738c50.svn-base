package br.com.jcv.aluguerelaxe.client.componente.galeria;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("galeriaimovelrpc")
public interface GaleriaImovelRPC extends RemoteService {

	List<ImovelFichaCompletaVO> listarGaleriaImovel(long clienteId) throws AlugueRelaxeFrontException;
	List<ImovelFichaCompletaVO> listarGaleriaImovel(String indPatrocinadorColaborador) throws AlugueRelaxeFrontException;
	
	public static class Util {
		public static GaleriaImovelRPCAsync getInstance() {
			return GWT.create(GaleriaImovelRPC.class);
		}
	}

}
