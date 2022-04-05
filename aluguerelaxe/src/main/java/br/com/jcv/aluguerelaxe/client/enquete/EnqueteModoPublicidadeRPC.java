package br.com.jcv.aluguerelaxe.client.enquete;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("enquetemprpc")
public interface EnqueteModoPublicidadeRPC extends RemoteService {
	
	VOPadrao incrementarModoPublicidadeVisita(long idModoPublicidade) throws AlugueRelaxeFrontException; 

	public static class Util {
		public static EnqueteModoPublicidadeRPCAsync getInstance() {
			return GWT.create(EnqueteModoPublicidadeRPC.class);
		}
	}
	

}
