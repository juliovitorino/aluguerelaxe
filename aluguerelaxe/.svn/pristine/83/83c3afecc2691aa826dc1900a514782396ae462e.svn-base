
package br.com.jcv.aluguerelaxe.client.componente.editpanel;

import br.com.jcv.aluguerelaxe.client.componente.toolbar.AbstractToolbar;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * Abstração da classe {@link AbstractEditPanel} para criação da composite
 * dentro da grid sem a CheckBox
 *
 * @author Julio Vitorino
 *
 * @param <T> Toolbar a ser usada pelas classes concretas
 * @param <C> Composite a ser incorporado
 * @param <V> VO a ser usado pelas classes concretas
 */
@SuppressWarnings("unchecked")
public abstract class AbstractFormEditPanel<T extends AbstractToolbar,C extends Composite, V> extends AbstractEditPanel<T,C,V> {
	
	private static final int COLUNA_COMPOSITE = 0;
	public AbstractFormEditPanel(T toolbar) {
		super(toolbar);
	}
	
	@Override
	protected void adicionaCompositeGrid(C objetoComposite) {
		grid.resize(++linhasGrid, 1);
		grid.setWidget(linhasGrid - 1, COLUNA_COMPOSITE, objetoComposite);
	}
	
	protected void esconderPainel(C painel) {
		painel.setVisible(false);
	}

	protected void mostrarPainel(C painel) {
		painel.setVisible(true);
	}

	


}

