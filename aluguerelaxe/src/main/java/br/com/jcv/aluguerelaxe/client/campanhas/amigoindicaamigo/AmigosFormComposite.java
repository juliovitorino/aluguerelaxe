package br.com.jcv.aluguerelaxe.client.campanhas.amigoindicaamigo;

import br.com.jcv.aluguerelaxe.client.EmailVO;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class AmigosFormComposite extends FormComposite<EmailVO> {

	private TextBox email;
	private TextBox nome;
	
	public AmigosFormComposite() {
		super();
		this.setStylePrimaryName("gwt-AmigosFormComposite");
	}
	
	@Override
	public EmailVO getVO() {
		EmailVO vo = new EmailVO();
		vo.email = email.getValue();
		vo.nome = nome.getValue();
		return vo;
	}

	@Override
	public void update(EmailVO vo) {
		//Nao aplicado para o contexto
	}

	@Override
	public Widget render() {
		VerticalPanel vp = new VerticalPanel();
		Grid grid = new Grid(2,2);
		
		// Monta os labels
		Label lblEmail = new Label("Email:");
		Label lblNome = new Label("Nome do amigo:");
		
		// Posiciona campos no grid
		int linha = -1;

		grid.setWidget(++linha, 0, lblEmail);
		grid.setWidget(linha, 1, email);

		grid.setWidget(++linha, 0, lblNome);
		grid.setWidget(linha, 1, nome);
		vp.add(grid);
		return vp;
	}

	@Override
	public void init() {
		email = new TextBox();
		nome = new TextBox();
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifier(EmailVO vo) {
		// TODO Auto-generated method stub
		
	}

}
