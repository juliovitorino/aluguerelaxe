package br.com.jcv.aluguerelaxe.client.imovel.ficha;

import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.notificacao.AreaNotificacao;
import br.com.jcv.aluguerelaxe.client.imovel.imagem.ImovelImagemVideoVO;

import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class VideoImovelFormComposite extends
		FormComposite<ImovelImagemVideoVO> {
	
	private HTML demovideo;
	private AreaNotificacao an;
	
	private String VIDEO_PROIBIDO = "O PLANO ESCOLHIDO POR ESTE ANUNCIANTE N\u00c3O PERMITE A APRESENTA\u00c7\u00c3O DE V\u00cdDEO.";

	@Override
	public ImovelImagemVideoVO getVO() {
		return null;
	}

	@Override
	public void update(ImovelImagemVideoVO vo) {
		if (vo == null){
			an.setMensagem(VIDEO_PROIBIDO, AreaNotificacao.NOTIFICACAO_WARNING);
			
		} else {
			String textoincorporar = "<object width=\"420\" height=\"315\"><param name=\"movie\" value=\"http://www.youtube.com/v/"+vo.nome+"?version=3&amp;hl=pt_BR\"></param><param name=\"allowFullScreen\" value=\"true\"></param><param name=\"allowscriptaccess\" value=\"always\"></param><embed src=\"http://www.youtube.com/v/"+vo.nome+"?version=3&amp;hl=pt_BR\" type=\"application/x-shockwave-flash\" width=\"420\" height=\"315\" allowscriptaccess=\"always\" allowfullscreen=\"true\"></embed></object>";
			this.demovideo.setHTML(textoincorporar);
		}
	}

	@Override
	public void notifier(ImovelImagemVideoVO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Widget render() {
		DockPanel dp = new DockPanel();
		VerticalPanel vpdemo = new VerticalPanel();
		
		vpdemo.add(demovideo);
		
		dp.add(an, DockPanel.NORTH);
		dp.add(vpdemo, DockPanel.CENTER);
		return dp;
	}

	@Override
	public void init() {
		demovideo = new HTML();
		an = new AreaNotificacao();
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

}
