package br.com.jcv.aluguerelaxe.client.enquete;

import java.util.Date;

import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.administracao.console.enquete.EnqueteModoPublicidadeWizard;
import br.com.jcv.aluguerelaxe.client.componente.dialog.AbstractDialogModal;
import br.com.jcv.aluguerelaxe.client.componente.windowpanel.WindowPanel;
import br.com.jcv.aluguerelaxe.client.componente.wizard.AbstractWizard;
import br.com.jcv.aluguerelaxe.client.componente.wizard.WizardListener;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class EnqueteModoPublicidadeDialogModal extends AbstractDialogModal<Widget> 
			implements WizardListener {
	
	public static final int COOKIE_EXPIRE_DAYS = 365;
	public static final long MILLISECS_PER_DAY = 1000L * 60L * 60L * 24L;
	
	public static final String COOKIE_MODO_PUBLICIDADE = "enqmp";


	private EnqueteModoPublicidadeWizard mpfc;
	
	public EnqueteModoPublicidadeDialogModal(){
		super();
		this.setStylePrimaryName("gwt-EnqueteModoPublicidadeDialogModal");
		// verifica o css do gwt-DialogBox que tem itens interessantes - pegar na web
	}
	
	@Override
	public Widget render() {
		WindowPanel wpmenu = new WindowPanel("Pesquisa AlugueRelaxe","orkut");
		VerticalPanel vp = new VerticalPanel();
		vp.add(mpfc);
		wpmenu.setComponenteCenter(vp);
		return wpmenu;
	}
	
	@Override
	public void init() {
		mpfc = new EnqueteModoPublicidadeWizard();
		mpfc.addWizardListener(this);
	}

	public void onVoltarClick() {
		// TODO Auto-generated method stub
		
	}

	public void onProximoClick() {
		// TODO Auto-generated method stub
		
	}

	public void onConcluirClick() {
		if(this.mpfc.getVO().id > -1){
			this.hide();
			EnqueteModoPublicidadeRPCAsync rpc = ServicosRPC.getEnqueteModoPublicidadeRPC();
			AsyncCallback<VOPadrao> callback = new AsyncCallback<VOPadrao>() {
				
				public void onFailure(Throwable caught) {
				}
				
				public void onSuccess(VOPadrao result) {
					Date d = new Date();
					d.setTime(d.getTime() + MILLISECS_PER_DAY * COOKIE_EXPIRE_DAYS);
					Cookies.setCookie(COOKIE_MODO_PUBLICIDADE, "1", d);
					redirect("/arweb");
				}
			};
			rpc.incrementarModoPublicidadeVisita(this.mpfc.getVO().id, callback);
		} else {
			this.mpfc.forcarVisibilidadeBotao(AbstractWizard.BOTAO_CONCLUIR, true);
		}
	}
	
	public static native void redirect(String url)/*-{
		$wnd.location = url;
	}-*/;
	
	
}