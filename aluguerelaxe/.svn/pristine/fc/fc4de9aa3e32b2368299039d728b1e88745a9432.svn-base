package br.com.jcv.aluguerelaxe.client.administracao.console.imovelpropriedade;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.grid.GridCaracteristica;
import br.com.jcv.aluguerelaxe.client.imovel.caracteristica.ImovelCaracteristicaVO;

import com.google.gwt.user.client.ui.Widget;

public class CaracteristicaFormComposite extends FormComposite<ImovelCaracteristicaVO> {
	private GridCaracteristica grdCarac;
	
	public CaracteristicaFormComposite() {
		super();
	}
	
	public List<ImovelCaracteristicaVO> getListCondominio() {
		return grdCarac.getList("C");
	}

	public List<ImovelCaracteristicaVO> getListImovel() {
		return grdCarac.getList("I");
	}

	@Override
	public ImovelCaracteristicaVO getVO() {
		return null;
	}

	@Override
	public void update(ImovelCaracteristicaVO vo) {
		// Nada a fazer!
	}
	
	public void update(List<ImovelCaracteristicaVO> lst) {
		grdCarac.setCheckBoxCaracteristica(lst);
	}

	@Override
	public void clear() {
		grdCarac.reset();
	}

	@Override
	public Widget render() {
		return grdCarac;
	}

	@Override
	public void init() {
		grdCarac = new GridCaracteristica(6);
	}

	@Override
	public void notifier(ImovelCaracteristicaVO vo) {
		// TODO Auto-generated method stub
		
	}
}
