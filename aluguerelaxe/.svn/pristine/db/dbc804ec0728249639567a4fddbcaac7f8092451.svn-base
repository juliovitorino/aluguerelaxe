package br.com.jcv.aluguerelaxe.client.administracao.console.publicidades;

import java.util.Date;

import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.componente.date.DateBoxWidget;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.plano.PlanoVO;
import br.com.jcv.aluguerelaxe.client.plano.PlanosRPCAsync;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class VendaPublicidadeDetalhesFormComposite extends FormComposite<PlanoVO> implements SelecaoPlanoFormCompositeListener, AsyncCallback<PlanoVO> {

	private Label codigo;
	private Label plano;
	private Label descricao;
	private DateBoxWidget dataPublicacao;
	private Label diasPublicacao;
	private Label valorPlano;
	private PlanoVO pvo;

	@Override
	public PlanoVO getVO() {
		PlanoVO vo = new PlanoVO();
		vo.id = Long.valueOf(codigo.getText());
		return vo;
	}

	public Date getVendaVO() {
		return dataPublicacao.getValue();
	}

	@Override
	public void update(PlanoVO pvo) {
		this.pvo = pvo;
		codigo.setText(String.valueOf(pvo.id));
		plano.setText(pvo.nome);
		descricao.setText(pvo.descricao);
		diasPublicacao.setText(String.valueOf(pvo.numeroDiasCalculo));
		valorPlano.setText(pvo.valorstr);
	}
	
	@Override
	public Widget render() {
		VerticalPanel vp = new VerticalPanel();
		Grid grid = new Grid(6,2);

		// Monta os labels
		Label lblCodigo = new Label("C\u00f3digo:");
		Label lblPlano = new Label("Plano:");
		Label lblDescricao = new Label("Descri\u00e7\u00e3o:");
		Label lblDataPublicacao = new Label("Data de In\u00edcio da Publica\u00e7\u00e3o:");
		Label lblDiasPublicacao = new Label("Dias de Publica\u00e7\u00e3o:");
		Label lblValorPlano = new Label("Valor da Publica\u00e7\u00e3o R$:");

		// Posiciona campos no grid
		int linha = -1;

		grid.setWidget(++linha, 0, lblCodigo);
		grid.setWidget(linha, 1, codigo);

		grid.setWidget(++linha, 0, lblPlano);
		grid.setWidget(linha, 1, plano);

		grid.setWidget(++linha, 0, lblDescricao);
		grid.setWidget(linha, 1, descricao);

		grid.setWidget(++linha, 0, lblDataPublicacao);
		grid.setWidget(linha, 1, dataPublicacao);

		grid.setWidget(++linha, 0, lblDiasPublicacao);
		grid.setWidget(linha, 1, diasPublicacao);

		grid.setWidget(++linha, 0, lblValorPlano);
		grid.setWidget(linha, 1, valorPlano);

		// Adiciona a grid ao VerticalPanel
		vp.add(grid);
		
		return vp;
	}

	@Override
	public void init() {
		plano = new Label();
		codigo = new Label();
		descricao = new Label();
		dataPublicacao = new DateBoxWidget();
		diasPublicacao = new Label();
		valorPlano = new Label();
		pvo = null;
	}
	
	@Override
	public void clear() {

	}

	@Override
	public void notifier(PlanoVO vo) {
		
	}
	
	public void onPesquisarPlanoClick(long idPlano) {
		// Realiza chamada RPC para carregar a listbox de acordo com o tipo de compra
		PlanosRPCAsync rpc = ServicosRPC.getPlanosRPC();
		AsyncCallback<PlanoVO> callback = this;
		rpc.pesquisarPlano(idPlano, callback);
		
	}

	public void onFailure(Throwable caught) {
		// nada a fazer!
	}

	public void onSuccess(PlanoVO result) {
		if (result != null) { 
			this.update(result);
		}
	}	

}
