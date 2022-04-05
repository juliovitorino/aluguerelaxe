
package br.com.jcv.aluguerelaxe.client.componente.toolbar;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class ToolbarNavegador extends AbstractToolbar<ToolbarNavegadorListener> {

	public static final String PATH_IMAGEM = "images/16x16/";
	
	private int registroPorPagina;
	private ListBox itensPagina;
	private int indice;
	private int paginas;
	private Label lblMostradorPagina;
	private Label lblResultados;
	
	public ToolbarNavegador(int registroPorPagina) {
		super();
		this.registroPorPagina = registroPorPagina;
	}
	
	private int efetuaCalculoDescobrirPaginas(long nRegistros) {
		double paginas = nRegistros / registroPorPagina;
		int paginasCalculadas = Double.valueOf(String.valueOf(paginas)).intValue();
		if (paginasCalculadas == 0) {
			paginasCalculadas = 1;
		} else {
			if ((nRegistros % registroPorPagina) > 0) {
				paginasCalculadas++;
			}
		}
		return paginasCalculadas;
	}

	public int getPaginas() {
		return this.paginas;
	}
	
	public int getIndice(){
		return this.indice;
	}
	
	public void update(int indice, long nRegistros) {
		this.indice = indice;
		this.paginas = efetuaCalculoDescobrirPaginas(nRegistros);
		lblMostradorPagina.setText(String.valueOf(this.indice + "/" + this.paginas));
		lblResultados.setText(String.valueOf(nRegistros));
	}

	@Override
	public Widget render() {

		DockPanel dpNavegador = new DockPanel();
		dpNavegador.add(montaItensPorPagina(), DockPanel.WEST);
		dpNavegador.add(montaNavegador(), DockPanel.WEST);
		dpNavegador.add(montaInfoTotalRegistros(), DockPanel.EAST);
		
		return dpNavegador;
	}

	private ListBox montaItensPorPagina() {
		itensPagina = new ListBox();
		itensPagina.addItem("5");
		itensPagina.addItem("10");
		itensPagina.addItem("20");
		itensPagina.addItem("30");
		itensPagina.addItem("50");
		itensPagina.addItem("100");
		itensPagina.setSelectedIndex(1);
		
		itensPagina.addChangeHandler(new ChangeHandler() {
			
			public void onChange(ChangeEvent event) {
				if (listeners != null) {
					registroPorPagina = Integer.valueOf(itensPagina.getItemText(itensPagina.getSelectedIndex()));
					for (ToolbarNavegadorListener lstnr : listeners) {
						lstnr.onItensPorPaginaChange(Integer.valueOf(itensPagina.getItemText(itensPagina.getSelectedIndex())));
					}
				}
			}
		});
		
		return itensPagina;
	}

	private Widget montaInfoTotalRegistros() {
		HorizontalPanel hp = new HorizontalPanel();
		lblResultados = new Label();
		hp.add(lblResultados);
		hp.add(new Label("resultados"));
		return hp;
	}

	private Widget montaNavegador() {
		HorizontalPanel hp = new HorizontalPanel();
		Image imgPrimeiro = new Image(PATH_IMAGEM + "navigate_beginning.png");
		Image imgAnterior = new Image(PATH_IMAGEM + "navigate_left.png");
		Image imgUltimo = new Image(PATH_IMAGEM + "navigate_end.png");
		Image imgProximo = new Image(PATH_IMAGEM + "navigate_right.png");
		
		lblMostradorPagina = new Label();
		
		// Adiciona handler quando acontecer click no botão ir para primeiro
		// e envia evento
		imgPrimeiro.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarNavegadorListener lstnr : listeners) {
						lstnr.onMoverParaInicio();
					}
				}
			}
		});
		
		// Adiciona handler quando acontecer click no botão ir para anterior
		// e envia evento
		imgAnterior.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarNavegadorListener lstnr : listeners) {
						lstnr.onMoverParaAnterior(indice);
					}
				}
			}
		});
		
		// Adiciona handler quando acontecer click no botão ir para ultimo
		// e envia evento
		imgUltimo.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarNavegadorListener lstnr : listeners) {
						lstnr.onMoverParaFinal();
					}
				}
			}
		});

		// Adiciona handler quando acontecer click no botão ir para proximo
		// e envia evento
		imgProximo.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (ToolbarNavegadorListener lstnr : listeners) {
						lstnr.onMoverParaProximo(indice);
					}
				}
			}
		});

		
		hp.add(imgPrimeiro);
		hp.add(imgAnterior);
		hp.add(lblMostradorPagina);
		hp.add(imgProximo);
		hp.add(imgUltimo);
		return hp;
	}

	

}

