package br.com.jcv.aluguerelaxe.client.administracao.console.publicidades;

import br.com.jcv.aluguerelaxe.client.DateParser;
import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.componente.chavevalor.ChaveValor;
import br.com.jcv.aluguerelaxe.client.componente.chavevalor.GrupoChaveValor;
import br.com.jcv.aluguerelaxe.client.componente.chavevalor.PropertiesGrupoChaveValor;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.form.FormCompositeListener;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;
import br.com.jcv.aluguerelaxe.client.plano.PlanoVO;
import br.com.jcv.aluguerelaxe.client.plano.PlanosRPCAsync;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ResumoCompraPublicidadeFormComposite extends FormComposite<VOPadrao> implements FormCompositeListener{
	private PropertiesGrupoChaveValor resumo;

	private ChaveValor cvPlanoNome;
	private ChaveValor cvPlanoDescricao;
	private ChaveValor cvValorUnitario;
	
	private ChaveValor cvTituloImovel;

	private ChaveValor cvDias;
	private ChaveValor cvValorFinal;
	private ChaveValor cvDataInicio;

	@Override
	public VOPadrao getVO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(VOPadrao vo) {
		// TODO Auto-generated method stub
		
	}
	
	public void update(ImovelFichaCompletaVO ifcvo, VendaPublicidadeVO vpvo){
		// Atualiza as informacoes do imovel e do plano
		update(ifcvo);
		update(vpvo);
	}
	
	private void update(VendaPublicidadeVO vpvo){

		((Label)cvDataInicio.getWidgetUi()).setText(DateParser.formatador(vpvo.dataInicio));

		// Busca informacoes atualizadas do plano
		PlanosRPCAsync rpc = ServicosRPC.getPlanosRPC();
		AsyncCallback<PlanoVO> callback = new AsyncCallback<PlanoVO>() {

			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			public void onSuccess(PlanoVO result) {
				ResumoCompraPublicidadeFormComposite.this.update(result);
				
			}
		};
		rpc.pesquisarPlano(vpvo.planoVendido.id, callback);
	}
	
	
	private void update(ImovelFichaCompletaVO ifcvo) {
		((Label)cvTituloImovel.getWidgetUi()).setText(ifcvo.imovel.descricaoTituloAnuncio);
	}

	private void update(PlanoVO pvo) {
		((Label)cvPlanoNome.getWidgetUi()).setText(pvo.nome);
		((Label)cvPlanoDescricao.getWidgetUi()).setText(pvo.descricao);
		((Label)cvValorUnitario.getWidgetUi()).setText(pvo.valorstr);
		((Label)cvDias.getWidgetUi()).setText(String.valueOf(pvo.numeroDiasCalculo));
		((Label)cvValorFinal.getWidgetUi()).setText(String.valueOf(pvo.numeroDiasCalculo * pvo.valor));
	}
	
	@Override
	public void notifier(VOPadrao vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Widget render() {
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(resumo);
		resumo.init();
		return hp;
	}

	@Override
	public void init() {
		
		// grupo com informacoes do plano
		GrupoChaveValor gcvPlano = new GrupoChaveValor("Plano");
		cvPlanoNome = new ChaveValor("Plano", new Label(),"Nome do plano na tabela de pre\u00e7os");
		cvPlanoDescricao = new ChaveValor("Descri\u00e7\u00e3o", new Label(),"Descri\u00e7\u00e3o do plano na tabela de pre\u00e7os");
		cvValorUnitario = new ChaveValor("Valor Unit\u00e1rio R$", new Label(),"Valor Unit\u00e1rio de cada publica\u00e7\u00e3o");
		gcvPlano.add(cvPlanoNome);
		gcvPlano.add(cvPlanoDescricao);
		gcvPlano.add(cvValorUnitario);
		
		// grupo com informacoes do imovel
		GrupoChaveValor gcvImovel = new GrupoChaveValor("Im\u00f3vel");
		cvTituloImovel = new ChaveValor("T\u00edtulo", new Label(), "T\u00edtulo do Im\u00f3vel");
		gcvImovel.add(cvTituloImovel);
		
		// grupo com informações da compra
		GrupoChaveValor gcvCompra = new GrupoChaveValor("Dados da Compra"); 
		cvDias = new ChaveValor("Dias comprados", new Label(), "Quantidade de dias comprados");;
		cvValorFinal = new ChaveValor("Valor a pagar R$", new Label(), "Valor a pagar pela publica\u00e7\u00e3o");;
		cvDataInicio = new ChaveValor("In\u00edcio da publica\u00e7\u00e3o", new Label(), "Valor a pagar pela publica\u00e7\u00e3o");;
		gcvCompra.add(cvDias);
		gcvCompra.add(cvDataInicio);
		gcvCompra.add(cvValorFinal);

		resumo = new PropertiesGrupoChaveValor();
		resumo.add(gcvPlano);
		resumo.add(gcvImovel);
		resumo.add(gcvCompra);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

}
