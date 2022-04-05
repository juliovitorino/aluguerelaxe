package br.com.jcv.aluguerelaxe.client.publicidade;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * <h1>PublicidadeVO</h1> 
 * <p>Classe de transferência de dados</p>
 *
 * @author Júlio Vitorino
 * @version 1.10
 * @since 02 Nov 2009
 */

public class PublicidadeVO implements IsSerializable {
	
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

}

