package br.com.jcv.backend.reserva;

import java.util.HashMap;
import java.util.List;

import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.mensagem.MensagemCache;
import br.com.jcv.backend.validador.Validador;

public class CodigoTrackingValidador extends
		Validador<ReservaDTO> {

	@Override
	public List<String> execute(ReservaDTO dto) {
		try {
			if (dto == null) {
				HashMap<String, String> parametros = new HashMap<String, String>();
				parametros.put(Constantes.P1, "Validação do Código Tracking");
				super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_NAO_PODE_ESTAR_NULO, parametros));
			} else {
				
							
				//--------------------------------------------------------------------------------------
				// campos nulos
				//--------------------------------------------------------------------------------------
				if (dto.chaveTracker == null) {
					HashMap<String, String> parametros = new HashMap<String, String>();
					parametros.put(Constantes.P1, "[Código Tracking]");
					super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_NAO_PODE_ESTAR_NULO, parametros));
				} 
				
				//--------------------------------------------------------------------------------------
				// Tamanhos de campos
				//--------------------------------------------------------------------------------------
				if (dto.chaveTracker != null) {
					if ((dto.chaveTracker.length() < 1) || (dto.chaveTracker.length() > 10)) {
						HashMap<String, String> parametros = new HashMap<String, String>();
						parametros.put(Constantes.P1, "[Código Tracking]");
						parametros.put(Constantes.P2, "1");
						parametros.put(Constantes.P3, "10");
						super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_COM_TAMANHO_INTERVALO_INCORRETO, parametros));
					}
				}
				
			}
			
		} catch (AlugueRelaxeException e) {
			// TODO: Tratar excecao
		}

		
		return super.getList();

	}

}