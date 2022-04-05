package br.com.jcv.aluguerelaxe.client.componente.datagrid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.jcv.aluguerelaxe.client.Constantes;

/**
* <h2>DefinicaoDataGrid</h2>
* <p>Definicao das características para AbstractDataGrid
* </p>
* @author Julio
*/
public class DefinicaoDataGrid {

	private List<DefinicaoCampoDataGrid> lst;
	private List<CondicaoVO> lstCondicao;
	private List<String> lstOrderBy;
	private String tabelaView;
	private Map<String,String> param;
	private boolean selecaoMultiplosRegistros;

	public DefinicaoDataGrid() {
		// Define o mapa padrao para BuscaPaginaFactory
		this.param = new HashMap<String,String>();
		this.param.put(Constantes.MAPA_TAG, Constantes.MAPA_DATA_GRID);
		this.setSelecaoMultiplosRegistros(false); 
	}
	
	public DefinicaoCampoDataGrid getDefinicaoCampo(int index) {
		DefinicaoCampoDataGrid dcdg = null;
		if ((this.lst != null) && (this.lst.size() > 0)) {
			if ((index >= 0) && (index <= this.lst.size())) {
				dcdg = this.lst.get(index);
			}
		}
		return dcdg;
	}
	
	public List<CondicaoVO> getListCondicao(){
		return this.lstCondicao;
	}
	
	public void clearCondicao(){
		if((this.lstCondicao != null) && (this.lstCondicao.size() > 0)){
			for (int i = 0; i < this.lstCondicao.size(); i++  ){
				this.lstCondicao.remove(i);
			}
		}
	}
	
	public List<String> getListOrderBy(){
		return this.lstOrderBy;
	}
	
	public void setSelecaoMultiplosRegistros(boolean permissao) {
		this.selecaoMultiplosRegistros = permissao;
	}
	
	public boolean isSelecaoMultiplosRegistros() {
		return this.selecaoMultiplosRegistros;
	}
	
	public void setTabelaView(String tabelaView) {
		this.tabelaView = tabelaView;
	}
	
	public String getTabelaView(){
		return this.tabelaView;
	}
	
	public Map<String,String> getParam() {
		return this.param;
	}
	
	public void addCondicao(CondicaoVO condicao) {
		if (this.lstCondicao == null){
			this.lstCondicao = new ArrayList<CondicaoVO>();
		}
		this.lstCondicao.add(condicao);
	}

	public void addOrderBy(String campo) {
		if (this.lstOrderBy == null){
			this.lstOrderBy = new ArrayList<String>();
		}
		
		// Verific se o campo já existe na lista
		boolean existe = false;
		if(this.lstOrderBy.size() > 0){
			for (String cmp : this.lstOrderBy){
				if (cmp.equals(campo)){
					existe = true;
					break;
				}
			}
		}
		if (! existe) {
			this.lstOrderBy.add(campo);
		}
	}

	public void removeOrderBy(String campo) {
		// Verific se o campo já existe na lista
		boolean existe = false;
		int idx = 0;
		if (this.lstOrderBy != null){
			if(this.lstOrderBy.size() > 0){
				
				for (String cmp : this.lstOrderBy){
					if (cmp.equals(campo)){
						existe = true;
						break;
					}
					idx++;
				}
			}
		}
		
		if (existe) {
			this.lstOrderBy.remove(idx);
		}
	}
	
	public void addParam(Map<String, String> item) {/*
		if (this.param == null) {
			this.param = new HashMap<String,String>();
		}
		this.param.put(item);*/
	}
	
	public void add(DefinicaoCampoDataGrid item) {
		if (this.lst == null) {
			lst = new ArrayList<DefinicaoCampoDataGrid>();
		}
		this.lst.add(item);
	}
	
	public int getCols() {
		return lst.size();
	}
	
	public List<DefinicaoCampoDataGrid> getListCampos() {
		return lst;
	}
	
	public List<String> getList() {
		ArrayList<String> lstCampos = null;
		if (this.lst != null){
			lstCampos = new ArrayList<String>();
			for (DefinicaoCampoDataGrid dcdg : this.lst) {
				lstCampos.add(dcdg.getField());
			}
		}
		return lstCampos;
	}
	
}