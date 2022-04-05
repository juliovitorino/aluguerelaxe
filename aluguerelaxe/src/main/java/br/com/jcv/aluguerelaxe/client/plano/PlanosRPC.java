package br.com.jcv.aluguerelaxe.client.plano;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;



@RemoteServiceRelativePath("planosrpc")
public interface PlanosRPC extends RemoteService {

	
	List<PlanoVO> listarPlanosAtivos();
	List<PlanoVO> listarPlanosPorTipo(String tipo);
	PlanoVO pesquisarPlano(long idPlano);
	
	
	public static class Util {
		public static PlanosRPCAsync getInstance() {
			return GWT.create(PlanosRPC.class);
		}
	}

}

