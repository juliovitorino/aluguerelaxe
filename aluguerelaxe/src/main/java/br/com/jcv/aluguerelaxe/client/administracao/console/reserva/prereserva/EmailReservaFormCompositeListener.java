package br.com.jcv.aluguerelaxe.client.administracao.console.reserva.prereserva;

import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.LocatarioVO;
import br.com.jcv.aluguerelaxe.client.componente.form.FormCompositeListener;

/**
* Interface para TrackerCodigoFormCompositeListener e suas extensoes
* @author Julio Vitorino
*/ 

public interface EmailReservaFormCompositeListener extends FormCompositeListener {
	void onPesquisarEmailClick(LocatarioVO vo);
}
