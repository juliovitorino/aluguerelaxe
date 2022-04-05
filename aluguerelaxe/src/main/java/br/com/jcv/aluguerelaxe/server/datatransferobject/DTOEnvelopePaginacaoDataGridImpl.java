package br.com.jcv.aluguerelaxe.server.datatransferobject;

import java.util.ArrayList;

import br.com.jcv.aluguerelaxe.client.componente.datagrid.EnvelopePaginacaoDataGridVO;
import br.com.jcv.aluguerelaxe.client.componente.datagrid.RegDataGridVO;
import br.com.jcv.backend.datatransfer.AbstractDataTransferObject;
import br.com.jcv.backend.paginacao.EnvelopePaginacaoDataGridDTO;
import br.com.jcv.backend.paginacao.RegDataGridDTO;

public class DTOEnvelopePaginacaoDataGridImpl implements AbstractDataTransferObject<EnvelopePaginacaoDataGridDTO,EnvelopePaginacaoDataGridVO> {

	public EnvelopePaginacaoDataGridVO getDataTransferObject(EnvelopePaginacaoDataGridDTO dto) {
		EnvelopePaginacaoDataGridVO vo = null;
		if (dto != null){
			vo = new EnvelopePaginacaoDataGridVO();
			vo.totalRegistros = dto.totalRegistros;
			vo.lst = new ArrayList<RegDataGridVO>();
			for (RegDataGridDTO rdgdto : dto.lst) {
				RegDataGridVO rdgvo = new RegDataGridVO();
				rdgvo.campo = new String[rdgdto.campo.length];
				for (int i = 0; i < rdgdto.campo.length; i++){
					rdgvo.campo[i] = rdgdto.campo[i];
				}
				vo.lst.add(rdgvo);
			}
		}
		return vo;
	}

	public EnvelopePaginacaoDataGridDTO getDataTransferObject(EnvelopePaginacaoDataGridVO vo) {
		EnvelopePaginacaoDataGridDTO dto = null;
		if (vo != null){
		}
		return dto;
	}
}
