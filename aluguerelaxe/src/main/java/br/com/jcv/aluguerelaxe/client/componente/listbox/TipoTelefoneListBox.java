package br.com.jcv.aluguerelaxe.client.componente.listbox;


public class TipoTelefoneListBox extends ComponenteListBox {
	
	public TipoTelefoneListBox() {
		super();
		
		this.addItem("Residencial", "R");
		this.addItem("Celular", "C");
		this.addItem("Fax", "F");
	}

}
