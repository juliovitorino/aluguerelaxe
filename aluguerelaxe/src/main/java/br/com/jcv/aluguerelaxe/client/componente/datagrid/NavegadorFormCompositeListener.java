package br.com.jcv.aluguerelaxe.client.componente.datagrid;

import br.com.jcv.aluguerelaxe.client.componente.form.FormCompositeListener;

public interface NavegadorFormCompositeListener extends FormCompositeListener {
	void onPrimeiroClick();
	void onUltimoClick();
	void onProximoClick();
	void onAnteriorClick();
}