package br.com.jcv.aluguerelaxe.client.componente.filtro;

import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

/**
 *<h2>FiltroListener</h2>
 *<p>Metodos para implementação da interface FiltroListener.
 *</p>
 */
public interface FiltroListener<V extends VOPadrao> {
	void onFiltroAplicar(AbstractFiltro filtroImpl, V vofiltro);
}