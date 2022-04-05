package br.com.jcv.aluguerelaxe.client.componente.datagrid;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

/**
* <h2>SeletorDataGrid</h2>
* <p>Marcador de linha selecionada/deselecionada enviando evento de qual linha aconteceu o click
* </p>
* @author Julio
*/
public class SeletorDataGrid extends Composite {

	private static final String PATH_IMAGEM = "images/16x16/";
	private static final String IMG_SELETOR_ON = "seletor_on.png";
	private static final String IMG_SELETOR_OFF = "seletor_off.png";
	private static final String STYLE = "gwt-SeletorDataGrid"; 
	
	private boolean status;
	private int linha;
	private Image imgSeletor;
	private List<SeletorDataGridListener> listener;
	
	public SeletorDataGrid(int linha) {
		init(linha);
		initWidget(render());
		this.setStyleName(STYLE);
	}
	
	private void init(int linha) {
		this.status = false;
		this.linha = linha;
		imgSeletor = new Image(PATH_IMAGEM + IMG_SELETOR_OFF);
		imgSeletor.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				if (status){
					SeletorDataGrid.this.imgSeletor.setUrl(PATH_IMAGEM + IMG_SELETOR_OFF);
					// Envia evento
					if (SeletorDataGrid.this.listener != null){
						for (SeletorDataGridListener lstnr : SeletorDataGrid.this.listener){
							lstnr.onDeselectClick(SeletorDataGrid.this.linha);
						}
					}
				} else {
					SeletorDataGrid.this.imgSeletor.setUrl(PATH_IMAGEM + IMG_SELETOR_ON);
					// Envia evento
					if (SeletorDataGrid.this.listener != null){
						for (SeletorDataGridListener lstnr : SeletorDataGrid.this.listener){
							lstnr.onSelectClick(SeletorDataGrid.this.linha);
						}
					}
				}
				// Inverte o estado do seletor
				status = !status;
				
			}
		});
	}
	
	public boolean isTurnOn(){	
		return this.status;
	}
	
	public void turnOff(){	
		this.status = false;
		this.imgSeletor.setUrl(PATH_IMAGEM + IMG_SELETOR_OFF);
	}
	
	public void addListener(SeletorDataGridListener listener){
		if (this.listener == null) {
			this.listener = new ArrayList<SeletorDataGridListener>();
		}
		this.listener.add(listener);
	}
	
	private Widget render() {
		return imgSeletor;
	}
	
	
	
}