package br.com.jcv.aluguerelaxe.client.componente.progressbar;

import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.vo.UploadInfoVO;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

@Deprecated
public class ProgressBar extends Composite implements AsyncCallback<UploadInfoVO> {

	private String idSessao;
	private int progresso = 0;
	private Label lblProgresso;
	private Timer timer;
	
	public ProgressBar(String idSessao) {
		this.idSessao = idSessao;
		initWidget(render());
		this.setStylePrimaryName("gwt-ProgressBar");
		this.startUpdate(); 
	}
	
	private void startUpdate() {
		timer = new Timer() {
			
			@Override
			public void run() {
				update();
				if (isFimProgresso()){
					timer.cancel();
					terminate();
				}
			}
		};
		timer.schedule(15000);
		timer.scheduleRepeating(15000);
	}
	
	protected Widget render() {
		HorizontalPanel hp = new HorizontalPanel();
		this.lblProgresso = new Label("Aguarde...");
		
		hp.add(lblProgresso);
		
		return hp;
	}
	
	private boolean isFimProgresso() {
		return (progresso >= 100);
	}

	public void terminate() {
		ProgressBarRPCAsync rpc = ServicosRPC.getProgressBarRPC();
		AsyncCallback<VOPadrao> callback = new AsyncCallback<VOPadrao>(){

			public void onFailure(Throwable caught) {
			}

			public void onSuccess(VOPadrao result) {
				lblProgresso.setText("Imagem transferida!"); 
			}
		};
		rpc.terminateProgresso(this.idSessao, callback);
	}

	
	public void update() {
		ProgressBarRPCAsync rpc = ServicosRPC.getProgressBarRPC();
		AsyncCallback<UploadInfoVO> callback = this;
		rpc.getProgresso(this.idSessao, callback);
	}

	public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub
	}

	public void onSuccess(UploadInfoVO result) {
		if (result != null){
			lblProgresso.setText("Enviando " + String.valueOf(result.bytesTransferidos) + " bytes de " + 
									String.valueOf(result.bytesTotalArquivo) + " bytes (" + String.valueOf(result.percentualTransferido) + "%)"); 
			progresso = result.percentualTransferido;
			
		}
		
	}
	
}
