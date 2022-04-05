package br.com.jcv.backend.charter;

public class MapInfo {
	String uf;
	String chld;
	String chco;
	
	public MapInfo(String uf, String chld, String chco) {
		this.uf = uf;
		this.chld = chld;
		this.chco = chco;
	}
	
	public String getChm(int pos) {
		return "f" + uf + ",000000,0," + String.valueOf(pos) + ",10,15,v";
	}
}
