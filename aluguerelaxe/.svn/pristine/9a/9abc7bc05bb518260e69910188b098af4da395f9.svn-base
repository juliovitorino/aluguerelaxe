package br.com.jcv.aluguerelaxe.client.imovel.reserva;

import br.com.jcv.aluguerelaxe.client.componente.toolbar.NenhumaToolbar;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class ReservaImovel implements EntryPoint {
	
	private static final String GWT_DIV_RESERVA_IMOVEL = "gwt-reserva-imovel";

	public void onModuleLoad() {
		//-----------------------------------------------------------------
		// Obtem hash da contato principal enviado como parametro na URL
		//-----------------------------------------------------------------
		String hash = com.google.gwt.user.client.Window.Location.getParameter("hash");
		String omc = com.google.gwt.user.client.Window.Location.getParameter("omc");

		//-----------------------------------------------------------------
		// Adiciona a página e carrega o formulario para preenchimento
		//-----------------------------------------------------------------
		ReservaImovelEditPanel tipe = new ReservaImovelEditPanel(new NenhumaToolbar());
		
        RootPanel.get(GWT_DIV_RESERVA_IMOVEL).add(tipe);
        tipe.update(hash,omc);

	}

}
