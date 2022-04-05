
package br.com.jcv.aluguerelaxe.client.componente.toolbar;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Classe abstrata que controla o comportamento da Toolbar
 *
 * @author Julio Vitorino
 *
 */
public abstract class AbstractToolbar<L extends ToolbarListener> extends Composite {
	private static final String STYLE_TAG = "gwt-AbstractToolbar";
	
	protected List<L> listeners = null;
	
	public AbstractToolbar() {
		VerticalPanel vp = new VerticalPanel();
		vp.add(render());
		initWidget(vp);
		this.setStylePrimaryName(STYLE_TAG);
	}
	
	/**
	 * Adiciona os listeners dessa classe que desejam responder aos
	 * eventos da toolbar
	 *
	 * @param listener Classe que implementa {@link ToolbarListener}
	 */
	public void addToolbarListener(L listener) {
		if (this.listeners == null) {
			this.listeners = new ArrayList<L>();
		}
		this.listeners.add(listener);
	}
	
	/**
	 * Renderizador da toolbar. Deve ser implementado na classe concreta
	 *
	 * @return Widget que conterá a tela montada
	 */
	public abstract Widget render();
	
}

