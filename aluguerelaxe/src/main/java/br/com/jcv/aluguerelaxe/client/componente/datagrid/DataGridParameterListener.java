package br.com.jcv.aluguerelaxe.client.componente.datagrid;

import java.util.List;

/**
 * <h2>DatagridParameterListener</h2>
 * <p>Interface com metodos para apoiar @link{AbstractDataGrid}
 * </p>
 *
 * @author Julio Vitorino
 *
 */
public interface DataGridParameterListener {
	void onProcurarClick(List<CondicaoVO> lst);
}