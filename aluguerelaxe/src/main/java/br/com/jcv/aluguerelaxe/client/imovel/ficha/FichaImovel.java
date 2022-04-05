package br.com.jcv.aluguerelaxe.client.imovel.ficha;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.componente.dialog.ConfirmarDialogModal;
import br.com.jcv.aluguerelaxe.client.componente.galeria.GaleriaImovel;
import br.com.jcv.aluguerelaxe.client.componente.galeria.PortaRetrato;
import br.com.jcv.aluguerelaxe.client.componente.galeria.PortaRetratoListener;
import br.com.jcv.aluguerelaxe.client.componente.notificacao.AreaNotificacao;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class FichaImovel implements EntryPoint,
									AsyncCallback<ImovelFichaCompletaVO>, 
									ToolbarMaisInfoImovelListener,
									PortaRetratoListener{

	public static final String GWT_DIV_FICHA_IMOVEL = "gwt-fichaimovel";
	private static final String IMAGES_CHECK_PNG = "images/check.png";
	private static final String PATH_BOTOES = "images/botoes/";	
	private ImovelFichaCompletaVO ifcvo; 
	private ApresentaFichaImovelFormComposite afifc;
	private DockPanel dpMaisInfo;
	private Widget widgetCenter = new HorizontalPanel();
	private FichaContatoAnuncianteFormComposite fcafc;
	private IndicarAmigoFormComposite iafc;
	private VerticalPanel vpOutrosImoveisCliente;
	private boolean vejaOutrosImoveis = false;
	private AvaliacaoAnuncioImovelFormComposite aaifc;	
	private VideoImovelFormComposite vifc;
	private long idImovel;
	
	public void onModuleLoad() {
		
		//-----------------------------------------------------------------
		// Obtém o id do imóvel enviado como parâmetro na URL
		// e busca suas informações na base de dados
		//-----------------------------------------------------------------
		String codigoImovel = com.google.gwt.user.client.Window.Location.getParameter("id");
		long id = Long.valueOf(codigoImovel);
		String origemAcesso = com.google.gwt.user.client.Window.Location.getParameter("o");
		this.idImovel = id;
		
		//-----------------------------------------------------------------
		// Adiciona a página
		//-----------------------------------------------------------------
        RootPanel.get(GWT_DIV_FICHA_IMOVEL).add(montaFichaApresentacaoImovel());

		//-----------------------------------------------------------------
		// Atualiza ficha do imóvel com as informações do backend
		//-----------------------------------------------------------------
        update(id, origemAcesso);
	}
	
	@Deprecated
	private void update(long id){
		FichaImovelRPCAsync rpc = ServicosRPC.getFichaImovelRPC();
		AsyncCallback<ImovelFichaCompletaVO> callback = this;
		rpc.apresentarFichaCompletaImovel(id, callback);
	}

	private void update(long id, String origemAcesso){
		// Avisa o classificador sobre o novo imovel
		this.aaifc.update(id);
		this.aaifc.setVisible(true);
		
		// Atualiza a ficha
		FichaImovelRPCAsync rpc = ServicosRPC.getFichaImovelRPC();
		AsyncCallback<ImovelFichaCompletaVO> callback = this;
		rpc.apresentarFichaCompletaImovel(id, origemAcesso, callback);
	}

	private Widget montaFichaApresentacaoImovel() {
		//Monta a dock
		DockPanel dp = new DockPanel();
		dp.setStylePrimaryName("gwt-FichaImovel");
		afifc = new ApresentaFichaImovelFormComposite();
		
		dp.add(montaToolbarMaisDetalhes(), DockPanel.SOUTH);
		dp.add(afifc, DockPanel.CENTER);

		return dp;
	}

	private Widget montaToolbarMaisDetalhes() {
		dpMaisInfo = new DockPanel();
		ToolbarMaisInfoImovel tmif = new ToolbarMaisInfoImovel();
		tmif.addToolbarListener(this);
		
		aaifc = new AvaliacaoAnuncioImovelFormComposite();
		aaifc.update(this.idImovel);
		
		dpMaisInfo.add(tmif, DockPanel.NORTH);
		dpMaisInfo.add(widgetCenter, DockPanel.CENTER);
		dpMaisInfo.add(aaifc, DockPanel.SOUTH);
		
		return dpMaisInfo;
	}
	
	// Talvez precise setar um SetStyle para vpOutrosImoveisCliente
	private Widget montaVejaOutrosImoveis() {
		vpOutrosImoveisCliente = new VerticalPanel();
		vpOutrosImoveisCliente.add(new Label("Veja outros im\u00f3veis deste anunciante"));
		vpOutrosImoveisCliente.add(new GaleriaImovel(ifcvo.cliente.id, this, 5, true));
		return vpOutrosImoveisCliente;
	}

	public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub
		
	}

	public void onSuccess(ImovelFichaCompletaVO result) {
		this.ifcvo = result;
		
		// Apresenta as informacoes se o imovel esta em estado ativo
		if(this.ifcvo.imovel.indicadorStatus.equals("A")){
			updateFichaImovel();
			onMaisDadosImovelClick();
			if (! vejaOutrosImoveis)  {
				dpMaisInfo.add(montaVejaOutrosImoveis(), DockPanel.SOUTH);
				vejaOutrosImoveis = true;
			}
		} else {
			redirect("/arweb/JXControllerSmartInterface?cmd=dtgListaImovelUf&atvd=0&uf=" + this.ifcvo.imovel.endereco.uf +"&cidade=-1");
		}
	}
	
	public static native void redirect(String url)/*-{
		$wnd.location = url;
	}-*/;

	

	private void updateFichaImovel() {
		if (this.ifcvo != null){
			afifc.update(this.ifcvo);
			Document doc = Document.get();
			if (doc != null){
				doc.setTitle("AlugueRelaxe :: " + this.ifcvo.imovel.descricaoTituloAnuncio);
			}
		}
	}

	public void onMaisDadosImovelClick() {
		dpMaisInfo.remove(widgetCenter);
		widgetCenter = new MaisDadosImovelFormComposite();
		((MaisDadosImovelFormComposite) widgetCenter).update(this.ifcvo);
		dpMaisInfo.add(widgetCenter, DockPanel.CENTER);
	}

	public void onLocalizacaoClick() {
		dpMaisInfo.remove(widgetCenter);
		widgetCenter = new LocalizacaoFormComposite();
		((LocalizacaoFormComposite) widgetCenter).update(this.ifcvo);
		dpMaisInfo.add(widgetCenter, DockPanel.CENTER);
	}

	public void onGaleriaFotosClick() {
		dpMaisInfo.remove(widgetCenter);
		widgetCenter = new GaleriaFotosFormComposite();
		((GaleriaFotosFormComposite)widgetCenter).update(this.ifcvo);
		dpMaisInfo.add(widgetCenter, DockPanel.CENTER);
	}


	public void onTarifasClick() {
		dpMaisInfo.remove(widgetCenter);
		widgetCenter = new TarifaFormComposite();
		((TarifaFormComposite)widgetCenter).update(this.ifcvo);
		dpMaisInfo.add(widgetCenter, DockPanel.CENTER);
		
	}
	
	private void gravarCookieFicha(ContatoClienteVO ccvo){
		if (ccvo.nome != null){
			Cookies.setCookie("fcafc.nome",ccvo.nome);
		}
		
		if (ccvo.email != null) {
			Cookies.setCookie("fcafc.email",ccvo.email);
		}
		
		if (ccvo.ddd != null) {
			Cookies.setCookie("fcafc.ddd",ccvo.ddd);
		}
		
		if (ccvo.telefone != null) {
			Cookies.setCookie("fcafc.tel",ccvo.telefone);
		}
		
		if (ccvo.informacoesAdicionais != null) {
			Cookies.setCookie("fcafc.ia",ccvo.informacoesAdicionais);
		}
	}
	
	private ContatoClienteVO lerCookieFicha() {
		ContatoClienteVO ccvo = new ContatoClienteVO();

		if (Cookies.getCookie("fcafc.nome") != null){
			ccvo.nome = Cookies.getCookie("fcafc.nome");
		}


		if (Cookies.getCookie("fcafc.email") != null){
			ccvo.email = Cookies.getCookie("fcafc.email");
		}

		if (Cookies.getCookie("fcafc.ddd") != null){
			ccvo.ddd = Cookies.getCookie("fcafc.ddd");
		}

		if (Cookies.getCookie("fcafc.tel") != null){
			ccvo.telefone = Cookies.getCookie("fcafc.tel");
		}

		if (Cookies.getCookie("fcafc.ia") != null){
			ccvo.informacoesAdicionais = Cookies.getCookie("fcafc.ia");
		}
		return ccvo;
	}

	public void onContatoAnuncianteClick() {
		dpMaisInfo.remove(widgetCenter);
		VerticalPanel vpContato = new VerticalPanel();
		Image btn = new Image(PATH_BOTOES + "enviar.png");
		btn.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
			
				// grava cookie da ficha enquanto a sessao do browser estiver ativa
				FichaImovel.this.gravarCookieFicha(fcafc.getVO());
				
				FichaImovelRPCAsync rpc = ServicosRPC.getFichaImovelRPC();
				AsyncCallback<ContatoClienteVO> callback = new AsyncCallback<ContatoClienteVO>(){

					public void onFailure(Throwable caught) {
						try {
								throw caught;
						} catch (IncompatibleRemoteServiceException e) {
							fcafc.setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
						} catch (InvocationException e) {
							fcafc.setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
						} catch (AlugueRelaxeFrontException e) {
							if ((e.getListaErros() != null) && (e.getListaErros().size() > 0)) {
								fcafc.setMensagem(e.getListaErros(), AreaNotificacao.NOTIFICACAO_ERRO);
							} else {
								fcafc.setMensagem(e.getMensagem(), AreaNotificacao.NOTIFICACAO_ERRO);
							}
						} catch (Throwable e) {
							fcafc.setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
						}					
					}

					public void onSuccess(ContatoClienteVO result) {
						ConfirmarDialogModal cdm = new ConfirmarDialogModal("Contato com Anunciante", result.msgcodeString, ConfirmarDialogModal.TIPO_MODAL_INFO );
					}
					
				};
				rpc.entrarEmContatoComAnunciante(fcafc.getVO(), callback);
			}
		});
		
		fcafc = new FichaContatoAnuncianteFormComposite();
		fcafc.update(this.ifcvo);
		fcafc.update(this.lerCookieFicha());
		vpContato.add(fcafc);
		vpContato.add(btn);
		widgetCenter = vpContato;
		dpMaisInfo.add(widgetCenter, DockPanel.CENTER);
	}


	public void onDisponibilidadeClick() {
		dpMaisInfo.remove(widgetCenter);
		widgetCenter = new OcupacaoFormComposite();
		((OcupacaoFormComposite)widgetCenter).update(null);
		dpMaisInfo.add(widgetCenter, DockPanel.CENTER);
	}

	public void onObservacoesGeraisClick() {
		dpMaisInfo.remove(widgetCenter);
		widgetCenter = new ObservacoesFormComposite();
		((ObservacoesFormComposite)widgetCenter).update(this.ifcvo);
		dpMaisInfo.add(widgetCenter, DockPanel.CENTER);
	}

	public void onCaracteristicasImovelClick() {
		dpMaisInfo.remove(widgetCenter);
		widgetCenter = new CaracteristicaConfiguradaFormComposite();
		((CaracteristicaConfiguradaFormComposite)widgetCenter).update(this.ifcvo, "I");
		dpMaisInfo.add(widgetCenter, DockPanel.CENTER);
	}

	public void onCaracteristicasCondominioClick() {
		dpMaisInfo.remove(widgetCenter);
		widgetCenter = new CaracteristicaConfiguradaFormComposite();
		((CaracteristicaConfiguradaFormComposite)widgetCenter).update(this.ifcvo, "C");
		dpMaisInfo.add(widgetCenter, DockPanel.CENTER);
	}

	public void onIndicarAmigoClick() {
		dpMaisInfo.remove(widgetCenter);
		VerticalPanel vpContato = new VerticalPanel();
		Button btn = new Button("Enviar");
		btn.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				FichaImovelRPCAsync rpc = ServicosRPC.getFichaImovelRPC();
				AsyncCallback<VOPadrao> callback = new AsyncCallback<VOPadrao>(){

					public void onFailure(Throwable caught) {
						try {
								throw caught;
						} catch (IncompatibleRemoteServiceException e) {
							iafc.setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
						} catch (InvocationException e) {
							iafc.setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
						} catch (AlugueRelaxeFrontException e) {
							if ((e.getListaErros() != null) && (e.getListaErros().size() > 0)) {
								iafc.setMensagem(e.getListaErros(), AreaNotificacao.NOTIFICACAO_ERRO);
							} else {
								iafc.setMensagem(e.getMensagem(), AreaNotificacao.NOTIFICACAO_ERRO);
							}
						} catch (Throwable e) {
							iafc.setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
						}					
					}

					public void onSuccess(VOPadrao result) {
						iafc.setMensagem("Sua indica\u00e7\u00e3o foi enviada com sucesso.", AreaNotificacao.NOTIFICACAO_INFO);
					}
					
				};
				rpc.indicarImovelAmigo(ifcvo, iafc.getVO(), callback);
			}
		});
		
		iafc = new IndicarAmigoFormComposite();
		vpContato.add(iafc);
		vpContato.add(btn);
		widgetCenter = vpContato;
		dpMaisInfo.add(widgetCenter, DockPanel.CENTER);
	}

	public void onPortaRetratoClick(PortaRetrato portaRetrato) {
		//-----------------------------------------------------
		// Invoca a ficha com o novo imovel selecionado
		//-----------------------------------------------------
		ImovelFichaCompletaVO vo = portaRetrato.getFichaImovelPortaRetrato();
		update(vo.imovel.id, "LD");
	}

	public void onVideoImovelClick() {
		dpMaisInfo.remove(widgetCenter);
		widgetCenter = new VideoImovelFormComposite();
		((VideoImovelFormComposite)widgetCenter).update(this.ifcvo.videoImovel);
		dpMaisInfo.add(widgetCenter, DockPanel.CENTER);
	}
	
}
