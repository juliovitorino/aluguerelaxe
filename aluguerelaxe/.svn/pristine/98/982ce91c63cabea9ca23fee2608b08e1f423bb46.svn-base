package br.com.jcv.aluguerelaxe.client.superpanel;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.superpanel.superbanner.SuperBannerVO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("superpanelrpc")
public interface SuperPanelRPC extends RemoteService {
	
	//String getTemplatePromocaoPadraoSuperPanel() throws AlugueRelaxeFrontException;
	//List<ImovelPublicidadeVO> getImovelSuperBanner() throws AlugueRelaxeFrontException;
	SuperBannerVO getSuperBannerPP() throws AlugueRelaxeFrontException;
	SuperBannerVO getSuperBannerDD() throws AlugueRelaxeFrontException;
	

	public static class Util {
		public static SuperPanelRPCAsync getInstance() {
			return GWT.create(SuperPanelRPC.class);
		}
	}
	
}

