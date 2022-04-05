package br.com.jcv.aluguerelaxe.server.datatransferobject;

import br.com.jcv.aluguerelaxe.client.imovel.imagem.ImovelImagemVideoVO;
import br.com.jcv.backend.datatransfer.AbstractDataTransferObject;
import br.com.jcv.backend.imovel.ImovelDTO;
import br.com.jcv.backend.imovel.imagem.ImovelImagemVideoDTO;

public class DTOImovelImagemVideoImpl implements
		AbstractDataTransferObject<ImovelImagemVideoDTO, ImovelImagemVideoVO> {

	public ImovelImagemVideoVO getDataTransferObject(ImovelImagemVideoDTO dto) {
		ImovelImagemVideoVO vo = null;
		if (dto != null){
			vo = new ImovelImagemVideoVO();
			vo.id = dto.id;
			vo.nome = dto.nome;
			vo.tipo = dto.tipo;
			vo.codigoImovel = dto.imovel.id;
		}
		return vo;
	}

	public ImovelImagemVideoDTO getDataTransferObject(ImovelImagemVideoVO vo) {
		ImovelImagemVideoDTO dto = null;
		if (vo != null){
			dto = new ImovelImagemVideoDTO();
			dto.id = vo.id;
			dto.nome = vo.nome;
			dto.tipo = vo.tipo;
			dto.imovel = new ImovelDTO();
			dto.imovel.id = vo.codigoImovel;
		}
		return dto;
	}

}
