package br.com.jcv.backend.interfaces.services;

import br.com.jcv.backend.exception.AlugueRelaxeException;

public interface LocatarioService<DTO> extends ApplicationService<DTO> {
	DTO pesquisar(String email) throws AlugueRelaxeException;
}

