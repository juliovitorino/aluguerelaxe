package br.com.jcv.aluguerelaxe.server.datatransferobject;

import br.com.jcv.aluguerelaxe.client.modopublicidade.ModoPublicidadeVO;
import br.com.jcv.backend.datatransfer.AbstractDataTransferObject;
import br.com.jcv.backend.modopublicidade.ModoPublicidadeDTO;

public class DTOModoPublicidadeImpl implements
		AbstractDataTransferObject<ModoPublicidadeDTO, ModoPublicidadeVO> {

	public ModoPublicidadeVO getDataTransferObject(ModoPublicidadeDTO dto) {
		ModoPublicidadeVO vo = null;
		if (dto != null){
			vo = new ModoPublicidadeVO();
			vo.id = dto.id;
			vo.descricao = dto.descricao;
		}
		return vo;
	}

	public ModoPublicidadeDTO getDataTransferObject(ModoPublicidadeVO vo) {
		ModoPublicidadeDTO dto = null;
		if (vo != null){
			dto.id = vo.id;
			dto.descricao = dto.descricao;			
		}
		return dto;
	}

}
