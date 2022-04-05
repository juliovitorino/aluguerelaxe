package br.com.jcv.aluguerelaxe.client.imovel.ficha;

import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class OcupacaoFormComposite extends FormComposite<VOPadrao> {
	
	@Override
	public VOPadrao getVO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(VOPadrao vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Widget render() {
		// TODO Auto-generated method stub
		return new Label("Consulte o propriet\u00e1rio para maiores informa\u00e7\u00f5es sobre ocupa\u00e7\u00e3o do im\u00f3vel.");
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifier(VOPadrao vo) {
		// TODO Auto-generated method stub
		
	}

}
