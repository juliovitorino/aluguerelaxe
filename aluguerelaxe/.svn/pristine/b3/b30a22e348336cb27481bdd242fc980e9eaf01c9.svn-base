package br.com.jcv.aluguerelaxe.client.componente.listbox;

public class TipoPropriedadeListBox extends ComponenteListBox {
	
	public TipoPropriedadeListBox() {
		this(false,false);
	}
	
	public TipoPropriedadeListBox(boolean b, boolean filtro) {
		super(b);
		this.clear();
		if (filtro){
			this.addItem("Indiferente", "I");
		}
		this.preencheTipoAnunciante();
	}

	
	private void preencheTipoAnunciante() {
		this.addItem("Casa","C");                 
		this.addItem("Apartamento","A");    
		this.addItem("Hotel","H");    
		this.addItem("Ch\u00e1cara","X");    
		this.addItem("Flat","F");    
		this.addItem("Fazenda","Z");    
		this.addItem("S\u00edtio","S");    
		this.addItem("Chal\u00e9","L");    
		this.addItem("Pousada","P");    
	}

}
