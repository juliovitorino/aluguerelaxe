package br.com.jcv.aluguerelaxe.client.novaconta;

import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class NovaSenhaFormComposite extends FormComposite<NovaSenhaVO> {

	private PasswordTextBox senha;
	private PasswordTextBox contrasenha;
	
	public NovaSenhaFormComposite() {
		super();
	}
	
	@Override
	public NovaSenhaVO getVO() {
		NovaSenhaVO vo = new NovaSenhaVO();
		vo.senha = senha.getValue();
		vo.contrasenha = contrasenha.getValue();
		return vo;
	}

	@Override
	public void update(NovaSenhaVO vo) {
		//Nao aplicado para o contexto
	}

	@Override
	public Widget render() {
		VerticalPanel vp = new VerticalPanel();
		Grid grid = new Grid(2,2);
		
		// Monta os labels
		Label lblSenha = new Label("Nova Senha:");
		Label lblRepeteSenha = new Label("Repita a Senha");
		
		// Posiciona campos no grid
		int linha = -1;

		grid.setWidget(++linha, 0, lblSenha);
		grid.setWidget(linha, 1, senha);

		grid.setWidget(++linha, 0, lblRepeteSenha);
		grid.setWidget(linha, 1, contrasenha);
		vp.add(grid);
		return vp;
	}

	@Override
	public void init() {
		senha = new PasswordTextBox();
		contrasenha = new PasswordTextBox();
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifier(NovaSenhaVO vo) {
		// TODO Auto-generated method stub
		
	}

}
