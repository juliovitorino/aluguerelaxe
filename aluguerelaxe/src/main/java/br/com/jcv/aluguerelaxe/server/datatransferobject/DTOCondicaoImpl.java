package br.com.jcv.aluguerelaxe.server.datatransferobject;

import br.com.jcv.aluguerelaxe.client.componente.datagrid.CondicaoVO;
import br.com.jcv.backend.datatransfer.AbstractDataTransferObject;
import br.com.jcv.backend.paginacao.CondicaoDTO;

public class DTOCondicaoImpl implements AbstractDataTransferObject<CondicaoDTO, CondicaoVO> {

	public CondicaoVO getDataTransferObject(CondicaoDTO dto) {
		CondicaoVO vo = null;
		if (dto != null){
			vo = new CondicaoVO();
			vo.campo = dto.campo;
			vo.condicao = dto.condicao;
			vo.conteudo = dto.conteudo;
			vo.operadorLogico = dto.operadorLogico;
		}
		return vo;
	}

	public CondicaoDTO getDataTransferObject(CondicaoVO vo) {
		CondicaoDTO dto = null;
		if (vo != null){
			dto = new CondicaoDTO();
			dto.campo = vo.campo;
			dto.condicao = vo.condicao;
			dto.conteudo = vo.conteudo;
			dto.operadorLogico = vo.operadorLogico;
		}
		return dto;
	}

}
