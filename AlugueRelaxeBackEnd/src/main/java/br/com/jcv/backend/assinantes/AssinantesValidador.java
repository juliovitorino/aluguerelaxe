package br.com.jcv.backend.assinantes;

import java.util.HashMap;
import java.util.List;

import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.mensagem.MensagemCache;
import br.com.jcv.backend.validador.Validador;

/**
 * @author Julio
 *
 */
public class AssinantesValidador extends Validador<AssinantesDTO> {

	@SuppressWarnings("unchecked")
	@Override
	public List<String> execute(AssinantesDTO dto) {
		try {
			if (dto == null) {
				HashMap<String, String> parametros = new HashMap<String, String>();
				parametros.put(Constantes.P1, "Informações de Assinante");
				super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_NAO_PODE_ESTAR_NULO, parametros));
			} else {
				//--------------------------------------------------------------------------------------
				// campos nulos
				//--------------------------------------------------------------------------------------
				if (dto.nome == null) {
					HashMap<String, String> parametros = new HashMap<String, String>();
					parametros.put(Constantes.P1, "[NOME]");
					super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_NAO_PODE_ESTAR_NULO, parametros));
				} 
				if (dto.email == null) {
					HashMap<String, String> parametros = new HashMap<String, String>();
					parametros.put(Constantes.P1, "[EMAIL]");
					super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_NAO_PODE_ESTAR_NULO, parametros));
				}
				
				//--------------------------------------------------------------------------------------
				// Tamanhos de campos
				//--------------------------------------------------------------------------------------
				if (dto.nome != null) {
					if ((dto.nome.length() < 1) || (dto.nome.length() > 60)) {
						HashMap<String, String> parametros = new HashMap<String, String>();
						parametros.put(Constantes.P1, "[nome]");
						parametros.put(Constantes.P2, "1");
						parametros.put(Constantes.P3, "60");
						super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_COM_TAMANHO_INTERVALO_INCORRETO, parametros));
					}
				}
				
				if (dto.email != null){
					super.validarEmailPattern(dto.email);
					if ((dto.email.length() < 1) || (dto.email.length() > 100)) {
						HashMap<String, String> parametros = new HashMap<String, String>();
						parametros.put(Constantes.P1, "[EMAIL]");
						parametros.put(Constantes.P2, "1");
						parametros.put(Constantes.P3, "100");
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
