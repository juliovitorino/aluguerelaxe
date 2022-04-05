package br.com.jcv.aluguerelaxe.client.administracao.console.imovelwizard;

import br.com.jcv.aluguerelaxe.client.componente.autoajuda.AutoAjudaTextArea;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.imovel.ImovelVO;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;



public class ImovelTituloFormComposite extends FormComposite<ImovelVO> {
	
	private final static String AUTO_AJUDA_DESCRICAO_TITULO_ANUNCIO = "<B>T\u00edtulo do An\u00fancio</B>" + 
	"<p>Forne\u00e7a uma descri\u00e7\u00e3o impressionante do im\u00f3vel.<br/>Veja o exemplo abaixo:</p>" + 
	"<p>FANTASTICOS APARTAMENTOS MOBILIADOS EM PORTO SEGURO</p>";

	private AutoAjudaTextArea taDescricaoTituloAnuncio;

	public ImovelTituloFormComposite() {
		super();
		//this.setStylePrimaryName("gwt-ImovelTituloFormComposite");

	}

	public void init() {
		taDescricaoTituloAnuncio = new AutoAjudaTextArea(ImovelTituloFormComposite.AUTO_AJUDA_DESCRICAO_TITULO_ANUNCIO);
	}
	
	public Widget render() {
		VerticalPanel vp = new VerticalPanel();
		Grid grid = new Grid(2,2);

		// Posiciona campos no grid
		int linha = -1;
		
		grid.setWidget(++linha, 0, new Label("Qual ser\u00e1 o T\u00edtulo do An\u00fancio do Im\u00f3vel"));
		grid.setWidget(linha, 1, taDescricaoTituloAnuncio);

		// Adiciona a grid ao VerticalPanel
		vp.add(grid);
		
		return vp;
	}
	
	@Override
	public ImovelVO getVO() {
		ImovelVO vo = new ImovelVO();
		vo.descricaoTituloAnuncio = taDescricaoTituloAnuncio.getWidgetUI().getValue();
		return vo;
	}
	
	@Override
	public void update(ImovelVO vo) {
		//Não aplicável neste contexto
	}

	@Override
	public void clear() {
		//Não aplicável neste contexto
	}

	@Override
	public void notifier(ImovelVO vo) {
		// TODO Auto-generated method stub
		
	}

}
