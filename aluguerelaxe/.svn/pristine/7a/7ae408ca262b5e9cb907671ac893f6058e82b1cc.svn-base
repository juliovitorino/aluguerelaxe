package br.com.jcv.aluguerelaxe.client.administracao.console.imovelpropriedade;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.Widget;

import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.foto.FotoMICheckbox;
import br.com.jcv.aluguerelaxe.client.componente.grid.GridLadoLadoFotoMI;
import br.com.jcv.aluguerelaxe.client.imovel.imagem.ImovelImagemVideoVO;

public class GaleriaLadoLadoFotoMIFormComposite extends FormComposite<ImovelImagemVideoVO> {
	private GridLadoLadoFotoMI grdFotoMI;
	
	public GaleriaLadoLadoFotoMIFormComposite(List<ImovelImagemVideoVO> lst) {
		List<FotoMICheckbox> lstFotoMI = montaListaFotoMI(lst);
		
		grdFotoMI.update(8, lstFotoMI);
	}
	
	public List<FotoMICheckbox> getListWidgets() {
		return grdFotoMI.getListWidgets();
	}

	private List<FotoMICheckbox> montaListaFotoMI(List<ImovelImagemVideoVO> lst) {
		List<FotoMICheckbox> lstFotoMI = new ArrayList<FotoMICheckbox>();
		if (lst.size() > 0) {
			for (ImovelImagemVideoVO iivvo : lst) {
				FotoMICheckbox foto = new FotoMICheckbox(iivvo);
				lstFotoMI.add(foto);
			}
		}
		return lstFotoMI;
	}

	@Override
	public ImovelImagemVideoVO getVO() {
		return null;
	}

	@Override
	public void update(ImovelImagemVideoVO vo) {
		// Nada a fazer!
	}
	
	public void update(List<ImovelImagemVideoVO> lst) {
	}

	@Override
	public void clear() {
		grdFotoMI.clear();
	}

	@Override
	public Widget render() {
		return grdFotoMI;
	}

	@Override
	public void init() {
		grdFotoMI = new GridLadoLadoFotoMI(8, null);
	}

	@Override
	public void notifier(ImovelImagemVideoVO vo) {
		// TODO Auto-generated method stub
		
	}
}
