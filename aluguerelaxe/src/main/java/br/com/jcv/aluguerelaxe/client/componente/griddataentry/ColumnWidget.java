package br.com.jcv.aluguerelaxe.client.componente.griddataentry;

/**
 * Configuracao de cada coluna para {@link GridDataEntry}
 * @author Julio
 *
 */

public class ColumnWidget {

	public static final int CW_LABEL = 0;
	public static final int CW_TEXTBOX = 1;
	public static final int CW_HIDDEN = 2;
	public static final int CW_PASSWORD = 3;
	public static final int CW_LISTBOX = 4;
	public static final int CW_CHECKBOX = 5;
	public static final int CW_TEXTAREA = 6;
	public static final int CW_TELEFONE_LISTBOX = 7;
	public static final int CW_BUTTON = 8;
	public static final int CW_RICHTEXTAREA = 9;
	public static final int CW_IMAGE = 10;	
	public static final int CW_SIMNAO_LISTBOX = 11;
	public static final int CW_DATEBOX = 12;
	public static final int CW_CINCO_ANOS_LISTBOX = 13;
	
	private String cabecalho;
	private int uiTipoWidget;
	private String width;
	private ColumnWidgetListener cwl;

	public ColumnWidget(String cabecalho, int uiTipoWidget) {
		this(cabecalho, uiTipoWidget, "50px", null);
	}
	
	public ColumnWidget(String cabecalho, int uiTipoWidget, String width) {
		this(cabecalho, uiTipoWidget, width, null);
	}

	public ColumnWidget(String cabecalho, int uiTipoWidget, String width, ColumnWidgetListener cwl) {
		this.cabecalho = cabecalho;
		this.width = width;
		this.uiTipoWidget = uiTipoWidget;
		this.cwl = cwl;
	}

	public String getWidth() {
		return width;
	}
	
	public String getCabecalho() {
		return this.cabecalho;
	}
	
	public int getUiTipoWidget() {
		return this.uiTipoWidget;
	}
	
	public ColumnWidgetListener getColumnWidgetListener() {
		return this.cwl;
	}
	
}