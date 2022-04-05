package br.com.jcv.aluguerelaxe.client.componente.listbox;

public class TipoAnuncianteListBox extends ComponenteListBox {
	
	public TipoAnuncianteListBox() {
		super();
		this.preencheTipoAnunciante();
	}
	
	public TipoAnuncianteListBox(boolean b) {
		super(b);
		this.preencheTipoAnunciante();
	}

	
	private void preencheTipoAnunciante() {
		this.addItem("Propriet\u00e1rio","P");                 
		this.addItem("Imobili\u00e1ria","I");                 
		this.addItem("Patrocinador","C");              
	}

}
