package br.com.jcv.aluguerelaxe.client.imovel.listagem.uf;

import br.com.jcv.aluguerelaxe.client.componente.filtro.AbstractFiltro;
import br.com.jcv.aluguerelaxe.client.componente.paginacao.AbstractListPaginada;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ListaImovelUF implements EntryPoint {

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
		// teste de uma cidade
//		ComandoListaImovelDTO comando = new ComandoListaImovelDTO();
//		comando.cmd = COMANDO_LISTA_PAGINADA_CIDADE;
//		comando.cidade = 2947;
		
		// teste de uma uf
//		ComandoListaImovelDTO comando = new ComandoListaImovelDTO();
//		comando.cmd = COMANDO_LISTA_PAGINADA_UF;
//		comando.uf = "RJ";
		
		DockPanel hp = new DockPanel();
		
		AbstractListPaginada alp = concreteListPaginada(comando);
		
		hp.add(montaFiltros(alp), DockPanel.WEST);
		hp.add(alp, DockPanel.CENTER);
		
		RootPanel.get("gwt-ficha-lista-imovel-uf").add(hp);
	}

	private Widget montaFiltros(AbstractListPaginada alp) {
		VerticalPanel vp = new VerticalPanel();
		
		// cria o filtro e registra classe implementadora de FiltroListener para responder ao evento onFiltroAplicar()
		//AbstractFiltro afIdImovel = new ImovelIDFiltro(new FiltroImovelIDComposite());
		//afIdImovel.addFiltroListener(alp);
		
		AbstractFiltro afImovel = new ImovelFiltro(new FiltroImovelComposite());
		afImovel.addFiltroListener(alp);
		
		//vp.add(afIdImovel);
		vp.add(afImovel);
		
		return vp;
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
