package br.com.jcv.aluguerelaxe.client.faleconosco;

import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class FaleConoscoFormComposite extends FormComposite<FaleConoscoVO> {
	private TextBox nome;
	private TextBox email;
	private TextBox assunto;
	private ListBox topico;
	private TextArea mensagem;
	
	@Override
	public FaleConoscoVO getVO() {
		FaleConoscoVO vo = new FaleConoscoVO();
		vo.nome = nome.getValue();
		vo.email = email.getValue();
		vo.assunto = assunto.getValue();
		vo.mensagem = mensagem.getValue();
		vo.topico = topico.getItemText(topico.getSelectedIndex());
		return vo;
	}
	
	@Override
	public void update(FaleConoscoVO vo) {
		// Nao aplicado neste contexto
		
	}
	
	@Override
	public Widget render() {
		VerticalPanel vp = new VerticalPanel();
		Grid grid = new Grid(4,2);
		
		// Posiciona campos no grid
		int linha = -1;

		grid.setWidget(++linha, 0, new Label("Nome:"));
		grid.setWidget(linha, 1, nome);

		grid.setWidget(++linha, 0, new Label("E-mail:"));
		grid.setWidget(linha, 1, email);

		grid.setWidget(++linha, 0, new Label("Assunto:"));
		grid.setWidget(linha, 1, assunto);

		grid.setWidget(++linha, 0, new Label("T\u00f3pico:"));
		grid.setWidget(linha, 1, topico);

		// Adiciona a grid ao VerticalPanel
		vp.add(grid);
		vp.add(new Label("Mensagem"));
		vp.add(mensagem);
		
		
		return vp;
	}
	@Override
	public void init() {
		nome = new TextBox();
		email = new TextBox();
		assunto = new TextBox();
		mensagem = new TextArea();
		mensagem.setSize("500px","100px");
		
		topico = new ListBox();
		topico.addItem("Sugest\u00e3o");
		topico.addItem("Informa\u00e7\u00e3o");
		topico.addItem("Reclama\u00e7\u00e3o");
		topico.addItem("Outros");
	}
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifier(FaleConoscoVO vo) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
}
