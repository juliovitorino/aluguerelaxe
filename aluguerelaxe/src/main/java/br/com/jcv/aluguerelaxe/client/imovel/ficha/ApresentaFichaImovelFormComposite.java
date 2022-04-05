package br.com.jcv.aluguerelaxe.client.imovel.ficha;

import br.com.jcv.aluguerelaxe.client.administracao.console.proprietario.PerfilClienteFormComposite;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.galeria.PortaRetrato;
import br.com.jcv.aluguerelaxe.client.componente.galeria.PortaRetratoListener;

import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ApresentaFichaImovelFormComposite extends
		FormComposite<ImovelFichaCompletaVO> implements PortaRetratoListener {
	
	private HTML tituloImovel;
	private HTML cidadeuf;
	private HTML descricaoGeral;
	private HTML qtdeQuartos;
	private HTML qtdeSuites;
	private HTML qtdeBanheiros;
	private HTML qtdeCapacidade;
	private HTML tipoImovel;
	private Grid gridCapacidades;
	private HTML imagemtb;
	private VerticalPanel vpPerfil;
	
	public ApresentaFichaImovelFormComposite(){
		this.setStylePrimaryName("gwt-ApresentaFichaImovelFormComposite");
	}

	@Override
	public ImovelFichaCompletaVO getVO() {
		// Não aplicado neste contexto
		return null;
	}

	@Override
	public void update(ImovelFichaCompletaVO vo) {
		PortaRetrato fototb = new PortaRetrato(vo, this);
		fototb.hideDescricaoPortaRetrato();
		StringBuilder sb = new StringBuilder();
		sb.append("<br/>" + fototb.toString());
		sb.append("<br/><br/>");
		sb.append("Di\u00e1ria Base");
		sb.append("<h1>R$&nbsp;" + vo.imovel.valorTarifaBasica + "</h1>");
		imagemtb.setHTML(sb.toString());
		tituloImovel.setHTML("<h2>Propriedade #" + vo.imovel.id + "<br/>" + vo.imovel.descricaoTituloAnuncio +"</h2>");
		cidadeuf.setHTML("<br/><strong>Local:</strong>" + vo.imovel.endereco.cidade + "/" + vo.imovel.endereco.uf );
		descricaoGeral.setHTML(vo.imovel.descricaoGeral );

		qtdeQuartos.setHTML(String.valueOf(vo.imovel.qtdeQuartos));
		qtdeSuites.setHTML(String.valueOf(vo.imovel.qtdeSuites));
		qtdeBanheiros.setHTML(String.valueOf(vo.imovel.qtdeBanheiros));
		qtdeCapacidade.setHTML(String.valueOf(vo.imovel.qtdeCapacidade));
		tipoImovel.setHTML(converteTipoImovel(vo.imovel.indicadorTipoPropriedade));

		PerfilClienteFormComposite pcfc = new PerfilClienteFormComposite();
		pcfc.update(vo.cliente);
		if (vpPerfil.getWidgetCount() > 0) {
			vpPerfil.remove(0);
		}
		vpPerfil.add(pcfc);
	}

	private String converteTipoImovel(String tp) {
		String retorno = "";
		if (tp.toUpperCase().equals("C")) {
			retorno = "Casa";
		} else if(tp.toUpperCase().equals("A")) {
			retorno = "Apartamento";
		} else if(tp.toUpperCase().equals("H")) {
			retorno = "Hotel";
		} else if(tp.toUpperCase().equals("X")) {
			retorno = "Ch\u00e1cara";
		} else if(tp.toUpperCase().equals("F")) {
			retorno = "Flat";
		} else if(tp.toUpperCase().equals("Z")) {
			retorno = "Fazenda";
		} else if(tp.toUpperCase().equals("S")) {
			retorno = "S\u00edtio";
		} else if(tp.toUpperCase().equals("L")) {
			retorno = "Chal\u00e9";
		} else if(tp.toUpperCase().equals("P")) {
			retorno = "Pousada";
		}
		return retorno;
	}

	@Override
	public Widget render() {
		DockPanel dp = new DockPanel();
		VerticalPanel vp = new VerticalPanel();
		vp.add(cidadeuf);
		vp.add(new Label("Descri\u00e7\u00e3o Geral"));
		vp.add(descricaoGeral);
		//vp.add(recomendarfb);
		
		HorizontalPanel vpq = new HorizontalPanel();
		vpq.setStylePrimaryName("gwt-QuadroQuantidade");
		vpq.add(gridCapacidades);
		int i = 0;
		gridCapacidades.setWidget(0,i++,new Label("Tipo"));
		gridCapacidades.setWidget(0,i++,new Label("Quarto"));
		gridCapacidades.setWidget(0,i++,new Label("Banheiro"));
		gridCapacidades.setWidget(0,i++,new Label("Su\u00edte"));
		gridCapacidades.setWidget(0,i++,new Label("Capacidade"));
		i = 0;
		gridCapacidades.setWidget(1,i++,tipoImovel);
		gridCapacidades.setWidget(1,i++,qtdeQuartos);
		gridCapacidades.setWidget(1,i++,qtdeBanheiros);
		gridCapacidades.setWidget(1,i++,qtdeSuites);
		gridCapacidades.setWidget(1,i++,qtdeCapacidade);
/*		
		vpq.add(tipoImovel);
		vpq.add(qtdeQuartos);
		vpq.add(qtdeBanheiros);
		vpq.add(qtdeSuites);
		vpq.add(qtdeCapacidade);
*/		
		
		VerticalPanel vpInfoImovel = new VerticalPanel();
		vpInfoImovel.add(vp);
		vpInfoImovel.add(vpq);
		
		dp.add(tituloImovel, DockPanel.NORTH);
		dp.add(vpInfoImovel, DockPanel.CENTER);
		dp.add(this.vpPerfil, DockPanel.EAST);
		dp.add(imagemtb, DockPanel.WEST);
		return dp;
	}

	@Override
	public void init() {
		tituloImovel = new HTML();
		cidadeuf = new HTML();
		descricaoGeral = new HTML();
		qtdeQuartos = new HTML();
		qtdeSuites = new HTML();
		qtdeBanheiros = new HTML();
		qtdeCapacidade = new HTML();
		tipoImovel = new HTML();
		imagemtb = new HTML();
		vpPerfil = new VerticalPanel();
		gridCapacidades = new Grid(2,5);

	}

	@Override
	public void clear() {
		// Nao aplicado neste contexto
	}

	public void onPortaRetratoClick(PortaRetrato portaRetrato) {
		// Nao aplicado neste contexto
		
	}

	@Override
	public void notifier(ImovelFichaCompletaVO vo) {
		// TODO Auto-generated method stub
		
	}

}
