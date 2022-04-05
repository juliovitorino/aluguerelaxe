package br.com.jcv.aluguerelaxe.client.parameter;

import br.com.jcv.aluguerelaxe.client.cliente.ClienteVO;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class StatusFormComposite extends FormComposite<ClienteVO> {
	
	public StatusFormComposite(){
		super();
	}
	
	private TextBox status;

	@Override
	public ClienteVO getVO() {
		ClienteVO vo = new ClienteVO();
		vo.indicadorStatus = this.status.getText();
		return vo;
	}

	@Override
	public void update(ClienteVO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifier(ClienteVO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Widget render() {
		VerticalPanel vp = new VerticalPanel();
		vp.add(new Label("Informe status (A/I)"));
		vp.add(status);
		return vp;
	}

	@Override
	public void init() {
		status = new TextBox();
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

}
