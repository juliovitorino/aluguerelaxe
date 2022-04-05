package br.com.jcv.aluguerelaxe.client.componente.listbox;

import com.google.gwt.user.client.ui.ListBox;

/**
 * <h1>ComponenteListBox</h1>
 * <p>Componente base para customiza��o de uma ListBox.</p>
 * @author julio
 *
 */
public class ComponenteListBox extends ListBox {
	
	public ComponenteListBox() {
		super();
	}

	public ComponenteListBox(boolean b) {
		super(b);
	}

	/**
	 * Retorna o index de uma lista com base em uma string passada como
	 * parametro. Essa string deve ser igual ao conte�do retornado pelo 
	 * m�todo <code>getValue(int index)</code>
	 * @param item
	 * @return int - a posi��o do �ndice dentro da lista. Retorna -1 se a string 
	 * n�o for encontrada.
	 */
	public int getSelectedItemValue(String item) {
		int pos = -1;
		for (int i = 0; i < this.getItemCount(); i++) {
			String txt = this.getValue(i);
			if (txt.equals(item)) {
				pos = i;
				break;
			}
		}
		return pos;
	}

	/**
	 * Retorna o index de uma lista com base em uma string passada como
	 * parametro. Essa string deve ser igual ao conte�do retornado pelo 
	 * m�todo <code>getItemText(int index)</code>
	 * @param item
	 * @return int - a posi��o do �ndice dentro da lista. Retorna -1 se a string 
	 * n�o for encontrada.
	 */
	public int getSelectedItemText(String item) {
		int pos = -1;
		for (int i = 0; i < this.getItemCount(); i++) {
			String txt = this.getItemText(i);
			if (txt.equals(item)) {
				pos = i;
				break;
			}
		}
		return pos;
	}

}
