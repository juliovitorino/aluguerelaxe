package br.com.jcv.aluguerelaxe.client.componente.galeria;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GaleriaImovelRPCAsync {

	void listarGaleriaImovel(long clienteId, AsyncCallback<List<ImovelFichaCompletaVO>> callback);
	void listarGaleriaImovel(String indPatrocinadorColaborador, AsyncCallback<List<ImovelFichaCompletaVO>> callback);

}
