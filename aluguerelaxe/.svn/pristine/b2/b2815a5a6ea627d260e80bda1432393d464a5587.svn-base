package br.com.jcv.aluguerelaxe.client.imovel.ficha;

import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class ObservacoesFormComposite extends
		FormComposite<ImovelFichaCompletaVO> {

	private HTML observacao;
	
	@Override
	public ImovelFichaCompletaVO getVO() {
		// Nao aplicado
		return null;
	}

	@Override
	public void update(ImovelFichaCompletaVO vo) {
		if (vo.imovel.observacoes.trim().length() == 0){
			observacao.setHTML("<p>Nenhuma Observa\u00e7\u00e3o registrada. </p>");
		} else {
			observacao.setHTML("<p>" + vo.imovel.observacoes + "</p>");
		}

	}

	@Override
	public Widget render() {
		return observacao;
	}

	@Override
	public void init() {
		observacao = new HTML();

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
