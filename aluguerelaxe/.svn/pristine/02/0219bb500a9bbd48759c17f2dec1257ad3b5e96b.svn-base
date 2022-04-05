package br.com.jcv.aluguerelaxe.client.administracao.console.reserva.deposito;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.administracao.console.imovelpropriedade.ImovelPropriedadeFormComposite;
import br.com.jcv.aluguerelaxe.client.administracao.console.negociacao.TrackerNegociacaoFormComposite;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.ReservaRPCAsync;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.ReservaVO;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.ReservaViewFormComposite;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.aprovacao.AprovacaoPreReservaToolbar;
import br.com.jcv.aluguerelaxe.client.administracao.console.tarefaspendentes.TarefasPendentesTreeVO;
import br.com.jcv.aluguerelaxe.client.administracao.console.tracker.TrackerCodigoFormComposite;
import br.com.jcv.aluguerelaxe.client.administracao.login.SessaoVO;
import br.com.jcv.aluguerelaxe.client.componente.editpanel.AbstractEditPanel;
import br.com.jcv.aluguerelaxe.client.componente.editpanel.AbstractFormEditPanel;
import br.com.jcv.aluguerelaxe.client.componente.notificacao.AreaNotificacao;
import br.com.jcv.aluguerelaxe.client.componente.windowpanel.WindowPanel;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

public class ConfirmarDepositoEditPanel extends
		AbstractFormEditPanel<ConfirmarDepositoToolbar, WindowPanel, VOPadrao>
		implements ConfirmarDepositoToolbarListener {
	
	private static final String CONFIRMAR_DEPOSITO_TEMA = "orkut";

	private static final int ORDEM_CODIGO_RASTREAMENTO = 0;
	private static final int ORDEM_RASTREAMENTO = 1;
	private static final int ORDEM_DADOS_CONFIRMACAO = 2;
	private static final int ORDEM_DETALHES_NEGOCIO = 3;
	

	private SessaoVO sessaovo = null;
	private TarefasPendentesTreeVO tptvo = null;
	private TrackerNegociacaoFormComposite tnfc = new TrackerNegociacaoFormComposite();
	private TrackerCodigoFormComposite tcfc = new TrackerCodigoFormComposite();
	private ReservaViewFormComposite rvfc = new ReservaViewFormComposite();
	private ConfirmarDepositoFormComposite cdfc = new ConfirmarDepositoFormComposite();

	public ConfirmarDepositoEditPanel(TarefasPendentesTreeVO tptvo, ConfirmarDepositoToolbar toolbar){
		this(tptvo.sessaovo, toolbar);
		this.tptvo = tptvo; 
		tcfc.update(tptvo);
	}


	public ConfirmarDepositoEditPanel(SessaoVO sessao, ConfirmarDepositoToolbar toolbar){
		super(toolbar);
		this.sessaovo = sessao;
		
		WindowPanel wpCodTracking = new WindowPanel("Rastreamento",CONFIRMAR_DEPOSITO_TEMA,false,false,false);
		wpCodTracking.setSize("960px", "0px");
		wpCodTracking.setComponenteCenter( tcfc );

		WindowPanel wpTracking = new WindowPanel("Tracking da Negocia\u00e7\u00e3o",CONFIRMAR_DEPOSITO_TEMA,false,false,false);
		wpTracking.setSize("960px", "0px");
		wpTracking.setComponenteCenter( tnfc );

		WindowPanel wpConfDep = new WindowPanel("Confirma\u00e7\u00e3o de Dep\u00f3sito",CONFIRMAR_DEPOSITO_TEMA,false,false,false);
		wpConfDep.setSize("960px", "0px");
		wpConfDep.setComponenteCenter( cdfc );

		WindowPanel wpReservaView = new WindowPanel("Detalhes da Negocia\u00e7\u00e3o",CONFIRMAR_DEPOSITO_TEMA,false,false,false);
		wpReservaView.setSize("960px", "0px");
		wpReservaView.setComponenteCenter( rvfc );

		addObjetoCompositeToPanel(wpCodTracking);
		addObjetoCompositeToPanel(wpTracking);
		addObjetoCompositeToPanel(wpConfDep);
		addObjetoCompositeToPanel(wpReservaView);
		
		// Registra os ouvintes de TrackerCodigoFormComposite
		this.tcfc.addListener(tnfc);
		this.tcfc.addListener(rvfc);
		this.tcfc.addListener(cdfc);
		
	}

	@Override
	public VOPadrao getVO(WindowPanel composite) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VOPadrao getVO(List<WindowPanel> composite) {
		// TODO Auto-generated method stub
		return null;
	}

	public void onConfirmarDepositoClick() {
		getAreaNotificacao().setMensagem("Processando pagamento. Aguarde ...", AreaNotificacao.NOTIFICACAO_WARNING);

		// Obtem as informações que foram digitadas no formulario
		ReservaVO vo = new ReservaVO();
		
		WindowPanel wp = getListObjetoComposite().get(ORDEM_DADOS_CONFIRMACAO);
		ConfirmarDepositoFormComposite ipfc = (ConfirmarDepositoFormComposite) wp.getComponenteCenter();
		vo = ipfc.getVO();
		
		executarConfirmacaoDeposito(vo);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}

	private void executarConfirmacaoDeposito(ReservaVO vo) {
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
		rpc.confirmarReserva(vo.chaveTracker, vo.dataRealDeposito, vo.valorRealDeposito, callback);
		
	}

}
