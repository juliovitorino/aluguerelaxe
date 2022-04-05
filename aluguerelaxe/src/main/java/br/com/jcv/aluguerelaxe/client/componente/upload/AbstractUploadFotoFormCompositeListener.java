package br.com.jcv.aluguerelaxe.client.componente.upload;

import br.com.jcv.aluguerelaxe.client.componente.form.FormCompositeListener;

public interface AbstractUploadFotoFormCompositeListener extends FormCompositeListener {
	void onCancelarClick();
	void onUploadFinished();
}