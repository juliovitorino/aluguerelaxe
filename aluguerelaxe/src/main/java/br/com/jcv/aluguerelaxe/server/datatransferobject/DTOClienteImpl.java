package br.com.jcv.aluguerelaxe.server.datatransferobject;

import java.util.ArrayList;

import br.com.jcv.aluguerelaxe.client.cliente.ClienteVO;
import br.com.jcv.aluguerelaxe.client.cliente.TelefoneVO;
import br.com.jcv.aluguerelaxe.client.vo.EnderecoVO;
import br.com.jcv.backend.cliente.ClienteDTO;
import br.com.jcv.backend.cliente.TelefoneDTO;
import br.com.jcv.backend.datatransfer.AbstractDataTransferObject;
import br.com.jcv.backend.datemanager.DateManagerBase;

public class DTOClienteImpl implements
		AbstractDataTransferObject<ClienteDTO, ClienteVO> {

	public ClienteVO getDataTransferObject(ClienteDTO dto) {
		ClienteVO vo = null;
		if (dto != null){
			vo = new ClienteVO();
			vo.id = dto.id;
			vo.nome = dto.nome;
			vo.cpf = dto.cpf;
			vo.cgc = dto.cgc;

			vo.fotoPerfil = dto.fotoPerfil;
			vo.fotoChat = dto.fotoChat;
			vo.primeiroNome = dto.primeiroNome;
			vo.pathPerfil = dto.pathPerfil;
			vo.fotoPerfil = dto.fotoPerfil;
			vo.fotoChat = dto.fotoChat;
			vo.indicadorVerificado = dto.indicadorVerificado;
			vo.taxaResposta = dto.taxaResposta;
			vo.totalPergunta = dto.totalPergunta;
			vo.totalResposta = dto.totalResposta;
			vo.membroDesde = DateManagerBase.getDateManagerInstance(dto.dataCadastro).getMonth() +
										"/" +
										DateManagerBase.getDateManagerInstance(dto.dataCadastro).getYear();
			vo.dataNascimento = dto.dataNascimento;
			vo.email = dto.email;
			vo.dataCadastro = dto.dataCadastro;
			vo.dataAtualizacao = dto.dataAtualizacao;
			vo.indicadorStatus = dto.indicadorStatus;
			vo.urlwww = dto.urlwww;
			vo.indicadorAutorizaNotificacao = dto.indicadorAutorizaNotificacao;
			vo.msn = dto.msn;
			vo.skype = dto.skype;
			vo.googleTalk = dto.googleTalk;
			vo.indicadorTipoAnunciante = dto.indicadorTipoAnunciante;
			vo.endereco = new EnderecoVO();
			vo.endereco.nome = dto.endereco.nome;
			vo.endereco.numero = dto.endereco.numero;
			vo.endereco.complemento = dto.endereco.complemento;
			vo.endereco.bairro = dto.endereco.bairro;
			vo.endereco.cep = dto.endereco.cep;
			if (dto.telefones != null){
				if (dto.telefones.size() > 0) {
					vo.telefones = new ArrayList<TelefoneVO>();
					for (TelefoneDTO telefonedto : dto.telefones) {
						TelefoneVO telefonevo = new TelefoneVO();
						telefonevo.nomeContato = telefonedto.nomeContato;
						telefonevo.ddd = telefonedto.ddd;
						telefonevo.telefone = telefonedto.telefone;
						telefonevo.indPermiteExibir = telefonedto.indPermiteExibir;
						telefonevo.indTipoTelefone = telefonedto.indTipoTelefone;
						vo.telefones.add(telefonevo);
					}
				}
			}
			
		}
		return vo;
	}

	public ClienteDTO getDataTransferObject(ClienteVO vo) {
		ClienteDTO dto = null;
		if (vo != null){
			
		}
		return dto;
	}

}

