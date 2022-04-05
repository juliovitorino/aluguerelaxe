package br.com.jcv.aluguerelaxe.client.destino;

import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.listbox.CidadesListBox;
import br.com.jcv.aluguerelaxe.client.componente.listbox.CidadesListComImoveisBox;
import br.com.jcv.aluguerelaxe.client.componente.listbox.UFListBox;
import br.com.jcv.aluguerelaxe.client.imovel.listagem.uf.ListaImovelUF;
import br.com.jcv.aluguerelaxe.client.vo.CidadeUFVO;
import br.com.jcv.aluguerelaxe.client.vo.EnderecoVO;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class DestinoFormComposite extends FormComposite<EnderecoVO> {

	private static final String GWT_STYLE = "gwt-DestinoFormComposite";
	private static final String PATH_IMAGENS = "images/48x48/";

	
	private UFListBox txtUf;
	private CidadesListComImoveisBox txtCidade;
	private Image imgBuscar;

	public DestinoFormComposite() {
		super();
		this.setStyleName(GWT_STYLE);
	}

	public static native void redirect(String url)/*-{
	      $wnd.location = url;
	  }-*/;

	@Override
	public void init() {
		txtUf = new UFListBox(false);
		txtCidade = new CidadesListComImoveisBox(true);
		imgBuscar = new Image("images/btn_busca.png");
		imgBuscar.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				EnderecoVO vo = DestinoFormComposite.this.getVO();
				if (vo.codigoUfCidadeItem == -1){
					redirect("/arweb/JXControllerSmartInterface?cmd=dtgListaImovelUf&atvd="+ ListaImovelUF.COMANDO_LISTA_PAGINADA_UF + "&uf="+vo.uf+"&cidade=-1");
				} else {
					
					DestinoRPCAsync rpc = ServicosRPC.getDestinoRPC();
					AsyncCallback<CidadeUFVO> callback = new AsyncCallback<CidadeUFVO>(){
						public void onSuccess(CidadeUFVO result) {
							if (result!= null) {
								redirect("/arweb/JXControllerSmartInterface?cmd=dtgListaImovelUf&atvd="+ ListaImovelUF.COMANDO_LISTA_PAGINADA_CIDADE + "&uf=XX&cidade=" + result.cidade.id);
							}
						}
						public void onFailure(Throwable caught) {
							// TODO fazer tratamento dos retornos de falhas
						}
					};
					rpc.ProcurarUFCidadeItem(vo.codigoUfCidadeItem, callback);	
					
				}
				
			}
		});
	}
	
	@Override
	public Widget render() {
		DockPanel dp = new DockPanel();
		
		VerticalPanel vp = new VerticalPanel();
		Grid grid = new Grid(2,2);
		
		// Monta os labels
		Label lblCidade = new Label("Cidade:");
		Label lblUf = new Label("UF:");
		
		// Adiciona evento onChange() na lista de UF para
		// carregar a lista de cidades relacionada da UF
		txtUf.addChangeHandler(txtCidade);
		
		// Posiciona campos no grid
		int linha = -1;
		
		grid.setWidget(++linha, 0, lblUf);
		grid.setWidget(linha, 1, txtUf);

		grid.setWidget(++linha, 0, lblCidade);
		grid.setWidget(linha, 1, txtCidade);

		// Adiciona a grid ao VerticalPanel
		vp.add(grid);
		
		dp.add(new Image(PATH_IMAGENS + "schoolboy.png"), DockPanel.WEST);
		dp.add(vp,DockPanel.CENTER);
		dp.add(imgBuscar, DockPanel.SOUTH);
		
		return dp;
	}

	public UFListBox getTxtUf() {
		return txtUf;
	}

	public void setTxtUf(UFListBox txtUf) {
		this.txtUf = txtUf;
	}

	public CidadesListComImoveisBox getTxtCidade() {
		return txtCidade;
	}

	public void setTxtCidade(CidadesListComImoveisBox txtCidade) {
		this.txtCidade = txtCidade;
	}

	public EnderecoVO getVO() {
		EnderecoVO endvo = new EnderecoVO();
		try{
			endvo.uf = getTxtUf().getValue(getTxtUf().getSelectedIndex());
			endvo.codigoUfCidadeItem = Long.valueOf(getTxtCidade().getValue(getTxtCidade().getSelectedIndex()));
		} catch (IndexOutOfBoundsException e) {
			endvo.codigoUfCidadeItem = -1;
		}
		return endvo;
	}
	
	public void update(EnderecoVO endvo) {
		
	}

	@Override
	public void clear() {
		
	}

	@Override
	public void notifier(EnderecoVO vo) {
		// TODO Auto-generated method stub
		
	}
	
}
