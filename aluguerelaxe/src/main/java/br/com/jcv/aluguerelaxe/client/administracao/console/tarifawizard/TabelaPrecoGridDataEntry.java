package br.com.jcv.aluguerelaxe.client.administracao.console.tarifawizard;

import br.com.jcv.aluguerelaxe.client.componente.griddataentry.ColumnWidget;
import br.com.jcv.aluguerelaxe.client.componente.griddataentry.GridDataEntry;
import br.com.jcv.aluguerelaxe.client.imovel.tabelapreco.TabelaPrecoVO;

import com.google.gwt.i18n.client.DateTimeFormat;


public class TabelaPrecoGridDataEntry extends GridDataEntry<TabelaPrecoVO> {

	private final static int POS_PERIODO = 0;
	private final static int POS_DATA_INICIO = 1;
	private final static int POS_DATA_FIM = 2;
	private final static int POS_VALOR_TABELA = 3;
	private final static int POS_MINIMO = 4;
	private final static int POS_OBS = 5;
	
	public TabelaPrecoGridDataEntry() {
		super();
		this.setStylePrimaryName("gwt-TabelaPrecoGridDataEntry");
		this.addColunaGridDataEntry(new ColumnWidget("Per\u00edodo", ColumnWidget.CW_TEXTBOX, "230px"));
		this.addColunaGridDataEntry(new ColumnWidget("Data In\u00edcio (dd/mm/aaaa)", ColumnWidget.CW_TEXTBOX, "100px"));
		this.addColunaGridDataEntry(new ColumnWidget("Data Fim (dd/mm/aaaa)", ColumnWidget.CW_TEXTBOX, "100px"));
		this.addColunaGridDataEntry(new ColumnWidget("Valor", ColumnWidget.CW_TEXTBOX, "80px"));
		this.addColunaGridDataEntry(new ColumnWidget("M\u00ednimo de", ColumnWidget.CW_TEXTBOX, "160px"));
		this.addColunaGridDataEntry(new ColumnWidget("Observa\u00e7\u00e3o", ColumnWidget.CW_TEXTAREA, "205px"));
		
		this.init(7);
	}

	@Override
	public TabelaPrecoVO getVO(String conteudoColunas) {
		String[] conteudosplit = conteudoColunas.split("\\|");
		TabelaPrecoVO vo = new TabelaPrecoVO();
		DateTimeFormat fmt = DateTimeFormat.getFormat("dd/MM/yyyy");
		try {
			vo.periodo = conteudosplit[POS_PERIODO];
			vo.dataInicioStr = conteudosplit[POS_DATA_INICIO];
			vo.dataInicio = fmt.parse(vo.dataInicioStr);
			vo.dataFimStr = conteudosplit[POS_DATA_FIM];
			vo.dataFim = fmt.parse(vo.dataFimStr);
			vo.valorTabela = Double.valueOf(conteudosplit[POS_VALOR_TABELA]);
			vo.minimoDe = conteudosplit[POS_MINIMO];
			vo.observacao = conteudosplit[POS_OBS];
		} catch(NumberFormatException e) {
			vo = null;
		} catch(Exception e){
			vo = null;
		}
		return vo;
	}

	@Override
	public String update(TabelaPrecoVO vo, int index) {
		String retorno = "*";
		switch(index){
			case TabelaPrecoGridDataEntry.POS_PERIODO:
				retorno = vo.periodo;
				break;
			case TabelaPrecoGridDataEntry.POS_DATA_INICIO:
				retorno = vo.dataInicioStr;
				break;
			case TabelaPrecoGridDataEntry.POS_DATA_FIM:
				retorno = vo.dataFimStr;
				break;
			case TabelaPrecoGridDataEntry.POS_VALOR_TABELA:
				retorno = String.valueOf(vo.valorTabela);
				break;
			case TabelaPrecoGridDataEntry.POS_MINIMO:
				retorno = vo.minimoDe;
				break;
			case TabelaPrecoGridDataEntry.POS_OBS:
				retorno = vo.observacao;
				break;
		}
		return retorno;
	}

}
