package br.com.jcv.aluguerelaxe.client.componente.chavevalor;

import java.util.ArrayList;
import java.util.List;

public class GrupoChaveValor {
	
	private String grupo;
	private List<ChaveValor> lst;
	
	public GrupoChaveValor(String grupo) {
		this.grupo = grupo;
	}
	
	public void add(ChaveValor cv) {
		if (lst == null){
			this.lst = new ArrayList<ChaveValor>();
		}
		this.lst.add(cv);
	}
	
	public String getGrupo() {
		return this.grupo;
	}
	
	public List<ChaveValor> getList() {
		return this.lst;
	}
	
	
}