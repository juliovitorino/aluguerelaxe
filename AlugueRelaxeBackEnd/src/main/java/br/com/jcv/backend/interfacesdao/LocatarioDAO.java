package br.com.jcv.backend.interfacesdao;

import br.com.jcv.backend.exception.AlugueRelaxeException;

public interface LocatarioDAO<DTO> extends DAO<DTO> {
	
	DTO load(long id) throws AlugueRelaxeException;
	DTO load(String email) throws AlugueRelaxeException;
	
	 
}