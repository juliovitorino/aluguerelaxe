package br.com.jcv.aluguerelaxe.client.novaconta;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.administracao.console.proprietario.ClienteFormComposite;
import br.com.jcv.aluguerelaxe.client.cliente.ClienteVO;
import br.com.jcv.aluguerelaxe.client.cliente.TelefoneVO;
import br.com.jcv.aluguerelaxe.client.componente.form.EnderecoFormComposite;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.wizard.AbstractWizard;
import br.com.jcv.aluguerelaxe.client.modopublicidade.ModoPublicidadeVO;
import br.com.jcv.aluguerelaxe.client.vo.EnderecoVO;

/**
 *<h2>ClienteNovaContaWizard</h2>
 *<p>Classe concreta para criação de nova conta.
 *</p>
 * <h2>CSS Style Rules</h2>
 * <ul>
 * <li>.gwt-AbstractWizard {estilo primario}</li>
 * </ul>
 * @author Julio Vitorino
 */
 public class ClienteNovaContaWizard extends AbstractWizard<FormComposite<?>, ClienteCadastroVO >  {
	 
	private ClienteFormComposite cfc;
	private EnderecoFormComposite efc;
	private NovaSenhaFormComposite nsfc;
	private InstrucoesAssistenteNovoContaFormComposite iancfc;
	private TermoUsoFormComposite tufc;
	private TelefonesNovaContaFormComposite tncfc;
	private ComoConheceuARFormComposite ccarfc;
		
	public ClienteNovaContaWizard() {
		super();
		
		addWizard(iancfc, montaHeaderPasso("user1_edit.png", "Assistente de Abertura de Conta"));
		addWizard(tufc, montaHeaderPasso("user1_edit.png", "Termo de Uso"));
		addWizard(cfc, montaHeaderPasso("user1_edit.png", "Digite suas informa\u00e7\u00f5es pessoais"));
		addWizard(efc, montaHeaderPasso("house.png", "Digite seu endere\u00e7o"));
		addWizard(tncfc, montaHeaderPasso("house.png", "Telefones de contato"));
		addWizard(nsfc,montaHeaderPasso("key1.png", "Digite sua senha"));
		addWizard(ccarfc,montaHeaderPasso("information2.png", "Pesquisa"));
		addWizard(new InstrucoesFinaisFormComposite(),montaHeaderPasso("information2.png", "Instru\u00e7\u00f5es Finais"));
		
		// Neste processo o botão de upload de imagem NAO pode ser habilitado
		cfc.mudarVisibilidadeFotoUpload(false);

		
		init();
	}
	
	public TermoUsoVO getAceiteTermoUso() {
		return tufc.getVO();
	}

	@Override
	public ClienteCadastroVO getVO() {
		ClienteVO cfcvo = cfc.getVO();
		EnderecoVO efcvo = efc.getVO();
		NovaSenhaVO nsfcvo = nsfc.getVO();
		ModoPublicidadeVO mpvo = ccarfc.getVO();
		List<TelefoneVO> lst = tncfc.getListVO();
		
		ClienteCadastroVO vo = new ClienteCadastroVO();
		vo.id = cfcvo.id;                        
		vo.nome = cfcvo.nome;                   
		vo.cpf = cfcvo.cpf;                
		vo.cgc = cfcvo.cgc ;        
		vo.dataNascimento = cfcvo.dataNascimento;
		vo.email = cfcvo.email;                 
		vo.dataCadastro = cfcvo.dataCadastro;             
		vo.dataAtualizacao = cfcvo.dataAtualizacao;         
		vo.indicadorStatus = cfcvo.indicadorStatus ;       
		vo.urlwww = cfcvo.urlwww;                  
		vo.indicadorAutorizaNotificacao = cfcvo.indicadorAutorizaNotificacao;
		vo.msn = cfcvo.msn;                    
		vo.skype = cfcvo.skype;                   
		vo.googleTalk = cfcvo.googleTalk;              
		vo.indicadorTipoAnunciante = cfcvo.indicadorTipoAnunciante;     
		vo.endereco = efcvo;                
		vo.senha = nsfcvo.senha;              
		vo.senhaConfirmacao = nsfcvo.contrasenha;  
		vo.modoPublicidade = new ModoPublicidadeVO();
		vo.modoPublicidade.id = mpvo.id;
		if ((lst != null) && (lst.size() > 0)){
			vo.telefones = lst;
		}
		return vo;
	}

	@Override
	public void initComposites() {
		cfc = new ClienteFormComposite();
		efc = new EnderecoFormComposite();
		nsfc = new NovaSenhaFormComposite();
		iancfc = new InstrucoesAssistenteNovoContaFormComposite();
		tufc = new TermoUsoFormComposite();
		tncfc = new TelefonesNovaContaFormComposite();
		ccarfc = new ComoConheceuARFormComposite();
	}
}
