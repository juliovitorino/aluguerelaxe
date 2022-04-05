package br.com.jcv.backend.reserva;

import java.util.HashMap;
import java.util.List;

import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.datemanager.DateManagerBase;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.mensagem.MensagemCache;
import br.com.jcv.backend.validador.Validador;

public class CriarPreReservaValidador extends
		Validador<ReservaDTO> {

	@Override
	public List<String> execute(ReservaDTO dto) {
		try {
			if (dto == null) {
				HashMap<String, String> parametros = new HashMap<String, String>();
				parametros.put(Constantes.P1, "Campos da Pré-reserva incorretos");
				super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_NAO_PODE_ESTAR_NULO, parametros));
			} else {
				
							
				//--------------------------------------------------------------------------------------
				// campos nulos
				//--------------------------------------------------------------------------------------
//				if (dto.chaveTracker == null) {
//					HashMap<String, String> parametros = new HashMap<String, String>();
//					parametros.put(Constantes.P1, "[Código Tracking]");
//					super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_NAO_PODE_ESTAR_NULO, parametros));
//				} 
				
				//--------------------------------------------------------------------------------------
				// Tamanhos de campos
				//--------------------------------------------------------------------------------------
				
				//----------------------------------------------------------------
				// Regras de validacao
				//----------------------------------------------------------------
				if (dto.formaPagamento.equals(Constantes.FORMA_PGTO_PARCIAL)) {
					if(dto.valorPrevistoDeposito != (dto.valorAluguelNegociado + dto.valorTaxaServico) * 0.5) {
						HashMap<String, String> parametros = new HashMap<String, String>();
						parametros.put(Constantes.P1, String.valueOf((dto.valorAluguelNegociado + dto.valorTaxaServico ) * 0.5));
						parametros.put(Constantes.P2, Constantes.FORMA_PGTO_PARCIAL_STR);
						super.add(MensagemCache.getInstance().getMensagem(MSGCODE.VALOR_PREVISTO_DEPOSITO_INCOERENTE, parametros));
					}
				} 
				
				if (dto.formaPagamento.equals(Constantes.FORMA_PGTO_TOTAL)) {
					if(dto.valorPrevistoDeposito != (dto.valorAluguelNegociado + dto.valorTaxaServico)) {
						HashMap<String, String> parametros = new HashMap<String, String>();
						parametros.put(Constantes.P1, String.valueOf(dto.valorAluguelNegociado + dto.valorTaxaServico));
						parametros.put(Constantes.P2, Constantes.FORMA_PGTO_TOTAL_STR);
						super.add(MensagemCache.getInstance().getMensagem(MSGCODE.VALOR_PREVISTO_DEPOSITO_INCOERENTE, parametros));
					}
				} 
				//----------------------------------------------------------------
				// Check das datas
				//----------------------------------------------------------------
				DateManagerBase hoje = DateManagerBase.getDateManagerInstance();
				DateManagerBase dtCheckin = DateManagerBase.getDateManagerInstance(dto.dataCheckin);
				DateManagerBase dtCheckout = DateManagerBase.getDateManagerInstance(dto.dataCheckout);
				DateManagerBase dtPrevDeposito = DateManagerBase.getDateManagerInstance(dto.dataPrevistaDeposito);
				
				// Data de Checkin nao pode ser menor que a data de hoje
				if ( dtCheckin.getTimeInMillis() <= hoje.getTimeInMillis()){
					super.add(MensagemCache.getInstance().getMensagem(MSGCODE.DATA_CHECKIN_MENOR_DATA_HOJE));
				}
				// Data checkout precisa ser maior que data de checkin
				if ( dtCheckout.getTimeInMillis() <= dtCheckin.getTimeInMillis()){
					super.add(MensagemCache.getInstance().getMensagem(MSGCODE.DATA_CHECKOUT_MENOR_DATA_CHECKIN));
				}
				
				// Data prevista de deposito deve estar entre hoje e antes da data checkin
				if ( ( dtPrevDeposito.getTimeInMillis() < hoje.getTimeInMillis()) || 
					 ( dtPrevDeposito.getTimeInMillis() > dtCheckin.getTimeInMillis()) 
					){
					super.add(MensagemCache.getInstance().getMensagem(MSGCODE.DATA_PREVISTA_DEPOSITO_ENTRE_HOJE_CHECKIN));
				}
				
				//----------------------------------------------------------------
				// Valores negativos
				//----------------------------------------------------------------
				if(dto.valorAluguelNegociado < 0) {
					HashMap<String, String> parametros = new HashMap<String, String>();
					parametros.put(Constantes.P1, String.valueOf(dto.valorAluguelNegociado));
					super.add(MensagemCache.getInstance().getMensagem(MSGCODE.VALOR_NEGOCIADO_NEGATIVO, parametros));
				}
				if(dto.valorCaucao < 0) {
					HashMap<String, String> parametros = new HashMap<String, String>();
					parametros.put(Constantes.P1, String.valueOf(dto.valorCaucao));
					super.add(MensagemCache.getInstance().getMensagem(MSGCODE.VALOR_NEGOCIADO_NEGATIVO, parametros));
				}
				if(dto.valorPrevistoDeposito < 0) {
					HashMap<String, String> parametros = new HashMap<String, String>();
					parametros.put(Constantes.P1, String.valueOf(dto.valorPrevistoDeposito));
					super.add(MensagemCache.getInstance().getMensagem(MSGCODE.VALOR_NEGOCIADO_NEGATIVO, parametros));
				}
				
			}
			
		} catch (AlugueRelaxeException e) {
			// TODO: Tratar excecao
		}

		
		return super.getList();

	}

}