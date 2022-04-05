package br.com.jcv.aluguerelaxe.client.imovel.ficha;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.componente.editpanel.AbstractFormEditPanel;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.notificacao.AreaNotificacao;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.NenhumaToolbar;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.NenhumaToolbarListener;
import br.com.jcv.aluguerelaxe.client.imovel.thread.ContatoAnuncianteThreadVO;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;

public class ThreadComentarioImovelEditPanel 
	extends AbstractFormEditPanel<NenhumaToolbar, FormComposite<?>, ContatoAnuncianteThreadVO>
		implements 	NenhumaToolbarListener {

	public static final String PROPRIETARIO_VISAO_VISITANTE = "V";
	public static final String PROPRIETARIO_VISAO_ANUNCIANTE = "A";
	public static final String PROPRIETARIO_VISAO_ADMINISTRADOR = "M";

	public ThreadComentarioImovelEditPanel(NenhumaToolbar toolbar) {
		super(toolbar);
	}

	
	public void update(List<ContatoClienteVO> ccvo) {
		if ((ccvo != null) && (ccvo.size() > 0)) {
			for (ContatoClienteVO ccitem : ccvo){
				if ((ccitem.lstComentarios != null) && (ccitem.lstComentarios.size() > 0)) {
					// Aprensenta cada comentario
					for (ContatoAnuncianteThreadVO catvo : ccitem.lstComentarios){
						ThreadComentarioImovelVisitanteFormComposite tcivfc = new ThreadComentarioImovelVisitanteFormComposite();
						this.addObjetoCompositeToPanel(tcivfc);
						tcivfc.update(catvo, ccitem);
						break;
					}
				}
			}
		}
	}
	
	
	
	public void update(ContatoClienteVO ccvo) {
		if (ccvo != null) {
			//this.update(ccvo.lstComentarios);
			if ((ccvo.lstComentarios != null) && (ccvo.lstComentarios.size() > 0)) {
				// Aprensenta cada comentario
				for (ContatoAnuncianteThreadVO catvo : ccvo.lstComentarios){
					ThreadComentarioImovelVisitanteFormComposite tcivfc = new ThreadComentarioImovelVisitanteFormComposite();
					this.addObjetoCompositeToPanel(tcivfc);
					tcivfc.update(catvo, ccvo);
				}
			}
		}
	}
/*	
	public void update(List<ContatoAnuncianteThreadVO> lstcatvo) {
		
		if ((lstcatvo != null) && (lstcatvo.size() > 0)) {
			// Aprensenta cada comentario
			for (ContatoAnuncianteThreadVO catvo : lstcatvo){
				ThreadComentarioImovelVisitanteFormComposite tcivfc = new ThreadComentarioImovelVisitanteFormComposite();
				this.addObjetoCompositeToPanel(tcivfc);
				tcivfc.update(catvo);
			}
		}
	}
*/
	public void update(long idImovel) {
		FichaImovelRPCAsync rpc = ServicosRPC.getFichaImovelRPC();
		AsyncCallback<List<ContatoClienteVO>> callback = new AsyncCallback<List<ContatoClienteVO>>() {
		
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

		 public void onSuccess(List<ContatoClienteVO> result) {
			 if (result != null){
				ThreadComentarioImovelEditPanel.this.update(result);
			 }
		 }
		};
		
		// Busca ficha de contato e suas respectivas thread
		rpc.listarComentarioImovel(idImovel, callback);
		
	}


	@Override
	public ContatoAnuncianteThreadVO getVO(FormComposite<?> composite) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ContatoAnuncianteThreadVO getVO(List<FormComposite<?>> composite) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
