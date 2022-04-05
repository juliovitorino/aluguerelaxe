package br.com.jcv.backend.template;

import java.util.Map;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

/**
 * Template para inscricao de promocao
 * @author Julio
 *
 */
public class TemplatePropertaFichaImovel extends Template {

	@Deprecated
	public TemplatePropertaFichaImovel(Map<String,String> conteudo) throws  AlugueRelaxeException {
		super(VariavelCache.getInstance().getValor(VariavelConstantes.TEMPLATE_PATH).concat("template-properta-ficha-imovel.html"));
		initMap(conteudo);
		this.execute();
	}

	public TemplatePropertaFichaImovel(TemplateDTO dto) throws  AlugueRelaxeException {
		super(dto.path + dto.template);
		initMap(dto.conteudo);
		this.execute();
	}

}

