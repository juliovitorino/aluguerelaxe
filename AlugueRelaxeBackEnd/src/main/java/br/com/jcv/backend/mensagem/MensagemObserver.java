package br.com.jcv.backend.mensagem;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * Classe responsével por notificar e subscrever os objetos
 * que precisam saber de mudanéas dentro da tabela MENSAGENS.
 *
 * @author Julio.
 *
 */
public class MensagemObserver {
	
	private Collection<MensagemListener> assinantes = new ArrayList<MensagemListener>();

	public MensagemObserver() {}
	
	public synchronized void addMensagemListener(MensagemListener l) {
        if(!assinantes.contains(l)) {
        	assinantes.add(l);
        }
    }
	
	public synchronized void removeMensagemListener(MensagemListener l) {
		assinantes.remove(l);
	}
	
	public void notificaAssinantes() {
        Collection <MensagemListener> ml;
        synchronized (this) {
            // Clonar para evitar problemas de sincronizaééo durante a propagaééo
            ml = (Collection)(((ArrayList)assinantes).clone());
        }
        MensagemEvent evento = new MensagemEvent(this);                
        for (MensagemListener t : ml) {
        	t.mensagemNotificacao(evento);
        }
    }
}
