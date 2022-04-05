package br.com.jcv.aluguerelaxe.client.administracao.console.proprietario;

import br.com.jcv.aluguerelaxe.client.componente.autoajuda.AutoAjudaTextBox;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.vo.DadosBancariosVO;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class DadosBancariosFormComposite 
		extends FormComposite<DadosBancariosVO>  {
	
	private final static String AUTO_AJUDA_BANCO = "<B>Nome do banco</B>" + 
	"<p>Digite o nome do banco para recebimento do aluguel de temporada.</p>" ;
	
	private final static String AUTO_AJUDA_AGENCIA = "<B>Nome do ag\u00eancia</B>" + 
	"<p>Digite o nome do ag\u00eancia banc\u00e1ria para recebimento do aluguel de temporada.</p>" ;
	
	private final static String AUTO_AJUDA_CC = "<B>Conta Corrente</B>" + 
	"<p>Digite o No. da Conta Corrente para recebimento do aluguel de temporada.</p>" ;

	private AutoAjudaTextBox banco;
	private AutoAjudaTextBox agencia;
	private AutoAjudaTextBox contacorrente;
	
	@Override
	public DadosBancariosVO getVO() {
		DadosBancariosVO vo = new DadosBancariosVO();
		vo.banco = banco.getWidgetUI().getValue();
		vo.agencia = agencia.getWidgetUI().getValue();
		vo.contacorrente = contacorrente.getWidgetUI().getValue();
		return vo;
	}
	
	@Override
	public void update(DadosBancariosVO vo) {
		banco.getWidgetUI().setText(vo.banco);
		agencia.getWidgetUI().setText(vo.agencia);
		contacorrente.getWidgetUI().setText(vo.contacorrente);
	}
	
	@Override
	public Widget render() {
		VerticalPanel vp = new VerticalPanel();
		Grid grid = new Grid(3,2);
		
		// Posiciona campos no grid
		int linha = -1;

		grid.setWidget(++linha, 0, new Label("Banco:"));
		grid.setWidget(linha, 1, banco);
		
		grid.setWidget(++linha, 0, new Label("Ag\u00eancia:"));
		grid.setWidget(linha, 1, agencia);
		
		grid.setWidget(++linha, 0, new Label("No. Conta Corrente:"));
		grid.setWidget(linha, 1, contacorrente);
		
		// Adiciona a grid ao VerticalPanel
		vp.add(grid);
		
		return vp;
	}
	@Override
	public void init() {
		banco = new AutoAjudaTextBox(AUTO_AJUDA_BANCO);
		agencia = new AutoAjudaTextBox(AUTO_AJUDA_AGENCIA);
		contacorrente = new AutoAjudaTextBox(AUTO_AJUDA_CC);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifier(DadosBancariosVO vo) {
		// TODO Auto-generated method stub
		
	}

}
