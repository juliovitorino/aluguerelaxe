package br.com.jcv.aluguerelaxe.server;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.Constantes;
import br.com.jcv.aluguerelaxe.client.imovel.ImovelVO;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;
import br.com.jcv.aluguerelaxe.client.imovel.imagem.ImovelImagemVideoVO;
import br.com.jcv.aluguerelaxe.client.painel.PainelUltimosCadastradosRPC;
import br.com.jcv.aluguerelaxe.client.vo.EnderecoVO;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ImovelDTO;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;
import br.com.jcv.backend.imovel.ImovelServiceImpl;
import br.com.jcv.backend.imovel.imagem.ImovelImagemVideoDTO;
import br.com.jcv.backend.interfaces.services.ImovelService;
import br.com.jcv.backend.utility.StringUtil;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class PainelUltimosCadastradosRPCImpl extends RemoteServiceServlet implements
		PainelUltimosCadastradosRPC {

	private static final long serialVersionUID = 6545847126578467431L;

	@SuppressWarnings("unchecked")
	public List<ImovelFichaCompletaVO> listarUltimosImoveisCadastrados()
			throws AlugueRelaxeFrontException {
		List<ImovelFichaCompletaVO> lst = null;
		try {
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			List<ImovelFichaCompletaDTO> lstretorno = is.listarUltimosImoveisCadastrados();
			//List<ImovelFichaCompletaDTO> lstretorno = UltimosImoveisCadastradosCache.getInstance().getCache();
			if (lstretorno != null){
				lst = new ArrayList<ImovelFichaCompletaVO>();
				for (ImovelFichaCompletaDTO ifcdto : lstretorno) {
					ImovelFichaCompletaVO ifcvo = new ImovelFichaCompletaVO();
					obtemInformacoesImovel(ifcvo, ifcdto);
					obtemInformacoesMI(ifcvo, ifcdto);
					obtemInformacoesTB(ifcvo, ifcdto);
					lst.add(ifcvo);
				}
			}
			
		} catch (AlugueRelaxeException e) {
			// TODO: handle exception
		} 
		
		return lst;

	}
	
	private void obtemInformacoesMI(ImovelFichaCompletaVO ifcvo,
			ImovelFichaCompletaDTO ifcdto) {
		if ((ifcdto != null) && 
			(ifcdto.imagensImovelMI != null) && 
			(ifcdto.imagensImovelMI.size() > 0)) {
			
			//----------------------------------------------------------
			// Obtem o caminho root_fotos das imagens dentro do web.xml
			//----------------------------------------------------------
			ServletContext context = getServletContext();
			String root_fotos = context.getInitParameter(Constantes.ROOT_FOTOS);
			
			ifcvo.imagensImovelMI = new ArrayList<ImovelImagemVideoVO>();
			for (ImovelImagemVideoDTO iivdto : ifcdto.imagensImovelMI ) {
				ImovelImagemVideoVO iivvo = new ImovelImagemVideoVO();
				iivvo.id = iivdto.id;
				iivvo.nome = root_fotos.concat(String.valueOf(ifcvo.imovel.idCliente)).concat("/").concat(String.valueOf(ifcvo.imovel.id)).concat("/").concat(iivdto.nome);				
				iivvo.tipo = iivdto.tipo;
				ifcvo.imagensImovelMI.add(iivvo);
			}
		}
	}

	private void obtemInformacoesTB(ImovelFichaCompletaVO ifcvo,
			ImovelFichaCompletaDTO ifcdto) {
		if ((ifcdto != null) && 
			(ifcdto.imagensImovelTB != null) && 
			(ifcdto.imagensImovelTB.size() > 0)) {
			
			//----------------------------------------------------------
			// Obtem o caminho root_fotos das imagens dentro do web.xml
			//----------------------------------------------------------
			ServletContext context = getServletContext();
			String root_fotos = context.getInitParameter(Constantes.ROOT_FOTOS);
			
			ifcvo.imagensImovelTB = new ArrayList<ImovelImagemVideoVO>();
			for (ImovelImagemVideoDTO iivdto : ifcdto.imagensImovelTB ) {
				ImovelImagemVideoVO iivvo = new ImovelImagemVideoVO();
				iivvo.id = iivdto.id;
				iivvo.nome = root_fotos.concat(String.valueOf(ifcvo.imovel.idCliente)).concat("/").concat(String.valueOf(ifcvo.imovel.id)).concat("/").concat(iivdto.nome);				
				iivvo.tipo = iivdto.tipo;
				ifcvo.imagensImovelTB.add(iivvo);
			}
		}
	}


	private void obtemInformacoesImovel(ImovelFichaCompletaVO ifcvo,
			ImovelFichaCompletaDTO ifcdto) {
		ifcvo.imovel = new ImovelVO();
		ifcvo.imovel.id = ifcdto.imovel.id;
		ifcvo.imovel.idCliente = ifcdto.imovel.idCliente;
		ifcvo.imovel.qtdeQuartos = ifcdto.imovel.qtdeQuartos;
		ifcvo.imovel.qtdeSuites = ifcdto.imovel.qtdeSuites;
		ifcvo.imovel.qtdeBanheiros = ifcdto.imovel.qtdeBanheiros;
		ifcvo.imovel.qtdeCapacidade = ifcdto.imovel.qtdeCapacidade;
		ifcvo.imovel.descricaoGeral = ifcdto.imovel.descricaoGeral;
		ifcvo.imovel.descricaoQuartos = ifcdto.imovel.descricaoQuartos;
		ifcvo.imovel.descricaoArredores = ifcdto.imovel.descricaoArredores;
		ifcvo.imovel.observacoes = ifcdto.imovel.observacoes;
		ifcvo.imovel.indicadorTipoPropriedade = ifcdto.imovel.indicadorTipoPropriedade;
		ifcvo.imovel.qtdeVisitas = ifcdto.imovel.qtdeVisitas;
		ifcvo.imovel.endereco = new EnderecoVO();
		ifcvo.imovel.endereco.logradouro = StringUtil.splitEndereco(ifcdto.imovel.endereco.nome,StringUtil.TIPO_RETORNO_LOGRADOURO) ;
		ifcvo.imovel.endereco.nome = StringUtil.splitEndereco(ifcdto.imovel.endereco.nome,StringUtil.TIPO_RETORNO_ENDERECO);
		ifcvo.imovel.endereco.numero = ifcdto.imovel.endereco.numero;
		ifcvo.imovel.endereco.complemento = ifcdto.imovel.endereco.complemento;
		ifcvo.imovel.endereco.bairro = ifcdto.imovel.endereco.bairro;
		ifcvo.imovel.endereco.cep = ifcdto.imovel.endereco.cep;
		ifcvo.imovel.endereco.codigoUfCidadeItem = ifcdto.imovel.endereco.codigoUfCidadeItem;
		ifcvo.imovel.endereco.cidade = ifcdto.imovel.endereco.cidade;
		ifcvo.imovel.endereco.uf = ifcdto.imovel.endereco.uf;
		ifcvo.imovel.dataCadastro = ifcdto.imovel.dataCadastro;
		ifcvo.imovel.dataAtualizacao = ifcdto.imovel.dataAtualizacao;
		ifcvo.imovel.descricaoTituloAnuncio = ifcdto.imovel.descricaoTituloAnuncio;
		ifcvo.imovel.indicadorStatus = ifcdto.imovel.indicadorStatus;
		ifcvo.imovel.indicadorMostraTabelaPreco = ifcdto.imovel.indicadorMostraTabelaPreco;
		ifcvo.imovel.dataUltimaVisita = ifcdto.imovel.dataUltimaVisita;
		ifcvo.imovel.indicadorPermiteAlugarFestas = ifcdto.imovel.indicadorPermiteAlugarFestas;
	}




}
