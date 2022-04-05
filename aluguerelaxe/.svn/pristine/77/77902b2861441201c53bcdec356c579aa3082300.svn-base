package br.com.jcv.aluguerelaxe.client.administracao.console.publicidade.compra;

import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.listbox.PlanosTipoPublicidadeListBox;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

public class ComprarPublicidadePeriodoFormComposite extends FormComposite<ComprarPublicidadePeriodoVO> {

	private DateBox dataInicio;
	private DateBox dataFinal;
	private PlanosTipoPublicidadeListBox tipoCompra;
	private TextBox dias;
	
	public ComprarPublicidadePeriodoFormComposite() {
		super();
	}

	@Override
	public ComprarPublicidadePeriodoVO getVO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(ComprarPublicidadePeriodoVO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Widget render() {
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(new HTML("<span>De:</span>"));
		hp.add(dataInicio);
		hp.add(new HTML("<span>&nbsp;Até:</span>"));
		hp.add(dataFinal);
		hp.add(new HTML("<span>&nbsp;</span>"));
		hp.add(tipoCompra);
		hp.add(new HTML("<span>&nbsp;</span>"));
		hp.add(dias);

		return hp;	
	}

	@Override
	public void init() {
		dataInicio = new DateBox();
		dataFinal = new DateBox();
		tipoCompra = new PlanosTipoPublicidadeListBox();
		dias = new TextBox();
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifier(ComprarPublicidadePeriodoVO vo) {
		// TODO Auto-generated method stub
		
	}

}
