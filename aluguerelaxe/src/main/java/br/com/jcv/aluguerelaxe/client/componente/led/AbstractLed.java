package br.com.jcv.aluguerelaxe.client.componente.led;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractLed extends Composite {
	
	private final static String PATH_IMAGEM = "images/16x16/";

	private String ledOn;
	private String ledOff;
	private Image ledStatus;
	
	private static String STYLE = "gwt-AbstractLed";

	public AbstractLed(String ledOn, String ledOff){
		this.ledOn = ledOn;
		this.ledOff = ledOff;
		
		init();
		initWidget(render());
		//this.setStyleName(STYLE);
	}
	
	private void init() {
		// Sempre inicia com led em off
		this.ledStatus = new Image(PATH_IMAGEM + ledOff);
	}
	
	protected Widget render() {
		return this.ledStatus;
	}
	
	public void ledOn(){
		this.ledStatus.setUrl(PATH_IMAGEM + ledOn);
	}
	
	public void ledOff(){
		this.ledStatus.setUrl(PATH_IMAGEM + ledOff);
	}
	
}