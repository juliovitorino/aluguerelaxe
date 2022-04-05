package br.com.jcv.aluguerelaxe.client.painel;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("painelultimoscadastradosrpc")
public interface PainelUltimosCadastradosRPC extends RemoteService {
	
	List<ImovelFichaCompletaVO> listarUltimosImoveisCadastrados() throws AlugueRelaxeFrontException;
	
	public static class Util {
		public static PainelUltimosCadastradosRPCAsync getInstance() {
			return GWT.create(PainelUltimosCadastradosRPC.class);
		}
	}

}
