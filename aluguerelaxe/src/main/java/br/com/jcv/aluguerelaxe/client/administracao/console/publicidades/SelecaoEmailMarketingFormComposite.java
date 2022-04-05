package br.com.jcv.aluguerelaxe.client.administracao.console.publicidades;

import br.com.jcv.aluguerelaxe.client.componente.listbox.PlanosEmailListBox;

public class SelecaoEmailMarketingFormComposite extends AbstractSelecaoPlanoFormComposite  {

	public void init() {
		super.init();
		this.aplb = new PlanosEmailListBox();
		
	}
}
