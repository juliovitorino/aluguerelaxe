package br.com.jcv.aluguerelaxe.client.administracao.console.tarefaspendentes;

import br.com.jcv.aluguerelaxe.client.administracao.login.SessaoVO;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Widget;

public class TarefasPendentesFormComposite extends FormComposite<VOPadrao> {
	
	private TarefasPendentesTreeFormComposite tptfc;
	private ViewTarefasPendentesFormComposite vtpfc;
	
	public TarefasPendentesFormComposite(SessaoVO sessao) {
		super();
		
		// Encapsula a sessao no formulario
		tptfc.update(sessao);
		
		tptfc.update();
	}
	

	public void init() {
		tptfc = new TarefasPendentesTreeFormComposite();
		vtpfc = new ViewTarefasPendentesFormComposite();
		
		// Inscreve ViewTarefasPendentesFormComposite como ouvinte de eventos de TarefasPendentesTreeFormComposite
		tptfc.addListener(vtpfc);
	}

	public Widget render() {
		DockPanel dp = new DockPanel();
		dp.add(tptfc, DockPanel.WEST);
		dp.add(vtpfc, DockPanel.CENTER);
		return dp;
	}

	@Override
	public TarefasPendentesTreeVO getVO() {
		return null;
	}
	
	public void update(TarefasPendentesTreeVO vo){
	}

	@Override
	public void clear() {
	}

	@Override
	public void update(VOPadrao vo) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void notifier(VOPadrao vo) {
		// TODO Auto-generated method stub
		
	}


}
