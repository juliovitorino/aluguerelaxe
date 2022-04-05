package br.com.jcv.aluguerelaxe.client.administracao.console.publicidade.compra;

import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ComprarPublicidadeFormComposite extends FormComposite<VOPadrao> {
	
	private static final String PATH_IMAGEM = "images/";


	@Override
	public Widget render() {
		VerticalPanel vp = new VerticalPanel();
		vp.add(new Image(PATH_IMAGEM + "fluxo-cp-pagseguro.png"));

		return vp;
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
	public VOPadrao getVO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(VOPadrao vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifier(VOPadrao vo) {
		// TODO Auto-generated method stub
		
	}

}
