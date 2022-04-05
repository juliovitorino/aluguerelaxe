package br.com.jcv.backend.modopublicidade;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfaces.business.BusinessObject;
import br.com.jcv.backend.interfaces.services.ModoPublicidadeService;
import br.com.jcv.backend.plano.PlanoBusinessImpl;


/**
 * <h1>ModoPublicidadeServicesImpl</h1> 
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
 *			BusinessObject<ModoPublicidadeDTO> bo = new UFBusiness();
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
public class ModoPublicidadeServiceImpl implements ModoPublicidadeService<ModoPublicidadeDTO> {

	/**
	 * <h2>Atributo logger</h2>
	 * <p>Usado para obter uma instância de <code>Logger</code>
	 * para a classe UFServices.</p>
	 */
	private static Logger logger = Logger.getLogger(ModoPublicidadeServiceImpl.class); 

	/**
	 * <h2>atualizarRegistro()</h2>
	 * <p>Método com a finalidade de realizar a atualizaééo de registros 
	 * no banco de dados usando o metodo de negócio <code>BusinessObject.atualizar()</code>.</p>
	 * @param ModoPublicidadeDTO - DTO contendo os dados que serão atualizados fornecidos pela camada cliente.
	 * @return ModoPublicidadeDTO - DTO Contendo o resultado da operação.
	 * @exception AlugueRelaxeException - Exceção padrão de nível de aplicação.
	 */
	public ModoPublicidadeDTO atualizarRegistro(ModoPublicidadeDTO dto) throws AlugueRelaxeException {
		return null;
	}

	/**
	 * <h2>excluirRegistro()</h2>
	 * <p>Método com a finalidade de realizar a exclusão de registros 
	 * no banco de dados usando o metodo de negócio <code>BusinessObject.excluir()</code>.</p>
	 * @param ModoPublicidadeDTO - DTO contendo a chave de pesquisa para exclusão do registro.
	 * @return ModoPublicidadeDTO - DTO Contendo o resultado da operação.
	 * @exception AlugueRelaxeException - Exceção padrão de nível de aplicação.
	 */
	public ModoPublicidadeDTO excluirRegistro(ModoPublicidadeDTO dto) throws AlugueRelaxeException {
		return null;
	}

	/**
	 * <h2>gravarRegistro()</h2>
	 * <p>Método com a finalidade de realizar a inclusão de registros 
	 * no banco de dados usando o metodo de negócio <code>BusinessObject.incluir()</code>.</p>
	 * @param ModoPublicidadeDTO - DTO contendo os dados para inclusão do registro.
	 * @return ModoPublicidadeDTO - DTO Contendo o resultado da operação.
	 * @exception AlugueRelaxeException - Exceção padrão de nível de aplicação.
	 */
	public ModoPublicidadeDTO gravarRegistro(ModoPublicidadeDTO dto) throws AlugueRelaxeException {
		return null;	
	}

	/**
	 * <h2>pesquisarRegistros()</h2>
	 * <p>Método com a finalidade de retornar um registro  
	 * do banco de dados usando o metodo de negócio <code>BusinessObject.procurar()</code>.</p>
	 * @param ModoPublicidadeDTO - DTO contendo a chave de pesquisa do registro.
	 * @return ModoPublicidadeDTO - DTO Contendo o resultado da operação.
	 * @exception AlugueRelaxeException - Exceção padrão de nível de aplicação.
	 */
	public ModoPublicidadeDTO pesquisarRegistro(ModoPublicidadeDTO dto) throws AlugueRelaxeException {

		DAOFactory daoFactory =  null;
		ModoPublicidadeDTO dtoretorno = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			BusinessObject<ModoPublicidadeDTO> bo = new ModoPublicidadeBusinessImpl();
			dtoretorno = bo.procurar(daoFactory, dto);
			
			daoFactory.commit();

		} catch(AlugueRelaxeException are) {
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
		return dtoretorno;
	}
	/**
	 * <h2>listarRegistros()</h2>
	 * <p>Método com a finalidade de retornar toda a lista de registros 
	 * do banco de dados usando o metodo de negócio <code>BusinessObject.buscarTodas()</code>.</p>
	 * @param ModoPublicidadeDTO - DTO contendo os dados para inclusão do registro.
	 * @return ModoPublicidadeDTO - DTO Contendo o resultado da operação.
	 * @exception AlugueRelaxeException - Exceção padrão de nível de aplicação.
	 */
	public List<? extends ModoPublicidadeDTO> listarRegistros() throws AlugueRelaxeException{

		DAOFactory daoFactory =  null;
		List<ModoPublicidadeDTO> list = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			BusinessObject<ModoPublicidadeDTO> bo = new ModoPublicidadeBusinessImpl();
			list = bo.buscarTodas(daoFactory);
			daoFactory.commit();

		} catch(AlugueRelaxeException are) {
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

}
