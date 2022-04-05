package br.com.jcv.aluguerelaxe.client.administracao.console.imovelwizard;

import br.com.jcv.aluguerelaxe.client.administracao.console.imovelpropriedade.ImovelPropriedadeFormComposite;
import br.com.jcv.aluguerelaxe.client.componente.autoajuda.AutoAjudaListaNumeradaListBox;
import br.com.jcv.aluguerelaxe.client.componente.autoajuda.AutoAjudaTextBox;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.imovel.ImovelVO;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;


public class ImovelQuantidadesFormComposite extends FormComposite<ImovelVO> {
	
	private final static String AUTO_AJUDA_QTDE_QUARTOS = "<B>Qtde de Quartos</B>" + 
	"<p>Selecione a quantidade de quartos<br/> existentes no im\u00f3vel.</p>";

	private final static String AUTO_AJUDA_QTDE_SUITES = "<B>Qtde de Suites</B>" + 
	"<p>Selecione a quantidade de su\u00edtes<br/> existentes no im\u00f3vel.</p>";

	private final static String AUTO_AJUDA_QTDE_BANHEIROS = "<B>Qtde de Banheiros</B>" + 
	"<p>Selecione a quantidade de banheiros<br/> existentes no im\u00f3vel.</p>";

	private final static String AUTO_AJUDA_QTDE_CAPACIDADE = "<B>Capacidade de Pessoas</B>" + 
	"<p>Selecione a quantidade m\u00e1xima<br/> de pessoas permitidas no im\u00f3vel.</p>";
	
	//private final static String AUTO_AJUDA_TARIFA_BASICA = "<B>Valor Diária Básica</B>" + 
	//"<p>Descreva o valor da diária básica que você cobra em períodos normais.";


	private AutoAjudaListaNumeradaListBox lstQtdeQuartos;
	private AutoAjudaListaNumeradaListBox lstQtdeSuites;
	private AutoAjudaListaNumeradaListBox lstQtdeBanheiros;
	private AutoAjudaListaNumeradaListBox lstCapacidade;
	//private AutoAjudaTextBox tbTarifaBasica;

	
	public ImovelQuantidadesFormComposite() {
		super();
		//this.setStylePrimaryName("gwt-ImovelQuantidadesFormComposite");
	}

	public void init() {
		lstQtdeQuartos = new AutoAjudaListaNumeradaListBox(1, 20, ImovelQuantidadesFormComposite.AUTO_AJUDA_QTDE_QUARTOS);
		lstQtdeSuites = new AutoAjudaListaNumeradaListBox(0, 20, ImovelQuantidadesFormComposite.AUTO_AJUDA_QTDE_SUITES);
		lstQtdeBanheiros = new AutoAjudaListaNumeradaListBox(1, 20, ImovelQuantidadesFormComposite.AUTO_AJUDA_QTDE_BANHEIROS);
		lstCapacidade = new AutoAjudaListaNumeradaListBox(1, 100, ImovelQuantidadesFormComposite.AUTO_AJUDA_QTDE_CAPACIDADE);
		//tbTarifaBasica = new AutoAjudaTextBox(ImovelQuantidadesFormComposite.AUTO_AJUDA_TARIFA_BASICA);
	}
	
	public Widget render() {
		VerticalPanel vp = new VerticalPanel();
		Grid grid = new Grid(4,2);

		// Posiciona campos no grid
		int linha = -1;
		
		grid.setWidget(++linha, 0, new Label("Qtde Quartos:"));
		grid.setWidget(linha, 1, lstQtdeQuartos);
		
		grid.setWidget(++linha, 0, new Label("Qtde Su\u00edtes:"));
		grid.setWidget(linha, 1, lstQtdeSuites);
		
		grid.setWidget(++linha, 0, new Label("Qtde Banheiros:"));
		grid.setWidget(linha, 1, lstQtdeBanheiros);
		
		//grid.setWidget(++linha, 0, new Label("Valor da Diária Básica"));
		//grid.setWidget(linha, 1, tbTarifaBasica);
		
		// Adiciona a grid ao VerticalPanel
		vp.add(grid);
		
		return vp;
	}
	
	@Override
	public ImovelVO getVO() {
		ImovelVO vo = new ImovelVO();
		vo.qtdeBanheiros = Integer.valueOf(lstQtdeBanheiros.getWidgetUI().getItemText(lstQtdeBanheiros.getWidgetUI().getSelectedIndex()));
		vo.qtdeCapacidade = Integer.valueOf(lstCapacidade.getWidgetUI().getItemText(lstCapacidade.getWidgetUI().getSelectedIndex()));
		vo.qtdeQuartos = Integer.valueOf(lstQtdeQuartos.getWidgetUI().getItemText(lstQtdeQuartos.getWidgetUI().getSelectedIndex()));
		vo.qtdeSuites = Integer.valueOf(lstQtdeSuites.getWidgetUI().getItemText(lstQtdeSuites.getWidgetUI().getSelectedIndex()));
		//vo.valorTarifaBasica = tbTarifaBasica.getWidgetUI().getValue();
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
