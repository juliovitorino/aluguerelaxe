package br.com.jcv.aluguerelaxe.client.componente.advanceddatagrid;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.jcv.aluguerelaxe.client.Constantes;
import br.com.jcv.aluguerelaxe.client.componente.datagrid.CondicaoVO;

/**
* <h2>DefinicaoDataGrid</h2>
* <p>Definicao das características para AbstractDataGrid
* </p>
* @author Julio
*/
public class AdvancedDefinicaoDataGrid {

	private List<AbstractDefinicaoCampoDataGrid<?>> lst;
	private List<CondicaoVO> lstCondicao;
	private List<String> lstOrderBy;
	private String tabelaView;
	private Map<String,String> param;
	private boolean selecaoMultiplosRegistros;

	public AdvancedDefinicaoDataGrid() {
		// Define o mapa padrao para BuscaPaginaFactory
		this.param = new HashMap<String,String>();
		this.param.put(Constantes.MAPA_TAG, Constantes.MAPA_DATA_GRID);
		this.setSelecaoMultiplosRegistros(false); 
	}
	
	public AbstractDefinicaoCampoDataGrid<?> getDefinicaoCampo(int index) {
		AbstractDefinicaoCampoDataGrid<?> dcdg = null;
		if ((this.lst != null) && (this.lst.size() > 0)) {
			if ((index >= 0) && (index <= this.lst.size())) {
				dcdg = (AbstractDefinicaoCampoDataGrid<?>) this.lst.get(index);
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
	
	public void add(AbstractDefinicaoCampoDataGrid<?> item) {
		if (this.lst == null) {
			lst = new ArrayList<AbstractDefinicaoCampoDataGrid<?>>();
		}
		this.lst.add(item);
	}
	
	public int getCols() {
		return lst.size();
	}
	
	public List<AbstractDefinicaoCampoDataGrid<?>> getListCampos() {
		return lst;
	}
	
	public List<String> getList() {
		ArrayList<String> lstCampos = null;
		if (this.lst != null){
			lstCampos = new ArrayList<String>();
			for (AbstractDefinicaoCampoDataGrid<?> dcdg : this.lst) {
				lstCampos.add(dcdg.getCampobd());
			}
		}
		return lstCampos;
	}
	
}