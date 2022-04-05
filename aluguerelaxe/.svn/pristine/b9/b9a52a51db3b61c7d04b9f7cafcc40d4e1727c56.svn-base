package br.com.jcv.aluguerelaxe.client.galeria;

import br.com.jcv.aluguerelaxe.client.componente.galeria.GaleriaImovel;
import br.com.jcv.aluguerelaxe.client.componente.galeria.PortaRetrato;
import br.com.jcv.aluguerelaxe.client.componente.galeria.PortaRetratoListener;
import br.com.jcv.aluguerelaxe.client.componente.windowpanel.WindowPanel;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class GaleriaPatrocinador implements EntryPoint, PortaRetratoListener {

	private static final String GWT_GALERIA_PATROCINADOR = "gwt-galeria-patrocinador";

	public void onModuleLoad() {
		GaleriaImovel gPatrocinador = new GaleriaImovel("P", this, 5, true);
		WindowPanel wpGaleriaPatrocinador = new WindowPanel("Patrocinadores","orkut");
		wpGaleriaPatrocinador.setWidth("930px");
		
		wpGaleriaPatrocinador.setComponenteCenter(gPatrocinador);

		RootPanel.get(GWT_GALERIA_PATROCINADOR).add(wpGaleriaPatrocinador);
	}

	public void onPortaRetratoClick(PortaRetrato portaRetrato) {
		//-----------------------------------------------------
		// Invoca a ficha com o novo imovel selecionado
		//-----------------------------------------------------
		ImovelFichaCompletaVO vo = portaRetrato.getFichaImovelPortaRetrato();
		redirect("/arweb/JXControllerSmartInterface?cmd=dtgFichaImovel&id=" + vo.imovel.id + "&o=IP");
	}
	
	public static native void redirect(String url)/*-{
		$wnd.location = url;
	}-*/;



}
