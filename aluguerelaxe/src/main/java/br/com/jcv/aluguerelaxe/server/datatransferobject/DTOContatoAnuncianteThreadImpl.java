package br.com.jcv.aluguerelaxe.server.datatransferobject;

import java.text.ParseException;

import br.com.jcv.aluguerelaxe.client.imovel.thread.ContatoAnuncianteThreadVO;
import br.com.jcv.backend.datatransfer.AbstractDataTransferObject;
import br.com.jcv.backend.datemanager.DateManagerBase;
import br.com.jcv.backend.imovel.thread.ContatoAnuncianteThreadDTO;

public class DTOContatoAnuncianteThreadImpl
		implements
		AbstractDataTransferObject<ContatoAnuncianteThreadDTO, ContatoAnuncianteThreadVO> {

	public ContatoAnuncianteThreadVO getDataTransferObject(
			ContatoAnuncianteThreadDTO dto) {
		ContatoAnuncianteThreadVO vo = null;
		if (dto != null){
			vo = new ContatoAnuncianteThreadVO();
			vo.id = dto.id;
			vo.idParent = dto.idParent;
			vo.origem = dto.origem;
			vo.status = dto.status;
			vo.modo = dto.modo;
			vo.thread = dto.thread;
			vo.threadEditada = dto.threadEditada;
			if(dto.dataCadastro != null){
				vo.dataCadastro = dto.dataCadastro;
				DateManagerBase dc = DateManagerBase.getDateManagerInstance(dto.dataCadastro.getTime());
				try {
					vo.dataCadastroStr = dc.getDateTimeFull();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if (dto.threadfilho != null){
				vo.threadfilho = (new DTOContatoAnuncianteThreadImpl()).getDataTransferObject(dto.threadfilho);
			}
		}
		return vo;
	}

	public ContatoAnuncianteThreadDTO getDataTransferObject(
			ContatoAnuncianteThreadVO vo) {
		ContatoAnuncianteThreadDTO dto = null;
		if (vo != null){
			dto = new ContatoAnuncianteThreadDTO();
			dto.id = vo.id;
			dto.idParent = vo.idParent;
			dto.origem = vo.origem;
			dto.status = vo.status;
			dto.modo = vo.modo;
			dto.thread = vo.thread;
			dto.threadEditada = vo.threadEditada;
		}
		return dto;
	}

}
