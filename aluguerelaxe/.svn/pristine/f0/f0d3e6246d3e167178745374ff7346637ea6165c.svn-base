package br.com.jcv.aluguerelaxe.client.administracao.console.liberarpgto;

import br.com.jcv.aluguerelaxe.client.Constantes;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.ReservaVO;
import br.com.jcv.aluguerelaxe.client.administracao.console.tracker.TrackerCodigoFormCompositeListener;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class MemoriaCalculoFormComposite 
		extends FormComposite<ReservaVO> 
		implements TrackerCodigoFormCompositeListener {
	
	private static final String SIMBOLO_CURRENCY = "R$ ";
	private Label negociado;
	private Label comissao;
	private Label caucao;
	private Label custodia;
	private Label aliberar;
	private Label formaPgto;
	private Label valorEntregaChave;
	private Label percComissao;
	
	@Override
	public ReservaVO getVO() {
		
		return null;
	}
	
	@Override
	public void update(ReservaVO vo) {
		double vcomissao = vo.valorTaxaServico;
		double vliberar = vo.valorRealDeposito - vcomissao;
		
		double vpagochave = (vo.valorAluguelNegociado + vcomissao) * (vo.formaPagamento.equals(Constantes.FORMA_PGTO_PARCIAL) ? 0.5 : 0 );
		
		negociado.setText(SIMBOLO_CURRENCY + String.valueOf(vo.valorAluguelNegociado + vcomissao));
		formaPgto.setText( (vo.formaPagamento.equals(Constantes.FORMA_PGTO_PARCIAL) ? Constantes.FORMA_PGTO_PARCIAL_STR : Constantes.FORMA_PGTO_TOTAL_STR));
		custodia.setText(SIMBOLO_CURRENCY + String.valueOf(vo.valorRealDeposito));
		caucao.setText(SIMBOLO_CURRENCY + String.valueOf(vo.valorCaucao));
		valorEntregaChave.setText(SIMBOLO_CURRENCY + String.valueOf(vpagochave));
		percComissao.setText(String.valueOf(vo.percentualComissao * 100) + "%");
		comissao.setText(SIMBOLO_CURRENCY + String.valueOf(vcomissao));
		aliberar.setText(SIMBOLO_CURRENCY + String.valueOf(vliberar));
	}
	
	@Override
	public Widget render() {
		VerticalPanel vp = new VerticalPanel();
		Grid grid = new Grid(8,2);
		
		// Posiciona campos no grid
		int linha = -1;

		grid.setWidget(++linha, 0, new Label("Valor negociado da temporada:"));
		grid.setWidget(linha, 1, negociado);
		
		grid.setWidget(++linha, 0, new Label("Forma de Pagamento Escolhida:"));
		grid.setWidget(linha, 1, formaPgto);
		
		grid.setWidget(++linha, 0, new Label("Deposito antecipado (cust\u00f3dia): "));
		grid.setWidget(linha, 1, custodia);
		
		grid.setWidget(++linha, 0, new Label("Dep\u00f3sito Cau\u00e7\u00e3o: "));
		grid.setWidget(linha, 1, caucao);
		
		grid.setWidget(++linha, 0, new Label("Valor a ser pago na entrega da chave: "));
		grid.setWidget(linha, 1, valorEntregaChave);
		
		grid.setWidget(++linha, 0, new Label("Comiss\u00e3o do AlugueRelaxe (%): "));
		grid.setWidget(linha, 1, percComissao);
		
		grid.setWidget(++linha, 0, new Label("Comiss\u00e3o AlugueRelaxe: "));
		grid.setWidget(linha, 1, comissao);
		
		grid.setWidget(++linha, 0, new Label("Valor a ser Transferido: "));
		grid.setWidget(linha, 1, aliberar);

		// Adiciona a grid ao VerticalPanel
		vp.add(grid);
		vp.add(new Label("(*) O valor da cau\u00e7\u00e3o (C) ser\u00e1 devolvido ao cliente ao final da temporada conforme contrato de loca\u00e7\u00e3o."));
		
		return vp;
	}
	@Override
	public void init() {
		negociado = new Label();
		comissao = new Label();
		caucao = new Label();
		custodia = new Label();
		aliberar = new Label();
		formaPgto = new Label();
		valorEntregaChave = new Label();
		percComissao = new Label();
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifier(ReservaVO vo) {
		// TODO Auto-generated method stub
		
	}

	public void onPesquisarTrackerClick(ReservaVO vo) {
		update(vo);
	}
	
	
	
	
	
	
}
