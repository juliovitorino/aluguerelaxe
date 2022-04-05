package br.com.jcv.aluguerelaxe.client.enquete;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.RootPanel;

public class EnqueteModoPublicidade implements EntryPoint {
	
	public static final String COOKIE_MODO_PUBLICIDADE = "enqmp";

	public void onModuleLoad() {
		
		// Verifica o cookie
		if (Cookies.getCookie(COOKIE_MODO_PUBLICIDADE) == null){
			EnqueteModoPublicidadeDialogModal empdm = new EnqueteModoPublicidadeDialogModal();
            
            // Realiza a enquete
            RootPanel.get("gwt-EnqueteModoPublicidade").add(empdm);
            empdm.show();
		}
	 

	}

}
