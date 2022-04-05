package br.com.jcv.aluguerelaxe.client.componente.form;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 *
 * @author elmt
 *
 * @param <V>
 */

/**
 * <h2>AutoAjudaPopupPanel</h2>
 * <p>Classe que controla e/ou obriga a implementação de métodos com funções
 * comportamentais de formulário.</p>
 * <h2>CSS Style Rules</h2>
 * <ul>
 * <li>.gwt-FormComposite {estilo primario}</li>
 * </ul>
 * @author Julio Vitorino
 */
public abstract class FormComposite<V> extends Composite {
	
	public static final int ESTADO_ATUALIZAR = 1;
	public static final int ESTADO_CRIAR = 2;
	
	private int estado = ESTADO_ATUALIZAR;
	
	protected List<FormCompositeListener> listener;
	
	public FormComposite() {
		super();
		init();
		initWidget(render());
		this.setStyleName("gwt-FormComposite");
	}
	
	/**
	 * Retornara um VO do tipo V
	 *
	 * @return ValueObject do tipo V
	 */
	public abstract V getVO();
	public abstract void update(V vo);
	public abstract void notifier(V vo);
	public abstract Widget render();
	public abstract void init();

	/**
	 * Implementação para limpar os campos do formulário concreto
	 */
	public abstract void clear();

	public int getEstado() {
		return this.estado;
	}
	
	public void setEstado(int estado) {
		if ((estado < ESTADO_ATUALIZAR) || (estado > ESTADO_CRIAR)) {
			this.estado = ESTADO_ATUALIZAR;
		} else {
			this.estado = estado;
		}
	}
	
	/**
	* Adiciona o listener ao array
	*/
	public void addListener(FormCompositeListener l) {
		if (this.listener == null) {
			this.listener = new ArrayList<FormCompositeListener>();
		}
		this.listener.add(l);
	}
	

}
