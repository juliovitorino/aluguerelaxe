package br.com.jcv.aluguerelaxe.client.formpesquisa;

import br.com.jcv.aluguerelaxe.client.componente.datagrid.AbstractFormPesquisa;
import br.com.jcv.aluguerelaxe.client.datagrid.PlanosPagosAVencerDataGrid;
import br.com.jcv.aluguerelaxe.client.parameter.PlanosPagosAVencerFormComposite;
import br.com.jcv.aluguerelaxe.client.parameter.PlanosPagosAVencerParameter;

public class PlanosPagosAVencerFormPesquisa extends AbstractFormPesquisa<PlanosPagosAVencerParameter, PlanosPagosAVencerDataGrid> {

	public PlanosPagosAVencerFormPesquisa() {
		super(new PlanosPagosAVencerParameter(new PlanosPagosAVencerFormComposite()), new PlanosPagosAVencerDataGrid());
	}

	@Override
	public void setupGrid(PlanosPagosAVencerDataGrid grid) {
		grid.setMaximoLinhas(12);
	}

	@Override
	public String getFormPesquisaWidth() {
		return "860px";
	}

	@Override
	public String getFormPesquisaHeight() {
		return "560px";
	}

}
