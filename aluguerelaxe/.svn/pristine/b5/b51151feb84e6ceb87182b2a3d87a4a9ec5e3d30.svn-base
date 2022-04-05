package br.com.jcv.aluguerelaxe.client.administracao.console.publicidade.compra;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.componente.editpanel.AbstractGridCheckEditPanel;

import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class ComprarPublicidadePeriodoEditPanel extends
		AbstractGridCheckEditPanel<ComprarPublicidadePeriodoToolbar, ComprarPublicidadePeriodoFormComposite, ComprarPublicidadePeriodoVO> 
			implements ComprarPublicidadePeriodoToolbarListener {

	private static final String PATH_IMAGEM = "images/48x48/";
	
	private HTML areaCalculoFinal = new HTML();
	
	public ComprarPublicidadePeriodoEditPanel(ComprarPublicidadePeriodoToolbar toolbar) {
		super(toolbar);
		super.adicionarRodape(montaAreaTotalizacao());
		this.onAdicionarClick();
		this.onCalcularPublicidadeClick();
	}

	private Widget montaAreaTotalizacao() {
		DockPanel dp = new DockPanel();
		
		dp.add(new Image(PATH_IMAGEM + "cashier.png"), DockPanel.WEST);
		dp.add(areaCalculoFinal, DockPanel.CENTER);
		return dp;
	}

	@Override
	public ComprarPublicidadePeriodoVO getVO(
			ComprarPublicidadePeriodoFormComposite composite) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComprarPublicidadePeriodoVO getVO(
			List<ComprarPublicidadePeriodoFormComposite> composite) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	public void onAdicionarClick() {
		addObjetoCompositeToPanel(new ComprarPublicidadePeriodoFormComposite());
	}

	public void onRemoverClick() {
		super.removeCompositeChecked();
	}

	public void onCalcularPublicidadeClick() {
		areaCalculoFinal.setHTML("<h1>R$ 0,00</h1>");
		
	}

}
