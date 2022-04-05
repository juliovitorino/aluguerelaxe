package br.com.jcv.aluguerelaxe.server.datatransferobject;

import br.com.jcv.aluguerelaxe.client.imovel.reserva.AvaliacaoReservaVO;
import br.com.jcv.backend.datatransfer.AbstractDataTransferObject;
import br.com.jcv.backend.reserva.AvaliacaoReservaDTO;

public class DTOAvaliacaoReservaImovelImpl implements
		AbstractDataTransferObject<AvaliacaoReservaDTO, AvaliacaoReservaVO> {

	public AvaliacaoReservaVO getDataTransferObject(AvaliacaoReservaDTO dto) {
		AvaliacaoReservaVO arvo = null;
		if (dto != null){
			arvo = new AvaliacaoReservaVO();
			arvo.avaliacao = dto.avaliacao;
			arvo.contatoCliente = (new DTOContatoAnuncianteImpl("/")).getDataTransferObject(dto.contatoCliente);
			arvo.reserva = (new DTOReservaImpl()).getDataTransferObject(dto.reserva);
		}
		return arvo;
	}

	public AvaliacaoReservaDTO getDataTransferObject(AvaliacaoReservaVO vo) {
		AvaliacaoReservaDTO dto = null;
		if (vo != null){
			dto = new AvaliacaoReservaDTO();
			dto.avaliacao = vo.avaliacao;
			dto.contatoCliente = (new DTOContatoAnuncianteImpl("/")).getDataTransferObject(vo.contatoCliente);
			dto.reserva = (new DTOReservaImpl()).getDataTransferObject(vo.reserva);
			
		}
		return dto;
	}

}
