package br.com.jcv.aluguerelaxe.client.componente.progressbar;

import br.com.jcv.aluguerelaxe.client.vo.UploadInfoVO;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@Deprecated
@RemoteServiceRelativePath("progressbarrpc")
public interface ProgressBarRPC extends RemoteService {
	
	UploadInfoVO getProgresso(String idSessao);
	VOPadrao terminateProgresso(String idSessao);
	
	public static class Util {
		public static ProgressBarRPCAsync getInstance() {
			return GWT.create(ProgressBarRPC.class);
		}
	}
}
