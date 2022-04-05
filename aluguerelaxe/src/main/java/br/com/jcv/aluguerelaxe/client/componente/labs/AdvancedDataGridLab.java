package br.com.jcv.aluguerelaxe.client.componente.labs;

import br.com.jcv.aluguerelaxe.client.componente.advanceddatagrid.AbstractAdvancedDataGrid;
import br.com.jcv.aluguerelaxe.client.datagrid.ClienteAdvancedDataGrid;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;


public class AdvancedDataGridLab implements EntryPoint {
	public void onModuleLoad() {
		//AbstractDataGrid cdg = new ClienteDataGrid();
		AbstractAdvancedDataGrid cdg = new ClienteAdvancedDataGrid();
		cdg.setMaximoLinhas(22);
		RootPanel.get("gwt-datagrid").add(cdg);
		cdg.flush();
		//cdg.update(1);
		
	}
}