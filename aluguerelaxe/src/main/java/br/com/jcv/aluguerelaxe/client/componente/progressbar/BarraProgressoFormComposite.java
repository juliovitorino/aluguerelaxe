package br.com.jcv.aluguerelaxe.client.componente.progressbar;

import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public class BarraProgressoFormComposite extends FormComposite<BarraProgressoVO> {

	private HTML html;
	
	public BarraProgressoFormComposite() {
		super();
	}

	@Override
	public void init() {
		html = new HTML();
	}
	
	@Override
	public Widget render() {
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(html);
		return hp;
	}

	@Override
	public void clear() {
	}
	
	@Override
	public BarraProgressoVO getVO() {
		return null;
	}

	@Override
	public void update(BarraProgressoVO vo) {
		// pid - é enviado dento do VO pelo botao submit do formulario de upload (experiencia 1)

		// pid - igual explicacao acima, a a url do src chamaria um modulo do GWT para apresentar componente VUMeter (experiencia 2)
	
		// PRODUCAO
		String strHtml = "<iframe src='/arweb/barraprogresso.svlt?pid=" + vo.pid + "' frameborder=\"0\" scrolling=\"no\" width=\"500\" height=\"180\" marginwidth=\"5\" marginheight=\"5\" ></iframe>";

		// ATENCAO: USE A LINHA DE BAIXO QUANDO HOSTED MODE ATIVO
		//String strHtml = "<iframe src='/barraprogresso.svlt?pid=" + vo.pid + "' frameborder=\"0\" scrolling=\"no\" width=\"500\" height=\"180\" marginwidth=\"5\" marginheight=\"5\" ></iframe>";
		
		html.setHTML(strHtml);
	}

	@Override
	public void notifier(BarraProgressoVO vo) {
		
	}

}