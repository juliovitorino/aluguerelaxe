package br.com.jcv.aluguerelaxe.client.administracao.console.uploadwizard;

import java.util.ArrayList;
import java.util.List;

import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.administracao.console.imovelpropriedade.GaleriaLadoLadoFotoMIEditPanel;
import br.com.jcv.aluguerelaxe.client.administracao.console.imovelpropriedade.GaleriaLadoLadoFotoMIFormComposite;
import br.com.jcv.aluguerelaxe.client.administracao.console.imovelpropriedade.UploadEditPanel;
import br.com.jcv.aluguerelaxe.client.administracao.login.SessaoVO;
import br.com.jcv.aluguerelaxe.client.componente.editpanel.AbstractFormEditPanel;
import br.com.jcv.aluguerelaxe.client.componente.foto.FotoMICheckbox;
import br.com.jcv.aluguerelaxe.client.componente.galeria.GaleriaImovel;
import br.com.jcv.aluguerelaxe.client.componente.galeria.PortaRetrato;
import br.com.jcv.aluguerelaxe.client.componente.galeria.PortaRetratoListener;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.NenhumaToolbar;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.NenhumaToolbarListener;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.UploadToolbar;
import br.com.jcv.aluguerelaxe.client.componente.windowpanel.WindowPanel;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.FichaImovelRPCAsync;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;
import br.com.jcv.aluguerelaxe.client.imovel.imagem.ImovelImagemVideoVO;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.SimplePanel;

public class UploadImagemEditPanel extends AbstractFormEditPanel<NenhumaToolbar, WindowPanel, ImovelFichaCompletaVO> 
		implements NenhumaToolbarListener, PortaRetratoListener, GaleriaToolbarListener {

	private static final String CLIENTE_FORM_TEMA = "orkut";
	
	private WindowPanel wpUpload = null;
	private WindowPanel wpGaleriaFotoMI = null;
	private WindowPanel wpInstrucoes = null;
	private SessaoVO sessaovo;
	private UploadEditPanel uep = null;
	private InstrucoesUploadFormComposite iufc = null;
	private GaleriaLadoLadoFotoMIEditPanel gllep = null;
	private ImovelFichaCompletaVO ifcvo = null;
	
	public UploadImagemEditPanel(SessaoVO sessaovo, NenhumaToolbar toolbar) {
		super(toolbar);
		
		WindowPanel wpGaleria = new WindowPanel("Selecione o im\u00f3vel para manipular as imagens",CLIENTE_FORM_TEMA);
		wpGaleria.setSize("960px", "190px");
		
		uep = new UploadEditPanel(new UploadToolbar());

		wpUpload = new WindowPanel("Enviar novas imagens",CLIENTE_FORM_TEMA);
		wpUpload.setWidth("960px");
		wpUpload.setComponenteCenter(uep);
		wpUpload.setVisible(false);

		iufc = new InstrucoesUploadFormComposite();
		wpInstrucoes = new WindowPanel("Instru\u00e7\u00f5es para realizar o envio de imagens",CLIENTE_FORM_TEMA);
		wpInstrucoes.setWidth("960px");
		wpInstrucoes.setComponenteCenter(iufc);
		wpInstrucoes.setVisible(false);

		GaleriaToolbar gtb = new GaleriaToolbar();
		gtb.addToolbarListener(this);
		gllep = new GaleriaLadoLadoFotoMIEditPanel(gtb);
		wpGaleriaFotoMI = new WindowPanel("Imagens deste im\u00f3vel",CLIENTE_FORM_TEMA);
		wpGaleriaFotoMI.setComponenteCenter(gllep);
		wpGaleriaFotoMI.setVisible(false);
		
		SimplePanel sp = new SimplePanel();
		sp.add(new GaleriaImovel(sessaovo.clientevo.id, this));
		wpGaleria.setComponenteCenter(sp);

		addObjetoCompositeToPanel(wpGaleria);
		addObjetoCompositeToPanel(wpInstrucoes);
		addObjetoCompositeToPanel(wpUpload);
		addObjetoCompositeToPanel(wpGaleriaFotoMI);
		
		this.sessaovo = sessaovo;
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
		this.gllep.update(vo.imagensImovelMI);
		this.uep.update(vo.imovel, vo.cliente);
		this.uep.update(vo);
		this.ifcvo = vo;
	}

	private void atualizaAmbiente(ImovelFichaCompletaVO vo) {
		uep.removeTodosObjetoCompositeFromPanel();
		wpUpload.setVisible(true);
		wpGaleriaFotoMI.setVisible(true);
		wpInstrucoes.setVisible(true);
		
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
	
	public void onPortaRetratoClick(PortaRetrato portaRetrato) {
		//-----------------------------------------------------
		// Popula os FormComposite
		//-----------------------------------------------------
		ImovelFichaCompletaVO vo = portaRetrato.getFichaImovelPortaRetrato();
		this.atualizaAmbiente(vo);
	}

	public void onEnviarLixeiraClick() {
		// Recupera  a lista de imagens marcadas
		List<GaleriaLadoLadoFotoMIFormComposite> lstImagens = gllep.getListObjetoComposite();
		if (lstImagens != null){
			
			List<ImovelImagemVideoVO> vo = null;
			
			for (GaleriaLadoLadoFotoMIFormComposite item : lstImagens) {
				List<FotoMICheckbox> lstImgsChk = item.getListWidgets();
				if (lstImgsChk != null){
					vo = new ArrayList<ImovelImagemVideoVO>();
					for (FotoMICheckbox itemfoto : lstImgsChk) {
						if (itemfoto.getCheckDeletar().getValue()) {
							vo.add(itemfoto.getVO());
						}
					}
				}
			}
			
			this.executaEnviarImagensLixeira(vo);
			
		}


		
	}

	private void executaEnviarImagensLixeira(List<ImovelImagemVideoVO> vo) {
		FichaImovelRPCAsync rpc = ServicosRPC.getFichaImovelRPC();
		AsyncCallback<VOPadrao> callback = new AsyncCallback<VOPadrao>() {

			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			public void onSuccess(VOPadrao result) {
				UploadImagemEditPanel.this.atualizaAmbiente(UploadImagemEditPanel.this.ifcvo);
				
			}
		};
		
		rpc.enviarImagensLixeira(ifcvo.imovel.id, vo, callback);
		
	}

}
