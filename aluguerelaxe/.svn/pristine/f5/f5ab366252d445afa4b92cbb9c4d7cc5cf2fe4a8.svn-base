package br.com.jcv.aluguerelaxe.client.imovel.reserva;

import br.com.jcv.aluguerelaxe.client.EmailVO;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.LocatarioVO;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.ReservaVO;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.prereserva.EmailReservaFormComposite;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.prereserva.EnderecoLocatarioReservaFormComposite;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.prereserva.LocatarioFormComposite;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.prereserva.ReservaFormComposite;
import br.com.jcv.aluguerelaxe.client.cliente.TelefoneVO;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.wizard.AbstractWizard;
import br.com.jcv.aluguerelaxe.client.imovel.ImovelVO;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ContatoClienteVO;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;
import br.com.jcv.aluguerelaxe.client.vo.EnderecoVO;

/**
 *<h2>AssistenteReservaImovel</h2>
 *<p>Classe concreta para criacao de uma nova reserva.
 *</p>
 * <h2>CSS Style Rules</h2>
 * <ul>
 * <li>.gwt-AbstractWizard {estilo primario}</li>
 * </ul>
 * @author Julio Vitorino
 */
 public class AssistenteReservaImovel extends AbstractWizard<FormComposite<?>, ReservaVO> {
 
	// Define todos os formComposites que serao utilizados
	private EmailReservaFormComposite erfc;
	private LocatarioFormComposite lfc;
	private EnderecoLocatarioReservaFormComposite elrfc;
	private ReservaFormComposite rfc;
	private ContatoClienteVO ccvo;
	 
	public AssistenteReservaImovel() {
		super();
		this.setStylePrimaryName("gwt-AssistenteReservaImovel");

		addWizard(erfc, montaHeaderPasso("house.png", "Bem vindo ao Assistente de Reserva de Imóvel"));
		addWizard(lfc, montaHeaderPasso("house.png", "Informações pessoais"));
		addWizard(elrfc, montaHeaderPasso("house.png", "Informações pessoais"));
		addWizard(rfc, montaHeaderPasso("house.png", "Informações da sua negociação"));
		init();
	}
	
	public void update(ContatoClienteVO ccvo) {
		this.ccvo = ccvo;
		
		//----------------------------------------------
		// Popula Formulario de email
		//----------------------------------------------
		EmailVO evo = new EmailVO();
		evo.email = ccvo.email;
		erfc.update(evo);
		
		//----------------------------------------------
		// Popula de dados pessoais
		//----------------------------------------------
		LocatarioVO lvo = new LocatarioVO();
		lvo.nome = ccvo.nome;
		lvo.email = ccvo.email;
		lvo.telefone = new TelefoneVO();
		lvo.telefone.ddd = ccvo.ddd;
		lvo.telefone.telefone = ccvo.telefone;
		lfc.update(lvo);

		//----------------------------------------------
		// Popula de informacoes de checkin
		//----------------------------------------------
		ReservaVO rvo = new ReservaVO();
		rvo.dataCheckin = ccvo.chegadaPrevista;
		rvo.dataCheckout = ccvo.partidaPrevista;
		rvo.percentualComissao = ccvo.taxaComissao;
		rfc.update(rvo);
	}

	@Override
	public ReservaVO getVO() {
		ReservaVO vo = new ReservaVO();
		//-----------------------------------------------
		//Busca VOs das implementacoes de FormComposite
		//-----------------------------------------------
		EmailVO evo = erfc.getVO();
		LocatarioVO lvo = lfc.getVO();
		EnderecoVO endvo = elrfc.getVO();
		ReservaVO rvo = rfc.getVO();
		
		//-----------------------------------------------
		// Monta ReservaVO
		//-----------------------------------------------
		vo.dataCheckin = rvo.dataCheckin;
		vo.dataCheckout = rvo.dataCheckout;
		vo.dataPrevistaDeposito = rvo.dataPrevistaDeposito;
		vo.valorPrevistoDeposito = rvo.valorPrevistoDeposito;
		vo.valorAluguelNegociado = rvo.valorAluguelNegociado;
		vo.formaPagamento = rvo.formaPagamento;
		vo.valorCaucao = rvo.valorCaucao;
		vo.valorTaxaServico = rvo.valorTaxaServico;
		vo.idContato = ccvo.id;

		// Monta VO do locatario
		vo.locatario = new LocatarioVO();
		vo.locatario.id = vo.id;
		vo.locatario.nome = lvo.nome;
		vo.locatario.cpf = lvo.cpf;
		vo.locatario.dataNascimento = lvo.dataNascimento;
		vo.locatario.email = evo.email;
		vo.locatario.endereco = new EnderecoVO();
		vo.locatario.endereco.nome =  endvo.logradouro + " " + endvo.nome;
		vo.locatario.endereco.numero = endvo.numero;
		vo.locatario.endereco.complemento = endvo.complemento;
		vo.locatario.endereco.bairro =  endvo.bairro;
		vo.locatario.endereco.cep = endvo.cep;
		vo.locatario.endereco.cidade = endvo.cidade;
		vo.locatario.endereco.uf = endvo.uf;
		vo.locatario.endereco.nomeuf = endvo.nomeuf;
		vo.locatario.endereco.codigoUfCidadeItem = endvo.codigoUfCidadeItem;
		vo.locatario.telefone = new TelefoneVO();
		vo.locatario.telefone.ddd = lvo.telefone.ddd;
		vo.locatario.telefone.telefone = lvo.telefone.telefone;
		
		vo.ifcdto = new ImovelFichaCompletaVO();
		vo.ifcdto.imovel = new ImovelVO();
		vo.ifcdto.imovel.id = ccvo.idImovel;
		return vo;
	}
	
	@Override
	public void initComposites() {
		erfc = new EmailReservaFormComposite();
		lfc = new LocatarioFormComposite();
		elrfc = new EnderecoLocatarioReservaFormComposite();
		rfc = new ReservaFormComposite();
		
		erfc.addListener(lfc);
	}
}
