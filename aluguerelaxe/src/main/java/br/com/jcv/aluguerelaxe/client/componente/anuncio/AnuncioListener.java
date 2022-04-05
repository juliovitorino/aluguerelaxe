
package br.com.jcv.aluguerelaxe.client.componente.anuncio;

import java.util.EventListener;

/**
 * <h2>AnuncioListener</h2>
 *
 * <p>Interface que conter� todos os eventos que executar�o as a��es
 * quando um an�ncio for clicado.</p>
 * @author Julio Cesar Vitorino
 *
 */
public interface AnuncioListener extends EventListener {
	
	/**
	 * Evento enviado quando o an�ncio for clicado
	 *
	 * @param anuncio Classe que implementa uma abstra��o de an�ncio
	 */
	void onAnuncioClick(AbstractAnuncio anuncio);

}

