package br.com.jcv.aluguerelaxe.client.administracao.console.reserva.prereserva;

import br.com.jcv.aluguerelaxe.client.componente.autoajuda.AutoAjudaTextBox;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.imovel.ImovelVO;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ImovelReservaFormComposite extends
		FormComposite<ImovelFichaCompletaVO> {
	
	private final static String AUTO_AJUDA_IMOVEL = "<B>C\u00f3digo do Im\u00f3vel</B>" + 
	"<p>Digite o n\u00famero do CPF do propriet\u00e1rio da conta de im\u00f3vel.</p>" +
	"<p>Digite somente n\u00fameros no campo.</p>";
	
	private AutoAjudaTextBox codigoImovel;
	
	@Override
	public ImovelFichaCompletaVO getVO() {
		ImovelFichaCompletaVO vo = new ImovelFichaCompletaVO();
		vo.imovel = new ImovelVO();
		vo.imovel.id = Long.valueOf(codigoImovel.getWidgetUI().getValue());
		return vo;
	}

	@Override
	public void update(ImovelFichaCompletaVO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifier(ImovelFichaCompletaVO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Widget render() {
		VerticalPanel vp = new VerticalPanel();
		Grid grid = new Grid(1,2);
		
		// Posiciona campos no grid
		int linha = -1;

		grid.setWidget(++linha, 0, new Label("C\u00f3digo do Im\u00f3vel:"));
		grid.setWidget(linha, 1, codigoImovel);

		// Adiciona a grid ao VerticalPanel
		vp.add(grid);
		
		return vp;
	}

	@Override
	public void init() {
		codigoImovel = new AutoAjudaTextBox(AUTO_AJUDA_IMOVEL);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

}
