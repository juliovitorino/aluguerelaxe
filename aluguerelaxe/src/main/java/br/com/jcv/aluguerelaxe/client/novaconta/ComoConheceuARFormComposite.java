package br.com.jcv.aluguerelaxe.client.novaconta;

import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.listbox.ModoPublicidadeListBox;
import br.com.jcv.aluguerelaxe.client.modopublicidade.ModoPublicidadeVO;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ComoConheceuARFormComposite extends FormComposite<ModoPublicidadeVO> {

	private ModoPublicidadeListBox modopublicidade;
	
	public ComoConheceuARFormComposite() {
		super();
	}
	
	@Override
	public ModoPublicidadeVO getVO() {
		ModoPublicidadeVO vo = new ModoPublicidadeVO();
		vo.id = Integer.valueOf(modopublicidade.getValue(modopublicidade.getSelectedIndex()));
		return vo;
	}

	@Override
	public void update(ModoPublicidadeVO vo) {
		//Nao aplicado para o contexto
	}

	@Override
	public Widget render() {
		VerticalPanel vp = new VerticalPanel();
		Grid grid = new Grid(1,2);
		
		// Monta os labels
		Label lblSenha = new Label("Como conheceu o AlugueRelaxe ?");
		
		// Posiciona campos no grid
		int linha = -1;

		grid.setWidget(++linha, 0, lblSenha);
		grid.setWidget(linha, 1, modopublicidade);

		vp.add(grid);
		return vp;
	}

	@Override
	public void init() {
		modopublicidade = new ModoPublicidadeListBox();
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifier(ModoPublicidadeVO vo) {
		// TODO Auto-generated method stub
		
	}

}
