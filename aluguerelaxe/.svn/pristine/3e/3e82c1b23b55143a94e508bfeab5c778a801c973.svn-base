package br.com.jcv.aluguerelaxe.client.administracao.console.tarifawizard;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;
import br.com.jcv.aluguerelaxe.client.imovel.tabelapreco.TabelaPrecoVO;

import com.google.gwt.user.client.ui.Widget;



public class TabelaPrecoFormComposite extends
		FormComposite<ImovelFichaCompletaVO> {

	private TabelaPrecoGridDataEntry tpgde;
	
	@Override
	public ImovelFichaCompletaVO getVO() {
		return null;
	}
	
	public List<TabelaPrecoVO> getListVO() {
		return this.tpgde.getListVO();
	}

	@Override
	public void update(ImovelFichaCompletaVO vo) {
		this.tpgde.update(vo.tabelaPreco);
	}

	@Override
	public Widget render() {
		return this.tpgde;
	}

	@Override
	public void init() {
		this.tpgde = new TabelaPrecoGridDataEntry();
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifier(ImovelFichaCompletaVO vo) {
		// TODO Auto-generated method stub
		
	}

}
