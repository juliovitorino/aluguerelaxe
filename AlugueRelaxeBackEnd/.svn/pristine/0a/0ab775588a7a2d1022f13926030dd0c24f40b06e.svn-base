/**
 * 
 */
package br.com.jcv.backend.cidade;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.dto.CidadeUFDTO;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfaces.business.BusinessObject;
import br.com.jcv.backend.interfaces.services.CidadeService;
import br.com.jcv.backend.mensagem.MensagemCache;
import br.com.jcv.backend.utility.Introspeccao;

/**
 * <h1>CidadeServices</h1> 
 * <p>Classe de serviços para Cidade</p>
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
public class CidadeServiceImpl implements CidadeService<CidadeDTO> {
	/**
	 * <h2>Atributo logger</h2>
	 * <p>Usado para obter uma instância de <code>Logger</code>
	 * para a classe UFServices.</p>
	 */
	private static final Logger logger = Logger.getLogger(CidadeServiceImpl.class.getName());
	/**
	 * <h2>atualizarRegistro()</h2>
	 * <p>Método com a finalidade de realizar a atualizaééo de registros 
	 * no banco de dados usando o metodo de negócio <code>BusinessObject.atualizar()</code>.</p>
	 * @param CidadeDTO - DTO contendo os dados que serão atualizados fornecidos pela camada cliente.
	 * @return CidadeDTO - DTO Contendo o resultado da operação.
	 * @exception AlugueRelaxeException - Exceção padrão de nível de aplicação.
	 */
	public CidadeDTO atualizarRegistro(CidadeDTO dto)
			throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		CidadeDTO dtoretorno = null;

		logger.info("Executando atualizando de registro:");
		Introspeccao.debugdto(logger, dto);
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			BusinessObject<CidadeDTO> bo = new CidadeBusinessImpl();
			dtoretorno = bo.atualizar(daoFactory, dto);
			daoFactory.commit();
			Introspeccao.debugdto(logger, dtoretorno);
			dtoretorno.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			dtoretorno.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);
			logger.info(dtoretorno.msgcode + "-" + dtoretorno.msgcodeString);
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
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
	 * @param CidadeDTO - DTO contendo a chave de pesquisa para exclusão do registro.
	 * @return CidadeDTO - DTO Contendo o resultado da operação.
	 * @exception AlugueRelaxeException - Exceção padrão de nível de aplicação.
	 */
	public CidadeDTO excluirRegistro(CidadeDTO dto) throws AlugueRelaxeException {
		CidadeDTO dtoretorno = null;
		DAOFactory daoFactory =  null;

		logger.info("Executando excluir registro:");
		Introspeccao.debugdto(logger, dto);
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			BusinessObject<CidadeDTO> bo = new CidadeBusinessImpl();
			bo.excluir(daoFactory, dto);
			daoFactory.commit();
			Introspeccao.debugdto(logger, dto);
			dtoretorno = new CidadeDTO();
			dtoretorno.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			dtoretorno.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);
			logger.info(dtoretorno.msgcode + "-" + dtoretorno.msgcodeString);
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		return dtoretorno;
	}

	/**
	 * <h2>gravarRegistro()</h2>
	 * <p>Método com a finalidade de realizar a inclusão de registros 
	 * no banco de dados usando o metodo de negócio <code>BusinessObject.incluir()</code>.</p>
	 * @param CidadeDTO - DTO contendo os dados para inclusão do registro.
	 * @return CidadeDTO - DTO Contendo o resultado da operação.
	 * @exception AlugueRelaxeException - Exceção padrão de nível de aplicação.
	 */
	public CidadeDTO gravarRegistro(CidadeDTO dto) throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		CidadeDTO dtoretorno = null;

		logger.info("Executando gravar registro:");
		Introspeccao.debugdto(logger, dto);

		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			
			BusinessObject<CidadeDTO> bo = new CidadeBusinessImpl();
			dtoretorno = bo.incluir(daoFactory, dto);
			
			daoFactory.commit();
			Introspeccao.debugdto(logger, dto);
			
			dtoretorno.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			dtoretorno.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);
			logger.info(dtoretorno.msgcode + "-" + dtoretorno.msgcodeString);
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		return dtoretorno;
	}

	/**
	 * <h2>listarRegistros()</h2>
	 * <p>Método com a finalidade de retornar toda a lista de registros 
	 * do banco de dados usando o metodo de negócio <code>BusinessObject.buscarTodas()</code>.</p>
	 * @param CidadeDTO - DTO contendo os dados para inclusão do registro.
	 * @return CidadeDTO - DTO Contendo o resultado da operação.
	 * @exception AlugueRelaxeException - Exceção padrão de nível de aplicação.
	 */
	public List<CidadeDTO> listarRegistros() throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		List<CidadeDTO> list = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			BusinessObject<CidadeDTO> bo = new CidadeBusinessImpl();
			list = bo.buscarTodas(daoFactory);
			daoFactory.commit();
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		return list;
	}

	/**
	 * <h2>pesquisarRegistros()</h2>
	 * <p>Método com a finalidade de retornar um registro  
	 * do banco de dados usando o metodo de negócio <code>BusinessObject.procurar()</code>.</p>
	 * @param CidadeDTO - DTO contendo a chave de pesquisa do registro.
	 * @return CidadeDTO - DTO Contendo o resultado da operação.
	 * @exception AlugueRelaxeException - Exceção padrão de nível de aplicação.
	 */
	public CidadeDTO pesquisarRegistro(CidadeDTO dto)
			throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		CidadeDTO dtoretorno = null;
		Introspeccao.debugdto(logger, dto);
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			BusinessObject<CidadeDTO> bo = new CidadeBusinessImpl();
			CidadeDTO dtoPesquisa = bo.procurar(daoFactory, dto);
			
			dtoretorno = new CidadeDTO();
			dtoretorno.setId(dtoPesquisa.getId());
			dtoretorno.setNome(dtoPesquisa.getNome());
			
			daoFactory.commit();
			Introspeccao.debugdto(logger, dtoretorno);

			dtoretorno.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			dtoretorno.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);
			logger.info(dtoretorno.msgcode + "-" + dtoretorno.msgcodeString);

		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		return dtoretorno;
	}

	public List<CidadeDTO> listCidadesDaUfComImoveis(String uf) throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		List<CidadeDTO> dtoretorno = null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			CidadeBusinessImpl bo = new CidadeBusinessImpl();
			dtoretorno = bo.listCidadesDaUfComImoveis(daoFactory, uf);
			daoFactory.commit();
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		return dtoretorno;
	}


	public List<CidadeUFDTO> listCidadesDaUfComImoveis() throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		List<CidadeUFDTO> dtoretorno = null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			CidadeBusinessImpl bo = new CidadeBusinessImpl();
			dtoretorno = bo.listCidadesDaUfComImoveis(daoFactory);
			daoFactory.commit();
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		return dtoretorno;
	}

	
	public List<CidadeDTO> listCidadesDaUfComImovesMaisVisitadas(String uf) throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		List<CidadeDTO> dtoretorno = null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			CidadeBusinessImpl bo = new CidadeBusinessImpl();
			dtoretorno = bo.listCidadesDaUfComImovesMaisVisitadas(daoFactory, uf);
			daoFactory.commit();
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		return dtoretorno;
	}
	
}
