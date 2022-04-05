package br.com.jcv.backend.interfaces.services;

import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.exception.AlugueRelaxeException;

public interface ImovelImagemVideoService<DTO> extends ApplicationService<DTO> {
	
	DTOPadrao adicionarVideoImovel(DTO dto) throws AlugueRelaxeException;

}
