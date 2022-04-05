package br.com.jcv.aluguerelaxe.server.datatransferobject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.ReservaVO;
import br.com.jcv.aluguerelaxe.client.cliente.ClienteVO;
import br.com.jcv.aluguerelaxe.client.cliente.TelefoneVO;
import br.com.jcv.aluguerelaxe.client.imovel.ImovelPlanoVO;
import br.com.jcv.aluguerelaxe.client.imovel.ImovelVO;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;
import br.com.jcv.aluguerelaxe.client.vo.DadosBancariosVO;
import br.com.jcv.aluguerelaxe.client.vo.EnderecoVO;
import br.com.jcv.backend.cliente.TelefoneDTO;
import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.datatransfer.AbstractDataTransferObject;
import br.com.jcv.backend.datemanager.DateManagerBase;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ImovelDTO;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;
import br.com.jcv.backend.reserva.ReservaDTO;
import br.com.jcv.backend.template.Template;
import br.com.jcv.backend.template.TemplateConstantes;
import br.com.jcv.backend.template.TemplateFactory;
import br.com.jcv.backend.utility.StringUtil;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

public class DTOReservaImpl implements
		AbstractDataTransferObject<ReservaDTO, ReservaVO> {

	public ReservaVO getDataTransferObject(ReservaDTO dto) {
		ReservaVO vo = null;
		if (dto != null){
			vo = new ReservaVO();
			vo.msgcode = dto.msgcode;
			vo.msgcodeString = dto.msgcodeString;
			
			vo.id = dto.id;
			vo.dataCheckin = dto.dataCheckin;
			vo.dataCheckout = dto.dataCheckout;
			
			DateManagerBase dci = DateManagerBase.getDateManagerInstance(vo.dataCheckin);
			DateManagerBase dco = DateManagerBase.getDateManagerInstance(vo.dataCheckout);
			try {
				vo.dataCheckinStr = dci.getDateCustom("dd/MM/yyyy");
				vo.dataCheckoutStr = dco.getDateCustom("dd/MM/yyyy");
			} catch (ParseException e2) {
				e2.printStackTrace();
			}
			
			vo.notaAnfitriao = dto.notaAnfitriao;
			vo.notaImovel = dto.notaImovel;
			vo.dataPrevistaDeposito = dto.dataPrevistaDeposito;
			vo.valorPrevistoDeposito = dto.valorPrevistoDeposito;
			vo.dataCadastro = dto.dataCadastro;
			vo.token = dto.token;
			vo.chaveLiberacaoDeposito = dto.chaveLiberacaoDeposito;
			vo.dataEmailLiberacao = dto.dataEmailLiberacao;
			vo.dataValidacaoPreReserva = dto.dataValidacaoPreReserva;
			vo.dataRealDeposito = dto.dataRealDeposito;
			vo.valorRealDeposito = dto.valorRealDeposito;
			vo.urlContratoLocador = dto.urlContratoLocador;
			vo.urlContratoLocatario = dto.urlContratoLocatario;
			vo.chaveLiberacaoCheck = dto.chaveLiberacaoCheck;
			vo.dataChaveLiberacaoCheck = dto.dataChaveLiberacaoCheck;
			vo.dataTranferenciaDeposito = dto.dataTranferenciaDeposito;
			vo.valorTransferenciaDeposito = dto.valorTransferenciaDeposito;
			vo.chaveTracker = dto.chaveTracker;
			vo.valorAluguelNegociado = dto.valorAluguelNegociado;
			vo.formaPagamento = dto.formaPagamento;
			vo.dataReprovacao = dto.dataReprovacao;
			vo.valorCaucao = dto.valorCaucao;
			vo.percentualComissao = dto.percentualComissao;
			vo.dataAvaliacao = dto.dataAvaliacao;
			vo.valorTaxaServico = dto.valorTaxaServico;
			vo.idContato = dto.idContato;
			vo.imgVisitante = dto.imgVisitante;
			
			if (dto.locatario != null){
				// Monta VO do locatario
				vo.locatario = (new DTOLocatarioImpl()).getDataTransferObject(dto.locatario);
			}

			if (dto.ifcdto != null){
				// Informacoes do imovel
				vo.ifcdto = new ImovelFichaCompletaVO();
				vo.ifcdto.imovel = new ImovelVO();
				vo.ifcdto.imovel.id = dto.ifcdto.imovel.id;
				vo.ifcdto.imovel.idCliente = dto.ifcdto.imovel.idCliente;
				vo.ifcdto.imovel.qtdeQuartos = dto.ifcdto.imovel.qtdeQuartos;
				vo.ifcdto.imovel.qtdeSuites = dto.ifcdto.imovel.qtdeSuites;
				vo.ifcdto.imovel.qtdeBanheiros = dto.ifcdto.imovel.qtdeBanheiros;
				vo.ifcdto.imovel.qtdeCapacidade = dto.ifcdto.imovel.qtdeCapacidade;
				vo.ifcdto.imovel.descricaoGeral = dto.ifcdto.imovel.descricaoGeral;
				vo.ifcdto.imovel.descricaoQuartos = dto.ifcdto.imovel.descricaoQuartos;
				vo.ifcdto.imovel.descricaoArredores = dto.ifcdto.imovel.descricaoArredores;
				vo.ifcdto.imovel.observacoes = dto.ifcdto.imovel.observacoes;
				vo.ifcdto.imovel.indicadorTipoPropriedade = dto.ifcdto.imovel.indicadorTipoPropriedade;
				vo.ifcdto.imovel.qtdeVisitas = dto.ifcdto.imovel.qtdeVisitas;
				vo.ifcdto.imovel.endereco = new EnderecoVO();
				vo.ifcdto.imovel.endereco.logradouro = StringUtil.splitEndereco(dto.ifcdto.imovel.endereco.nome,StringUtil.TIPO_RETORNO_LOGRADOURO) ;
				vo.ifcdto.imovel.endereco.nome = StringUtil.splitEndereco(dto.ifcdto.imovel.endereco.nome,StringUtil.TIPO_RETORNO_ENDERECO);
				vo.ifcdto.imovel.endereco.numero = dto.ifcdto.imovel.endereco.numero;
				vo.ifcdto.imovel.endereco.complemento = dto.ifcdto.imovel.endereco.complemento;
				vo.ifcdto.imovel.endereco.bairro = dto.ifcdto.imovel.endereco.bairro;
				vo.ifcdto.imovel.endereco.cep = dto.ifcdto.imovel.endereco.cep;
				vo.ifcdto.imovel.endereco.codigoUfCidadeItem = dto.ifcdto.imovel.endereco.codigoUfCidadeItem;
				vo.ifcdto.imovel.endereco.cidade = dto.ifcdto.imovel.endereco.cidade;
				vo.ifcdto.imovel.endereco.uf = dto.ifcdto.imovel.endereco.uf;
				vo.ifcdto.imovel.dataCadastro = dto.ifcdto.imovel.dataCadastro;
				vo.ifcdto.imovel.dataAtualizacao = dto.ifcdto.imovel.dataAtualizacao;
				vo.ifcdto.imovel.descricaoTituloAnuncio = dto.ifcdto.imovel.descricaoTituloAnuncio;
				vo.ifcdto.imovel.indicadorStatus = dto.ifcdto.imovel.indicadorStatus;
				vo.ifcdto.imovel.indicadorMostraTabelaPreco = dto.ifcdto.imovel.indicadorMostraTabelaPreco;
				vo.ifcdto.imovel.dataUltimaVisita = dto.ifcdto.imovel.dataUltimaVisita;
				vo.ifcdto.imovel.indicadorPermiteAlugarFestas = dto.ifcdto.imovel.indicadorPermiteAlugarFestas;
				
				// Informacoes do cliente (anuciante)
				vo.ifcdto.cliente = new ClienteVO();
				vo.ifcdto.cliente.id = dto.ifcdto.cliente.id;
				vo.ifcdto.cliente.nome = dto.ifcdto.cliente.nome;
				vo.ifcdto.cliente.cpf = dto.ifcdto.cliente.cpf;
				vo.ifcdto.cliente.cgc = dto.ifcdto.cliente.cgc;
				vo.ifcdto.cliente.dataNascimento = dto.ifcdto.cliente.dataNascimento;
				vo.ifcdto.cliente.email = dto.ifcdto.cliente.email;
				vo.ifcdto.cliente.dataCadastro = dto.ifcdto.cliente.dataCadastro;
				vo.ifcdto.cliente.dataAtualizacao = dto.ifcdto.cliente.dataAtualizacao;
				vo.ifcdto.cliente.indicadorStatus = dto.ifcdto.cliente.indicadorStatus;
				vo.ifcdto.cliente.urlwww = dto.ifcdto.cliente.urlwww;
				vo.ifcdto.cliente.fotoPerfil = dto.ifcdto.cliente.fotoPerfil;
				vo.ifcdto.cliente.fotoChat = dto.ifcdto.cliente.fotoChat;
				vo.ifcdto.cliente.indicadorAutorizaNotificacao = dto.ifcdto.cliente.indicadorAutorizaNotificacao;
				vo.ifcdto.cliente.msn = dto.ifcdto.cliente.msn;
				vo.ifcdto.cliente.skype = dto.ifcdto.cliente.skype;
				vo.ifcdto.cliente.googleTalk = dto.ifcdto.cliente.googleTalk;
				vo.ifcdto.cliente.indicadorTipoAnunciante = dto.ifcdto.cliente.indicadorTipoAnunciante;
				vo.ifcdto.cliente.endereco = new EnderecoVO();
				vo.ifcdto.cliente.endereco.nome = dto.ifcdto.cliente.endereco.nome;
				vo.ifcdto.cliente.endereco.numero = dto.ifcdto.cliente.endereco.numero;
				vo.ifcdto.cliente.endereco.complemento = dto.ifcdto.cliente.endereco.complemento;
				vo.ifcdto.cliente.endereco.bairro = dto.ifcdto.cliente.endereco.bairro;
				vo.ifcdto.cliente.endereco.cep = dto.ifcdto.cliente.endereco.cep;
				if (dto.ifcdto.cliente.telefones != null){
					if (dto.ifcdto.cliente.telefones.size() > 0) {
						vo.ifcdto.cliente.telefones = new ArrayList<TelefoneVO>();
						for (TelefoneDTO telefonedto : dto.ifcdto.cliente.telefones) {
							TelefoneVO telefonevo = new TelefoneVO();
							telefonevo.nomeContato = telefonedto.nomeContato;
							telefonevo.ddd = telefonedto.ddd;
							telefonevo.telefone = telefonedto.telefone;
							telefonevo.indPermiteExibir = telefonedto.indPermiteExibir;
							telefonevo.indTipoTelefone = telefonedto.indTipoTelefone;
							vo.ifcdto.cliente.telefones.add(telefonevo);
						}
					}
				}
				vo.ifcdto.cliente.db = new DadosBancariosVO();
				vo.ifcdto.cliente.db.banco = dto.ifcdto.cliente.banco;
				vo.ifcdto.cliente.db.agencia = dto.ifcdto.cliente.agencia;
				vo.ifcdto.cliente.db.contacorrente = dto.ifcdto.cliente.contacorrente;
				
				if (dto.ifcdto.imovelPlano != null){
					vo.ifcdto.imovelPlano = new ImovelPlanoVO();
					vo.ifcdto.imovelPlano.plano = (new DTOPlanoImpl()).getDataTransferObject(dto.ifcdto.imovelPlano.plano);
				}
				
			}
			
			DateManagerBase checkin = DateManagerBase.getDateManagerInstance(vo.dataCheckin);
			DateManagerBase checkout = DateManagerBase.getDateManagerInstance(vo.dataCheckout);
			
			//-----------------------------------------------------
			// Obtem o html de pagamento do pagseguro
			//-----------------------------------------------------
			Map<String,String> conteudo = new HashMap<String,String>();
			try {
				conteudo.put(TemplateConstantes.TAGC_EMAIL_ALUGUE_RELAXE, VariavelCache.getInstance().getValor(VariavelConstantes.EMAIL_ADMIN_ALUGUERELAXE));
			} catch (AlugueRelaxeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conteudo.put(TemplateConstantes.TAGC_CODIGO_PLANO, String.valueOf(vo.ifcdto.imovelPlano.plano.id));
			try {
				conteudo.put(TemplateConstantes.TAGC_DESCRICAO_DO_PLANO, "Aluguel de temporada " +
						" de " + checkin.getDateCustom("dd/MM/yyyy") + " ate " + checkout.getDateCustom("dd/MM/yyyy") +
						" em " + 
						vo.ifcdto.imovel.endereco.cidade + "/" +
						vo.ifcdto.imovel.endereco.uf + " - REF:" +
						String.valueOf(vo.ifcdto.imovel.id));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			conteudo.put(TemplateConstantes.TAGC_VALOR_TOTAL, 
				StringUtil.valorCorreto((vo.valorAluguelNegociado + vo.valorTaxaServico)
											* (vo.formaPagamento.equals(Constantes.FORMA_PGTO_PARCIAL) ? 0.5 : 1),"."));
			conteudo.put(TemplateConstantes.TAGC_ID_FATURA, String.valueOf(vo.id));
			conteudo.put(TemplateConstantes.TAGC_NOME_CLIENTE, vo.locatario.nome.toUpperCase() );
			conteudo.put(TemplateConstantes.TAGC_EMAIL_CLIENTE, vo.locatario.email );

			Template htmlps = null;
			try {
				htmlps = TemplateFactory.getInstance(TemplateConstantes.TEMPLATE_PGTO_PAGSEGURO, conteudo);
			} catch (AlugueRelaxeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			vo.htmlFormPagtoPagseguro = htmlps.getHtmlTemplate();
		}
		return vo;
	}

	public ReservaDTO getDataTransferObject(ReservaVO vo) {
		ReservaDTO dto = null;
		if (vo != null){
			dto = new ReservaDTO();
			dto.id = vo.id;
			dto.dataCheckin = vo.dataCheckin;
			dto.dataCheckout = vo.dataCheckout;
			dto.dataPrevistaDeposito = vo.dataPrevistaDeposito;
			dto.valorPrevistoDeposito = vo.valorPrevistoDeposito;
			dto.valorAluguelNegociado = vo.valorAluguelNegociado;
			dto.formaPagamento = vo.formaPagamento;
			dto.valorCaucao = vo.valorCaucao;
			dto.valorTaxaServico = vo.valorTaxaServico;
			dto.idContato = vo.idContato;
			dto.imgVisitante = vo.imgVisitante;
			dto.notaAnfitriao = vo.notaAnfitriao;
			dto.notaImovel = vo.notaImovel;
			
			// Monta DTO do contato
			dto.contatoCliente = (new DTOContatoAnuncianteImpl("/")).getDataTransferObject(vo.contatoCliente);
			
			// Monta DTO do locatario
			dto.locatario = (new DTOLocatarioImpl()).getDataTransferObject(vo.locatario);
			
			// Informacoes do imovel
			dto.ifcdto = new ImovelFichaCompletaDTO();
			dto.ifcdto.imovel = new ImovelDTO();
			dto.ifcdto.imovel.id = vo.ifcdto.imovel.id;
			
			
		}
		return dto;
	}

	public ReservaDTO getDataTransferObjectCriarReserva(ReservaVO vo) {
		ReservaDTO dto = null;
		
		if(vo != null){
			dto = new ReservaDTO();
			dto.dataCheckin = vo.dataCheckin;
			dto.dataCheckout = vo.dataCheckout;
			dto.dataPrevistaDeposito = vo.dataPrevistaDeposito;
			dto.valorPrevistoDeposito = vo.valorPrevistoDeposito;
			dto.valorAluguelNegociado = vo.valorAluguelNegociado;
			dto.formaPagamento = vo.formaPagamento;
			dto.valorCaucao = vo.valorCaucao;
			dto.valorTaxaServico = vo.valorTaxaServico;
			dto.idContato = vo.idContato;
			dto.imgVisitante = vo.imgVisitante;
			
			// Monta DTO do contato
			dto.contatoCliente = (new DTOContatoAnuncianteImpl("/")).getDataTransferObject(vo.contatoCliente);
			
			// Monta DTO do locatario
			dto.locatario = (new DTOLocatarioImpl()).getDataTransferObject(vo.locatario);
			
			// Informacoes do imovel
			dto.ifcdto = new ImovelFichaCompletaDTO();
			dto.ifcdto.imovel = new ImovelDTO();
			dto.ifcdto.imovel.id = vo.ifcdto.imovel.id;
		}
		
		return dto;
	}

}
