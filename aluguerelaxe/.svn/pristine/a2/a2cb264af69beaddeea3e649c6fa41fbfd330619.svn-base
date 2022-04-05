package br.com.jcv.aluguerelaxe.client.componente.advanceddatagrid;



import java.util.ArrayList;
import java.util.List;

import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.componente.datagrid.CondicaoVO;
import br.com.jcv.aluguerelaxe.client.componente.datagrid.DefinicaoDataGrid;
import br.com.jcv.aluguerelaxe.client.componente.datagrid.EnvelopePaginacaoDataGridVO;
import br.com.jcv.aluguerelaxe.client.componente.datagrid.RegDataGridVO;
import br.com.jcv.aluguerelaxe.client.componente.paginacao.PaginacaoRPCAsync;

import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;

/**
* <h2>AbstractAdvancedDataGrid</h2>
* <p>Criacao dos comportamentos da grid de dados
* </p>
* @author Julio
*/

public abstract class AbstractAdvancedDataGrid extends DataGrid<RegDataGridVO> 
	implements AsyncCallback<EnvelopePaginacaoDataGridVO> {
				
	
	private static final int MAXIMO_LINHAS = 30;
	private static final int COLUNA_SELETOR = 0;
	private static final int PAGINA_INICIAL = 1;
	private static final String PATH_IMAGEM = "images/16x16/";
	private static final String PATH_IMAGEM_GIF = "images/gif/";
	private static final String WAIT_IMAGEM = "ajax-loading.gif";
	private static final String STYLE = "gwt-AbstractDataGrid";
	
	private AdvancedDefinicaoDataGrid definicao;
	private int maximoLinhas;
	private int indicePagina;
	private int totalPaginas;
	private List<AbstractAdvancedDataGridListener> listener;
	
	public abstract AdvancedDefinicaoDataGrid getInstanceDefinicaoDataGrid();
	
	public AbstractAdvancedDataGrid() {
		super();
		this.maximoLinhas = MAXIMO_LINHAS;
		this.indicePagina = PAGINA_INICIAL;
		this.definicao = this.getInstanceDefinicaoDataGrid();
		init();
		//this.setStyleName(STYLE);
	}
	
	private void update(EnvelopePaginacaoDataGridVO envelope) {
		if ((envelope != null) && 
		    (envelope.lst != null) && 
			(envelope.lst.size() > 0)) {
			// Set the total row count. This isn't strictly necessary, but it affects
			// paging calculations, so its good habit to keep the row count up to date.
			this.setRowCount( (int) envelope.totalRegistros, true);

			// Push the data into the widget.
			this.setRowData(0, envelope.lst);

		}
	}
	
	public AdvancedDefinicaoDataGrid getDefinicaoDataGrid(){
		return this.definicao;
	}
	
	public void addCondicao(CondicaoVO condicao) {
		if (this.definicao != null){
			this.definicao.addCondicao(condicao);
		}
	}
	
	
	public void addListener(AbstractAdvancedDataGridListener listener) {
		if (this.listener == null){
			this.listener = new ArrayList<AbstractAdvancedDataGridListener>();
		}
		this.listener.add(listener);
	}
	
	@SuppressWarnings("unchecked")
	private void init() {
		List<AbstractDefinicaoCampoDataGrid<?>> lst = this.definicao.getListCampos();
		if ((lst != null) && (lst.size() > 0)){
			for(AbstractDefinicaoCampoDataGrid<?> def : lst){
				this.addColumn(def.getColDef(), def.getHeader());

			}
			this.refresh();
		}
	}
	
	public void update(int pagina) {
		//wait.setVisible(true);
		PaginacaoRPCAsync rpc = ServicosRPC.getPaginacaoRPC();
		AsyncCallback<EnvelopePaginacaoDataGridVO> callback = this;
		rpc.buscarPagina(this.definicao.getTabelaView(), 
						this.definicao.getList(), 
						this.definicao.getParam(), 
						this.definicao.getListCondicao(), 
						this.definicao.getListOrderBy(),
						pagina, 
						maximoLinhas, 
						callback);
	}
	
	public void onFailure(Throwable caught) {
		 try {
			//wait.setVisible(false);
		   throw caught;
		 } catch (IncompatibleRemoteServiceException e) {
			//
		 } catch (InvocationException e) {
			//
		 } catch (Throwable e) {
			//
		 }
	 }

	public void onSuccess(EnvelopePaginacaoDataGridVO result) {
			// atualizar linhas da grid
			this.update(result);
	}
	
	public void setMaximoLinhas(int maximoLinhas) {
		this.maximoLinhas = maximoLinhas;
	}

/** ParameterListener	
	public void onProcurarClick(List<CondicaoVO> lst) {
		// Limpa as condicoes pre-existentes
		this.definicao.clearCondicao();
		
		// Se existir condicao, envia-as.
		if ((lst != null) && (lst.size() > 0)){
			for (CondicaoVO vo : lst){
				this.addCondicao(vo);
			}
		}
		this.refresh();
	}
*/	

	private void refresh() {
		// Manda grid se atualizar
		this.indicePagina = PAGINA_INICIAL;
		update(this.indicePagina);
	}
	
}