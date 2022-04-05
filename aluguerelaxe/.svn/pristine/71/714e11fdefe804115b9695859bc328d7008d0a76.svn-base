package br.com.jcv.aluguerelaxe.client.componente.labs;


import java.util.List;

import br.com.jcv.aluguerelaxe.client.componente.datagrid.AbstractFormPesquisa;
import br.com.jcv.aluguerelaxe.client.componente.datagrid.AbstractFormPesquisaListener;
import br.com.jcv.aluguerelaxe.client.componente.datagrid.RegDataGridVO;
import br.com.jcv.aluguerelaxe.client.datagrid.ClienteDataGrid;
import br.com.jcv.aluguerelaxe.client.formpesquisa.ClienteFormPesquisa;
import br.com.jcv.aluguerelaxe.client.parameter.ClienteParameter;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

public class FormPesquisaLab implements EntryPoint, AbstractFormPesquisaListener {

	public void onModuleLoad() {
		AbstractFormPesquisa<ClienteParameter, ClienteDataGrid> qafc = new ClienteFormPesquisa();
		RootPanel.get("gwt-form-imovel").add(qafc);
		qafc.addListener(this);
	}

	public void onSelecionarClick(RegDataGridVO registro) {
		GWT.log(registro.campo[1]);
		
	}

	public void onSelecionarClick(List<RegDataGridVO> lstRegistros) {
		// TODO Auto-generated method stub
		
	}

	public void onCancelarClick() {
		// TODO Auto-generated method stub
		
	}

}
