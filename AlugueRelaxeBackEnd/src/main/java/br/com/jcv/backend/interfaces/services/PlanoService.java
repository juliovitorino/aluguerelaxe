
package br.com.jcv.backend.interfaces.services;

import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;


public interface PlanoService<DTO> extends ApplicationService<DTO> {
	List<? extends DTO> listarRegistros(String tipo) throws AlugueRelaxeException;
	DTO pesquisarRegistro(long idPlano) throws AlugueRelaxeException;
}

