
package br.com.jcv.aluguerelaxe.client.componente.editpanel;

import br.com.jcv.aluguerelaxe.client.componente.toolbar.AbstractToolbar;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;

/**
 * Abstração da classe {@link AbstractEditPanel} para criação da composite
 * dentro da grid com uma CheckBox na primeira coluna
 *
 * @author Julio Vitorino
 *
 * @param <T> Toolbar a ser usada pelas classes concretas
 * @param <C> Composite a ser incorporado
 * @param <V> VO a ser usado pelas classes concretas
 */
@SuppressWarnings("unchecked")
public abstract class AbstractGridCheckEditPanel<T extends AbstractToolbar,C extends Composite, V> extends AbstractEditPanel<T,C,V> {
	
	private static final int COLUNA_CHECK_BOX = 0;
	private static final int COLUNA_COMPOSITE = 1;

	public AbstractGridCheckEditPanel(T toolbar) {
		super(toolbar);
	}
	
	@Override
	protected void adicionaCompositeGrid(C objetoComposite) {
		grid.resize(++linhasGrid, 2);
		grid.setWidget(linhasGrid - 1, COLUNA_CHECK_BOX, new CheckBox());
		grid.setWidget(linhasGrid - 1, COLUNA_COMPOSITE, objetoComposite);
	}
	
	protected void removeCompositeChecked() {
		// Verifica se tem Composite dentro da grid
		if ( grid.getRowCount() > 0 ) {
			for (int i = grid.getRowCount() - 1 ; i > 0; i--) {
				CheckBox cb = (CheckBox) grid.getWidget(i, COLUNA_CHECK_BOX);
				if (cb.getValue()) {
					super.removeObjetoCompositeFromPanel((C)grid.getWidget(i, COLUNA_COMPOSITE));
					grid.removeRow(i);
				}
			}
		}
	}
	
}

