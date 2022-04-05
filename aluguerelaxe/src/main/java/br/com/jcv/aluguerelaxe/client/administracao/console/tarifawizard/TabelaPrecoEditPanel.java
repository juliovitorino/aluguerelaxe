package br.com.jcv.aluguerelaxe.client.administracao.console.tarifawizard;


import java.util.List;

import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.administracao.login.SessaoVO;
import br.com.jcv.aluguerelaxe.client.componente.dialog.ConfirmarDialogModal;
import br.com.jcv.aluguerelaxe.client.componente.editpanel.AbstractFormEditPanel;
import br.com.jcv.aluguerelaxe.client.componente.galeria.GaleriaImovel;
import br.com.jcv.aluguerelaxe.client.componente.galeria.PortaRetrato;
import br.com.jcv.aluguerelaxe.client.componente.galeria.PortaRetratoListener;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.NenhumaToolbar;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.NenhumaToolbarListener;
import br.com.jcv.aluguerelaxe.client.componente.windowpanel.WindowPanel;
import br.com.jcv.aluguerelaxe.client.componente.wizard.AbstractWizard;
import br.com.jcv.aluguerelaxe.client.componente.wizard.WizardListener;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.FichaImovelRPCAsync;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;
import br.com.jcv.aluguerelaxe.client.imovel.tabelapreco.TabelaPrecoVO;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.SimplePanel;

public class TabelaPrecoEditPanel extends AbstractFormEditPanel<NenhumaToolbar, WindowPanel, ImovelFichaCompletaVO> 
		implements NenhumaToolbarListener, PortaRetratoListener, WizardListener {

	private static final String CLIENTE_FORM_TEMA = "orkut";
	
	private ImovelFichaCompletaVO ifcvo;
	private TabelaPrecoWizard tpw = null;
	private WindowPanel wpTarifaGDE = null;
	//private SessaoVO sessaovo;
	
	public TabelaPrecoEditPanel(SessaoVO sessaovo, NenhumaToolbar toolbar) {
		super(toolbar);
		
		WindowPanel wpGaleria = new WindowPanel("Selecione o im\u00f3vel para lan\u00e7ar as tarifas",CLIENTE_FORM_TEMA);
		wpGaleria.setSize("960px", "190px");
		
		wpTarifaGDE = new WindowPanel(null,CLIENTE_FORM_TEMA);
		wpTarifaGDE.setWidth("960px");
		tpw = new TabelaPrecoWizard();
		wpTarifaGDE.setComponenteCenter(tpw);
		wpTarifaGDE.setVisible(false);
		tpw.addWizardListener(this);
		
		SimplePanel sp = new SimplePanel();
		sp.add(new GaleriaImovel(sessaovo.clientevo.id, this));
		wpGaleria.setComponenteCenter(sp);

		addObjetoCompositeToPanel(wpGaleria);
		addObjetoCompositeToPanel(wpTarifaGDE);
		
		//this.sessaovo = sessaovo;
	}
	
	@Override
	public ImovelFichaCompletaVO getVO(WindowPanel composite) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImovelFichaCompletaVO getVO(List<WindowPanel> composite) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update() {
		// Nada a Fazer!
	}
	
	public void update(ImovelFichaCompletaVO vo) {
		this.tpw.update(vo);
	}

	public void onPortaRetratoClick(PortaRetrato portaRetrato) {
		wpTarifaGDE.setVisible(true);
		
		//-----------------------------------------------------
		// Popula os FormComposite
		//-----------------------------------------------------
		ImovelFichaCompletaVO vo = portaRetrato.getFichaImovelPortaRetrato();
		this.ifcvo = vo;
		FichaImovelRPCAsync rpc = ServicosRPC.getFichaImovelRPC();
		AsyncCallback<ImovelFichaCompletaVO> callback = new AsyncCallback<ImovelFichaCompletaVO>() {

			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			public void onSuccess(ImovelFichaCompletaVO result) {
				update(result);
			}
		};
		rpc.pesquisarFichaCompletaImovel(vo.imovel.id, callback);
	}

	public void onVoltarClick() {
		// TODO Auto-generated method stub
		
	}

	public void onProximoClick() {
		// TODO Auto-generated method stub
		
	}

	public void onConcluirClick() {
		List<TabelaPrecoVO> lst = this.tpw.getListVO();
		if (lst != null){
			this.ifcvo.tabelaPreco = lst;
			FichaImovelRPCAsync rpc = ServicosRPC.getFichaImovelRPC();
			AsyncCallback<VOPadrao> callback = new AsyncCallback<VOPadrao>() {

				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					
				}

				public void onSuccess(VOPadrao result) {
					if (result != null){
						ConfirmarDialogModal cmd = new ConfirmarDialogModal("Sucesso", result.msgcodeString, ConfirmarDialogModal.TIPO_MODAL_INFO);
						TabelaPrecoEditPanel.this.tpw.forcarVisibilidadeBotao(AbstractWizard.BOTAO_CONCLUIR, true);
					}
					
				}
			};
			rpc.atualizarTarifaImovel(ifcvo, callback);
		}
		
	}

}
