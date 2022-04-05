package br.com.jcv.aluguerelaxe.client.mobile.componente.anuncio;

import br.com.jcv.aluguerelaxe.client.mobile.vo.ImovelFichaCompletaVO;

public class AnuncioFactory {
	
	public static final int TIPO_ANUNCIO_GRID = 1;

	/**
	 * Construtor para esta classe não pode ser instanciado.
	 */
	private AnuncioFactory() {}
	
	/**
	 * Cria uma instância concreta de {@link AbstractAnuncio}
	 *
	 * @param Constantes identificando o tipo de anúncio a ser criado
	 * @return Uma instância concreta de {@link AbstractAnuncio}
	 */
	public static AbstractAnuncio getInstance(int tipoAnuncio, ImovelFichaCompletaVO ifcvo) {
		switch (tipoAnuncio) {
		case TIPO_ANUNCIO_GRID:
			return new AnuncioGrid(ifcvo);

		default:
			break;
		}
		return null;
	}
}

