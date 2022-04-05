package br.com.jcv.aluguerelaxe.client.administracao.console.publicidades;


import java.util.Date;

import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;
import br.com.jcv.aluguerelaxe.client.plano.PlanoVO;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.user.client.rpc.IsSerializable;

public class VendaPublicidadeVO extends VOPadrao implements IsSerializable {
	public ImovelFichaCompletaVO imovel;
	public PlanoVO planoVendido;
	public Date dataInicio;
}

