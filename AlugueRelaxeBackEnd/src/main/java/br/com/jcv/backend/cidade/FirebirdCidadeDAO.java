/*
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
package br.com.jcv.backend.cidade;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.dto.CidadeUFDTO;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfacesdao.CidadeDAO;
import br.com.jcv.backend.utility.Introspeccao;

/**
 * <h1>FirebirdCidadeDAO</h1> 
 * <p>Objetivo desta classe é gerenciar todos os Métodos de acesso a dados na tabela CIDADE.
 *
 * @author Júlio Vitorino
 * @version 1.10
 * @since 07 May 2009
 */
public class FirebirdCidadeDAO implements CidadeDAO<CidadeDTO> {
	private static Logger logger = Logger.getLogger(FirebirdCidadeDAO.class.getName());

	/**
	 * SQL_LISTA_CIDADE_DA_UF - Listar as 6 cidades de uma UF que possui imoveis
	 */
	private static String SQL_LISTA_CIDADE_DA_UF = "SELECT FIRST 6" +
												   "       a.CIDA_CD_CIDADE," +
    											   "       b.CIDA_NM_CIDADE " + 
    											   "FROM UF_CIDADE_ITEM a," +
    											   "     CIDADE b " +
    											   "WHERE a.CIDA_CD_CIDADE = b.CIDA_CD_CIDADE " +
    											   "and   a.UF_CD_ESTADO = ? " +
    											   "and   a.UFCI_QT_IMOVEIS > 0 " +
    											   "order by a.UFCI_QT_IMOVEIS DESC";

	private static String SQL_LISTA_FULL_CIDADE_UF = "SELECT " +
												   "a.UFCI_CD_UF_CIDADE_ITEM," +
												   "a.UF_CD_ESTADO," +
    											   "b.CIDA_NM_CIDADE," + 
												   "c.UF_NM_ESTADO " +
    											   "FROM UF_CIDADE_ITEM a " +
												   "INNER JOIN CIDADE b ON a.CIDA_CD_CIDADE = b.CIDA_CD_CIDADE " +
    											   "INNER JOIN UF c ON a.UF_CD_ESTADO = c.UF_CD_ESTADO " +
    											   "WHERE a.UFCI_QT_IMOVEIS > 0";

	/**
	 *  SQL_LISTA_CIDADE_DA_UF_VISITADAS - Query para recuperar as 6 Cidades com maior número de visitas aos imóveis 
	 */
	private static String SQL_LISTA_CIDADE_DA_UF_VISITADAS = "SELECT FIRST 6 " +
															 "b.CIDA_CD_CIDADE," +
															 "c.CIDA_NM_CIDADE," +
															 "sum(a.IMOV_QT_VISITAS) " + 
															 "FROM IMOVEL a, " +
															 "     UF_CIDADE_ITEM b," +
															 "     CIDADE c " +
															 "WHERE a.UFCI_CD_UF_CIDADE_ITEM = b.UFCI_CD_UF_CIDADE_ITEM " +
															 "AND   b.CIDA_CD_CIDADE = c.CIDA_CD_CIDADE " +
															 "AND   b.UF_CD_ESTADO = ? " +
															 "GROUP BY b.CIDA_CD_CIDADE, " +
															 "         c.CIDA_NM_CIDADE " +
															 "HAVING sum(a.IMOV_QT_VISITAS) > 0";
	
	
	/**
	 * <h2>Atributo daofactory</h2>
	 * <p>Objeto DAOFactory que contém um encapsulamento para uma sesséo Hibernate</p>
	 */
	private DAOFactory daofactory = null;
	/**
	 * <h2>Construtor FirebirdCidadeDAO</h2>
	 * <p>Construtor para a classe concreta de acesso a dados Hibernate para Firebird </p>
	 * @param DAOFactory - DAO factory para Hibernate a ser encapsulada.
	 */
	public FirebirdCidadeDAO(DAOFactory daofactory) {
		this.daofactory = daofactory;
	}

	/**
	 * <h2>delete()</h2>
	 * <p>Método para exclusão de registro com base em uma PK fornecida no DTO</p>
	 * @param CidadeDTO - DTO contendo o identificador para excluir o registro.
	 * @throws AlugueRelaxeException - Exceção padrão do sistema.
	 * @return void
	 */
	public void delete(CidadeDTO dto) throws AlugueRelaxeException {
		Introspeccao.debugdto(logger, dto);
		Session session = daofactory.getSession();
		session.delete(dto);
	}

	/**
	 * <h2>insert()</h2>
	 * <p>Método para inclusão de registro com base em um DTO</p>
	 * @param CidadeDTO - DTO contendo os dados para incluir o registro.
	 * @throws AlugueRelaxeException - Exceção padrão do sistema.
	 * @return void
	 */
	public CidadeDTO insert(CidadeDTO dto) throws AlugueRelaxeException {
		Introspeccao.debugdto(logger, dto);
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
	public List<CidadeDTO> list() throws AlugueRelaxeException {
		Session session =  daofactory.getSession();
		List<CidadeDTO> result = session.createQuery("from CidadeDTO").list();
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
	public List<CidadeDTO> list(int start) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query q = session.createQuery("from CidadeDTO");
		q.setFirstResult(start-1);
		q.setMaxResults(Constantes.MAX_RETORNO_REGISTRO);
		List<CidadeDTO> result = q.list();
		return result;
	}

	/**
	 * <h2>load()</h2>
	 * <p>Método para localizar um registro com base em um identificador. 
	 * @param CidadeDTO - DTO contendo o campo chave para a busca.
	 * @return CidadeDTO - DTO contendo as informaéées completas do registro.
	 * @throws AlugueRelaxeException - Exceção padrão do sistema.
	 */
	public CidadeDTO load(CidadeDTO dto) throws AlugueRelaxeException {
		Introspeccao.debugdto(logger, dto);
		Session session = daofactory.getSession();
		Long id = dto.getId();
		CidadeDTO dtoDepoimento = (CidadeDTO) session.load(CidadeDTO.class, id);
		Introspeccao.debugdto(logger, dtoDepoimento);
		return dtoDepoimento;
	}

	/**
	 * <h2>update()</h2>
	 * <p>Método para atualizar um registro com base em um identificador. 
	 * @param CidadeDTO - DTO contendo os dados para atualizaééo na tabela.
	 * @return CidadeDTO - DTO contendo as informaéées completas do registro.
	 * @throws AlugueRelaxeException - Exceção padrão do sistema.
	 */
	public CidadeDTO update(CidadeDTO dto) throws AlugueRelaxeException {
		Introspeccao.debugdto(logger, dto);
		Session session = daofactory.getSession();
		session.update(dto);
		return dto;
	}
	
	public List<CidadeUFDTO> listCidadesDaUfComImoveis() throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_LISTA_FULL_CIDADE_UF);
		List<Object> resultado = qry.list();
		List<CidadeUFDTO> retorno = null;
		if (resultado.size() > 0) {
			retorno = new ArrayList<CidadeUFDTO>();
			for (int i = 0; i < resultado.size(); i++) {
				CidadeUFDTO dto = getDTO((Object[]) resultado.get(i));
				retorno.add(dto);
			}
		}
		return retorno;
	}

	public List<CidadeDTO> listCidadesDaUfComImoveis(String uf) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_LISTA_CIDADE_DA_UF);
		List<Object> resultado = qry.setString(0, uf).list();
		List<CidadeDTO> retorno = new ArrayList<CidadeDTO>();
		if (resultado.size() > 0) {
			for (int i = 0; i < resultado.size(); i++) {
				Object dto[] = (Object[])resultado.get(i);
				CidadeDTO cidadedto = new CidadeDTO();
				cidadedto.setId(Long.valueOf(((Integer)dto[0]).longValue()));
				cidadedto.setNome((String)dto[1]);
				retorno.add(cidadedto);
			}
		}
		return retorno;
	}

	public List<CidadeDTO> listCidadesDaUfComImovesMaisVisitados(String uf)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_LISTA_CIDADE_DA_UF_VISITADAS);
		List<Object> resultado = qry.setString(0, uf).list();
		List<CidadeDTO> retorno = new ArrayList<CidadeDTO>();
		if (resultado.size() > 0) {
			for (int i = 0; i < resultado.size(); i++) {
				Object dto[] = (Object[])resultado.get(i);
				CidadeDTO cidadedto = new CidadeDTO();
				cidadedto.setId(Long.valueOf(((Integer)dto[0]).longValue()));
				cidadedto.setNome((String)dto[1]);
				retorno.add(cidadedto);
			}
		}
		return retorno;
	}

	private CidadeUFDTO getDTO(Object[] registro) throws AlugueRelaxeException {
		CidadeUFDTO dto = new CidadeUFDTO();
		dto.cidade = new CidadeDTO();
		int j = -1;
		dto.id = Long.valueOf(registro[++j].toString());
		dto.uf = (registro[++j] == null ? null : registro[j].toString());
		dto.cidade.nome = (registro[++j] == null ? null : registro[j].toString());
		dto.ufCompleta = (registro[++j] == null ? null : registro[j].toString());
		return dto;
	}

}
