package br.com.jcv.aluguerelaxe.client.componente.autoajuda;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public abstract class AutoAjudaWidget<W extends Widget> extends Composite {
	
	private String stringAutoAjuda;
	private W widgetUI;
	
	public AutoAjudaWidget(String stringAutoAjuda, W widgetUI) {
		this.stringAutoAjuda = stringAutoAjuda;
		this.widgetUI = widgetUI;
		initWidget(render());
		this.setStylePrimaryName("gwt-AutoAjudaWidget");
	}
	
	public void setSize(String width, String height) {
		this.widgetUI.setSize(width, height);
	}
	
	public void setHeight(String height) {
		this.widgetUI.setHeight(height);
	}
	
	public void setWidth(String width) {
		this.widgetUI.setWidth(width);
	}
	
	public W getWidgetUI() {
		return this.widgetUI;
	}

	private Widget render() {
		HorizontalPanel hp = new HorizontalPanel();
		Image image = new Image("images/16x16/help2.png");
		image.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				AutoAjudaPopupPanel aapp = new AutoAjudaPopupPanel(stringAutoAjuda);
				Image img = (Image) event.getSource();
				int left = img.getAbsoluteLeft() + 20;
	            int top = img.getAbsoluteTop();
	            aapp.setPopupPosition(left, top);
				aapp.show();
			}
		});
		
		// Adiciona os componentes do painel
		hp.add(widgetUI);
		hp.add(image);
		
		return hp;
	}
}
