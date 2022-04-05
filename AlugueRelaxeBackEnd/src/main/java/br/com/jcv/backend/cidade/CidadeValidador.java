package br.com.jcv.backend.cidade;

import java.util.HashMap;
import java.util.List;

import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.mensagem.MensagemCache;
import br.com.jcv.backend.validador.Validador;

public class CidadeValidador extends Validador<CidadeDTO> {

	@Override
	public List<String> execute(CidadeDTO dto) {
		try {
			HashMap<String, String> parametros = new HashMap<String, String>();
			if (dto == null) {
				parametros.put(Constantes.P1, "dto de Cidade");
				super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_NAO_PODE_ESTAR_NULO, parametros));
			}
			else if (dto.nome == null) {
				parametros.put(Constantes.P1, "[nome da cidade]");
				super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_NAO_PODE_ESTAR_NULO, parametros));
			} else if (dto.nome.length() > 50) {
					parametros.put(Constantes.P1, "0");
					parametros.put(Constantes.P2, "50");
					super.add(MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_COM_TAMANHO_INCORRETO));
			}
		} catch (AlugueRelaxeException e) {
			// TODO: Tratar excecao
		}

		return super.getList();
	}

}
