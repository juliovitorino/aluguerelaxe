package br.com.jcv.aluguerelaxe.client.administracao.console.reserva.transferirdeposito;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.administracao.console.liberarpgto.LiberarPgtoFormComposite;
import br.com.jcv.aluguerelaxe.client.administracao.console.liberarpgto.MemoriaCalculoFormComposite;
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

public class TransferirDepositoEditPanel extends
		AbstractFormEditPanel<TransferirDepositoToolbar, WindowPanel, TrackerVO>
		implements 	TransferirDepositoToolbarListener,
					TrackerCodigoFormCompositeListener{
	
	private static final String ANDAMENTO_TEMA = "orkut";

	private static final int ORDEM_CODIGO_RASTREAMENTO = 0;
	private static final int ORDEM_RASTREAMENTO = 1;
	private static final int ORDEM_MEMORIA_CALCULO = 2;
	private static final int ORDEM_DADOS_CONTA_CORRENTE = 3;
	private static final int ORDEM_DETALHES_NEGOCIO = 4;
	
	private SessaoVO sessaovo = null;
	private TarefasPendentesTreeVO tptvo = null;
	private TrackerNegociacaoFormComposite tnfc = new TrackerNegociacaoFormComposite();
	private TrackerCodigoFormComposite tcfc = new TrackerCodigoFormComposite();
	private ReservaViewFormComposite rvfc = new ReservaViewFormComposite();
	private LiberarPgtoFormComposite lpfc = new LiberarPgtoFormComposite();
	private MemoriaCalculoFormComposite mcfc = new MemoriaCalculoFormComposite();
	
	private WindowPanel wpCodTracking;
	private WindowPanel wpTracking;
	private WindowPanel wpmc;
	private WindowPanel wpcc;
	private WindowPanel wpReservaView;

	public TransferirDepositoEditPanel(TarefasPendentesTreeVO tptvo, TransferirDepositoToolbar toolbar){
		this(tptvo.sessaovo, toolbar);
		this.tptvo = tptvo; 
		tcfc.update(tptvo);
	}
	
	public TransferirDepositoEditPanel(SessaoVO sessao, TransferirDepositoToolbar toolbar){
		super(toolbar);
		this.sessaovo = sessao;
		
		wpCodTracking = new WindowPanel("Rastreamento",ANDAMENTO_TEMA,false,false,false);
		wpCodTracking.setSize("960px", "0px");
		wpCodTracking.setComponenteCenter( tcfc );

		wpTracking = new WindowPanel("Tracking da Negocia\u00e7\u00e3o",ANDAMENTO_TEMA,false,false,false);
		wpTracking.setSize("960px", "0px");
		wpTracking.setComponenteCenter( tnfc );

		wpmc = new WindowPanel("Mem\u00f3ria de C\u00e1lculo",ANDAMENTO_TEMA,false,false,false);
		wpmc.setSize("960px", "0px");
		wpmc.setComponenteCenter( mcfc );

		wpcc = new WindowPanel("Dados da Conta Corrente de Dep\u00f3sito",ANDAMENTO_TEMA,false,false,false);
		wpcc.setSize("960px", "0px");
		wpcc.setComponenteCenter( lpfc );

		wpReservaView = new WindowPanel("Detalhes da Negocia\u00e7\u00e3o",ANDAMENTO_TEMA,false,false,false);
		wpReservaView.setSize("960px", "0px");
		wpReservaView.setComponenteCenter( rvfc );

		addObjetoCompositeToPanel(wpCodTracking);
		addObjetoCompositeToPanel(wpTracking);
		addObjetoCompositeToPanel(wpmc);
		addObjetoCompositeToPanel(wpcc);
		addObjetoCompositeToPanel(wpReservaView);
		
		// Registra os ouvintes de TrackerCodigoFormComposite
		this.tcfc.addListener(tnfc);
		this.tcfc.addListener(rvfc);
		this.tcfc.addListener(lpfc);
		this.tcfc.addListener(mcfc);
		this.tcfc.addListener(this);
		
		// Desaabilita a visibilidade dos demais formComposite
		wpTracking.setVisible(false);
		wpmc.setVisible(false);
		wpcc.setVisible(false);
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
		TrackerVO tvo = tcfc.getVO();
		
		return tvo;
	}

	public void onPesquisarTrackerClick(ReservaVO vo) {
		wpCodTracking.setVisible(false);
		wpTracking.setVisible(true);
		wpmc.setVisible(true);
		wpcc.setVisible(true);
		wpReservaView.setVisible(true);
	}

	public void onPesquisarNovoTracking() {
		wpCodTracking.setVisible(true);
		wpTracking.setVisible(false);
		wpmc.setVisible(false);
		wpcc.setVisible(false);
		wpReservaView.setVisible(false);
	}
	
	private void executarRegistroTransferencia(TrackerVO vo) {
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
		rpc.transferirDeposito(vo, callback);
	}

	public void onTransferirDepositoClick() {
		executarRegistroTransferencia(this.getVO(this.getListObjetoComposite()));
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
