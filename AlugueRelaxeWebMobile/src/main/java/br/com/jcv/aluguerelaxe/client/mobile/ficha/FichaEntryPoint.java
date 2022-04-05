package br.com.jcv.aluguerelaxe.client.mobile.ficha;

import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.mobile.vo.EnvelopeFichaVO;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

public class FichaEntryPoint implements EntryPoint {
	
	private HTML html;

	public void onModuleLoad() {
		
		// Obtem o id da ficha do imovel
		long id = Integer.valueOf(com.google.gwt.user.client.Window.Location.getParameter("id"));
		
		html = new HTML();
		RootPanel.get("gwt-ficha").add(html);
		this.update(id);

	}
	
	private void update(long id){
		FichaImovelRPCAsync rpc = ServicosRPC.getFichaImovelRPC();
		AsyncCallback<EnvelopeFichaVO> callback = new AsyncCallback<EnvelopeFichaVO>() {

			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			public void onSuccess(EnvelopeFichaVO result) {
				if (result != null){
					FichaEntryPoint.this.apresentarFichaCompletaImovel(result);
				}
			}
		};
		
		rpc.apresentarFichaCompletaImovel(id, "LI", callback);
		
	}

	private void apresentarFichaCompletaImovel(EnvelopeFichaVO efvo) {
		this.html.setHTML(efvo.hmtl);
	}

}
