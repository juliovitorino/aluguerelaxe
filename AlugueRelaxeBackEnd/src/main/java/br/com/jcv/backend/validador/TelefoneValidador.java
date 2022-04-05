package br.com.jcv.backend.validador;

import java.util.HashMap;
import java.util.List;

import br.com.jcv.backend.cliente.TelefoneDTO;
import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.mensagem.MensagemCache;

public class TelefoneValidador extends Validador<TelefoneDTO> {

	@Override
	public List<String> execute(TelefoneDTO dto) {
		try {
			if (dto == null) {
				HashMap<String, String> parametros = new HashMap<String, String>();
				parametros.put(Constantes.P1, "Endereco");
				super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_NAO_PODE_ESTAR_NULO, parametros));
			} else {
				//--------------------------------------------------------------------------------------
				// campos nulos
				//--------------------------------------------------------------------------------------
				
				if (dto.ddd == null) {
					HashMap<String, String> parametros = new HashMap<String, String>();
					parametros.put(Constantes.P1, "[DDD]");
					super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_NAO_PODE_ESTAR_NULO, parametros));
				}
				if (dto.telefone == null) {
					HashMap<String, String> parametros = new HashMap<String, String>();
					parametros.put(Constantes.P1, "[Telefone]");
					super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_NAO_PODE_ESTAR_NULO, parametros));
				}
				
				//--------------------------------------------------------------------------------------
				// Tamanhos de campos
				//--------------------------------------------------------------------------------------
				if (dto.ddd != null){
					validarTamanhoCampoString(dto.ddd, 1, 2, "[DDD]");
					try {
						long ddd = Long.valueOf(dto.ddd);
					} catch (NumberFormatException nfe) {
						HashMap<String, String> parametros = new HashMap<String, String>();
						parametros.put(Constantes.P1, "[DDD]");
						super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_CONTER_SOMENTE_NUMEROS, parametros));
					}
				}
				
				if (dto.telefone != null){
					validarTamanhoCampoString(dto.telefone, 1, 10, "[Número]");
					try {
						long fone = Long.valueOf(dto.telefone);
					} catch (NumberFormatException nfe) {
						HashMap<String, String> parametros = new HashMap<String, String>();
						parametros.put(Constantes.P1, "[Número do Telefone]");
						super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_CONTER_SOMENTE_NUMEROS, parametros));
					}
				}
				
			}
			

		} catch (AlugueRelaxeException e) {
			// TODO: Tratar excecao
		}

		return super.getList();

	}

}
