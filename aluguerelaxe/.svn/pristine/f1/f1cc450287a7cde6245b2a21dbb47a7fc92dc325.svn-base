package br.com.jcv.aluguerelaxe.client.logodinamico;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("logodinamicorpc")
public interface LogoDinamicoRPC extends RemoteService {
	LogoDinamicoVO getLogoDinamico() throws AlugueRelaxeFrontException;

	public static class Util {

		public static LogoDinamicoRPCAsync getInstance() {

			return GWT.create(LogoDinamicoRPC.class);
		}
	}

}
