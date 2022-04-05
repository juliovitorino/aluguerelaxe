package br.com.jcv.aluguerelaxe.client.administracao.console.videos;

import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.imovel.imagem.ImovelImagemVideoVO;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class PublicacaoVideoFormComposite extends
		FormComposite<ImovelImagemVideoVO> {
	
	private TextBox urlyoutube;
	private HTML demovideo;

	@Override
	public ImovelImagemVideoVO getVO() {
		ImovelImagemVideoVO vo = new ImovelImagemVideoVO();
		vo.nome = urlyoutube.getValue();
		return vo;
	}

	@Override
	public void update(ImovelImagemVideoVO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifier(ImovelImagemVideoVO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Widget render() {
		DockPanel dp = new DockPanel();
		VerticalPanel vp = new VerticalPanel();
		VerticalPanel vpdemo = new VerticalPanel();

		
		vp.add(new Label("Copie e cole c\u00f3digo do seu v\u00eddeo informado pelo youtube:"));
		vp.add(urlyoutube);
		
		Button btnplay = new Button("Play V\u00eddeo");
		btnplay.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				
				String textoincorporar = "<object width=\"420\" height=\"315\"><param name=\"movie\" value=\"http://www.youtube.com/v/"+urlyoutube.getValue()+"?version=3&amp;hl=pt_BR\"></param><param name=\"allowFullScreen\" value=\"true\"></param><param name=\"allowscriptaccess\" value=\"always\"></param><embed src=\"http://www.youtube.com/v/"+urlyoutube.getValue()+"?version=3&amp;hl=pt_BR\" type=\"application/x-shockwave-flash\" width=\"420\" height=\"315\" allowscriptaccess=\"always\" allowfullscreen=\"true\"></embed></object>";
				PublicacaoVideoFormComposite.this.demovideo.setHTML(textoincorporar);
			}});
		vp.add(btnplay);
		
		vpdemo.add(demovideo);
		
		dp.add(vp, DockPanel.WEST);
		dp.add(vpdemo, DockPanel.CENTER);
		return dp;
	}

	@Override
	public void init() {
		urlyoutube = new TextBox();
		demovideo = new HTML();
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

}
