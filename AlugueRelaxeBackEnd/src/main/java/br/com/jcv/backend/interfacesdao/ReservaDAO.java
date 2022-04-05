package br.com.jcv.backend.interfacesdao;

import java.util.Date;
import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.reserva.AvaliacaoReservaDTO;

public interface ReservaDAO<DTO> extends DAO<DTO> {
	void updateComentario(AvaliacaoReservaDTO dto) throws AlugueRelaxeException;
	DTO loadReserva(String hashContatoAnunciante) throws AlugueRelaxeException;
	List<DTO> listTarefasPendentes(int fase) throws AlugueRelaxeException;
	DTO loadToken(String token) throws AlugueRelaxeException ;
	DTO loadChaveTracker(String ct) throws AlugueRelaxeException ;
	void updateAprovacaoPreReserva(String token) throws AlugueRelaxeException;
	void updateReprovacaoPreReserva(String token) throws AlugueRelaxeException;
	void updateAprovacaoPreReservaTracker(String chave) throws AlugueRelaxeException;
	void updateReprovacaoPreReservaTracker(String chave) throws AlugueRelaxeException;
	void  updateLiberacaoDeposito(String tracking, String voucher) throws AlugueRelaxeException;
	void  updateTransferenciaDeposito(long idReserva, double vlTransferencia) throws AlugueRelaxeException;
	void updatePagamentoReserva(String token, 
								Date dataRealDeposito, 
								double valorRealDeposito,
								String urlContratoLocador,
								String urlContratoLocatario) throws AlugueRelaxeException; 
}

