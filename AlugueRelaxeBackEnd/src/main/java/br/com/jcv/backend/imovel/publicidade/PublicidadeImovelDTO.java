package br.com.jcv.backend.imovel.publicidade;

import java.io.Serializable;

import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;
import br.com.jcv.backend.imovel.faturamento.ImovelPlanoFaturaDTO;
import br.com.jcv.backend.plano.PlanoDTO;


/**
 * <h1>Publicidade</h1> 
 * <p>Classe de transferencia de dados para publicidade</p>
 *
 * @author Julio Vitorino
 * @version 1.10
 * @since 12 Aug 2009
 */
public class PublicidadeImovelDTO extends DTOPadrao implements Serializable {

	private static final long serialVersionUID = -2556438420014654012L;
	public PublicidadeDTO publicidade;
	public ImovelFichaCompletaDTO fichaImovel;
	public ImovelPlanoFaturaDTO fatura;
	public PlanoDTO plano;
}

