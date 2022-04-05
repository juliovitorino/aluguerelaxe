package br.com.jcv.aluguerelaxe.client.imovel.reserva;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.ReservaVO;
import br.com.jcv.aluguerelaxe.client.componente.editpanel.AbstractFormEditPanel;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.notificacao.AreaNotificacao;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.NenhumaToolbar;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.NenhumaToolbarListener;
import br.com.jcv.aluguerelaxe.client.componente.wizard.WizardListener;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ContatoClienteVO;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.FichaImovelRPCAsync;
import br.com.jcv.aluguerelaxe.client.imovel.thread.AnuncioGridThreadImovelFormComposite;
import br.com.jcv.aluguerelaxe.client.imovel.thread.ContatoAnuncianteThreadVO;
import br.com.jcv.aluguerelaxe.client.imovel.thread.ThreadImovelAdminFormComposite;
import br.com.jcv.aluguerelaxe.client.imovel.thread.ThreadImovelAnuncianteFormComposite;
import br.com.jcv.aluguerelaxe.client.imovel.thread.ThreadImovelVisitanteFormComposite;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;

public class ReservaImovelEditPanel 
	extends AbstractFormEditPanel<NenhumaToolbar, FormComposite<?>, ReservaVO>
		implements NenhumaToolbarListener {

	public static final String PROPRIETARIO_VISAO_VISITANTE = "V";
	public static final String PROPRIETARIO_VISAO_ANUNCIANTE = "A";
	public static final String PROPRIETARIO_VISAO_ADMINISTRADOR = "M";

	private String omc;
	private String originador;
	
	public ReservaImovelEditPanel(NenhumaToolbar toolbar) {
		super(toolbar);
		
	}
	
	public void update(ContatoClienteVO ccvo) {
	
		//---------------------------------------------------------------------------------
		// Monta assistente para coletar as informacoes do visitante e assim realizar
		// o preenchimento da ficha de reserva
		//---------------------------------------------------------------------------------
		AssistenteReservaImovelFormComposite arifc = new AssistenteReservaImovelFormComposite(); 
		this.addObjetoCompositeToPanel(arifc);
		arifc.update(ccvo);
	
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
			
			AnuncioGridReservaImovelFormComposite agtifc = new AnuncioGridReservaImovelFormComposite();
			agtifc.update(ccvo.ifcvo);
			
			// Apresenta em forma de anuncio o imovel visitado/consultado 
			this.addObjetoCompositeToPanel(agtifc);
			
			// Adiciona o formulario de nova pergunta
			/*
			tfnpfc = new ThreadFazerNovaPerguntaFormComposite();
			this.addObjetoCompositeToPanel(tfnpfc);
			tfnpfc.update(ccvo, originador);
			tfnpfc.setVisible(false);
			*/
			
			// Apresenta thread por thread
			if ((ccvo.lstThread != null) && (ccvo.lstThread.size() > 0)) {
				for (ContatoAnuncianteThreadVO vo : ccvo.lstThread){
					if (vo.origem.equals("V")){
						ThreadImovelVisitanteFormComposite threadfc = new ThreadImovelVisitanteFormComposite();
						this.addObjetoCompositeToPanel(threadfc);
						threadfc.update(ccvo, vo, omc, originador);
					} else if (vo.origem.equals("A")){
						ThreadImovelAnuncianteFormComposite threadfc = new ThreadImovelAnuncianteFormComposite();
						this.addObjetoCompositeToPanel(threadfc);
						threadfc.update(ccvo, vo);
					} else {
						ThreadImovelAdminFormComposite threadfc = new ThreadImovelAdminFormComposite();
						this.addObjetoCompositeToPanel(threadfc);
						threadfc.update(ccvo, vo);
						
					}
				}
			}
		}
	}

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
				ReservaImovelEditPanel.this.update(result);
			 }
		 }
		};
		
		// Busca ficha de contato e suas respectivas thread
		rpc.pesquisarContatoAnunciante(hash, callback);
		
	}

	@Override
	public ReservaVO getVO(FormComposite<?> composite) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReservaVO getVO(List<FormComposite<?>> composite) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}


}
