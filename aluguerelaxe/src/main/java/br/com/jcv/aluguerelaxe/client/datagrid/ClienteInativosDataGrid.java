package br.com.jcv.aluguerelaxe.client.datagrid;

import br.com.jcv.aluguerelaxe.client.componente.datagrid.AbstractDataGrid;
import br.com.jcv.aluguerelaxe.client.componente.datagrid.CondicaoVO;
import br.com.jcv.aluguerelaxe.client.componente.datagrid.DefinicaoCampoDataGrid;
import br.com.jcv.aluguerelaxe.client.componente.datagrid.DefinicaoDataGrid;

/**
* <h2>ClienteDataGrid</h2>
* <p>Classe concreta para apresentacao da Grid de dados da tabela clientes
* </p>
* @author Julio
*/

public class ClienteInativosDataGrid extends AbstractDataGrid {

	public ClienteInativosDataGrid() {
		super();
	}
	
	@Override
	public DefinicaoDataGrid getInstanceDefinicaoDataGrid() {
		int i = 0;
		DefinicaoDataGrid ddg = new DefinicaoDataGrid();
		ddg.setTabelaView("CLIENTE");
		ddg.setSelecaoMultiplosRegistros(true);
		ddg.addCondicao(new CondicaoVO("CLIE_IN_STATUS", "=", "'I'"));
		ddg.add(new DefinicaoCampoDataGrid(i++,"Codigo#", "CLIE_CD_CLIENTE"));
		ddg.add(new DefinicaoCampoDataGrid(i++,"Nome", "CLIE_NM_CLIENTE"));
		ddg.add(new DefinicaoCampoDataGrid(i++,"Email", "CLIE_TX_EMAIL"));
		ddg.add(new DefinicaoCampoDataGrid(i++,"Aniversario", "CLIE_DT_NASCIMENTO"));
		ddg.add(new DefinicaoCampoDataGrid(i++,"STATUS", "CLIE_IN_STATUS"));
		return ddg;
	}
	
}