package br.com.jcv.backend.interfaces.services;

import java.util.List;

import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.exception.AlugueRelaxeException;


/**
 * @author Julio
 * @version 1.0
 * @created 18 Jan 2012
 */
public interface AssinantesService<DTO> extends ApplicationService<DTO> {

	/**
	 * <h2>inscricao</h2>
	 * <p>Inscricao de novos assinantes</p>
	 * @param DTO
	 * @return DTO
	 * @throws AlugueRelaxeException
	 */	
	 DTO inscricao(DTO dto) throws AlugueRelaxeException;;	 

	 /**
	 * <h2>inscricao</h2>
	 * <p>Inscricao de novos assinantes e seus amigos indicados</p>
	 * @param DTO
	 * @param List<DTO>
	 * @return DTOPadrao
	 * @throws AlugueRelaxeException
	 */	
	 DTOPadrao inscricao(DTO dto, List<DTO> amigos) throws AlugueRelaxeException;	 

	 /**
	 * <h2>inscricao</h2>
	 * <p>Muda o status de uma inscricao do tipo P para A</p>
	 * @param String
	 * @return DTOPadrao
	 * @throws AlugueRelaxeException
	 */	
	 DTOPadrao ativarInscricao(String hash) throws AlugueRelaxeException;	 
	 
	 /**
	 * <h2>listarRegistros</h2>
	 * <p>Lista as assinaturas da promocao ativa de acordo com o status</p>
	 * @param String
	 * @return List<DTO>
	 * @throws AlugueRelaxeException
	 */	
	 List<DTO> listarRegistros(String status) throws AlugueRelaxeException;
	 /**
	 * <h2>listarRegistros</h2>
	 * <p>Lista as assinaturas dde uma determinada de acordo com o status</p>
	 * @param String
	 * @return List<DTO>
	 * @throws AlugueRelaxeException
	 */	
	 List<DTO> listarRegistros(String campanha, String status) throws AlugueRelaxeException;
	 
	 /**
	 * <h2>enviarEmailAtivacaoAssinatura</h2>
	 * <p>Envia email solicitando confirmacao de cadastro da assinatura</p>
	 * @param String
	 * @return List<DTO>
	 * @throws AlugueRelaxeException
	 */	
	 void enviarEmailAtivacaoAssinatura(DTO dto) throws AlugueRelaxeException;
}