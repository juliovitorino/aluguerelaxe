package br.com.jcv.aluguerelaxe.client.imovel.ficha;

import br.com.jcv.aluguerelaxe.client.imovel.ImovelPlanoFaturaVO;
import br.com.jcv.aluguerelaxe.client.plano.PlanoVO;
import br.com.jcv.aluguerelaxe.client.publicidade.PublicidadeVO;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * <h1>Publicidade</h1> 
 * <p>Classe de transferencia de dados para publicidade</p>
 *
 * @author Julio Vitorino
 * @version 1.10
 * @since 12 Aug 2009
 */
public class PublicidadeImovelVO extends VOPadrao implements IsSerializable {

	public PublicidadeVO publicidade;
	public ImovelFichaCompletaVO fichaImovel;
	public ImovelPlanoFaturaVO fatura;
	public PlanoVO plano;
}

