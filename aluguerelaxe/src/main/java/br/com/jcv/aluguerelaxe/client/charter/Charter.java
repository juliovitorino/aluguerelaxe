package br.com.jcv.aluguerelaxe.client.charter;

import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.uf.UFRPCAsync;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

public class Charter implements EntryPoint {
	public static final String GWT_CHARTER_BRASIL = "gwt-charter-brasil";

	private CharterImovelUFFormComposite fc;
	public void onModuleLoad() {
		fc = new CharterImovelUFFormComposite();
		RootPanel.get(GWT_CHARTER_BRASIL).add(fc);
		update();

	}
	private void update() {
		UFRPCAsync rpc = ServicosRPC.getUFRPC();
		AsyncCallback<CharterVO> callback = new AsyncCallback<CharterVO>() {

			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			public void onSuccess(CharterVO result) {
				Charter.this.fc.update(result);
			}
		};
		rpc.charterSumarizadoImoveisUF(callback);
		
	}

}
