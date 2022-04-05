package br.com.jcv.aluguerelaxe.client.administracao.console.reserva.prereserva;

import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.ReservaVO;
import br.com.jcv.aluguerelaxe.client.componente.autoajuda.AutoAjudaTextBox;
import br.com.jcv.aluguerelaxe.client.componente.date.DateBoxWidget;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.listbox.FormaPagamentoReservaListBox;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ReservaFormComposite extends FormComposite<ReservaVO> {
	
	private final static String AUTO_AJUDA_VALOR_CAUCAO = "<B>Valor da Cau\u00e7\u00e3o</B>" + 
	"<p>Digite o valor do seguro cau\u00e7\u00e3o</p>";

	private final static String AUTO_AJUDA_VALOR_NEGOCIADO = "<B>Valor Negociado</B>" + 
	"<p>Digite o total do valor negociado.<br/>. Neste campo já devem estar contabilizados taxas de limpeza, seguro caução, entre outros.</p>";
	
	private final static String AUTO_AJUDA_VALOR_PREVISTO = "<B>Valor Previsto Dep\u00f3sito</B>" + 
	"<p>Digite o montante previsto para depósito.</p>";

	private final static String AUTO_AJUDA_TAXA_SERVICO = "<B>Taxa de serviço do AlugueRelaxe</B>" + 
	"<p>Calculado automaticamente.</p>";

	private static final String AUTO_AJUDA_TOTAL_PAGAR = "<B>Valor Total a Pagar</B>" + 
	"<p>Valor total da temporada.<br/>Pode ser pago com Cartão de Crédito em até 12x pelo ambiente do nosso parceiro UOL Pagseguro.</p>";

	private static final String AUTO_AJUDA_PAGAR_NA_CHAVE = "<B>Valor a Pagar na Entrega das Chaves</B>" + 
	"<p>Se você optou por pagar 50% de entrada, o restante deve ser pago ao proprietário do imóvel no seu checkin.<br/>" +
	"Pode ser conforme sua negociação direta com o proprietário pelo gerenciador de dúvidas do AlugueRelaxe.</p>";

	private AutoAjudaTextBox valorTaxaServico;
	private AutoAjudaTextBox valorCaucao;
	private AutoAjudaTextBox valorNegociado;
	private DateBoxWidget dataPrevistaDeposito;
	private AutoAjudaTextBox valorPrevistoDeposito;
	private DateBoxWidget dataCheckin;
	private DateBoxWidget dataCheckout;
	private FormaPagamentoReservaListBox formapgto;
	private AutoAjudaTextBox valorTotalPagar;
	private AutoAjudaTextBox valorPagarChave;
	
	private ReservaVO rvo;

	
	public ReservaFormComposite() {
		super();
	}
	
	public void init() {
		valorTotalPagar =  new AutoAjudaTextBox(AUTO_AJUDA_TOTAL_PAGAR);
		valorPagarChave = new AutoAjudaTextBox(AUTO_AJUDA_PAGAR_NA_CHAVE);
		valorTaxaServico =  new AutoAjudaTextBox(AUTO_AJUDA_TAXA_SERVICO);
		valorCaucao =  new AutoAjudaTextBox(AUTO_AJUDA_VALOR_CAUCAO);
		valorNegociado =  new AutoAjudaTextBox(AUTO_AJUDA_VALOR_NEGOCIADO);
		valorPrevistoDeposito =  new AutoAjudaTextBox(AUTO_AJUDA_VALOR_PREVISTO);
		dataPrevistaDeposito = new DateBoxWidget();
		dataCheckin = new DateBoxWidget();
		dataCheckout = new DateBoxWidget();
		formapgto = new FormaPagamentoReservaListBox();
		
		// Adiciona eventos em alguns campos
		formapgto.addChangeHandler(new ChangeHandler() {
			
			public void onChange(ChangeEvent event) {
				ReservaFormComposite.this.calcularTemporada();
			}
		});
		
		// Desabilita campos nao editaveis
		valorTaxaServico.getWidgetUI().setEnabled(false);
		valorPagarChave.getWidgetUI().setEnabled(false);
		valorTotalPagar.getWidgetUI().setEnabled(false);
		valorPrevistoDeposito.getWidgetUI().setEnabled(false);
		valorCaucao.getWidgetUI().setEnabled(false);
		
	}
	
	private void calcularTemporada() {
		
		double vlneg = Double.valueOf(valorNegociado.getWidgetUI().getValue());
		//---------------------------------------------------
		// calcula a taxa de servico
		//---------------------------------------------------
		double txs = vlneg * rvo.percentualComissao;
		
		//---------------------------------------------------
		// calcula total a pagar
		//---------------------------------------------------
		double ttp = vlneg + txs;
		
		//---------------------------------------------------
		// calcula valor previsto a ser pago de acordo
		// com a forma de pagamento
		//---------------------------------------------------
		double vlprevpgto = ttp;
		if (formapgto.getValue(formapgto.getSelectedIndex()).equals("P")){
			vlprevpgto /= 2;
		}
		
		//---------------------------------------------------
		// calcula total a pagar na chave
		//---------------------------------------------------
		double tpgchave = ttp - vlprevpgto;
		
		//---------------------------------------------------
		// Popula campos calculados
		//---------------------------------------------------
		valorTotalPagar.getWidgetUI().setValue(String.valueOf(ttp));
		valorPagarChave.getWidgetUI().setValue(String.valueOf(tpgchave));
		valorTaxaServico.getWidgetUI().setValue(String.valueOf(txs));
		valorPrevistoDeposito.getWidgetUI().setValue(String.valueOf(vlprevpgto));

	}

	public Widget render() {
		VerticalPanel vp = new VerticalPanel();
		Grid grid = new Grid(10,2);
		
		// Posiciona campos no grid
		int linha = -1;
		
		grid.setWidget(++linha, 0, new Label("Valor Negociado:"));
		grid.setWidget(linha, 1, valorNegociado);

		grid.setWidget(++linha, 0, new Label("Forma de Pagamento:"));
		grid.setWidget(linha, 1, formapgto);

		grid.setWidget(++linha, 0,  new Label("Seguro Cau\u00e7\u00e3o:"));
		grid.setWidget(linha, 1, valorCaucao);
		
		grid.setWidget(++linha, 0,  new Label("Taxa de Servi\u00e7o:"));
		grid.setWidget(linha, 1, valorTaxaServico);
		
		grid.setWidget(++linha, 0,  new Label("TOTAL A PAGAR:"));
		grid.setWidget(linha, 1, valorTotalPagar);
		
		grid.setWidget(++linha, 0, new Label("Data Prevista do Dep\u00f3sito:"));
		grid.setWidget(linha, 1, dataPrevistaDeposito);
		
		grid.setWidget(++linha, 0, new Label("Valor Previsto em Dep\u00f3sito:"));
		grid.setWidget(linha, 1, valorPrevistoDeposito);

		grid.setWidget(++linha, 0, new Label("Valor pagar na Chave:"));
		grid.setWidget(linha, 1, valorPagarChave);

		grid.setWidget(++linha, 0,  new Label("Data Checkin:"));
		grid.setWidget(linha, 1, dataCheckin);

		grid.setWidget(++linha, 0, new Label("Data Checkout:"));
		grid.setWidget(linha, 1, dataCheckout);

		// Adiciona a grid ao VerticalPanel
		vp.add(grid);
		
		return vp;
	}

	@Override
	public ReservaVO getVO() {
		ReservaVO vo = new ReservaVO();
		//vo.valorCaucao = Double.valueOf(valorCaucao.getWidgetUI().getValue());
		vo.valorCaucao = 0;
		vo.valorAluguelNegociado = Double.valueOf(valorNegociado.getWidgetUI().getValue());
		vo.valorPrevistoDeposito = Double.valueOf(valorPrevistoDeposito.getWidgetUI().getValue());
		vo.valorTaxaServico = Double.valueOf(valorTaxaServico.getWidgetUI().getValue());
		vo.dataPrevistaDeposito = dataPrevistaDeposito.getValue();
		vo.dataCheckin = dataCheckin.getValue();
		vo.dataCheckout = dataCheckout.getValue();
		vo.formaPagamento = formapgto.getValue(formapgto.getSelectedIndex()) ; 
		return vo;
	}
	
	@Override
	public void update(ReservaVO result){
		// Preserva para uso futuro
		this.rvo = result;
		
		dataCheckin.setValue(result.dataCheckin);
		dataCheckout.setValue(result.dataCheckout);
	}

	@Override
	public void clear() {
	}

	@Override
	public void notifier(ReservaVO vo) {
		// TODO Auto-generated method stub
		
	}


}
