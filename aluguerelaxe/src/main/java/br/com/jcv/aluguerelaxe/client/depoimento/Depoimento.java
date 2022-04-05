package br.com.jcv.aluguerelaxe.client.depoimento;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.ServicosRPC;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class Depoimento implements EntryPoint {

	
	private static final String AR_PNL_DEPOIMENTO_LADO_EAST_WEST = "ladoEAST_WEST";
	private static final String AR_PNL_DEPOIMENTO_DEPOIMENTO_NOME = "depoimentoNome";
	private static final String AR_PNL_DEPOIMENTO_DEPOIMENTO_CENTER = "depoimentoCenter";
	private static final String AR_PNL_DEPOIMENTO = "ar-pnl-depoimento";

	private static final String GWT_DEPOIMENTO = "gwt-depoimento";
	
	VerticalPanel containerNovoDepoimento = new VerticalPanel();
	//TextBox txtNome = null;
	//TextArea taDepoimento = null;
	SimplePanel spMensagem = null;
	Label msg = new Label();
	Label lblDepoimentoCenter = null;
	Label lblNome = null;
	Label lblDataCadastro = null;
	long start = 1000000;
	int indice = -1;
	List<DepoimentoVO> lstDepoimentos = null;

	public void onModuleLoad() {

		SimplePanel sp = new SimplePanel();
		sp.setStylePrimaryName(AR_PNL_DEPOIMENTO);
		sp.add(montaPainelDepoimento());
		RootPanel.get(GWT_DEPOIMENTO).add(sp);
		update();
	}

	private void update() {
		DepoimentoRPCAsync rpc = ServicosRPC.getDepoimentoRPC();
		AsyncCallback<List<DepoimentoVO>> callback = new AsyncCallback<List<DepoimentoVO>>() {
			public void onSuccess(List<DepoimentoVO> result) {
				if (result != null){
					lstDepoimentos = result;
					if (result.size() > 1){
						//imgPrev.setVisible(true);
						//imgNext.setVisible(true);
					}
					indice += 1;
					updateDepoimento();
				} else {
					//apresentar a mensagem que "Realize agora seu depoimento" e desabilita/visible false as setas
				}
			}

			public void onFailure(Throwable caught) {
				//Colocar tratamento de erro
			}
		};
		
		try {
			rpc.ListarPaginaDepoimentos(callback);
		} catch (AlugueRelaxeFrontException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void updateDepoimento() {
		DepoimentoVO vo = lstDepoimentos.get(indice);
		lblDepoimentoCenter.setText(vo.depoimento);
		lblNome.setText(vo.nome);
		lblDataCadastro.setText("em " + vo.dataDepoimento);
	}

	private Widget montaPainelDepoimento() {
		DockPanel dp = new DockPanel();
		
		dp.add(montaLadoWEST(), DockPanel.WEST);
		dp.add(montaDepoimentoCenter(),DockPanel.CENTER);
		return dp;
	}
	
	private Widget montaDepoimentoCenter() {
		VerticalPanel vp = new VerticalPanel();
		lblDepoimentoCenter = new Label();
		lblDepoimentoCenter.setStylePrimaryName(AR_PNL_DEPOIMENTO);
		lblDepoimentoCenter.addStyleName(AR_PNL_DEPOIMENTO_DEPOIMENTO_CENTER);
		
		lblNome = new Label();
		lblNome.setStylePrimaryName(AR_PNL_DEPOIMENTO);
		lblNome.addStyleName(AR_PNL_DEPOIMENTO_DEPOIMENTO_NOME);

		lblDataCadastro = new Label();
		lblDataCadastro.setStylePrimaryName(AR_PNL_DEPOIMENTO);
		lblDataCadastro.addStyleName(AR_PNL_DEPOIMENTO_DEPOIMENTO_NOME);
		
		vp.add(lblNome);
		vp.add(lblDataCadastro);
		vp.add(lblDepoimentoCenter);
		return vp;
	}

	private Widget montaLadoWEST() {
		VerticalPanel sp = new VerticalPanel();
		sp.setStylePrimaryName(AR_PNL_DEPOIMENTO);
		sp.addStyleName(AR_PNL_DEPOIMENTO_LADO_EAST_WEST);
		Image imgPrev = new Image("images/prev.png");
		imgPrev.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				indice -= 1;
				if (indice < 0) {
					indice = lstDepoimentos.size() - 1;
				}
				updateDepoimento();
			}
		});

		Image imgNext = new Image("images/next.png");
		imgNext.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				indice += 1;
				if (indice > lstDepoimentos.size() ) {
					indice = 0;
				}
				updateDepoimento();
			}
		});

		sp.add(imgPrev);
		sp.add(imgNext);
		
		imgPrev.setVisible(true);
		imgNext.setVisible(true);
		
		return sp;
	}

}

