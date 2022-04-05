package br.com.jcv.backend.interfaces.services;

import java.util.Date;
import java.util.List;

import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.reserva.AvaliacaoReservaDTO;

public interface ReservaService<DTO> extends ApplicationService<DTO> {
	DTO criarPreReserva(DTO dto) throws AlugueRelaxeException;
	DTO aprovarPreReserva(String token) throws AlugueRelaxeException;
	DTO reprovarPreReserva(String token) throws AlugueRelaxeException;
	DTO confirmarReserva(String chave, Date dataRealDeposito, double valorRealDeposito) throws AlugueRelaxeException;
	DTO solicitarLiberacaoDeposito(long idClienteSolicitante, String voucher, String tracking) throws AlugueRelaxeException;
	DTO procurarChaveTracker(String chave) throws AlugueRelaxeException;
	DTO aprovarPreReservaTracker(String chave) throws AlugueRelaxeException;
	DTO reprovarPreReservaTracker(String chave) throws AlugueRelaxeException;
	DTO transferirDeposito(String chave) throws AlugueRelaxeException;
	List<DTO> listarTarefasPendentes(String fase) throws AlugueRelaxeException;
	DTO pesquisarReserva(String hashContatoAnunciante) throws AlugueRelaxeException;
	DTOPadrao criarComentario(AvaliacaoReservaDTO dto) throws AlugueRelaxeException;
}

