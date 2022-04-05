package br.com.jcv.backend.datatransfer;

import br.com.jcv.backend.dto.DTOPadrao;

/**
* <h2>AbstractDataTransferObject</h2>
* <p>Interface para orientacao da implementação de apoio no 
* Server Side para transferencia do DTO provido da camada de negocio
* e alimentar o VO da camada de visao (GWT)</p>
* @author Julio Vitorino
*/
public interface AbstractDataTransferObject<DTO extends DTOPadrao, VO> {
	VO  getDataTransferObject(DTO dto);
	DTO getDataTransferObject(VO vo);
}