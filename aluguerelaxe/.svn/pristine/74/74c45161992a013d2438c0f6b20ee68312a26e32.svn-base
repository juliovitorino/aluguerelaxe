package br.com.jcv.aluguerelaxe.client.administracao.console.tarifawizard;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.cliente.ClienteVO;
import br.com.jcv.aluguerelaxe.client.cliente.TelefoneVO;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.wizard.AbstractWizard;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;
import br.com.jcv.aluguerelaxe.client.imovel.tabelapreco.TabelaPrecoVO;
import br.com.jcv.aluguerelaxe.client.novaconta.ClienteCadastroVO;
import br.com.jcv.aluguerelaxe.client.novaconta.NovaSenhaVO;
import br.com.jcv.aluguerelaxe.client.vo.EnderecoVO;


/**
 *<h2>TabelaPrecoWizard</h2>
 *<p>Classe concreta para criação das tarifas do imovel.
 *</p>
 * <h2>CSS Style Rules</h2>
 * <ul>
 * <li>.gwt-AbstractWizard {estilo primario}</li>
 * </ul>
 * @author Julio Vitorino
 */
 public class TabelaPrecoWizard extends AbstractWizard<FormComposite<?>, TabelaPrecoVO >  {
	 
	private TabelaPrecoFormComposite tpfc;
		
	public TabelaPrecoWizard() {
		super();
		
		addWizard(tpfc, montaHeaderPasso("user1_edit.png", "Assistente de Tabela de Pre\u00e7os"));
		init();
	}
	
	public List<TabelaPrecoVO> getListVO() {
		return this.tpfc.getListVO();
	}
	
	public void update(ImovelFichaCompletaVO vo) {
		this.tpfc.update(vo);
	}
	
	@Override
	public TabelaPrecoVO getVO() {
		// Nao aplicavel neste contexto
		return null;
	}

	@Override
	public void initComposites() {
		tpfc = new TabelaPrecoFormComposite();
	}
}
