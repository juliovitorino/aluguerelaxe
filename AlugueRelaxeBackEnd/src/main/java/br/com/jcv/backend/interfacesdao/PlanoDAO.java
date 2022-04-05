package br.com.jcv.backend.interfacesdao;

import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;


public interface PlanoDAO<DTO> extends DAO<DTO> {
	public List<DTO> list(String tipo) throws AlugueRelaxeException;

}
