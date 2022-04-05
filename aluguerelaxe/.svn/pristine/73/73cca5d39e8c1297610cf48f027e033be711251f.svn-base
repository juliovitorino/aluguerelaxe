package br.com.jcv.aluguerelaxe.client.imovel.ficha;

import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class MaisDadosImovelFormComposite extends
		FormComposite<ImovelFichaCompletaVO> {

	private HTML permiteAlugarFesta;
	private HTML descricaoQuartos;
	private HTML descricaoArredores;
	
	@Override
	public ImovelFichaCompletaVO getVO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(ImovelFichaCompletaVO vo) {
		descricaoQuartos.setHTML("<p>" + vo.imovel.descricaoQuartos + "</p>");
		descricaoArredores.setHTML("<p>" + vo.imovel.descricaoArredores + "</p>");

	}

	@Override
	public Widget render() {
		VerticalPanel vp = new VerticalPanel();
		vp.add(new Label("Descri\u00e7\u00e3o dos Quartos:"));
		vp.add(descricaoQuartos);
		
		vp.add(new Label("Descri\u00e7\u00e3o dos Arredores:"));
		vp.add(descricaoArredores);
		
		return vp;
	}

	@Override
	public void init() {
		permiteAlugarFesta = new HTML();
		descricaoArredores = new HTML();
		descricaoQuartos = new HTML();

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
