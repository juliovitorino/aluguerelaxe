package br.com.jcv.aluguerelaxe.client.administracao.console.imovelwizard;

import br.com.jcv.aluguerelaxe.client.componente.autoajuda.AutoAjudaTextArea;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.imovel.ImovelVO;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;



public class ImovelDescritivoFormComposite extends FormComposite<ImovelVO> {
	
	private final static String AUTO_AJUDA_DESCRICAO_GERAL = "<B>Descri\u00e7\u00e3o Geral</B>" + 
	"<p>Forne\u00e7a uma descri\u00e7\u00e3o geral sobre o im\u00f3vel.<br/>Veja o exemplo abaixo:</p>" + 
	"<p>Apartamentos mobiliados (com TV, antena parab\u00f3lica, geladeira, <br/>" +
	"ventiladores, cozinha americana completa, sala de estar com jogo <br/>" +
	"de sof\u00e1 e mesa, etc) para 06 a 10 pessoas pr\u00f3ximo da praia, com garagem<br/>" +
	"para 16 carros, piscina adulto e crian\u00e7a, \u00e1rea para churrasca, mesa ping-pong,<br/>" +
	"sinuca, etc</p>";

	private final static String AUTO_AJUDA_DESCRICAO_QUARTOS = "<B>Descri\u00e7\u00e3o dos Quartos</B>" + 
	"<p>Forne\u00e7a uma descri\u00e7\u00e3o dos quartos do im\u00f3vel.<br/>Veja o exemplo abaixo:</p>" + 
	"<p>Apartamento com 2 quartos possui : <br/>" +
	"Quarto Casal - Com uma cama de casal e um beliche, <br/>" +
	"ar condicionado e ventilador tuf\u00e3o,Piso cer\u00e2mico  <br/>" +
	"com TV e outro quarto com uma cama de casal e 2 beliches <br/>" +
	"e ventilador de teto. Apartamento 1 quarto : Quarto com uma <br/>" +
	"cama de casal e dois beliches e sala revers\u00edvel  <br/>" +
	"com um ou dois beliches.</p>";

	private final static String AUTO_AJUDA_DESCRICAO_ARREDORES = "<B>Descri\u00e7\u00e3o dos Arredores</B>" + 
	"<p>Forne\u00e7a uma descri\u00e7\u00e3o dos arredores do im\u00f3vel.<br/>Veja o exemplo abaixo:</p>" + 
	"<p>Possui mercearia (casa do p\u00e3o) no quarteir\u00e3o do condom\u00ednio. <br/>" +
	"pra\u00e7a de esportes, igreja, bosque com pista de cooper, <br/>" +
	"pr\u00f3ximo \u00e0 praia etc.</p>";

	private AutoAjudaTextArea taDescricaoGeralImovel;
	private AutoAjudaTextArea taDescricaoQuartos;
	private AutoAjudaTextArea taDescricaoArredores;

	public ImovelDescritivoFormComposite() {
		super();
		//this.setStylePrimaryName("gwt-ImovelDescritivoFormComposite");
	}

	public void init() {
		taDescricaoGeralImovel = new AutoAjudaTextArea(ImovelDescritivoFormComposite.AUTO_AJUDA_DESCRICAO_GERAL);
		taDescricaoQuartos = new AutoAjudaTextArea(ImovelDescritivoFormComposite.AUTO_AJUDA_DESCRICAO_QUARTOS);
		taDescricaoArredores =new AutoAjudaTextArea(ImovelDescritivoFormComposite.AUTO_AJUDA_DESCRICAO_ARREDORES);
	}
	
	public Widget render() {
		VerticalPanel vp = new VerticalPanel();
		Grid grid = new Grid(3,2);

		// Posiciona campos no grid
		int linha = -1;
		
		grid.setWidget(++linha, 0, new Label("Descri\u00e7\u00e3o Geral do Im\u00f3vel:"));
		grid.setWidget(linha, 1,taDescricaoGeralImovel );
		
		grid.setWidget(++linha, 0, new Label("Descri\u00e7\u00e3o dos Quartos:"));
		grid.setWidget(linha, 1,taDescricaoQuartos );
		
		grid.setWidget(++linha, 0, new Label("Descri\u00e7\u00e3o dos Arredores"));
		grid.setWidget(linha, 1,taDescricaoArredores );
		
		// Adiciona a grid ao VerticalPanel
		vp.add(grid);
		
		return vp;
	}
	
	@Override
	public ImovelVO getVO() {
		ImovelVO vo = new ImovelVO();
		vo.descricaoArredores = taDescricaoArredores.getWidgetUI().getValue();
		vo.descricaoGeral = taDescricaoGeralImovel.getWidgetUI().getValue();
		vo.descricaoQuartos = taDescricaoQuartos.getWidgetUI().getValue();
		return vo;
	}

	@Override
	public void update(ImovelVO vo) {
		//Não aplicável neste contexto	
	}

	@Override
	public void clear() {
		//Não aplicável neste contexto	
	}

	@Override
	public void notifier(ImovelVO vo) {
		// TODO Auto-generated method stub
		
	}

}
