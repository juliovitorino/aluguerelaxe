package br.com.jcv.aluguerelaxe.client.administracao.console.imovelpropriedade;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.componente.editpanel.AbstractGridCheckEditPanel;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.AdicionarRemoverToolbar;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.AdicionarRemoverToolbarListener;
import br.com.jcv.aluguerelaxe.client.imovel.tabelapreco.TabelaPrecoVO;

public class TabelaPrecoEditPanel extends AbstractGridCheckEditPanel<AdicionarRemoverToolbar, TabelaPrecoFormComposite, TabelaPrecoVO> 
		implements AdicionarRemoverToolbarListener {

	public TabelaPrecoEditPanel(AdicionarRemoverToolbar toolbar) {
		super(toolbar);
	}

	@Override
	public TabelaPrecoVO getVO(TabelaPrecoFormComposite composite) {
		return composite.getVO();
	}

	@Override
	public TabelaPrecoVO getVO(List<TabelaPrecoFormComposite> composite) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update() {
		// Nada a Fazer!
	}
	
	public void update(List<TabelaPrecoVO> l) {
		removeTodosObjetoCompositeFromPanel();
		if ((l != null) && 
			(l.size() > 0) ) {
			for (TabelaPrecoVO vo : l) {
				TabelaPrecoFormComposite tpfc = new TabelaPrecoFormComposite();
				tpfc.update(vo);
				addObjetoCompositeToPanel(tpfc);
			}
		}
	}

	public void onAdicionarClick() {
		addObjetoCompositeToPanel(new TabelaPrecoFormComposite());
	}

	public void onRemoverClick() {
		super.removeCompositeChecked();
	}

}
