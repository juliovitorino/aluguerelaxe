package br.com.jcv.aluguerelaxe.client.administracao.console.negociacao;

import br.com.jcv.aluguerelaxe.client.DateParser;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.ReservaVO;
import br.com.jcv.aluguerelaxe.client.administracao.console.tracker.TrackerCodigoFormCompositeListener;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.tracker.Fase;
import br.com.jcv.aluguerelaxe.client.componente.tracker.Tracker;

import com.google.gwt.user.client.ui.Widget;

/**
 * <h2>Componente para apresentacao das fases da negociacao em forma de fases</<h2>
 * @author Julio
 *
 */
public class TrackerNegociacaoFormComposite extends FormComposite<ReservaVO> implements TrackerCodigoFormCompositeListener {
	
	private static final String GWT_STYLE = "gwt-TrackerNegociacaoFormComposite";
	private Tracker tracker;
	private Fase fsPreReserva;
	private Fase fsAprovacao;
	private Fase fsConfirmacaoDeposito;
	private Fase fsPedidoLiberacao;
	private Fase fsTransferenciaPgto;
	private Fase fsAvaliacao;
	
	public TrackerNegociacaoFormComposite(){
		super();
		this.setStyleName(GWT_STYLE);
	}

	@Override
	public ReservaVO getVO() {
		return null;
	}

	@Override
	public void update(ReservaVO vo) {
		clear();
		
		if (vo.dataReprovacao != null){
			fsPreReserva.setStatus(Fase.STATUS_FINALIZADO);
			fsPreReserva.setRodape(DateParser.getDataHoraCheia(vo.dataCadastro));
			
			fsAprovacao.setStatus(Fase.STATUS_ERRO);
			fsAprovacao.setRodape(DateParser.getDataHoraCheia(vo.dataReprovacao));
		} else {
			fsPreReserva.setStatus(Fase.STATUS_FINALIZADO);
			fsPreReserva.setRodape(DateParser.getDataHoraCheia(vo.dataCadastro));

			// Atualiza sinalizador da Aprovacao
			if((vo.dataCadastro != null) && (vo.dataValidacaoPreReserva == null)){
				fsAprovacao.setStatus(Fase.STATUS_EM_ANDAMENTO);
			} else if ((vo.dataCadastro != null) && (vo.dataValidacaoPreReserva != null)){
				fsAprovacao.setStatus(Fase.STATUS_FINALIZADO);
				fsAprovacao.setRodape(DateParser.getDataHoraCheia(vo.dataValidacaoPreReserva));

			} else {
				fsAprovacao.setStatus(Fase.STATUS_NAO_INICIADO);
			}	
			
			// Atualiza sinalizador da Confirmacao de deposito
			if((vo.dataValidacaoPreReserva != null) && (vo.dataRealDeposito == null)){
				fsConfirmacaoDeposito.setStatus(Fase.STATUS_EM_ANDAMENTO);
			} else if ((vo.dataValidacaoPreReserva != null) && (vo.dataRealDeposito != null)){
				fsConfirmacaoDeposito.setStatus(Fase.STATUS_FINALIZADO);
				fsConfirmacaoDeposito.setRodape(DateParser.getDataHoraCheia(vo.dataRealDeposito));

			} else {
				fsConfirmacaoDeposito.setStatus(Fase.STATUS_NAO_INICIADO);
			}	
			
			// Atualiza sinalizador do pedido de liberacao 
			if((vo.dataRealDeposito != null) && (vo.dataChaveLiberacaoCheck == null)){
				fsPedidoLiberacao.setStatus(Fase.STATUS_EM_ANDAMENTO);
			} else if((vo.dataRealDeposito != null) && (vo.dataChaveLiberacaoCheck != null)){		
				fsPedidoLiberacao.setStatus(Fase.STATUS_FINALIZADO);
				fsPedidoLiberacao.setRodape(DateParser.getDataHoraCheia(vo.dataChaveLiberacaoCheck));
			} else {
				fsPedidoLiberacao.setStatus(Fase.STATUS_NAO_INICIADO);
			}		
			
			// Atualiza sinalizador do transferencia de pagamento 
			if((vo.dataChaveLiberacaoCheck != null) && (vo.dataTranferenciaDeposito == null)){
				fsTransferenciaPgto.setStatus(Fase.STATUS_EM_ANDAMENTO);
			} else if((vo.dataChaveLiberacaoCheck != null) && (vo.dataTranferenciaDeposito != null)) {
				fsTransferenciaPgto.setStatus(Fase.STATUS_FINALIZADO);
				fsTransferenciaPgto.setRodape(DateParser.getDataHoraCheia(vo.dataTranferenciaDeposito));
			} else {
				fsTransferenciaPgto.setStatus(Fase.STATUS_NAO_INICIADO);
			}
			
			// Atualiza sinalizador de avaliacao 
			if((vo.dataTranferenciaDeposito != null) && (vo.dataAvaliacao == null)){
				fsAvaliacao.setStatus(Fase.STATUS_EM_ANDAMENTO);
			} else if((vo.dataTranferenciaDeposito != null) && (vo.dataAvaliacao != null)) {
				fsAvaliacao.setStatus(Fase.STATUS_FINALIZADO);
				fsAvaliacao.setRodape(DateParser.getDataHoraCheia(vo.dataAvaliacao));
			} else {
				fsAvaliacao.setStatus(Fase.STATUS_NAO_INICIADO);
			}
		}
	}

	@Override
	public Widget render() {
		return tracker;
	}

	@Override
	public void init() {
		this.tracker = new Tracker();
		fsPreReserva = new Fase("Pr\u00e9-reserva");
		fsAprovacao = new Fase("Aprova\u00e7\u00e3o");
		fsConfirmacaoDeposito = new Fase("Confirma\u00e7\u00e3o dep\u00f3sito");
		fsPedidoLiberacao = new Fase("Pedido de Libera\u00e7\u00e3o");
		fsTransferenciaPgto = new Fase("Transfer\u00eancia Pagamento");
		fsAvaliacao = new Fase("Avalia\u00e7\u00e3o");
		tracker.add(fsPreReserva);
		tracker.add(fsAprovacao);
		tracker.add(fsConfirmacaoDeposito);
		tracker.add(fsPedidoLiberacao);
		tracker.add(fsTransferenciaPgto);
		tracker.add(fsAvaliacao);
	}

	@Override
	public void clear() {
		fsPreReserva.clear();
		fsAprovacao.clear();
		fsConfirmacaoDeposito.clear();
		fsPedidoLiberacao.clear();
		fsTransferenciaPgto.clear();
		fsAvaliacao.clear();
	}

	public void onPesquisarTrackerClick(ReservaVO vo) {
		this.update(vo);
		
	}

	@Override
	public void notifier(ReservaVO vo) {
		// TODO Auto-generated method stub
		
	}

}
