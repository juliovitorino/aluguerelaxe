package br.com.jcv.aluguerelaxe.client.componente.advanceddatagrid;

import br.com.jcv.aluguerelaxe.client.componente.datagrid.RegDataGridVO;

import com.google.gwt.cell.client.TextInputCell;
import com.google.gwt.user.cellview.client.Column;

public class TextInputDefinicaoCampoDataGrid extends AbstractDefinicaoCampoDataGrid<TextInputCell> {
	
	public TextInputDefinicaoCampoDataGrid(int pos, String header, String campo) {
		super(pos, header, campo);
	}
	
	@Override
	public TextInputCell getInstanceAbstractCell(){
		return new TextInputCell();
	}
	
	@Override
	public Column<RegDataGridVO, String> getColumn(TextInputCell widget) {
		Column<RegDataGridVO, String> col = new Column<RegDataGridVO, String>(widget) {
			public String getValue(RegDataGridVO object){
				return object.campo[TextInputDefinicaoCampoDataGrid.this.getPos()];
			}
		};
		return col;
	}

}