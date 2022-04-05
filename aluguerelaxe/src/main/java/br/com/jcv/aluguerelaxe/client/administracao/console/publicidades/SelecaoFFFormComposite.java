package br.com.jcv.aluguerelaxe.client.administracao.console.publicidades;

import br.com.jcv.aluguerelaxe.client.componente.listbox.PlanosFFListBox;

public class SelecaoFFFormComposite extends AbstractSelecaoPlanoFormComposite  {

	public void init() {
		super.init();
		this.aplb = new PlanosFFListBox();
		
	}
}
