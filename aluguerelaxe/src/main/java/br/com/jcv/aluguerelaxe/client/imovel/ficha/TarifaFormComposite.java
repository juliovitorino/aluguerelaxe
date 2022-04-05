package br.com.jcv.aluguerelaxe.client.imovel.ficha;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.imovel.tabelapreco.TabelaPrecoVO;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class TarifaFormComposite extends FormComposite<ImovelFichaCompletaVO> {

	private TabelaPrecoReadOnlyGridDataEntry tprogde;
	private Label lblSemTarifaInformada;

	@Override
	public ImovelFichaCompletaVO getVO() {
		// Nao aplicavel no contexto
		return null;
	}

	private void update(boolean isMostrarTabela, List<TabelaPrecoVO> lst) {
		if ( (lst == null) || 
			 (! isMostrarTabela) ){
			lblSemTarifaInformada.setVisible(true);
		} else {
			tprogde.setVisible(true);
			tprogde.update(lst);
		}
	}

	@Override
	public void update(ImovelFichaCompletaVO vo) {
		this.update( (vo.imovel.indicadorMostraTabelaPreco.equals("S")), vo.tabelaPreco);
	}

	@Override
	public Widget render() {
		VerticalPanel vp = new VerticalPanel();
		vp.add(tprogde);
		vp.add(lblSemTarifaInformada);
		return vp;
	}

	@Override
	public void init() {
		tprogde = new TabelaPrecoReadOnlyGridDataEntry();
		lblSemTarifaInformada = new Label("Consulte o propriet\u00e1rio para maiores informa\u00e7\u00f5es sobre tarifas.");
		tprogde.setVisible(false);
		lblSemTarifaInformada.setVisible(false);
	}

	@Override
	public void clear() {
		// Nao aplicavel no contexto
	}

	@Override
	public void notifier(ImovelFichaCompletaVO vo) {
		// TODO Auto-generated method stub
		
	}

}
