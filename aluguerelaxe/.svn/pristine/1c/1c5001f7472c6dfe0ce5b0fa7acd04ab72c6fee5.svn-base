package br.com.jcv.aluguerelaxe.client.componente.form;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.componente.listbox.CidadesListBox;
import br.com.jcv.aluguerelaxe.client.componente.listbox.LogradouroListBox;
import br.com.jcv.aluguerelaxe.client.componente.listbox.UFListBox;
import br.com.jcv.aluguerelaxe.client.componente.notificacao.AreaNotificacao;
import br.com.jcv.aluguerelaxe.client.vo.CepVO;
import br.com.jcv.aluguerelaxe.client.vo.EnderecoVO;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class EnderecoFormComposite extends FormComposite<EnderecoVO> {

	private static final String PATH_IMAGEM = "images/24x24/";
	
	private LogradouroListBox lstTipoLogradouro;
	private TextBox txtEndereco;
	private TextBox txtNumero;
	private TextBox txtComplemento;
	private TextBox txtBairro;
	private UFListBox txtUf;
	private CidadesListBox txtCidade;
	private TextBox txtCep;
	private AreaNotificacao an;

	public EnderecoFormComposite() {
		super();
	}

	@Override
	public void init() {
		lstTipoLogradouro = new LogradouroListBox();
		txtEndereco = new TextBox();
		txtNumero = new TextBox();
		txtComplemento = new TextBox();
		txtBairro = new TextBox();
		txtUf = new UFListBox(false);
		txtCidade = new CidadesListBox();
		txtCep = new TextBox();
		an = new AreaNotificacao();
	}
	
	@Override
	public Widget render() {
		VerticalPanel vp = new VerticalPanel();
		vp.add(an);
		Grid grid = new Grid(7,2);
		
		// Monta os labels
		Label lblEndereco = new Label("Endere\u00e7o:");
		Label lblNumero = new Label("No.:");
		Label lblComplemento = new Label("Complemento:");
		Label lblBairro = new Label("Bairro:");
		Label lblCidade = new Label("Cidade:");
		Label lblUf = new Label("UF:");
		Label lblCep = new Label("CEP:");
		
		// Configura tamanhos
		txtEndereco.setWidth("560px");
		txtNumero.setWidth("200px");
		txtComplemento.setWidth("400px");
		txtBairro.setWidth("400px");
		txtCep.setWidth("65px");

		
		// Adiciona evento onChange() na lista de UF para
		// carregar a lista de cidades relacionada da UF
		txtUf.addChangeHandler(txtCidade);
		
		// Agrupa alguns campos de endereco
		HorizontalPanel hpEndereco = new HorizontalPanel();
		hpEndereco.add(lstTipoLogradouro);
		hpEndereco.add(txtEndereco);
		
		// Agrupa Cep para permitir busca
		HorizontalPanel hpcep = new HorizontalPanel();
		hpcep.add(txtCep);
		Image btnBuscacep = new Image(PATH_IMAGEM + "replace.png");
		btnBuscacep.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				CepRPCAsync rpc = ServicosRPC.getCepRPC();
				AsyncCallback<CepVO> callback = new AsyncCallback<CepVO>() {

					public void onFailure(Throwable caught) {
					     try {
						       throw caught;
						     } catch (IncompatibleRemoteServiceException e) {
								an.setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
						     } catch (InvocationException e) {
								an.setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
						     } catch (AlugueRelaxeFrontException e) {
						    	 if ((e.getListaErros() != null) && (e.getListaErros().size() > 0)) {
						    		 an.setMensagem(e.getListaErros(), AreaNotificacao.NOTIFICACAO_ERRO);
						    	 } else {
						    		 an.setMensagem(e.getMensagem(), AreaNotificacao.NOTIFICACAO_ERRO);
						    	 }
						     } catch (Throwable e) {
								an.setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
						     }
					}

					public void onSuccess(CepVO result) {
						if (result != null){
							txtBairro.setText(result.endereco.bairro);
							lstTipoLogradouro.setSelectedIndex(lstTipoLogradouro.getSelectedItemText(result.endereco.logradouro));
							txtEndereco.setText(result.endereco.nome);
							txtUf.setSelectedIndex(txtUf.getSelectedItemValue(result.endereco.uf));
							txtCidade.onChange(txtUf,result.endereco.cidade);

						}
						
					}
				};
				try {
					rpc.buscaEndereco(txtCep.getValue(), callback);
				} catch (AlugueRelaxeFrontException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		hpcep.add(btnBuscacep);
		
		// Posiciona campos no grid
		int linha = -1;
		grid.setWidget(++linha, 0, lblCep);
		grid.setWidget(linha, 1, hpcep);
		
		grid.setWidget(++linha, 0, lblEndereco);
		grid.setWidget(linha, 1, hpEndereco);
		
		grid.setWidget(++linha, 0, lblNumero);
		grid.setWidget(linha, 1, txtNumero);
		
		grid.setWidget(++linha, 0, lblComplemento);
		grid.setWidget(linha, 1, txtComplemento);

		grid.setWidget(++linha, 0, lblBairro);
		grid.setWidget(linha, 1, txtBairro);

		grid.setWidget(++linha, 0, lblUf);
		grid.setWidget(linha, 1, txtUf);

		grid.setWidget(++linha, 0, lblCidade);
		grid.setWidget(linha, 1, txtCidade);

		// Adiciona a grid ao VerticalPanel
		vp.add(grid);
		
		return vp;
	}

	public LogradouroListBox getLstTipoLogradouro() {
		return lstTipoLogradouro;
	}

	public void setLstTipoLogradouro(LogradouroListBox lstTipoLogradouro) {
		this.lstTipoLogradouro = lstTipoLogradouro;
	}

	public TextBox getTxtEndereco() {
		return txtEndereco;
	}

	public void setTxtEndereco(TextBox txtEndereco) {
		this.txtEndereco = txtEndereco;
	}

	public TextBox getTxtNumero() {
		return txtNumero;
	}

	public void setTxtNumero(TextBox txtNumero) {
		this.txtNumero = txtNumero;
	}

	public TextBox getTxtComplemento() {
		return txtComplemento;
	}

	public void setTxtComplemento(TextBox txtComplemento) {
		this.txtComplemento = txtComplemento;
	}

	public TextBox getTxtBairro() {
		return txtBairro;
	}

	public void setTxtBairro(TextBox txtBairro) {
		this.txtBairro = txtBairro;
	}

	public UFListBox getTxtUf() {
		return txtUf;
	}

	public void setTxtUf(UFListBox txtUf) {
		this.txtUf = txtUf;
	}

	public CidadesListBox getTxtCidade() {
		return txtCidade;
	}

	public void setTxtCidade(CidadesListBox txtCidade) {
		this.txtCidade = txtCidade;
	}

	public TextBox getTxtCep() {
		return txtCep;
	}

	public void setTxtCep(TextBox txtCep) {
		this.txtCep = txtCep;
	}

	public EnderecoVO getVO() {
		EnderecoVO endvo = new EnderecoVO();
		endvo.bairro = getTxtBairro().getValue();
		endvo.cep = getTxtCep().getValue();
		try{
			endvo.codigoUfCidadeItem = Long.valueOf(getTxtCidade().getValue(getTxtCidade().getSelectedIndex()));
		} catch (IndexOutOfBoundsException e) {
			endvo.codigoUfCidadeItem = -1;
		}
		endvo.complemento = getTxtComplemento().getValue();
		endvo.logradouro = getLstTipoLogradouro().getItemText(getLstTipoLogradouro().getSelectedIndex());
		endvo.nome = getTxtEndereco().getValue();
		endvo.numero = getTxtNumero().getValue();
		return endvo;
	}
	
	public void update(EnderecoVO endvo) {
		getLstTipoLogradouro().setSelectedIndex(getLstTipoLogradouro().getSelectedItemText(endvo.logradouro));
		getTxtUf().setSelectedIndex(getTxtUf().getSelectedItemValue(endvo.uf));
		getTxtCidade().clear();
		txtCidade.onChange(txtUf, endvo.cidade);
		//getTxtCidade().addItem(endvo.cidade, String.valueOf(endvo.codigoUfCidadeItem));
		getTxtEndereco().setText(endvo.nome);
		getTxtNumero().setText(endvo.numero);
		getTxtComplemento().setText(endvo.complemento);
		getTxtBairro().setText(endvo.bairro);
		getTxtCep().setText(endvo.cep);
	}

	@Override
	public void clear() {
		getLstTipoLogradouro().setSelectedIndex(0);
		getTxtUf().setSelectedIndex(0);
		getTxtCidade().clear();
		getTxtEndereco().setText("");
		getTxtNumero().setText("");
		getTxtComplemento().setText("");
		getTxtBairro().setText("");
		getTxtCep().setText("");
	}

	@Override
	public void notifier(EnderecoVO vo) {
		// TODO Auto-generated method stub
		
	}
	
}
