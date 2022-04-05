package br.com.jcv.aluguerelaxe.client.administracao.console.imovelpropriedade;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.componente.editpanel.AbstractFormEditPanel;
import br.com.jcv.aluguerelaxe.client.componente.notificacao.AreaNotificacao;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.AbstractToolbar;
import br.com.jcv.aluguerelaxe.client.imovel.imagem.ImovelImagemVideoVO;

public class GaleriaLadoLadoFotoMIEditPanel extends AbstractFormEditPanel<AbstractToolbar<?>, GaleriaLadoLadoFotoMIFormComposite, ImovelImagemVideoVO> 
	{
	
	public GaleriaLadoLadoFotoMIEditPanel(AbstractToolbar<?> toolbar) {
		super(toolbar);
	}

	@Override
	public ImovelImagemVideoVO getVO(
			GaleriaLadoLadoFotoMIFormComposite composite) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImovelImagemVideoVO getVO(
			List<GaleriaLadoLadoFotoMIFormComposite> composite) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	public void update(List<ImovelImagemVideoVO> lst) {
		removeTodosObjetoCompositeFromPanel();
		if (lst != null){
			addObjetoCompositeToPanel(new GaleriaLadoLadoFotoMIFormComposite(lst));
		}
	}

}
