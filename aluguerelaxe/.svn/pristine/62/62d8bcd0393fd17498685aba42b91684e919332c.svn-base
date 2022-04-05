package br.com.jcv.aluguerelaxe.client.componente.griddataentry;

import br.com.jcv.aluguerelaxe.client.cliente.TelefoneVO;

public class TelefoneGridDataEntry extends GridDataEntry<TelefoneVO> {

	private final static int POS_NOME_CONTATO = 0;
	private final static int POS_TIPO = 1;
	private final static int POS_DDD = 2;
	private final static int POS_TELEFONE = 3;
	private final static int POS_EXIBIR_FONE = 4;
	
	public TelefoneGridDataEntry() {
		super();
		this.setStylePrimaryName("gwt-TelefoneGridDataEntry");
		this.addColunaGridDataEntry(new ColumnWidget("Nome do Contato", ColumnWidget.CW_TEXTBOX, "250px"));
		this.addColunaGridDataEntry(new ColumnWidget("Tipo", ColumnWidget.CW_TELEFONE_LISTBOX, "100px"));
		this.addColunaGridDataEntry(new ColumnWidget("DDD", ColumnWidget.CW_TEXTBOX, "25px"));
		this.addColunaGridDataEntry(new ColumnWidget("Telefone", ColumnWidget.CW_TEXTBOX, "80px"));
		this.addColunaGridDataEntry(new ColumnWidget("Exibir?", ColumnWidget.CW_SIMNAO_LISTBOX, "60px"));
		this.init(4);
	}

	@Override
	public TelefoneVO getVO(String conteudoColunas) {
		String[] conteudosplit = conteudoColunas.split("\\|");
		TelefoneVO vo = null;
		if ((conteudosplit[POS_NOME_CONTATO].length() == 0) || 
			(conteudosplit[POS_DDD].length() == 0) ||
			(conteudosplit[POS_TELEFONE].length() == 0)
			){
			return vo;
		}
		vo = new TelefoneVO();
		vo.nomeContato = conteudosplit[POS_NOME_CONTATO];
		vo.indTipoTelefone = conteudosplit[POS_TIPO];
		vo.ddd = conteudosplit[POS_DDD];
		vo.telefone = conteudosplit[POS_TELEFONE];
		vo.indPermiteExibir = conteudosplit[POS_EXIBIR_FONE];
		return vo;
	}

	@Override
	public String update(TelefoneVO vo, int index) {
		String retorno = "*";
		switch(index){
			case TelefoneGridDataEntry.POS_NOME_CONTATO:
				retorno = vo.nomeContato;
				break;
			case POS_TIPO:
				retorno = vo.indTipoTelefone;
				break;
			case POS_DDD:
				retorno = vo.ddd;
				break;
			case POS_TELEFONE:
				retorno = vo.telefone;
				break;
			case POS_EXIBIR_FONE:
				retorno = vo.indPermiteExibir;
				break;
		}
		return retorno;
	}

}
