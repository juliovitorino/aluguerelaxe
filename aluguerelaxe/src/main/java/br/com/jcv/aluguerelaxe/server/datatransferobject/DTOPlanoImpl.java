package br.com.jcv.aluguerelaxe.server.datatransferobject;

import br.com.jcv.aluguerelaxe.client.plano.PlanoVO;
import br.com.jcv.backend.datatransfer.AbstractDataTransferObject;
import br.com.jcv.backend.plano.PlanoDTO;

public class DTOPlanoImpl implements AbstractDataTransferObject<PlanoDTO, PlanoVO> {

	public PlanoVO getDataTransferObject(PlanoDTO dto) {
		PlanoVO vo = null;
		if (dto != null){
			vo = new PlanoVO();
			vo.id = dto.id;
			vo.nome = dto.nome;
			vo.descricao = dto.descricao;
			vo.valor = dto.valor;
			vo.indicadorStatus = dto.indicadorStatus;
			vo.dataCadastro = dto.dataCadastro;
			vo.dataAtivacao = dto.dataAtivacao;
			vo.dataCancelamento = dto.dataCancelamento;
			vo.indicadorPeriodicidade = dto.indicadorPeriodicidade;
			vo.indicadorTipoCompra = dto.indicadorTipoCompra;
			vo.numeroDiasCalculo = dto.numeroDiasCalculo;
			vo.htmlBtnPagseguro = dto.htmlBtnPagseguro;
			vo.htmlBtnPayPal = dto.htmlBtnPayPal;
			vo.limiteFotos = dto.limiteFotos;
		}
		return vo;
	}

	public PlanoDTO getDataTransferObject(PlanoVO vo) {
		PlanoDTO dto = null;
		if (vo != null){
			dto.id = vo.id;
			dto.nome = vo.nome;
			dto.descricao = vo.descricao;
			dto.valor = vo.valor;
			dto.indicadorStatus = vo.indicadorStatus;
			dto.indicadorPeriodicidade = vo.indicadorPeriodicidade;
			dto.indicadorTipoCompra = vo.indicadorTipoCompra;
			dto.numeroDiasCalculo = vo.numeroDiasCalculo;
			dto.htmlBtnPagseguro = vo.htmlBtnPagseguro;
			dto.htmlBtnPayPal = vo.htmlBtnPayPal;
			dto.limiteFotos = vo.limiteFotos;
		}
		return dto;
	}

}
