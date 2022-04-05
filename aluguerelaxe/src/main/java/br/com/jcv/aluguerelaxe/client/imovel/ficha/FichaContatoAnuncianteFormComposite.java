package br.com.jcv.aluguerelaxe.client.imovel.ficha;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.Constantes;
import br.com.jcv.aluguerelaxe.client.cliente.TelefoneVO;
import br.com.jcv.aluguerelaxe.client.componente.autoajuda.AutoAjudaTextArea;
import br.com.jcv.aluguerelaxe.client.componente.autoajuda.AutoAjudaTextBox;
import br.com.jcv.aluguerelaxe.client.componente.date.DateBoxWidget;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.listbox.CidadesListBox;
import br.com.jcv.aluguerelaxe.client.componente.listbox.ListaNumeradaListBox;
import br.com.jcv.aluguerelaxe.client.componente.listbox.ModoPublicidadeListBox;
import br.com.jcv.aluguerelaxe.client.componente.listbox.PaisesListBox;
import br.com.jcv.aluguerelaxe.client.componente.listbox.UFListBox;
import br.com.jcv.aluguerelaxe.client.componente.notificacao.AreaNotificacao;
import br.com.jcv.aluguerelaxe.client.modopublicidade.ModoPublicidadeVO;

import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class FichaContatoAnuncianteFormComposite extends FormComposite<ContatoClienteVO> {

	private static final String AA_NOME = "Informe seu nome para contato com o anunciante do im\u00f3vel";
	private static final String AA_EMAIL = "Informe seu email para contato com o anunciante do im\u00f3vel";
	private static final String AA_DDD = "Informe seu DDD para contato com o anunciante do im\u00f3vel";
	private static final String AA_TELEFONE = "Informe seu telefone para contato com o anunciante do im\u00f3vel";
	private static final String AA_MAISINFO = "Solicite aqui maiores informações ao anunciante";
	
	/*
	private static final String TEXTO_PADRAO_CONTATO = "Ol\u00e1, gostei muito do im\u00f3vel. Poderia me mandar mais fotos recentes? " +
	"Gostaria tamb\u00e9m de informa\u00e7\u00f5es sobre pessoas que j\u00e1 o alugaram, pre\u00e7os, taxas e " +
	"quaisquer outras condi\u00e7\u00f5es para poder alugar seu im\u00f3vel.";
	*/
	
	private long idCodigoImovel;
	private AutoAjudaTextBox nome;
	private AutoAjudaTextBox email;
	private AutoAjudaTextBox ddd;
	private AutoAjudaTextBox telefone;
	private UFListBox uf;
	private CidadesListBox cidade;
	private PaisesListBox pais;
	private ListaNumeradaListBox qtdeAdultos;
	private ListaNumeradaListBox qtdeCriancas;
	private DateBoxWidget chegadaPrevista;
	private DateBoxWidget partidaPrevista;
	private AutoAjudaTextArea informacoesAdicionais;
	private AreaNotificacao an;
	private ModoPublicidadeListBox mplb;
	
	private HTML dadosAnunciante;

	@Override
	public ContatoClienteVO getVO() {
		ContatoClienteVO ccvo = new ContatoClienteVO();
		ccvo.idImovel = idCodigoImovel;
		ccvo.nome = nome.getWidgetUI().getText();
		ccvo.email = email.getWidgetUI().getText();
		ccvo.ddd = ddd.getWidgetUI().getText();
		ccvo.telefone = telefone.getWidgetUI().getText();
		ccvo.idImovel = idCodigoImovel;
		try {
			ccvo.cidade = cidade.getItemText(cidade.getSelectedIndex());
		} catch(IndexOutOfBoundsException e ) {
			ccvo.cidade = "-1";
		}
		ccvo.modopublicidade = new ModoPublicidadeVO(); 
		try {
			ccvo.modopublicidade.id = Long.valueOf(mplb.getValue(mplb.getSelectedIndex()));
		} catch(IndexOutOfBoundsException e ) {
			ccvo.modopublicidade.id = -1;
		}
		try {
			ccvo.uf = uf.getValue(uf.getSelectedIndex());
		} catch (IndexOutOfBoundsException e) {
			ccvo.uf = "-1";
		}
		ccvo.pais = pais.getValue(pais.getSelectedIndex());
		ccvo.qtdeAdultos = Integer.valueOf(qtdeAdultos.getValue(qtdeAdultos.getSelectedIndex()));
		ccvo.qtdeCriancas = Integer.valueOf(qtdeCriancas.getValue(qtdeCriancas.getSelectedIndex()));
		ccvo.chegadaPrevista = chegadaPrevista.getValue();
		ccvo.partidaPrevista = partidaPrevista.getValue();
		ccvo.informacoesAdicionais = informacoesAdicionais.getWidgetUI().getText();
		return ccvo;
	}

	public void update(ImovelFichaCompletaVO vo) {
		this.idCodigoImovel = vo.imovel.id;
		/*
		String infoAnunciante = "";
		
		if ((vo.imovelPlano.plano.id == Constantes.CODIGO_ESPECIAL_PLANO_GRATUITO) && 
			(vo.indCentralReserva) ){
			infoAnunciante = "Contato:<br/><span>Central de Reservas</span><br/>" +
			"Telefones:<br/>" +
			"<span>CLARO: (24) 9-9275-3073</span><br/>" ;
		} else {
			infoAnunciante = "Propriet\u00e1rio:<br/><span>" + vo.cliente.nome + "</span><br/>" +
			"Telefones de Contato:<br/>";
			if (vo.cliente.telefones != null){
				for (TelefoneVO fone : vo.cliente.telefones){
					if (fone.indPermiteExibir.equals("S")) {
						infoAnunciante += "<span>" + fone.nomeContato + " (" + fone.ddd + ")" + fone.telefone + "</span><br/>";
					}
				}
			}
		}
		this.dadosAnunciante.setHTML(infoAnunciante);
		*/
	}

	@Override
	public Widget render() {
		DockPanel dp = new DockPanel();
		dp.add(an, DockPanel.NORTH);
		dp.add(montaFormContatoAnunciante(), DockPanel.CENTER);
		dp.add(montaInfoAnunciante(), DockPanel.EAST);
		return dp;
	}
	
	public void setMensagem(String lblMensagem, int tipo) {
		an.setMensagem(lblMensagem, tipo);
	}

	public void setMensagem(List<String> lstErros, int tipo) {
		an.setMensagem(lstErros, tipo);
	}
	

	@Override
	public void init() {
		an = new AreaNotificacao();
		nome = new AutoAjudaTextBox(AA_NOME);
		email = new AutoAjudaTextBox(AA_EMAIL);
		ddd = new AutoAjudaTextBox(AA_DDD);
		telefone = new AutoAjudaTextBox(AA_TELEFONE);
		uf = new UFListBox(false);
		cidade = new CidadesListBox();
		pais = new PaisesListBox(false);
		qtdeAdultos = new ListaNumeradaListBox(1,20,false);
		qtdeCriancas = new ListaNumeradaListBox(0,20,false);
		chegadaPrevista = new DateBoxWidget();
		partidaPrevista = new DateBoxWidget();
		informacoesAdicionais = new AutoAjudaTextArea(AA_MAISINFO);
		//informacoesAdicionais.getWidgetUI().setValue(TEXTO_PADRAO_CONTATO);
		mplb = new ModoPublicidadeListBox();
		dadosAnunciante = new HTML();
		dadosAnunciante.setStyleName("gwt-dadosAnunciante");
		
		// Ativa evento ChangleHandler para carregar cidades da UF
		uf.addChangeHandler(cidade);
		
		// configura tamanhos
		informacoesAdicionais.setSize("480px", "120px");
		nome.setWidth("320px");
		email.setWidth("320px");
		ddd.setWidth("40px");
	}

	@Override
	public void clear() {
		// Nao Aplicada neste contexto
	}

	private Widget montaFormContatoAnunciante() {
		VerticalPanel vp = new VerticalPanel();
		vp.setStylePrimaryName("gwt-montaFormContatoAnunciante");
		vp.add(new HTML("<h3>FICHA DE CONTATO COM ANUNCIANTE</h3>"));
		
		Grid grid = new Grid(12,2);
		grid.setWidget(0, 0, new Label("Nome"));
		grid.setWidget(1, 0, new Label("E-mail"));
		grid.setWidget(2, 0, new Label("DDD"));
		grid.setWidget(3, 0, new Label("Telefone"));
		grid.setWidget(4, 0, new Label("Pa\u00eds"));
		grid.setWidget(5, 0, new Label("UF"));
		grid.setWidget(6, 0, new Label("Cidade"));
		grid.setWidget(7, 0, new Label("Data Prevista Chegada"));
		grid.setWidget(8, 0, new Label("Data Prevista Partida"));
		grid.setWidget(9, 0, new Label("Quant. Adultos"));
		grid.setWidget(10, 0, new Label("Quant. Crian\u00e7as"));
		grid.setWidget(11, 0, new Label("Como nos conheceu?"));
		
		grid.setWidget(0, 1, nome);
		grid.setWidget(1, 1, email);
		grid.setWidget(2, 1, ddd);
		grid.setWidget(3, 1, telefone);
		grid.setWidget(4, 1, pais);
		grid.setWidget(5, 1, uf);
		grid.setWidget(6, 1, cidade);
		grid.setWidget(7, 1, chegadaPrevista);
		grid.setWidget(8, 1, partidaPrevista);
		grid.setWidget(9, 1, qtdeAdultos);
		grid.setWidget(10, 1, qtdeCriancas);
		grid.setWidget(11, 1, mplb);
		
		
		vp.add(grid);
		
		vp.add(new HTML("Digite abaixo sua pergunta:"));
		vp.add(this.informacoesAdicionais);
		return vp;
	}
	
	private Widget montaInfoAnunciante() {
		VerticalPanel vp = new VerticalPanel();
		vp.add(dadosAnunciante);
		//vp.add(montaAvisoAlugueRelaxe());
		return vp;
	}

	private Widget montaAvisoAlugueRelaxe() {
		DockPanel dp = new DockPanel();
		dp.add(new Image("images/phone-contact.png"), DockPanel.CENTER);
		return dp;
	}

	@Override
	public void update(ContatoClienteVO vo) {
		if (vo != null) {
			if (vo.nome != null){
				this.nome.getWidgetUI().setText(vo.nome);
			}
			if (vo.email != null){
				this.email.getWidgetUI().setText(vo.email);
			}
			if (vo.ddd != null){
				this.ddd.getWidgetUI().setText(vo.ddd);
			}
			if (vo.telefone != null){
				this.telefone.getWidgetUI().setText(vo.telefone);
			}
			if (vo.informacoesAdicionais != null){
				this.informacoesAdicionais.getWidgetUI().setText(vo.informacoesAdicionais);
			}
		}
	}

	@Override
	public void notifier(ContatoClienteVO vo) {
		// TODO Auto-generated method stub
		
	}
	
}
