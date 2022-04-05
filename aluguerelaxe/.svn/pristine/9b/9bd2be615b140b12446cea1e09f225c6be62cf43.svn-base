package br.com.jcv.aluguerelaxe.server.datatransferobject;

import br.com.jcv.aluguerelaxe.client.vo.CidadeUFVO;
import br.com.jcv.aluguerelaxe.client.vo.CidadeVO;
import br.com.jcv.backend.datatransfer.AbstractDataTransferObject;
import br.com.jcv.backend.dto.CidadeUFDTO;

public class DTOCidadeUFImpl implements AbstractDataTransferObject<CidadeUFDTO, CidadeUFVO> {

	public CidadeUFVO getDataTransferObject(CidadeUFDTO dto) {
		CidadeUFVO vo = null;
		if (dto != null){
			vo = new CidadeUFVO();
			vo.id = dto.id;
			vo.cidade = new CidadeVO();
			vo.cidade.id = dto.cidade.id;
			vo.uf = dto.uf;
		}
		return vo;
	}

	public CidadeUFDTO getDataTransferObject(CidadeUFVO vo) {
		return null;
	}

}
