package br.com.jcv.aluguerelaxe.client.administracao.console.desktop;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.administracao.login.SessaoVO;
import br.com.jcv.aluguerelaxe.client.componente.editpanel.AbstractFormEditPanel;
import br.com.jcv.aluguerelaxe.client.componente.galeria.GaleriaImovel;
import br.com.jcv.aluguerelaxe.client.componente.galeria.PortaRetrato;
import br.com.jcv.aluguerelaxe.client.componente.galeria.PortaRetratoListener;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.NenhumaToolbar;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.NenhumaToolbarListener;
import br.com.jcv.aluguerelaxe.client.componente.windowpanel.WindowPanel;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.FichaImovelRPCAsync;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;



public class MeuDesktopEditPanel extends AbstractFormEditPanel<NenhumaToolbar, Composite, VOPadrao> 
		implements NenhumaToolbarListener, PortaRetratoListener {

	private static final String CLIENTE_FORM_TEMA = "orkut";
	
	private SessaoVO sessaovo;
	//private DesktopContatoAnuncianteFormComposite dcafc;
	private CharterDistribuicaoVisitasFormComposite cdvfc;
	private WindowPanel wpcdvfc = null;
	
	public MeuDesktopEditPanel(SessaoVO sessaovo, NenhumaToolbar toolbar) {
		super(toolbar);
		
		WindowPanel wpGaleria = new WindowPanel("Selecione o im\u00f3vel para utilizar no desktop",CLIENTE_FORM_TEMA);
		wpGaleria.setSize("960px", "190px");
		
		SimplePanel sp = new SimplePanel();
		sp.add(new GaleriaImovel(sessaovo.clientevo.id, this));
		wpGaleria.setComponenteCenter(sp);

		wpcdvfc = new WindowPanel("Distribui\u00e7\u00e3o das Visitas ao Im\u00f3vel",CLIENTE_FORM_TEMA);
		wpcdvfc.setWidth("960px");
		wpcdvfc.setVisible(false);
		this.cdvfc = new CharterDistribuicaoVisitasFormComposite();
		wpcdvfc.setComponenteCenter(cdvfc); 
		

		//-------------------------------------------------
		// Inibir de acordo com a release de 02/09/2012
		// nao remover as linhas em funcao de poder voltar 
		// qualquer outro dia
		//-------------------------------------------------
		//WindowPanel wpContatosAnunciante = new WindowPanel("Contatos realizados por clientes",CLIENTE_FORM_TEMA);
		//wpContatosAnunciante.setWidth("960px");
		//this.dcafc = new DesktopContatoAnuncianteFormComposite();
		//wpContatosAnunciante.setComponenteCenter(dcafc); 
		
		addObjetoCompositeToPanel(wpGaleria);
		addObjetoCompositeToPanel(wpcdvfc);
		//addObjetoCompositeToPanel(wpContatosAnunciante);
		
		this.sessaovo = sessaovo;
		//this.update();
		
	}
	
	@Override
	public VOPadrao getVO(Composite composite) {
		// Nao aplicavel neste contexto
		return null;
	}

	@Override
	public VOPadrao getVO(List<Composite> composite) {
		// Nao aplicavel neste contexto
		return null;
	}

	private void update(ImovelFichaCompletaVO result){
		this.wpcdvfc.setVisible(true);
		this.cdvfc.update(result);
	}
	
	@Override
	public void update() {
		//-------------------------------------------------
		// atualiza o DesktopContatoAnuncianteFormComposite
		//-------------------------------------------------
		//this.dcafc.update(this.sessaovo.clientevo);
	}

	public void onPortaRetratoClick(PortaRetrato portaRetrato) {
		this.wpcdvfc.setVisible(false);
		//-----------------------------------------------------
		// Popula o composite 
		//-----------------------------------------------------
		ImovelFichaCompletaVO vo = portaRetrato.getFichaImovelPortaRetrato();
		FichaImovelRPCAsync rpc = ServicosRPC.getFichaImovelRPC();
		AsyncCallback<ImovelFichaCompletaVO> callback = new AsyncCallback<ImovelFichaCompletaVO>() {

			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			public void onSuccess(ImovelFichaCompletaVO result) {
				update(result);
			}
		};
		rpc.pesquisarFichaCompletaImovel(vo.imovel.id, callback);	}
	

}
