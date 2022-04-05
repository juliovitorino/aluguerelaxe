package br.com.jcv.aluguerelaxe.client.componente.superpanel;

import br.com.jcv.aluguerelaxe.client.componente.dialog.AbstractDialogModal;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractSuperPanel<V> extends AbstractDialogModal<Widget> {
	
	private VerticalPanel vp;
	private Image imgFechar;
	private VerticalPanel html;
	
	private static String PATH_IMAGEM = "images/32x32/";
	private static String FECHAR = "delete.png";
	private static String STYLE = "gwt-AbstractSuperPanel";
	private static String STYLE_FECHAR = "gwt-asp-fechar";
	
	public abstract void update(V vo);
	
	public AbstractSuperPanel() {
		super();
		this.setStyleName(STYLE);
		//this.center();
	}
	
	public void setHTML(Widget html){
		//this.html.setHTML(html);
		this.html.add(html);
	}
	
	public void init() {
		vp = new VerticalPanel();
		imgFechar = new Image(PATH_IMAGEM + FECHAR);
		html = new VerticalPanel();
		
		imgFechar.setStyleName(STYLE_FECHAR);
		imgFechar.setAltText("Fechar");
		imgFechar.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				AbstractSuperPanel.this.hide();
			}});
	}
	
	public Widget render() {
		vp.add(html);
		vp.add(imgFechar);
		return vp;
	}
	
}
