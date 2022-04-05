package br.com.jcv.aluguerelaxe.client.administracao.console.geolocalizacaowizard;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.componente.dialog.ConfirmarDialogModal;
import br.com.jcv.aluguerelaxe.client.componente.dialog.ConfirmarDialogModalListener;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.notificacao.AreaNotificacao;
import br.com.jcv.aluguerelaxe.client.componente.wizard.AbstractWizard;
import br.com.jcv.aluguerelaxe.client.componente.wizard.WizardListener;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.FichaImovelRPCAsync;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;

/**
 *<h2>AssistenteNovoImovel</h2>
 *<p>Classe concreta para cria\u00e7\u00e4o de nova conta.
 *</p>
 * <h2>CSS Style Rules</h2>
 * <ul>
 * <li>.gwt-AbstractWizard {estilo primario}</li>
 * </ul>
 * @author Julio Vitorino
 */
 public class AssistenteGeoLocalizacao extends AbstractWizard<FormComposite<?>, ImovelFichaCompletaVO> 
 	implements WizardListener, AsyncCallback<VOPadrao> {
	 

	private ImovelFichaCompletaVO ifcvo;
	private BemVindoGeoLocalizacaoFormComposite bvglfc;
	private GeoLocalizacaoFormComposite glfc;
	private VisualizarGeoLocalizacaoFormComposite vglfc;
	private PassoaPassoGoogleMapsFormComposite ppgmfc;
	private InstrucoesFinaisGoogleMapsFormComposite ifgmfc;
		
	public AssistenteGeoLocalizacao() {
		super();
		this.setStylePrimaryName("gwt-AssistenteGeoLocalizacao");
		addWizardListener(this);
		
		addWizard(bvglfc, montaHeaderPasso("house.png", "Bem vindo ao Assistente de Geo-Localiza\u00e7\u00e3o"));
		addWizard(ppgmfc, montaHeaderPasso("house.png", "Passo a Passo Google Maps"));
		addWizard(glfc, montaHeaderPasso("house.png", "Informe as coordenadas do Google Maps"));
		addWizard(vglfc, montaHeaderPasso("house.png", "Confirmando a Geo-Localiza\u00e7\u00e3o"));
		addWizard(ifgmfc, montaHeaderPasso("house.png", "Instru\u00e7\u00f5es Finais"));
		
		init();
	}
	
	@Override
	public ImovelFichaCompletaVO getVO() {
		return ifgmfc.getVO();
	}
	
	public void update(ImovelFichaCompletaVO vo){
		this.ifcvo = vo;
		bvglfc.update(vo);
		glfc.update(vo);
		ifgmfc.update(vo);
	}

	public void onVoltarClick() {
		// TODO Auto-generated method stub
		
	}

	public void onProximoClick() {
		vglfc.update(glfc.getVO());
	}

	public void onConcluirClick() {
		ImovelFichaCompletaVO fcvo = this.getVO();
		if (fcvo.geolocalizacao == null){
			this.setMensagem("Coordenadas Inv\u00e1lidas", AreaNotificacao.NOTIFICACAO_ERRO);
		} else {
			FichaImovelRPCAsync rpc = ServicosRPC.getFichaImovelRPC();
			AsyncCallback<VOPadrao> callback = this;
			rpc.atualizarGeoLocalizacaoImovel(fcvo, callback);
		}
	}


	public void onFailure(Throwable caught) {
		try {
			throw caught;
		} catch (IncompatibleRemoteServiceException e) {
			this.setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
		} catch (InvocationException e) {
			this.setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
		} catch (AlugueRelaxeFrontException e) {
			if ((e.getListaErros() != null) && (e.getListaErros().size() > 0)) {
				this.setMensagem(e.getListaErros(), AreaNotificacao.NOTIFICACAO_ERRO);
			} else {
				this.setMensagem(e.getMensagem(), AreaNotificacao.NOTIFICACAO_ERRO);
			}
		} catch (Throwable e) {
			this.setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
		}	
	}

	public void onSuccess(VOPadrao result) {
		//this.setMensagem(result.msgcodeString, AreaNotificacao.NOTIFICACAO_INFO);
		ConfirmarDialogModal cdm = new ConfirmarDialogModal("Sucesso", 
				"Suas coordenadas foram geradas com sucesso.", 
				ConfirmarDialogModal.TIPO_MODAL_INFO);
		
		cdm.addConfirmarDialogModalListener(new ConfirmarDialogModalListener() {
			
			public void onSimClick() {
			}
			
			public void onOkClick() {
			}
			
			public void onNaoClick() {
			}
			
			public void onCancelarClick() {
			}
		});
		
		
	}

	@Override
	public void initComposites() {
		bvglfc = new BemVindoGeoLocalizacaoFormComposite();
		glfc = new GeoLocalizacaoFormComposite();
		vglfc = new VisualizarGeoLocalizacaoFormComposite();
		ppgmfc = new PassoaPassoGoogleMapsFormComposite();
		ifgmfc = new InstrucoesFinaisGoogleMapsFormComposite();
	}
	
}
