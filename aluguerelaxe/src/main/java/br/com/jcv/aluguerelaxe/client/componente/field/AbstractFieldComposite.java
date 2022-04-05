package br.com.jcv.aluguerelaxe.client.componente.field;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * <h2>AbstractFieldComposite</h2>
 * <p>Classe responsavel por montar um composite composto dentro de um VerticalPanel
 * na referida sequencia:<br>
 * <li>Label</li>
 * <li>Widget</li>
 * </p>
 * 
 * @author Julio
 *
 * @param <W>
 */
public abstract class AbstractFieldComposite<W extends Widget> extends Composite {

	Label tituloLabel;
	W componenteUI;
	
	/**
	 * Metodo para aplicar os estilos na classe concreta
	 * caso tenha necessidade de aplicar um estilo proprio realize uma sobrecarga
	 * do metodo.
	 */
	public void configurarComponenteUI() {} 
	
	/**
	 * Monta o componente com base no VerticalPanel
	 * @param tituloLabel
	 * @param componenteUI
	 */
	public AbstractFieldComposite(String tituloLabel, W componenteUI) {
		this.tituloLabel = new Label(tituloLabel);
		this.componenteUI = componenteUI;
		initWidget(render());
	}
	
	private Widget render() {
		VerticalPanel vp = new VerticalPanel();
		
		vp.add(tituloLabel);
		vp.add(componenteUI);
		
		return vp;
	}
	
	/**
	 * Retorna o componente encapsulado na classe
	 * @return Widget
	 */
	public W getFieldComposite(){
		return this.componenteUI;
	}
}
