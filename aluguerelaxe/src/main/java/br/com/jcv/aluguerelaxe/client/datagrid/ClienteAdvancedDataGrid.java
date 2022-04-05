package br.com.jcv.aluguerelaxe.client.datagrid;

import br.com.jcv.aluguerelaxe.client.componente.advanceddatagrid.AbstractAdvancedDataGrid;
import br.com.jcv.aluguerelaxe.client.componente.advanceddatagrid.AdvancedDefinicaoDataGrid;
import br.com.jcv.aluguerelaxe.client.componente.advanceddatagrid.DateDefinicaoCampoDataGrid;
import br.com.jcv.aluguerelaxe.client.componente.advanceddatagrid.TextInputDefinicaoCampoDataGrid;



/**
* <h2>ClienteDataGrid</h2>
* <p>Classe concreta para apresentacao da Grid de dados da tabela clientes
* </p>
* @author Julio
*/

public class ClienteAdvancedDataGrid extends AbstractAdvancedDataGrid {

	public ClienteAdvancedDataGrid() {
		super();
	}
	
	@Override
	public AdvancedDefinicaoDataGrid getInstanceDefinicaoDataGrid() {
		int i = 0;
		AdvancedDefinicaoDataGrid ddg = new AdvancedDefinicaoDataGrid();
		ddg.setTabelaView("CLIENTE");
		ddg.add(new TextInputDefinicaoCampoDataGrid(i++,"Codigo#", "CLIE_CD_CLIENTE"));
		ddg.add(new TextInputDefinicaoCampoDataGrid(i++,"Nome", "CLIE_NM_CLIENTE"));
		ddg.add(new TextInputDefinicaoCampoDataGrid(i++,"Email", "CLIE_TX_EMAIL"));
		ddg.add(new DateDefinicaoCampoDataGrid(i++,"Aniversario", "CLIE_DT_NASCIMENTO"));
		ddg.add(new TextInputDefinicaoCampoDataGrid(i++,"STATUS", "CLIE_IN_STATUS"));
		return ddg;
	}

	
}