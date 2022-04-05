package br.com.jcv.aluguerelaxe.client.administracao.console.desktop;

import br.com.jcv.aluguerelaxe.client.componente.griddataentry.ColumnWidget;
import br.com.jcv.aluguerelaxe.client.componente.griddataentry.GridDataEntry;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ContatoClienteVO;

public class ContatoAnuncianteGridDataEntry extends GridDataEntry<ContatoClienteVO> {

	private final static int POS_NOME = 0;
	private final static int POS_EMAIL = 1;
	private final static int POS_TELEFONE = 2;
	private final static int POS_UF = 3;
	private final static int POS_CIDADE = 4;
	private final static int POS_CHEGADAPREVISTA = 5;
	private final static int POS_DATA_CONTATO = 6;

	
	public ContatoAnuncianteGridDataEntry() {
		super();
		this.setStylePrimaryName("gwt-ContatoAnuncianteGridDataEntry");
		this.addColunaGridDataEntry(new ColumnWidget("Nome do Cliente", ColumnWidget.CW_LABEL, "180px"));
		this.addColunaGridDataEntry(new ColumnWidget("E-mail", ColumnWidget.CW_LABEL, "180px"));
		this.addColunaGridDataEntry(new ColumnWidget("Telefone", ColumnWidget.CW_LABEL, "90px"));
		this.addColunaGridDataEntry(new ColumnWidget("Cidade", ColumnWidget.CW_LABEL, "120px"));
		this.addColunaGridDataEntry(new ColumnWidget("UF", ColumnWidget.CW_LABEL, "40px"));
		this.addColunaGridDataEntry(new ColumnWidget("Chegada", ColumnWidget.CW_LABEL, "80px"));
		this.addColunaGridDataEntry(new ColumnWidget("Data Contato", ColumnWidget.CW_LABEL, "160px"));
		this.init(4);
	}

	@Override
	public ContatoClienteVO getVO(String conteudoColunas) {
		// Nao aplicado neste contexto
		return null;
	}

	@Override
	public String update(ContatoClienteVO vo, int index) {
		String retorno = "*";
		switch(index){
			case POS_NOME:
				retorno = vo.nome;
				break;
			case POS_EMAIL:
				retorno = vo.email;
				break;
			case POS_TELEFONE:
				retorno = "(" + vo.ddd + ")" + vo.telefone;
				break;
			case POS_CIDADE:
				retorno = vo.cidade;
				break;
			case POS_UF:
				retorno = vo.uf;
				break;
			case POS_CHEGADAPREVISTA:
				retorno = vo.chegadaPrevistaStr;
				break;
			case POS_DATA_CONTATO:
				retorno = vo.dataCadastro;
				break;
		}
		return retorno;
	}

}
