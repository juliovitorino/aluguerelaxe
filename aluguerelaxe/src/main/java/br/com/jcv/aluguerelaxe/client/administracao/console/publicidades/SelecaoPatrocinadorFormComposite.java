package br.com.jcv.aluguerelaxe.client.administracao.console.publicidades;

import br.com.jcv.aluguerelaxe.client.componente.listbox.PlanosPatrocinadorListBox;

public class SelecaoPatrocinadorFormComposite extends AbstractSelecaoPlanoFormComposite  {

	public void init() {
		super.init();
		this.aplb = new PlanosPatrocinadorListBox();
		
	}
}
