package br.com.jcv.aluguerelaxe.server.datatransferobject;

import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.LocatarioVO;
import br.com.jcv.aluguerelaxe.client.cliente.TelefoneVO;
import br.com.jcv.backend.cliente.TelefoneDTO;
import br.com.jcv.backend.datatransfer.AbstractDataTransferObject;
import br.com.jcv.backend.locatario.LocatarioDTO;

public class DTOLocatarioImpl implements
		AbstractDataTransferObject<LocatarioDTO, LocatarioVO> {

	public LocatarioVO getDataTransferObject(LocatarioDTO dto) {
		LocatarioVO vo = null;
		if (dto != null){
			vo = new LocatarioVO();
			vo.id = dto.id;
			vo.nome = dto.nome;
			vo.cpf = dto.cpf;
			vo.dataNascimento = dto.dataNascimento;
			vo.dataCadastro = dto.dataCadastro;
			vo.email = dto.email;
			vo.endereco = (new DTOEnderecoImpl()).getDataTransferObject(dto.endereco);
			vo.telefone = new TelefoneVO();
			vo.telefone.ddd = dto.telefone.ddd;
			vo.telefone.telefone = dto.telefone.telefone;
		}
		return vo;
	}

	public LocatarioDTO getDataTransferObject(LocatarioVO vo) {
		LocatarioDTO dto = new LocatarioDTO();
		dto.id = vo.id;
		dto.nome = vo.nome;
		dto.cpf = vo.cpf;
		dto.dataNascimento = vo.dataNascimento;
		dto.email = vo.email;
		dto.endereco = (new DTOEnderecoImpl()).getDataTransferObject(vo.endereco);
		dto.telefone = new TelefoneDTO();
		dto.telefone.ddd = vo.telefone.ddd;
		dto.telefone.telefone = vo.telefone.telefone;
		return dto;
	}

}
