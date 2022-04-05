package br.com.jcv.aluguerelaxe.client.charter;

import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class CharterImovelUFFormComposite extends FormComposite<CharterVO> {

	private Image chart;
	
	public CharterImovelUFFormComposite() {
		super();
		this.setStylePrimaryName("gwt-CharterImovelUFFormComposite");
	}
	
	@Override
	public CharterVO getVO() {
		// Nao Aplicavel
		return null;
	}

	@Override
	public void update(CharterVO vo) {
		chart.setUrl(vo.url);

	}

	@Override
	public Widget render() {
		VerticalPanel vp = new VerticalPanel();
		vp.add(chart);
		return vp;
	}

	@Override
	public void init() {
		chart = new Image();

	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifier(CharterVO vo) {
		// TODO Auto-generated method stub
		
	}

}
