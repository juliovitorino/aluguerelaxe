package br.com.jcv.aluguerelaxe.client.componente.datagrid;

import java.util.ArrayList;
import java.util.List;

import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.componente.paginacao.PaginacaoRPCAsync;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
* <h2>AbstractDataGrid</h2>
* <p>Criacao dos comportamentos da grid de dados
* </p>
* @author Julio
*/

public abstract class AbstractDataGrid extends Composite 
	implements AsyncCallback<EnvelopePaginacaoDataGridVO>, 
				NavegadorFormCompositeListener,
				DataGridParameterListener,
				SeletorDataGridListener,
				HeaderDataGridListener {
				
	
	public static final int COLPOS_1 = 0;
	public static final int COLPOS_2 = 1;
	public static final int COLPOS_3 = 2;
	public static final int COLPOS_4 = 3;
	public static final int COLPOS_5 = 4;
	public static final int COLPOS_6 = 5;
	public static final int COLPOS_7 = 6;
	public static final int COLPOS_8 = 7;
	public static final int COLPOS_9 = 8;
	public static final int COLPOS_10 = 9;
	public static final int COLPOS_11 = 10;
	public static final int COLPOS_12 = 11;
	public static final int COLPOS_13 = 12;
	public static final int COLPOS_14 = 13;
	public static final int COLPOS_15 = 14;
	public static final int COLPOS_16 = 15;
	public static final int COLPOS_17 = 16;
	public static final int COLPOS_18 = 17;
	public static final int COLPOS_19 = 18;
	public static final int COLPOS_20 = 19;
	public static final int COLPOS_21 = 20;
	public static final int COLPOS_22 = 21;
	public static final int COLPOS_23 = 22;
	public static final int COLPOS_24 = 23;
	public static final int COLPOS_25 = 24;
	public static final int COLPOS_26 = 25;
	public static final int COLPOS_27 = 26;
	public static final int COLPOS_28 = 27;
	public static final int COLPOS_29 = 28;
	public static final int COLPOS_30 = 29;
	public static final int COLPOS_31 = 30;
	public static final int COLPOS_32 = 31;
	public static final int COLPOS_33 = 32;
	public static final int COLPOS_34 = 33;
	public static final int COLPOS_35 = 34;
	public static final int COLPOS_36 = 35;
	public static final int COLPOS_37 = 36;
	public static final int COLPOS_38 = 37;
	public static final int COLPOS_39 = 38;
	public static final int COLPOS_40 = 39;
	private static final int MAXIMO_LINHAS = 30;
	private static final int COLUNA_SELETOR = 0;
	private static final int PAGINA_INICIAL = 1;
	private static final String PATH_IMAGEM = "images/16x16/";
	private static final String PATH_IMAGEM_GIF = "images/gif/";
	private static final String WAIT_IMAGEM = "ajax-loading.gif";
	private static final String STYLE = "gwt-AbstractDataGrid";
	
	private DefinicaoDataGrid definicao;
	private int maximoLinhas;
	private int indicePagina;
	private int totalPaginas;
	private Grid grid;
	private ScrollPanel sp;
	private VerticalPanel vpAbstractDataGrid;
	private List<AbstractDataGridListener> listener;
	private NavegadorFormComposite nfc;
	private Image wait;
	private String height;
	private String width;
	
	public abstract DefinicaoDataGrid getInstanceDefinicaoDataGrid();
	
	public AbstractDataGrid() {
		this.maximoLinhas = MAXIMO_LINHAS;
		this.indicePagina = PAGINA_INICIAL;
		this.definicao = this.getInstanceDefinicaoDataGrid();
		//this.setHeight("100%");
		//this.setWidth("100%");
		init();
		initWidget(render());
		this.setStyleName(STYLE);
		//update(this.indicePagina);
	}
	
	private void update(EnvelopePaginacaoDataGridVO envelope) {
		if ((envelope != null) && 
		    (envelope.lst != null) && 
			(envelope.lst.size() > 0)) {
			for ( RegDataGridVO registro : envelope.lst ) {
				this.addDataGrid(registro);
			}
			
			NavegadorVO nvo = new NavegadorVO();
			nvo.indicePagina = indicePagina;
			nvo.totalPaginas = efetuaCalculoDescobrirPaginas(envelope.totalRegistros);
			nvo.totalRegistros = envelope.totalRegistros;
			this.totalPaginas = nvo.totalPaginas;
			
			// Atualiza a NavegadorFormComposite
			nfc.update(nvo);
		}
		wait.setVisible(false);
	}
	
	public DefinicaoDataGrid getDefinicaoDataGrid(){
		return this.definicao;
	}
	
	public void setHeight(String height) {
		this.height = height;
		vpAbstractDataGrid.setHeight(height);
	}
	
	public void addCondicao(CondicaoVO condicao) {
		if (this.definicao != null){
			this.definicao.addCondicao(condicao);
		}
	}
	
	
	public void setWidth(String width) {
		this.width = width;
		vpAbstractDataGrid.setWidth(width);
	}

	public void addListener(AbstractDataGridListener listener) {
		if (this.listener == null){
			this.listener = new ArrayList<AbstractDataGridListener>();
		}
		this.listener.add(listener);
	}
	
	private void init() {
		vpAbstractDataGrid = new VerticalPanel();
		
		grid = new Grid(1, 1 + this.definicao.getCols());
		grid.setWidth("100%");
		grid.setHeight("100%");
		
		sp = new ScrollPanel();
		sp.setWidth("100%");
		sp.setHeight("100%");
		
		wait = new Image(PATH_IMAGEM_GIF + WAIT_IMAGEM);
		wait.setVisible(false);
		
		nfc = new NavegadorFormComposite();
		nfc.addListener(this);

	}
	
	protected Widget render() {
		this.montaHeader();
		
		// Adiciona Grid ao Scrollpanel e ao content principal
		sp.add(grid);
		vpAbstractDataGrid.add(sp);
		
		// Cria container para imagem de wait e navegador
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(wait);
		hp.add(nfc);
		vpAbstractDataGrid.add(hp);
		
		return vpAbstractDataGrid;
	}
	
	public void addDataGrid(RegDataGridVO rdg) {
		this.grid.resizeRows(this.grid.getRowCount() + 1);
		SeletorDataGrid seletor = new SeletorDataGrid(this.grid.getRowCount()-1);
		seletor.addListener(this);
		this.grid.setWidget(this.grid.getRowCount()-1, COLUNA_SELETOR, seletor);

		for (int col = 0; col < rdg.campo.length; col++) {
			this.grid.setWidget(this.grid.getRowCount()-1, col + 1, new Label(rdg.campo[col]));
		}
	}
	
	public void update(int pagina) {
		wait.setVisible(true);
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
			wait.setVisible(false);
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
			this.resetGridDataEntry();
			
			// atualizar linhas da grid
			this.update(result);
	}
	

	private void montaHeader() {
		// Monta headers
		List<DefinicaoCampoDataGrid> lst = this.definicao.getListCampos();
		if (lst != null){
			int i = 1;
			for (DefinicaoCampoDataGrid campo : lst ){
				// Cria o header da coluna com possibilidade de ordenacao e habilita resposta de evento 
				HeaderDataGrid hdg = new HeaderDataGrid(campo.getHeader(), i - 1); 
				hdg.addListener(this);
				grid.setWidget(0, i++, hdg);
			}
		}
	}
	
	// Deixa somente a linha de cabecalho da grid
	private void resetGridDataEntry() {
		if (this.grid.getRowCount() > 1) {
			for (int i = this.grid.getRowCount() - 1; i > 0; i--){
				this.grid.removeRow(i);
			}
		}
	}
	
	public void onPrimeiroClick() {
		this.indicePagina = PAGINA_INICIAL;
		this.update(this.indicePagina);
	}
	
	public void onUltimoClick() {
		this.indicePagina = this.totalPaginas;
		this.update(this.totalPaginas);
	}
	
	public void onProximoClick() {
		if (this.indicePagina + 1 <= this.totalPaginas){
			this.update(++this.indicePagina);
		}
	}
	
	public void onAnteriorClick() {
		if(this.indicePagina - 1 >= PAGINA_INICIAL){
			this.update(--this.indicePagina);
		}
	}
	
	public void setMaximoLinhas(int maximoLinhas) {
		this.maximoLinhas = maximoLinhas;
	}
	
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
	

	public void onDeselectClick(int linha) {
		// TODO Auto-generated method stub
		
	}	
	
	public void onSelectClick(int linha){
		// desmarca os outros seletores se a definicao da  grid for de selecao simples
		if (! this.definicao.isSelecaoMultiplosRegistros()){
			this.uncheckSeletores(linha);
			
			// Obtem a linha da grid marcada e envia evento para quem
			if (this.listener != null){
				RegDataGridVO rdgvo = getRegistroGrid(linha);
				for (AbstractDataGridListener lstnr : this.listener){
					lstnr.onSelectClick(rdgvo);
				}
			}

		} else {
			// Envia uma lista de linhas marcadas
			if (this.listener != null){
				// Verifica se tem  pelo menos 1 itens marcardo
				List<RegDataGridVO> lstRegistros = null;
				if (this.isCheckedItem()){
					lstRegistros = new ArrayList<RegDataGridVO>();
					for (int i = 1; i < this.grid.getRowCount(); i++){
						RegDataGridVO rdgvo = getRegistroGrid(i);
						lstRegistros.add(rdgvo);
					}
					for (AbstractDataGridListener lstnr : this.listener){
						lstnr.onSelectClick(lstRegistros);
					}
				}
			}
		}
		
	}
	
	public boolean isCheckedItem() {
		boolean marcado = false;
		for (int i = 1; i < this.grid.getRowCount(); i++){
			SeletorDataGrid seletor = (SeletorDataGrid) this.grid.getWidget(i,0);
			if (seletor.isTurnOn()){
				marcado = true;
				break;
			}
		}
		return marcado;
	}

	public RegDataGridVO getPrimeiroItemChecked() {
		RegDataGridVO rdgvo = null;

		if (this.isCheckedItem()) {
			for (int i = 1; i < this.grid.getRowCount(); i++){
				SeletorDataGrid seletor = (SeletorDataGrid) this.grid.getWidget(i,0);
				if (seletor.isTurnOn()){
					rdgvo = new RegDataGridVO();
					rdgvo.campo = new String[this.definicao.getCols()];
					
					for (int j = 1; j <= this.definicao.getCols(); j++ ){
						Label lbl = (Label) this.grid.getWidget(i, j);
						rdgvo.campo[j-1] = lbl.getText();
					}
					break;
				}
			}
		}
		return rdgvo;
	}
	
	public List<RegDataGridVO> getTodosItemChecked() {
		List<RegDataGridVO> lstrdgvo = null;

		if (this.isCheckedItem()) {
			lstrdgvo = new ArrayList<RegDataGridVO>(); 
			for (int i = 1; i < this.grid.getRowCount(); i++){
				SeletorDataGrid seletor = (SeletorDataGrid) this.grid.getWidget(i,0);
				if (seletor.isTurnOn()){
					RegDataGridVO rdgvo = new RegDataGridVO();
					rdgvo.campo = new String[this.definicao.getCols()];
					
					for (int j = 1; j <= this.definicao.getCols(); j++ ){
						Label lbl = (Label) this.grid.getWidget(j, i);
						rdgvo.campo[j] = lbl.getText();
					}
					
					lstrdgvo.add(rdgvo);
				}
			}
		}
		
		return lstrdgvo;
	}
	
	
	private RegDataGridVO getRegistroGrid(int linha){
		RegDataGridVO rdgvo = new RegDataGridVO();
		rdgvo.campo = new String[this.definicao.getCols()];
		
		for (int i = 1; i <= this.definicao.getCols(); i++ ){
			Label lbl = (Label) this.grid.getWidget(linha, i);
			rdgvo.campo[i] = lbl.getText();
		}
		
		return rdgvo;
	}
	
	private void uncheckSeletores(int linhaExcecao) {
		for (int i = 1; i < this.grid.getRowCount(); i++){
			if (i != linhaExcecao){
				SeletorDataGrid seletor = (SeletorDataGrid) this.grid.getWidget(i,0);
				seletor.turnOff();
			}
		}
	}
	
	public void onHeaderAddSortClick(int colunaPos){
		DefinicaoCampoDataGrid dcdg = this.definicao.getDefinicaoCampo(colunaPos);
		if(dcdg != null){
			this.definicao.addOrderBy(dcdg.getField());
			this.refresh();
		}
	}
	
	public void onHeaderRemoveSortClick(int colunaPos){
		DefinicaoCampoDataGrid dcdg = this.definicao.getDefinicaoCampo(colunaPos);
		if(dcdg != null){
			this.definicao.removeOrderBy(dcdg.getField());
			this.refresh();
		}
	}

	private int efetuaCalculoDescobrirPaginas(long nRegistros) {
		double paginas = nRegistros / maximoLinhas;
		int paginasCalculadas = Double.valueOf(String.valueOf(paginas)).intValue();
		if (paginasCalculadas == 0) {
			paginasCalculadas = 1;
		} else {
			if ((nRegistros % maximoLinhas) > 0) {
				paginasCalculadas++;
			}
		}
		return paginasCalculadas;
	}
	
	private void refresh() {
		// Manda grid se atualizar
		this.indicePagina = PAGINA_INICIAL;
		update(this.indicePagina);
	}
	
}