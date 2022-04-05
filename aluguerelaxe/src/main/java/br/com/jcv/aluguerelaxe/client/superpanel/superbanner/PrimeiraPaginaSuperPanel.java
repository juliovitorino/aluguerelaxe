package br.com.jcv.aluguerelaxe.client.superpanel.superbanner;

import br.com.jcv.aluguerelaxe.client.componente.superpanel.AbstractSuperPanel;

import com.google.gwt.user.client.ui.HTML;

public class PrimeiraPaginaSuperPanel extends AbstractSuperPanel<SuperBannerVO> {
	
	public PrimeiraPaginaSuperPanel() {
		super();
	}
	
	public void update(SuperBannerVO vo){
		this.setHTML(new HTML(vo.html));
	}


}
