package br.com.jcv.aluguerelaxe.server;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.cliente.ClienteRPC;
import br.com.jcv.aluguerelaxe.client.cliente.ClienteVO;
import br.com.jcv.aluguerelaxe.client.cliente.TelefoneVO;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ContatoClienteVO;
import br.com.jcv.aluguerelaxe.client.modopublicidade.ModoPublicidadeVO;
import br.com.jcv.aluguerelaxe.client.novaconta.ClienteCadastroVO;
import br.com.jcv.aluguerelaxe.client.vo.DadosBancariosVO;
import br.com.jcv.aluguerelaxe.client.vo.EnderecoVO;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;
import br.com.jcv.aluguerelaxe.server.datatransferobject.DTOClienteImpl;
import br.com.jcv.aluguerelaxe.server.datatransferobject.DTOClienteModoPublicidadeImpl;
import br.com.jcv.backend.cliente.ClienteContraSenhaDTO;
import br.com.jcv.backend.cliente.ClienteDTO;
import br.com.jcv.backend.cliente.ClienteServiceImpl;
import br.com.jcv.backend.cliente.TelefoneDTO;
import br.com.jcv.backend.datemanager.DateManager;
import br.com.jcv.backend.datemanager.DateManagerBase;
import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.dto.EnderecoDTO;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ContatoClienteDTO;
import br.com.jcv.backend.interfaces.services.ClienteService;
import br.com.jcv.backend.modopublicidade.ModoPublicidadeDTO;
import br.com.jcv.backend.utility.StringUtil;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ClienteRPCImpl extends RemoteServiceServlet implements ClienteRPC{

	private static final long serialVersionUID = 4222511913532439254L;

	public ClienteVO pesquisaRegistro(ClienteVO vo) {
		ClienteVO clientevo = null;
		
		try {
			
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO dto = new ClienteDTO();
			dto.id = vo.id;
			ClienteDTO clientedto = cs.pesquisarRegistro(dto);

			// Repassa retorno do objeto para encaminhar o frontend
			clientevo = new ClienteVO();
			clientevo.id = clientedto.id ;                             
			clientevo.nome = clientedto.nome ;
			clientevo.cpf = clientedto.cpf ;
			clientevo.cgc = clientedto.cgc ;
			clientevo.dataNascimento = clientedto.dataNascimento ;
			clientevo.email = clientedto.email ;
			clientevo.senha = clientedto.senha ;
			clientevo.dataCadastro = clientedto.dataCadastro ;
			clientevo.dataAtualizacao = clientedto.dataAtualizacao ;
			clientevo.indicadorStatus = clientedto.indicadorStatus ;
			clientevo.urlwww = clientedto.urlwww ;
			clientevo.indicadorAutorizaNotificacao = clientedto.indicadorAutorizaNotificacao ;
			clientevo.msn = clientedto.msn ;
			clientevo.skype = clientedto.skype ;
			clientevo.googleTalk = clientedto.googleTalk ;
			clientevo.fotoPerfil = clientedto.fotoPerfil ;
			clientevo.fotoChat = clientedto.fotoChat ;
			clientevo.primeiroNome = clientedto.primeiroNome;
			clientevo.pathPerfil = clientedto.pathPerfil;
			clientevo.membroDesde = DateManagerBase.getDateManagerInstance(clientedto.dataCadastro).getMonth() + "/" + DateManagerBase.getDateManagerInstance(clientedto.dataCadastro).getYear();
			clientevo.indicadorVerificado = clientedto.indicadorVerificado;
			clientevo.taxaResposta = clientedto.taxaResposta;
			clientevo.totalPergunta = clientedto.totalPergunta;
			clientevo.totalResposta = clientedto.totalResposta;
			clientevo.indicadorTipoAnunciante = clientedto.indicadorTipoAnunciante ;
			clientevo.modoPublicidade = new ModoPublicidadeVO();
			clientevo.modoPublicidade.id = clientedto.modoPublicidade.id;			
			clientevo.endereco = new EnderecoVO() ;
			clientevo.endereco.logradouro = StringUtil.splitEndereco(clientedto.endereco.nome,StringUtil.TIPO_RETORNO_LOGRADOURO) ;
			clientevo.endereco.nome = StringUtil.splitEndereco(clientedto.endereco.nome,StringUtil.TIPO_RETORNO_ENDERECO);
			clientevo.endereco.numero = clientedto.endereco.numero ;
			clientevo.endereco.complemento = clientedto.endereco.complemento ;
			clientevo.endereco.bairro = clientedto.endereco.bairro ;
			clientevo.endereco.cep = clientedto.endereco.cep ;
			clientevo.endereco.cidade = clientedto.endereco.cidade ;
			clientevo.endereco.uf = clientedto.endereco.uf ;
			clientevo.endereco.nomeuf = clientedto.endereco.nomeuf ;
			clientevo.endereco.codigoUfCidadeItem = clientedto.endereco.codigoUfCidadeItem ;
			
			if (clientedto.telefones != null) {
				if (clientedto.telefones.size() > 0) {
					clientevo.telefones = new ArrayList<TelefoneVO>();
					for (TelefoneDTO teldto : clientedto.telefones) {
						TelefoneVO telvo = new TelefoneVO();
						telvo.nomeContato = teldto.nomeContato;
						telvo.ddd = teldto.ddd;
						telvo.telefone = teldto.telefone;
						telvo.indPermiteExibir = teldto.indPermiteExibir;
						telvo.indTipoTelefone = teldto.indTipoTelefone;
						clientevo.telefones.add(telvo);
					}
				}
			}
			
			clientevo.db = new DadosBancariosVO();
			clientevo.db.banco = clientedto.banco ;
			clientevo.db.agencia = clientedto.agencia ;
			clientevo.db.contacorrente = clientedto.contacorrente ;

			

		} catch (AlugueRelaxeException e) {
			// TODO: handle exception
		}
		
		return clientevo;
	}

	@SuppressWarnings("unchecked")
	public VOPadrao atualizarFichaCadastro(ClienteVO vo) throws AlugueRelaxeFrontException {
		
		VOPadrao vopadrao = null;
		try {
			
			ClienteDTO clientedto = new ClienteDTO();

			// Repassa objeto do frontend para encaminhar ao backend
			clientedto.id = vo.id ;                             
			clientedto.nome = vo.nome ;
			clientedto.cpf = vo.cpf ;
			clientedto.cgc = vo.cgc ;
			clientedto.dataNascimento = vo.dataNascimento ;
			clientedto.email = vo.email ;
			clientedto.senha = vo.senha ;
			clientedto.urlwww = vo.urlwww ;
			clientedto.msn = vo.msn ;
			clientedto.skype = vo.skype ;
			clientedto.banco = vo.db.banco ;
			clientedto.agencia = vo.db.agencia ;
			clientedto.contacorrente = vo.db.contacorrente ;
			clientedto.googleTalk = vo.googleTalk ;
			clientedto.indicadorTipoAnunciante = vo.indicadorTipoAnunciante ;
			clientedto.indicadorAutorizaNotificacao = vo.indicadorAutorizaNotificacao;
			clientedto.indicadorStatus = vo.indicadorStatus;
			clientedto.endereco = new EnderecoDTO() ;
			clientedto.endereco.nome = vo.endereco.logradouro + " " + vo.endereco.nome;
			clientedto.endereco.numero = vo.endereco.numero ;
			clientedto.endereco.complemento = vo.endereco.complemento ;
			clientedto.endereco.bairro = vo.endereco.bairro ;
			clientedto.endereco.cep = vo.endereco.cep ;
			clientedto.endereco.cidade = vo.endereco.cidade ;
			clientedto.endereco.uf = vo.endereco.uf ;
			clientedto.endereco.nomeuf = vo.endereco.nomeuf ;
			clientedto.endereco.codigoUfCidadeItem = vo.endereco.codigoUfCidadeItem ;
			
			if (vo.telefones != null) {
				if (vo.telefones.size() > 0) {
					clientedto.telefones = new ArrayList<TelefoneDTO>();
					for (TelefoneVO telvo : vo.telefones) {
						if ((telvo.nomeContato.trim().length() > 0) &&
							(telvo.ddd.trim().length() > 0) &&
							(telvo.telefone.trim().length() > 0)) {
							TelefoneDTO teldto = new TelefoneDTO();
							teldto.nomeContato = telvo.nomeContato;
							teldto.ddd = telvo.ddd;
							teldto.telefone = telvo.telefone;
							teldto.indPermiteExibir = telvo.indPermiteExibir;
							teldto.indTipoTelefone = telvo.indTipoTelefone;
							clientedto.telefones.add(teldto);
						}
					}
				}
			}
			
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			DTOPadrao dtopadrao = cs.atualizarFichaCadastral(clientedto);
			
			vopadrao = new VOPadrao();
			vopadrao.msgcode = dtopadrao.msgcode;
			vopadrao.msgcodeString = dtopadrao.msgcodeString;
			
			
		} catch (AlugueRelaxeException e) {
			if (e.getObjeto() instanceof List){
				throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem(), (List)e.getObjeto()); 
			}
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem()); 
		}
		
		return vopadrao;
	}

	public ClienteVO criarNovaConta(ClienteCadastroVO vo)
			throws AlugueRelaxeFrontException {
		ClienteVO vopadrao = null;
		try {
			
			ClienteContraSenhaDTO clientedto = new ClienteContraSenhaDTO();

			// Repassa objeto do frontend para encaminhar ao backend
			clientedto.id = vo.id ;                             
			clientedto.nome = vo.nome ;
			clientedto.cpf = vo.cpf ;
			clientedto.cgc = vo.cgc ;
			clientedto.dataNascimento = vo.dataNascimento ;
			clientedto.email = vo.email ;
			clientedto.senha = vo.senha ;
			clientedto.contraSenha = vo.senhaConfirmacao;
			clientedto.urlwww = vo.urlwww ;
			clientedto.msn = vo.msn ;
			clientedto.skype = vo.skype ;
			clientedto.googleTalk = vo.googleTalk ;
			clientedto.indicadorTipoAnunciante = vo.indicadorTipoAnunciante ;
			clientedto.indicadorAutorizaNotificacao = vo.indicadorAutorizaNotificacao;
			clientedto.indicadorStatus = vo.indicadorStatus;
			clientedto.endereco = new EnderecoDTO() ;
			clientedto.endereco.nome = vo.endereco.logradouro + " " + vo.endereco.nome;
			clientedto.endereco.numero = vo.endereco.numero ;
			clientedto.endereco.complemento = vo.endereco.complemento ;
			clientedto.endereco.bairro = vo.endereco.bairro ;
			clientedto.endereco.cep = vo.endereco.cep ;
			clientedto.endereco.cidade = vo.endereco.cidade ;
			clientedto.endereco.uf = vo.endereco.uf ;
			clientedto.endereco.nomeuf = vo.endereco.nomeuf ;
			clientedto.endereco.codigoUfCidadeItem = vo.endereco.codigoUfCidadeItem ;
			clientedto.modoPublicidade = new ModoPublicidadeDTO();
			clientedto.modoPublicidade.id = vo.modoPublicidade.id;

			if (vo.telefones != null) {
				if (vo.telefones.size() > 0) {
					clientedto.telefones = new ArrayList<TelefoneDTO>();
					for (TelefoneVO telvo : vo.telefones) {
						TelefoneDTO teldto = new TelefoneDTO();
						teldto.nomeContato = telvo.nomeContato;
						teldto.ddd = telvo.ddd;
						teldto.telefone = telvo.telefone;
						teldto.indPermiteExibir = telvo.indPermiteExibir;
						teldto.indTipoTelefone = telvo.indTipoTelefone;
						clientedto.telefones.add(teldto);
					}
				}
			}

			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			ClienteDTO dtopadrao = cs.criarNovaConta(clientedto);
			
			vopadrao = (new DTOClienteImpl()).getDataTransferObject(dtopadrao);
			vopadrao.msgcode = dtopadrao.msgcode;
			vopadrao.msgcodeString = dtopadrao.msgcodeString;
			
			
		} catch (AlugueRelaxeException e) {
			if (e.getObjeto() instanceof List){
				throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem(), (List)e.getObjeto()); 
			}
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem()); 
		}
		
		return vopadrao;
	}

	public VOPadrao trocarSenha(ClienteCadastroVO vo)
			throws AlugueRelaxeFrontException {
		ClienteVO vopadrao = null;
		try {
			
			ClienteContraSenhaDTO clientedto = new ClienteContraSenhaDTO();

			// Repassa objeto do frontend para encaminhar ao backend
			clientedto.id = vo.id ;                             
			clientedto.senha = vo.senha ;
			clientedto.contraSenha = vo.senhaConfirmacao;
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			DTOPadrao dtopadrao = cs.trocarSenha(clientedto);
			
			vopadrao = new ClienteVO();
			vopadrao.msgcode = dtopadrao.msgcode;
			vopadrao.msgcodeString = dtopadrao.msgcodeString;
			
			
		} catch (AlugueRelaxeException e) {
			if (e.getObjeto() instanceof List){
				throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem(), (List)e.getObjeto()); 
			}
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem()); 
		}
		
		return vopadrao;
	}

	public List<ContatoClienteVO> listarContatosCliente(ClienteVO cvo)
			throws AlugueRelaxeFrontException {
		List<ContatoClienteVO> lst = null;
		try {
			
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			List<ContatoClienteDTO> lstdto = cs.listarContatosAnunciante(cvo.id);
			if (lstdto != null){
				lst = new ArrayList<ContatoClienteVO>();
				for(ContatoClienteDTO dto : lstdto){
					ContatoClienteVO vo = new ContatoClienteVO();
					vo.id = dto.id;
					vo.idImovel = dto.idImovel;
					vo.nome = dto.proposto;
					vo.email = dto.email;
					vo.ddd = dto.ddd;
					vo.telefone = dto.telefone;
					vo.cidade = dto.cidade;
					vo.uf = dto.uf;
					try {
						vo.chegadaPrevistaStr = DateManager.getDateManagerInstance(dto.chegadaPrevista).getDateCustom("dd/MM/yyyy");
						vo.dataCadastro = DateManager.getDateManagerInstance(dto.dataCadastro).getDateCustom("dd/MM/yyyy hh:mm");
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					lst.add(vo);
				}
			}

		} catch (AlugueRelaxeException e) {
			if (e.getObjeto() instanceof List){
				throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem(), (List)e.getObjeto()); 
			}
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem()); 
		}
		
		return lst;
	}

	public VOPadrao atualizarEnqueteModoPublicidade(ClienteVO vo) throws AlugueRelaxeFrontException {
		VOPadrao vopadrao = null;
		try {
			
			ClienteDTO dto = (new DTOClienteModoPublicidadeImpl()).getDataTransferObject(vo);
			
			ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
			DTOPadrao dtopadrao = cs.atualizarEnqueteModoPublicidade(dto);
			
			vopadrao = new ClienteVO();
			vopadrao.msgcode = dtopadrao.msgcode;
			vopadrao.msgcodeString = dtopadrao.msgcodeString;
			
			
		} catch (AlugueRelaxeException e) {
			if (e.getObjeto() instanceof List){
				throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem(), (List)e.getObjeto()); 
			}
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem()); 
		}
		
		return vopadrao;
	}

}

