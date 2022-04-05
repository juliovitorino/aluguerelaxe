package br.com.jcv.aluguerelaxe.client.administracao.console.proprietario;

import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.cliente.ClienteRPCAsync;
import br.com.jcv.aluguerelaxe.client.cliente.ClienteVO;
import br.com.jcv.aluguerelaxe.client.componente.autoajuda.AutoAjudaTextBox;
import br.com.jcv.aluguerelaxe.client.componente.date.DateWidget;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.listbox.SimNaoListBox;
import br.com.jcv.aluguerelaxe.client.componente.listbox.StatusClienteListBox;
import br.com.jcv.aluguerelaxe.client.componente.listbox.TipoAnuncianteListBox;
import br.com.jcv.aluguerelaxe.client.componente.upload.AbstractUploadFotoFormCompositeListener;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.google.gwt.user.client.ui.FormPanel.SubmitHandler;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ClienteFormComposite extends FormComposite<ClienteVO> implements AbstractUploadFotoFormCompositeListener {

	private static final String PATH_IMAGEM_BOTOES = "images/botoes/";
	
	private final static String AUTO_AJUDA_EMAIL_CLIENTE = "<B>Email</B>" + 
					"<p>Digite o e-mail de acordo com o exemplo abaixo:<br/><br/>" +
					"joao.silva@novec.com.br</p>" +
					"<p><strong>\u00c9 muito importante que o e-mail esteja correto,<br/>" +
					"pois, ser\u00e1 atrav\u00e9s dele que o proprit\u00e1rio ir\u00e1 <br/>" +
					"receber os contatos dos visitantes ao seu im\u00f3vel.</strong></p>";
	
	private final static String AUTO_AJUDA_NOME_CLIENTE = "<B>Nome Completo</B>" + 
	"<p>Digite o nome completo do propriet\u00e1rio da conta de im\u00f3vel</p>";

	private final static String AUTO_AJUDA_CPF = "<B>CPF</B>" + 
	"<p>Digite o n\u00famero do CPF do propriet\u00e1rio da conta de im\u00f3vel.</p>" +
	"<p>Digite somente n\u00fameros no campo.</p>";
	
	private final static String AUTO_AJUDA_CGC = "<B>CGC</B>" + 
	"<p>Digite o n\u00famero do CGC do propriet\u00e1rio da conta de im\u00f3vel.</p>" +
	"<p>Digite somente n\u00fameros no campo.</p>";

	private final static String AUTO_AJUDA_URL = "<B>Website</B>" + 
	"<p>Digite o endereco de acordo com o exemplo abaixo:<br/><br/>" +
	"http://www.aluguerelaxe.com.br</p>" +
	"<p>Caso voc\u00ea tenha um website particular e deseje que o cliente<br/>" +
	"visite-o para conhec\u00ea-lo.<br/>";

	private final static String AUTO_AJUDA_MSN = "<B>MSN</B>" + 
	"<p>Caso o propriet\u00e1rio tenha uma conta de MSN e deseje que o cliente<br/>" +
	"possa ter contato direto com ele.<br/>";

	private final static String AUTO_AJUDA_SKYPE = "<B>SKYPE</B>" + 
	"<p>Caso o propriet\u00e1rio tenha uma conta de SKYPE e deseje que o cliente<br/>" +
	"possa ter contato direto com ele.<br/>";

	private final static String AUTO_AJUDA_GTALK = "<B>GOOGLE TALK</B>" + 
	"<p>Caso o propriet\u00e1rio tenha uma conta de Google Talk e deseje que o cliente<br/>" +
	"possa ter contato direto com ele.<br/>";

	private Label txtId;
	private StatusClienteListBox txtIndStatus;
	private SimNaoListBox txtIndAutorzNotificacoes;
	private TipoAnuncianteListBox txtTipoAnunciante;
	private AutoAjudaTextBox txtEmail;
	private AutoAjudaTextBox txtNomeCompleto;
	private AutoAjudaTextBox txtCpf;
	private AutoAjudaTextBox txtCgc;
	private AutoAjudaTextBox txtUrl;
	private AutoAjudaTextBox txtMsn;
	private AutoAjudaTextBox txtSkype;
	private AutoAjudaTextBox txtGoogletalk;
	private DateWidget dataNascimento;
	private PerfilClienteFormComposite pcfc;
	private ClienteVO cvo;
	private UploadFotoPerfilDialogModal ufpdm;
	private Image imgFotoUpload;
	
	public ClienteFormComposite() {
		super();
	}
	
	public void init() {
		txtId = new Label();
		txtIndStatus = new StatusClienteListBox();
		txtIndAutorzNotificacoes = new SimNaoListBox();
		txtTipoAnunciante = new TipoAnuncianteListBox();
		txtEmail = new AutoAjudaTextBox(ClienteFormComposite.AUTO_AJUDA_EMAIL_CLIENTE);
		txtNomeCompleto =  new AutoAjudaTextBox(ClienteFormComposite.AUTO_AJUDA_NOME_CLIENTE);
		txtCpf =  new AutoAjudaTextBox(ClienteFormComposite.AUTO_AJUDA_CPF);
		txtCgc =  new AutoAjudaTextBox(ClienteFormComposite.AUTO_AJUDA_CGC);
		txtUrl =  new AutoAjudaTextBox(ClienteFormComposite.AUTO_AJUDA_URL);
		txtMsn =  new AutoAjudaTextBox(ClienteFormComposite.AUTO_AJUDA_MSN);
		txtSkype =  new AutoAjudaTextBox(ClienteFormComposite.AUTO_AJUDA_SKYPE);
		txtGoogletalk =  new AutoAjudaTextBox(ClienteFormComposite.AUTO_AJUDA_GTALK);
		dataNascimento = new DateWidget();
		pcfc = new PerfilClienteFormComposite();

		imgFotoUpload = new Image(PATH_IMAGEM_BOTOES + "fotoadd.png"); 
		imgFotoUpload.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				// Abre formulario de upload da imagem de perfil
				ClienteFormComposite.this.uploadFotoPerfilDialogModal();
			}
		});
	}
	
	public void mudarVisibilidadeFotoUpload(boolean visibilidade) {
		this.imgFotoUpload.setVisible(visibilidade);
	}

	public Widget render() {
		DockPanel dp = new DockPanel();
		Grid grid = new Grid(13,2);
		
		// Monta os labels
		Label lblId = new Label("ID:");
		Label lblIndStatus = new Label("Status:");
		Label lblTipoAnunciante = new Label("Tipo de Anunciante:");
		Label lblIndAutorzNotif = new Label("Autoriza Notifica\u00e7\u00f5es:");
		Label lblEmail = new Label("E-mail:");
		Label lblNomeCompleto = new Label("Nome Completo:");
		Label lblCpf = new Label("C.P.F:");
		Label lblCgc = new Label("C.G.C:");
		Label lblUrl = new Label("Website:");
		Label lblMsn = new Label("MSN:");
		Label lblSkype = new Label("Skype:");
		Label lblGoogletalk = new Label("Google Talk:");
		Label lblDataNascimento = new Label("Data Nascimento:");
		
		// Configura tamanhos
		txtEmail.setWidth("440px");
		txtNomeCompleto.setWidth("440px");
		txtCpf.setWidth("120px");
		txtCgc.setWidth("120px");
		txtUrl.setWidth("440px");
		txtMsn.setWidth("440px");
		txtSkype.setWidth("440px");
		txtGoogletalk.setWidth("440px");
		
		txtIndStatus.setEnabled(false);
		txtTipoAnunciante.setEnabled(false);
		
		// Posiciona campos no grid
		int linha = -1;

		grid.setWidget(++linha, 0, lblId);
		grid.setWidget(linha, 1, txtId);

		grid.setWidget(++linha, 0, lblTipoAnunciante);
		grid.setWidget(linha, 1, txtTipoAnunciante);

		grid.setWidget(++linha, 0, lblIndStatus);
		grid.setWidget(linha, 1, txtIndStatus);

		grid.setWidget(++linha, 0, lblEmail);
		grid.setWidget(linha, 1, txtEmail);

		grid.setWidget(++linha, 0, lblNomeCompleto);
		grid.setWidget(linha, 1, txtNomeCompleto);

		grid.setWidget(++linha, 0, lblCpf);
		grid.setWidget(linha, 1, txtCpf);

		grid.setWidget(++linha, 0, lblCgc);
		grid.setWidget(linha, 1, txtCgc);

		grid.setWidget(++linha, 0, lblUrl);
		grid.setWidget(linha, 1, txtUrl);

		grid.setWidget(++linha, 0, lblMsn);
		grid.setWidget(linha, 1, txtMsn);

		grid.setWidget(++linha, 0, lblSkype);
		grid.setWidget(linha, 1, txtSkype);

		grid.setWidget(++linha, 0, lblGoogletalk);
		grid.setWidget(linha, 1, txtGoogletalk);

		grid.setWidget(++linha, 0, lblDataNascimento);
		grid.setWidget(linha, 1, dataNascimento);

		grid.setWidget(++linha, 0, lblIndAutorzNotif);
		grid.setWidget(linha, 1, txtIndAutorzNotificacoes);
		
		// Container para Imagem do Perfil e upload da imagem para troca/adicao
		VerticalPanel vp = new VerticalPanel();
		vp.add(pcfc);
		vp.add(imgFotoUpload);

		// Adiciona a grid ao VerticalPanel
		dp.add(grid, DockPanel.CENTER);
		dp.add(vp, DockPanel.WEST);
		
		return dp;
	}
	
	private void uploadFotoPerfilDialogModal() {
		ufpdm = new UploadFotoPerfilDialogModal();
		ufpdm.update(this.cvo);
		ufpdm.updateHandler(this);
		ufpdm.show();
	}

	public Label getTxtId() {
		return txtId;
	}

	public void setTxtId(Label txtId) {
		this.txtId = txtId;
	}

	public StatusClienteListBox getTxtIndStatus() {
		return txtIndStatus;
	}

	public void setTxtIndStatus(StatusClienteListBox txtIndStatus) {
		this.txtIndStatus = txtIndStatus;
	}

	public SimNaoListBox getTxtIndAutorzNotificacoes() {
		return txtIndAutorzNotificacoes;
	}

	public void setTxtIndAutorzNotificacoes(SimNaoListBox txtIndAutorzNotificacoes) {
		this.txtIndAutorzNotificacoes = txtIndAutorzNotificacoes;
	}

	public TipoAnuncianteListBox getTxtTipoAnunciante() {
		return txtTipoAnunciante;
	}

	public void setTxtTipoAnunciante(TipoAnuncianteListBox txtTipoAnunciante) {
		this.txtTipoAnunciante = txtTipoAnunciante;
	}

	public AutoAjudaTextBox getTxtEmail() {
		return txtEmail;
	}

	public void setTxtEmail(AutoAjudaTextBox txtEmail) {
		this.txtEmail = txtEmail;
	}

	public AutoAjudaTextBox getTxtNomeCompleto() {
		return txtNomeCompleto;
	}

	public void setTxtNomeCompleto(AutoAjudaTextBox txtNomeCompleto) {
		this.txtNomeCompleto = txtNomeCompleto;
	}

	public AutoAjudaTextBox getTxtCpf() {
		return txtCpf;
	}

	public void setTxtCpf(AutoAjudaTextBox txtCpf) {
		this.txtCpf = txtCpf;
	}

	public AutoAjudaTextBox getTxtCgc() {
		return txtCgc;
	}

	public void setTxtCgc(AutoAjudaTextBox txtCgc) {
		this.txtCgc = txtCgc;
	}

	public AutoAjudaTextBox getTxtUrl() {
		return txtUrl;
	}

	public void setTxtUrl(AutoAjudaTextBox txtUrl) {
		this.txtUrl = txtUrl;
	}

	public AutoAjudaTextBox getTxtMsn() {
		return txtMsn;
	}

	public void setTxtMsn(AutoAjudaTextBox txtMsn) {
		this.txtMsn = txtMsn;
	}

	public AutoAjudaTextBox getTxtSkype() {
		return txtSkype;
	}

	public void setTxtSkype(AutoAjudaTextBox txtSkype) {
		this.txtSkype = txtSkype;
	}

	public AutoAjudaTextBox getTxtGoogletalk() {
		return txtGoogletalk;
	}

	public void setTxtGoogletalk(AutoAjudaTextBox txtGoogletalk) {
		this.txtGoogletalk = txtGoogletalk;
	}

	public DateWidget getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(DateWidget dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public ClienteVO getVO() {
		ClienteVO vo = new ClienteVO();
		vo.cgc = getTxtCgc().getWidgetUI().getValue();
		vo.cpf = getTxtCpf().getWidgetUI().getValue();
		vo.dataNascimento = getDataNascimento().getDate();
		vo.email = getTxtEmail().getWidgetUI().getValue();
		vo.googleTalk = getTxtGoogletalk().getWidgetUI().getValue();
		vo.indicadorAutorizaNotificacao = getTxtIndAutorzNotificacoes().getValue(getTxtIndAutorzNotificacoes().getSelectedIndex());
		vo.indicadorStatus = getTxtIndStatus().getValue(getTxtIndStatus().getSelectedIndex());
		vo.indicadorTipoAnunciante = getTxtTipoAnunciante().getValue(getTxtTipoAnunciante().getSelectedIndex());
		vo.msn = getTxtMsn().getWidgetUI().getValue();
		vo.nome = getTxtNomeCompleto().getWidgetUI().getValue();
		vo.skype = getTxtSkype().getWidgetUI().getValue();
		vo.urlwww = getTxtUrl().getWidgetUI().getValue();
		return vo;
	}
	
	public void update(ClienteVO result){
		getTxtId().setText(String.valueOf(result.id));
		getTxtIndStatus().setSelectedIndex(getTxtIndStatus().getSelectedItemValue(result.indicadorStatus));
		getTxtIndAutorzNotificacoes().setSelectedIndex(getTxtIndAutorzNotificacoes().getSelectedItemValue(result.indicadorAutorizaNotificacao));
		getTxtTipoAnunciante().setSelectedIndex(getTxtTipoAnunciante().getSelectedItemValue(result.indicadorTipoAnunciante));
		getTxtEmail().getWidgetUI().setText(result.email);
		getTxtNomeCompleto().getWidgetUI().setText(result.nome);
		getTxtCpf().getWidgetUI().setText(result.cpf);
		getTxtCgc().getWidgetUI().setText(result.cgc);
		getTxtUrl().getWidgetUI().setText(result.urlwww);
		getTxtMsn().getWidgetUI().setText(result.msn);
		getTxtSkype().getWidgetUI().setText(result.skype);
		getTxtGoogletalk().getWidgetUI().setText(result.googleTalk);
		getDataNascimento().setDate(result.dataNascimento);
		this.pcfc.update(result);
		this.cvo = result;
	}

	@Override
	public void clear() {
		getTxtId().setText("");
		getTxtIndStatus().setSelectedIndex(0);
		getTxtIndAutorzNotificacoes().setSelectedIndex(0);
		getTxtTipoAnunciante().setSelectedIndex(0);
		getTxtEmail().getWidgetUI().setText("");
		getTxtNomeCompleto().getWidgetUI().setText("");
		getTxtCpf().getWidgetUI().setText("");
		getTxtCgc().getWidgetUI().setText("");
		getTxtUrl().getWidgetUI().setText("");
		getTxtMsn().getWidgetUI().setText("");
		getTxtSkype().getWidgetUI().setText("");
		getTxtGoogletalk().getWidgetUI().setText("");
	}

	@Override
	public void notifier(ClienteVO vo) {
		// TODO Auto-generated method stub
		
	}

	public void update() {
		ClienteRPCAsync rpc = ServicosRPC.getClienteRPC();
		AsyncCallback<ClienteVO> callback = new AsyncCallback<ClienteVO>() {

			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			public void onSuccess(ClienteVO result) {
				ClienteFormComposite.this.update(result);
			}
		};
		rpc.pesquisaRegistro(cvo, callback);
		
	}

	public void onCancelarClick() {
		ufpdm.hide();
		//------------------------------------------------
		// Atualizar o painel de informacoes do anunciante
		//--------------------------------------------------
		update();
	}

	public void onUploadFinished() {
		ufpdm.hide();
		//------------------------------------------------
		// Atualizar o painel de informacoes do anunciante
		//--------------------------------------------------
		update();
	}

}
