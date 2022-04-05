package br.com.jcv.aluguerelaxe.client.superpanel.superbanner;

import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.componente.superpanel.AbstractSuperPanel;
import br.com.jcv.aluguerelaxe.client.enquete.EnqueteModoPublicidade;
import br.com.jcv.aluguerelaxe.client.superpanel.SuperPanelRPCAsync;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

public class SuperBanner implements EntryPoint,
									AsyncCallback<SuperBannerVO>{
	
	public static final String GWT_SUPER_BANNER = "gwt-super-banner";

	private AbstractSuperPanel<SuperBannerVO> asp = null;

	public void onModuleLoad() {
		
		// Verifica o cookie
		if (Cookies.getCookie(EnqueteModoPublicidade.COOKIE_MODO_PUBLICIDADE) != null){
			update();
		}
	}
	
	private void update(){
		SuperPanelRPCAsync rpc = ServicosRPC.getSuperPanelRPC();
		AsyncCallback<SuperBannerVO> callback = this;
		rpc.getSuperBannerPP(callback);
	}
	
	public void onFailure(Throwable caught) {
	}

	public void onSuccess(SuperBannerVO result) {
		boolean ok = false;
		if(result != null){
			if(result.lstipvo != null){
				asp = new ImovelSuperBannerSuperPanel();
				ok = true;
			} else {
				if (result.indSuperBanner){
					asp = new PrimeiraPaginaSuperPanel();
					ok = true;
				}
			}
			if (ok) {
				RootPanel.get(GWT_SUPER_BANNER).add(asp);
				asp.update(result);
				asp.show();
				asp.center();
			}
		}
	}

}
