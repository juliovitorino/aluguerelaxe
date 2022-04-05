package br.com.jcv.aluguerelaxe.client.imovel.reserva;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.ReservaRPCAsync;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.ReservaVO;
import br.com.jcv.aluguerelaxe.client.componente.autoajuda.AutoAjudaTextArea;
import br.com.jcv.aluguerelaxe.client.componente.classificador.Classificador;
import br.com.jcv.aluguerelaxe.client.componente.dialog.ConfirmarDialogModal;
import br.com.jcv.aluguerelaxe.client.componente.dialog.ConfirmarDialogModalListener;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.notificacao.AreaNotificacao;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ContatoClienteVO;
import br.com.jcv.aluguerelaxe.client.imovel.imagem.ImovelImagemVideoVO;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class AvaliacaoReservaVisitanteFormComposite extends FormComposite<AvaliacaoReservaVO> {

	private static final String GWT_STYLE = "gwt-AvaliacaoVisitanteFormComposite";
	private static final String PATH_BOTOES = "images/botoes/";
	private static final String PATH_IMAGEM = "images/";
	private static final String PATH_IMAGEM_48x48 = "images/48x48/";
	
	private static final String AJUDA_COMENTARIO = "xxxxxxxxxxxx";
	
	private FotoVisitante fv;
	private AutoAjudaTextArea aata;
	private DockPanel dp;
	private ReservaVO rvo;
	private ContatoClienteVO ccvo;
	private Classificador imovelclass;
	private Classificador anfitriaoclass;
	private AreaNotificacao an;
	
	public AvaliacaoReservaVisitanteFormComposite() {
		super();
		this.setStyleName(GWT_STYLE);
	}
	
	@Override
	public void init() {
		aata = new AutoAjudaTextArea(AJUDA_COMENTARIO);
		imovelclass = new Classificador("Im\u00f3vel") ;
		anfitriaoclass = new Classificador("Anfitri\u00e3o") ;
		an = new AreaNotificacao();
	}
	
	@Override
	public Widget render() {
		dp = new DockPanel();
		dp.add(an,DockPanel.NORTH);
		
		// Cria um Vertical para acomodar a TextArea e Imagem Enviar
		VerticalPanel vp = new VerticalPanel();
		vp.add(aata);
		Image imgEnviar = new Image(PATH_BOTOES + "enviar.png");
		imgEnviar.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				AvaliacaoReservaVisitanteFormComposite.this.enviar();
			}
		});
		
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(new Image(PATH_IMAGEM_48x48 + "home.png"));
		hp.add(imovelclass);
		hp.add(new Image(PATH_IMAGEM_48x48 + "user1_edit.png"));
		hp.add(anfitriaoclass);
		hp.add(imgEnviar);
		
		vp.add(hp);
		
		// Adiciona panel a Dock
		dp.add(vp, DockPanel.CENTER);
		
		return dp;
	}
	
	public void update(ReservaVO rvo, ContatoClienteVO ccvo) {
		this.rvo = rvo;
		this.ccvo = ccvo;
		ImovelImagemVideoVO iivvo = new ImovelImagemVideoVO();
		if (rvo.imgVisitante != null){
			iivvo.nome = rvo.imgVisitante;
		} else {
			iivvo.nome = PATH_IMAGEM + "noface.png";
		}
		fv = new FotoVisitante(iivvo);
		dp.add(fv, DockPanel.WEST);
	}

	@Override
	public void clear() {
	}

	@Override
	public void notifier(AvaliacaoReservaVO vo) {
	}
	
	private void enviar(){
		// Obtem uma interface RPC para Reserva
		ReservaRPCAsync rpc = ServicosRPC.getReservaRPC();
		AsyncCallback<VOPadrao> callback = new AsyncCallback<VOPadrao>(){

			public void onFailure(Throwable caught) {
			     try {
			       throw caught;
			     } catch (IncompatibleRemoteServiceException e) {
			    	 AvaliacaoReservaVisitanteFormComposite.this.an.setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
			     } catch (InvocationException e) {
			    	 AvaliacaoReservaVisitanteFormComposite.this.an.setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
			     } catch (AlugueRelaxeFrontException e) {
			    	 if ((e.getListaErros() != null) && (e.getListaErros().size() > 0)) {
			    		 AvaliacaoReservaVisitanteFormComposite.this.an.setMensagem(e.getListaErros(), AreaNotificacao.NOTIFICACAO_ERRO);
			    	 } else {
			    		 AvaliacaoReservaVisitanteFormComposite.this.an.setMensagem(e.getMensagem(), AreaNotificacao.NOTIFICACAO_ERRO);
			    	 }
			     } catch (Throwable e) {
			    	 AvaliacaoReservaVisitanteFormComposite.this.an.setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
			     }
			 }

			public void onSuccess(VOPadrao result) {
				ConfirmarDialogModal cdm = new ConfirmarDialogModal("Testemunho de Temporada", result.msgcodeString, ConfirmarDialogModal.TIPO_MODAL_INFO);
				cdm.addConfirmarDialogModalListener(new ConfirmarDialogModalListener() {
					
					public void onSimClick() {
					}
					
					public void onOkClick() {
						redirect("http://www.aluguerelaxe.com.br");
					}
					
					public void onNaoClick() {
					}
					
					public void onCancelarClick() {
					}
				});

				
			}
		};
		rpc.enviarComentario(this.getVO(), callback);
	}
	
	public static native void redirect(String url)/*-{
		$wnd.location = url;
	}-*/;

	

	@Override
	public AvaliacaoReservaVO getVO() {
		AvaliacaoReservaVO arivo = new AvaliacaoReservaVO();
		arivo.contatoCliente = this.ccvo;
		arivo.reserva = this.rvo;
		try{
			arivo.reserva.notaAnfitriao = anfitriaoclass.getNotaClassificador().notaClassificador;
			arivo.reserva.notaImovel = imovelclass.getNotaClassificador().notaClassificador;
		} catch (Exception e){
			arivo.reserva.notaAnfitriao = 0;
			arivo.reserva.notaImovel = 0;
		}
		arivo.avaliacao = aata.getWidgetUI().getValue();
		return arivo;
	}

	@Override
	public void update(AvaliacaoReservaVO vo) {
		// TODO Auto-generated method stub
		
	}
	
}