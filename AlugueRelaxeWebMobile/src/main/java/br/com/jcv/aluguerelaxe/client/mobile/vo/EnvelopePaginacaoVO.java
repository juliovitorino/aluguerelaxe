package br.com.jcv.aluguerelaxe.client.mobile.vo;

import java.util.List;

import br.com.jcv.ui.client.vo.VOPadrao;

import com.google.gwt.user.client.rpc.IsSerializable;

public class EnvelopePaginacaoVO extends VOPadrao implements IsSerializable {
	public long totalRegistros;
	public int indice;
	public List<ImovelFichaCompletaVO> lst;
}
