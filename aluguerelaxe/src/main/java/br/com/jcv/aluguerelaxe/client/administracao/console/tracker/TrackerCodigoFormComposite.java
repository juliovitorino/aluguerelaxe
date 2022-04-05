package br.com.jcv.aluguerelaxe.client.administracao.console.tracker;

import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.ReservaRPCAsync;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.ReservaVO;
import br.com.jcv.aluguerelaxe.client.administracao.console.tarefaspendentes.TarefasPendentesTreeVO;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.form.FormCompositeListener;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class TrackerCodigoFormComposite extends FormComposite<TrackerVO> {

	private static final String GWT_STYLE ="gwt-TrackerCodigoFormComposite";
	
	private TarefasPendentesTreeVO tptvo = null;
	
	private TextBox codigo;
	private Button btnPesquisar;
	
	public TrackerCodigoFormComposite() {
		super();
		this.setStylePrimaryName(GWT_STYLE);
	}
	
	public void update(ReservaVO rvo){
		codigo.setText(rvo.chaveTracker);
		this.executarPesquisarChaveTracker();
	}
	
	public void update(TarefasPendentesTreeVO tptvo){
		this.tptvo = tptvo;
		codigo.setText(tptvo.codigoTracking);
		this.executarPesquisarChaveTracker();
	}
	
	@Override
	public TrackerVO getVO() {
		TrackerVO vo = new TrackerVO();
		vo.codigo = codigo.getText();
		return vo;
	}
	
	@Override
	public void update(TrackerVO vo) {
	}

	@Override
	public Widget render() {
		HorizontalPanel hp = new HorizontalPanel();
		
		hp.add(new Label("C\u00f3digo de rastreamento:"));
		hp.add(this.codigo);
		hp.add(btnPesquisar);
		
		return hp;
	}

	@Override
	public void init() {
		this.codigo = new TextBox();
		
		this.btnPesquisar = new Button("Pesquisar");
		this.btnPesquisar.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				TrackerCodigoFormComposite.this.executarPesquisarChaveTracker();
			}
		});
		
	}
	
	private void executarPesquisarChaveTracker() {
		ReservaRPCAsync rpc = ServicosRPC.getReservaRPC();
		AsyncCallback<ReservaVO> callback = new AsyncCallback<ReservaVO>() {

		public void onFailure(Throwable caught) {
//		     try {
//		       throw caught;
//		     } catch (IncompatibleRemoteServiceException e) {
//				getAreaNotificacao().setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
//		     } catch (InvocationException e) {
//				getAreaNotificacao().setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
//		     } catch (AlugueRelaxeFrontException e) {
//		    	 if ((e.getListaErros() != null) && (e.getListaErros().size() > 0)) {
//		    		 getAreaNotificacao().setMensagem(e.getListaErros(), AreaNotificacao.NOTIFICACAO_ERRO);
//		    	 } else {
//		    		 getAreaNotificacao().setMensagem(e.getMensagem(), AreaNotificacao.NOTIFICACAO_ERRO);
//		    	 }
//		     } catch (Throwable e) {
//				getAreaNotificacao().setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
//		     }
		 }

		 public void onSuccess(ReservaVO result) {
			 TrackerCodigoFormComposite.this.notifier(result);
		 }
		};
		
		rpc.getReservaChaveTracker(TrackerCodigoFormComposite.this.codigo.getValue(), callback);			

	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifier(TrackerVO vo) {
		// TODO Auto-generated method stub
	}

	public void notifier(ReservaVO vo) {
		// Notifica todos os listener que estão inscritos
		if ( (this.listener != null) && (this.listener.size() > 0) ){
			for (FormCompositeListener fcl : this.listener) {
				TrackerCodigoFormCompositeListener tcfcl = (TrackerCodigoFormCompositeListener) fcl;
				tcfcl.onPesquisarTrackerClick(vo);
			}
		}
	}

}
