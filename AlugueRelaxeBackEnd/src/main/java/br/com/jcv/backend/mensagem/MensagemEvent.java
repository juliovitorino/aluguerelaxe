package br.com.jcv.backend.mensagem;

import java.util.EventObject;

public class MensagemEvent extends EventObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8439300342118208598L;

	public MensagemEvent(MensagemObserver observador) {
		super(observador);
	}
}
