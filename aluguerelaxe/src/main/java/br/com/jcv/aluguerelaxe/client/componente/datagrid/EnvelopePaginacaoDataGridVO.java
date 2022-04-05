package br.com.jcv.aluguerelaxe.client.componente.datagrid;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class EnvelopePaginacaoDataGridVO implements IsSerializable {
	public long totalRegistros;
	public List<RegDataGridVO> lst;
}
