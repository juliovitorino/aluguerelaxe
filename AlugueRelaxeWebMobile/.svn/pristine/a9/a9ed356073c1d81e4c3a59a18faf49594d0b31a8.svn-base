package br.com.jcv.aluguerelaxe.client.mobile.main;

import br.com.jcv.aluguerelaxe.client.mobile.componente.paginacao.AbstractListPaginada;
import br.com.jcv.aluguerelaxe.client.mobile.vo.ComandoListaImovelDTO;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.RootPanel;

public class FiltroBaseResultadoEntryPoint implements EntryPoint {

	public static final int COMANDO_LISTA_PAGINADA_UF = 0;
	public static final int COMANDO_LISTA_PAGINADA_CIDADE = 1;

	public void onModuleLoad() {

		//----------------------------------------------------------------
		// O comando podera vir populado ou com UF ou com cidade
		// NUNCA com os dois ao mesmo tempo (XOR)
		// a decisao final sera tomada pela variavel 'cmd'
		//----------------------------------------------------------------
		ComandoListaImovelDTO comando = new ComandoListaImovelDTO();
		comando.cmd = Integer.valueOf(com.google.gwt.user.client.Window.Location.getParameter("atvd"));
		comando.uf = com.google.gwt.user.client.Window.Location.getParameter("uf");
		comando.cidade = Integer.valueOf(com.google.gwt.user.client.Window.Location.getParameter("cidade"));
//		
		DockPanel hp = new DockPanel();
		
		AbstractListPaginada alp = concreteListPaginada(comando);
		
		hp.add(alp, DockPanel.CENTER);
		
		RootPanel.get("gwt-resultado").add(hp);
	}

	
	private AbstractListPaginada concreteListPaginada(ComandoListaImovelDTO comando) {
		int cmd = comando.cmd;
		switch(cmd) {
			case COMANDO_LISTA_PAGINADA_UF:
				return new FichaImovelListPaginada(comando.uf);
			case COMANDO_LISTA_PAGINADA_CIDADE:
				return new FichaImovelListPaginadaCidade(comando.cidade);
		}
		return null;
	}

}
