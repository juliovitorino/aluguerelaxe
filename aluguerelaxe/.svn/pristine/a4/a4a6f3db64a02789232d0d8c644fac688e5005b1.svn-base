package br.com.jcv.aluguerelaxe.client.administracao.console.geolocalizacaowizard;

import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class GeoLocalizacaoFormComposite extends
		FormComposite<ImovelFichaCompletaVO> {

	private ImovelFichaCompletaVO ifcvo;
	private TextBox ll;
	
	@Override
	public ImovelFichaCompletaVO getVO() {
		GeoLocalizacaoVO glvo = new GeoLocalizacaoVO();
		
		String gmc = ll.getValue();
		int i = gmc.indexOf(",");
		if (i > 0){
			try{
				glvo.latitude = Double.valueOf(gmc.substring(0, i));
				glvo.longitude = Double.valueOf(gmc.substring(i+1, gmc.length()));
			} catch(NumberFormatException e){
				glvo.latitude = -23.725012;
				glvo.longitude = -115.488281;
			}
		} else {
			glvo.latitude = -23.725012;
			glvo.longitude = -115.488281;
		}
		ifcvo.geolocalizacao = glvo;
		return ifcvo;
	}

	@Override
	public void update(ImovelFichaCompletaVO vo) {
		this.ifcvo = vo;
	}

	@Override
	public Widget render() {
		VerticalPanel vp = new VerticalPanel();
		vp.add(new Label("Copie aqui as coordenadas informadas pelo Google Maps"));
		vp.add(ll);
		return vp;
	}

	@Override
	public void init() {
		ll = new TextBox();
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
