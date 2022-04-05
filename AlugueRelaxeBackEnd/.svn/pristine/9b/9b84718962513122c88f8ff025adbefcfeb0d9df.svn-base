
package br.com.jcv.backend.plano;

import java.io.Serializable;
import java.sql.Timestamp;

import br.com.jcv.backend.anotacoes.Obrigatorio;
import br.com.jcv.backend.dto.DTOPadrao;

public class PlanoDTO extends DTOPadrao implements Serializable {

	private static final long serialVersionUID = 7328092037765397810L;
	
	public long id;
	
	@Obrigatorio(tamanho=30, notNull=true)
	public String nome;
	
	@Obrigatorio(tamanho=100,notNull=true)
	public String descricao;
	
	public double valor;
	
	public double valorDesconto;
	
	// TODO colocar constantes
	@Obrigatorio(tamanho=1, notNull=true, dominio= {"A", "I"})
	public String indicadorStatus;
	
	public Timestamp dataCadastro;
	
	public Timestamp dataAtivacao;
	
	public Timestamp dataCancelamento;
	
	@Obrigatorio(tamanho=1,notNull=true,dominio={"M","T","S","A","P"})
	public String indicadorPeriodicidade;
	
	public int numeroDiasCalculo;
	
	public String indicadorTipoCompra;
	
	public String htmlBtnPagseguro;
	
	public String htmlBtnPayPal;
	
	public int limiteFotos;
	
	public long bytesVault;
	
	public String[] recurso;
	
}

