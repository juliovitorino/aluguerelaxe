package br.com.jcv.aluguerelaxe.server.datatransferobject;

import java.util.ArrayList;

import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.ReservaVO;
import br.com.jcv.aluguerelaxe.client.cliente.ClienteVO;
import br.com.jcv.aluguerelaxe.client.cliente.TelefoneVO;
import br.com.jcv.aluguerelaxe.client.imovel.ImovelVO;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;
import br.com.jcv.aluguerelaxe.client.vo.DadosBancariosVO;
import br.com.jcv.aluguerelaxe.client.vo.EnderecoVO;
import br.com.jcv.backend.cliente.TelefoneDTO;
import br.com.jcv.backend.datatransfer.AbstractDataTransferObject;
import br.com.jcv.backend.imovel.ImovelDTO;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;
import br.com.jcv.backend.reserva.ReservaDTO;
import br.com.jcv.backend.utility.StringUtil;

public class DTOReservaLightImpl implements
		AbstractDataTransferObject<ReservaDTO, ReservaVO> {

	public ReservaVO getDataTransferObject(ReservaDTO dto) {
		ReservaVO vo = new ReservaVO();
		vo.msgcode = dto.msgcode;
		vo.msgcodeString = dto.msgcodeString;
		
		vo.id = dto.id;
		vo.dataCheckin = dto.dataCheckin;
		vo.dataCheckout = dto.dataCheckout;
		vo.dataPrevistaDeposito = dto.dataPrevistaDeposito;
		vo.valorPrevistoDeposito = dto.valorPrevistoDeposito;
		vo.dataCadastro = dto.dataCadastro;
		vo.token = dto.token;
		vo.chaveLiberacaoDeposito = dto.chaveLiberacaoDeposito;
		vo.dataEmailLiberacao = dto.dataEmailLiberacao;
		vo.dataValidacaoPreReserva = dto.dataValidacaoPreReserva;
		vo.dataRealDeposito = dto.dataRealDeposito;
		vo.valorRealDeposito = dto.valorRealDeposito;
		vo.urlContratoLocador = dto.urlContratoLocador;
		vo.urlContratoLocatario = dto.urlContratoLocatario;
		vo.chaveLiberacaoCheck = dto.chaveLiberacaoCheck;
		vo.dataChaveLiberacaoCheck = dto.dataChaveLiberacaoCheck;
		vo.dataTranferenciaDeposito = dto.dataTranferenciaDeposito;
		vo.valorTransferenciaDeposito = dto.valorTransferenciaDeposito;
		vo.chaveTracker = dto.chaveTracker;
		vo.valorAluguelNegociado = dto.valorAluguelNegociado;
		vo.formaPagamento = dto.formaPagamento;
		vo.dataReprovacao = dto.dataReprovacao;
		vo.valorCaucao = dto.valorCaucao;
		vo.percentualComissao = dto.percentualComissao;
		vo.dataAvaliacao = dto.dataAvaliacao;

		// Monta VO do locatario
		vo.locatario = (new DTOLocatarioImpl()).getDataTransferObject(dto.locatario);
		
		return vo;
	}

	public ReservaDTO getDataTransferObject(ReservaVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

}
