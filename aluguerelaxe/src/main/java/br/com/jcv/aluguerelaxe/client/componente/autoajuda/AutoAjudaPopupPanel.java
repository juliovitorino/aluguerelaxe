package br.com.jcv.aluguerelaxe.client.componente.autoajuda;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PopupPanel;

/**
 * <h2>AutoAjudaPopupPanel</h2>
 * <p>Apresenta um popup contendo uma ajuda ao usuario</p>
 * <h2>CSS Style Rules</h2>
 * <ul>
 *		<li>.gwt-AutoAjudaPopupPanel {estilo primario}</li>
 * </ul>
 * @author Julio Vitorino
 */
public class AutoAjudaPopupPanel extends PopupPanel {
	
	public AutoAjudaPopupPanel(String stringAutoAjuda) {
		// Ativa auto-hide
		super(true);
		this.setStylePrimaryName("gwt-AutoAjudaPopupPanel");
		String div = "<div>";
		String divend = "</div>";
		String codigoHtml = div + stringAutoAjuda + divend;
		this.setWidget(new HTML(codigoHtml));
	}
}
