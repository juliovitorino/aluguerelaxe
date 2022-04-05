package br.com.jcv.aluguerelaxe.client.administracao.console.imovelpropriedade;

import br.com.jcv.aluguerelaxe.client.administracao.login.SessaoVO;
import br.com.jcv.aluguerelaxe.client.componente.autoajuda.AutoAjudaListaNumeradaListBox;
import br.com.jcv.aluguerelaxe.client.componente.autoajuda.AutoAjudaSimNaoListBox;
import br.com.jcv.aluguerelaxe.client.componente.autoajuda.AutoAjudaTextArea;
import br.com.jcv.aluguerelaxe.client.componente.autoajuda.AutoAjudaTextBox;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.listbox.SituacaoImovelListBox;
import br.com.jcv.aluguerelaxe.client.componente.listbox.TipoPropriedadeListBox;
import br.com.jcv.aluguerelaxe.client.imovel.ImovelVO;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ImovelPropriedadeFormComposite extends FormComposite<ImovelVO> {
	
	private final static String AUTO_AJUDA_QTDE_QUARTOS = "<B>Qtde de Quartos</B>" + 
	"<p>Selecione a quantidade de quartos<br/> existentes no im\u00f3vel.</p>";

	private final static String AUTO_AJUDA_QTDE_SUITES = "<B>Qtde de Suites</B>" + 
	"<p>Selecione a quantidade de su\u00edtes<br/> existentes no im\u00f3vel.</p>";

	private final static String AUTO_AJUDA_QTDE_BANHEIROS = "<B>Qtde de Banheiros</B>" + 
	"<p>Selecione a quantidade de banheiros<br/> existentes no im\u00f3vel.</p>";

	private final static String AUTO_AJUDA_QTDE_CAPACIDADE = "<B>Capacidade de Pessoas</B>" + 
	"<p>Selecione a quantidade m\u00e1xima<br/> de pessoas permitidas no im\u00f3vel.</p>";

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

	private final static String AUTO_AJUDA_DESCRICAO_GERAL = "<B>Descri\u00e7\u00e3o Geral</B>" + 
	"<p>Forne\u00e7a uma descri\u00e7\u00e3o geral sobre o im\u00f3vel.<br/>Veja o exemplo abaixo:</p>" + 
	"<p>Apartamentos mobiliados (com TV, antena parab\u00f3lica, geladeira, <br/>" +
	"ventiladores, cozinha americana completa, sala de estar com jogo <br/>" +
	"de sof\u00e1 e mesa, etc) para 06 a 10 pessoas pr\u00f3ximo da praia, com garagem<br/>" +
	"para 16 carros, piscina adulto e crian\u00e7a, \u00e1rea para churrasca, mesa ping-pong,<br/>" +
	"sinuca, etc</p>";

	private final static String AUTO_AJUDA_DESCRICAO_QUARTOS = "<B>Descri\u00e7\u00e3o dos Quartos</B>" + 
	"<p>Forne\u00e7a uma descri\u00e7\u00e3o dos quartos do im\u00f3vel.<br/>Veja o exemplo abaixo:</p>" + 
	"<p>Apartamento com 2 quartos possui : <br/>" +
	"Quarto Casal - Com uma cama de casal e um beliche, <br/>" +
	"ar condicionado e ventilador tuf\u00e3o,Piso cer\u00e2mico  <br/>" +
	"com TV e outro quarto com uma cama de casal e 2 beliches <br/>" +
	"e ventilador de teto. Apartamento 1 quarto : Quarto com uma <br/>" +
	"cama de casal e dois beliches e sala revers\u00edvel  <br/>" +
	"com um ou dois beliches.</p>";

	private final static String AUTO_AJUDA_DESCRICAO_ARREDORES = "<B>Descri\u00e7\u00e3o dos Arredores</B>" + 
	"<p>Forne\u00e7a uma descri\u00e7\u00e3o dos arredores do im\u00f3vel.<br/>Veja o exemplo abaixo:</p>" + 
	"<p>Possui mercearia (casa do p\u00e3o) no quarteir\u00e3o do condom\u00ednio. <br/>" +
	"pra\u00e7a de esportes, igreja, bosque com pista de cooper, <br/>" +
	"pr\u00f3ximo \u00e0 praia etc.</p>";

	private final static String AUTO_AJUDA_DESCRICAO_TITULO_ANUNCIO = "<B>T\u00edtulo do An\u00fancio</B>" + 
	"<p>Forne\u00e7a uma descri\u00e7\u00e3o impressionante do im\u00f3vel.<br/>Veja o exemplo abaixo:</p>" + 
	"<p>FANTASTICOS APARTAMENTOS MOBILIADOS EM PORTO SEGURO</p>";

	private final static String AUTO_AJUDA_OBSERVACAO = "<B>Observa\u00e7\u00f5es Gerais</B>" + 
	"<p>Descreva qualquer observa\u00e7\u00e3o que ache necess\u00e1rio.<br/>informar ao cliente na ficha do im\u00f3vel:<br/>Veja o exemplo abaixo:</p>" + 
	"<p>N\u00e3o aceitamos Animais e tem cobran\u00e7a de tarifa de limpeza.</p>";

	private final static String AUTO_AJUDA_TARIFA_BASICA = "<B>Valor Diária Básica</B>" + 
	"<p>Descreva o valor da diária básica que você cobra em períodos normais.";


	private Label txtId;
	private Label txtDataCadastro;
	private Label txtDataAtualizacao;
	private Label txtPlanoImovel;
	private AutoAjudaListaNumeradaListBox lstQtdeQuartos;
	private AutoAjudaListaNumeradaListBox lstQtdeSuites;
	private AutoAjudaListaNumeradaListBox lstQtdeBanheiros;
	private AutoAjudaListaNumeradaListBox lstCapacidade;
	private AutoAjudaTextArea taDescricaoGeralImovel;
	private AutoAjudaTextArea taDescricaoQuartos;
	private AutoAjudaTextArea taDescricaoArredores;
	private AutoAjudaTextArea taDescricaoTituloAnuncio;
	private AutoAjudaTextArea taObs;
	private AutoAjudaSimNaoListBox lstMostraTabela;
	private SituacaoImovelListBox lstSituacao;
	private AutoAjudaSimNaoListBox lstDentroCondominio;
	private Label txtUltimaVisita;
	private Label txtTotalVisitas;
	private AutoAjudaSimNaoListBox lstPermiteAlugar;
	private TipoPropriedadeListBox tipoPropriedade;
	private AutoAjudaTextBox tbTarifaBasica;

	private SessaoVO sessaovo = null;
	
	public ImovelPropriedadeFormComposite(SessaoVO sessaovo) {
		super();
		this.sessaovo = sessaovo;
	}

	public void init() {
		txtId = new Label();
		txtDataCadastro = new Label();
		txtDataAtualizacao = new Label();
		txtPlanoImovel = new Label();
		lstQtdeQuartos = new AutoAjudaListaNumeradaListBox(1, 20, ImovelPropriedadeFormComposite.AUTO_AJUDA_QTDE_QUARTOS);
		lstQtdeSuites = new AutoAjudaListaNumeradaListBox(0, 20, ImovelPropriedadeFormComposite.AUTO_AJUDA_QTDE_SUITES);
		lstQtdeBanheiros = new AutoAjudaListaNumeradaListBox(1, 20, ImovelPropriedadeFormComposite.AUTO_AJUDA_QTDE_BANHEIROS);
		lstCapacidade = new AutoAjudaListaNumeradaListBox(1, 20, ImovelPropriedadeFormComposite.AUTO_AJUDA_QTDE_CAPACIDADE);
		taDescricaoGeralImovel = new AutoAjudaTextArea(ImovelPropriedadeFormComposite.AUTO_AJUDA_DESCRICAO_GERAL);
		taDescricaoQuartos = new AutoAjudaTextArea(ImovelPropriedadeFormComposite.AUTO_AJUDA_DESCRICAO_QUARTOS);
		taDescricaoArredores =new AutoAjudaTextArea(ImovelPropriedadeFormComposite.AUTO_AJUDA_DESCRICAO_ARREDORES);
		taDescricaoTituloAnuncio = new AutoAjudaTextArea(ImovelPropriedadeFormComposite.AUTO_AJUDA_DESCRICAO_TITULO_ANUNCIO);
		taObs = new AutoAjudaTextArea(ImovelPropriedadeFormComposite.AUTO_AJUDA_OBSERVACAO);
		lstMostraTabela = new AutoAjudaSimNaoListBox(ImovelPropriedadeFormComposite.AUTO_AJUDA_SIM_NAO_MOSTRA_TABELA);
		lstSituacao = new SituacaoImovelListBox();
		lstDentroCondominio = new AutoAjudaSimNaoListBox(ImovelPropriedadeFormComposite.AUTO_AJUDA_SIM_NAO_CONDOMINIO);
		txtUltimaVisita = new Label();
		txtTotalVisitas = new Label();
		lstPermiteAlugar = new AutoAjudaSimNaoListBox(ImovelPropriedadeFormComposite.AUTO_AJUDA_SIM_NAO_PERMITE_ALUGAR);
		tipoPropriedade = new TipoPropriedadeListBox();
		tbTarifaBasica = new AutoAjudaTextBox(ImovelPropriedadeFormComposite.AUTO_AJUDA_TARIFA_BASICA);
		
	}
	
	public Widget render() {
		VerticalPanel vp = new VerticalPanel();
		Grid grid = new Grid(21,2);

		// Configura tamanhos
		taDescricaoGeralImovel.setSize("560px", "100px");
		taDescricaoQuartos.setSize("560px", "100px");
		taDescricaoArredores.setSize("560px", "100px");
		taDescricaoTituloAnuncio.setSize("560px", "100px");
		taObs.setSize("560px", "100px");

		// Posiciona campos no grid
		int linha = -1;
		
		grid.setWidget(++linha, 0, new Label("ID:"));
		grid.setWidget(linha, 1, txtId);
		
		grid.setWidget(++linha, 0, new Label("Cadastro em:"));
		grid.setWidget(linha, 1, txtDataCadastro);
		
		grid.setWidget(++linha, 0, new Label("Atualizado em:"));
		grid.setWidget(linha, 1, txtDataAtualizacao );

		grid.setWidget(++linha, 0, new Label("Plano:"));
		grid.setWidget(linha, 1, txtPlanoImovel );
		
		grid.setWidget(++linha, 0, new Label("Qtde Quartos:"));
		grid.setWidget(linha, 1, lstQtdeQuartos);
		
		grid.setWidget(++linha, 0, new Label("Qtde Su\u00edtes:"));
		grid.setWidget(linha, 1, lstQtdeSuites);
		
		grid.setWidget(++linha, 0, new Label("Qtde Banheiros:"));
		grid.setWidget(linha, 1, lstQtdeBanheiros);
		
		grid.setWidget(++linha, 0, new Label("Capacidade M\u00e1xima:"));
		grid.setWidget(linha, 1, lstCapacidade);
		
		grid.setWidget(++linha, 0, new Label("T\u00edtulo do An\u00fancio do Im\u00f3vel"));
		grid.setWidget(linha, 1, taDescricaoTituloAnuncio);
		
		grid.setWidget(++linha, 0, new Label("Descri\u00e7\u00e3o Geral do Im\u00f3vel:"));
		grid.setWidget(linha, 1,taDescricaoGeralImovel );
		
		grid.setWidget(++linha, 0, new Label("Descri\u00e7\u00e3o dos Quartos:"));
		grid.setWidget(linha, 1,taDescricaoQuartos );
		
		grid.setWidget(++linha, 0, new Label("Descri\u00e7\u00e3o dos Arredores"));
		grid.setWidget(linha, 1,taDescricaoArredores );
		
		grid.setWidget(++linha, 0, new Label("Observa\u00e7\u00f5es"));
		grid.setWidget(linha, 1,taObs );

		grid.setWidget(++linha, 0, new Label("Mostra Tabela de Pre\u00e7os"));
		grid.setWidget(linha, 1, lstMostraTabela);

		grid.setWidget(++linha, 0, new Label("Valor da Di\u00e1ria B\u00e1sica"));
		grid.setWidget(linha, 1, tbTarifaBasica);
		
		grid.setWidget(++linha, 0, new Label("Situa\u00e7\u00e3o"));
		grid.setWidget(linha, 1, lstSituacao);
		
		grid.setWidget(++linha, 0, new Label("Im\u00f3vel fica em condom\u00ednio:"));
		grid.setWidget(linha, 1, lstDentroCondominio);
		
		grid.setWidget(++linha, 0, new Label("\u00daltima visita:"));
		grid.setWidget(linha, 1,txtUltimaVisita );
		
		grid.setWidget(++linha, 0, new Label("Total de Visitas:"));
		grid.setWidget(linha, 1, txtTotalVisitas);
		
		grid.setWidget(++linha, 0, new Label("Permite Alugar pra Festas:"));
		grid.setWidget(linha, 1, lstPermiteAlugar);
		
		grid.setWidget(++linha, 0, new Label("Tipo de Propriedade:"));
		grid.setWidget(linha, 1, tipoPropriedade);

		// Adiciona a grid ao VerticalPanel
		vp.add(grid);
		
		return vp;
	}
	
	@Override
	public ImovelVO getVO() {
		ImovelVO vo = new ImovelVO();
		vo.descricaoArredores = getTaDescricaoArredores().getWidgetUI().getValue();
		vo.descricaoGeral = getTaDescricaoGeralImovel().getWidgetUI().getValue();
		vo.descricaoQuartos = getTaDescricaoQuartos().getWidgetUI().getValue();
		vo.descricaoTituloAnuncio = getTaDescricaoTituloAnuncio().getWidgetUI().getValue();
		vo.valorTarifaBasica = getTbTarifaBasica().getWidgetUI().getValue();
		try {
			vo.id = Long.valueOf(getTxtId().getText());
		} catch (NumberFormatException e) {
			vo.id = 0;
		}
		vo.idCliente = this.sessaovo.clientevo.id;
		vo.indicadorMostraTabelaPreco = getLstMostraTabela().getWidgetUI().getValue(getLstMostraTabela().getWidgetUI().getSelectedIndex());
		vo.indicadorPermiteAlugarFestas = getLstPermiteAlugar().getWidgetUI().getValue(getLstPermiteAlugar().getWidgetUI().getSelectedIndex());
		vo.indicadorTipoPropriedade = getTipoPropriedade().getValue(getTipoPropriedade().getSelectedIndex());
		vo.indicadorStatus = getLstSituacao().getValue(getLstSituacao().getSelectedIndex());
		vo.indicadorCondominio = getLstDentroCondominio().getWidgetUI().getValue(getLstDentroCondominio().getWidgetUI().getSelectedIndex());
		vo.observacoes = getTaObs().getWidgetUI().getValue();
		vo.qtdeBanheiros = Integer.valueOf(getLstQtdeBanheiros().getWidgetUI().getItemText(getLstQtdeBanheiros().getWidgetUI().getSelectedIndex()));
		vo.qtdeCapacidade = Integer.valueOf(getLstCapacidade().getWidgetUI().getItemText(getLstCapacidade().getWidgetUI().getSelectedIndex()));
		vo.qtdeQuartos = Integer.valueOf(getLstQtdeQuartos().getWidgetUI().getItemText(getLstQtdeQuartos().getWidgetUI().getSelectedIndex()));
		vo.qtdeSuites = Integer.valueOf(getLstQtdeSuites().getWidgetUI().getItemText(getLstQtdeSuites().getWidgetUI().getSelectedIndex()));
		return vo; 
	}
	
	public Label getTxtId() {
		return txtId;
	}

	public void setTxtId(Label txtId) {
		this.txtId = txtId;
	}

	public Label getTxtPlanoImovel() {
		return txtPlanoImovel;
	}

	public void setTxtPlanoImovel(Label txtPlanoImovel) {
		this.txtPlanoImovel = txtPlanoImovel;
	}

	public AutoAjudaListaNumeradaListBox getLstQtdeQuartos() {
		return lstQtdeQuartos;
	}

	public void setLstQtdeQuartos(AutoAjudaListaNumeradaListBox lstQtdeQuartos) {
		this.lstQtdeQuartos = lstQtdeQuartos;
	}

	public AutoAjudaListaNumeradaListBox getLstQtdeSuites() {
		return lstQtdeSuites;
	}

	public void setLstQtdeSuites(AutoAjudaListaNumeradaListBox lstQtdeSuites) {
		this.lstQtdeSuites = lstQtdeSuites;
	}

	public AutoAjudaListaNumeradaListBox getLstQtdeBanheiros() {
		return lstQtdeBanheiros;
	}

	public void setLstQtdeBanheiros(AutoAjudaListaNumeradaListBox lstQtdeBanheiros) {
		this.lstQtdeBanheiros = lstQtdeBanheiros;
	}

	public AutoAjudaListaNumeradaListBox getLstCapacidade() {
		return lstCapacidade;
	}

	public void setLstCapacidade(AutoAjudaListaNumeradaListBox lstCapacidade) {
		this.lstCapacidade = lstCapacidade;
	}

	public AutoAjudaTextArea getTaDescricaoGeralImovel() {
		return taDescricaoGeralImovel;
	}

	public void setTaDescricaoGeralImovel(AutoAjudaTextArea taDescricaoGeralImovel) {
		this.taDescricaoGeralImovel = taDescricaoGeralImovel;
	}
	
	public AutoAjudaTextBox getTbTarifaBasica() {
		return this.tbTarifaBasica;
	}

	public AutoAjudaTextArea getTaDescricaoQuartos() {
		return taDescricaoQuartos;
	}

	public void setTaDescricaoQuartos(AutoAjudaTextArea taDescricaoQuartos) {
		this.taDescricaoQuartos = taDescricaoQuartos;
	}

	public AutoAjudaTextArea getTaDescricaoArredores() {
		return taDescricaoArredores;
	}

	public void setTaDescricaoArredores(AutoAjudaTextArea taDescricaoArredores) {
		this.taDescricaoArredores = taDescricaoArredores;
	}

	public AutoAjudaTextArea getTaDescricaoTituloAnuncio() {
		return taDescricaoTituloAnuncio;
	}

	public void setTaDescricaoTituloAnuncio(AutoAjudaTextArea taDescricaoTituloAnuncio) {
		this.taDescricaoTituloAnuncio = taDescricaoTituloAnuncio;
	}

	public AutoAjudaTextArea getTaObs() {
		return taObs;
	}

	public void setTaObs(AutoAjudaTextArea taObs) {
		this.taObs = taObs;
	}

	public AutoAjudaSimNaoListBox getLstMostraTabela() {
		return lstMostraTabela;
	}

	public void setLstMostraTabela(AutoAjudaSimNaoListBox lstMostraTabela) {
		this.lstMostraTabela = lstMostraTabela;
	}

	public SituacaoImovelListBox getLstSituacao() {
		return lstSituacao;
	}

	public void setLstSituacao(SituacaoImovelListBox lstSituacao) {
		this.lstSituacao = lstSituacao;
	}

	public AutoAjudaSimNaoListBox getLstDentroCondominio() {
		return lstDentroCondominio;
	}

	public void setLstDentroCondominio(AutoAjudaSimNaoListBox lstDentroCondominio) {
		this.lstDentroCondominio = lstDentroCondominio;
	}

	public AutoAjudaSimNaoListBox getLstPermiteAlugar() {
		return lstPermiteAlugar;
	}

	public void setLstPermiteAlugar(AutoAjudaSimNaoListBox lstPermiteAlugar) {
		this.lstPermiteAlugar = lstPermiteAlugar;
	}

	public TipoPropriedadeListBox getTipoPropriedade() {
		return tipoPropriedade;
	}

	public void setTipoPropriedade(TipoPropriedadeListBox tipoPropriedade) {
		this.tipoPropriedade = tipoPropriedade;
	}

	@Override
	public void update(ImovelVO vo) {
		getTbTarifaBasica().getWidgetUI().setValue(vo.valorTarifaBasica);
		getTaDescricaoArredores().getWidgetUI().setValue(vo.descricaoArredores);
		getTaDescricaoGeralImovel().getWidgetUI().setValue(vo.descricaoGeral);
		getTaDescricaoQuartos().getWidgetUI().setValue(vo.descricaoQuartos);
		getTaDescricaoTituloAnuncio().getWidgetUI().setValue(vo.descricaoTituloAnuncio);
		getTaObs().getWidgetUI().setValue(vo.observacoes);
		getTxtId().setText(String.valueOf(vo.id));
		getLstMostraTabela().getWidgetUI().setSelectedIndex(getLstMostraTabela().getWidgetUI().getSelectedItemValue(String.valueOf(vo.indicadorMostraTabelaPreco)));
		getLstPermiteAlugar().getWidgetUI().setSelectedIndex(getLstPermiteAlugar().getWidgetUI().getSelectedItemValue(String.valueOf(vo.indicadorPermiteAlugarFestas)));
		getTipoPropriedade().setSelectedIndex(getTipoPropriedade().getSelectedItemValue(String.valueOf(vo.indicadorTipoPropriedade)));
		getLstQtdeBanheiros().getWidgetUI().setSelectedIndex(getLstQtdeBanheiros().getWidgetUI().getSelectedItemText(String.valueOf(vo.qtdeBanheiros)));
		getLstCapacidade().getWidgetUI().setSelectedIndex(getLstCapacidade().getWidgetUI().getSelectedItemText(String.valueOf(vo.qtdeCapacidade)));
		getLstQtdeQuartos().getWidgetUI().setSelectedIndex(getLstQtdeQuartos().getWidgetUI().getSelectedItemText(String.valueOf(vo.qtdeQuartos)));
		getLstQtdeSuites().getWidgetUI().setSelectedIndex(getLstQtdeSuites().getWidgetUI().getSelectedItemText(String.valueOf(vo.qtdeSuites)));
	}

	@Override
	public void clear() {
		getTaDescricaoArredores().getWidgetUI().setValue("");
		getTaDescricaoGeralImovel().getWidgetUI().setValue("");
		getTaDescricaoQuartos().getWidgetUI().setValue("");
		getTaDescricaoTituloAnuncio().getWidgetUI().setValue("");
		getTaObs().getWidgetUI().setValue("");
		getTxtId().setText("");
		getLstMostraTabela().getWidgetUI().setSelectedIndex(0);
		getLstPermiteAlugar().getWidgetUI().setSelectedIndex(0);
		getTipoPropriedade().setSelectedIndex(0);
		getLstQtdeBanheiros().getWidgetUI().setSelectedIndex(0);
		getLstCapacidade().getWidgetUI().setSelectedIndex(0);
		getLstQtdeQuartos().getWidgetUI().setSelectedIndex(0);
		getLstQtdeSuites().getWidgetUI().setSelectedIndex(0);
	}

	@Override
	public void notifier(ImovelVO vo) {
		// TODO Auto-generated method stub
		
	}

}
