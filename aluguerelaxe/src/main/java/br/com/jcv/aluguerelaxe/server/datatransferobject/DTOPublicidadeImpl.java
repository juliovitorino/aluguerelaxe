package br.com.jcv.aluguerelaxe.server.datatransferobject;

import br.com.jcv.aluguerelaxe.client.publicidade.PublicidadeVO;
import br.com.jcv.backend.datatransfer.AbstractDataTransferObject;
import br.com.jcv.backend.imovel.publicidade.PublicidadeDTO;

public class DTOPublicidadeImpl implements AbstractDataTransferObject<PublicidadeDTO, PublicidadeVO> {

	public PublicidadeVO getDataTransferObject(PublicidadeDTO dto) {
		PublicidadeVO vo = null;
		if (dto != null){
			vo = new PublicidadeVO();
			vo.idPublicidade = dto.idPublicidade;
			vo.dataInicio = dto.dataInicio;
			vo.dataFim = dto.dataFim;
			vo.tipoPublicidade = dto.tipoPublicidade;
			vo.idFatura = dto.idFatura;
		}
		return vo;
	}

	public PublicidadeDTO getDataTransferObject(PublicidadeVO vo) {
		PublicidadeDTO dto = null;
		if (vo != null){
			dto.idPublicidade = vo.idPublicidade;
			dto.dataInicio = vo.dataInicio;
			dto.dataFim = vo.dataFim;
			dto.tipoPublicidade = vo.tipoPublicidade;
			dto.idFatura = vo.idFatura;
		}
		return dto;
	}

}
