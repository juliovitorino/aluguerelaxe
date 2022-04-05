package br.com.jcv.aluguerelaxe.client.administracao.console.imovelpropriedade;

import br.com.jcv.aluguerelaxe.client.componente.autoajuda.AutoAjudaTextArea;
import br.com.jcv.aluguerelaxe.client.componente.autoajuda.AutoAjudaTextBox;
import br.com.jcv.aluguerelaxe.client.componente.date.DateAdvancedWidget;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.imovel.tabelapreco.TabelaPrecoVO;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

/**
 * Formulário para entrada de dados de tabela de preços do imóvel
 * 
 * @author julio
 *
 */
public class TabelaPrecoFormComposite extends FormComposite<TabelaPrecoVO> {

	private final static String AUTO_AJUDA_PERIODO = "<B>Per\u00edodo</B>" + 
	"<p>Digite o per\u00edodo de acordo com os exemplos abaixo:<br/><br/>" +
	"Carnaval; Ano Novo; Semana Santa</p>";
	 
	private final static String AUTO_AJUDA_VALOR = "<B>Valor</B>" + 
	"<p>Digite o valor a ser cobrado no per\u00edodo informado</p>";

	private final static String AUTO_AJUDA_MINIMO_DE = "<B>M\u00ednimo de</B>" + 
	"<p>Este campo \u00e9 um campo livre e permite que voc\u00ea<br/>" +
	"informe na ficha de imóvel qual \u00e9 a condi\u00e7\u00e3o m\u00ednima de <br/>" +
	"ocupa\u00e7\u00e3o do seu imóvel. Digite as informa\u00e7ões de acordo <br/>" +
	"com os exemplos abaixo:<br/><br/>" +
	"2 Pessoas Adultas; 4 Dias</p>";

	private final static String AUTO_AJUDA_OBS = "<B>Observa\u00e7\u00e3o</B>" + 
	"<p>Este campo \u00e9 um campo livre e permite que voc\u00ea<br/>" +
	"informe na ficha de imóvel qualquer observa\u00e7\u00e3o que<br/>" +
	"ache relevante informar para seu cliente. Digite as informa\u00e7ões de acordo" +
	"com os exemplos abaixo:<br/><br/>" +
	"Cobro taxa de limpeza de 5%; N\u00e3o aceitamos animais; etc.</p>";

	private AutoAjudaTextBox periodo;
	private DateBox periodoDe;
	private DateBox periodoAte;
	private AutoAjudaTextBox valor;
	private AutoAjudaTextBox minimoDe;
	private AutoAjudaTextArea observacao;
	private VerticalPanel vpObservacao;
	
	public TabelaPrecoFormComposite() {
		super();
	}

	@Override
	public void init() {
		 periodo = new AutoAjudaTextBox(TabelaPrecoFormComposite.AUTO_AJUDA_PERIODO);
		periodoDe = new DateBox();
		periodoAte = new DateBox();
		valor = new AutoAjudaTextBox(TabelaPrecoFormComposite.AUTO_AJUDA_VALOR);
		minimoDe = new AutoAjudaTextBox(TabelaPrecoFormComposite.AUTO_AJUDA_MINIMO_DE);
		observacao = new AutoAjudaTextArea(TabelaPrecoFormComposite.AUTO_AJUDA_OBS);
		vpObservacao = new VerticalPanel();
	}
	
	@Override
	public Widget render() {
		Image imgobs = new Image("images/16x16/notebook_edit.png");
		imgobs.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				vpObservacao.setVisible(true);
			}
		});
		
		VerticalPanel vp = new VerticalPanel();
		
		// Cria grid com campos
		Grid grid = new Grid(2, 6);
		grid.setWidget(0, 0, new Label("Per\u00edodo"));
		grid.setWidget(0, 1, new Label("De"));
		grid.setWidget(0, 2, new Label("At\u00e9"));
		grid.setWidget(0, 3, new Label("Valor"));
		grid.setWidget(0, 4, new Label("M\u00ednimo de"));
		grid.setWidget(0, 5, imgobs);
		
		grid.setWidget(1, 0, periodo);
		grid.setWidget(1, 1, periodoDe);
		grid.setWidget(1, 2, periodoAte);
		grid.setWidget(1, 3, valor);
		grid.setWidget(1, 4, minimoDe);
		
		// Formata DateBox
		periodoDe.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("dd/MM/yyyy")));
		periodoAte.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("dd/MM/yyyy")));

		// Configura tamanhos
		observacao.setSize("670px", "100px");

		// Cria espaço de observações
		vpObservacao.setVisible(false);
		vpObservacao.add(new Label("Observa\u00e7\u00f6es"));
		vpObservacao.add(observacao);
		
		// adiciona componentes ao painel
		
		vp.add(grid);
		vp.add(vpObservacao);
		
		return vp;

	}

	public AutoAjudaTextBox getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo.getWidgetUI().setValue(periodo);
	}

	public DateBox getPeriodoDe() {
		return periodoDe;
	}

	public void setPeriodoDe(DateBox periodoDe) {
		this.periodoDe = periodoDe;
	}

	public DateBox getPeriodoAte() {
		return periodoAte;
	}

	public void setPeriodoAte(DateBox periodoAte) {
		this.periodoAte = periodoAte;
	}

	public AutoAjudaTextBox getValor() {
		return valor;
	}

	public void setValor(AutoAjudaTextBox valor) {
		this.valor = valor;
	}

	public AutoAjudaTextBox getMinimoDe() {
		return minimoDe;
	}

	public void setMinimoDe(AutoAjudaTextBox minimoDe) {
		this.minimoDe = minimoDe;
	}

	public AutoAjudaTextArea getObservacao() {
		return observacao;
	}

	public void setObservacao(AutoAjudaTextArea observacao) {
		this.observacao = observacao;
	}
	
	public TabelaPrecoVO getVO() {
		TabelaPrecoVO tpvo = new TabelaPrecoVO();
		tpvo.periodo = periodo.getWidgetUI().getValue();
		tpvo.dataInicio = periodoDe.getValue();
		tpvo.dataFim = periodoAte.getValue();
		tpvo.valorTabela = Double.valueOf(valor.getWidgetUI().getValue());
		tpvo.minimoDe = minimoDe.getWidgetUI().getValue();
		tpvo.observacao = observacao.getWidgetUI().getValue();
		return tpvo;
	}

	@Override
	public void update(TabelaPrecoVO vo) {
		periodo.getWidgetUI().setValue(vo.periodo);
		periodoDe.setValue(vo.dataInicio);
		periodoAte.setValue(vo.dataFim);
		valor.getWidgetUI().setValue(String.valueOf(vo.valorTabela));
		minimoDe.getWidgetUI().setValue(vo.minimoDe);
		observacao.getWidgetUI().setValue(vo.observacao);
		
	}

	@Override
	public void clear() {
		periodo.getWidgetUI().setValue("");
		valor.getWidgetUI().setValue("");
		minimoDe.getWidgetUI().setValue("");
		observacao.getWidgetUI().setValue("");
		//periodoDe.setDate("");
		//periodoAte.setDate("");
	}

	@Override
	public void notifier(TabelaPrecoVO vo) {
		// TODO Auto-generated method stub
		
	}
	
	
}
