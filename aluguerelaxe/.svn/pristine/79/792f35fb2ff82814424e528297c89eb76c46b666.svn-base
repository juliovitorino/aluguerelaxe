package br.com.jcv.aluguerelaxe.client.administracao.console.publicidades;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.administracao.login.SessaoVO;
import br.com.jcv.aluguerelaxe.client.componente.editpanel.AbstractFormEditPanel;
import br.com.jcv.aluguerelaxe.client.componente.galeria.GaleriaImovel;
import br.com.jcv.aluguerelaxe.client.componente.galeria.PortaRetrato;
import br.com.jcv.aluguerelaxe.client.componente.galeria.PortaRetratoListener;
import br.com.jcv.aluguerelaxe.client.componente.listbox.AbstractPlanoListbox;
import br.com.jcv.aluguerelaxe.client.componente.notificacao.AreaNotificacao;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.NenhumaToolbar;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.NenhumaToolbarListener;
import br.com.jcv.aluguerelaxe.client.componente.windowpanel.WindowPanel;
import br.com.jcv.aluguerelaxe.client.componente.wizard.WizardListener;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.FichaImovelRPCAsync;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.PublicidadeImovelVO;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.user.client.ui.SimplePanel;

public class VendaPublicidadeEditPanel extends AbstractFormEditPanel<NenhumaToolbar, WindowPanel, ImovelFichaCompletaVO> 
	implements NenhumaToolbarListener, PortaRetratoListener, WizardListener, AsyncCallback<PublicidadeImovelVO> {

	private static final String CLIENTE_FORM_TEMA = "orkut";
	
	private static final int ORDEM_GALERIA = 0;
	private static final int ORDEM_ESCOLHER_PLANO = 1;
	private static final int ORDEM_ASSISTENTE_MIGRAR_PLANO = 2;
	
	private ImovelFichaCompletaVO ifcvo = null;	
	private VendaPublicidadeWizard amp = null;
	private AbstractSelecaoPlanoFormComposite aspfc;
	
	public VendaPublicidadeEditPanel(SessaoVO sessaovo, NenhumaToolbar toolbar, String tipoPlano) {
		super(toolbar);
		
		WindowPanel wpGaleria = new WindowPanel("Selecione o im\u00f3vel que deseja comprar plano de publicidade",CLIENTE_FORM_TEMA);
		wpGaleria.setSize("960px", "190px");

		SimplePanel sp = new SimplePanel();
		sp.add(new GaleriaImovel(sessaovo.clientevo.id, this));
		wpGaleria.setComponenteCenter(sp);

		WindowPanel wpEscolherPlano = new WindowPanel("Assistente de compra de publicidade",CLIENTE_FORM_TEMA,true,true,true);
		wpEscolherPlano.setWidth("960px");
		if (tipoPlano.equals(AbstractPlanoListbox.TIPO_COMPRA_BANNER)){
			aspfc = new SelecaoBannerFormComposite();
		} else if (tipoPlano.equals(AbstractPlanoListbox.TIPO_COMPRA_PP)){
			aspfc = new SelecaoPPFormComposite();
		} else if (tipoPlano.equals(AbstractPlanoListbox.TIPO_COMPRA_PD)){
			aspfc = new SelecaoPDFormComposite();
		} else if (tipoPlano.equals(AbstractPlanoListbox.TIPO_COMPRA_EMAIL)){
				aspfc = new SelecaoEmailMarketingFormComposite();
		} else if (tipoPlano.equals(AbstractPlanoListbox.TIPO_COMPRA_FB)){
				aspfc = new SelecaoFBFormComposite();
		} else if (tipoPlano.equals(AbstractPlanoListbox.TIPO_COMPRA_FF)){
				aspfc = new SelecaoFFFormComposite();
		} else if (tipoPlano.equals(AbstractPlanoListbox.TIPO_COMPRA_PATROCINADOR)){
				aspfc = new SelecaoPatrocinadorFormComposite();
		} else if (tipoPlano.equals(AbstractPlanoListbox.TIPO_COMPRA_SB)){
				aspfc = new SelecaoSBFormComposite();
		} else if (tipoPlano.equals(AbstractPlanoListbox.TIPO_COMPRA_SMS)){
				aspfc = new SelecaoSMSFormComposite();
		}
		wpEscolherPlano.setComponenteCenter(aspfc);

		WindowPanel wpMigrarPlano = new WindowPanel("Assistente de compra de publicidade",CLIENTE_FORM_TEMA,true,true,true);
		wpMigrarPlano.setWidth("960px");
//		amp = new VendaPublicidadeWizard(tipoPlano);
		amp = new VendaPublicidadeWizard();
		wpMigrarPlano.setComponenteCenter(amp);

		// Inscreve assistente para receber resultado da pesquisa
		aspfc.addListener(amp);
		amp.addWizardListener(this);
		
		// Adiciona copmposites aos paineis
		addObjetoCompositeToPanel(wpGaleria);
		addObjetoCompositeToPanel(wpEscolherPlano);
		addObjetoCompositeToPanel(wpMigrarPlano);
		
		esconderPainel(wpEscolherPlano);
		esconderPainel(wpMigrarPlano);

		update();

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

	public void onPortaRetratoClick(PortaRetrato portaRetrato) {
		
		WindowPanel wpMigrarPlano = getListObjetoComposite().get(ORDEM_ASSISTENTE_MIGRAR_PLANO);
		WindowPanel wpEscolherPlano = getListObjetoComposite().get(ORDEM_ESCOLHER_PLANO);
		
		mostrarPainel(wpEscolherPlano);
		mostrarPainel(wpMigrarPlano);

		//-----------------------------------------------------
		// Popula os FormComposite
		//-----------------------------------------------------
		ImovelFichaCompletaVO vo = portaRetrato.getFichaImovelPortaRetrato();
		this.ifcvo = portaRetrato.getFichaImovelPortaRetrato();
		
		//-----------------------------------------------------
		// Emite chamada RPC
		//-----------------------------------------------------
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

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	public void update(ImovelFichaCompletaVO vo) {
		this.ifcvo = vo;
	}

	public void onVoltarClick() {
		// TODO Auto-generated method stub
		
	}

	public void onProximoClick() {
		amp.getResumoCompra().update(ifcvo, amp.getVO());
	}

	public void onConcluirClick() {
		VendaPublicidadeVO vo = amp.getVO();
		vo.imovel = ifcvo;
		
		// Pede uma interface RPC 
		FichaImovelRPCAsync rpc = ServicosRPC.getFichaImovelRPC();
		AsyncCallback<PublicidadeImovelVO> callback = this;
		rpc.criarPublicidade(vo, callback);
	}

	public void onFailure(Throwable caught) {
	     try {
		       throw caught;
		     } catch (IncompatibleRemoteServiceException e) {
				getAreaNotificacao().setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
		     } catch (InvocationException e) {
				getAreaNotificacao().setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
		     } catch (AlugueRelaxeFrontException e) {
		    	 if ((e.getListaErros() != null) && (e.getListaErros().size() > 0)) {
		    		 getAreaNotificacao().setMensagem(e.getListaErros(), AreaNotificacao.NOTIFICACAO_ERRO);
		    	 } else {
		    		 getAreaNotificacao().setMensagem(e.getMensagem(), AreaNotificacao.NOTIFICACAO_ERRO);
		    	 }
		     } catch (Throwable e) {
				getAreaNotificacao().setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
		     }
	}

	public void onSuccess(PublicidadeImovelVO result) {
		getAreaNotificacao().setMensagem(result.msgcodeString, AreaNotificacao.NOTIFICACAO_INFO);
		realizarPagamento(result);
	}
	
	private void realizarPagamento(PublicidadeImovelVO pivo) {
		RealizarPagtoFormComposite rpfc = new RealizarPagtoFormComposite();
		amp.showCompositeFinal(rpfc, amp.montaHeaderPasso("money.png", "Realizar Pagamento"));
		rpfc.update(pivo);
	}
	
	
}
