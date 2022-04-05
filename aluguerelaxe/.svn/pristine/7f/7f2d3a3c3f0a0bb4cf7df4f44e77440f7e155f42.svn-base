package br.com.jcv.aluguerelaxe.client.componente.griddataentry;

import java.util.ArrayList;
import java.util.List;

import br.com.jcv.aluguerelaxe.client.componente.listbox.SimNaoListBox;
import br.com.jcv.aluguerelaxe.client.componente.listbox.TipoTelefoneListBox;
import br.com.jcv.aluguerelaxe.client.componente.listbox.UltimosCincoAnosListBox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

/**
 *<h2>GridDataEntry</h2>
 *<p>Componente de grid que permite colocar em cada coluna um {@link ColumnWidget}
 *@author Julio Vitorino
 *
 */
public abstract class GridDataEntry<V> extends Composite implements GridDataEntryListener {
	private List<ColumnWidget> colunas = null;
	private Grid grid = null;
	private int linhas;
	private VerticalPanel cabecalho;
	private VerticalPanel rodape;
	
	private static final String GWT_STYLE = "gwt-GridDataEntry"; 
	private static final String GWT_STYLE_LINHA_HEADER = "gwt-gde-header";
	private static final String GWT_STYLE_LINHA_PAR = "gwt-gde-par";
	private static final String GWT_STYLE_LINHA_IMPAR = "gwt-gde-impar";
	
	public GridDataEntry() {
		initWidget(render());
		this.setStyleName(GWT_STYLE);
	}
	
	public void update(List<V> lst){
		this.resetGridDataEntry();
		this.inicializaLinhasGrid();
		
		int i =  1;
		for ( V vo : lst){
			// Varre coluna a coluna
			for (int j = 0; j < this.grid.getColumnCount(); j++){
				Widget uiwidget = this.grid.getWidget(i, j);
				if (uiwidget instanceof ListBox){
					String conteudo = update(vo, j);
					int pos = -1;
					for (int k = 0; k < ((ListBox)uiwidget).getItemCount(); k++) {
						String txt = ((ListBox)uiwidget).getValue(k);
						if (txt.equals(conteudo)) {
							pos = k;
							break;
						}
					}
					if (pos > -1){
						((ListBox)uiwidget).setSelectedIndex(pos);
					}

				} else if (uiwidget instanceof Label){
					((Label) uiwidget).setText(update(vo, j));

				} else if (uiwidget instanceof TextBox){
					((TextBox) uiwidget).setValue(update(vo, j));

				} else if (uiwidget instanceof Hidden){
					((Hidden) uiwidget).setValue(update(vo, j));

				} else if (uiwidget instanceof TextArea){
					((TextArea) uiwidget).setValue(update(vo, j));

				} else {
					// TODO Verificar outros objetos
				}
			}
			i += 1;
			
			
		}
		
	}
	
	// Deixa somente a linha de cabecalho da grid
	private void resetGridDataEntry() {
		if (this.grid.getRowCount() > 1) {
			for (int i = this.grid.getRowCount() - 1; i > 0; i--){
				this.grid.removeRow(i);
			}
		}
	}
	
	/**
	 * Retorna uma List objetos V
	 * @return
	 */
	public List<V> getListVO() {
		List<V> lst = new ArrayList<V>();
		
		if(this.grid.getRowCount() > 0){
			lst = new ArrayList<V>();
			
			// Varre linha a linha da grid
			for (int i = 1; i < this.grid.getRowCount(); i++){
				StringBuilder sb = new StringBuilder();

				// Varre coluna a coluna
				for (int j = 0; j < this.grid.getColumnCount(); j++){
					Widget uiwidget = this.grid.getWidget(i, j);
					if (uiwidget instanceof ListBox){
						sb.append(((ListBox)uiwidget).getValue(((ListBox)uiwidget).getSelectedIndex()));
						sb.append("|");
					} else if (uiwidget instanceof Label){
						sb.append(((Label)uiwidget).getText());
						sb.append("|");
					} else if (uiwidget instanceof TextBox){
						sb.append(((TextBox)uiwidget).getValue());
						sb.append("|");
					} else if (uiwidget instanceof Hidden){
						sb.append(((Hidden)uiwidget).getValue());
						sb.append("|");
					} else if (uiwidget instanceof TextArea){
						sb.append(((TextArea)uiwidget).getValue());
						sb.append("|");
					} else {
						sb.append("Col:" + j +". Verificar|");
					}
				}
				V voretorno = this.getVO(sb.toString());
				if (voretorno != null){
					lst.add(this.getVO(sb.toString()));
				}
			}
		}
		
		return lst;
	}
	
	public abstract V getVO(String conteudoColunas);
	public abstract String update(V vo, int index);
	
	/**
	 * Inicializa a montagem das colunas na Grid
	 */
	public void init(int li) {
		this.linhas = li;
		if ( (this.colunas != null) && (this.colunas.size() > 0) ) {
			
			// redimensiona a grid de acordo com a quantidade de colunas a comportar
			this.grid.resize(1,this.colunas.size());
			
			int c = 0;
			for ( ColumnWidget cw : this.colunas ) {
				this.grid.setText(0, c, cw.getCabecalho());
				this.grid.getCellFormatter().setWidth(0, c, cw.getWidth());
				c += 1;
			}
			
			inicializaLinhasGrid();
		}
	}
	
	// Inicializa uma certa quantidade de linhas
	private void inicializaLinhasGrid() {
		if (this.linhas > 0) {
			for (int i = 0; i < this.linhas; i++) {
				this.addLinhaGridDataEntry();
				
				if (i == 0) {
					this.grid.getRowFormatter().setStyleName(i, GWT_STYLE_LINHA_HEADER); 
				} else if (i % 2 == 0) { 
					this.grid.getRowFormatter().setStyleName(i, GWT_STYLE_LINHA_PAR); 
				} else { 
					this.grid.getRowFormatter().setStyleName(i, GWT_STYLE_LINHA_IMPAR); 
				} 
				
			}
		}
	}
	
	/**
	 * Adiciona um novo Widget no cabecalho da grid
	 */
	public void addCabecalhoItem(Widget item) {
		this.cabecalho.setVisible(true);
		this.cabecalho.add(item);
	}

	/**
	 * Adiciona um novo Widget no rodape da grid
	 */
	public void addRodapeItem(Widget item) {
		this.rodape.setVisible(true);
		this.rodape.add(item);
	}
	
	private Widget render() {
		VerticalPanel vp = new VerticalPanel();
		this.grid = new Grid(1,1);
		this.cabecalho = new VerticalPanel();
		this.cabecalho.setVisible(false);

		this.rodape = new VerticalPanel();
		this.rodape.setVisible(false);

		vp.add(this.cabecalho);
		vp.add(this.grid);
		vp.add(this.rodape);
		return vp;
	}
	
	/**
	 * Adiciona uma coluna a lista
	 */
	public void addColunaGridDataEntry(ColumnWidget c) {
		if (this.colunas == null) {
			this.colunas = new ArrayList<ColumnWidget>();
		}
		this.colunas.add(c);
	}

	/**
	 * Cria nova linha em branco na {@link GridDataEntry}
	 */
	public void addLinhaGridDataEntry() {
		if (this.colunas != null) {
			this.grid.resizeRows(this.grid.getRowCount() + 1);
			int c = 0;
			int li = this.grid.getRowCount() - 1;
			for ( final ColumnWidget cw : this.colunas ) {
				switch(cw.getUiTipoWidget()) {
					case ColumnWidget.CW_CINCO_ANOS_LISTBOX:
						this.grid.setWidget(li, c, (UltimosCincoAnosListBox) GWT.create(UltimosCincoAnosListBox.class));
						((UltimosCincoAnosListBox)this.grid.getWidget(li, c)).setWidth(cw.getWidth());
						break;
					case ColumnWidget.CW_TELEFONE_LISTBOX:
						this.grid.setWidget(li, c, (TipoTelefoneListBox) GWT.create(TipoTelefoneListBox.class));
						((TipoTelefoneListBox)this.grid.getWidget(li, c)).setWidth(cw.getWidth());
						break;
					case ColumnWidget.CW_LISTBOX:
						this.grid.setWidget(li, c, (ListBox) GWT.create(ListBox.class));
						((ListBox)this.grid.getWidget(li, c)).setWidth(cw.getWidth());
						break;
					case ColumnWidget.CW_TEXTBOX:
						this.grid.setWidget(li, c, (TextBox) GWT.create(TextBox.class));
						((TextBox)this.grid.getWidget(li, c)).setWidth(cw.getWidth());
						break;
					case ColumnWidget.CW_PASSWORD:
						this.grid.setWidget(li, c, (PasswordTextBox) GWT.create(PasswordTextBox.class));
						((PasswordTextBox)this.grid.getWidget(li, c)).setWidth(cw.getWidth());
						break;
					case ColumnWidget.CW_LABEL:
						/* Cria o objeto */
						Label lbl = (Label) GWT.create(Label.class);
						
						/* Se tiver algum listener associado a coluna cria o evento para disparo */
						if (cw.getColumnWidgetListener() != null){
							lbl.addClickHandler(new ClickHandler() {
								public void onClick(ClickEvent event) {
									Label uiwidget = (Label) event.getSource();
									cw.getColumnWidgetListener().onColumnLabelClick(uiwidget.getText());
								}
							});
						}
						this.grid.setWidget(li, c, lbl);
						((Label)this.grid.getWidget(li, c)).setWidth(cw.getWidth());
						break;
					case ColumnWidget.CW_HIDDEN:
						this.grid.setWidget(li, c, (Hidden) GWT.create(Hidden.class));
						((Hidden)this.grid.getWidget(li, c)).setWidth(cw.getWidth());
						break;
					case ColumnWidget.CW_CHECKBOX:
						this.grid.setWidget(li, c, (CheckBox) GWT.create(CheckBox.class));
						((CheckBox)this.grid.getWidget(li, c)).setWidth(cw.getWidth());
						break;
					case ColumnWidget.CW_TEXTAREA:
						this.grid.setWidget(li, c, (TextArea) GWT.create(TextArea.class));
						((TextArea)this.grid.getWidget(li, c)).setWidth(cw.getWidth());
						break;
					case ColumnWidget.CW_BUTTON:
						this.grid.setWidget(li, c, (Button) GWT.create(Button.class));
						((Button)this.grid.getWidget(li, c)).setWidth(cw.getWidth());
						break;
					case ColumnWidget.CW_RICHTEXTAREA:
						this.grid.setWidget(li, c, (RichTextArea) GWT.create(RichTextArea.class));
						((RichTextArea)this.grid.getWidget(li, c)).setWidth(cw.getWidth());
						break;
					case ColumnWidget.CW_IMAGE:
						this.grid.setWidget(li, c, (Image) GWT.create(Image.class));
						((Image)this.grid.getWidget(li, c)).setWidth(cw.getWidth());
						break;
					case ColumnWidget.CW_SIMNAO_LISTBOX:
						this.grid.setWidget(li, c, (SimNaoListBox) GWT.create(SimNaoListBox.class));
						((SimNaoListBox)this.grid.getWidget(li, c)).setWidth(cw.getWidth());
						break;
					case ColumnWidget.CW_DATEBOX:
						this.grid.setWidget(li, c, (DateBox) GWT.create(DateBox.class));
						((DateBox)this.grid.getWidget(li, c)).setWidth(cw.getWidth());
						break;
				}
				
				c += 1;
			}
		}
	}

	public void onAdicionarLinhaEvent() {
		this.addLinhaGridDataEntry();
	}
	
	
	
}
