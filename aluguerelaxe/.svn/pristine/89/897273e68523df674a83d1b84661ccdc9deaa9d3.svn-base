package br.com.jcv.aluguerelaxe.client.componente.datagrid;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 *<h2>AbstractFormPesquisa</h2>
 *<p>Classe responsável pelo comportamento padrão das composições de obtencao de parametros de lancar para @link{AbstractDataGrid}.
 *</p>
 * <h2>CSS Style Rules</h2>
 * <ul>
 * <li>.gwt-AbstractFormPesquisa {estilo primario}</li>
 * </ul>
 * @author Julio Vitorino
 */
public abstract class AbstractFormPesquisa<P extends AbstractDataGridParameter<?>, 
					G extends AbstractDataGrid> extends Composite {
	
	private static final String IMAGEM_PATH = "images/botoes/";
	private static final String IMG_SELECIONAR = "selecionar.png";
	private static final String IMG_CANCELAR = "cancelar.png";

	private Image imgSelecionar;
	private Image imgCancelar;
	private DockPanel dp;
	private P params;
	private G datagrid;
	private List<AbstractFormPesquisaListener> listener;
	protected VerticalPanel rodape;
	protected VerticalPanel header;
	
	public abstract void setupGrid(G grid);
	public abstract String getFormPesquisaWidth();
	public abstract String getFormPesquisaHeight();

	/**
	 *<h2>AbstractFormPesquisa</h2>
	 *<p>Encapsula uma instância de @link{FormComposite} e inicia o Widget de Composite atraves do método @link{render}
	 *</p>
	 * @param FiltroListener
	 */
	public AbstractFormPesquisa(P params,G datagrid) {
		this.params = params;
		this.datagrid = datagrid;
		this.init();
		initWidget(this.render());  
		this.setStyleName("gwt-AbstractFormPesquisa");
		this.setupGrid(this.datagrid);
		this.dp.setSize(getFormPesquisaWidth(), getFormPesquisaHeight());
	}
	
	/**
	 *<h2>addListener</h2>
	 *<p>Inscreve a instância da classe que implementa @link{FormPesquisaListener} na lista de ouvintes.
	 *</p>
	 * @param FormPesquisaListener
	 */
	public void addListener(AbstractFormPesquisaListener fl) {
		if (this.listener == null) {
			this.listener = new ArrayList<AbstractFormPesquisaListener>();
		}
		this.listener.add(fl);
	}
	
	/**
	 *<h2>addRodape</h2>
	 *<p>Encapsula um espaço para um rodapé. Caso deseje uma renderização fora deste padrão, basta sobreescrever o método <code>addRodape</code>. 
	 * @param Widget
	 */
	protected void addRodape(Widget widget) {
		this.rodape.add(widget);
	}
	
	/**
	 *<h2>addHeader</h2>
	 *<p>Encapsula um espaço para um header. Caso deseje uma renderização fora deste padrão, basta sobreescrever o método <code>addHeader</code>. 
	 * @param Widget
	 */
	protected void addHeader(Widget widget) {
		this.header.add(widget);
	}
	
	/**
	 *<h2>init</h2>
	 *<p>Iniciarlizar alguns widgets</p> 
	 */
	private void init() {
		
		// Container principal
		DockPanel dp = new DockPanel();
		
		// Cria o botão selecionar
		imgSelecionar = new Image(IMAGEM_PATH + IMG_SELECIONAR);	
		imgSelecionar.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				// desmarca os outros seletores se a definicao da  grid for de selecao simples
				if (! AbstractFormPesquisa.this.datagrid.getDefinicaoDataGrid().isSelecaoMultiplosRegistros()){
					// Obtem a linha da grid marcada e envia evento para quem
					if (AbstractFormPesquisa.this.listener != null){
						if (AbstractFormPesquisa.this.datagrid.isCheckedItem()){
							RegDataGridVO rdgvo = AbstractFormPesquisa.this.datagrid.getPrimeiroItemChecked();
							for (AbstractFormPesquisaListener lstnr : AbstractFormPesquisa.this.listener){
								lstnr.onSelecionarClick(rdgvo);
							}
						}
					}

				} else {
					// Envia uma lista de linhas marcadas
					if (AbstractFormPesquisa.this.listener != null){
						// Verifica se tem  pelo menos 1 itens marcardo
						List<RegDataGridVO> lstRegistros = null;
						if (AbstractFormPesquisa.this.datagrid.isCheckedItem()){
							lstRegistros = AbstractFormPesquisa.this.datagrid.getTodosItemChecked();
							for (AbstractFormPesquisaListener lstnr : AbstractFormPesquisa.this.listener){
								lstnr.onSelecionarClick(lstRegistros);
							}
						}
					}
				}
			}

		});
		
		// Cria o botão cancelar
		imgCancelar = new Image(IMAGEM_PATH + IMG_CANCELAR);	
		imgCancelar.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				for (AbstractFormPesquisaListener lstnr : AbstractFormPesquisa.this.listener){
					lstnr.onCancelarClick();
				}
			}
		});
		

		// Inscreve datagrid no parameter
		this.params.addListener(this.datagrid); 
	}


	/**
	 *<h2>render</h2>
	 *<p>Encapsula a instância de @link{FormComposite} dentro de um container juntamente com um botão para ativar o evento onClick.
	 *</p>
	 *<p>Caso deseje uma renderização fora deste padrão, basta sobreescrever o método <code>render</code>. NUNCA ESQUEÇA DE ADICIONAR O
	 *<code>objeto.addClickHandler(this);</code>
	 *</p>
	 * @param C
	 */
	protected Widget render() {
		dp = new DockPanel();

		//Cria container para absorcer cabecalhos e rodape		
		header = new VerticalPanel();
		rodape = new VerticalPanel();
		
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(imgSelecionar);
		hp.add(imgCancelar);

		HorizontalPanel hpRodape = new HorizontalPanel();
		hpRodape.add(rodape);
		hpRodape.add(hp);
		
		VerticalPanel vpHeader = new VerticalPanel();
		vpHeader.add(header);
		vpHeader.add(this.params);

		// Monta composicao final
		dp.add(hpRodape, DockPanel.SOUTH);
		dp.add(vpHeader, DockPanel.NORTH);
		dp.add(this.datagrid, DockPanel.EAST);

		return dp;
	}

}