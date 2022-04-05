package br.com.jcv.aluguerelaxe.client.imovel.listagem.uf;

import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.listbox.CidadesListBox;
import br.com.jcv.aluguerelaxe.client.componente.listbox.CidadesListComImoveisBox;
import br.com.jcv.aluguerelaxe.client.componente.listbox.ListaNumeradaListBox;
import br.com.jcv.aluguerelaxe.client.componente.listbox.SimNaoListBox;
import br.com.jcv.aluguerelaxe.client.componente.listbox.TipoPropriedadeListBox;
import br.com.jcv.aluguerelaxe.client.componente.listbox.UFListBox;
import br.com.jcv.aluguerelaxe.client.imovel.ImovelVO;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;
import br.com.jcv.aluguerelaxe.client.vo.EnderecoVO;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class FiltroImovelComposite extends FormComposite<ImovelFichaCompletaVO> {

	private ListaNumeradaListBox lstQuartos;
	private ListaNumeradaListBox lstSuites;
	private ListaNumeradaListBox lstBanheiros;
	private ListaNumeradaListBox lstCapacidade;
	private SimNaoListBox lstCondominio;
	private SimNaoListBox lstAlugaFesta;
	private TipoPropriedadeListBox lstTipo;
	private UFListBox lstuf;
	private CidadesListComImoveisBox lstCidades;
	 
	public FiltroImovelComposite() {
		super();
		this.setStylePrimaryName("gwt-FormComposite-FiltroImovelComposite");
	}
	
	@Override
	public ImovelFichaCompletaVO getVO() {
		ImovelFichaCompletaVO vo = new ImovelFichaCompletaVO();
		vo.imovel = new ImovelVO();
		vo.imovel.indicadorTipoPropriedade = lstTipo.getValue(lstTipo.getSelectedIndex()) ; 
		try {
			vo.imovel.qtdeQuartos = Integer.valueOf(lstQuartos.getValue(lstQuartos.getSelectedIndex())); 
		} catch (NumberFormatException e) {
			vo.imovel.qtdeQuartos = -1;
		}
		
		try {
			vo.imovel.qtdeBanheiros = Integer.valueOf(lstBanheiros.getValue(lstBanheiros.getSelectedIndex()));
		} catch (NumberFormatException e) {
			vo.imovel.qtdeBanheiros = -1;
		}
		
		try {
			vo.imovel.qtdeCapacidade = Integer.valueOf(lstCapacidade.getValue(lstCapacidade.getSelectedIndex()));  
		} catch (NumberFormatException e) {
			vo.imovel.qtdeCapacidade = -1;
		}
		
		try {
			vo.imovel.qtdeSuites = Integer.valueOf(lstSuites.getValue(lstSuites.getSelectedIndex())); ; 
		} catch (NumberFormatException e) {
			vo.imovel.qtdeSuites = -1;
		}
		
		vo.imovel.indicadorCondominio = lstCondominio.getValue(lstCondominio.getSelectedIndex()) ; 
		vo.imovel.indicadorPermiteAlugarFestas = lstAlugaFesta.getValue(lstAlugaFesta.getSelectedIndex()) ;
		vo.imovel.endereco = new EnderecoVO();
		vo.imovel.endereco.uf = lstuf.getValue(lstuf.getSelectedIndex()) ; 
		vo.imovel.endereco.codigoUfCidadeItem = Integer.valueOf(lstCidades.getValue(lstCidades.getSelectedIndex())); 
		return vo;
	}

	@Override
	public void update(ImovelFichaCompletaVO vo) {
		//Nao aplicado neste contexo
		
	}

	@Override
	public Widget render() {
		VerticalPanel vp = new VerticalPanel();
		
		vp.add(renderBox("UF", lstuf));
		vp.add(renderBox("Cidade", lstCidades));
		vp.add(renderBox("Tipo de Im\u00f3vel", lstTipo));
		vp.add(renderBox("M\u00ednimo de Quartos", lstQuartos));
		vp.add(renderBox("Su\u00edtes", lstSuites));
		vp.add(renderBox("Banheiros", lstBanheiros));
		vp.add(renderBox("Capacidade M\u00ednima", lstCapacidade));
		vp.add(renderBox("Dentro do Condom\u00ednio", lstCondominio));
		vp.add(renderBox("Alugar para Festas e Eventos", lstAlugaFesta));
		return vp;
	}
	
	private Widget renderBox(String titulo, Widget UI){
		VerticalPanel hp = new VerticalPanel();
		hp.add(new Label(titulo));
		hp.add(UI);
		return hp;
	}

	@Override
	public void init() {
		lstQuartos = new ListaNumeradaListBox(1, 20, false, true);
		lstSuites = new ListaNumeradaListBox(0, 20, false, true);
		lstBanheiros = new ListaNumeradaListBox(1, 20, false, true);
		lstCapacidade = new ListaNumeradaListBox(1, 20, false, true);
		lstCondominio = new SimNaoListBox(false, true);
		lstAlugaFesta = new SimNaoListBox(false, true);
		lstTipo = new TipoPropriedadeListBox(false, true);
		lstuf = new UFListBox(false);
		lstCidades = new CidadesListComImoveisBox(true);
		lstuf.addChangeHandler(lstCidades);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifier(ImovelFichaCompletaVO vo) {
		// TODO Auto-generated method stub
		
	}

}
