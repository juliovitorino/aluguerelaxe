package br.com.jcv.aluguerelaxe.client.formpesquisa;

import br.com.jcv.aluguerelaxe.client.componente.datagrid.AbstractFormPesquisa;
import br.com.jcv.aluguerelaxe.client.datagrid.PublicidadeAVencerDataGrid;
import br.com.jcv.aluguerelaxe.client.parameter.PlanosPagosAVencerFormComposite;
import br.com.jcv.aluguerelaxe.client.parameter.PublicidadeAVencerParameter;

public class PublicidadeAVencerFormPesquisa extends AbstractFormPesquisa<PublicidadeAVencerParameter, PublicidadeAVencerDataGrid> {

	public PublicidadeAVencerFormPesquisa() {
		super(new PublicidadeAVencerParameter(new PlanosPagosAVencerFormComposite()), new PublicidadeAVencerDataGrid());
	}

	@Override
	public void setupGrid(PublicidadeAVencerDataGrid grid) {
		grid.setMaximoLinhas(6);
	}

	@Override
	public String getFormPesquisaWidth() {
		return "860px";
	}

	@Override
	public String getFormPesquisaHeight() {
		return "360px";
	}

}
