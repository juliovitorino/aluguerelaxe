package br.com.jcv.aluguerelaxe.client.painel;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.componente.anuncio.AbstractAnuncio;
import br.com.jcv.aluguerelaxe.client.componente.anuncio.AnuncioListener;
import br.com.jcv.aluguerelaxe.client.componente.anuncio.AnuncioUltimoCadastrado;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootPanel;

public class PainelUltimosCadastrados implements EntryPoint, AsyncCallback<List<ImovelFichaCompletaVO>>, AnuncioListener {

	private FlowPanel hp = new FlowPanel();
	
	public void onModuleLoad() {
		hp.setWidth("560px");
		RootPanel.get("gwt-ultimos-cadastrados").add(hp);
		update();

	}

	private void update() {
		PainelUltimosCadastradosRPCAsync rpc = ServicosRPC.getPainelUltimosImoveisCadastradosRPC();
		AsyncCallback<List<ImovelFichaCompletaVO>> callback = this;
		rpc.listarUltimosImoveisCadastrados(callback);
		
	}

	public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub
		
	}

	public void onSuccess(List<ImovelFichaCompletaVO> result) {
		if (result != null) {
			for (ImovelFichaCompletaVO ifcvo : result) {
				if ((ifcvo.imagensImovelTB != null) && (ifcvo.imagensImovelTB.size() > 0)) {
					AnuncioUltimoCadastrado auc = new AnuncioUltimoCadastrado(ifcvo);
					auc.addAnuncioListener(this);
					hp.add(auc);
				}
			}
		}

	}

	public void onAnuncioClick(AbstractAnuncio anuncio) {
		redirect("/arweb/JXControllerSmartInterface?cmd=dtgFichaImovel&id="+anuncio.getImovelFichaCompletaVO().imovel.id + "&o=LD");
	}

	public static native void redirect(String url)/*-{
	      $wnd.location = url;
	}-*/;


}
