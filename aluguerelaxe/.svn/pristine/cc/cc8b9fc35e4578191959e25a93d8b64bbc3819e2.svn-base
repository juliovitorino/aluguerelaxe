package br.com.jcv.aluguerelaxe.client.centralreserva;

import java.util.ArrayList;
import java.util.List;

import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.ReservaRPCAsync;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.ReservaVO;
import br.com.jcv.aluguerelaxe.client.administracao.login.SessaoVO;
import br.com.jcv.aluguerelaxe.client.componente.menu.MenuToolbar;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.AbstractToolbar;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.NenhumaToolbar;
import br.com.jcv.aluguerelaxe.client.componente.windowpanel.WindowPanel;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class CentralReserva implements EntryPoint,
										ToolbarCentralReservaListener {
	
	private static final String TEMA_HEADER = "orkut";
	private static final String TEMA_FOOTER = "orkut";
	
	private DockPanel dp = new DockPanel();
	private SessaoVO sessaovo = null;
	private Widget widgetCenter = new HorizontalPanel();
	private ReservaVO rvo;
	
	public void onModuleLoad() {

		RootPanel.get("gwt-centralreserva").add(dp);

		String hash = com.google.gwt.user.client.Window.Location.getParameter("hash");
		if (hash != null){
			ReservaRPCAsync rpc = ServicosRPC.getReservaRPC();
			AsyncCallback<ReservaVO> callback = new AsyncCallback<ReservaVO>() {

				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					
				}

				public void onSuccess(ReservaVO result) {
					if (result != null){
						CentralReserva.this.rvo = result;
						CentralReserva.this.onAndamentoClick();
					}
					
				}
			};
			rpc.getReserva(hash, callback);
		} else {
			dp.add(montaHeader(), DockPanel.NORTH);
		}
		
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Widget montaHeader() {

		MenuToolbar am = new MenuToolbar();

		//-----------------------------------------------------------------
		// Cria o menu item da central de reserva
		//-----------------------------------------------------------------
		ToolbarCentralReserva tmc = new ToolbarCentralReserva();
		tmc.addToolbarListener(this);
		
		// empilha lista de toolbars
		List<AbstractToolbar> lsttmc = new ArrayList<AbstractToolbar>();
		lsttmc.add(tmc);
		
		// Cria item o menu
		am.addMenuItem("Central de Reservas", lsttmc);

		return am;
	}

	private Widget montaFooter() {
		VerticalPanel vp = new VerticalPanel();
		WindowPanel hp = new WindowPanel(null,TEMA_FOOTER);
		hp.setSize("967px", "80px");
		vp.add(hp);
		return vp;
	}

	public void onMeusDadosClick() {
		//dp.remove(widgetCenter);
		//widgetCenter = new ProprietarioEditPanel(sessaovo, new ManterClienteToolbar());
		//dp.add(widgetCenter, DockPanel.CENTER);
	}



	public static native void redirect(String url)/*-{
		$wnd.location = url;
	}-*/;

	public void onAndamentoClick() {
		dp.remove(widgetCenter);
		widgetCenter = new AndamentoPublicoEditPanel(this.rvo, new NenhumaToolbar());
		dp.add(widgetCenter, DockPanel.CENTER);
	}

	public void onCancelarReservaClick() {
		// TODO Auto-generated method stub
		
	}

	public void onAvaliarReservaClick() {
		// TODO Auto-generated method stub
		
	}

}

