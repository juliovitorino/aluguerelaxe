package br.com.jcv.aluguerelaxe.client.administracao.console.publicidades;

import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.form.FormCompositeListener;
import br.com.jcv.aluguerelaxe.client.componente.listbox.AbstractPlanoListbox;
import br.com.jcv.aluguerelaxe.client.plano.PlanoVO;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractSelecaoPlanoFormComposite extends FormComposite<PlanoVO> {

	protected AbstractPlanoListbox aplb;
	private Button btn;

	@Override
	public PlanoVO getVO() {
		PlanoVO vo = new PlanoVO();
		vo.id = Long.valueOf( aplb.getValue(aplb.getSelectedIndex()));
		vo.indicadorTipoCompra = aplb.getTipoCompra();
		return vo;
	}

	@Override
	public void update(PlanoVO pvo) {

	}
	
	@Override
	public Widget render() {
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(new Label("Selecione um tipo de publicidade:"));
		hp.add(aplb);
		hp.add(btn);
		return hp;
	}

	@Override
	public void init() {
		btn = new Button("Pesquisar");
		btn.addClickHandler(new ClickHandler(){

			public void onClick(ClickEvent event) {
				AbstractSelecaoPlanoFormComposite.this.notifier(AbstractSelecaoPlanoFormComposite.this.getVO());
				
			}
		});
		
		//... sera sobrescrito pelas classes concretas para criar listbox de planos
		
	}
	
	@Override
	public void clear() {

	}

	@Override
	public void notifier(PlanoVO vo) {
		// Notifica as classes que estao escutando o evento
		if ( (this.listener != null) && (this.listener.size() > 0) ){
			for (FormCompositeListener fcl : this.listener) {
				SelecaoPlanoFormCompositeListener tcfcl = (SelecaoPlanoFormCompositeListener) fcl;
				tcfcl.onPesquisarPlanoClick(vo.id);
			}
		}
		

	}

}
