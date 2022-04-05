package br.com.jcv.aluguerelaxe.client.modopublicidade;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("modopublicidaderpc")
public interface ModoPublicidadeRPC extends RemoteService {
	
	List<ModoPublicidadeVO> listarModoPublicidade();

	public static class Util {
		public static ModoPublicidadeRPCAsync getInstance() {
			return GWT.create(ModoPublicidadeRPC.class);
		}
	}

}
