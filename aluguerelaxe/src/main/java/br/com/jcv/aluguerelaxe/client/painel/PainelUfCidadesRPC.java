package br.com.jcv.aluguerelaxe.client.painel;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("painelufcidadesrpc")
public interface PainelUfCidadesRPC extends RemoteService {

	public List<PainelUfCidadesVO> listarMaioresQtdeImoveisPorEstado();
	public List<PainelUfCidadesVO> listarCidadesDaUf(String uf);
	public List<PainelUfCidadesVO> listarEstadosMaisVisitados();

	public static class Util {

		public static PainelUfCidadesRPCAsync getInstance() {

			return GWT.create(PainelUfCidadesRPC.class);
		}
	}

}
