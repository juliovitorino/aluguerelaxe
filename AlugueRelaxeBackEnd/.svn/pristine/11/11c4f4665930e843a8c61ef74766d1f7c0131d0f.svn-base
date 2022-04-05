package br.com.jcv.backend.cliente;

import java.util.HashMap;
import java.util.List;

import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ContatoClienteDTO;
import br.com.jcv.backend.mensagem.MensagemCache;
import br.com.jcv.backend.validador.Validador;

public class ContatoAnuncianteValidador extends Validador<ContatoClienteDTO> {

	@Override
	public List<String> execute(ContatoClienteDTO dto) {
		try {
			if (dto == null) {
				HashMap<String, String> parametros = new HashMap<String, String>();
				parametros.put(Constantes.P1, "Contato do Cliente");
				super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_NAO_PODE_ESTAR_NULO, parametros));
			} else {
				//--------------------------------------------------------------------------------------
				// campos nulos
				//--------------------------------------------------------------------------------------
				
				if (dto.uf == null) {
					HashMap<String, String> parametros = new HashMap<String, String>();
					parametros.put(Constantes.P1, "[UF]");
					super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_NAO_PODE_ESTAR_NULO, parametros));
				}
				if (dto.pais == null) {
					HashMap<String, String> parametros = new HashMap<String, String>();
					parametros.put(Constantes.P1, "[País]");
					super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_NAO_PODE_ESTAR_NULO, parametros));
				}
				if (dto.proposto == null) {
					HashMap<String, String> parametros = new HashMap<String, String>();
					parametros.put(Constantes.P1, "[Nome do Proposto]");
					super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_NAO_PODE_ESTAR_NULO, parametros));
				}
				if (dto.email == null) {
					HashMap<String, String> parametros = new HashMap<String, String>();
					parametros.put(Constantes.P1, "[e-mail]");
					super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_NAO_PODE_ESTAR_NULO, parametros));
				}
				if (dto.cidade == null) {
					HashMap<String, String> parametros = new HashMap<String, String>();
					parametros.put(Constantes.P1, "[Cidade]");
					super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_NAO_PODE_ESTAR_NULO, parametros));
				}
				if (dto.informacoesAdicionais == null) {
					HashMap<String, String> parametros = new HashMap<String, String>();
					parametros.put(Constantes.P1, "[Informações Adicionais]");
					super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_NAO_PODE_ESTAR_NULO, parametros));
				}
				if (dto.ddd == null) {
					HashMap<String, String> parametros = new HashMap<String, String>();
					parametros.put(Constantes.P1, "[ddd]");
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
				if (dto.informacoesAdicionais != null) {
					validarTamanhoCampoString(dto.informacoesAdicionais, 1, 2000, "[Informações Adicionais]");
				}
				if (dto.cidade != null) {
					validarTamanhoCampoString(dto.cidade, 1, 50, "[Cidade]");
					
				}
				if (dto.pais != null) {
					validarTamanhoCampoString(dto.pais, 1, 30, "[País]");
				}
				if (dto.uf != null) {
					validarTamanhoCampoString(dto.uf, 1, 30, "[UF]");
					if (dto.uf.equals("XX")) {
						HashMap<String, String> parametros = new HashMap<String, String>();
						parametros.put(Constantes.P1, "[UF]");
						super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_NAO_PODE_ESTAR_NULO, parametros));
					}
				}
				if (dto.email != null) {
					validarTamanhoCampoString(dto.email, 1, 200, "[e-mail]");
				}
				if (dto.proposto != null) {
					validarTamanhoCampoString(dto.proposto, 1, 100, "[Nome do Proposto]");
				}

				try {
					validarTamanhoCampoString(String.valueOf(dto.qtdeAdultos), 1, 2, "[Qtde Adultos]");
				} catch (NumberFormatException nfe) {
					HashMap<String, String> parametros = new HashMap<String, String>();
					parametros.put(Constantes.P1, "[Qtde Adultos]");
					super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_CONTER_SOMENTE_NUMEROS, parametros));
				}
				try {
					validarTamanhoCampoString(String.valueOf(dto.qtdeCriancas), 1, 2, "[Qtde Crianças]");
				} catch (NumberFormatException nfe) {
					HashMap<String, String> parametros = new HashMap<String, String>();
					parametros.put(Constantes.P1, "[Qtde Crianças]");
					super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_CONTER_SOMENTE_NUMEROS, parametros));
				}

				if (dto.modopublicidade.id < 1) {
					HashMap<String, String> parametros = new HashMap<String, String>();
					parametros.put(Constantes.P1, "[Como conheceu o AlugueRelaxe]");
					super.add(MensagemCache.getInstance().getMensagem(MSGCODE.PESQUISA_CONHECER_PUBLICIDADE_NAO_PREENCHIDA, parametros));
				}

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
