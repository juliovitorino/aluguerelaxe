package br.com.jcv.aluguerelaxe.client.administracao.console.tarefaspendentes;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.ReservaRPCAsync;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.ReservaVO;
import br.com.jcv.aluguerelaxe.client.administracao.login.SessaoVO;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.form.FormCompositeListener;

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;

public class TarefasPendentesTreeFormComposite extends FormComposite<TarefasPendentesTreeVO> {

	public static final String ROOT_APROVACAO = "Aprova\u00e7\u00e3o";
	public static final String ROOT_CONFIRMACAO_DEPOSITO = "Confirma\u00e7\u00e3o de Dep\u00f3sito";
	public static final String ROOT_LIBERACAO_DEPOSITO = "Solicita\u00e7\u00e3o de Libera\u00e7\u00e3o de Dep\u00f3sito";
	public static final String ROOT_TRANSFERENCIA_DEPOSITO = "Transfer\u00eancia de Dep\u00f3sito";
	public static final String ROOT_AVALIACAO = "Avalia\u00e7\u00e3o";

	private Tree treeTP;
	private TreeItem tiAprovacao;
	private TreeItem tiConfirmacaoDeposito;
	private TreeItem tiSolicitacaoLiberacaoDeposito;
	private TreeItem tiTransferenciaDeposito;
	private TreeItem tiAvaliacao;
	
	private SessaoVO sessao;
	
	@SuppressWarnings("unchecked")
	public void init() {
		// cria a treeview e associa seu pacote de imagens
		//TreeImages images = (TreeImages) GWT.create(TarefasPendentesTreeImages.class);
		//treeTP = new Tree(images);
		treeTP = new Tree();
		
		// cria seus roots
		tiAprovacao = new TreeItem(ROOT_APROVACAO);
		tiConfirmacaoDeposito = new TreeItem(ROOT_CONFIRMACAO_DEPOSITO);
		tiSolicitacaoLiberacaoDeposito = new TreeItem(ROOT_LIBERACAO_DEPOSITO);
		tiTransferenciaDeposito = new TreeItem(ROOT_TRANSFERENCIA_DEPOSITO);
		tiAvaliacao = new TreeItem(ROOT_AVALIACAO);
		
		// associa os roots a treeview
		treeTP.addItem(tiAprovacao);
		treeTP.addItem(tiConfirmacaoDeposito);
		treeTP.addItem(tiSolicitacaoLiberacaoDeposito);
		treeTP.addItem(tiTransferenciaDeposito);
		treeTP.addItem(tiAvaliacao);
		
		//Cria a associacao com eventos
//		treeTP.addMouseDownHandler(new MouseDownHandler() {
//			
//			public void onMouseDown(MouseDownEvent event) {
//				Tree tree = (Tree) event.getSource();
//				TreeItem node = tree.getSelectedItem();
//				TarefasPendentesTreeVO vo = new TarefasPendentesTreeVO();
//				vo.codigoTracking = node.getText();
//				vo.sessaovo = TarefasPendentesTreeFormComposite.this.sessao;
//				vo.secaoTreeview = node.getParentItem().getText();
//				TarefasPendentesTreeFormComposite.this.notifier(vo);
//			}
//		});
		

		treeTP.addSelectionHandler(new SelectionHandler() {
			public void onSelection(SelectionEvent event) {
				Tree tree = (Tree) event.getSource();
				TreeItem node = tree.getSelectedItem();
				TarefasPendentesTreeVO vo = new TarefasPendentesTreeVO();
				vo.codigoTracking = node.getText();
				vo.sessaovo = TarefasPendentesTreeFormComposite.this.sessao;
				vo.secaoTreeview = node.getParentItem().getText();
				TarefasPendentesTreeFormComposite.this.notifier(vo);
			}
	        
	      });
		
		
	}

	public Widget render() {
		ScrollPanel vp = new ScrollPanel();
		vp.setSize("300px", "520px");

		vp.add(treeTP);
		return vp;
	}

	@Override
	public TarefasPendentesTreeVO getVO() {
		return null;
	}
	
	@Override
	public void update(TarefasPendentesTreeVO result){
	}
	
	public void update(SessaoVO sessao){
		this.sessao = sessao;
	}
	
	public void update() {
		buscarTarefasPendentesAprovacao();
		buscarTarefasPendentesConfirmacaoDeposito();
		buscarTarefasPendentesSolicitacaoLiberacaoDeposito();
		buscarTarefasPendentesTransferenciaDeposito();
		buscarTarefasPendentesAvaliacao();
	}

	@Override
	public void clear() {
	}

	@Override
	public void notifier(TarefasPendentesTreeVO vo) {
		// Notifica todos os listener que estão inscritos
		if ( (this.listener != null) && (this.listener.size() > 0) ){
			for (FormCompositeListener fcl : this.listener) {
				TarefasPendentesTreeFormCompositeListener tcfcl = (TarefasPendentesTreeFormCompositeListener) fcl;
				tcfcl.onNodeTarefasPendentesClick(vo);
			}
		}
	}
	
	private void buscarTarefasPendentesAprovacao() {
		ReservaRPCAsync rpc = ServicosRPC.getReservaRPC();
		AsyncCallback<List<ReservaVO>> callback = new AsyncCallback<List<ReservaVO>>() {

				public void onFailure(Throwable caught) {
//				     try {
//				       throw caught;
//				     } catch (IncompatibleRemoteServiceException e) {
//						getAreaNotificacao().setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
//				     } catch (InvocationException e) {
//						getAreaNotificacao().setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
//				     } catch (AlugueRelaxeFrontException e) {
//				    	 if ((e.getListaErros() != null) && (e.getListaErros().size() > 0)) {
//				    		 getAreaNotificacao().setMensagem(e.getListaErros(), AreaNotificacao.NOTIFICACAO_ERRO);
//				    	 } else {
//				    		 getAreaNotificacao().setMensagem(e.getMensagem(), AreaNotificacao.NOTIFICACAO_ERRO);
//				    	 }
//				     } catch (Throwable e) {
//						getAreaNotificacao().setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
//				     }
				 }

				 public void onSuccess(List<ReservaVO> result) {
					 TarefasPendentesTreeFormComposite.this.popularNode(ROOT_APROVACAO, result);
				 }
				};
				
		rpc.listarTarefasPendentes("AP", callback);	
	}
	
	private void buscarTarefasPendentesConfirmacaoDeposito() {
		ReservaRPCAsync rpc = ServicosRPC.getReservaRPC();
		AsyncCallback<List<ReservaVO>> callback = new AsyncCallback<List<ReservaVO>>() {

				public void onFailure(Throwable caught) {
//				     try {
//				       throw caught;
//				     } catch (IncompatibleRemoteServiceException e) {
//						getAreaNotificacao().setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
//				     } catch (InvocationException e) {
//						getAreaNotificacao().setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
//				     } catch (AlugueRelaxeFrontException e) {
//				    	 if ((e.getListaErros() != null) && (e.getListaErros().size() > 0)) {
//				    		 getAreaNotificacao().setMensagem(e.getListaErros(), AreaNotificacao.NOTIFICACAO_ERRO);
//				    	 } else {
//				    		 getAreaNotificacao().setMensagem(e.getMensagem(), AreaNotificacao.NOTIFICACAO_ERRO);
//				    	 }
//				     } catch (Throwable e) {
//						getAreaNotificacao().setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
//				     }
				 }

				 public void onSuccess(List<ReservaVO> result) {
					 TarefasPendentesTreeFormComposite.this.popularNode(ROOT_CONFIRMACAO_DEPOSITO, result);
				 }
				};
				
		rpc.listarTarefasPendentes("CD", callback);	
	}
	
	private void buscarTarefasPendentesSolicitacaoLiberacaoDeposito() {
		ReservaRPCAsync rpc = ServicosRPC.getReservaRPC();
		AsyncCallback<List<ReservaVO>> callback = new AsyncCallback<List<ReservaVO>>() {

				public void onFailure(Throwable caught) {
//				     try {
//				       throw caught;
//				     } catch (IncompatibleRemoteServiceException e) {
//						getAreaNotificacao().setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
//				     } catch (InvocationException e) {
//						getAreaNotificacao().setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
//				     } catch (AlugueRelaxeFrontException e) {
//				    	 if ((e.getListaErros() != null) && (e.getListaErros().size() > 0)) {
//				    		 getAreaNotificacao().setMensagem(e.getListaErros(), AreaNotificacao.NOTIFICACAO_ERRO);
//				    	 } else {
//				    		 getAreaNotificacao().setMensagem(e.getMensagem(), AreaNotificacao.NOTIFICACAO_ERRO);
//				    	 }
//				     } catch (Throwable e) {
//						getAreaNotificacao().setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
//				     }
				 }

				 public void onSuccess(List<ReservaVO> result) {
					 TarefasPendentesTreeFormComposite.this.popularNode(ROOT_LIBERACAO_DEPOSITO, result);
				 }
				};
				
		rpc.listarTarefasPendentes("LD", callback);	
	}
	
	private void buscarTarefasPendentesTransferenciaDeposito() {
		ReservaRPCAsync rpc = ServicosRPC.getReservaRPC();
		AsyncCallback<List<ReservaVO>> callback = new AsyncCallback<List<ReservaVO>>() {

				public void onFailure(Throwable caught) {
//				     try {
//				       throw caught;
//				     } catch (IncompatibleRemoteServiceException e) {
//						getAreaNotificacao().setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
//				     } catch (InvocationException e) {
//						getAreaNotificacao().setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
//				     } catch (AlugueRelaxeFrontException e) {
//				    	 if ((e.getListaErros() != null) && (e.getListaErros().size() > 0)) {
//				    		 getAreaNotificacao().setMensagem(e.getListaErros(), AreaNotificacao.NOTIFICACAO_ERRO);
//				    	 } else {
//				    		 getAreaNotificacao().setMensagem(e.getMensagem(), AreaNotificacao.NOTIFICACAO_ERRO);
//				    	 }
//				     } catch (Throwable e) {
//						getAreaNotificacao().setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
//				     }
				 }

				 public void onSuccess(List<ReservaVO> result) {
					 TarefasPendentesTreeFormComposite.this.popularNode(ROOT_TRANSFERENCIA_DEPOSITO, result);
				 }
				};
				
		rpc.listarTarefasPendentes("TD", callback);	
	}
	
	private void buscarTarefasPendentesAvaliacao(){
		ReservaRPCAsync rpc = ServicosRPC.getReservaRPC();
		AsyncCallback<List<ReservaVO>> callback = new AsyncCallback<List<ReservaVO>>() {

				public void onFailure(Throwable caught) {
//				     try {
//				       throw caught;
//				     } catch (IncompatibleRemoteServiceException e) {
//						getAreaNotificacao().setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
//				     } catch (InvocationException e) {
//						getAreaNotificacao().setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
//				     } catch (AlugueRelaxeFrontException e) {
//				    	 if ((e.getListaErros() != null) && (e.getListaErros().size() > 0)) {
//				    		 getAreaNotificacao().setMensagem(e.getListaErros(), AreaNotificacao.NOTIFICACAO_ERRO);
//				    	 } else {
//				    		 getAreaNotificacao().setMensagem(e.getMensagem(), AreaNotificacao.NOTIFICACAO_ERRO);
//				    	 }
//				     } catch (Throwable e) {
//						getAreaNotificacao().setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
//				     }
				 }

				 public void onSuccess(List<ReservaVO> result) {
					 TarefasPendentesTreeFormComposite.this.popularNode(ROOT_AVALIACAO, result);
				 }
				};
				
		rpc.listarTarefasPendentes("AV", callback);	
	}
	
	private void popularNode(String idNode, List<ReservaVO> lst) {
		TreeItem node = null;
		boolean erro = false;

		if (idNode.equals(ROOT_APROVACAO)) {
			node = tiAprovacao;
		} else if(idNode.equals(ROOT_CONFIRMACAO_DEPOSITO)) {
			node = tiConfirmacaoDeposito;
		} else if(idNode.equals(ROOT_LIBERACAO_DEPOSITO)) {
			node = tiSolicitacaoLiberacaoDeposito;
		} else if(idNode.equals(ROOT_TRANSFERENCIA_DEPOSITO)) {
			node = tiTransferenciaDeposito;
		} else if(idNode.equals(ROOT_AVALIACAO)) {
			node = tiAvaliacao;
		} else {
			erro = true;
		}
		
		if (!erro) {
			popularNode(node, lst);
		}
		
	}

	private void popularNode(TreeItem node, List<ReservaVO> lst) {
		if (lst != null) {
			for(ReservaVO vo : lst) {
				node.addItem(new Label(vo.chaveTracker));
			}
		}
	}



}
