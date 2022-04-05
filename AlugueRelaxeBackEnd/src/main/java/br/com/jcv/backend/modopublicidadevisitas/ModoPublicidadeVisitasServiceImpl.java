package br.com.jcv.backend.modopublicidadevisitas;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfaces.business.ModoPublicidadeVisitasBusiness;
import br.com.jcv.backend.interfaces.services.ModoPublicidadeVisitasService;
import br.com.jcv.backend.mensagem.MensagemCache;


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


/**
 * <h1>ModoPublicidadeVisitasServiceImpl</h1> 
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
public class ModoPublicidadeVisitasServiceImpl implements ModoPublicidadeVisitasService<ModoPublicidadeVisitasDTO> {

	/**
	 * <h2>Atributo logger</h2>
	 * <p>Usado para obter uma instância de <code>Logger</code>
	 * para a classe UFServices.</p>
	 */
	private static Logger logger = Logger.getLogger(ModoPublicidadeVisitasServiceImpl.class); 

	/**
	 * <h2>incrementarModoPublicidadeVisita()</h2>
	 * <p>Método com a finalidade de incrementar uma visita em um modo de publicidade em uma determinada data</p>
	 * @param Date
	 * @return long
	 * @exception AlugueRelaxeException - Exceção padrão de nível de aplicação.
	 */
	public DTOPadrao incrementarModoPublicidadeVisita(long idModoPublicidade) throws AlugueRelaxeException{

		DAOFactory daoFactory =  null;
		DTOPadrao dtoretorno = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			ModoPublicidadeVisitasBusiness<ModoPublicidadeVisitasDTO> bo = new ModoPublicidadeVisitasBusinessImpl();
			bo.incrementarModoPublicidadeVisita(daoFactory, new Date(), idModoPublicidade);
			daoFactory.commit();
			
			dtoretorno = new DTOPadrao();
			dtoretorno.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			dtoretorno.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);

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
		return dtoretorno;
	}

	public ModoPublicidadeVisitasDTO gravarRegistro(
			ModoPublicidadeVisitasDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public ModoPublicidadeVisitasDTO excluirRegistro(
			ModoPublicidadeVisitasDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public ModoPublicidadeVisitasDTO atualizarRegistro(
			ModoPublicidadeVisitasDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public ModoPublicidadeVisitasDTO pesquisarRegistro(
			ModoPublicidadeVisitasDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<? extends ModoPublicidadeVisitasDTO> listarRegistros()
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

}
