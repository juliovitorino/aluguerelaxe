/*
 * Diretos Reservados (c) 2006-2008 Petrobras - Petróleo Brasileiro S.A.
 *
 * Este software é confidencial e a informação proprietária da
 * Petrobras - Petróleo Brasileiro S.A.
 *
 * Projeto    : Sistema de Gerenciamento de Empreendimentos
 * Versão     : 4.0
 * Cliente    : Petrobras - Petróleo Brasileiro S.A.
 * Fornecedor : Avanti Engenharia Ltda
 * Natureza   : GED
 * Tecnologia : Java
 * Criado em  : Nov 4, 2009
 *
 * Historico de Modificação:
 * =========================
 * Nov 4, 2009 - Início de tudo, por elmt
 *
 */
package br.com.jcv.backend.imovel.publicidade;

import java.io.Serializable;
import java.util.Date;

import br.com.jcv.backend.imovel.ImovelBaseDTO;
import br.com.jcv.backend.imovel.imagem.ImovelImagemVideoDTO;

/**
 * <h1>ImovelPublicidadeDTO</h1> 
 * <p>Classe de transferencia de dados para publicidade do imovel na primeira pagina</p>
 *
 * @author Julio Vitorino
 * @version 1.10
 * @since 12 Aug 2009
 */
public class ImovelPublicidadeDTO extends ImovelBaseDTO implements Serializable {

	/**
	 * Atributo '<code>serialVersionUID</code>' do tipo long
	 */
	private static final long serialVersionUID = 1456561349141024326L;
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
	public Date tipoPublicidade;
	/**
	 * <h2>nomeImagemVideo</h2>
	 * <p>Nome da imagem ou video. Este nome é o nome do arquivo que estará armazenado
	 * no provedor</p>
	 */
	public String nomeImagemVideo;
	/**
	 * <h2>descricaoUF</h2>
	 * <p>Nome da UF de localizacao do imovel em publicidade</p>
	 */
	public String descricaoUF;
	/**
	 * <h2>idImagemPreferida</h2>
	 * <p>Identificador da imagem preferida de publicidade</p>
	 */
	public long idImagemPreferida;
	/**
	 * <h2>imagemPreferida</h2>
	 * <p>DTO da imagem preferida de publicidade</p>
	 */
	public ImovelImagemVideoDTO imagemPreferida;

	
}

