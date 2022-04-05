package br.com.jcv.aluguerelaxe.client.administracao.console.proprietario;

import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class TagFormComposite extends FormComposite<TagVO> {

	private static final String GWT_STYLE = "gwt-TagFormComposite";
	
	private Image img;
	private HTML texto;
	
	public TagFormComposite() {
		super();
		this.setStyleName(GWT_STYLE);
	}
	
	@Override
	public TagVO getVO() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void update(TagVO vo) {
		img.setUrl(vo.img.getUrl());
		texto.setHTML(vo.texto.getHTML());
	}
	
	@Override
	public void notifier(TagVO vo) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Widget render() {
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(img);
		hp.add(texto);
		return hp;
	}
	
	@Override
	public void init() {
		img = new Image();
		texto = new HTML();
	}
	
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	
	
}