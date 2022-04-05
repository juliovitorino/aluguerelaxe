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
package br.com.jcv.backend.caracteristicas;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfacesdao.CaracteristicaDAO;
import br.com.jcv.backend.mensagem.MensagemCache;
import br.com.jcv.backend.utility.Introspeccao;

/**
 * <h1>FirebirdCaracteristicaDAO</h1> 
 * <p>Objetivo desta classe é gerenciar todos os Métodos de acesso a dados na tabela CARACTERISTICA.
 *
 * @author Júlio Vitorino
 * @version 1.10
 * @since 31 May 2009
 */
public class FirebirdCaracteristicaDAO implements CaracteristicaDAO<CaracteristicaDTO> {
	private static Logger logger = Logger.getLogger(FirebirdCaracteristicaDAO.class.getName());
	
	private static final String SQL_BASICO_CARACTERISTICA = "select " +
		"CARA_CD_CARACTERISTICA," +
		"CARA_NM_CARACTERISTICA," +
		"CARA_TX_ANUNCIO ";
	
	private static final String SQL_FULL_CARACTERISTICA = SQL_BASICO_CARACTERISTICA +
	"from CARACTERISTICA";
	
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
	public FirebirdCaracteristicaDAO(DAOFactory daofactory) {
		this.daofactory = daofactory;
	}

	/**
	 * <h2>delete()</h2>
	 * <p>Método para exclusão de registro com base em uma PK fornecida no DTO</p>
	 * @param CaracteristicaDTO - DTO contendo o identificador para excluir o registro.
	 * @throws AlugueRelaxeException - Exceção padrão do sistema.
	 * @return void
	 */
	public void delete(CaracteristicaDTO dto) throws AlugueRelaxeException {
		Introspeccao.debugdto(logger, dto);
		Session session = daofactory.getSession();
		session.delete(dto);
	}

	/**
	 * <h2>insert()</h2>
	 * <p>Método para inclusão de registro com base em um DTO</p>
	 * @param CaracteristicaDTO - DTO contendo os dados para incluir o registro.
	 * @throws AlugueRelaxeException - Exceção padrão do sistema.
	 * @return void
	 */
	public CaracteristicaDTO insert(CaracteristicaDTO dto) throws AlugueRelaxeException {
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
	public List<CaracteristicaDTO> list() throws AlugueRelaxeException {
		Session session =  daofactory.getSession();
		List<CaracteristicaDTO> result = session.createQuery("from CaracteristicaDTO").list();
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
	public List<CaracteristicaDTO> list(int start) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query q = session.createQuery("from CaracteristicaDTO");
		q.setFirstResult(start-1);
		q.setMaxResults(Constantes.MAX_RETORNO_REGISTRO);
		List<CaracteristicaDTO> result = q.list();
		return result;
	}

	/**
	 * <h2>load()</h2>
	 * <p>Método para localizar um registro com base em um identificador. 
	 * @param CaracteristicaDTO - DTO contendo o campo chave para a busca.
	 * @return CaracteristicaDTO - DTO contendo as informaéées completas do registro.
	 * @throws AlugueRelaxeException - Exceção padrão do sistema.
	 */
	public CaracteristicaDTO load(CaracteristicaDTO dto) throws AlugueRelaxeException {
		Introspeccao.debugdto(logger, dto);
		Session session = daofactory.getSession();
		Long id = dto.getId();
		CaracteristicaDTO dtoDepoimento = (CaracteristicaDTO) session.load(CaracteristicaDTO.class, id);
		Introspeccao.debugdto(logger, dtoDepoimento);
		return dtoDepoimento;
	}

	/**
	 * <h2>update()</h2>
	 * <p>Método para atualizar um registro com base em um identificador. 
	 * @param CaracteristicaDTO - DTO contendo os dados para atualizaééo na tabela.
	 * @return CaracteristicaDTO - DTO contendo as informaéées completas do registro.
	 * @throws AlugueRelaxeException - Exceção padrão do sistema.
	 */
	public CaracteristicaDTO update(CaracteristicaDTO dto) throws AlugueRelaxeException {
		Introspeccao.debugdto(logger, dto);
		Session session = daofactory.getSession();
		session.update(dto);
		return dto;
	}

}
