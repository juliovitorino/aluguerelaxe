package br.com.jcv.backend.imovel.publicidade;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import br.com.jcv.backend.dto.DTOPadrao;


/**
 * <h1>Publicidade</h1> 
 * <p>Classe de transferencia de dados para publicidade</p>
 *
 * @author Julio Vitorino
 * @version 1.10
 * @since 12 Aug 2009
 */
public class PublicidadeDTO extends DTOPadrao implements Serializable {

	/**
	 * <h2>idPublicidade</h2>
	 * <p>Identificador unico de publicidade do imovel.</p>
	 */
	public long idPublicidade;
	/**
	 * <h2>dataInicio</h2>
	 * <p>Data de inicio da vigencia da publicidade</p>
	 */
	public Date dataInicio;
	/**
	 * <h2>dataFim</h2>
	 * <p>Data de fim da vigencia da publicidade</p>
	 */
	public Date dataFim;
	/**
	 * <h2>tipoPublicidade</h2>
	 * <p>Tipo da publicidade</p>
	 */
	public String tipoPublicidade;
	/**
	 * <h2>idFatura</h2>
	 * <p>Id da Fatura para relacionamento</p>
	 */
	public long idFatura;
	
	public String status;
	/**
	 * <h2>dataCadastro</h2>
	 * <p>Data de cadastro da publicidade</p>
	 */
	public Timestamp dataCadastro;
	
}

