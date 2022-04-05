
package br.com.jcv.backend.interfaces.business;

import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;

public interface AssinantesBusiness<DTO> extends BusinessObject<DTO> {
	
	/**
	 * inscrever - Popular o DTO com informacoes de seguranca para a geracao da inscricao do novo assinante em estado de Pendencia (P)
	 * @param DAOFactory
	 * @param DTO
	 * @throws AlugueRelaxeException
	 */
	DTO inscrever(DAOFactory daoFactory, DTO dto) throws AlugueRelaxeException;
	
	/**
	 * ativarInscricao - Muda o status da inscricao do assinante para A - Ativo
	 * @param DAOFactory
	 * @param String
	 * @throws AlugueRelaxeException
	 */
	void ativarInscricao(DAOFactory daoFactory, String hash) throws AlugueRelaxeException;

	/**
	 * procurar - Procura uma inscricao pelo codigo hash
	 * @param DAOFactory
	 * @param String
	 * @throws AlugueRelaxeException
	 */
	DTO procurar(DAOFactory daofactory, String hash) throws AlugueRelaxeException;
	
	/**
	 * procurar - Procura uma inscricao pelo parent
	 * @param DAOFactory
	 * @param String
	 * @throws AlugueRelaxeException
	 */
	DTO procurar(DAOFactory daofactory, DTO dto, String campanha, long parent) throws AlugueRelaxeException;
	
	/**
	 * verificarIndicacaoAmigo - Verificar se um amigo ja foi indicado por outra pessoa na mesma campanha
	 * @param DAOFactory
	 * @param String
	 * @throws AlugueRelaxeException
	 */
	DTO verificarIndicacaoAmigo(DAOFactory daoFactory, DTO amigodto) throws AlugueRelaxeException;
	
	/**
	 * verificarIndicadoViraParticipante - Verificar se um indicado quer virar participante duplicado na mesma campanha
	 * @param DAOFactory
	 * @param String
	 * @throws AlugueRelaxeException
	 */
	DTO verificarIndicadoViraParticipante(DAOFactory daoFactory, DTO dto) throws AlugueRelaxeException;
	
	/**
	 * listarRegistros 
	 * Listar os registros de uma determinada promocao em determinado status
	 * @param DAOFactory
	 * @param String
	 * @param String
	 * @throws AlugueRelaxeException
	 */
	List<DTO> listarRegistros(DAOFactory daoFactory, String promocao, String status) throws AlugueRelaxeException;
}