package br.com.jcv.backend.contatoanunciante;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.jcv.backend.cliente.ClienteBusinessImpl;
import br.com.jcv.backend.cliente.ClienteDTO;
import br.com.jcv.backend.cliente.TelefoneDTO;
import br.com.jcv.backend.datemanager.DateManagerBase;
import br.com.jcv.backend.email.Email;
import br.com.jcv.backend.email.EmailFactory;
import br.com.jcv.backend.email.EmailRecord;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.imovel.ContatoClienteDTO;
import br.com.jcv.backend.imovel.ImovelBusinessImpl;
import br.com.jcv.backend.imovel.ImovelDTO;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;
import br.com.jcv.backend.imovel.thread.ContatoAnuncianteThreadBusinessImpl;
import br.com.jcv.backend.imovel.thread.ContatoAnuncianteThreadDTO;
import br.com.jcv.backend.interfaces.business.ClienteBusiness;
import br.com.jcv.backend.interfaces.business.ContatoAnuncianteBusiness;
import br.com.jcv.backend.interfaces.business.ContatoAnuncianteThreadBusiness;
import br.com.jcv.backend.interfaces.business.ImovelBusiness;
import br.com.jcv.backend.interfacesdao.ContatoAnuncianteDAO;
import br.com.jcv.backend.template.TemplateConstantes;
import br.com.jcv.backend.utility.StringUtil;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

public class ContatoAnuncianteBusinessImpl implements
		ContatoAnuncianteBusiness {

	public void setDados(ContatoClienteDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public ContatoClienteDTO getDados() throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public void validarCamposObrigatorios(ContatoClienteDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public ContatoClienteDTO incluir(DAOFactory daofactory,
			ContatoClienteDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public ContatoClienteDTO atualizar(DAOFactory daofactory,
			ContatoClienteDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public void excluir(DAOFactory daofactory, ContatoClienteDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}
	
	public void atualizarFotoThread(DAOFactory daoFactory, String hashParent, String foto) throws AlugueRelaxeException {
		ContatoAnuncianteDAO dao = daoFactory.getContatoAnuncianteDAO(daoFactory);
		dao.updateFotoThread(hashParent, foto);
	}
	
	
	public List<ContatoClienteDTO> listarContatosPendentesAlerta24h(DAOFactory daofactory) throws AlugueRelaxeException {
		ContatoAnuncianteDAO dao = daofactory.getContatoAnuncianteDAO(daofactory);
		return dao.listContatosPendentesAlerta24h();
	}
	
	public List<ContatoClienteDTO> listarContatosAnuncianteStatus(DAOFactory daofactory, String status) throws AlugueRelaxeException {
		ContatoAnuncianteDAO dao = daofactory.getContatoAnuncianteDAO(daofactory);
		return dao.listContatosAnuncianteStatus(status);
	}
	
	public void atualizarFilaAlerta24h(DAOFactory daofactory, String hashID, String status) throws AlugueRelaxeException {
		ContatoAnuncianteDAO dao = daofactory.getContatoAnuncianteDAO(daofactory);
		dao.updateFilaAlerta24h(hashID,status);
	}


	public ContatoClienteDTO procurar(DAOFactory daofactory,
			ContatoClienteDTO dto) throws AlugueRelaxeException {
		ContatoAnuncianteDAO dao = daofactory.getContatoAnuncianteDAO(daofactory);
		ContatoClienteDTO dtoretorno = dao.load(dto);
		dtoretorno.primeiroNome = StringUtil.getPrimeiroNome(dtoretorno.proposto);
		dtoretorno.taxaComissao = Double.valueOf(VariavelCache.getInstance().getValor(VariavelConstantes.PERC_COMISSAO_PADRAO_TEMPORADA));
		
		// Carrega todas as thread envolvidas neste contato
		ContatoAnuncianteThreadBusiness catb = new ContatoAnuncianteThreadBusinessImpl();
		dtoretorno.lstThread = catb.listarThreads(daofactory, dto.id, "PR");
		
		//
		if ((dtoretorno.lstThread != null) && (dtoretorno.lstThread.size() > 0)){
			for (ContatoAnuncianteThreadDTO threaddto : dtoretorno.lstThread ){
				threaddto.threadfilho = catb.procurar(daofactory, threaddto.id);
			}
			
		}
		
		// Carrega todas as thread envolvidas neste contato
		//dtoretorno.lstComentarios = catb.listarComentarios(daofactory, dtoretorno.idImovel);
		dtoretorno.lstComentarios = catb.listarThreads(daofactory, dto.id, "CO");
		if ((dtoretorno.lstComentarios != null) && (dtoretorno.lstComentarios.size() > 0)){
			for (ContatoAnuncianteThreadDTO threaddto : dtoretorno.lstComentarios ){
				threaddto.threadfilho = catb.procurar(daofactory, threaddto.id);
			}
		}
		
		return dtoretorno;
	}

	public List<ContatoClienteDTO> buscarTodas(DAOFactory daofactory)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ContatoClienteDTO> buscarTodas(DAOFactory daofactory, int start)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public void liberarEmailContatoAnunciante(DAOFactory daofactory,
			String id) throws AlugueRelaxeException {
		ContatoClienteDTO ccdto = new ContatoClienteDTO();
		ccdto.id = id;
		ContatoClienteDTO dto = this.procurar(daofactory, ccdto);
		
		ImovelBusiness<ImovelDTO> ib = new ImovelBusinessImpl();
		ImovelDTO idto = new ImovelDTO();
		idto.id = dto.idImovel;
		idto = ib.procurar(daofactory, idto);
		
		ClienteBusiness<ClienteDTO> cb = new ClienteBusinessImpl();
		ClienteDTO cdto = new ClienteDTO();
		cdto.id = idto.idCliente;
		cdto = cb.procurar(daofactory, cdto);
		
		
		Map<String,String> conteudo = new HashMap<String, String>();
		conteudo.put(TemplateConstantes.TCAP_TAG_NOME_ANUNCIANTE, cdto.nome);
		conteudo.put(TemplateConstantes.TCAP_TAG_DATA_CONTATO, dto.dataCadastro.toString());
		conteudo.put(TemplateConstantes.TCAP_TAG_ID_IMOVEL, String.valueOf(dto.idImovel));
		conteudo.put(TemplateConstantes.TCAP_TAG_NOME_PROPOSTO, dto.proposto);
		conteudo.put(TemplateConstantes.TCAP_TAG_ID_IMOVEL, String.valueOf(dto.idImovel));
		conteudo.put(TemplateConstantes.TCAP_TAG_NOME_PROPOSTO, dto.proposto);
		conteudo.put(TemplateConstantes.TCAP_TAG_CIDADE_PROPOSTO, dto.cidade);
		conteudo.put(TemplateConstantes.TCAP_TAG_UF_PROPOSTO, dto.uf);
		conteudo.put(TemplateConstantes.TCAP_TAG_EMAIL_PROPOSTO, dto.email);
		conteudo.put(TemplateConstantes.TCAP_TAG_DDD_PROPOSTO, dto.ddd);
		conteudo.put(TemplateConstantes.TCAP_TAG_FONE_PROPOSTO, dto.telefone);
		try {
			String cp = DateManagerBase.getDateManagerInstance(dto.chegadaPrevista).getDateCustom("dd/MM/yyyy");
			String pp = DateManagerBase.getDateManagerInstance(dto.partidaPrevista).getDateCustom("dd/MM/yyyy");
			conteudo.put(TemplateConstantes.TCAP_TAG_CHEGADA_PREVISTA_PROPOSTO, cp);
			conteudo.put(TemplateConstantes.TCAP_TAG_PARTIDA_PREVISTA_PROPOSTO, pp);
		} catch (ParseException e) {
			conteudo.put(TemplateConstantes.TCAP_TAG_CHEGADA_PREVISTA_PROPOSTO, dto.chegadaPrevista.toString());
			conteudo.put(TemplateConstantes.TCAP_TAG_PARTIDA_PREVISTA_PROPOSTO, dto.partidaPrevista.toString());
		}
		
		
		conteudo.put(TemplateConstantes.TCAP_TAG_ADULTOS_PROPOSTO, String.valueOf(dto.qtdeAdultos));
		conteudo.put(TemplateConstantes.TCAP_TAG_CRIANCAS_PROPOSTO, String.valueOf(dto.qtdeCriancas));
		conteudo.put(TemplateConstantes.TCAP_TAG_INFO_ADICIONAL_PROPOSTO, dto.informacoesAdicionais);
		
		Email email = EmailFactory.getInstanceEmailContatoAnuncianteLiberado(conteudo);
		
		ArrayList<EmailRecord> lst = new ArrayList<EmailRecord>();
		lst.add(new EmailRecord(cdto.email,cdto.nome));

		email.enviar(lst, null, "AlugueRelaxe.com.br - Contato de Cliente no imovel #" + dto.idImovel, null);
	}

	public void liberarEmailContatoAnuncianteSimples(DAOFactory daofactory,
			String id) throws AlugueRelaxeException {
		ContatoClienteDTO ccdto = new ContatoClienteDTO();
		ccdto.id = id;
		ContatoClienteDTO dto = this.procurar(daofactory, ccdto);
		
		ImovelBusiness<ImovelDTO> ib = new ImovelBusinessImpl();
		ImovelDTO idto = new ImovelDTO();
		idto.id = dto.idImovel;
		idto = ib.procurar(daofactory, idto);
		
		ClienteBusiness<ClienteDTO> cb = new ClienteBusinessImpl();
		ClienteDTO cdto = new ClienteDTO();
		cdto.id = idto.idCliente;
		cdto = cb.procurar(daofactory, cdto);
		
		
		Map<String,String> conteudo = new HashMap<String, String>();
		conteudo.put(TemplateConstantes.TCAP_TAG_NOME_ANUNCIANTE, cdto.nome);
		conteudo.put(TemplateConstantes.TCAP_TAG_DATA_CONTATO, dto.dataCadastro.toString());
		conteudo.put(TemplateConstantes.TCAP_TAG_ID_IMOVEL, String.valueOf(dto.idImovel));
		conteudo.put(TemplateConstantes.TCAP_TAG_NOME_PROPOSTO, dto.proposto);
		conteudo.put(TemplateConstantes.TCAP_TAG_ID_IMOVEL, String.valueOf(dto.idImovel));
		conteudo.put(TemplateConstantes.TCAP_TAG_NOME_PROPOSTO, dto.proposto);
		conteudo.put(TemplateConstantes.TCAP_TAG_CIDADE_PROPOSTO, dto.cidade);
		conteudo.put(TemplateConstantes.TCAP_TAG_UF_PROPOSTO, dto.uf);
		conteudo.put(TemplateConstantes.TCAP_TAG_EMAIL_PROPOSTO, dto.email);
		conteudo.put(TemplateConstantes.TCAP_TAG_DDD_PROPOSTO, dto.ddd);
		conteudo.put(TemplateConstantes.TCAP_TAG_FONE_PROPOSTO, dto.telefone);
		try {
			String cp = DateManagerBase.getDateManagerInstance(dto.chegadaPrevista).getDateCustom("dd/MM/yyyy");
			String pp = DateManagerBase.getDateManagerInstance(dto.partidaPrevista).getDateCustom("dd/MM/yyyy");
			conteudo.put(TemplateConstantes.TCAP_TAG_CHEGADA_PREVISTA_PROPOSTO, cp);
			conteudo.put(TemplateConstantes.TCAP_TAG_PARTIDA_PREVISTA_PROPOSTO, pp);
		} catch (ParseException e) {
			conteudo.put(TemplateConstantes.TCAP_TAG_CHEGADA_PREVISTA_PROPOSTO, dto.chegadaPrevista.toString());
			conteudo.put(TemplateConstantes.TCAP_TAG_PARTIDA_PREVISTA_PROPOSTO, dto.partidaPrevista.toString());
		}
		
		
		conteudo.put(TemplateConstantes.TCAP_TAG_ADULTOS_PROPOSTO, String.valueOf(dto.qtdeAdultos));
		conteudo.put(TemplateConstantes.TCAP_TAG_CRIANCAS_PROPOSTO, String.valueOf(dto.qtdeCriancas));
		conteudo.put(TemplateConstantes.TCAP_TAG_INFO_ADICIONAL_PROPOSTO, dto.informacoesAdicionais);
		
		Email email = EmailFactory.getInstanceEmailContatoAnuncianteLiberadoSimples(conteudo);
		
		ArrayList<EmailRecord> lst = new ArrayList<EmailRecord>();
		lst.add(new EmailRecord(cdto.email,cdto.nome));

		email.enviar(lst, null, "AlugueRelaxe.com.br - Contato de Cliente no imovel #" + dto.idImovel, null);
	}

	public void liberarEmailInstrucoesCaptarAutorizacao(ContatoClienteDTO ccdto, ImovelFichaCompletaDTO ifcdto)
			throws AlugueRelaxeException {
		
		Map<String,String> conteudo = new HashMap<String, String>();
		if ((ifcdto.cliente.telefones != null) && (ifcdto.cliente.telefones.size() > 0)){
			StringBuilder sb = new StringBuilder();
			for (TelefoneDTO telefone : ifcdto.cliente.telefones) {
				sb.append(" (");
				sb.append(telefone.ddd);
				sb.append(")");
				sb.append(telefone.telefone);
			}
			conteudo.put(TemplateConstantes.TICA_TAG_PROPRIETARIO_TELEFONE, sb.toString());
		} else {
			conteudo.put(TemplateConstantes.TICA_TAG_PROPRIETARIO_TELEFONE, "SEM TELEFONE");
		}
		
		conteudo.put(TemplateConstantes.TICA_TAG_NOME_PROPRIETARIO, ifcdto.cliente.nome);
		conteudo.put(TemplateConstantes.TICA_TAG_TITULO_IMOVEL, ifcdto.imovel.descricaoTituloAnuncio);
		conteudo.put(TemplateConstantes.TICA_TAG_ENDERECO_COMPLETO_IMOVEL, ifcdto.imovel.endereco.toString());
		conteudo.put(TemplateConstantes.TICA_TAG_NOME_CONTATO, ccdto.proposto);
		conteudo.put(TemplateConstantes.TICA_TAG_CIDADE_CONTATO, ccdto.cidade);
		conteudo.put(TemplateConstantes.TICA_TAG_UF_CONTATO, ccdto.uf);
		conteudo.put(TemplateConstantes.TICA_TAG_TELEFONE, ccdto.ddd + "-" + ccdto.telefone);
		conteudo.put(TemplateConstantes.TICA_TAG_EMAIL_CONTATO, ccdto.email);
		conteudo.put(TemplateConstantes.TICA_TAG_UF_CONTATO, ccdto.uf);
		try {
			conteudo.put(TemplateConstantes.TICA_TAG_DATA_CHECKIN_CONTATO, DateManagerBase.getDateManagerInstance(ccdto.chegadaPrevista).getDateCustom("dd/MM/yyyy"));
			conteudo.put(TemplateConstantes.TICA_TAG_DATA_CHECKOUT_CONTATO, DateManagerBase.getDateManagerInstance(ccdto.partidaPrevista).getDateCustom("dd/MM/yyyy"));
		} catch (ParseException e) {
			conteudo.put(TemplateConstantes.TICA_TAG_DATA_CHECKIN_CONTATO, ccdto.chegadaPrevista.toString());
			conteudo.put(TemplateConstantes.TICA_TAG_DATA_CHECKOUT_CONTATO, ccdto.partidaPrevista.toString());
		}
		
		Email email = EmailFactory.getInstanceEmailInstrucoesCaptarAutorizacao(conteudo);
		
		ArrayList<EmailRecord> lst = new ArrayList<EmailRecord>();
		lst.add(new EmailRecord(VariavelCache.getInstance().getValor(VariavelConstantes.EMAIL_ADMIN_ALUGUERELAXE),"Administrador"));


		email.enviar(lst, null, "Passos para buscar aprovação de negociação Imovel #" + ifcdto.imovel.id, null);
	}

	public ContatoClienteDTO procurarProximoOferecimento(DAOFactory daofactory)
			throws AlugueRelaxeException {
		ContatoAnuncianteDAO dao = daofactory.getContatoAnuncianteDAO(daofactory);
		List<ContatoClienteDTO> lst = dao.loadProximoOferecimento();
		if (lst != null){
			return lst.get(0);
		} else {
			return null;
		}
	}

	public void atualizarOferecimento(DAOFactory daoFactory, String id)
			throws AlugueRelaxeException {
		ContatoAnuncianteDAO dao = daoFactory.getContatoAnuncianteDAO(daoFactory);
		long seq = daoFactory.getNextSequence(daoFactory, "SEQ_OFERECIMENTO");
		dao.updateOferecimento(id, seq);
	}

	public ContatoClienteDTO procurar(DAOFactory daoFactory, String hash)
			throws AlugueRelaxeException {
		ContatoAnuncianteDAO dao = daoFactory.getContatoAnuncianteDAO(daoFactory);
		ContatoClienteDTO dto = new ContatoClienteDTO();
		dto.id = hash;
		return this.procurar(daoFactory, dto);
		//return dao.load(dto);
	}
	
	public void atualizarStatusThread(DAOFactory daoFactory, String idContato, String status) throws AlugueRelaxeException {
		ContatoAnuncianteDAO dao = daoFactory.getContatoAnuncianteDAO(daoFactory);
		dao.updateStatusThread(idContato,status);
	}


}
