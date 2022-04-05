package br.com.jcv.aluguerelaxe.client.administracao.console.reserva.prereserva;

import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.LocatarioVO;
import br.com.jcv.aluguerelaxe.client.cliente.TelefoneVO;
import br.com.jcv.aluguerelaxe.client.componente.autoajuda.AutoAjudaTextBox;
import br.com.jcv.aluguerelaxe.client.componente.dataentry.TelefoneDataEntry;
import br.com.jcv.aluguerelaxe.client.componente.date.DateBoxWidget;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.notificacao.AreaNotificacao;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class LocatarioFormComposite extends FormComposite<LocatarioVO> 
		implements EmailReservaFormCompositeListener {
	
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
	private Label txtEmail;
	private AutoAjudaTextBox txtNomeCompleto;
	private AutoAjudaTextBox txtCpf;
	private TelefoneDataEntry txtTelefone;
	private DateBoxWidget dataNascimento;
	private AreaNotificacao an;
	
	public LocatarioFormComposite() {
		super();
	}
	
	public void init() {
		txtId = new Label();
		txtEmail = new Label();
		txtNomeCompleto =  new AutoAjudaTextBox(AUTO_AJUDA_NOME_CLIENTE);
		txtCpf =  new AutoAjudaTextBox(AUTO_AJUDA_CPF);
		txtTelefone =  new TelefoneDataEntry();
		dataNascimento = new DateBoxWidget();
		an = new AreaNotificacao();
	}

	public Widget render() {
		VerticalPanel vp = new VerticalPanel();
		Grid grid = new Grid(6,2);
		
		// Monta os labels
		Label lblId = new Label("ID:");
		Label lblEmail = new Label("Email:");
		Label lblNomeCompleto = new Label("Nome Completo:");
		Label lblCpf = new Label("C.P.F:");
		Label lblTelefone = new Label("Telefone Contato:");
		Label lblDataNascimento = new Label("Data Nascimento:");
		
		// Configura tamanhos
		txtNomeCompleto.setWidth("580px");
		txtCpf.setWidth("120px");
		
		
		// Posiciona campos no grid
		int linha = -1;

		grid.setWidget(++linha, 0, lblId);
		grid.setWidget(linha, 1, txtId);

		grid.setWidget(++linha, 0, lblEmail);
		grid.setWidget(linha, 1, txtEmail);

		grid.setWidget(++linha, 0, lblNomeCompleto);
		grid.setWidget(linha, 1, txtNomeCompleto);

		grid.setWidget(++linha, 0, lblCpf);
		grid.setWidget(linha, 1, txtCpf);

		grid.setWidget(++linha, 0, lblTelefone);
		grid.setWidget(linha, 1, txtTelefone);

		grid.setWidget(++linha, 0, lblDataNascimento);
		grid.setWidget(linha, 1, dataNascimento);

		// Adiciona a grid ao VerticalPanel
		vp.add(an);
		vp.add(grid);
		
		return vp;
	}

	@Override
	public LocatarioVO getVO() {
		LocatarioVO vo = new LocatarioVO();
		try {
			vo.id = Long.valueOf(txtId.getText());
		} catch (NumberFormatException e) {
			vo.id = -1;
		}
		vo.email = txtEmail.getText();
		vo.cpf = txtCpf.getWidgetUI().getValue();
		vo.dataNascimento = dataNascimento.getValue();
		vo.nome = txtNomeCompleto.getWidgetUI().getValue();
		vo.telefone = new TelefoneVO();
		vo.telefone.ddd = txtTelefone.getDDD();
		vo.telefone.telefone = txtTelefone.getTelefone();
		return vo;
	}
	
	@Override
	public void update(LocatarioVO result){
		txtId.setText(String.valueOf(result.id));
		txtEmail.setText(result.email);
		txtNomeCompleto.getWidgetUI().setText(result.nome);
		txtCpf.getWidgetUI().setText(result.cpf);
		if (result.dataNascimento != null){
			dataNascimento.setValue(result.dataNascimento);
		}
		
		if(result.telefone != null){
			txtTelefone.setDDD(result.telefone.ddd);
			txtTelefone.setTelefone(result.telefone.telefone);
		}
	}

	@Override
	public void clear() {
	}

	@Override
	public void notifier(LocatarioVO vo) {
		// TODO Auto-generated method stub
		
	}

	public void onPesquisarEmailClick(LocatarioVO vo) {
		if (vo != null){
			update(vo);
		} 
	}


}
