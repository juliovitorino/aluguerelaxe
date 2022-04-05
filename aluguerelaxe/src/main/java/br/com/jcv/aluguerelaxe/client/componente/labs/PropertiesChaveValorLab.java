package br.com.jcv.aluguerelaxe.client.componente.labs;

import br.com.jcv.aluguerelaxe.client.componente.chavevalor.ChaveValor;
import br.com.jcv.aluguerelaxe.client.componente.chavevalor.GrupoChaveValor;
import br.com.jcv.aluguerelaxe.client.componente.chavevalor.PropertiesGrupoChaveValor;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;


public class PropertiesChaveValorLab implements EntryPoint {
	public void onModuleLoad() {
		GrupoChaveValor conexao = new GrupoChaveValor("Connection");
		conexao.add(new ChaveValor("Connection Name", new Label("sql6cl1/inst6 (MASTER/CS67581)"), "Nome da Conexao SQLServer" ));
	
		GrupoChaveValor conexaoDetails = new GrupoChaveValor("Connection Details");
		conexaoDetails.add(new ChaveValor("Elapsed time", new Label("1342134324"), "Tempo decorrido"));
		conexaoDetails.add(new ChaveValor("Session Tag", new Label("FAFD09AB"), "Hash da sessao"));
		conexaoDetails.add(new ChaveValor("Time Session Tag", new Label("hsrtrirwt FAFD09AB"), "Hash da sessao"));
		
		PropertiesGrupoChaveValor pgcv = new PropertiesGrupoChaveValor();
		pgcv.add(conexao);
		pgcv.add(conexaoDetails);
		
		pgcv.init();
		RootPanel.get("gwt-datagrid").add(pgcv);
		
	}
}