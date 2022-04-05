package br.com.jcv.aluguerelaxe.client.imovel;

import java.sql.Timestamp;
import java.util.Date;

import br.com.jcv.aluguerelaxe.client.plano.PlanoVO;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ImovelPlanoFaturaVO extends VOPadrao implements IsSerializable {
	public long id;
	public long idImovelPlano;
	public PlanoVO plano;
	public String indicadorStatus;
	public String indicadorStatusStr;
	public double valorFatura;
	public double valorDesconto;
	public Date dataVencimento;
	public Timestamp dataPagamento;
	public Timestamp dataCadastro;
	public String valorFinalStr;
	public String dataVencimentoStr;
	public String dataPagamentoStr;
	public String dataCadastroStr;
	public String linkPagamento;
}
