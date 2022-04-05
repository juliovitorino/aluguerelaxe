package br.com.jcv.aluguerelaxe.client.imovel.thread;

import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ContatoClienteVO;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;

import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Widget;

public class AnuncioGridThreadImovelFormComposite extends
		FormComposite<ImovelFichaCompletaVO> {
	
	private AnuncioGridThreadImovel agti;
	private ThreadDispararPreReservaFormComposite tdprfc; 
	private DockPanel dp;
	
	@Override
	public ImovelFichaCompletaVO getVO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(ImovelFichaCompletaVO vo) {
		agti = new AnuncioGridThreadImovel(vo); 
		tdprfc = new ThreadDispararPreReservaFormComposite() ; 
		dp.add(agti, DockPanel.CENTER);
		dp.add(tdprfc, DockPanel.EAST);
		tdprfc.update(vo.cliente);
	}

	public void update(ContatoClienteVO vo) {
		tdprfc.update(vo);
	}

	@Override
	public void notifier(ImovelFichaCompletaVO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Widget render() {
		dp = new DockPanel();
		return dp;
	}

	@Override
	public void init() {
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

}
