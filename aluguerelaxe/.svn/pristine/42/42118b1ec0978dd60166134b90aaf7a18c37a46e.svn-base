package br.com.jcv.aluguerelaxe.client.logodinamico;

import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class LogoDinamicoFormComposite extends FormComposite<LogoDinamicoVO> {

	private Image logo;
	
	public LogoDinamicoFormComposite() {
		this.setStylePrimaryName("gwt-LogoDinamicoFormComposite");
	}
	
	@Override
	public LogoDinamicoVO getVO() {
		// Nao aplicavel neste contexto
		return null;
	}

	@Override
	public void update(LogoDinamicoVO vo) {
		this.logo.setUrl(vo.pathCompleto);
		this.logo.setTitle(vo.hint);
	}
	
	@Override
	public Widget render() {
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(this.logo);
		return hp;
	}

	@Override
	public void init() {
		this.logo = new Image(); 
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifier(LogoDinamicoVO vo) {
		// TODO Auto-generated method stub
		
	}

}
