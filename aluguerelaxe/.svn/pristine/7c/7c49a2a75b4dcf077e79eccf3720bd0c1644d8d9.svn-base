
package br.com.jcv.aluguerelaxe.client.componente.editpanel;

import java.util.ArrayList;
import java.util.List;

import br.com.jcv.aluguerelaxe.client.componente.notificacao.AreaNotificacao;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.AbstractToolbar;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.ToolbarListener;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Classe abstrata para o painel em edição
 *
 * @author Julio Vitorino
 *
 */
@SuppressWarnings("unchecked")
public abstract class AbstractEditPanel<T extends AbstractToolbar,C extends Composite, V> extends Composite implements ToolbarListener {
	
	public static final int ESTADO_ATUALIZAR = 0;
	public static final int ESTADO_ADICIONAR = 1;
	private static final String STYLE_TAG = "gwt-AbstractEditPanel";
	private static final String STYLE_TAG_GRID = "gwt-grid";
	
	private T toolbar;
	private List<C> lstObjetoComposite = new ArrayList<C>();
	protected Grid grid = new Grid();
	private int estado = ESTADO_ATUALIZAR;
	private AreaNotificacao fan = new AreaNotificacao();
	private VerticalPanel vpRodape = new VerticalPanel();
	
	protected int linhasGrid = 1;
	
	public AbstractEditPanel(T toolbar) {
		if (toolbar != null){
			this.toolbar = toolbar;
			this.toolbar.addToolbarListener(this);
		}
		initWidget(render());
		this.setStylePrimaryName(STYLE_TAG);
	}
	
	public void addObjetoCompositeToPanel(C objetoComposite) {
		this.lstObjetoComposite.add(objetoComposite);
		adicionaCompositeGrid(objetoComposite);
	}
	
	public List<V> getVO() {
		List<V> lst = null;
		
		if ((this.lstObjetoComposite != null) && 
			(this.lstObjetoComposite.size() > 0)) {
			lst = new ArrayList<V>();
			for (C item : this.lstObjetoComposite) {
				lst.add(getVO(item));
			}
		}
		return lst;
	}
	
	public abstract V getVO(C composite);
	public abstract V getVO(List<C> composite);
	public abstract void update();
	
	/**
	 * Método será sobreescrito nas classes que extendem a esta
	 *
	 * @param objetoComposite
	 */
	protected void adicionaCompositeGrid(C objetoComposite) {
	}

	public void removeTodosObjetoCompositeFromPanel() {
		linhasGrid = 1;
		if (this.lstObjetoComposite.size() > 0){
			for(int i = this.lstObjetoComposite.size(); i > 0; i--){
				this.lstObjetoComposite.remove(i-1);
			}
		}
			
		for(int i = this.grid.getRowCount(); i > 0; i--){
			this.grid.removeRow(i-1);
		}
	}

	public void removeObjetoCompositeFromPanel(C objetoComposite) {
		this.lstObjetoComposite.remove(objetoComposite);
	}
	
	public List<C> getListObjetoComposite() {
		return this.lstObjetoComposite;
	}
	
	private Widget render() {
		VerticalPanel vp = new VerticalPanel();
		vp.add(this.toolbar);
		vp.add(this.fan);
		vp.add(this.grid);
		vp.add(this.vpRodape);
		return vp;
	}
	
	protected void adicionarRodape(Widget widget) {
		vpRodape.add(widget);
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	public AreaNotificacao getAreaNotificacao() {
		return this.fan;
	}
	
}

