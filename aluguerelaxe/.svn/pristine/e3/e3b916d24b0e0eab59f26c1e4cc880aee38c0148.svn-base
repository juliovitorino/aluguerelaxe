package br.com.jcv.aluguerelaxe.client.componente.galeria;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;
import br.com.jcv.aluguerelaxe.client.interfaces.Navegador;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

/**
 * <h2>GaleriaImovel</h2>
 * <p>Componente responsável por montar a galeria de fotos dos imóveis do cliente.<p>
 * @author julio
 *
 */
public class GaleriaImovel extends Composite 
							implements AsyncCallback<List<ImovelFichaCompletaVO>>, 
										Navegador {
	
	private static final int MAXIMO_PORTA_RETRATOS = 5;
	private static final String PATH_IMAGENS = "images/16x16/";
	
	private List<ImovelFichaCompletaVO> lstImoveis = null;
	private DockPanel navegadorGaleria = new DockPanel();
	private HorizontalPanel mainfp = new HorizontalPanel();
	private Image imgPrev;
	private Image imgNext;
	private PortaRetratoListener clickHandler = null;
	private PortaRetrato[] portaRetrato;
	private int maxpr = 0;
	private int indice = 0;
	private boolean bAtivos = false;
	
	
	/**
	 * Construtor para criação da galeria.
	 * @param clienteId - Identificador do cliente que se deseja montar a galeria.
	 * @param PortaRetratoListener - Handler que será repassado para o porta retrato
	 * identificando a classe que estará implementando um evento <code>onClick</code>.
	 */
	public GaleriaImovel(long clienteId, PortaRetratoListener clickHandler){
		this(clienteId, clickHandler, MAXIMO_PORTA_RETRATOS, false);
	}

	/**
	 * Construtor para criação da galeria.
	 * @param clienteId - Identificador do cliente que se deseja montar a galeria.
	 * @param PortaRetratoListener - Handler que será repassado para o porta retrato
	 * identificando a classe que estará implementando um evento <code>onClick</code>.
	 */
	public GaleriaImovel(long clienteId, PortaRetratoListener clickHandler, int maxpr, boolean bImoveisAtivos){
		this.bAtivos = bImoveisAtivos;
		updateGaleria(clienteId);
		this.maxpr = maxpr;
		this.clickHandler = clickHandler;
		initWidget(montaNavegadorGaleria());
	}

	/**
	 * Construtor para criação da galeria.
	 * @param indPatrocinadorColaborador - Identificador do tipo de colaboração do imóvel
	 * @param PortaRetratoListener - Handler que será repassado para o porta retrato
	 * identificando a classe que estará implementando um evento <code>onClick</code>.
	 */
	public GaleriaImovel(String indPatrocinadorColaborador, PortaRetratoListener clickHandler, int maxpr, boolean bImoveisAtivos){
		this.bAtivos = bImoveisAtivos;
		updateGaleria(indPatrocinadorColaborador);
		this.maxpr = maxpr;
		this.clickHandler = clickHandler;
		initWidget(montaNavegadorGaleria());
	}

	private Widget montaNavegadorGaleria() {
		// Associa o style ao componente
		this.navegadorGaleria.setStylePrimaryName("galeria");
		this.mainfp.addStyleName("carrosel");
		
		// Cria as imagens e associa os eventos
		imgPrev = new Image(PATH_IMAGENS + "previous.png");
		imgNext = new Image(PATH_IMAGENS + "next.png");
		
		imgPrev.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				irParaAnterior();
			}
		});
		
		imgNext.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				irParaProximo();
			}
		});
		
		navegadorGaleria.add(mainfp, DockPanel.CENTER);
		navegadorGaleria.add(imgPrev, DockPanel.WEST);
		navegadorGaleria.add(imgNext, DockPanel.EAST);
		return navegadorGaleria;
	}

	private void updateGaleria(long clienteId) {
		GaleriaImovelRPCAsync rpc = ServicosRPC.getGaleriaImovelRPC();
		AsyncCallback<List<ImovelFichaCompletaVO>> callback = this;
		rpc.listarGaleriaImovel(clienteId, callback);
	}

	private void updateGaleria(String indPatrocinadorColaborador) {
		GaleriaImovelRPCAsync rpc = ServicosRPC.getGaleriaImovelRPC();
		AsyncCallback<List<ImovelFichaCompletaVO>> callback = this;
		rpc.listarGaleriaImovel(indPatrocinadorColaborador, callback);
	}


	public void onFailure(Throwable caught) {
		try {
			throw caught;
		} catch (IncompatibleRemoteServiceException e) {
			// this client is not compatible with the server; cleanup and refresh the browser
		} catch (InvocationException e) {
			// the call didn't complete cleanly
		} catch (AlugueRelaxeFrontException e) {
			// TODO: handle exception
		} catch(Throwable e) {
			// TODO Erro totalmente inesperado
		}
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.rpc.AsyncCallback#onSuccess(java.lang.Object)
	 */
	public void onSuccess(List<ImovelFichaCompletaVO> result) {
		this.lstImoveis = result;
		if (result != null){
			// Antes de popular a galeria, verifica se precisa ser montada somente com
			// a lista de imoveis ativos
			if (bAtivos){
				removeInativos();
			}
			popularGaleria(this.indice);
			if (result.size() <= maxpr) {
				GaleriaImovel.this.imgPrev.setVisible(false);
				GaleriaImovel.this.imgNext.setVisible(false);
			}
		} else {
			GaleriaImovel.this.mainfp.add(new HTML("<b>Nenhum im\u00f3vel cadastrado. Para incluir um novo utilize a op\u00e7\u00e3o Assistente Novo im\u00f3vel.</b>"));
			GaleriaImovel.this.imgPrev.setVisible(false);
			GaleriaImovel.this.imgNext.setVisible(false);
		}
	}

	private void removeInativos() {
		if ((this.lstImoveis != null) && (this.lstImoveis.size() > 0)){
			for (int i = this.lstImoveis.size()-1 ; i >= 0; i--){
				ImovelFichaCompletaVO vo =  this.lstImoveis.get(i);
				if (!vo.imovel.indicadorStatus.equals("A")) {
					this.lstImoveis.remove(i);
				}
			}
			
		}
		
	}

	/**
	 * Método de apoio para onSucess. Use <code>popularGaleria(int)</code> ao invés
	 * deste.
	 */
	@Deprecated
	private void popularGaleria() {
		for (ImovelFichaCompletaVO ifcvo : this.lstImoveis) {
			this.mainfp.add(new PortaRetrato(ifcvo, this.clickHandler));
		}
	}
	
	/**
	 * Executa o carrosel no painel da galeria de imagens de 3 em 3 imagens
	 *
	 * @param indice Posição atual da imagem que será rolada dentro da lista
	 * para o carrosel.
	 */
	private void popularGaleria(int indice) {
		if( (this.lstImoveis != null) || (this.lstImoveis.size() > 0) ) {
			removerWidgetFilhos();
			int idxPortaRetrato = 0;
			PortaRetrato[] portaRetrato = new PortaRetrato[maxpr];

			for (int i = indice; i < (indice + maxpr); i++, idxPortaRetrato++) {
				ImovelFichaCompletaVO ifcvo = this.lstImoveis.get(this.indice);
				portaRetrato[idxPortaRetrato] = new PortaRetrato(ifcvo, this.clickHandler);
				this.mainfp.add(portaRetrato[idxPortaRetrato]);
				this.indice++;
				if (this.indice > this.lstImoveis.size()-1) {
					this.indice--;
					break;
				}
			}
		}
	}

	private void removerWidgetFilhos() {
		int i = this.mainfp.getWidgetCount();
		if (i > 0){
			for (int j = i; j > 0; j--){
				this.mainfp.remove(j-1);
			}
		}
	}

	public void irParaAnterior() {
		if (this.indice > this.lstImoveis.size()) {
			this.indice = this.lstImoveis.size() - maxpr;
			popularGaleria(this.indice);
		} else {
			this.indice -= maxpr;
			if (this.indice < 0) {
				this.indice = 0;
				popularGaleria(this.indice);
			} else {
				int oldIndice = this.indice;
				popularGaleria(this.indice);
				this.indice = oldIndice;
			}
		}
	}

	public void irParaPrimeiro() {
	}

	public void irParaProximo() {
		if (this.indice > this.lstImoveis.size()) {
			this.indice = 0;
		} 
		popularGaleria(this.indice);
	}

	public void irParaUltimo() {
	}

}
