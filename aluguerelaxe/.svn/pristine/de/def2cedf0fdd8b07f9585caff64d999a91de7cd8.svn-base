package br.com.jcv.aluguerelaxe.client.administracao.console.reserva.aprovacao;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.administracao.console.negociacao.TrackerNegociacaoFormComposite;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.ReservaRPCAsync;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.ReservaVO;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.ReservaViewFormComposite;
import br.com.jcv.aluguerelaxe.client.administracao.console.tarefaspendentes.TarefasPendentesTreeVO;
import br.com.jcv.aluguerelaxe.client.administracao.console.tracker.TrackerCodigoFormComposite;
import br.com.jcv.aluguerelaxe.client.administracao.console.tracker.TrackerCodigoFormCompositeListener;
import br.com.jcv.aluguerelaxe.client.administracao.console.tracker.TrackerVO;
import br.com.jcv.aluguerelaxe.client.administracao.login.SessaoVO;
import br.com.jcv.aluguerelaxe.client.componente.editpanel.AbstractFormEditPanel;
import br.com.jcv.aluguerelaxe.client.componente.notificacao.AreaNotificacao;
import br.com.jcv.aluguerelaxe.client.componente.windowpanel.WindowPanel;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;

public class AprovacaoPreReservaEditPanel extends
		AbstractFormEditPanel<AprovacaoPreReservaToolbar, WindowPanel, TrackerVO>
		implements 	AprovacaoPreReservaToolbarListener,
					TrackerCodigoFormCompositeListener{
	
	private static final String ANDAMENTO_TEMA = "orkut";

	private static final int ORDEM_CODIGO_RASTREAMENTO = 0;
	private static final int ORDEM_RASTREAMENTO = 1;
	private static final int ORDEM_DETALHES_NEGOCIO = 2;
	
	private SessaoVO sessaovo = null;
	private TarefasPendentesTreeVO tptvo = null;
	private TrackerCodigoFormComposite tcfc = new TrackerCodigoFormComposite();
	private TrackerNegociacaoFormComposite tnfc = new TrackerNegociacaoFormComposite();
	private ReservaViewFormComposite rvfc = new ReservaViewFormComposite();
	
	private WindowPanel wpCodTracking;
	private WindowPanel wpTracking;
	private WindowPanel wpReservaView;

	public AprovacaoPreReservaEditPanel(TarefasPendentesTreeVO tptvo, AprovacaoPreReservaToolbar toolbar){
		this(tptvo.sessaovo, toolbar);
		this.tptvo = tptvo; 
		tcfc.update(tptvo);
		
	}

	public AprovacaoPreReservaEditPanel(SessaoVO sessao, AprovacaoPreReservaToolbar toolbar){
		super(toolbar);
		this.sessaovo = sessao;
		
		wpCodTracking = new WindowPanel("Rastreamento",ANDAMENTO_TEMA,false,false,false);
		wpCodTracking.setSize("960px", "0px");
		wpCodTracking.setComponenteCenter( tcfc );

		wpTracking = new WindowPanel("Tracking da Negocia\u00e7\u00e3o",ANDAMENTO_TEMA,false,false,false);
		wpTracking.setSize("960px", "0px");
		wpTracking.setComponenteCenter( tnfc );

		wpReservaView = new WindowPanel("Detalhes da Negocia\u00e7\u00e3o",ANDAMENTO_TEMA,false,false,false);
		wpReservaView.setSize("960px", "0px");
		wpReservaView.setComponenteCenter( rvfc );

		addObjetoCompositeToPanel(wpCodTracking);
		addObjetoCompositeToPanel(wpTracking);
		addObjetoCompositeToPanel(wpReservaView);
		
		// Registra os ouvintes de TrackerCodigoFormComposite
		this.tcfc.addListener(tnfc);
		this.tcfc.addListener(rvfc);
		this.tcfc.addListener(this);
		
		// Desaabilita a visibilidade dos demais formComposite
		wpTracking.setVisible(false);
		wpReservaView.setVisible(false);
	}

	public void onPesquisarTrackerClick(ReservaVO vo) {
		wpCodTracking.setVisible(false);
		wpTracking.setVisible(true);
		wpReservaView.setVisible(true);
	}

	public void onAprovarClick() {
		executarAprovacao(this.getVO(this.getListObjetoComposite()));
	}

	public void onPesquisarNovoTrackingClick() {
		wpCodTracking.setVisible(true);
		wpTracking.setVisible(false);
		wpReservaView.setVisible(false);
	}

	@Override
	public TrackerVO getVO(WindowPanel composite) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TrackerVO getVO(List<WindowPanel> composite) {
		WindowPanel wpTracking = composite.get(ORDEM_CODIGO_RASTREAMENTO);
		
		TrackerCodigoFormComposite tcfc = (TrackerCodigoFormComposite) wpTracking.getComponenteCenter();
		
		TrackerVO vo = tcfc.getVO();
		return vo;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	public void onReprovarClick() {
		executarReprovacao(this.getVO(this.getListObjetoComposite()));
	}

	private void executarAprovacao(TrackerVO vo) {
		ReservaRPCAsync rpc = ServicosRPC.getReservaRPC();
		AsyncCallback<ReservaVO> callback = new AsyncCallback<ReservaVO>() {

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

			public void onSuccess(ReservaVO result) {
				getAreaNotificacao().setMensagem(result.msgcodeString, AreaNotificacao.NOTIFICACAO_INFO);
			}
		};
		rpc.aprovarPreReservaTracker(vo, callback);
	}

	private void executarReprovacao(TrackerVO vo) {
		ReservaRPCAsync rpc = ServicosRPC.getReservaRPC();
		AsyncCallback<ReservaVO> callback = new AsyncCallback<ReservaVO>() {

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

			public void onSuccess(ReservaVO result) {
				getAreaNotificacao().setMensagem(result.msgcodeString, AreaNotificacao.NOTIFICACAO_INFO);
			}
		};
		rpc.reprovarPreReservaTracker(vo, callback);
	}


}
