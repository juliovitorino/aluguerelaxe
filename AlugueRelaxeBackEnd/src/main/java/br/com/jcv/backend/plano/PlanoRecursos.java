package br.com.jcv.backend.plano;

import br.com.jcv.backend.chain.AbstractChainOfResponsability;
import br.com.jcv.backend.imovel.publicidade.PublicidadeImovelDTO;

public class PlanoRecursos extends AbstractChainOfResponsability<PublicidadeImovelDTO>{
	
	public static final int RECURSO_VIDEO = 0;
	public static final int RECURSO_CANECA = 1;
	public static final int RECURSO_SUPERBANNER = 2;
	public static final int RECURSO_PP = 3;
	public static final int RECURSO_PD = 4;
	public static final int RECURSO_IMOVEL_PATROCINADOR = 5;
	public static final int RECURSO_NEGOCIA_DIRETO = 6;
	public static final int RECURSO_CAMPANHA_EMAIL = 7;
	public static final int RECURSO_CAMPANHA_SMS = 8;
	public static final int RECURSO_APOIO_JURIDICO = 9;
	public static final int RECURSO_FACEBOOK = 10;
	public static final int RECURSO_BANNER = 11;
	public static final int RECURSO_ANUNCIO_NARRADO = 12;
	public static final int RECURSO_ANUNCIO_DIFERENCIADO = 13;
	public static final int RECURSO_ALERTA_24H = 14;
	public static final int RECURSO_FURA_FILA = 15;

	public void setContext(PublicidadeImovelDTO pdto) {
		this.pdto = pdto;
	}

}
