
package br.com.jcv.aluguerelaxe.client.mobile.vo;

import java.util.Date;

import br.com.jcv.ui.client.vo.VOPadrao;

import com.google.gwt.user.client.rpc.IsSerializable;

public class PlanoVO extends VOPadrao implements IsSerializable {
	public long id;
	public String nome;
	public String descricao;
	public double valor;
	public String valorstr;
	public String indicadorStatus;
	public Date dataCadastro;
	public Date dataAtivacao;
	public Date dataCancelamento;
	public String indicadorPeriodicidade;
	public String indicadorTipoCompra;
	public String periodicidadeTraducao;
	public int numeroDiasCalculo;
	public String htmlBtnPagseguro;
	public String htmlBtnPayPal;
	public int limiteFotos;
}

