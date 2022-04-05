package br.com.jcv.aluguerelaxe.client.componente.galeria;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;

public class GaleriaDemo implements EntryPoint, PortaRetratoListener {

	public void onModuleLoad() {
		// Carregar galeria do cliente 2
		//RootPanel.get("gwt-galeria").add(new GaleriaImovel(2, this,5));

		RootPanel.get("gwt-galeria").add(new GaleriaImovel("P", this,5,false));
	}

	public void onPortaRetratoClick(PortaRetrato portaRetrato) {
		Window.alert("clicado");
	}

}
