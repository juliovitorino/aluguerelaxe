package br.com.jcv.aluguerelaxe.client.administracao.console.publicidades;

import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.wizard.AbstractWizard;

import com.google.gwt.user.client.ui.Composite;

/**
 *<h2>VendaPublicidadeWizard</h2>
 *<p>Classe concreta para venda de publicidade ao assinante.
 *</p>
 * <h2>CSS Style Rules</h2>
 * <ul>
 * <li>.gwt-AbstractWizard {estilo primario}</li>
 * </ul>
 * @author Julio Vitorino
 */
 public class VendaPublicidadeWizard extends AbstractWizard<Composite, VendaPublicidadeVO>
 			implements SelecaoPlanoFormCompositeListener{
	 
	private VendaPublicidadeDetalhesFormComposite dvfc;
	private ResumoCompraPublicidadeFormComposite rcpfc;
		
	public VendaPublicidadeWizard() {
		super();
		addWizard(dvfc, montaHeaderPasso("user1_edit.png", "Detalhes da publicidade"));
		addWizard(rcpfc, montaHeaderPasso("flag_checkered.png", "Resumo da compra"));
		init();
	}
	
	public ResumoCompraPublicidadeFormComposite getResumoCompra(){
		return rcpfc;
		
	}
	
	@Override
	public VendaPublicidadeVO getVO() {
		VendaPublicidadeVO vo = new VendaPublicidadeVO();
		vo.planoVendido = dvfc.getVO();
		vo.dataInicio = dvfc.getVendaVO();
		return vo;
	}

	@Override
	public void initComposites() {
		dvfc = new VendaPublicidadeDetalhesFormComposite();
		rcpfc = new ResumoCompraPublicidadeFormComposite();
		dvfc.addListener(rcpfc);
	}

/*	public void initComposites(String tipo) {
		dvfc = new VendaPublicidadeDetalhesFormComposite();
	}
*/
	public void onPesquisarPlanoClick(long idPlano) {
		dvfc.onPesquisarPlanoClick(idPlano);
		
	}
}

