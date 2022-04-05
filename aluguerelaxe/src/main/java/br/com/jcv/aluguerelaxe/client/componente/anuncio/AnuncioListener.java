
package br.com.jcv.aluguerelaxe.client.componente.anuncio;

import java.util.EventListener;

/**
 * <h2>AnuncioListener</h2>
 *
 * <p>Interface que conterá todos os eventos que executarão as ações
 * quando um anúncio for clicado.</p>
 * @author Julio Cesar Vitorino
 *
 */
public interface AnuncioListener extends EventListener {
	
	/**
	 * Evento enviado quando o anúncio for clicado
	 *
	 * @param anuncio Classe que implementa uma abstração de anúncio
	 */
	void onAnuncioClick(AbstractAnuncio anuncio);

}

