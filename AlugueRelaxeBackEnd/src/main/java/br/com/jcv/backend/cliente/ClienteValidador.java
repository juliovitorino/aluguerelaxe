package br.com.jcv.backend.cliente;

import java.util.HashMap;
import java.util.List;

import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.ValidadorFactory;
import br.com.jcv.backend.mensagem.MensagemCache;
import br.com.jcv.backend.validador.Validador;

/**
 * @author Julio
 *
 */
public class ClienteValidador extends Validador<ClienteDTO> {

	@SuppressWarnings("unchecked")
	@Override
	public List<String> execute(ClienteDTO dto) {
		try {
			if (dto == null) {
				HashMap<String, String> parametros = new HashMap<String, String>();
				parametros.put(Constantes.P1, "Informações de Cliente");
				super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_NAO_PODE_ESTAR_NULO, parametros));
			} else {
				//--------------------------------------------------------------------------------------
				// campos nulos
				//--------------------------------------------------------------------------------------
				if (dto.nome == null) {
					HashMap<String, String> parametros = new HashMap<String, String>();
					parametros.put(Constantes.P1, "[NOME DO CLIENTE]");
					super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_NAO_PODE_ESTAR_NULO, parametros));
				} 
				if (( dto.cgc == null) && (dto.cpf == null)) {
					HashMap<String, String> parametros = new HashMap<String, String>();
					parametros.put(Constantes.P1, "[CPF e CGC]");
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
				
				if (dto.cpf != null){
					if (dto.cpf.length() != 11) {
						HashMap<String, String> parametros = new HashMap<String, String>();
						parametros.put(Constantes.P1, "[CPF]");
						parametros.put(Constantes.P2, "11");
						super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_COM_TAMANHO_EXATO, parametros));
					}
					try {
						long cpf = Long.valueOf(dto.cpf);
					} catch (NumberFormatException nfe) {
						HashMap<String, String> parametros = new HashMap<String, String>();
						parametros.put(Constantes.P1, "[CPF]");
						super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_CONTER_SOMENTE_NUMEROS, parametros));
					}
				} else 

				if((dto.cgc != null) && (dto.cpf.trim().length() == 0)){
					if (dto.cgc.length() != 18) {
						HashMap<String, String> parametros = new HashMap<String, String>();
						parametros.put(Constantes.P1, "[CGC]");
						parametros.put(Constantes.P2, "18");
						super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_COM_TAMANHO_EXATO, parametros));
					}
					try {
						long cgc = Long.valueOf(dto.cgc);
					} catch (NumberFormatException nfe) {
						HashMap<String, String> parametros = new HashMap<String, String>();
						parametros.put(Constantes.P1, "[CGC]");
						super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_CONTER_SOMENTE_NUMEROS, parametros));
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

				if (dto.senha != null){
					if (dto.senha.length() > 40) {
						HashMap<String, String> parametros = new HashMap<String, String>();
						parametros.put(Constantes.P1, "[SENHA]");
						parametros.put(Constantes.P2, "40");
						super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_COM_TAMANHO_INCORRETO, parametros));
					}
				}
				if (dto.urlwww != null){
					if (dto.urlwww.length() > 500) {
						HashMap<String, String> parametros = new HashMap<String, String>();
						parametros.put(Constantes.P1, "[URL]");
						parametros.put(Constantes.P2, "500");
						super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_COM_TAMANHO_INCORRETO, parametros));
					}
				}
				
				if (dto.msn != null){
					if (dto.msn.length() > 100) {
						HashMap<String, String> parametros = new HashMap<String, String>();
						parametros.put(Constantes.P1, "[MSN]");
						parametros.put(Constantes.P2, "100");
						super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_COM_TAMANHO_INCORRETO, parametros));
					}
				}
				if (dto.skype != null){
					if (dto.skype.length() > 100) {
						HashMap<String, String> parametros = new HashMap<String, String>();
						parametros.put(Constantes.P1, "[SKYPE]");
						parametros.put(Constantes.P2, "100");
						super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_COM_TAMANHO_INCORRETO, parametros));
					}
				}
				if (dto.googleTalk != null){
					if (dto.googleTalk.length() > 100) {
						HashMap<String, String> parametros = new HashMap<String, String>();
						parametros.put(Constantes.P1, "[Google Talk]");
						parametros.put(Constantes.P2, "100");
						super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_COM_TAMANHO_INCORRETO, parametros));
					}
				}
				
				// Verifica os erros de outros DTOs aninhados
				List<String> lstErrosEndereco = ValidadorFactory.getInstanceEndereco().execute(dto.endereco);
				super.merge(lstErrosEndereco);
				
				if (dto.telefones != null) {
					if (dto.telefones.size() > 0) {
						for (TelefoneDTO telefone : dto.telefones) {
							List<String> lstErrosTelefones = ValidadorFactory.getInstanceTelefone().execute(telefone);
							super.merge(lstErrosTelefones);
						}
					} else {
						super.add(MensagemCache.getInstance().getMensagem(MSGCODE.TELEFONE_OBRIGATORIO));
					}
				} else {
					super.add(MensagemCache.getInstance().getMensagem(MSGCODE.TELEFONE_OBRIGATORIO));
				}
				
			}
			
		} catch (AlugueRelaxeException e) {
			// TODO: Tratar excecao
		}

		
		return super.getList();

	}

}
