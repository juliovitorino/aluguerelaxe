package br.com.jcv.aluguerelaxe.client.mobile.componente.paginacao;

import java.util.HashMap;
import java.util.Map;

import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.mobile.vo.EnvelopePaginacaoVO;
import br.com.jcv.aluguerelaxe.client.mobile.vo.ImovelFichaCompletaVO;
import br.com.jcv.ui.client.componente.filtro.FiltroListener;
import br.com.jcv.ui.client.componente.toolbar.ToolbarNavegador;
import br.com.jcv.ui.client.componente.toolbar.ToolbarNavegadorListener;
import br.com.jcv.ui.client.vo.VOPadrao;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Classe que controla todo o comportamento de uma lista paginada
 *
 * @author Julio Vitorino
 *
 * @param <V>
 * @param <C>
 */
public abstract class AbstractListPaginada<V extends VOPadrao, C extends Composite> extends Composite 
			implements AsyncCallback<EnvelopePaginacaoVO>, ToolbarNavegadorListener, FiltroListener<V> {

	private int qtdeItensPorPagina;
	private VerticalPanel vpLista = new VerticalPanel();
	private VerticalPanel vpToolbarNavegador = new VerticalPanel();
	private VerticalPanel vpTrilhaPao = new VerticalPanel();
	private HTML htmlTrilhaPao = new HTML();
	private ToolbarNavegador toolbarNavegador;
	protected Map<String,String> param = new HashMap<String,String>();
	
	public AbstractListPaginada() {
		this.qtdeItensPorPagina = 10;
		initWidget(montaListaPaginacao());
	}

	protected abstract C renderItemLista(ImovelFichaCompletaVO vo);
	
	protected void updateTrilhaDePao(String trilha) {
		this.htmlTrilhaPao.setHTML("<h2>Im\u00f3veis em " + trilha + "</h2>");
	}
	
	private Widget montaListaPaginacao() {
		VerticalPanel vp = new VerticalPanel();
		this.toolbarNavegador = new ToolbarNavegador(this.qtdeItensPorPagina);
		this.toolbarNavegador.setToolbarNavegadorMobile(true);
		this.toolbarNavegador.addToolbarListener(this);
		vpToolbarNavegador.add(this.toolbarNavegador);
		
		vpTrilhaPao.add(htmlTrilhaPao);

		vp.add(vpTrilhaPao);
		vp.add(vpLista);
		vp.add(vpToolbarNavegador);
		return vp;
	}

	/**
	 * Método que invoca de forma assíncrona para buscar dados no backend paginado
	 */
	@SuppressWarnings("unchecked")
	protected void update(int indice) {
		PaginacaoRPCAsync rpc = ServicosRPC.getPaginacaoRPC();
		AsyncCallback<EnvelopePaginacaoVO> callback = this;
		rpc.buscarPagina(param, indice, this.qtdeItensPorPagina, callback);
	}

	public void onFailure(Throwable caught) {
	}

	/**
	 * renderiza do resultado da lista paginada obtida
	 */
	public void onSuccess(EnvelopePaginacaoVO result) {
		if (result != null) {
			vpLista.clear();
			if(result.lst != null){
				for (ImovelFichaCompletaVO vo : result.lst) {
					vpLista.add(renderItemLista(vo));
				}
				this.toolbarNavegador.setVisible(true);
				this.toolbarNavegador.update(result.indice, result.totalRegistros);
			} else {
				this.toolbarNavegador.setVisible(false);
				vpLista.add(renderItemNaoEncontrado());
			}
				
		}
	}
	
	/**
	 * Se quiser outra renderizacao para registros nao encontrados, basta criar uma sobrecarga
	 */
	protected Widget renderItemNaoEncontrado() {
		return new Label("Nenhum registro foi encontrado...");
	}
	
	/**
	 * processa o click realizado na toolbar do navegador, ou seja, atualiza a lista da grid com nova pesquisa
	 */
	public void onMoverParaInicio() {
		update(1);
		
	}

	public void onMoverParaFinal() {
		update(this.toolbarNavegador.getPaginas());
	}

	public void onMoverParaAnterior(int indice) {
		if (indice > 1) {
			update(indice - 1);
		} else {
			onMoverParaInicio();
		}
		
	}

	public void onMoverParaProximo(int indice) {
		if (indice < this.toolbarNavegador.getPaginas()) {
			update(indice + 1);
		} else {
			onMoverParaFinal();
		}
	}

	public void onItensPorPaginaChange(int item) {
		this.qtdeItensPorPagina = item;
		update(this.toolbarNavegador.getIndice());
	}
	
	
}

