package br.com.jcv.aluguerelaxe.client.componente.listbox;

import com.google.gwt.user.client.ui.ListBox;

/**
 * <h1>ComponenteListBox</h1>
 * <p>Componente base para customização de uma ListBox.</p>
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
	 * parametro. Essa string deve ser igual ao conteúdo retornado pelo 
	 * método <code>getValue(int index)</code>
	 * @param item
	 * @return int - a posição do índice dentro da lista. Retorna -1 se a string 
	 * não for encontrada.
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
	 * parametro. Essa string deve ser igual ao conteúdo retornado pelo 
	 * método <code>getItemText(int index)</code>
	 * @param item
	 * @return int - a posição do índice dentro da lista. Retorna -1 se a string 
	 * não for encontrada.
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
