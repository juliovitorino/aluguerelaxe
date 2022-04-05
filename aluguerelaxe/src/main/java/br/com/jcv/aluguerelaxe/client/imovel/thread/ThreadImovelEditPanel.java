package br.com.jcv.aluguerelaxe.client.imovel.thread;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.componente.editpanel.AbstractFormEditPanel;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.notificacao.AreaNotificacao;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ContatoClienteVO;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.FichaImovelRPCAsync;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;

public class ThreadImovelEditPanel 
	extends AbstractFormEditPanel<ToolbarImovelThread, FormComposite<?>, ContatoClienteVO>
		implements 	ToolbarImovelThreadListener, 
					ThreadFazerNovaPerguntaFormCompositeListener {

	public static final String PROPRIETARIO_VISAO_VISITANTE = "V";
	public static final String PROPRIETARIO_VISAO_ANUNCIANTE = "A";
	public static final String PROPRIETARIO_VISAO_ADMINISTRADOR = "M";
	private static final String THREAD_SOMENTE_LEITURA = "Negocia\u00e7\u00e3o somente para leitura. "+
	"Todas os procedimentos para modifica\u00e7\u00f5es est\u00e3o desabilitados";

	private String omc;
	private String originador;
	private ThreadFazerNovaPerguntaFormComposite tfnpfc;
	private ContatoClienteVO ccvo;
	
	public ThreadImovelEditPanel(ToolbarImovelThread toolbar) {
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

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	public void update(ContatoClienteVO ccvo) {
		//---------------------------------------------------------------------------------
		// Preserva VO recebido para uso futuro
		//---------------------------------------------------------------------------------
		this.ccvo = ccvo;
		
		//---------------------------------------------------------------------------------
		// Verifica se a thread esta encerrada e emite aviso somente de leitura
		//---------------------------------------------------------------------------------
		if (ccvo.threadStatus.equals("I")){
			this.getAreaNotificacao().setMensagem(THREAD_SOMENTE_LEITURA, AreaNotificacao.NOTIFICACAO_WARNING);
		}
	
		//---------------------------------------------------------------------------------
		// Antes de montar os formularios valida o codigo omc recebido com o enviado na URL
		//---------------------------------------------------------------------------------
		if ((!this.omc.equals(ccvo.codigoOMCThreadVisitante)) && 
			(!this.omc.equals(ccvo.codigoOMCThreadAnunciante)) &&
			(!this.omc.equals(ccvo.codigoOMCThreadAdmin)) ){
    		 this.getAreaNotificacao().setMensagem("Propriet\u00e1rio incorreto.", AreaNotificacao.NOTIFICACAO_ERRO);
		} else {
		
			// seta a propriedade que vai controlar os botoes de resposta e pergunta dos FCs
			if (this.omc.equals(ccvo.codigoOMCThreadVisitante)) {
				originador = PROPRIETARIO_VISAO_VISITANTE;
			} else if (this.omc.equals(ccvo.codigoOMCThreadAnunciante)){
				originador = PROPRIETARIO_VISAO_ANUNCIANTE;
			} else {
				originador = PROPRIETARIO_VISAO_ADMINISTRADOR;
			}
			// Adiciona o formulario de nova pergunta
			tfnpfc = new ThreadFazerNovaPerguntaFormComposite();
			tfnpfc.addListener(this);
			this.addObjetoCompositeToPanel(tfnpfc);
			tfnpfc.update(ccvo, originador);
			tfnpfc.setVisible(false);
			
			// Mensagem de Boas vindas do Administrador
			if ((ccvo.lstThread != null) && (ccvo.lstThread.size() > 0)) {
				ContatoAnuncianteThreadVO vo = ccvo.lstThread.get(0);
				if (vo.origem.equals("M")){
					ThreadImovelAdminFormComposite threadfc = new ThreadImovelAdminFormComposite();
					this.addObjetoCompositeToPanel(threadfc);
					threadfc.update(ccvo, vo);
				}
			}
			
			// Apresenta em forma de anuncio o imovel visitado/consultado 
			AnuncioGridThreadImovelFormComposite agtifc = new AnuncioGridThreadImovelFormComposite();
			agtifc.update(ccvo.ifcvo);
			agtifc.update(ccvo);
			this.addObjetoCompositeToPanel(agtifc);
			
			// Adiciona um formulario com informacoes sobre a consulta
			ContatoAnuncianteDetalhesFormComposite cadfc = new ContatoAnuncianteDetalhesFormComposite();
			cadfc.update(ccvo);
			this.addObjetoCompositeToPanel(cadfc);
			
			// Aprensenta thread por thread
			if ((ccvo.lstThread != null) && (ccvo.lstThread.size() > 0)) {
				int idx = 0;
				for (ContatoAnuncianteThreadVO vo : ccvo.lstThread){
					if (vo.origem.equals("V")){
						ThreadImovelVisitanteFormComposite threadfc = new ThreadImovelVisitanteFormComposite();
						this.addObjetoCompositeToPanel(threadfc);
						threadfc.update(ccvo, vo, omc, originador);
					} else if (vo.origem.equals("A")){
						ThreadImovelAnuncianteFormComposite threadfc = new ThreadImovelAnuncianteFormComposite();
						this.addObjetoCompositeToPanel(threadfc);
						threadfc.update(ccvo, vo);
					} else if (vo.origem.equals("M") && (idx > 0)){
						ThreadImovelAdminFormComposite threadfc = new ThreadImovelAdminFormComposite();
						this.addObjetoCompositeToPanel(threadfc);
						threadfc.update(ccvo, vo);
					}
					idx++;
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
				ThreadImovelEditPanel.this.update(result);
			 }
		 }
		};
		
		// Busca ficha de contato e suas respectivas thread
		rpc.pesquisarContatoAnunciante(hash, callback);
		
	}

	public void onFazerNovaPerguntaClick() {
		if (ccvo != null){
			if (ccvo.threadStatus.equals("A")) {
				tfnpfc.setVisible(true);
			} else if (ccvo.threadStatus.equals("I")) {
				this.getAreaNotificacao().setMensagem(THREAD_SOMENTE_LEITURA, AreaNotificacao.NOTIFICACAO_WARNING);
			}
		}
	}

	public void onEnviarPerguntaClick() {
		tfnpfc.setVisible(false);
	}

	public void onCancelarClick() {
		tfnpfc.setVisible(false);
	}



}
