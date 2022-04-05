package br.com.jcv.backend.template;

import br.com.jcv.backend.exception.AlugueRelaxeException;

/**
 * Template para inscricao de promocao
 * @author Julio
 *
 */
public class TemplatePropertaPaginacao extends Template {

	public TemplatePropertaPaginacao(TemplateDTO dto) throws  AlugueRelaxeException {
		super(dto.path + dto.template);
		initMap(dto.conteudo);
		this.execute();
	}

}

