package br.com.jcv.aluguerelaxe.client.faleconosco;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("faleconoscorpc")
public interface FaleConoscoRPC extends RemoteService {
	
	VOPadrao execute(FaleConoscoVO vo) throws AlugueRelaxeFrontException;
	
	public static class Util {

		public static FaleConoscoRPCAsync getInstance() {
			return GWT.create(FaleConoscoRPC.class);
		}
	}
	


}

