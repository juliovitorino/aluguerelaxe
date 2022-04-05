package br.com.jcv.aluguerelaxe.server;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.Constantes;
import br.com.jcv.aluguerelaxe.client.cliente.ClienteVO;
import br.com.jcv.aluguerelaxe.client.componente.galeria.GaleriaImovelRPC;
import br.com.jcv.aluguerelaxe.client.imovel.ImovelVO;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;
import br.com.jcv.aluguerelaxe.client.imovel.imagem.ImovelImagemVideoVO;
import br.com.jcv.aluguerelaxe.client.vo.EnderecoVO;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ImovelDTO;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;
import br.com.jcv.backend.imovel.ImovelServiceImpl;
import br.com.jcv.backend.imovel.imagem.ImovelImagemVideoDTO;
import br.com.jcv.backend.interfaces.services.ImovelService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class GaleriaImovelRPCImpl extends RemoteServiceServlet implements
		GaleriaImovelRPC {

	private static final Logger logger = Logger.getLogger(GaleriaImovelRPCImpl.class);
	private static final long serialVersionUID = -755544619559031310L;

	@SuppressWarnings("unchecked")
	public List<ImovelFichaCompletaVO> listarGaleriaImovel(long clienteId)
			throws AlugueRelaxeFrontException {
		
		List<ImovelFichaCompletaVO> lst = null;
		try {
			
			//----------------------------------------------------------
			// Obtem o caminho root_fotos das imagens dentro do web.xml
			//----------------------------------------------------------
			ServletContext context = getServletContext();
			String root_fotos = context.getInitParameter(Constantes.ROOT_FOTOS);
			logger.info("Path base para imagens:" + root_fotos);
			
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			List<ImovelFichaCompletaDTO> lstdto = new ArrayList<ImovelFichaCompletaDTO>();
			lstdto = is.listarGaleriaFotosImoveis(clienteId);
			if (lstdto != null){
				if (lstdto.size() > 0) {
					lst = new ArrayList<ImovelFichaCompletaVO>();
					for (ImovelFichaCompletaDTO ifcdto : lstdto) {
						
						ImovelFichaCompletaVO ifcvo = new ImovelFichaCompletaVO();
						copiaDadosCliente(ifcvo, ifcdto);
						copiaDadosImovel(ifcvo,ifcdto);
						copiaImagensTB(ifcvo,ifcdto,root_fotos);
						
						lst.add(ifcvo);
					}
				}
			}
			
		} catch (AlugueRelaxeException e) {
			logger.error(e);
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem());
		}
		
		return lst;

	}

	public List<ImovelFichaCompletaVO> listarGaleriaImovel(String indPatrocinadorColaborador)
			throws AlugueRelaxeFrontException {
		
		List<ImovelFichaCompletaVO> lst = null;
		try {
			
			//----------------------------------------------------------
			// Obtem o caminho root_fotos das imagens dentro do web.xml
			//----------------------------------------------------------
			ServletContext context = getServletContext();
			String root_fotos = context.getInitParameter(Constantes.ROOT_FOTOS);
			logger.info("Path base para imagens:" + root_fotos);
			
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			List<ImovelFichaCompletaDTO> lstdto = new ArrayList<ImovelFichaCompletaDTO>();
			lstdto = is.listarGaleriaFotosImoveis(indPatrocinadorColaborador);
			if (lstdto != null){
				if (lstdto.size() > 0) {
					lst = new ArrayList<ImovelFichaCompletaVO>();
					for (ImovelFichaCompletaDTO ifcdto : lstdto) {
						
						ImovelFichaCompletaVO ifcvo = new ImovelFichaCompletaVO();
						copiaDadosCliente(ifcvo, ifcdto);
						copiaDadosImovel(ifcvo,ifcdto);
						copiaImagensTB(ifcvo,ifcdto,root_fotos);
						
						lst.add(ifcvo);
					}
				}
			}
			
		} catch (AlugueRelaxeException e) {
			logger.error(e);
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem());
		}
		
		return lst;

	}
	
	private void copiaImagensTB(ImovelFichaCompletaVO ifcvo, ImovelFichaCompletaDTO ifcdto, String root_fotos) {
		if (ifcdto.imagensImovelTB != null) {
			ifcvo.imagensImovelTB = new ArrayList<ImovelImagemVideoVO>();
			for (int i = 0; i < ifcdto.imagensImovelTB.size(); i++){
				ImovelImagemVideoDTO iivdto = (ImovelImagemVideoDTO) ifcdto.imagensImovelTB.get(i); 
				ImovelImagemVideoVO iivvo = new ImovelImagemVideoVO();
				iivvo.id = iivdto.id;
				iivvo.nome = root_fotos.concat(String.valueOf(ifcvo.cliente.id)).concat("/").concat(String.valueOf(ifcvo.imovel.id)).concat("/").concat(iivdto.nome);
				iivvo.tipo = iivdto.tipo;
				ifcvo.imagensImovelTB.add(iivvo);
			}
		}
	}

	private void copiaDadosImovel(ImovelFichaCompletaVO ifcvo, ImovelFichaCompletaDTO ifcdto) {
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
		ifcvo.imovel.endereco.nome = ifcdto.imovel.endereco.nome;
		ifcvo.imovel.endereco.numero = ifcdto.imovel.endereco.numero;
		ifcvo.imovel.endereco.complemento = ifcdto.imovel.endereco.complemento;
		ifcvo.imovel.endereco.bairro = ifcdto.imovel.endereco.bairro;
		ifcvo.imovel.endereco.cep = ifcdto.imovel.endereco.cep;
		ifcvo.imovel.dataCadastro = ifcdto.imovel.dataCadastro;
		ifcvo.imovel.dataAtualizacao = ifcdto.imovel.dataAtualizacao;
		ifcvo.imovel.descricaoTituloAnuncio = ifcdto.imovel.descricaoTituloAnuncio;
		ifcvo.imovel.indicadorStatus = ifcdto.imovel.indicadorStatus;
		ifcvo.imovel.indicadorMostraTabelaPreco = ifcdto.imovel.indicadorMostraTabelaPreco;
		ifcvo.imovel.dataUltimaVisita = ifcdto.imovel.dataUltimaVisita;
		ifcvo.imovel.indicadorPermiteAlugarFestas = ifcdto.imovel.indicadorPermiteAlugarFestas;
	}

	private void copiaDadosCliente(ImovelFichaCompletaVO ifcvo, ImovelFichaCompletaDTO ifcdto) {
		 ifcvo.cliente = new ClienteVO();
		 ifcvo.cliente.id = ifcdto.cliente.id;
	}

}
