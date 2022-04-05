package br.com.jcv.backend.interfacesdao;

import br.com.jcv.backend.exception.AlugueRelaxeException;

public interface ChatDAO<DTO> extends DAO<DTO> {
	
	DTO load(String sessao, String status) throws AlugueRelaxeException;
	DTO load(String sessao, long idCliente, String status) throws AlugueRelaxeException;

}
