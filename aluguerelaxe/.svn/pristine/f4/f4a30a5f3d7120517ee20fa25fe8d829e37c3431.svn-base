package br.com.jcv.aluguerelaxe.client.componente.filtro;

import java.util.ArrayList;
import java.util.List;

import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 *<h2>AbstractFiltro</h2>
 *<p>Classe responsável pelo comportamento padrão das composições de filtro. Ela usa uma implementação de @link{FormComposite}
 * para encapsular em <code>filtroComposite</code> e depois poder enviar um evento <code>onFiltroAplicar</code> com o conteudo
 * do filtro informado através de um <code>getVO()</code>.
 *</p>
 * <h2>CSS Style Rules</h2>
 * <ul>
 * <li>.gwt-AbstractFiltro {estilo primario}</li>
 * </ul>
 * @author Julio Vitorino
 */
public abstract class AbstractFiltro<V extends VOPadrao, C extends FormComposite<V>> extends Composite implements ClickHandler {

	private List<FiltroListener> listener;
	private C filtroComposite;
	protected VerticalPanel rodape;
	protected VerticalPanel header;
	private Button btn;
	
	/**
	 *<h2>AbstractFiltro</h2>
	 *<p>Encapsula uma instância de @link{FormComposite} e inicia o Widget de Composite atraves do método @link{render}
	 *</p>
	 * @param FiltroListener
	 */
	public AbstractFiltro(C filtroComposite) {
		this.filtroComposite = filtroComposite;
		initWidget(this.render(this.filtroComposite));
		this.setStylePrimaryName("gwt-AbstractFiltro");
	}
	
	/**
	 *<h2>addFiltroListener</h2>
	 *<p>Inscreve a instância da classe que implementa @link{FiltroListener} na lista de ouvintes.
	 *</p>
	 * @param FiltroListener
	 */
	public void addFiltroListener(FiltroListener fl) {
		if (this.listener == null) {
			this.listener = new ArrayList<FiltroListener>();
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
		
		btn = new Button("Filtrar");
		btn.addClickHandler(this);

		header = new VerticalPanel();
		rodape = new VerticalPanel();
		
		vp.add(header);
		vp.add(filtroComposite);
		vp.add(btn);
		vp.add(rodape);

		return vp;
	}
	
	/**
	 * Emite um evento <code>onFiltroAplicar(V)</code> para cada instância inscrita de FiltroListener
	 */
	public void onClick(ClickEvent event) {
		for (FiltroListener fl : this.listener) {
			fl.onFiltroAplicar(this, this.filtroComposite.getVO());
		}
	}

}