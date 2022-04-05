package br.com.jcv.aluguerelaxe.client.administracao.console.tracker;

import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.ReservaVO;
import br.com.jcv.aluguerelaxe.client.componente.form.FormCompositeListener;

/**
* Interface para TrackerCodigoFormCompositeListener e suas extensoes
* @author Julio Vitorino
*/ 

public interface TrackerCodigoFormCompositeListener extends FormCompositeListener {
	void onPesquisarTrackerClick(ReservaVO vo);
}
