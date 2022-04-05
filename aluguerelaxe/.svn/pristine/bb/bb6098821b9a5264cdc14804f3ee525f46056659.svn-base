package br.com.jcv.aluguerelaxe.client.componente.datagrid;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class HeaderDataGrid extends Composite {

	private static final String PATH_IMAGEM = "images/gif/";
	private static final String IMG_SORT = "sort.gif";
	private static final String STYLE = "gwt-HeaderDataGrid";
	

	private int colunaPos;
	private Label titulo;
	private Image imgSort;
	private boolean sort_on;
	private List<HeaderDataGridListener> listener;
	
	public HeaderDataGrid(String tituloHeader, int colunaPos) {
		init(tituloHeader);
		this.sort_on = false;
		this.colunaPos = colunaPos;
		initWidget(render());
		this.setStyleName(STYLE);
	}
	
	public void addListener(HeaderDataGridListener listener){
		if (this.listener == null) {
			this.listener = new ArrayList<HeaderDataGridListener>();
		}
		this.listener.add(listener);
	}
	
	private void init(String tituloHeader) {
		titulo = new Label(tituloHeader);
		imgSort = new Image(PATH_IMAGEM + IMG_SORT);
		imgSort.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				// Troca o estado da coluna
				HeaderDataGrid.this.sort_on = ! HeaderDataGrid.this.sort_on;
				
				if (HeaderDataGrid.this.listener != null){
					for (HeaderDataGridListener lstnr : HeaderDataGrid.this.listener){
						if(HeaderDataGrid.this.sort_on) {
							lstnr.onHeaderAddSortClick(HeaderDataGrid.this.colunaPos);
						} else {
							lstnr.onHeaderRemoveSortClick(HeaderDataGrid.this.colunaPos);
						}
					}
				}

			}
		});
		
	}
	
	private Widget render() {
		HorizontalPanel fp = new HorizontalPanel();
		fp.add(titulo);
		fp.add(imgSort);
		return fp;
	}
	
}