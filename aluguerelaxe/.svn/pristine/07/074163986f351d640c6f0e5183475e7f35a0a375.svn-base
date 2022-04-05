package br.com.jcv.aluguerelaxe.client.imovel.ficha;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.imovel.caracteristica.ImovelCaracteristicaVO;

public class CaracteristicaConfiguradaFormComposite extends
		FormComposite<ImovelFichaCompletaVO> {

	private static final String IMAGES_CHECK_PNG = "images/check.png";

	Grid gridCaracteristica;
	
	@Override
	public ImovelFichaCompletaVO getVO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(ImovelFichaCompletaVO vo) {
		// TODO Auto-generated method stub

	}

	public void update(ImovelFichaCompletaVO vo, String tipo) {
		if (tipo.equals("I")) {
			if (vo.caracteristicaImovel != null){
				int j = 0;
				int linha = 1;
				for (int i = 0; i < vo.caracteristicaImovel.size(); i++){
					ImovelCaracteristicaVO icvo = (ImovelCaracteristicaVO) vo.caracteristicaImovel.get(i);
					
					Label lblcaracteristica = new Label(icvo.caracteristica.nome);
					
					HorizontalPanel hpCelula = new HorizontalPanel();
					hpCelula.add(new Image(IMAGES_CHECK_PNG));
					hpCelula.add(lblcaracteristica);
					gridCaracteristica.setWidget(linha, j++, hpCelula);
					if (j > 4) {
						j = 0;
						gridCaracteristica.resizeRows(++linha + 1);
					}
				}
			}
		} else {
			if (vo.caracteristicaCondominio != null){
				int j = 0;
				int linha = 1;
				for (int i = 0; i < vo.caracteristicaCondominio.size(); i++){
					ImovelCaracteristicaVO icvo = (ImovelCaracteristicaVO) vo.caracteristicaCondominio.get(i);
					
					Label lblcaracteristica = new Label(icvo.caracteristica.nome);
					
					HorizontalPanel hpCelula = new HorizontalPanel();
					hpCelula.add(new Image(IMAGES_CHECK_PNG));
					hpCelula.add(lblcaracteristica);
					gridCaracteristica.setWidget(linha, j++, hpCelula);
					if (j > 4) {
						j = 0;
						gridCaracteristica.resizeRows(++linha + 1);
					}
				}
			}
		}
	}

	@Override
	public Widget render() {
		return gridCaracteristica;
	}

	@Override
	public void init() {
		gridCaracteristica = new Grid(2,5);
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
