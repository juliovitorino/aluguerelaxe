package br.com.jcv.aluguerelaxe.server.datatransferobject;

import br.com.jcv.aluguerelaxe.client.imovel.ImovelPlanoFaturaVO;
import br.com.jcv.backend.datatransfer.AbstractDataTransferObject;
import br.com.jcv.backend.imovel.faturamento.ImovelPlanoFaturaDTO;

public class DTOImovelPlanoFaturaImpl implements
		AbstractDataTransferObject<ImovelPlanoFaturaDTO, ImovelPlanoFaturaVO> {

	public ImovelPlanoFaturaVO getDataTransferObject(ImovelPlanoFaturaDTO dto) {
		ImovelPlanoFaturaVO vo = null;
		if (dto != null){
			vo = new ImovelPlanoFaturaVO();
			vo.id = dto.id;
			vo.idImovelPlano = dto.idImovelPlano;
			vo.indicadorStatus = dto.indicadorStatus;
			vo.valorFatura = dto.valorFatura;
			vo.valorDesconto = dto.valorDesconto;
			vo.dataVencimento = dto.dataVencimento;
			vo.dataPagamento = dto.dataPagamento;
			vo.dataCadastro = dto.dataCadastro;
			vo.msgcode = dto.msgcode;
			vo.msgcodeString = dto.msgcodeString;
			vo.plano = (new DTOPlanoImpl()).getDataTransferObject(dto.plano);
		}
		return vo;
	}

	public ImovelPlanoFaturaDTO getDataTransferObject(ImovelPlanoFaturaVO vo) {
		ImovelPlanoFaturaDTO dto = null;
		if (vo != null){
			dto = new ImovelPlanoFaturaDTO();
			dto.id = vo.id;
			dto.idImovelPlano = vo.idImovelPlano;
			dto.indicadorStatus = vo.indicadorStatus;
			dto.valorFatura = vo.valorFatura;
			dto.valorDesconto = vo.valorDesconto;
			dto.dataVencimento = vo.dataVencimento;
			dto.dataPagamento = vo.dataPagamento;
			dto.dataCadastro = vo.dataCadastro;
			dto.msgcode = vo.msgcode;
			dto.msgcodeString = vo.msgcodeString;
		}
		return dto;
	}

}
