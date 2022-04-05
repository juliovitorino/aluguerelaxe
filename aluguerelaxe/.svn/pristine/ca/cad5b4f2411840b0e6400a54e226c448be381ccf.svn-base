package br.com.jcv.aluguerelaxe.client.componente.advanceddatagrid;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.user.cellview.client.Column;

public abstract class AbstractDefinicaoCampoDataGrid<W extends AbstractCell<?>> {
	
	private W widget;
	private int pos;
	private String header;
	private String campobd;
	private Column coldef;
	
	public abstract W getInstanceAbstractCell();
	public abstract Column<?,?> getColumn(W widget);
	
	public AbstractDefinicaoCampoDataGrid(int pos, String header, String campobd) {	
		this.widget = this.getInstanceAbstractCell();
		this.coldef = this.getColumn(this.widget);
		this.header = header;
		this.campobd = campobd;
		this.pos = pos;
	}
	
	public String getHeader() {
		return this.header;
	}
	
	public Column getColDef(){
		return this.coldef;
	}
	
	public int getPos() {
		return this.pos;
	}
	
	public String getCampobd(){
		return this.campobd;
	}
	
}
