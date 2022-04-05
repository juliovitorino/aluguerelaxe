package br.com.jcv.backend.template;

import java.util.Map;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

/**
 * Template para HTML Index Bootstrap AlugueRelaxe V3
 * @author Julio
 *
 */
public class TemplatePropertaIndex extends Template {

	@Deprecated
	public TemplatePropertaIndex(Map<String,String> conteudo) throws  AlugueRelaxeException {
		super(VariavelCache.getInstance().getValor(VariavelConstantes.TEMPLATE_PATH).concat("template-properta-index.html"));
		initMap(conteudo);
		this.execute();
	}

	public TemplatePropertaIndex(TemplateDTO dto) throws  AlugueRelaxeException {
		super(dto.path + dto.template);
		initMap(dto.conteudo);
		this.execute();
	}

}

