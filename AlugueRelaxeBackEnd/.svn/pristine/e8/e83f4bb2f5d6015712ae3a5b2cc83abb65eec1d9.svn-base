package br.com.jcv.backend.imovel.tabelapreco;

import java.util.HashMap;
import java.util.List;

import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.mensagem.MensagemCache;
import br.com.jcv.backend.validador.Validador;

public class TabelaPrecoValidador extends Validador<TabelaPrecoDTO> {

	@Override
	public List<String> execute(TabelaPrecoDTO dto) {
		try {
			if (dto == null) {
				HashMap<String, String> parametros = new HashMap<String, String>();
				parametros.put(Constantes.P1, "Tabela de Preço");
				super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_NAO_PODE_ESTAR_NULO, parametros));
			} else {
				//--------------------------------------------------------------------------------------
				// campos nulos
				//--------------------------------------------------------------------------------------

				//validarCampoNulo(dto.textoPeriodo, "[Período]");
				//validarCampoNulo(dto.dataInicio, "[Data de - Tabela de Preço]");
				//validarCampoNulo(dto.dataFim, "[Data até - Tabela de Preço]");
				//validarCampoNulo(dto.textoMinimoDe, "[Mínimo de]");
				//validarCampoNulo(dto.observacao, "[Observação]");
				
				//--------------------------------------------------------------------------------------
				// Tamanhos de campos
				//--------------------------------------------------------------------------------------
				//validarTamanhoCampoString(dto.textoPeriodo,1,50,"[Período]");
				//validarTamanhoCampoString(dto.textoMinimoDe,1,18,"[Mínimo de]");
				validarTamanhoCampoString(dto.observacao,0,200,"[Observação]");
				
			}
			

		} catch (AlugueRelaxeException e) {
			// TODO: Tratar excecao
		}

		return super.getList();
	}

}
