package br.com.jcv.aluguerelaxe.client.componente.advanceddatagrid;

import java.util.Date;

import br.com.jcv.aluguerelaxe.client.componente.datagrid.RegDataGridVO;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.user.cellview.client.Column;

public class DateDefinicaoCampoDataGrid extends AbstractDefinicaoCampoDataGrid<DateCell> {
	
	public DateDefinicaoCampoDataGrid(int pos, String header, String campo) {
		super(pos, header, campo);
	}
	
	@Override
	public DateCell getInstanceAbstractCell(){
		return new DateCell();
	}
	
	@Override
	public Column<RegDataGridVO, Date> getColumn(DateCell widget) {
		Column<RegDataGridVO,Date> col = new Column<RegDataGridVO, Date>(widget) {
			public Date getValue(RegDataGridVO object){
				// forca um retorno para teste
				return new Date();
			}
		};
		return col;
	}

}