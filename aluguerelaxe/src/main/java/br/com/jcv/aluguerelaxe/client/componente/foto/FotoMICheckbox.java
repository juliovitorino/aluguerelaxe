package br.com.jcv.aluguerelaxe.client.componente.foto;

import br.com.jcv.aluguerelaxe.client.imovel.imagem.ImovelImagemVideoVO;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;

public class FotoMICheckbox extends AbstractFoto {

	private CheckBox chkDeletar;
	
	public FotoMICheckbox(ImovelImagemVideoVO iivvo) {
		super(iivvo);
		this.getImg().setWidth("82px");
		this.getImg().setHeight("63px");
	}

	public Panel montaRodape() {
		HorizontalPanel hp = new HorizontalPanel();
		chkDeletar = new CheckBox();
		hp.add(chkDeletar);
		hp.add(new Label(" Apagar"));
		return hp;
	}
	
	public CheckBox getCheckDeletar(){
		//return (CheckBox) hp.getWidget(0);
		return this.chkDeletar;
	}

}
