package br.com.jcv.aluguerelaxe.server.datatransferobject;

import br.com.jcv.aluguerelaxe.client.cliente.ClienteVO;
import br.com.jcv.aluguerelaxe.client.modopublicidade.ModoPublicidadeVO;
import br.com.jcv.backend.cliente.ClienteDTO;
import br.com.jcv.backend.datatransfer.AbstractDataTransferObject;
import br.com.jcv.backend.modopublicidade.ModoPublicidadeDTO;

public class DTOClienteModoPublicidadeImpl implements AbstractDataTransferObject<ClienteDTO, ClienteVO> {

	public ClienteVO getDataTransferObject(ClienteDTO dto) {
		ClienteVO vo = null;
		if (dto != null){
			vo = new ClienteVO();
			vo.id = dto.id;
			vo.modoPublicidade = new ModoPublicidadeVO();
			vo.modoPublicidade.id = dto.modoPublicidade.id;
		}
		return vo;
	}

	public ClienteDTO getDataTransferObject(ClienteVO vo) {
		ClienteDTO dto = null;
		if (vo != null){
			dto = new ClienteDTO();
			dto.id = vo.id;
			dto.modoPublicidade = new ModoPublicidadeDTO();
			dto.modoPublicidade.id = vo.modoPublicidade.id;
		}
		return dto;
	}


}
