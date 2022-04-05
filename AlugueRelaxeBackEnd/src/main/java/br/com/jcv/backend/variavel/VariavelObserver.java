package br.com.jcv.backend.variavel;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * Classe responsavel por notificar e subscrever os objetos
 * que precisam saber de mudancas dentro da tabela VARIAVEIS.
 *
 * @author Julio.
 *
 */
public class VariavelObserver {
	
	private Collection<VariavelListener> assinantes = new ArrayList<VariavelListener>();

	public VariavelObserver() {}
	
	public synchronized void addVariavelListener(VariavelListener l) {
        if(!assinantes.contains(l)) {
        	assinantes.add(l);
        }
    }
	
	public synchronized void removeVariavelListener(VariavelListener l) {
		assinantes.remove(l);
	}
	
	public void notificaAssinantes() {
        Collection <VariavelListener> ml;
        synchronized (this) {
            // Clonar para evitar problemas de sincronizacao durante a propagacao
            ml = (Collection)(((ArrayList)assinantes).clone());
        }
        VariavelEvent evento = new VariavelEvent(this);                
        for (VariavelListener t : ml) {
        	t.notificacao(evento);
        }
    }
}
