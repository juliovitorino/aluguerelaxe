package br.com.jcv.aluguerelaxe.client.administracao.console.enquete;

import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.listbox.ModoPublicidadeListBox;
import br.com.jcv.aluguerelaxe.client.modopublicidade.ModoPublicidadeVO;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class EnqueteModoPublicidadeFormComposite extends
		FormComposite<ModoPublicidadeVO> {

	private static String GWT_STYLE = "gwt-EnqueteModoPublicidadeFormComposite";
	
	private HTML html;
	private ModoPublicidadeListBox mplb;
	
	public EnqueteModoPublicidadeFormComposite(){
		super();
		this.setStyleName(GWT_STYLE);
	}
	
	@Override
	public ModoPublicidadeVO getVO() {
		ModoPublicidadeVO vo = new ModoPublicidadeVO();
		vo.id = Integer.valueOf(mplb.getValue(mplb.getSelectedIndex()));
		return vo;
	}

	@Override
	public void update(ModoPublicidadeVO vo) {
	}

	@Override
	public void notifier(ModoPublicidadeVO vo) {
	}

	@Override
	public Widget render() {
		VerticalPanel vp = new VerticalPanel();
		vp.add(html);
		vp.add(mplb);
		return vp;
	}

	@Override
	public void init() {
		 mplb = new ModoPublicidadeListBox();
		 html = new HTML("Responda a enquete abaixo: Como voc\u00ea nos conheceu?");
	}

	@Override
	public void clear() {
	}

}
