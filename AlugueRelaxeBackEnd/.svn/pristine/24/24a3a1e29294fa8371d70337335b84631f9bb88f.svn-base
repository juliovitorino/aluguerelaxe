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

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import br.com.jcv.backend.cidade.CidadeDTO;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.dto.CidadeUFDTO;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfaces.business.BusinessObject;
import br.com.jcv.backend.interfaces.business.UFBusiness;
import br.com.jcv.backend.interfaces.services.UFService;
import br.com.jcv.backend.mensagem.MensagemCache;

/**
 * <h1>UFServices</h1> 
 * <p>Classe de serviços para UF</p>
 * <p>Esta classe tem por objetivo invocar os Métodos de negócio nos respectivos Business Object apontados pela interface <code>BusinessObject</code>.
 * </p>
 * <p>Esta é a camada de comunicação que o cliente usa para ter acesso ao backend de negócios, podem ser:</p>
 * <ul>
 *    <li>Chamada RPC via servlet</li>
 *    <li>JUnit</li>
 * </ul>
 * <p> Todo Método de <code>ApplicationService</code> que invocar uma chamada a Implementação de <code>BusinessObject</code> envolvendo acesso a dados, deve quando necessério
 * abrir um contexto de transação, tal como apresentando no código abaixo:
 * <pre>		
 * 		try {
 *			daoFactory = DAOFactory.getDAOFactory();
 *			daoFactory.open();
 *			daoFactory.beginTransaction();
 *			
 *			BusinessObject<UFDTO> bo = new UFBusiness();
 *			dtoretorno = bo.atualizar(daoFactory, dto);
 *			daoFactory.commit();
 *			dtoretorno.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
 *			dtoretorno.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);
 *		} catch (Exception e) {
 *			daoFactory.rollback();
 *			e.printStackTrace();
 *			throw new AlugueRelaxeException(e);
 *		} finally {
 *			try {
 *				daoFactory.close();
 *			} catch (Exception e) {
 *				throw new AlugueRelaxeException(e);
 *			}
 *		}
 * </pre>
 * 
 * <br>O ideal é que toda Método de <code>ApplicationService</code> ao seu final devolva encapsulada uma mensagem de retorno
 * para que o cliente que invocou o serviéo tenha o retorno da operação e decida fazer o que for melhor para seu processo.
 * </p>
 * @author Júlio Vitorino
 * @version 1.10
 * @since 07 May 2009
 */
public class UFServiceImpl implements UFService<UFDTO> {

	/**
	 * <h2>Atributo logger</h2>
	 * <p>Usado para obter uma instância de <code>Logger</code>
	 * para a classe UFServices.</p>
	 */
	private static Logger logger = Logger.getLogger(UFServiceImpl.class); 

	/**
	 * <h2>atualizarRegistro()</h2>
	 * <p>Método com a finalidade de realizar a atualizaééo de registros 
	 * no banco de dados usando o metodo de negócio <code>BusinessObject.atualizar()</code>.</p>
	 * @param UFDTO - DTO contendo os dados que serão atualizados fornecidos pela camada cliente.
	 * @return UFDTO - DTO Contendo o resultado da operação.
	 * @exception AlugueRelaxeException - Exceção padrão de nível de aplicação.
	 */
	public UFDTO atualizarRegistro(UFDTO dto) throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		UFDTO dtoretorno = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			
			BusinessObject<UFDTO> bo = new UFBusinessImpl();
			dtoretorno = bo.atualizar(daoFactory, dto);
			
			daoFactory.commit();
			dtoretorno.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			dtoretorno.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
				throw new AlugueRelaxeException(e);
			}
		}
		return dtoretorno;
	}

	/**
	 * <h2>excluirRegistro()</h2>
	 * <p>Método com a finalidade de realizar a exclusão de registros 
	 * no banco de dados usando o metodo de negócio <code>BusinessObject.excluir()</code>.</p>
	 * @param UFDTO - DTO contendo a chave de pesquisa para exclusão do registro.
	 * @return UFDTO - DTO Contendo o resultado da operação.
	 * @exception AlugueRelaxeException - Exceção padrão de nível de aplicação.
	 */
	public UFDTO excluirRegistro(UFDTO dto) throws AlugueRelaxeException {
		UFDTO dtoretorno = null;
		DAOFactory daoFactory =  null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			BusinessObject<UFDTO> bo = new UFBusinessImpl();
			bo.excluir(daoFactory, dto);
			daoFactory.commit();
			dtoretorno = new UFDTO();
			dtoretorno.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			dtoretorno.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);

		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
				throw new AlugueRelaxeException(e);
			}
		}
		return dtoretorno;
	}

	/**
	 * <h2>gravarRegistro()</h2>
	 * <p>Método com a finalidade de realizar a inclusão de registros 
	 * no banco de dados usando o metodo de negócio <code>BusinessObject.incluir()</code>.</p>
	 * @param UFDTO - DTO contendo os dados para inclusão do registro.
	 * @return UFDTO - DTO Contendo o resultado da operação.
	 * @exception AlugueRelaxeException - Exceção padrão de nível de aplicação.
	 */
	public UFDTO gravarRegistro(UFDTO dto) throws AlugueRelaxeException {

		DAOFactory daoFactory =  null;
		UFDTO dtoretorno = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			BusinessObject<UFDTO> bo = new UFBusinessImpl();
			dtoretorno = bo.incluir(daoFactory, dto);
			daoFactory.commit();
			dtoretorno.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			dtoretorno.msgcodeString = MensagemCache.getInstance().getMensagem(dtoretorno.msgcode);
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
				throw new AlugueRelaxeException(e);
			}
		}
		return dtoretorno;	
	}

	/**
	 * <h2>listarRegistros()</h2>
	 * <p>Método com a finalidade de retornar toda a lista de registros 
	 * do banco de dados usando o metodo de negócio <code>BusinessObject.buscarTodas()</code>.</p>
	 * @param UFDTO - DTO contendo os dados para inclusão do registro.
	 * @return UFDTO - DTO Contendo o resultado da operação.
	 * @exception AlugueRelaxeException - Exceção padrão de nível de aplicação.
	 */
	public List<UFDTO> listarRegistros() throws AlugueRelaxeException {

		DAOFactory daoFactory =  null;
		List<UFDTO> list = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			BusinessObject<UFDTO> bo = new UFBusinessImpl();
			list = bo.buscarTodas(daoFactory);
			daoFactory.commit();

		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
				throw new AlugueRelaxeException(e);
			}
		}
		return list;
	}

	/**
	 * <h2>pesquisarRegistros()</h2>
	 * <p>Método com a finalidade de retornar um registro  
	 * do banco de dados usando o metodo de negócio <code>BusinessObject.procurar()</code>.</p>
	 * @param UFDTO - DTO contendo a chave de pesquisa do registro.
	 * @return UFDTO - DTO Contendo o resultado da operação.
	 * @exception AlugueRelaxeException - Exceção padrão de nível de aplicação.
	 */
	public UFDTO pesquisarRegistro(UFDTO dto) throws AlugueRelaxeException {

		DAOFactory daoFactory =  null;
		UFDTO dtoretorno = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			BusinessObject<UFDTO> bo = new UFBusinessImpl();
			UFDTO dtoPesquisa = bo.procurar(daoFactory, dto);
			
			dtoretorno = new UFDTO();
			dtoretorno.setId(dtoPesquisa.getId());
			dtoretorno.setDescricao(dtoPesquisa.getDescricao());
			
			// Apresenta os dados no console
			logger.debug("Dados localizados");
			logger.debug("código --> " + dtoPesquisa.getId());
			logger.debug("Descricao --> " + dtoPesquisa.getDescricao());
			
			daoFactory.commit();

		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
				throw new AlugueRelaxeException(e);
			}
		}
		return dtoretorno;
	}
	/**
	 * <h2>listarMaioresQtdeImoveisPorEstado()</h2>
	 * <p>Método com a reponsabilidade de totalizar todos os imoveis por estado 
	 * @return List - DTO Contendo o resultado da operação.
	 * @exception AlugueRelaxeException - Exceção padrão de nível de aplicação.
	 */
	public List<UFDTO> listarMaioresQtdeImoveisPorEstado() throws AlugueRelaxeException {

		DAOFactory daoFactory =  null;
		List<UFDTO> list = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			UFBusinessImpl bo = new UFBusinessImpl();
			list = bo.listarMaioresQtdeImoveisPorEstado(daoFactory);
			daoFactory.commit();

		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
				throw new AlugueRelaxeException(e);
			}
		}
		return list;
	}
	/**
	 * <h2>listarEstadosMaisVisitados()</h2>
	 * <p>Método com a reponsabilidade de totalizar todos os imoveis por estado 
	 * @return List - DTO Contendo o resultado da operação.
	 * @exception AlugueRelaxeException - Exceção padrão de nível de aplicação.
	 */
	public List<UFDTO> listarEstadosMaisVisitados() throws AlugueRelaxeException {

		DAOFactory daoFactory =  null;
		List<UFDTO> list = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			UFBusinessImpl bo = new UFBusinessImpl();
			list = bo.listarEstadosMaisVisitados(daoFactory);
			daoFactory.commit();

		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
				throw new AlugueRelaxeException(e);
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<CidadeDTO> listarCidadesDaUF(String uf) throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		List<CidadeDTO> list = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			UFBusiness bo = new UFBusinessImpl();
			list = bo.listarCidadesDaUF(daoFactory, uf);
			daoFactory.commit();

		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
				throw new AlugueRelaxeException(e);
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<CidadeDTO> listarCidadesDaUFComImoveis(String uf) throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		List<CidadeDTO> list = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			UFBusiness bo = new UFBusinessImpl();
			list = bo.listarCidadesDaUFComImoveis(daoFactory, uf);
			daoFactory.commit();

		} catch (AlugueRelaxeException are) {
			logger.error(are.getMessage(), are);
			daoFactory.rollback();
			throw are;

		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
				throw new AlugueRelaxeException(e);
			}
		}
		return list;
	}
	
	public List<UFDTO> listarSumarizadoImoveisUF() throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		List<UFDTO> list = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			UFBusiness bo = new UFBusinessImpl();
			list = bo.listarSumarizadoImoveisUF(daoFactory);
			daoFactory.commit();

		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
				throw new AlugueRelaxeException(e);
			}
		}
		return list;
	}

	public String charterSumarizadoImoveisUF() throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		String list = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			UFBusiness bo = new UFBusinessImpl();
			list = bo.charterSumarizadoImoveisUF(daoFactory);
			daoFactory.commit();

		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
				throw new AlugueRelaxeException(e);
			}
		}
		return list;
	}
	/**
	 * <h2>ProcurarUFCidadeItem()</h2>
	 */
	public CidadeUFDTO ProcurarUFCidadeItem(long idUfCidadeItem) throws AlugueRelaxeException {

		DAOFactory daoFactory =  null;
		CidadeUFDTO dtoPesquisa = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			UFBusiness<?> bo = new UFBusinessImpl();
			dtoPesquisa = bo.ProcurarUFCidadeItem(daoFactory, idUfCidadeItem);
			
			daoFactory.commit();

		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
				throw new AlugueRelaxeException(e);
			}
		}
		return dtoPesquisa;
	}

}
