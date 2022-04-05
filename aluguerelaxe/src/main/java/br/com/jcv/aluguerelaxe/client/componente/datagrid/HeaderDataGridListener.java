package br.com.jcv.aluguerelaxe.client.componente.datagrid;

public interface HeaderDataGridListener {
	void onHeaderAddSortClick(int colunaPos);
	void onHeaderRemoveSortClick(int colunaPos);
}