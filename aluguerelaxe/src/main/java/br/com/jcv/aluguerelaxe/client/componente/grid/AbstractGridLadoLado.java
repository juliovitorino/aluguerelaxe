package br.com.jcv.aluguerelaxe.client.componente.grid;

import java.util.List;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Abstração de uma classe para criar widget na forma lado a lado (tile)
 * 
 * @author julio
 *
 * @param <W> Generics do tipo Widget
 */
public abstract class AbstractGridLadoLado<W extends Widget> extends Composite {

	private int colunas = 1;
	private Grid grid = new Grid();
	List<W> lstWidgets = null;
	
	public AbstractGridLadoLado(int colunas, List<W> widgets) {
		this.colunas = colunas;
		this.lstWidgets = widgets;
		initWidget(montaAbstractGridLadoLado());
	}

	public void update(int colunas, List<W> widgets) {
		this.colunas = colunas;
		this.lstWidgets = widgets;
		this.montaGrid();
	}

	private Widget montaAbstractGridLadoLado() {
		SimplePanel sp = new SimplePanel();
		sp.add(this.grid);
		montaGrid();
		return sp;
	}
	
	public void clear() {
		this.lstWidgets.clear();
		this.grid.clear();
	}
	
	public List<W> getListWidgets() {
		return lstWidgets;
	}

	private void montaGrid() {
		if (this.lstWidgets != null) {
			int linhasGrid = this.lstWidgets.size() / this.colunas + 1;
			this.grid.resize(linhasGrid, this.colunas);
			
			int l = 0;
			int c = 0;
			
			for (W vo : lstWidgets) {
				this.grid.setWidget(l, c, vo);
				if (c == this.colunas - 1) {
					c = 0;
					l++;
				} else {
					c++;
				}
			}
		}
	}
	
	
}
