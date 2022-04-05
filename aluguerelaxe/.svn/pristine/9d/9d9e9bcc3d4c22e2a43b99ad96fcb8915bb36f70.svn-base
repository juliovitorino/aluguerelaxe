package br.com.jcv.aluguerelaxe.client.administracao.console.publicidade.compra;

import br.com.jcv.aluguerelaxe.client.componente.toolbar.AdicionarRemoverToolbar;
import br.com.jcv.aluguerelaxe.client.componente.wizard.AbstractWizard;
import br.com.jcv.aluguerelaxe.client.componente.wizard.WizardListener;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.user.client.ui.Composite;

public class ComprarPublicidadeAssistente extends
		AbstractWizard<Composite, VOPadrao> implements WizardListener{

	private ComprarPublicidadeFormComposite cpfc;
	private ComprarPublicidadePeriodoEditPanel cppep;
	
	public ComprarPublicidadeAssistente() {
		super();
		this.setStylePrimaryName("gwt-ComprarPublicidadeAssistente");
		addWizardListener(this);
		addWizard(cpfc, montaHeaderPasso("house.png", "Bem vindo ao Assistente de Geo-Localiza\u00e7\u00e3o"));
		addWizard(cppep, montaHeaderPasso("house.png", "Comprar Períodos de Publicidade"));
		
		init();
	}
	
	@Override
	public VOPadrao getVO() {
		// TODO Auto-generated method stub
		return null;
	}

	public void onVoltarClick() {
		// TODO Auto-generated method stub
		
	}

	public void onProximoClick() {
		// TODO Auto-generated method stub
		
	}

	public void onConcluirClick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initComposites() {
		cpfc = new ComprarPublicidadeFormComposite();
		cppep = new ComprarPublicidadePeriodoEditPanel(new ComprarPublicidadePeriodoToolbar());
	}

}
