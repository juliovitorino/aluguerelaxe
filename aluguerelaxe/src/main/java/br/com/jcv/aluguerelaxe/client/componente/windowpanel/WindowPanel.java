package br.com.jcv.aluguerelaxe.client.componente.windowpanel;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class WindowPanel extends Composite {
	
	private static final String TEMA_PADRAO = "gwt-WindowPanel";
	
	private HorizontalPanel hpContainer = null;
	private Grid grid = null;
	
	private String titulo;
	private boolean minimizar = false;
	private boolean restaurar = false;
	private boolean fechar = false;
	public String tema = null;

	public WindowPanel(String titulo, String tema) {
		this(titulo,tema,false,false,false);
	}

	public WindowPanel() {
		this(null,TEMA_PADRAO,false,false,false);
	}

	public WindowPanel(String titulo) {
		this(titulo,TEMA_PADRAO,false,false,false);
	}

	public WindowPanel(String titulo, boolean minimizar, boolean restaurar, boolean fechar) { 
		this(titulo, TEMA_PADRAO, minimizar, restaurar, fechar);
	}
	
	public WindowPanel(String titulo, String tema, boolean minimizar, boolean restaurar, boolean fechar) {
		this.titulo = titulo;
		this.minimizar = minimizar;
		this.restaurar = restaurar;
		this.fechar = fechar;
		if (tema == null) {
			this.tema = TEMA_PADRAO;
			initWidget(montaWindowPanel());
		} else {
			this.tema = tema;
			initWidget(montaWindowPanel());
		}
		
		this.setStylePrimaryName("gwt-windowpanel");
		
	}

	private Widget montaWindowPanel() {
		
		hpContainer = new HorizontalPanel();
		
		grid = new Grid(3,3);
		grid.setBorderWidth(0);
		grid.setCellSpacing(0);
		grid.setStylePrimaryName(this.tema);
		grid.setSize("100%", "100%");
		
		grid.getCellFormatter().setStylePrimaryName(0, 0, this.tema);
		grid.getCellFormatter().addStyleName(0, 0, "topLeft");
		
		grid.getCellFormatter().setStylePrimaryName(0, 1, this.tema);
		grid.getCellFormatter().addStyleName(0, 1, "topCenter");
		
		grid.getCellFormatter().setStylePrimaryName(0, 2, this.tema);
		grid.getCellFormatter().addStyleName(0, 2, "topRight");
		
		grid.getCellFormatter().setStylePrimaryName(1, 0, this.tema);
		grid.getCellFormatter().addStyleName(1, 0, "middleLeft");
		
		grid.getCellFormatter().setStylePrimaryName(1, 1, this.tema);
		grid.getCellFormatter().addStyleName(1, 1, "middleCenter");
		
		grid.getCellFormatter().setStylePrimaryName(1, 2, this.tema);
		grid.getCellFormatter().addStyleName(1, 2, "middleRight");
		
		grid.getCellFormatter().setStylePrimaryName(2, 0, this.tema);
		grid.getCellFormatter().addStyleName(2, 0, "bottomLeft");
		
		grid.getCellFormatter().setStylePrimaryName(2, 1, this.tema);
		grid.getCellFormatter().addStyleName(2, 1, "bottomCenter");
		
		grid.getCellFormatter().setStylePrimaryName(2, 2, this.tema);
		grid.getCellFormatter().addStyleName(2, 2, "bottomRight");
		
		montaBarraTitulo();

		hpContainer.add(this.grid);
		return this.hpContainer;
	}
	
	public void setSize(String width, String height) {
		this.hpContainer.setSize(width, height);
	}
	
	public void setHeight(String height) {
		this.hpContainer.setHeight(height);
	}
	
	public void setWidth(String width) {
		this.hpContainer.setWidth(width);
	}
	
	private void montaBarraTitulo() {
		HorizontalPanel hp = new HorizontalPanel();

		if ( this.fechar || this.minimizar || this.restaurar) {
			HorizontalPanel hpbtn = new HorizontalPanel();
			hpbtn.addStyleName("botoes");
			
			if (this.minimizar) {
				Image imgMinimizar = new Image("images/" + this.tema + "-wp-btn-minimizar.png");
				imgMinimizar.setStylePrimaryName(this.tema);
				imgMinimizar.addStyleName("imgminimizar");
				imgMinimizar.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						Window.alert("Implementar funcoes minimizar");
					}
				});
				hpbtn.add(imgMinimizar);
			}
			
			if (this.restaurar){
				Image imgRestaurar = new Image("images/" + this.tema + "-wp-btn-restaurar.png");
				imgRestaurar.setStylePrimaryName(this.tema);
				imgRestaurar.addStyleName("imgrestaurar");
				imgRestaurar.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						Window.alert("Implementar funcoes restaurar");
					}
				});
				hpbtn.add(imgRestaurar);
			}
			
			if (this.fechar) {
				Image imgfechar = new Image("images/" + this.tema + "-wp-btn-close.png");
				imgfechar.setStylePrimaryName(this.tema);
				imgfechar.addStyleName("imgfechar");
				imgfechar.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						Window.alert("Implementar funcoes fechar");
					}
				});
				hpbtn.add(imgfechar);
			}
			
			hp.add(hpbtn);
		}
		

		if (this.titulo != null) {
			if (this.titulo.length() > 0){
				Label lblTitulo = new Label();
				lblTitulo.setStylePrimaryName(this.tema);
				lblTitulo.addStyleName("titulowp");
				lblTitulo.setText(this.titulo);
				hp.add(lblTitulo);
			}
		}
		grid.setWidget(0, 1, hp);
	}

	public void setComponenteCenter(Widget w) {
		this.grid.setWidget(1,1,w);
	}
	
	public Widget getComponenteCenter() {
		return this.grid.getWidget(1, 1);
	}

}

