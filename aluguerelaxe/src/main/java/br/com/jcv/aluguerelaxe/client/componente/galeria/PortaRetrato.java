package br.com.jcv.aluguerelaxe.client.componente.galeria;

import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class PortaRetrato extends Composite implements ClickHandler {
	
	private static final String PORTA_RETRATO_CSS_PRINCIPAL = "porta-retrato";
	
	/**
	 * Atributo que conterá a subscrição dos listeners para disparar o evento
	 * onClick
	 */
	List<PortaRetratoListener> listeners = new ArrayList<PortaRetratoListener>();
	
	private ImovelFichaCompletaVO ifcvo = null;
	private Label lbl = null;
	
	/**
	 * Construtor do Porta Retrato
	 * @param ifcvo - Ficha completa do imóvel que será encapsulada para uso do evento
	 * ClickHandler.
	 * @param clickHandler - Classe que implementa o evento ClickHandler e será
	 * responsável por dar o tratamento do objeto enviado <code>ImovelFichaCompletaVO</code>.
	 */
	public PortaRetrato(ImovelFichaCompletaVO ifcvo, PortaRetratoListener clickHandler) {
		this.ifcvo = ifcvo;
		this.addPortaRetratoListener(clickHandler);
		initWidget(montaPortaRetrato());
	}
	
	public void hideDescricaoPortaRetrato() {
		this.lbl.setVisible(false);
	}
	
	/**
	 * Adicionar uma instância de implementação de PortaRetratoListener
	 *
	 * @param prl Comentar aqui.
	 */
	public void addPortaRetratoListener(PortaRetratoListener prl) {
		listeners.add(prl);
	}
	
	private Widget montaPortaRetrato() {
		String urlimagem = "images/no-photo.jpg";
		if (ifcvo.imagensImovelTB != null){
			if (ifcvo.imagensImovelTB.size() > 0){
				urlimagem = ifcvo.imagensImovelTB.get(0).nome;
			}
		}
		
		VerticalPanel vpRetrato = new VerticalPanel();
		vpRetrato.setStylePrimaryName(PORTA_RETRATO_CSS_PRINCIPAL);
		
		Image img = new Image(urlimagem);
		img.setStylePrimaryName(PORTA_RETRATO_CSS_PRINCIPAL);
		img.addStyleName("gwt-image");
		img.addClickHandler(this);
		
		lbl = new Label(ifcvo.imovel.descricaoTituloAnuncio);
		lbl.setStylePrimaryName(PORTA_RETRATO_CSS_PRINCIPAL);
		lbl.addStyleName("gwt-label");
		lbl.addClickHandler(this);
		
		vpRetrato.add(img);
		vpRetrato.add(lbl);
		return vpRetrato;
	}
	
	/**
	 * Retorna a ficha do imóvel que foi previamente encapsulada no construtor
	 * @see PortaRetrato
	 * @return
	 */
	public ImovelFichaCompletaVO getFichaImovelPortaRetrato() {
		return this.ifcvo;
	}

	/**
	 * Envia um evento onClick para todos os listener {@link PortaRetratoListener}
	 * inscritos nesta instância de {@link PortaRetrato}
	 * @see com.google.gwt.event.dom.client.ClickHandler#onClick(com.google.gwt.event.dom.client.ClickEvent)
	 */
	public void onClick(ClickEvent event) {
		for (PortaRetratoListener prl : this.listeners) {
			prl.onPortaRetratoClick(this);
		}
	}

}

