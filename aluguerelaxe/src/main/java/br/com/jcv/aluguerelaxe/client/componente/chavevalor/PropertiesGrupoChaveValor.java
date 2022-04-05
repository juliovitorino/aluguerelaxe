package br.com.jcv.aluguerelaxe.client.componente.chavevalor;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class PropertiesGrupoChaveValor extends Composite {

	
	private List<GrupoChaveValor> lst;
	private Grid grid;

	public PropertiesGrupoChaveValor() {
		initWidget(render());
	}
	
	public void init() {
		if (this.lst != null){
			for(GrupoChaveValor gcv : this.lst){
				renderGrupoChaveValor(gcv);
			}
		}
	}
	
	private void renderGrupoChaveValor(GrupoChaveValor gcv){
		// Cria uma nova linha na grid para colocar o grupo
		grid.resizeRows(grid.getRowCount() + 1);
		
		Expansor expansor = new Expansor();
		grid.setWidget(grid.getRowCount() - 1, 0, expansor);
		grid.setWidget(grid.getRowCount() - 1, 1, new Label(gcv.getGrupo()));
		
		// realiza uma iteracao em todos elementos ChaveValor
		if (gcv.getList() != null) {
			Grid gridcv = new Grid(gcv.getList().size(),2);
			int i = 0;
			for(ChaveValor cv : gcv.getList()){
				gridcv.setWidget(i,0,new Label(cv.getChave()));
				gridcv.setWidget(i,1,cv.getWidgetUi());
				i++;
			}
			// Insere a grid dentro da celula
			grid.resizeRows(grid.getRowCount() + 1);
			grid.setWidget(grid.getRowCount() -1, 1, gridcv);
			
			// associa o expansor a grid interna
			expansor.setWidgetExpansor(gridcv);
		}
	}
	
	private Widget render() {
		VerticalPanel vp = new VerticalPanel();
		grid = new Grid(1,2);
		vp.add(grid);
		return vp;
	}
	
	public void add(GrupoChaveValor gcv) {
		if (this.lst == null){
			this.lst = new ArrayList<GrupoChaveValor>();
		}
		
		this.lst.add(gcv);
	}
	
}