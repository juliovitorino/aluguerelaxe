package br.com.jcv.aluguerelaxe.client.mobile.uf;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.mobile.vo.CidadeVO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ufrpc")
public interface UFRPC extends RemoteService {

	
	List<CidadeVO> listarCidadesDaUF(String  uf);
	List<CidadeVO> listarCidadesDaUFComImoveis(String  uf);
	
	public static class Util {
		public static UFRPCAsync getInstance() {
			return GWT.create(UFRPC.class);
		}
	}

}

