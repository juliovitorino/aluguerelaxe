package br.com.jcv.aluguerelaxe.client.imovel.ficha;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.notificacao.AreaNotificacao;

import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class IndicarAmigoFormComposite extends FormComposite<IndicarAmigoVO> {

	private TextBox seunome;
	private TextBox seuemail;
	private TextBox nomeamigo;
	private TextBox emailamigo;
	private TextArea mensagem;
	private AreaNotificacao an;

	@Override
	public IndicarAmigoVO getVO() {
		IndicarAmigoVO iavo = new IndicarAmigoVO();
		iavo.seuemail = seuemail.getValue();
		iavo.seunome = seunome.getValue();
		iavo.nomeamigo = nomeamigo.getValue();
		iavo.emailamigo = emailamigo.getValue();
		iavo.mensagem = mensagem.getValue();
		return iavo;
	}

	@Override
	public void update(IndicarAmigoVO vo) {
		// TODO Auto-generated method stub

	}

	@Override
	public Widget render() {
		DockPanel vp = new DockPanel();
		vp.add(an, DockPanel.NORTH);
		vp.add(new HTML("<h3>Enviar para um amigo</h3>"), DockPanel.NORTH);
		
		Grid grid = new Grid(4,2);
		grid.setWidget(0, 0, new Label("Seu Nome"));
		grid.setWidget(1, 0, new Label("Seu e-mail"));
		grid.setWidget(2, 0, new Label("Nome do amigo"));
		grid.setWidget(3, 0, new Label("E-mail do amigo"));
		
		grid.setWidget(0, 1, seunome);
		grid.setWidget(1, 1, seuemail);
		grid.setWidget(2, 1, nomeamigo);
		grid.setWidget(3, 1, emailamigo);
		
		
		vp.add(grid, DockPanel.CENTER);
		
		vp.add(mensagem, DockPanel.SOUTH);
		vp.add(new HTML("Digite alguma mensagem para seu amigo:"), DockPanel.SOUTH);
		return vp;
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
		seunome = new TextBox();
		seuemail = new TextBox();
		nomeamigo = new TextBox();
		emailamigo = new TextBox();
		mensagem = new TextArea();
		mensagem.setSize("680px", "120px");
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifier(IndicarAmigoVO vo) {
		// TODO Auto-generated method stub
		
	}

}
