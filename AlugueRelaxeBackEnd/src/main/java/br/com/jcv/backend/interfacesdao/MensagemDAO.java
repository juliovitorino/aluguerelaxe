package br.com.jcv.backend.interfacesdao;

import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;

/**
 * @author Julio
 * @version 1.0
 * @created 10-nov-2009 00:19:21
 */
public interface MensagemDAO<DTO> extends DAO<DTO> {
	public List<DTO> list(String prefixo) throws AlugueRelaxeException;
}