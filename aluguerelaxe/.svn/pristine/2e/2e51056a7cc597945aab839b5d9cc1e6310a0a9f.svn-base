package br.com.jcv.aluguerelaxe.client.doacao;

import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.indicadorgauge.AbstractIndicadorGauge;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public class DoacaoFormComposite extends FormComposite<DoacaoVO> {

	AbstractIndicadorGauge<DoacaoVO> iig;
	
	public DoacaoFormComposite() {
		super();
	}
	
	@Override
	public DoacaoVO getVO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(DoacaoVO vo) {
		iig.update(vo);
		
	}

	@Override
	public Widget render() {
		HorizontalPanel dp = new HorizontalPanel();
		dp.add(iig);
		return dp;
	}

	@Override
	public void init() {
		iig = new DoacaoGauge();
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifier(DoacaoVO vo) {
		// TODO Auto-generated method stub
		
	}

}
