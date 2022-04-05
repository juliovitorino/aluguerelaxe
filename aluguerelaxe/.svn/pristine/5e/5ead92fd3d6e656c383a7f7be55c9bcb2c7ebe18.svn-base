package br.com.jcv.aluguerelaxe.client.datagrid;

import br.com.jcv.aluguerelaxe.client.componente.datagrid.AbstractDataGrid;
import br.com.jcv.aluguerelaxe.client.componente.datagrid.DefinicaoCampoDataGrid;
import br.com.jcv.aluguerelaxe.client.componente.datagrid.DefinicaoDataGrid;

public class PublicidadeAVencerDataGrid extends AbstractDataGrid {

	@Override
	public DefinicaoDataGrid getInstanceDefinicaoDataGrid() {
		int i = 0;
		DefinicaoDataGrid ddg = new DefinicaoDataGrid();
		ddg.setTabelaView("VW_PUBLICIDADE_FATURAS_A_VENCER");
		ddg.add(new DefinicaoCampoDataGrid(i++,"#Id.Fatura", "ID_FATURA"));
		ddg.add(new DefinicaoCampoDataGrid(i++,"Plano", "NOME_PLANO"));
		ddg.add(new DefinicaoCampoDataGrid(i++,"Descrição", "DESC_PLANO"));
		ddg.add(new DefinicaoCampoDataGrid(i++,"#Id.Imovel", "ID_IMOVEL"));
		ddg.add(new DefinicaoCampoDataGrid(i++,"Valor", "VALOR_FATURA"));
		ddg.add(new DefinicaoCampoDataGrid(i++,"Desconto", "VALOR_DESCONTO"));
		ddg.add(new DefinicaoCampoDataGrid(i++,"Dt.Vencimento", "DT_VENCIMENTO"));
		ddg.add(new DefinicaoCampoDataGrid(i++,"Dt.Cadastro", "DT_CADASTRO"));
		return ddg;
	}

}
