package br.com.jcv.backend.interfacesdao;

import br.com.jcv.backend.exception.AlugueRelaxeException;

public interface DepoimentoDAO<DTO> extends DAO<DTO> {
	
	DTO nextDepoimento(Long id) throws AlugueRelaxeException;
	DTO prevDepoimento(Long id) throws AlugueRelaxeException;
	void atualizarStatus(long id, String acao) throws AlugueRelaxeException;
}
