package br.com.jcv.aluguerelaxe.client.componente.labs;

import br.com.jcv.aluguerelaxe.client.componente.datagrid.AbstractDataGrid;
import br.com.jcv.aluguerelaxe.client.datagrid.ClienteDataGrid;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;


public class DataGridLab implements EntryPoint {
	public void onModuleLoad() {
		//AbstractDataGrid cdg = new ClienteDataGrid();
		AbstractDataGrid cdg = new ClienteDataGrid();
		cdg.setMaximoLinhas(22);
		RootPanel.get("gwt-datagrid").add(cdg);
		cdg.update(1);
		
	}
}