package br.com.jcv.aluguerelaxe.client.componente.listbox;


/**
 * <h1>ListaNumeradaListBox</h1>
 * <p>Retorna uma ListBox numerada.</p>
 * @author julio
 *
 */
public class ListaNumeradaListBox extends ComponenteListBox {

	public ListaNumeradaListBox(int inicioNumeracao, int fimNumeracao, boolean b) {
		this(inicioNumeracao,fimNumeracao,b, false);
	}
	/**
	 * <p>Retorna uma ListBox numerada.</p>
	 * @param inicioNumeracao
	 * @param fimNumeracao
	 */
	public ListaNumeradaListBox(int inicioNumeracao, int fimNumeracao, boolean b, boolean filtro){ 
		super(b);
		this.setWidth("100px");
		if (filtro){
			this.addItem(String.valueOf("Indiferente"));
		}
		for (int i = inicioNumeracao; i < (fimNumeracao + 1); i++){
			this.addItem(String.valueOf(i));
		}

	}

}
