package br.com.jcv.aluguerelaxe.client.logodinamico;

import br.com.jcv.aluguerelaxe.client.ServicosRPC;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class LogoDinamico implements EntryPoint, AsyncCallback<LogoDinamicoVO> {
	
	private static final String GWT_LOGO_DINAMICO = "gwt-logo-dinamico";
	
	private LogoDinamicoFormComposite ldfc;

	public void onModuleLoad() {
		RootPanel.get(GWT_LOGO_DINAMICO).add(init());
		update();

	}

	private Widget init() {
		this.ldfc = new LogoDinamicoFormComposite();
		return this.ldfc;
	}

	private void update() {
		LogoDinamicoRPCAsync rpc = ServicosRPC.getLogoDinamicoRPC();
		AsyncCallback<LogoDinamicoVO> callback = this;
		rpc.getLogoDinamico(callback);
	}

	public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub
		
	}

	public void onSuccess(LogoDinamicoVO result) {
		this.ldfc.update(result);
	}

}
