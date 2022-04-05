package br.com.jcv.aluguerelaxe.client.destino;

import br.com.jcv.aluguerelaxe.client.componente.toolbar.NenhumaToolbar;
import br.com.jcv.aluguerelaxe.client.componente.windowpanel.WindowPanel;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * <h1>DestinoFerias</h1>
 * <p>Classe responsável por gerenciar o módulo DestinoFerias. O componente de combo
 * responsável por buscar todas as UFs do site para que o usuário possa selecionar o
 * destino em que deseja procurar por um imóvel de temporada.
 * </p>
 * @author julio
 *
 */
public class DestinoFerias implements EntryPoint {

	/**
	 * <h2>GWT_COMBO_DESTINO</h2>
	 * <p>Tag que identifica o componente de combo dentro do HTML.
	 * </p>
	 */
	private static final String GWT_COMBO_DESTINO = "gwt-combo-destino";

	public void onModuleLoad() {
		WindowPanel wp = new WindowPanel("Busca R\u00e1pida","blue01",false,false,false);
		wp.setComponenteCenter(new DestinoEditPanel(new NenhumaToolbar()));
		RootPanel.get(GWT_COMBO_DESTINO).add(wp);
	}


}
