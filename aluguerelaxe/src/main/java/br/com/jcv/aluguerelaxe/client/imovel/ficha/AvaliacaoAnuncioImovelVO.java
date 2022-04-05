package br.com.jcv.aluguerelaxe.client.imovel.ficha;



import br.com.jcv.aluguerelaxe.client.componente.classificador.ClassificadorVO;

import com.google.gwt.user.client.rpc.IsSerializable;

public class AvaliacaoAnuncioImovelVO implements IsSerializable {
	public long idImovelAvaliado;
	public ClassificadorVO classFotografia;
	public ClassificadorVO classQualidadeTexto;
	public ClassificadorVO classInformacaoRelevante;
}