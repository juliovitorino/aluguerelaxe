package br.com.jcv.backend.interfaces.business;

import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;

/**
 * <h1>MensagemBusiness</h1>
 * <p>Interface responśavel por contratos que a Implementação de 
 * negócio de Mensagem deve realizar.
 * </p>
 * @author julio
 * @version 1.0
 */
public interface MensagemBusiness<DTO> extends BusinessObject<DTO> {
	List<DTO> buscarTodas(DAOFactory daofactory, String sufixo) throws AlugueRelaxeException;

}
