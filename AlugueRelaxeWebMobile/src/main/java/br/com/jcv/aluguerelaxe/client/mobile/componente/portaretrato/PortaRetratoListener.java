
package br.com.jcv.aluguerelaxe.client.mobile.componente.portaretrato;

import java.util.EventListener;

/**
 * Interface com métodos que serão disparados pela classe {@link PortaRetrato} durante
 * um evento de <code>onClick</code>
 *
 * @author elmt
 *
 */
public interface PortaRetratoListener extends EventListener {

	/**
	 * Implementação para o evento de onClick quando ocorrer no PortaRetrato
	 *
	 * @param portaRetrato - Será enviado uma instância de {@link PortaRetrato} para
	 * a classe implementadora.
	 */
	void onPortaRetratoClick(PortaRetrato portaRetrato);
}

