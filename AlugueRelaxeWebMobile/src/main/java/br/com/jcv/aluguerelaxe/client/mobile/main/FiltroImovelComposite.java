package br.com.jcv.aluguerelaxe.client.mobile.main;

import br.com.jcv.aluguerelaxe.client.mobile.componente.listbox.CidadesListComImoveisBox;
import br.com.jcv.aluguerelaxe.client.mobile.componente.listbox.UFListBox;
import br.com.jcv.aluguerelaxe.client.mobile.vo.EnderecoVO;
import br.com.jcv.aluguerelaxe.client.mobile.vo.ImovelFichaCompletaVO;
import br.com.jcv.aluguerelaxe.client.mobile.vo.ImovelVO;
import br.com.jcv.ui.client.componente.form.FormComposite;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class FiltroImovelComposite extends FormComposite<EnderecoVO> {

	@UiField
	VerticalPanel vp;

	private UFListBox lstuf;
	private CidadesListComImoveisBox lstCidades;

	private static FiltroImovelCompositeUiBinder binder = GWT.create(FiltroImovelCompositeUiBinder.class);

	@UiTemplate("FiltroImovelComposite.ui.xml")
	interface FiltroImovelCompositeUiBinder extends UiBinder<Widget, FiltroImovelComposite> {
	}
	
	public FiltroImovelComposite() {
		super();
		//this.setStylePrimaryName("gwt-FormComposite-FiltroImovelComposite");
	}
	
	@Override
	public EnderecoVO getVO() {
		EnderecoVO vo = new EnderecoVO();
		vo.uf = lstuf.getValue(lstuf.getSelectedIndex()) ; 
		vo.codigoUfCidadeItem = Integer.valueOf(lstCidades.getValue(lstCidades.getSelectedIndex())); 
		return vo;
	}

	@Override
	public void update(EnderecoVO vo) {
		//Nao aplicado neste contexo
		
	}

	@Override
	public Widget render() {
		return binder.createAndBindUi(this);
		
	}
	
	private Widget renderBox(String titulo, Widget UI){
		VerticalPanel hp = new VerticalPanel();
		hp.add(new Label(titulo));
		hp.add(UI);
		return hp;
	}

	@Override
	public void init() {
		lstuf = new UFListBox(false);
		lstCidades = new CidadesListComImoveisBox(true);
		lstuf.addChangeHandler(lstCidades);

		vp.add(renderBox("UF", lstuf));
		vp.add(renderBox("Cidade", lstCidades));
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifier(EnderecoVO vo) {
		// TODO Auto-generated method stub
		
	}

}
