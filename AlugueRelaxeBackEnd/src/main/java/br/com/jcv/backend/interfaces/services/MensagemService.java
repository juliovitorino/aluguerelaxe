package br.com.jcv.backend.interfaces.services;

import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.mensagem.MensagemDTO;


/**
 * @author Julio
 * @version 1.0
 * @created 06-nov-2009 22:07:34
 */
public interface MensagemService<DTO> extends ApplicationService<DTO> {
	public List<MensagemDTO> listarRegistros(String sufixo) throws AlugueRelaxeException;

}