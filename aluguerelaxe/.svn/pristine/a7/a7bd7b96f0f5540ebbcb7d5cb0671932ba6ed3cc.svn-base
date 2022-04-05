package br.com.jcv.aluguerelaxe.server;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

import br.com.jcv.aluguerelaxe.client.Constantes;
import br.com.jcv.aluguerelaxe.client.cliente.ClienteVO;
import br.com.jcv.aluguerelaxe.client.imovel.ImovelVO;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;
import br.com.jcv.aluguerelaxe.client.imovel.imagem.ImovelImagemVideoVO;
import br.com.jcv.aluguerelaxe.client.imovel.listagem.uf.ListaImovelUfRPC;
import br.com.jcv.aluguerelaxe.client.vo.CidadeUFVO;
import br.com.jcv.aluguerelaxe.client.vo.CidadeVO;
import br.com.jcv.aluguerelaxe.client.vo.EnderecoVO;
import br.com.jcv.backend.dto.CidadeUFDTO;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;
import br.com.jcv.backend.imovel.ImovelServiceImpl;
import br.com.jcv.backend.imovel.imagem.ImovelImagemVideoDTO;
import br.com.jcv.backend.interfaces.services.ImovelService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ListaImovelUfRPCImpl extends RemoteServiceServlet implements
		ListaImovelUfRPC {

	/**
	 * <h2>logger</h2>
	 * <p>logger estático para log4j</p>
	 */
	private static final Logger logger = Logger.getLogger(ListaImovelUfRPCImpl.class.getName());

	private static final long serialVersionUID = -7240548626423840653L;

	public List<ImovelFichaCompletaVO> buscaListaImovelUf(String uf, int pagina) {
		List<ImovelFichaCompletaVO> lstretorno = null;

		//----------------------------------------------------------
		// Obtem o caminho root_fotos das imagens dentro do web.xml
		//----------------------------------------------------------
		ServletContext context = getServletContext();
		String root_fotos = context.getInitParameter(Constantes.ROOT_FOTOS);
		logger.info("Path base para imagens:" + root_fotos);
		
		ImovelService is = new ImovelServiceImpl();
		try {
			List<ImovelFichaCompletaDTO> lst = is.listaImoveisPorEstado(uf, pagina,10);
			if (lst != null) {
				lstretorno = new ArrayList<ImovelFichaCompletaVO>();
				
				for (ImovelFichaCompletaDTO ifcdto : lst) {
					ImovelFichaCompletaVO ifcvo = new ImovelFichaCompletaVO();
					obtemInformacoesImovel(ifcvo, ifcdto);
					obtemInformacoesCliente(ifcvo, ifcdto);
					obtemImagensImovelTB(ifcvo, root_fotos, ifcdto);
					lstretorno.add(ifcvo);
				}
			}
		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lstretorno;
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
		ifcvo.imovel.endereco.nome = ifcdto.imovel.endereco.nome;
		ifcvo.imovel.endereco.numero = ifcdto.imovel.endereco.numero;
		ifcvo.imovel.endereco.complemento = ifcdto.imovel.endereco.complemento;
		ifcvo.imovel.endereco.bairro = ifcdto.imovel.endereco.bairro;
		ifcvo.imovel.endereco.cep = ifcdto.imovel.endereco.cep;
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

	private void obtemImagensImovelTB(ImovelFichaCompletaVO ifcvo,
			String root_fotos, ImovelFichaCompletaDTO ifcdto) {
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

	private void obtemInformacoesCliente(ImovelFichaCompletaVO ifcvo,
			ImovelFichaCompletaDTO ifcdto) {
		ifcvo.cliente = new ClienteVO();
		ifcvo.cliente.id = ifcdto.cliente.id;
	}

	public List<CidadeUFVO> listarQtdeImoveisCidadeUF() {
		List<CidadeUFVO> lstretorno = null;

		ImovelService is = new ImovelServiceImpl();
		try {
			List<CidadeUFDTO> lst = is.listarQtdeImoveisCidadeUF();
			if (lst != null) {
				lstretorno = new ArrayList<CidadeUFVO>();
				
				for (CidadeUFDTO ifcdto : lst) {
					CidadeUFVO ifcvo = new CidadeUFVO();
					ifcvo.id = ifcdto.id;
					ifcvo.qtdeImoveis = ifcdto.qtdeImoveis;
					ifcvo.cidade = new CidadeVO();
					ifcvo.cidade.id = ifcdto.cidade.id;  
					ifcvo.cidade.nome = ifcdto.cidade.nome;
					ifcvo.uf = ifcdto.uf;
					lstretorno.add(ifcvo);
				}
			}
		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lstretorno;	
	}

}
