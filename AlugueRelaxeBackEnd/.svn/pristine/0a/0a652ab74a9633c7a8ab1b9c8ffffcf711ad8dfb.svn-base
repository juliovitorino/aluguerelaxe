package br.com.jcv.backend.validador;

import java.util.HashMap;
import java.util.List;

import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.dto.EnderecoDTO;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.mensagem.MensagemCache;

public class EnderecoValidador extends Validador<EnderecoDTO> {

	@Override
	public List<String> execute(EnderecoDTO dto) {
		try {
			if (dto == null) {
				HashMap<String, String> parametros = new HashMap<String, String>();
				parametros.put(Constantes.P1, "Endereco");
				super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_NAO_PODE_ESTAR_NULO, parametros));
			} else {
				//--------------------------------------------------------------------------------------
				// campos nulos
				//--------------------------------------------------------------------------------------
				
				if (dto.nome == null) {
					HashMap<String, String> parametros = new HashMap<String, String>();
					parametros.put(Constantes.P1, "[ENDERECO]");
					super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_NAO_PODE_ESTAR_NULO, parametros));
				}
				if (dto.numero == null) {
					HashMap<String, String> parametros = new HashMap<String, String>();
					parametros.put(Constantes.P1, "[NUMERO]");
					super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_NAO_PODE_ESTAR_NULO, parametros));
				}
				if (dto.bairro == null) {
					HashMap<String, String> parametros = new HashMap<String, String>();
					parametros.put(Constantes.P1, "[BAIRRO");
					super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_NAO_PODE_ESTAR_NULO, parametros));
				}
				if (dto.cep == null) {
					HashMap<String, String> parametros = new HashMap<String, String>();
					parametros.put(Constantes.P1, "[CEP]");
					super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_NAO_PODE_ESTAR_NULO, parametros));
				}
				if (dto.codigoUfCidadeItem <= 0) {
					HashMap<String, String> parametros = new HashMap<String, String>();
					parametros.put(Constantes.P1, "[UF e Cidade]");
					super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_NAO_PODE_ESTAR_NULO, parametros));
				}
				
				//--------------------------------------------------------------------------------------
				// Tamanhos de campos
				//--------------------------------------------------------------------------------------
				validarTamanhoCampoString(dto.nome,1,120,"[Nome do Endereço]");
				validarTamanhoCampoString(dto.numero,1,10,"[Número]");
				validarTamanhoCampoString(dto.complemento,0,20,"[Complemento]");
				validarTamanhoCampoString(dto.bairro,0,120,"[Bairro]");
				
				if (dto.cep != null){
					if (dto.cep.length() != 8) {
						HashMap<String, String> parametros = new HashMap<String, String>();
						parametros.put(Constantes.P1, "[Cep]");
						parametros.put(Constantes.P2, "8");
						super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_COM_TAMANHO_EXATO, parametros));
					}
					
					try {
						long cep = Long.valueOf(dto.cep);
					} catch (NumberFormatException nfe) {
						HashMap<String, String> parametros = new HashMap<String, String>();
						parametros.put(Constantes.P1, "[Cep]");
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
