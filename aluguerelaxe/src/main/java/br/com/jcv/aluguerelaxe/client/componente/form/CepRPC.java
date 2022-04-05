package br.com.jcv.aluguerelaxe.client.componente.form;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.vo.CepVO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("ceprpc")
public interface CepRPC extends RemoteService {
	
	CepVO buscaEndereco(String cep) throws AlugueRelaxeFrontException;

	public static class Util {
		public static CepRPCAsync getInstance() {
			return GWT.create(CepRPC.class);
		}
	}
	
}

