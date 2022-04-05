package br.com.jcv.backend.cliente;

import java.util.HashMap;
import java.util.List;

import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.ValidadorFactory;
import br.com.jcv.backend.mensagem.MensagemCache;
import br.com.jcv.backend.validador.Validador;

public class ClienteContraSenhaValidador extends
		Validador<ClienteContraSenhaDTO> {

	@Override
	public List<String> execute(ClienteContraSenhaDTO dto) {
		try {
			if (dto == null) {
				HashMap<String, String> parametros = new HashMap<String, String>();
				parametros.put(Constantes.P1, "Novo Login");
				super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_NAO_PODE_ESTAR_NULO, parametros));
			} else {
				
				// Valida DTO do cliente
				Validador<ClienteDTO> validador = ValidadorFactory.getInstanceCliente();
				validador.execute(dto);
				
				//--------------------------------------------------------------------------------------
				// campos nulos
				//--------------------------------------------------------------------------------------
				if (dto.senha == null) {
					HashMap<String, String> parametros = new HashMap<String, String>();
					parametros.put(Constantes.P1, "[senha]");
					super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_NAO_PODE_ESTAR_NULO, parametros));
				} 
				
				if (dto.contraSenha == null) {
					HashMap<String, String> parametros = new HashMap<String, String>();
					parametros.put(Constantes.P1, "[Contra-Senha]");
					super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_NAO_PODE_ESTAR_NULO, parametros));
				}
				
				//--------------------------------------------------------------------------------------
				// Tamanhos de campos
				//--------------------------------------------------------------------------------------
				if (dto.senha != null) {
					if ((dto.senha.length() < 1) || (dto.senha.length() > 60)) {
						HashMap<String, String> parametros = new HashMap<String, String>();
						parametros.put(Constantes.P1, "[Senha]");
						parametros.put(Constantes.P2, "1");
						parametros.put(Constantes.P3, "8");
						super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_COM_TAMANHO_INTERVALO_INCORRETO, parametros));
					}
				}
				
				if (dto.contraSenha != null) {
					if ((dto.contraSenha.length() < 1) || (dto.contraSenha.length() > 60)) {
						HashMap<String, String> parametros = new HashMap<String, String>();
						parametros.put(Constantes.P1, "[Contra-Senha]");
						parametros.put(Constantes.P2, "1");
						parametros.put(Constantes.P3, "8");
						super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_COM_TAMANHO_INTERVALO_INCORRETO, parametros));
					}
				}
				
				if (
						(dto.contraSenha != null) &&
						(dto.senha != null) &&
						(! dto.contraSenha.equals(dto.senha))
					) {
					HashMap<String, String> parametros = new HashMap<String, String>();
					parametros.put(Constantes.P1, "[Contra-Senha]");
					parametros.put(Constantes.P2, "1");
					parametros.put(Constantes.P3, "8");
					super.add(MensagemCache.getInstance().getMensagem(MSGCODE.SENHA_CONTRA_SENHA_INCORRETOS, parametros));
				}

				if (dto.modoPublicidade.id < 1) {
					HashMap<String, String> parametros = new HashMap<String, String>();
					parametros.put(Constantes.P1, "[Como conheceu o AlugueRelaxe]");
					super.add(MensagemCache.getInstance().getMensagem(MSGCODE.PESQUISA_CONHECER_PUBLICIDADE_NAO_PREENCHIDA, parametros));
				}

			}
			
		} catch (AlugueRelaxeException e) {
			// TODO: Tratar excecao
		}

		
		return super.getList();

	}

}