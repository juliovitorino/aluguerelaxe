package br.com.jcv.aluguerelaxe.client.parameter;

import br.com.jcv.aluguerelaxe.client.cliente.ClienteVO;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;

import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class PlanosPagosAVencerFormComposite extends FormComposite<ClienteVO> {
	
	private TextBox idCliente;

	@Override
	public ClienteVO getVO() {
		ClienteVO vo = new ClienteVO();
		vo.id = Long.valueOf(idCliente.getText());
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
		return idCliente;
	}

	@Override
	public void init() {
		idCliente = new TextBox();

		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	
}
