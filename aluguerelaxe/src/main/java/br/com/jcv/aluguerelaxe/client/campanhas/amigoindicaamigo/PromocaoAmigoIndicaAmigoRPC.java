package br.com.jcv.aluguerelaxe.client.campanhas.amigoindicaamigo;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.campanhas.promocaopadrao.PromocaoPadraoVO;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("paiarpc")
public interface PromocaoAmigoIndicaAmigoRPC extends RemoteService {
	
	VOPadrao realizarInscricao(PromocaoAmigoIndicaAmigoVO vo) throws AlugueRelaxeFrontException;
	VOPadrao realizarInscricao(PromocaoPadraoVO vo) throws AlugueRelaxeFrontException;

	public static class Util {
		public static PromocaoAmigoIndicaAmigoRPCAsync getInstance() {
			return GWT.create(PromocaoAmigoIndicaAmigoRPC.class);
		}
	}
	
}

