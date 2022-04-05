package br.com.jcv.aluguerelaxe.client.componente.datagrid;

import java.util.ArrayList;
import java.util.List;

import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 *<h2>AbstractDataGridParameter</h2>
 *<p>Classe responsável pelo comportamento padrão das composições de obtencao de parametros. Ela usa uma implementação de @link{FormComposite}
 * para encapsular em <code>filtroComposite</code> e depois poder enviar um evento <code>onProcurarClick()</code> com o conteudo
 * do filtro informado através de um <code>getListVO()</code>.
 *</p>
 * <h2>CSS Style Rules</h2>
 * <ul>
 * <li>.gwt-AbstractDataGridParameter {estilo primario}</li>
 * </ul>
 * @author Julio Vitorino
 */
public abstract class AbstractDataGridParameter<C extends FormComposite<? extends VOPadrao>> extends Composite {
	
	private static final String IMAGEM_PATH = "images/botoes/";
	private static final String IMG_PROCURAR = "buscar.png";
	private static final String IMG_LIMPAR = "limpar.png";
	
	private Image imgProcurar;
	private Image imgLimpar;

	private List<DataGridParameterListener> listener;
	private C filtroComposite;
	protected VerticalPanel rodape;
	protected VerticalPanel header;
	private Button btn;

	public abstract List<CondicaoVO> getListVO();

	
	/**
	 *<h2>AbstractFiltro</h2>
	 *<p>Encapsula uma instância de @link{FormComposite} e inicia o Widget de Composite atraves do método @link{render}
	 *</p>
	 * @param C
	 */
	public AbstractDataGridParameter(C filtroComposite) {
		this.filtroComposite = filtroComposite;
		init();
		initWidget(this.render(this.filtroComposite));  
		this.setStyleName("gwt-AbstractDataGridParameter");
	}
	
	/**
	 *<h2>getFiltroComposite</h2>
	 *<p>Retorna a instancia de generics C.
	 *</p>
	 * @param DatagridParameterListener
	 */
	 public C getFiltroComposite() {
		return this.filtroComposite;
	 }
	
	
	/**
	 *<h2>addListener</h2>
	 *<p>Inscreve a instância da classe que implementa @link{DatagridParameterListener} na lista de ouvintes.
	 *</p>
	 * @param DatagridParameterListener
	 */
	public void addListener(DataGridParameterListener fl) {
		if (this.listener == null) {
			this.listener = new ArrayList<DataGridParameterListener>();
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
	 *<h2>setLabelButton</h2>
	 *<p>Configura um novo valor para o texto do botão 
	 * @param Widget
	 */
	protected void setLabelButton(String txt) {
		this.btn.setText(txt);
	}
	/**
	 *<h2>clear</h2>
	 *<p>Limpa o conteudo do formComposite 
	 */
	protected void clear() {
		this.filtroComposite.clear();
	}

	/**
	 *<h2>init</h2>
	 *<p>Iniciarlizar alguns widgets</p> 
	 */
	private void init() {
		// Cria widget do botao pesquisar
		imgProcurar = new Image(IMAGEM_PATH + IMG_PROCURAR);	
		imgProcurar.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				// Se a lista de ouvintes existir envia evento para cada um deles com o conteudo do formulario
				if (AbstractDataGridParameter.this.listener != null) {
					for (DataGridParameterListener fl : AbstractDataGridParameter.this.listener) {
						fl.onProcurarClick(AbstractDataGridParameter.this.getListVO());
					}
				}
				
			}
		});
		
		// Cria widget do botao limpar
		imgLimpar = new Image(IMAGEM_PATH + IMG_LIMPAR);	
		imgLimpar.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				AbstractDataGridParameter.this.filtroComposite.clear();
				
			}
		});

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
	protected Widget render(C filtroComposite) {
		VerticalPanel vp = new VerticalPanel();
		HorizontalPanel hp = new HorizontalPanel();

		// Coloca os botoes lado a lado
		hp.add(imgProcurar);
		hp.add(imgLimpar);

		//Cria container para absorcer cabecalhos e rodape		
		header = new VerticalPanel();
		rodape = new VerticalPanel();
		
		vp.add(header);
		vp.add(filtroComposite);
		vp.add(hp);
		vp.add(rodape);

		return vp;
	}

}