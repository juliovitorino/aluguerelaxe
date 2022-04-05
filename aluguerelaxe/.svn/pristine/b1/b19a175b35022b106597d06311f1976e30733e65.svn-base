package br.com.jcv.aluguerelaxe.client.depoimento;

import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.listbox.UFListBox;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class DepoimentoFormComposite extends FormComposite<DepoimentoVO> {

	private TextBox nome;
	private ListBox uf;
	private TextArea depoimento;
	
	@Override
	public DepoimentoVO getVO() {
		DepoimentoVO vo = new DepoimentoVO();
		vo.nome = nome.getValue() + ", " + uf.getValue(uf.getSelectedIndex());
		vo.depoimento = depoimento.getValue();
		return vo;
	}

	@Override
	public void update(DepoimentoVO vo) {
		// Nao aplicavel ao contexto
		
	}

	@Override
	public Widget render() {
		VerticalPanel vp = new VerticalPanel();
		Grid grid = new Grid(2,2);
		
		// Posiciona campos no grid
		int linha = -1;

		grid.setWidget(++linha, 0, new Label("Nome:"));
		grid.setWidget(linha, 1, nome);

		grid.setWidget(++linha, 0, new Label("UF:"));
		grid.setWidget(linha, 1, uf);

		// Adiciona a grid ao VerticalPanel
		vp.add(grid);
		vp.add(new Label("Envie-nos o seu depoimento"));
		vp.add(depoimento);
		
		
		return vp;	}

	@Override
	public void init() {
		nome = new TextBox();
		uf = new UFListBox();
		depoimento = new TextArea();
		depoimento.setSize("500px","100px");
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifier(DepoimentoVO vo) {
		// TODO Auto-generated method stub
		
	}

}
