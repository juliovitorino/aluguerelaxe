package br.com.jcv.backend.imovel.faturamento;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.plano.PlanoDTO;

public class ImovelPlanoFaturaDTO extends DTOPadrao implements Serializable {
	private static final long serialVersionUID = 4061695047407565453L;
	public long id;
	public long idImovelPlano;
	public PlanoDTO plano;
	public String indicadorStatus;
	public double valorFatura;
	public double valorDesconto;
	public Date dataVencimento;
	public Timestamp dataPagamento;
	public Timestamp dataCadastro;
}