package br.com.jcv.aluguerelaxe.client.componente.labs;


import java.util.List;

import br.com.jcv.aluguerelaxe.client.componente.datagrid.AbstractDataGridParameter;
import br.com.jcv.aluguerelaxe.client.componente.datagrid.CondicaoVO;
import br.com.jcv.aluguerelaxe.client.componente.datagrid.DataGridParameterListener;
import br.com.jcv.aluguerelaxe.client.parameter.ClienteParameter;
import br.com.jcv.aluguerelaxe.client.parameter.StatusFormComposite;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class ParameterLab implements EntryPoint, DataGridParameterListener {

	public void onModuleLoad() {
		AbstractDataGridParameter<StatusFormComposite> qafc = new ClienteParameter(new StatusFormComposite());
		RootPanel.get("gwt-form-imovel").add(qafc);
		qafc.addListener(this);
		//qafc.show();
	}

	public void onProcurarClick(List<CondicaoVO> lst) {
		// TODO Auto-generated method stub
		
	}

}
