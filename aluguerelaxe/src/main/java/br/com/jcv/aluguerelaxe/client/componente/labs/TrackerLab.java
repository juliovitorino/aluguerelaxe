package br.com.jcv.aluguerelaxe.client.componente.labs;

import br.com.jcv.aluguerelaxe.client.componente.tracker.Fase;
import br.com.jcv.aluguerelaxe.client.componente.tracker.Tracker;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;


public class TrackerLab implements EntryPoint {
	public void onModuleLoad() {
		Tracker tracker = new Tracker();
		tracker.add(new Fase("Pre-reserva"));
		tracker.add(new Fase("Aceite"));
		tracker.add(new Fase("Validacao"));
		tracker.add(new Fase("Aguardando deposito"));
		tracker.add(new Fase("Confirmacao deposito"));
		tracker.add(new Fase("Entrega de contrato"));
		tracker.add(new Fase("Validacao do imovel"));
		tracker.add(new Fase("Solicitar liberacao deposito"));
		tracker.add(new Fase("Transferencia deposito"));
		RootPanel.get("gwt-tacker").add(tracker);
	}
}