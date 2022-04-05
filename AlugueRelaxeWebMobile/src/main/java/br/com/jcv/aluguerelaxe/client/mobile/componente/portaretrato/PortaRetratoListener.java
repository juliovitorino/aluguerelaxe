
package br.com.jcv.aluguerelaxe.client.mobile.componente.portaretrato;

import java.util.EventListener;

/**
 * Interface com m�todos que ser�o disparados pela classe {@link PortaRetrato} durante
 * um evento de <code>onClick</code>
 *
 * @author elmt
 *
 */
public interface PortaRetratoListener extends EventListener {

	/**
	 * Implementa��o para o evento de onClick quando ocorrer no PortaRetrato
	 *
	 * @param portaRetrato - Ser� enviado uma inst�ncia de {@link PortaRetrato} para
	 * a classe implementadora.
	 */
	void onPortaRetratoClick(PortaRetrato portaRetrato);
}

