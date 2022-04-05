package br.com.jcv.aluguerelaxe.client.mobile.ficha;

import br.com.jcv.aluguerelaxe.client.mobile.vo.EnvelopeFichaVO;
import br.com.jcv.aluguerelaxe.client.mobile.vo.ImovelFichaCompletaVO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface FichaImovelRPCAsync {

	void pesquisarFichaCompletaImovel(long codigoImovel,AsyncCallback<ImovelFichaCompletaVO> callback);
	void apresentarFichaCompletaImovel(long codigoImovel,String origemAcesso,AsyncCallback<EnvelopeFichaVO> callback);
	
}
