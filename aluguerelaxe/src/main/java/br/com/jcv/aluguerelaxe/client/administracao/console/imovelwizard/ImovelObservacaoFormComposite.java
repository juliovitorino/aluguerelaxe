package br.com.jcv.aluguerelaxe.client.administracao.console.imovelwizard;

import br.com.jcv.aluguerelaxe.client.componente.autoajuda.AutoAjudaTextArea;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.imovel.ImovelVO;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;



public class ImovelObservacaoFormComposite extends FormComposite<ImovelVO> {
	
	private final static String AUTO_AJUDA_OBSERVACAO = "<B>Observa\u00e7\u00f5es Gerais</B>" + 
	"<p>Descreva qualquer observa\u00e7\u00e3o que ache necess\u00e1rio<br/>informar ao cliente na ficha do im\u00f3vel:<br/>Veja o exemplo abaixo:</p>" + 
	"<p>N\u00e3o aceitamos animais e tem cobran\u00e7a de tarifa de limpeza.</p>";

	private AutoAjudaTextArea taObs;
	
	public ImovelObservacaoFormComposite() {
		super();
		//this.setStylePrimaryName("gwt-ImovelObservacaoFormComposite");
	}

	public void init() {
		taObs = new AutoAjudaTextArea(ImovelObservacaoFormComposite.AUTO_AJUDA_OBSERVACAO);
	}
	
	public Widget render() {
		VerticalPanel vp = new VerticalPanel();
		Grid grid = new Grid(2,2);

		// Posiciona campos no grid
		int linha = -1;
		
		grid.setWidget(++linha, 0, new Label("Observa\u00e7\u00f5es"));
		grid.setWidget(linha, 1,taObs );

		// Adiciona a grid ao VerticalPanel
		vp.add(grid);
		
		return vp;
	}
	
	@Override
	public ImovelVO getVO() {
		ImovelVO vo = new ImovelVO();
		vo.observacoes = taObs.getWidgetUI().getValue();
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
