package br.com.jcv.backend.charter;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStaticCharter<V> {

	public static final String URL_GOOGLE_CHARTER = "https://chart.googleapis.com/chart?";
	
	public static final int TIPO_CHARTER_PIZZA_3D = 0;
	public static final int TIPO_CHARTER_PIZZA_2D = 1;
	public static final int TIPO_CHARTER_MAPA_BRASIL = 2;

	private List<V> cs = null;
	

	private String urlTipo;
	private int width;
	private int height;
	
	public abstract String getUrl();
	
	public AbstractStaticCharter(int urlTipo, int width, int height) {
		switch(urlTipo) {
			case TIPO_CHARTER_PIZZA_3D:
				this.urlTipo = "cht=p3";
				break;
			case TIPO_CHARTER_PIZZA_2D:
				this.urlTipo = "cht=p";
				break;
			case TIPO_CHARTER_MAPA_BRASIL:
				this.urlTipo = "cht=map";
				break;
		}
		this.width = width;
		this.height = height;
		init();
	}
	
	
	public void addCharterSequence(V cs) {
		this.cs.add(cs);
	}
	
	private void init() {
		cs = new ArrayList<V>();
	}

	public String getUrlTipo() {
		return urlTipo;
	}

	public void setUrlTipo(String urlTipo) {
		this.urlTipo = urlTipo;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public List<V> getCharterSequence() {
		return cs;
	}

}
