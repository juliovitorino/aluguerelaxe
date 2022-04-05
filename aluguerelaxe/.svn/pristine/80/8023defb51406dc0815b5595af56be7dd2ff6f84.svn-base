package br.com.jcv.aluguerelaxe.client.imovel.ficha;

import br.com.jcv.aluguerelaxe.client.componente.toolbar.NenhumaToolbar;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class ThreadComentarioImovel implements EntryPoint {
	
	private static final String GWT_DIV_THREAD_IMOVEL = "gwt-imovel-thread-comentario";

	public void onModuleLoad() {
		//-----------------------------------------------------------------
		// Obtem hash da contato principal enviado como parametro na URL
		//-----------------------------------------------------------------
		long idImovel = Integer.valueOf(com.google.gwt.user.client.Window.Location.getParameter("id"));

		//-----------------------------------------------------------------
		// Adiciona a página e carrega as threads
		//-----------------------------------------------------------------
		ThreadComentarioImovelEditPanel tipe = new ThreadComentarioImovelEditPanel(new NenhumaToolbar());
		
        RootPanel.get(GWT_DIV_THREAD_IMOVEL).add(tipe);
        tipe.update(idImovel);

	}

}
