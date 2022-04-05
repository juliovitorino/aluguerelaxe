package br.com.jcv.backend.paginacao;

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


import java.util.List;

import org.hibernate.HibernateException;

import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfaces.business.PaginacaoBusiness;
import br.com.jcv.backend.interfaces.services.PaginacaoService;

/**
 * <h1>PaginacaoServiceImpl</h1> 
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
 *			BusinessObject<PlanoDTO> bo = new UFBusiness();
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
public class PaginacaoServiceImpl implements PaginacaoService {

	/**
	 * <h2>Atributo logger</h2>
	 * <p>Usado para obter uma instância de <code>Logger</code>
	 * para a classe UFServices.</p>
	 */
	//private static Logger logger = Logger.getLogger(PaginacaoServiceImpl.class); 

	/**
	 * <h2>listarRegistros()</h2>
	 * <p>Método com a finalidade de retornar toda a lista de registros 
	 * do banco de dados usando o metodo de negócio <code>BusinessObject.buscarTodas()</code>.</p>
	 * @param PlanoDTO - DTO contendo os dados para inclusão do registro.
	 * @return PlanoDTO - DTO Contendo o resultado da operação.
	 * @exception AlugueRelaxeException - Exceção padrão de nível de aplicação.
	 */
	public EnvelopePaginacaoDataGridDTO listarPorFiltro(String tabelaView,
												List<String> campos,
												List<CondicaoDTO> param,
												List<String> lstOrderBy, 
												int indice, 
												int qtdePorPagina) throws AlugueRelaxeException {

		DAOFactory daoFactory =  null;
		EnvelopePaginacaoDataGridDTO dto = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			PaginacaoBusiness bo = new PaginacaoBusinessImpl();
			dto = bo.listarPorFiltro(daoFactory, tabelaView, campos, param, lstOrderBy, indice, qtdePorPagina);
			daoFactory.commit();

		} catch (HibernateException he) {
			//logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				//logger.error(e.getMessage(),e);
				throw new AlugueRelaxeException(e);
			}
		}
		return dto;
	}
}
