
package br.com.jcv.aluguerelaxe.client.componente.anuncio;

import br.com.jcv.aluguerelaxe.client.componente.galeria.PortaRetrato;
import br.com.jcv.aluguerelaxe.client.componente.galeria.PortaRetratoListener;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * Classe abstrata para adicionar o listener {@link AnuncioListener} e 
 * disparar o evento <code>onAnuncioClick</code> para os listeners cadastrados
 *
 * @author Julio Vitorino
 *
 */
public abstract class AbstractAnuncio extends Composite implements ClickHandler,PortaRetratoListener {

	List<AnuncioListener> listeners = null;
	private ImovelFichaCompletaVO ifcvo = null;
	
	public AbstractAnuncio() {
		initWidget(render());
		this.setStylePrimaryName("gwt-AbstractAnuncio");
	}
	
	public AbstractAnuncio(ImovelFichaCompletaVO ifcvo) {
		this.ifcvo = ifcvo;
		initWidget(render());
		this.setStylePrimaryName("gwt-AbstractAnuncio");
	}
	
	/**
	 * Adiciona um {@link AnuncioListener} a lista de inscri��o de listeners
	 *
	 * @param al Comentar aqui.
	 */
	public void addAnuncioListener(AnuncioListener al) {
		if (listeners == null) {
			listeners = new ArrayList<AnuncioListener>();
		}
		listeners.add(al);
	}
	
	/**
	 * Dispara o evento <code>onAnuncioClick</code> para as inst�ncias registradas
	 * que est�o implementando a interface {@link AnuncioListener}.
	 * @see com.google.gwt.event.dom.client.ClickHandler#onClick(com.google.gwt.event.dom.client.ClickEvent)
	 */
	public void onClick(ClickEvent event) {
		if (this.listeners != null){
			for (AnuncioListener al : this.listeners) {
				al.onAnuncioClick(this);
			}
		}
	}
	
	/**
	 * M�todo deve ser implementado pelas classes concretas caso desejem
	 * ter seu an�ncio renderizado
	 *
	 * @return Widget com o componente que dever� ser renderizado no browser
	 */
	abstract protected Widget render();

	/**
	 * Este m�todo � respons�vel por retornar o
	 * conte�do de 'ifcvo'.
	 * @return Retorna um objeto do tipo ImovelFichaCompletaVO
	 */
	public ImovelFichaCompletaVO getImovelFichaCompletaVO() {
		return this.ifcvo;
	}
	
	public void onPortaRetratoClick(PortaRetrato portaRetrato) {
		if (this.listeners != null){
			for (AnuncioListener al : this.listeners) {
				al.onAnuncioClick(this);
			}
		}
	}

	

}

