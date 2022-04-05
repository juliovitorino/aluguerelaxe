package br.com.jcv.aluguerelaxe.client.administracao.console.imovelpropriedade;

import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.notificacao.AreaNotificacao;
import br.com.jcv.aluguerelaxe.client.imovel.ImovelPlanoFaturaVO;

import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class SituacaoFinanceiraAnuncioFormComposite extends
		FormComposite<ImovelPlanoFaturaVO> {

	private static final String MSG_VENCIDO_1 = "** O an\u00fancio do seu im\u00f3vel somente come\u00e7ar\u00e1 a ser veiculado em nossos canais de comunica\u00e7\u00e3o" +
	" ap\u00f3s o pagamento da pend\u00eancia.";

	private static final String MSG_CONTRIBUICAO = "** Gostaria diretamente da sua ajuda, pois voc\u00ea j\u00e1 utiliza todos os servi\u00e7os e " +
	"recursos do site para poder atrair os olhos de clientes para seu im\u00f3vel. " + 
	"Saliento, que toda a contribui\u00e7\u00e3o ser\u00e1 bem-vinda, e desde j\u00e1 agrade\u00e7o seu apoio, fundamental para o sucesso deste trabalho. " +
	"Na oportunidade, aproveito para apresentar minha estima e considera\u00e7\u00e3o. conto com a sua contribui\u00e7\u00e3o!";
	
	private AreaNotificacao an;
	private HTML htmlBotaoPagseguro;
	private Label idFatura;
	private Label plano;
	private Label dtVencAnuncio;
	private Label dtCompra;
	private Label valor;
	private Label status;
	private Label dtPgto;
	
	@Override
	public ImovelPlanoFaturaVO getVO() {
		// Nao aplicavel neste contexto
		return null;
	}

	@Override
	public void update(ImovelPlanoFaturaVO vo) {
		this.clear();
		idFatura.setText(String.valueOf(vo.id));
		plano.setText(vo.plano.nome + " - " + vo.plano.descricao);
		dtVencAnuncio.setText(vo.dataVencimentoStr);
		dtCompra.setText(vo.dataCadastroStr);
		valor.setText(vo.valorFinalStr);
		status.setText(vo.indicadorStatusStr);
		dtPgto.setText(vo.dataPagamentoStr);
		
		if ((vo.indicadorStatusStr.equals("PENDENTE")) && (! vo.valorFinalStr.equals("0,00"))) {
			this.an.setMensagem(MSG_VENCIDO_1, AreaNotificacao.NOTIFICACAO_WARNING);
			htmlBotaoPagseguro.setVisible(true);
			htmlBotaoPagseguro.setHTML(vo.plano.htmlBtnPagseguro);
		} else if ((vo.valorFinalStr.equals("0,00")) && (vo.dataPagamento != null)) {
			this.an.setMensagem(MSG_CONTRIBUICAO, AreaNotificacao.NOTIFICACAO_INFO);
			htmlBotaoPagseguro.setVisible(true);
			htmlBotaoPagseguro.setHTML(vo.plano.htmlBtnPagseguro);
		} else if (vo.indicadorStatusStr.equals("LIBERADO")) {
			this.an.setVisible(false);
			htmlBotaoPagseguro.setVisible(false);
		} else {
			this.an.setVisible(false);
			htmlBotaoPagseguro.setVisible(false);
		}
	}

	@Override
	public Widget render() {
		DockPanel dp = new DockPanel();
		VerticalPanel vp = new VerticalPanel();
		vp.add(containerHorizontal("Fatura #", idFatura));
		vp.add(containerHorizontal("Plano:", plano));
		vp.add(containerHorizontal("Anunciar At\u00e9:", dtVencAnuncio));
		vp.add(containerHorizontal("Comprado em:", dtCompra));
		vp.add(containerHorizontal("Valor (R$):", valor));
		vp.add(containerHorizontal("Status Pgto:", status));
		vp.add(containerHorizontal("Pago em:", dtPgto));
		
		dp.add(an, DockPanel.NORTH);
		dp.add(vp, DockPanel.CENTER);
		dp.add(htmlBotaoPagseguro, DockPanel.EAST);
		return dp;
	}
	
	private Widget containerHorizontal(String caption, Label container) {
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(new Label(caption));
		hp.add(container);
		return hp;
	}

	@Override
	public void init() {
		idFatura = new Label();
		plano = new Label();
		dtVencAnuncio = new Label();
		dtCompra = new Label();
		valor = new Label();
		status = new Label();
		dtPgto = new Label();
		an = new AreaNotificacao();
		htmlBotaoPagseguro = new HTML();
		
		// Aplicação de CSS
		idFatura.setStylePrimaryName("gwt-status");
		plano.setStylePrimaryName("gwt-plano");
		dtVencAnuncio.setStylePrimaryName("gwt-datas");
		dtCompra.setStylePrimaryName("gwt-datas");
		dtPgto.setStylePrimaryName("gwt-datas");
		status.setStylePrimaryName("gwt-status");
		valor.setStylePrimaryName("gwt-valor");
	}

	@Override
	public void clear() {
		idFatura.setText(" ");
		plano.setText(" ");
		dtVencAnuncio.setText(" ");
		dtCompra.setText(" ");
		valor.setText(" ");
		status.setText(" ");
		dtPgto.setText(" ");
		an.setVisible(false);
	}

	@Override
	public void notifier(ImovelPlanoFaturaVO vo) {
		// TODO Auto-generated method stub
		
	}

}
