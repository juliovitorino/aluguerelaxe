/*
*
* Copyright (c) 2009-2009, Julio Cesar Vitorino, Todos os direitos reservados.
*
* This software is the confidential and proprietary information of Sun
* Microsystems, Inc. ("Confidential Information"). You shall not
* disclose such Confidential Information and shall use it only in
* accordance with the terms of the license agreement you entered into
* with Sun.
*
* SUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
* THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
* TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
* PARTICULAR PURPOSE, OR NON-INFRINGEMENT. SUN SHALL NOT BE LIABLE FOR
* ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
* DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
*/
package br.com.jcv.backend.uf;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.jcv.backend.cidade.CidadeDTO;
import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.dto.CidadeUFDTO;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfacesdao.UFDAO;
import br.com.jcv.backend.plano.PlanoDTO;
/**
 * <h1>FirebirdUFDAO</h1> 
 * <p>Objetivo desta classe é gerenciar todos os métodos de acesso a dados na tabela UF.
 *
 * @author Júlio Vitorino
 * @version 1.10
 * @since 07 May 2009
 */
public class FirebirdUFDAO implements UFDAO<UFDTO> {

	private static final String SQL_SUM_IMOVEIS_UF = "select UF_CD_ESTADO, " +
	"sum(UFCI_QT_IMOVEIS) " +
	"from  UF_CIDADE_ITEM " +
	"group by UF_CD_ESTADO " +
	"having  sum(UFCI_QT_IMOVEIS) > 0 ";
	
	private static final String SQL_BASICO_CIDADE_UF_ITEM = "Select " +
	"UFCI_CD_UF_CIDADE_ITEM, " +
    "UFCI_IN_STATUS, " +
    "UFCI_QT_IMOVEIS, " +
    "CIDA_CD_CIDADE, " +
    "UF_CD_ESTADO, " +
    "UFCI_NR_LATITUDE, " +
    "UFCI_NR_LONGITUDE ";
	
	private static final String SQL_BASICO_CIDADE_UF_ITEM_PK = SQL_BASICO_CIDADE_UF_ITEM +
    "from UF_CIDADE_ITEM " + 
    "where UFCI_CD_UF_CIDADE_ITEM = ?";

	/**
	 * SQL_LISTA_CIDADE_DA_UF - Recuperar todas as cidades de uma UF
	 */
	private static final String SQL_LISTA_CIDADE_DA_UF = "select " +	
		"UF_CIDADE_ITEM.UFCI_CD_UF_CIDADE_ITEM, " +
		"CIDADE.CIDA_NM_CIDADE " +
		"from UF_CIDADE_ITEM " +
		"inner join CIDADE on UF_CIDADE_ITEM.CIDA_CD_CIDADE = CIDADE.CIDA_CD_CIDADE " +
		"where UF_CIDADE_ITEM.UF_CD_ESTADO = ? " +
		"order by CIDADE.CIDA_NM_CIDADE";

	/**
	 * SQL_LISTA_CIDADE_DA_UF_COM_IMOVEIS - Recuperar todas as cidades de uma UF que possuem imoveis
	 */
	private static final String SQL_LISTA_CIDADE_DA_UF_COM_IMOVEIS = "select " +	
		"UF_CIDADE_ITEM.UFCI_CD_UF_CIDADE_ITEM, " +
		"CIDADE.CIDA_NM_CIDADE " +
		"from UF_CIDADE_ITEM " +
		"inner join CIDADE on UF_CIDADE_ITEM.CIDA_CD_CIDADE = CIDADE.CIDA_CD_CIDADE " +
		"where UF_CIDADE_ITEM.UF_CD_ESTADO = ? " +
		"and UF_CIDADE_ITEM.UFCI_QT_IMOVEIS > 0 " +
		"order by CIDADE.CIDA_NM_CIDADE";

	/**
	 * SQL_LISTA_MAIORES_UF - Listar as 4 cidades com maior numero de imóveis
	 */
	private static String SQL_LISTA_MAIORES_UF = "SELECT FIRST 4 a.UF_CD_ESTADO," +
       											 "b.UF_NM_ESTADO," +
       											 "sum(a.UFCI_QT_IMOVEIS) TOTAL " +
       											 "FROM UF_CIDADE_ITEM a," +
       											 "UF b " +
       											 "WHERE a.UF_CD_ESTADO = b.UF_CD_ESTADO " +      
       											 "GROUP BY a.UF_CD_ESTADO,b.UF_NM_ESTADO " +
       											 "HAVING sum(a.UFCI_QT_IMOVEIS) > 0";
	
	/**
	 * SQL_LISTA_CIDADE_DA_UF_VISITADAS - Query para recuperar as 4 Ufs com maior número de visitas aos imóveis
	 */
	private static String SQL_LISTA_CIDADE_DA_UF_VISITADAS = "SELECT FIRST 4 " +
															 "b.UF_CD_ESTADO," +
															 "c.UF_NM_ESTADO," +
															 "sum(a.IMOV_QT_VISITAS) " +
															 "FROM IMOVEL a," +
															 "UF_CIDADE_ITEM b," +
															 "UF c " +
															 "WHERE a.UFCI_CD_UF_CIDADE_ITEM = b.UFCI_CD_UF_CIDADE_ITEM " +     
															 "AND   b.UF_CD_ESTADO = c.UF_CD_ESTADO " +
															 "GROUP BY b.UF_CD_ESTADO, c.UF_NM_ESTADO " +
															 "HAVING sum(a.IMOV_QT_VISITAS) > 0 " +
															 "ORDER BY 3 DESC";
	
	
	/**
	 * <h2>Atributo daofactory</h2>
	 * <p>Objeto DAOFactory que contém um encapsulamento para uma sesséo Hibernate</p>
	 */
	private DAOFactory daofactory = null;
	
	/**
	 * <h2>Construtor FirebirdUFDAO</h2>
	 * <p>Construtor para a classe concreta de acesso a dados Hibernate para Firebird </p>
	 * @param DAOFactory - DAO factory para Hibernate a ser encapsulada.
	 */
	public FirebirdUFDAO(DAOFactory daofactory) {
		this.daofactory = daofactory;
	}

	/**
	 * <h2>delete()</h2>
	 * <p>Método para exclusão de registro com base em uma PK fornecida no DTO</p>
	 * @param UFDTO - DTO contendo o identificador para excluir o registro.
	 * @throws AlugueRelaxeException - Exceção padrão do sistema.
	 * @return void
	 */
	public void delete(UFDTO dto) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		session.delete(dto);
	}

	/**
	 * <h2>insert()</h2>
	 * <p>Método para inclusão de registro com base em um DTO</p>
	 * @param UFDTO - DTO contendo os dados para incluir o registro.
	 * @throws AlugueRelaxeException - Exceção padrão do sistema.
	 * @return void
	 */
	public UFDTO insert(UFDTO dto) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		session.save(dto);
		return dto;
	}

	/**
	 * <h2>list()</h2>
	 * <p>Método para listar todos registro da tabela</p>
	 * @throws AlugueRelaxeException - Exceção padrão do sistema.
	 * @return List - lista contendo um conjunto de DTO que representam todos
	 * os registros da tabela.
	 */
	public List<UFDTO> list() throws AlugueRelaxeException {
		Session session =  daofactory.getSession();
		List<UFDTO> result = session.createQuery("from UFDTO").list();
		return result;	
	}
	
	/**
	 * <h2>list()</h2>
	 * <p>Método para listar um conjunto de registros definido pela variével <code>Constantes.MAX_RETORNO_REGISTRO</code> 
	 * tabela iniciados por <code>start</code></p>
	 * @param int - Pégina inicial dos registros
	 * @throws AlugueRelaxeException - Exceção padrão do sistema.
	 * @return List - lista contendo um conjunto de DTO que representam todos
	 * os registros da tabela.
	 */
	public List<UFDTO> list(int start) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query q = session.createQuery("from UFDTO");
		q.setFirstResult(start-1);
		q.setMaxResults(Constantes.MAX_RETORNO_REGISTRO);
		List<UFDTO> result = q.list();
		return result;
	}

	/**
	 * <h2>load()</h2>
	 * <p>Método para localizar um registro com base em um identificador. 
	 * @param UFDTO - DTO contendo o campo chave para a busca.
	 * @return UFDTO - DTO contendo as informaéées completas do registro.
	 * @throws AlugueRelaxeException - Exceção padrão do sistema.
	 */
	public UFDTO load(UFDTO dto) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		String id = dto.getId();
		UFDTO dtouf = (UFDTO) session.load(UFDTO.class, id);
		return dtouf;
	}

	/**
	 * <h2>update()</h2>
	 * <p>Método para atualizar um registro com base em um identificador. 
	 * @param UFDTO - DTO contendo os dados para atualizaééo na tabela.
	 * @return UFDTO - DTO contendo as informaéées completas do registro.
	 * @throws AlugueRelaxeException - Exceção padrão do sistema.
	 */
	public UFDTO update(UFDTO dto) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		session.update(dto);
		return dto;
	}

	public List<UFDTO> listMaioresQtdeImoveisPorEstado()
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_LISTA_MAIORES_UF);
		List<Object> resultado = qry.list();
		List<UFDTO> retorno = new ArrayList<UFDTO>();
		if (resultado.size() > 0) {
			for (int i = 0; i < resultado.size(); i++) {
				Object dto[] = (Object[])resultado.get(i);
				UFDTO ufdto = new UFDTO();
				ufdto.setId((String)dto[0]);
				ufdto.setDescricao((String)dto[1]);
				ufdto.setQuantidade(((BigInteger)dto[2]).intValue());
				retorno.add(ufdto);
			}
		}
		
		return retorno;
	}

	public List<UFDTO> listEstadosMaisVisitados() throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_LISTA_CIDADE_DA_UF_VISITADAS);
		List<Object> resultado = qry.list();
		List<UFDTO> retorno = new ArrayList<UFDTO>();
		if (resultado.size() > 0) {
			for (int i = 0; i < resultado.size(); i++) {
				Object dto[] = (Object[])resultado.get(i);
				UFDTO ufdto = new UFDTO();
				ufdto.setId((String)dto[0]);
				ufdto.setDescricao((String)dto[1]);
				ufdto.setQuantidade(((BigInteger)dto[2]).intValue());
				retorno.add(ufdto);
			}
		}
		
		return retorno;
	}

	@SuppressWarnings("unchecked")
	public List<CidadeDTO> listarCidadesDaUF(String uf) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_LISTA_CIDADE_DA_UF);
		qry.setString(0, uf);
		List resultado = qry.list();
		List<CidadeDTO> retorno = new ArrayList<CidadeDTO>();
		if (resultado.size() > 0) {
			for (int i = 0; i < resultado.size(); i++) {
				Object dto[] = (Object[])resultado.get(i);
				CidadeDTO cidadedto = new CidadeDTO();
				int j = -1;
				cidadedto.id = Long.valueOf(dto[++j].toString());
				cidadedto.nome = (dto[++j] == null ? null : (String)dto[j]);
				retorno.add(cidadedto);
			}
		}
		
		return retorno;
	}

	@SuppressWarnings("unchecked")
	public List<CidadeDTO> listarCidadesDaUFComImoveis(String uf) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_LISTA_CIDADE_DA_UF_COM_IMOVEIS);
		qry.setString(0, uf);
		List resultado = qry.list();
		List<CidadeDTO> retorno = new ArrayList<CidadeDTO>();
		if (resultado.size() > 0) {
			for (int i = 0; i < resultado.size(); i++) {
				Object dto[] = (Object[])resultado.get(i);
				CidadeDTO cidadedto = new CidadeDTO();
				int j = -1;
				cidadedto.id = Long.valueOf(dto[++j].toString());
				cidadedto.nome = (dto[++j] == null ? null : (String)dto[j]);
				retorno.add(cidadedto);
			}
		}
		
		return retorno;
	}

	public List<UFDTO> listSumarizadoImoveisUF() throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_SUM_IMOVEIS_UF);
		List<Object> resultado = qry.list();
		List<UFDTO> retorno = new ArrayList<UFDTO>();
		if (resultado.size() > 0) {
			for (int i = 0; i < resultado.size(); i++) {
				Object dto[] = (Object[])resultado.get(i);
				UFDTO ufdto = new UFDTO();
				ufdto.setId((String)dto[0]);
				ufdto.setQuantidade(((BigInteger)dto[1]).intValue());
				retorno.add(ufdto);
			}
		}
		
		return retorno;
	}
	
	public CidadeUFDTO load(long idCidadeUfItem) throws AlugueRelaxeException {
		CidadeUFDTO registroDto = null;
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_BASICO_CIDADE_UF_ITEM_PK);
		qry.setLong(0, idCidadeUfItem);
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			registroDto = getDTO((Object[]) resultado.get(0));
		}
		return registroDto;
	}
	

	private CidadeUFDTO getDTO(Object[] registro) throws AlugueRelaxeException {
		CidadeUFDTO dto = new CidadeUFDTO();
		int j = -1;
		dto.id = Long.valueOf(registro[++j].toString());
		dto.status = (registro[++j] == null ? null : registro[j].toString());
		dto.qtdeImoveis = Long.valueOf(registro[++j].toString());
		dto.cidade = new CidadeDTO();
		dto.cidade.id = Long.valueOf(registro[++j].toString());
		dto.uf = (registro[++j] == null ? null : registro[j].toString());
		return dto;
	}
}
