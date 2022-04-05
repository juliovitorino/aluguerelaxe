package br.com.jcv.aluguerelaxe.client.administracao.console.publicidades;

import br.com.jcv.aluguerelaxe.client.componente.listbox.PlanosSMSListBox;

public class SelecaoSMSFormComposite extends AbstractSelecaoPlanoFormComposite  {

	public void init() {
		super.init();
		this.aplb = new PlanosSMSListBox();
		
	}
}
