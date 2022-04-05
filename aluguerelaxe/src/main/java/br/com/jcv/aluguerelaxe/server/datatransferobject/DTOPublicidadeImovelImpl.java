package br.com.jcv.aluguerelaxe.server.datatransferobject;

import br.com.jcv.aluguerelaxe.client.imovel.ficha.PublicidadeImovelVO;
import br.com.jcv.backend.datatransfer.AbstractDataTransferObject;
import br.com.jcv.backend.imovel.publicidade.PublicidadeImovelDTO;

public class DTOPublicidadeImovelImpl implements AbstractDataTransferObject<PublicidadeImovelDTO, PublicidadeImovelVO> {
	
	private String path;
	
	public DTOPublicidadeImovelImpl(String path){
		this.path = path;
	}

	public PublicidadeImovelVO getDataTransferObject(PublicidadeImovelDTO dto) {
		PublicidadeImovelVO vo = null;
		if (dto != null){
			vo = new PublicidadeImovelVO();
			vo.publicidade = (new DTOPublicidadeImpl()).getDataTransferObject(dto.publicidade);
			vo.fichaImovel = (new DTOImovelFichaCompletaImpl(path)).getDataTransferObject(dto.fichaImovel);
			vo.fatura = (new DTOImovelPlanoFaturaImpl()).getDataTransferObject(dto.fatura);
			vo.plano = (new DTOPlanoImpl()).getDataTransferObject(dto.plano);
		}
		return vo;
	}

	public PublicidadeImovelDTO getDataTransferObject(PublicidadeImovelVO vo) {
		PublicidadeImovelDTO dto = null;
		if (vo != null){
		}
		return dto;
	}

}
