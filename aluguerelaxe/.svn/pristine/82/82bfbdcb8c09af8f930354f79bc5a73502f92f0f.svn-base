package br.com.jcv.aluguerelaxe.client.imovel.thread;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class ThreadImovel implements EntryPoint {
	
	private static final String GWT_DIV_THREAD_IMOVEL = "gwt-imovel-thread";

	public void onModuleLoad() {
		//-----------------------------------------------------------------
		// Obtem hash da contato principal enviado como parametro na URL
		//-----------------------------------------------------------------
		String hash = com.google.gwt.user.client.Window.Location.getParameter("hash");
		String omc = com.google.gwt.user.client.Window.Location.getParameter("omc");

		//-----------------------------------------------------------------
		// Adiciona a página e carrega as threads
		//-----------------------------------------------------------------
		ThreadImovelEditPanel tipe = new ThreadImovelEditPanel(new ToolbarImovelThread());
		
        RootPanel.get(GWT_DIV_THREAD_IMOVEL).add(tipe);
        tipe.update(hash,omc);

	}

}
