package br.com.jcv.aluguerelaxe.client.administracao.console.reserva;

import java.util.Date;
import java.util.List;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.administracao.console.tracker.TrackerVO;
import br.com.jcv.aluguerelaxe.client.imovel.reserva.AvaliacaoReservaVO;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("reservarpc")
public interface ReservaRPC extends RemoteService {

	ReservaVO getReservaChaveTracker(String chaveTracker) throws AlugueRelaxeFrontException;
	ReservaVO confirmarReserva(String chave, Date dataRealDeposito, double valorRealDeposito) throws AlugueRelaxeFrontException;
	LocatarioVO pesquisarLocatarioReserva(String email) throws AlugueRelaxeFrontException;
	ReservaVO criarPreReserva(ReservaVO vo) throws AlugueRelaxeFrontException;
	ReservaVO aprovarPreReservaTracker(TrackerVO chave) throws AlugueRelaxeFrontException;
	ReservaVO reprovarPreReservaTracker(TrackerVO chave) throws AlugueRelaxeFrontException;
	ReservaVO solicitarLiberacaoDeposito(long idClienteSolicitante, String voucher, String tracking) throws AlugueRelaxeFrontException;
	ReservaVO transferirDeposito(TrackerVO chave) throws AlugueRelaxeFrontException;
	List<ReservaVO> listarTarefasPendentes(String fase) throws AlugueRelaxeFrontException;
	ReservaVO getReserva(String hashContatoAnunciante) throws AlugueRelaxeFrontException;
	VOPadrao enviarComentario(AvaliacaoReservaVO vo) throws AlugueRelaxeFrontException;
	
	public static class Util {

		public static ReservaRPCAsync getInstance() {

			return GWT.create(ReservaRPC.class);
		}
	}
	

}
