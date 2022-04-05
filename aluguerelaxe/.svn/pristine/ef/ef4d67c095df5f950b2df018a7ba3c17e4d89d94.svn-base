package br.com.jcv.aluguerelaxe.client.componente.chavevalor;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class Expansor extends Image {
	
	private static final String PATH_IMAGE = "images/16x16/";
	private static final String IMG_MAIS = "down_plus.png";
	private static final String IMG_MENOS = "down_minus.png";
	
	private Widget widget;
	private boolean maisLigado;
	
	public Expansor() {
		super(PATH_IMAGE + IMG_MENOS);
		init();
	}
	
	public void setWidgetExpansor(Widget widget){
		this.widget = widget;
	}
	
	private void init() {
		maisLigado = true;
		this.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				if (Expansor.this.maisLigado){
					Expansor.this.setUrl(PATH_IMAGE + IMG_MAIS);
					if(Expansor.this.widget != null){
						Expansor.this.widget.setVisible(false);
					}
				} else {
					Expansor.this.setUrl(PATH_IMAGE + IMG_MENOS);
					if(Expansor.this.widget != null){
						Expansor.this.widget.setVisible(true);
					}
				}
				Expansor.this.maisLigado = ! Expansor.this.maisLigado;
			}
			
		});
	}
}
