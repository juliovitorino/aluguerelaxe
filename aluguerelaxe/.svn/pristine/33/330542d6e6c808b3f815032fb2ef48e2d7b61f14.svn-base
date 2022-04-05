package br.com.jcv.aluguerelaxe.client.administracao.console.proprietario;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.cliente.TelefoneVO;
import br.com.jcv.aluguerelaxe.client.componente.dataentry.TipoTelefoneDataEntry;
import br.com.jcv.aluguerelaxe.client.componente.editpanel.AbstractGridCheckEditPanel;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.AdicionarRemoverToolbar;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.AdicionarRemoverToolbarListener;

public class TelefoneEditPanel extends AbstractGridCheckEditPanel<AdicionarRemoverToolbar, TipoTelefoneDataEntry, TelefoneVO>
		implements AdicionarRemoverToolbarListener {

	public TelefoneEditPanel(AdicionarRemoverToolbar toolbar) {
		super(toolbar);
	}

	@Override
	public TelefoneVO getVO(TipoTelefoneDataEntry composite) {
		TelefoneVO vo = new TelefoneVO();
		vo.indTipoTelefone = composite.getTipoTelefone();
		vo.ddd = composite.getTelefoneDataEntry().getDDD();
		vo.telefone = composite.getTelefoneDataEntry().getTelefone();
		// TODO colocar o dado sendo fornecido por um componente visual
		vo.indPermiteExibir = "S";
		return vo;
	}

	@Override
	public TelefoneVO getVO(List<TipoTelefoneDataEntry> composite) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update() {
		// Nada a fazer! :-)
		
	}
	
	public void update(List<TelefoneVO> l) {
		if ((l != null) && 
			(l.size() > 0) ) {
			for (TelefoneVO vo : l) {
				TipoTelefoneDataEntry ttde = new TipoTelefoneDataEntry();
				ttde.update(vo);
				addObjetoCompositeToPanel(ttde);
			}
		}
	}

	public void onAdicionarClick() {
		addObjetoCompositeToPanel(new TipoTelefoneDataEntry());
	}

	public void onRemoverClick() {
		super.removeCompositeChecked();
	}

}
