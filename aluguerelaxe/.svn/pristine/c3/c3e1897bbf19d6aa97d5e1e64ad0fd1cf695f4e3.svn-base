package br.com.jcv.aluguerelaxe.client.administracao.console.liberarpgto;

import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.ReservaVO;
import br.com.jcv.aluguerelaxe.client.componente.autoajuda.AutoAjudaTextBox;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class VoucherFormComposite 
		extends FormComposite<ReservaVO>  {
	
	private final static String AUTO_AJUDA_CODIGO_VOUCHER = "<B>O que \u00e9 um voucher?</B>" + 
	"<p>Voucher \u00e9 um t\u00edtulo, recibo ou documento que comprova o pagamento e o direito a um servi\u00e7o<br/><br/>" +
	"<p><strong>Os vouchers s\u00e3o utilizados principalmente no setor de turismo como prova,<br/>" +
	"em nome do cliente , de ter direito a um servi\u00e7o em um tempo espec\u00edfico e lugar.<br/>" +
	"Os prestadores de servi\u00e7os os recebem para cobrar do agente de viagens<br/>" +
	"que enviou esse cliente, para provar que o servi\u00e7o foi entregue.</strong></p>";

	private AutoAjudaTextBox voucher;
	
	@Override
	public ReservaVO getVO() {
		ReservaVO vo = new ReservaVO();
		vo.chaveLiberacaoDeposito = voucher.getWidgetUI().getText();
		return vo;
	}
	
	@Override
	public void update(ReservaVO vo) {
		
	}
	
	@Override
	public Widget render() {
		VerticalPanel vp = new VerticalPanel();
		Grid grid = new Grid(1,2);
		
		// Posiciona campos no grid
		int linha = -1;

		grid.setWidget(++linha, 0, new Label("C\u00f3digo de Libera\u00e7\u00e3o (Voucher):"));
		grid.setWidget(linha, 1, voucher);

		// Adiciona a grid ao VerticalPanel
		vp.add(grid);
		
		return vp;
	}
	@Override
	public void init() {
		voucher = new AutoAjudaTextBox(AUTO_AJUDA_CODIGO_VOUCHER);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifier(ReservaVO vo) {
		// TODO Auto-generated method stub
		
	}

}
