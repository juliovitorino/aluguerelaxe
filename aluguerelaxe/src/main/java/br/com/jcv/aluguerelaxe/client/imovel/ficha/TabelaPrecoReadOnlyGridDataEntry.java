package br.com.jcv.aluguerelaxe.client.imovel.ficha;

import br.com.jcv.aluguerelaxe.client.componente.griddataentry.ColumnWidget;
import br.com.jcv.aluguerelaxe.client.componente.griddataentry.GridDataEntry;
import br.com.jcv.aluguerelaxe.client.imovel.tabelapreco.TabelaPrecoVO;

import com.google.gwt.user.client.ui.HTML;


public class TabelaPrecoReadOnlyGridDataEntry extends GridDataEntry<TabelaPrecoVO> {

	private final static int POS_PERIODO = 0;
	private final static int POS_DATA_INICIO = 1;
	private final static int POS_DATA_FIM = 2;
	private final static int POS_VALOR_TABELA = 3;
	private final static int POS_MINIMO = 4;
	private final static int POS_OBS = 5;
	
	public TabelaPrecoReadOnlyGridDataEntry() {
		super();
		this.setStylePrimaryName("gwt-tprogde");
		this.addColunaGridDataEntry(new ColumnWidget("Per\u00edodo", ColumnWidget.CW_LABEL, "200px"));
		this.addColunaGridDataEntry(new ColumnWidget("Data In\u00edcio", ColumnWidget.CW_LABEL, "90px"));
		this.addColunaGridDataEntry(new ColumnWidget("Data Fim", ColumnWidget.CW_LABEL, "90px"));
		this.addColunaGridDataEntry(new ColumnWidget("Valor", ColumnWidget.CW_LABEL, "80px"));
		this.addColunaGridDataEntry(new ColumnWidget("M\u00ednimo de", ColumnWidget.CW_LABEL, "160px"));
		this.addColunaGridDataEntry(new ColumnWidget("Observa\u00e7\u00e3o", ColumnWidget.CW_LABEL, "205px"));
		this.addCabecalhoItem(new HTML("<h1>TABELA DE PRE\u00c7O APLICADA AO IM\u00d3VEL</h1>"));
		this.init(7);
	}

	@Override
	public TabelaPrecoVO getVO(String conteudoColunas) {
		// Nao aplicavel neste contexto
		return null;
	}

	@Override
	public String update(TabelaPrecoVO vo, int index) {
		String retorno = "*";
		switch(index){
			case TabelaPrecoReadOnlyGridDataEntry.POS_PERIODO:
				retorno = vo.periodo;
				break;
			case TabelaPrecoReadOnlyGridDataEntry.POS_DATA_INICIO:
				retorno = vo.dataInicioStr;
				break;
			case TabelaPrecoReadOnlyGridDataEntry.POS_DATA_FIM:
				retorno = vo.dataFimStr;
				break;
			case TabelaPrecoReadOnlyGridDataEntry.POS_VALOR_TABELA:
				retorno = "R$ " + String.valueOf(vo.valorTabela);
				break;
			case TabelaPrecoReadOnlyGridDataEntry.POS_MINIMO:
				retorno = vo.minimoDe;
				break;
			case TabelaPrecoReadOnlyGridDataEntry.POS_OBS:
				retorno = vo.observacao;
				break;
		}
		return retorno;
	}

}
