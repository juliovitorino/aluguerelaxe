package br.com.jcv.aluguerelaxe.client.administracao.console.imovelwizard;

import br.com.jcv.aluguerelaxe.client.componente.autoajuda.AutoAjudaSimNaoListBox;
import br.com.jcv.aluguerelaxe.client.componente.autoajuda.AutoAjudaTextBox;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.listbox.TipoPropriedadeListBox;
import br.com.jcv.aluguerelaxe.client.imovel.ImovelVO;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;



public class ImovelIndicadoresFormComposite extends FormComposite<ImovelVO> {
	

	private final static String AUTO_AJUDA_SIM_NAO_MOSTRA_TABELA = "<B>Mostrar Tabela de Pre\u00e7os</B>" + 
	"<p>Selecione a op\u00e7\u00e3o se o propriet\u00e1rio do im\u00f3vel deseja que sua<br/>" +
	"tabela de pre\u00e7os seja apresentada na ficha do im\u00f3vel para<br/>" +
	"seu cliente.</p>";

	private final static String AUTO_AJUDA_SIM_NAO_CONDOMINIO = "<B>Im\u00f3vel dentro do Condom\u00ednio</B>" + 
	"<p>Selecione a op\u00e7\u00e3o se o im\u00f3vel encontra-se dentro<br/>" +
	"de \u00e1rea de condom\u00ednio.</p>";

	private final static String AUTO_AJUDA_SIM_NAO_PERMITE_ALUGAR = "<B>Permite Alugar para Festas</B>" + 
	"<p>Selecione a op\u00e7\u00e3o se o im\u00f3vel poder\u00e1 ser<br/>" +
	"alugado para festas e outros eventos.</p>";

	private final static String AUTO_AJUDA_VALOR_DIARIA = "<B>Valor da di\u00e1ria / Pernoite</B>" + 
	"<p>Informe o valor da di\u00e1ria / Pernoite do im\u00f3vel.<br/>Não coloque símbolos, ponto ou vírgulas. Apenas números.</p>";

	private AutoAjudaSimNaoListBox lstMostraTabela;
	private AutoAjudaSimNaoListBox lstDentroCondominio;
	private AutoAjudaSimNaoListBox lstPermiteAlugar;
	private TipoPropriedadeListBox tipoPropriedade;
	private AutoAjudaTextBox valorDiaria;

	public ImovelIndicadoresFormComposite() {
		super();
		//this.setStylePrimaryName("gwt-ImovelIndicadoresFormComposite");
	}

	public void init() {
		lstMostraTabela = new AutoAjudaSimNaoListBox(ImovelIndicadoresFormComposite.AUTO_AJUDA_SIM_NAO_MOSTRA_TABELA);
		lstDentroCondominio = new AutoAjudaSimNaoListBox(ImovelIndicadoresFormComposite.AUTO_AJUDA_SIM_NAO_CONDOMINIO);
		lstPermiteAlugar = new AutoAjudaSimNaoListBox(ImovelIndicadoresFormComposite.AUTO_AJUDA_SIM_NAO_PERMITE_ALUGAR);
		tipoPropriedade = new TipoPropriedadeListBox();
		valorDiaria = new AutoAjudaTextBox(ImovelIndicadoresFormComposite.AUTO_AJUDA_VALOR_DIARIA);
	}
	
	public Widget render() {
		VerticalPanel vp = new VerticalPanel();
		Grid grid = new Grid(5,2);

		// Posiciona campos no grid
		int linha = -1;
		
		grid.setWidget(++linha, 0, new Label("Mostra Tabela de Pre\u00e7os"));
		grid.setWidget(linha, 1, lstMostraTabela);
		
		grid.setWidget(++linha, 0, new Label("Im\u00f3vel fica em condom\u00ednio:"));
		grid.setWidget(linha, 1, lstDentroCondominio);
		
		grid.setWidget(++linha, 0, new Label("Permite Alugar pra Festas:"));
		grid.setWidget(linha, 1, lstPermiteAlugar);
		
		grid.setWidget(++linha, 0, new Label("Tipo de Propriedade:"));
		grid.setWidget(linha, 1, tipoPropriedade);

		grid.setWidget(++linha, 0, new Label("Valor da di\u00e1ria / Pernoite:"));
		grid.setWidget(linha, 1, valorDiaria);

		// Adiciona a grid ao VerticalPanel
		vp.add(grid);
		
		return vp;
	}
	
	@Override
	public ImovelVO getVO() {
		ImovelVO vo = new ImovelVO();
		vo.indicadorMostraTabelaPreco = lstMostraTabela.getWidgetUI().getValue(lstMostraTabela.getWidgetUI().getSelectedIndex());
		vo.indicadorPermiteAlugarFestas = lstPermiteAlugar.getWidgetUI().getValue(lstPermiteAlugar.getWidgetUI().getSelectedIndex());
		vo.indicadorTipoPropriedade = tipoPropriedade.getValue(tipoPropriedade.getSelectedIndex());
		vo.indicadorCondominio = lstDentroCondominio.getWidgetUI().getValue(lstDentroCondominio.getWidgetUI().getSelectedIndex());
		vo.valorTarifaBasica = valorDiaria.getWidgetUI().getValue();
		return vo;
	}

	@Override
	public void update(ImovelVO vo) {
		//Não aplicável neste contexto
	}

	@Override
	public void clear() {
		//Não aplicável neste contexto
	}

	@Override
	public void notifier(ImovelVO vo) {
		// TODO Auto-generated method stub
		
	}

}
