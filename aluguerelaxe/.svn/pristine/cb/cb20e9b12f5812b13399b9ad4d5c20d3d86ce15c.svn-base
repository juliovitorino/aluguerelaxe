package br.com.jcv.aluguerelaxe.client.administracao.console.imovelwizard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.imovel.ImovelPlanoVO;
import br.com.jcv.aluguerelaxe.client.plano.PlanoVO;
import br.com.jcv.aluguerelaxe.client.plano.PlanosRPCAsync;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class PlanoFormComposite extends FormComposite<ImovelPlanoVO> {
	private ListBox planos;
	private Label nome;
	private Label descricao;
	private Label valor;
	
	private Map<Integer, PlanoVO> mpPlanosInfo;

	@Override
	public ImovelPlanoVO getVO() {
		ImovelPlanoVO ipvo = new ImovelPlanoVO();
		ipvo.plano = new PlanoVO();
		ipvo.plano.id = Long.valueOf(planos.getValue(planos.getSelectedIndex()));
		
		return ipvo;
	}

	@Override
	public void update(ImovelPlanoVO vo) {
		//Nao aplicado neste contexto
	}

	private void update(PlanoVO pvo) {
		descricao.setText(pvo.descricao);
		valor.setText("R$ " + pvo.valorstr);
	}
	
	@Override
	public Widget render() {
		DockPanel dp = new DockPanel();
		VerticalPanel vp = new VerticalPanel();

		//-----------------------
		// Selecao do plano
		//-----------------------
		vp.add(new Label("Selecione o plano de pagamento"));
		vp.add(planos);
		dp.add(vp, DockPanel.WEST);

		// ---------------------------------------
		// Adicao das descricoes
		//----------------------------------------
		descricao.setStylePrimaryName("gwt-descricao-plano");
		valor.setStylePrimaryName("gwt-valor-plano");
		
		VerticalPanel vpInfo = new VerticalPanel();
		Label lbl = new Label("Por apenas:");
		lbl.setStylePrimaryName("gwt-descricao-plano");
		
		vpInfo.add(descricao);
		vpInfo.add(lbl);
		vpInfo.add(valor);
		
		dp.add(vpInfo, DockPanel.CENTER);
		
		//-----------------------
		// UOL Pagseguro
		//-----------------------
		/*
		HTML uolpagseguro= new HTML();
		String linkuol = "<!-- INICIO CODIGO PAGSEGURO --> " +
		"<center> " +
		"<a href='https://pagseguro.uol.com.br' target='_blank'><img alt='Logotipos de meios de pagamento do PagSeguro' src='https://p.simg.uol.com.br/out/pagseguro/i/banners/pagamento/todos_estatico_550_100.gif' title='Este site aceita pagamentos com Visa, MasterCard, Diners, American Express, Hipercard, Aura, Bradesco, Ita\u00fa, Banco do Brasil, Unibanco, Banrisul, saldo em conta PagSeguro e boleto.' border='0'></a> " +
		"</center> " +
		"<!-- FINAL CODIGO PAGSEGURO --> ";
		uolpagseguro.setHTML(linkuol);
		dp.add(uolpagseguro, DockPanel.SOUTH); */
		
		return dp;
	}

	@Override
	public void init() {
		mpPlanosInfo = new HashMap<Integer, PlanoVO>();
		planos = new ListBox();
		nome = new Label();
		descricao = new Label();
		valor = new Label();
		
		//---------------------------------------------
		// Carrega a combo de planos
		//---------------------------------------------
		carregaComboPlanos();

		//---------------------------------------------
		// Ativa evento ChangeHandler
		//---------------------------------------------
		planos.addChangeHandler(new ChangeHandler() {
			
			public void onChange(ChangeEvent event) {
				ListBox lb = (ListBox) event.getSource();
				Integer mapkey = Integer.valueOf(lb.getValue(lb.getSelectedIndex()));
				PlanoVO vo = PlanoFormComposite.this.mpPlanosInfo.get(mapkey);
				PlanoFormComposite.this.update(vo);
			}
		});
		
	}
	
	private void carregaComboPlanos() {
		PlanosRPCAsync rpc = ServicosRPC.getPlanosRPC();
		AsyncCallback<List<PlanoVO>> callback = new AsyncCallback<List<PlanoVO>>() {

			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			public void onSuccess(List<PlanoVO> result) {
				if(result != null){
					PlanoFormComposite.this.planos.clear();
					PlanoFormComposite.this.mpPlanosInfo.clear();
					for (PlanoVO pvo : result) {
						PlanoFormComposite.this.planos.addItem(pvo.nome, String.valueOf(pvo.id));
						PlanoFormComposite.this.mpPlanosInfo.put(Integer.valueOf(String.valueOf(pvo.id)), pvo);
					}
				}
			}
		};
		
		rpc.listarPlanosPorTipo("N", callback);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifier(ImovelPlanoVO vo) {
		// TODO Auto-generated method stub
		
	}

}
