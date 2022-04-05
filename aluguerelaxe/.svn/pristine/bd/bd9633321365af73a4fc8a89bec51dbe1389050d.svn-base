package br.com.jcv.aluguerelaxe.client.administracao.console.reserva;

import java.util.Date;
import java.util.List;

import br.com.jcv.aluguerelaxe.client.administracao.console.tracker.TrackerVO;
import br.com.jcv.aluguerelaxe.client.imovel.reserva.AvaliacaoReservaVO;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ReservaRPCAsync {

	void getReservaChaveTracker(String chaveTracker, AsyncCallback<ReservaVO> callback);
	void confirmarReserva(String chave, Date dataRealDeposito, double valorRealDeposito, AsyncCallback<ReservaVO> callback);
	void  pesquisarLocatarioReserva(String email, AsyncCallback<LocatarioVO> callback);
	void criarPreReserva(ReservaVO vo, AsyncCallback<ReservaVO> callback);
	void aprovarPreReservaTracker(TrackerVO chave, AsyncCallback<ReservaVO> callback);
	void reprovarPreReservaTracker(TrackerVO chave, AsyncCallback<ReservaVO> callback);
	void solicitarLiberacaoDeposito(long idClienteSolicitante, String voucher,String tracking, AsyncCallback<ReservaVO> callback);
	void transferirDeposito(TrackerVO chave, AsyncCallback<ReservaVO> callback);
	void listarTarefasPendentes(String fase, AsyncCallback<List<ReservaVO>> callback);
	void getReserva(String hashContatoAnunciante, AsyncCallback<ReservaVO> callback);
	void enviarComentario(AvaliacaoReservaVO vo,AsyncCallback<VOPadrao> callback);
}
