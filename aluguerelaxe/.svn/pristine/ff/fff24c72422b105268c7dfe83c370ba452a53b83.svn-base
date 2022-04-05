package br.com.jcv.aluguerelaxe.client.imovel.reserva;

import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.ReservaRPCAsync;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.ReservaVO;
import br.com.jcv.aluguerelaxe.client.componente.chat.ChatFormComposite;
import br.com.jcv.aluguerelaxe.client.componente.chat.ChatVO;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ContatoClienteVO;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.FichaImovelRPCAsync;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class AvaliacaoReservaImovelFormComposite extends FormComposite<ReservaVO> {
	
	private static final String TEXTO_CHAT_ANUNCIANTE = "Ol\u00e1, " +
		"<p>Obrigado por usar nossas instala\u00e7\u00f5es. Por favor, pe\u00e7o que voc\u00ea nos avalie de uma maneira geral,<br/>"+
		"para que outras pessoas possam compartilhar de " +
		"sua experi\u00eancia.</p>" +
		"<p>Pedimos que voc\u00ea o fa\u00e7a com a maior sinceridade poss\u00edvel, para que o pr\u00f3ximo visitante " +
		"possa ter uma id\u00e9ia real de como poder\u00e1 ser a estadia dele.";
	
	private AvaliacaoReservaVisitanteFormComposite arvfc;
	private ChatFormComposite chatfc;
	private boolean callbackReserva;
	private boolean callbackContatoAnunciante;
	private ReservaVO rvo;
	private ContatoClienteVO ccvo;
	
	@Override
	public void init() {
		arvfc = new AvaliacaoReservaVisitanteFormComposite();
		chatfc = new ChatFormComposite();
	}
	
	@Override
	public Widget render() {
		VerticalPanel vp = new VerticalPanel();
		vp.add(chatfc);
		chatfc.setWidgetRodapeChat(arvfc);
		return vp;
	}
	
	public void update(ReservaVO rvo, ContatoClienteVO ccvo){
		this.rvo = rvo;
		this.ccvo = ccvo;
		callbackReserva = true;
		callbackContatoAnunciante = true;
		this.update();
		// é so pra teste de simulacao pois estes dois metodos updates serao fundidos
	}
	
	public void update() {
		// Os dois callbacks retornaram com sucesso e carregados
		if((callbackReserva) && (callbackContatoAnunciante)) {
			if ((rvo != null) && (ccvo != null)) {
				// Monta ChatVO e atualiza o ChatFormComposite com agradecimento do anunciante
				ChatVO chatvo = new ChatVO();
				chatvo.chat = rvo.locatario.nome + ", " + TEXTO_CHAT_ANUNCIANTE + "<br/>" +
				"per\u00edodo de loca\u00e7\u00e3o: " + rvo.dataCheckin.toString() + " at\u00e9 " + rvo.dataCheckout.toString() ;
				chatvo.dataPost = rvo.dataCheckout.toString();
				chatvo.titulo = "AVALIE MEU IM\u00d3VEL E NOSSA HOSPITALIDADE COMO ANFITRI\u00d5ES";
				chatvo.urlFoto = "/stream/fotos/" + rvo.ifcdto.cliente.id + "/" + rvo.ifcdto.cliente.fotoChat;
				chatfc.setWidgetRodapeFoto(new HTML(ccvo.ifcvo.cliente.primeiroNome));
				chatfc.update(chatvo);
				
				// Atualiza as informacoes em AvaliacaoReservaVisitanteFormComposite
				this.arvfc.update(rvo, ccvo);
			}
		}
	}

	@Deprecated
	public void update(String hash, String omc) {
		// Inicializa controlador de retorno dos callbacks
		callbackReserva = false;
		callbackContatoAnunciante = false;

		//-----------------------------------------
		// Obtem uma interface RPC para reserva
		//-----------------------------------------
		ReservaRPCAsync rpc = ServicosRPC.getReservaRPC();
		AsyncCallback<ReservaVO> callback = new AsyncCallback<ReservaVO>(){
		
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
			public void onSuccess(ReservaVO result) {
				AvaliacaoReservaImovelFormComposite.this.callbackReserva = true;
				AvaliacaoReservaImovelFormComposite.this.rvo = result;
				AvaliacaoReservaImovelFormComposite.this.update();
			}
				
		};
		rpc.getReserva(hash, callback);

		//-----------------------------------------
		// Obtem uma interface RPC para FichaImovel
		//-----------------------------------------
		FichaImovelRPCAsync rpcifcdto = ServicosRPC.getFichaImovelRPC();
		AsyncCallback<ContatoClienteVO> callbackifcdto = new AsyncCallback<ContatoClienteVO>(){
		
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
			public void onSuccess(ContatoClienteVO result) {
				AvaliacaoReservaImovelFormComposite.this.callbackContatoAnunciante = true;
				AvaliacaoReservaImovelFormComposite.this.ccvo = result;
				AvaliacaoReservaImovelFormComposite.this.update();
			}
				
		};
		rpcifcdto.pesquisarContatoAnunciante(hash, callbackifcdto);

	}

	@Override
	public void clear() {
	}

	@Override
	public void notifier(ReservaVO vo) {
		
	}

	@Override
	public ReservaVO getVO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(ReservaVO vo) {
		// TODO Auto-generated method stub
		
	}
	
}