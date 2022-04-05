package br.com.jcv.aluguerelaxe.server.datatransferobject;

import br.com.jcv.aluguerelaxe.client.vo.EnderecoVO;
import br.com.jcv.backend.datatransfer.AbstractDataTransferObject;
import br.com.jcv.backend.dto.EnderecoDTO;
import br.com.jcv.backend.utility.StringUtil;

public class DTOEnderecoImpl implements
		AbstractDataTransferObject<EnderecoDTO, EnderecoVO> {

	public EnderecoVO getDataTransferObject(EnderecoDTO dto) {
		EnderecoVO vo = new EnderecoVO();
		vo.logradouro = StringUtil.splitEndereco(dto.nome,StringUtil.TIPO_RETORNO_LOGRADOURO) ;
		vo.nome = StringUtil.splitEndereco(dto.nome,StringUtil.TIPO_RETORNO_ENDERECO);
		vo.numero = dto.numero;
		vo.complemento = dto.complemento;
		vo.bairro =  dto.bairro;
		vo.cep = dto.cep;
		vo.cidade = dto.cidade;
		vo.uf = dto.uf;
		vo.nomeuf = dto.nomeuf;
		vo.codigoUfCidadeItem = dto.codigoUfCidadeItem;
		return vo;
	}

	public EnderecoDTO getDataTransferObject(EnderecoVO vo) {
		EnderecoDTO dto = new EnderecoDTO();
		if (vo.logradouro == null){
			dto.nome =  vo.nome;
		} else {
			dto.nome =  vo.logradouro + " " + vo.nome;
		}
		dto.numero = vo.numero;
		dto.complemento = vo.complemento;
		dto.bairro =  vo.bairro;
		dto.cep = vo.cep;
		dto.cidade = vo.cidade;
		dto.uf = vo.uf;
		dto.nomeuf = vo.nomeuf;
		dto.codigoUfCidadeItem = vo.codigoUfCidadeItem;
		return dto;
	}

}
