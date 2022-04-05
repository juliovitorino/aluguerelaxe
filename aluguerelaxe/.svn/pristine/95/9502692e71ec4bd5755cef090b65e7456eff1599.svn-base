package br.com.jcv.aluguerelaxe.client.componente.toolbar;

import br.com.jcv.aluguerelaxe.client.componente.wizard.AbstractWizard;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * Toolbar para classes concretas baseadas em {@link AbstractWizard}
 *
 * @author Julio Vitorino
 *
 */
public class WizardToolbar extends AbstractToolbar<WizardToolbarListener> {

	@Override
	public Widget render() {
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(montaLinkAnterior());
		hp.add(montaLinkProximo());
		hp.add(montaLinkConcluir());
		return hp;
	}

	private Widget montaLinkConcluir() {
		Label lbl = new Label("Concluir");
		lbl.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (WizardToolbarListener item : listeners) {
						item.onConcluirClick();
					}
				}
			}
		});
		return lbl;
	}

	private Widget montaLinkAnterior() {
		Label lbl = new Label("Anterior");
		lbl.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (WizardToolbarListener item : listeners) {
						item.onAnteriorClick();
					}
				}
			}
		});
		return lbl;
	}

	private Widget montaLinkProximo() {
		Label lbl = new Label("Próximo");
		lbl.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (listeners != null) {
					for (WizardToolbarListener item : listeners) {
						item.onProximoClick();
					}
				}
			}
		});
		return lbl;
	}
	
}

