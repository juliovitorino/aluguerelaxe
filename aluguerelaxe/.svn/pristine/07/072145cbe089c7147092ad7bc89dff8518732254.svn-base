package br.com.jcv.aluguerelaxe.client.imovel.reserva;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.ReservaRPCAsync;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.ReservaVO;
import br.com.jcv.aluguerelaxe.client.componente.editpanel.AbstractFormEditPanel;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.notificacao.AreaNotificacao;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.NenhumaToolbar;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.NenhumaToolbarListener;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ContatoClienteVO;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.FichaImovelRPCAsync;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ThreadComentarioImovelVisitanteFormComposite;
import br.com.jcv.aluguerelaxe.client.imovel.thread.AnuncioGridThreadImovelFormComposite;
import br.com.jcv.aluguerelaxe.client.imovel.thread.ContatoAnuncianteDetalhesFormComposite;
import br.com.jcv.aluguerelaxe.client.imovel.thread.ContatoAnuncianteThreadVO;
import br.com.jcv.aluguerelaxe.client.imovel.thread.ThreadFazerNovaPerguntaFormComposite;
import br.com.jcv.aluguerelaxe.client.imovel.thread.ThreadImovelAdminFormComposite;
import br.com.jcv.aluguerelaxe.client.imovel.thread.ThreadImovelAnuncianteFormComposite;
import br.com.jcv.aluguerelaxe.client.imovel.thread.ThreadImovelVisitanteFormComposite;
import br.com.jcv.aluguerelaxe.client.imovel.thread.ThreadResponderFormComposite;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;

public class AvaliacaoReservaImovelEditPanel 
	extends AbstractFormEditPanel<NenhumaToolbar, FormComposite<?>, ContatoClienteVO>
		implements 	NenhumaToolbarListener {

	public static final String PROPRIETARIO_VISAO_VISITANTE = "V";
	public static final String PROPRIETARIO_VISAO_ANUNCIANTE = "A";
	public static final String PROPRIETARIO_VISAO_ADMINISTRADOR = "M";
	private static final String THREAD_SOMENTE_LEITURA = "Negocia\u00e7\u00e3o somente para leitura. "+
	"Todas os procedimentos para modifica\u00e7\u00f5es est\u00e3o desabilitados";

	private String omc;
	private String originador;
	private ThreadResponderFormComposite tfnpfc;
	private ContatoClienteVO ccvo;
	private ReservaVO rvo;
	private boolean callbackReserva;
	private boolean callbackContatoAnunciante;
	
	public AvaliacaoReservaImovelEditPanel(NenhumaToolbar toolbar) {
		super(toolbar);
		
	}

	@Override
	public ContatoClienteVO getVO(FormComposite<?> composite) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContatoClienteVO getVO(List<FormComposite<?>> composite) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	public void update(String hash, String omc) {
		//---------------------------------------------------------------------
		// Preserva o codigo do proprietario da visao (VISITANTE ou ANUNCIANTE)
		// para controlar os botoes de pergunta e resposta nos formComposite
		//---------------------------------------------------------------------
		this.omc = omc;

		FichaImovelRPCAsync rpc = ServicosRPC.getFichaImovelRPC();
		AsyncCallback<ContatoClienteVO> callback = new AsyncCallback<ContatoClienteVO>() {
		
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

		 public void onSuccess(ContatoClienteVO result) {
			 if (result != null){
				AvaliacaoReservaImovelEditPanel.this.update(result);
			 }
		 }
		};
		
		// Busca ficha de contato e suas respectivas thread
		rpc.pesquisarContatoAnunciante(hash, callback);
		
	}
*/
	public void onFazerNovaPerguntaClick() {
		if (ccvo != null){
			if (ccvo.threadStatus.equals("A")) {
				tfnpfc.setVisible(true);
			} else if (ccvo.threadStatus.equals("I")) {
				this.getAreaNotificacao().setMensagem(THREAD_SOMENTE_LEITURA, AreaNotificacao.NOTIFICACAO_WARNING);
			}
		}
	}

	@Override
	public void update() {

		// Os dois callbacks retornaram com sucesso e carregados
		if((callbackReserva) && (callbackContatoAnunciante)) {
			if ((rvo != null) && (ccvo != null)) {
				if (ccvo.reserva == null){
					ccvo.reserva = rvo;
				}
				//---------------------------------------------------------------------------------
				// Antes de montar os formularios valida o codigo omc recebido com o enviado na URL
				//---------------------------------------------------------------------------------
				if ((!this.omc.equals(ccvo.codigoOMCThreadVisitante)) && 
					(!this.omc.equals(ccvo.codigoOMCThreadAnunciante)) &&
					(!this.omc.equals(ccvo.codigoOMCThreadAdmin)) ){
					 this.getAreaNotificacao().setMensagem("Proprietario incorreto.", AreaNotificacao.NOTIFICACAO_ERRO);
				} else {
				
					// seta a propriedade que vai controlar os botoes de resposta e pergunta dos FCs
					if (this.omc.equals(ccvo.codigoOMCThreadVisitante)) {
						originador = PROPRIETARIO_VISAO_VISITANTE;
					} else if (this.omc.equals(ccvo.codigoOMCThreadAnunciante)){
						originador = PROPRIETARIO_VISAO_ANUNCIANTE;
					} else {
						originador = PROPRIETARIO_VISAO_ADMINISTRADOR;
					}			
				}
				//---------------------------------------------------------------------------------
				// Apresenta o formulario correto de acordo com o originador
				//---------------------------------------------------------------------------------
				if (originador.equals("V")){
					// TESTAR AQUI SE O COMENTARIO/TESTEMUNHO JA FOI FEITO
					//....
					
					
					// Apresenta formulário para visitante expressar seu testemunho
					AvaliacaoReservaImovelFormComposite threadfc = new AvaliacaoReservaImovelFormComposite();
					this.addObjetoCompositeToPanel(threadfc);
					threadfc.update(rvo, ccvo);
				} else if (originador.equals("A")){
					// Apresenta testemunho para o anunciante e abre formulário de resposta
					ThreadComentarioImovelVisitanteFormComposite threadfc = new ThreadComentarioImovelVisitanteFormComposite();
					this.addObjetoCompositeToPanel(threadfc);
					boolean respondido = false;
					if ((ccvo.lstComentarios != null) && (ccvo.lstComentarios.size() > 0)){
						ContatoAnuncianteThreadVO catparentvo = null;
						for (ContatoAnuncianteThreadVO catvo : ccvo.lstComentarios){
							threadfc.update(catvo,ccvo);
							catparentvo = catvo;
							if (catvo.threadfilho != null){
								respondido = true;
							}
							break;
						}
						// Este segundo formulario so pode ser apresentando se a thread filho for null
						if (! respondido){
							ThreadResponderFormComposite trfc = new ThreadResponderFormComposite();
							this.addObjetoCompositeToPanel(trfc);
							trfc.update(ccvo, catparentvo, originador, "CO");
						}
					}
					
					
				} else {
					// Emite um erro porque nao sabemos a origem da chamada
				}
			}
		}
	}

	public void update(String hash, String omc) {
		this.omc = omc;
		
		// Inicializa controlador de retorno dos callbacks
		callbackReserva = false;
		callbackContatoAnunciante = false;

		//-----------------------------------------
		// Obtem uma interface RPC para reserva
		//-----------------------------------------
		ReservaRPCAsync rpc = ServicosRPC.getReservaRPC();
		AsyncCallback<ReservaVO> callback = new AsyncCallback<ReservaVO>(){
		
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
				AvaliacaoReservaImovelEditPanel.this.callbackReserva = true;
				AvaliacaoReservaImovelEditPanel.this.rvo = result;
				AvaliacaoReservaImovelEditPanel.this.update();
			}
				
		};
		rpc.getReserva(hash, callback);

		//-----------------------------------------
		// Obtem uma interface RPC para FichaImovel
		//-----------------------------------------
		FichaImovelRPCAsync rpcifcdto = ServicosRPC.getFichaImovelRPC();
		AsyncCallback<ContatoClienteVO> callbackifcdto = new AsyncCallback<ContatoClienteVO>(){
		
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

			public void onSuccess(ContatoClienteVO result) {
				AvaliacaoReservaImovelEditPanel.this.callbackContatoAnunciante = true;
				AvaliacaoReservaImovelEditPanel.this.ccvo = result;
				AvaliacaoReservaImovelEditPanel.this.update();
			}
				
		};
		rpcifcdto.pesquisarContatoAnunciante(hash, callbackifcdto);

	}

}
