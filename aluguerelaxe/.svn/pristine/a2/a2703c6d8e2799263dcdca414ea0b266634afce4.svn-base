
package br.com.jcv.aluguerelaxe.server;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import br.com.jcv.aluguerelaxe.client.Constantes;
import br.com.jcv.aluguerelaxe.client.cliente.ClienteVO;
import br.com.jcv.aluguerelaxe.client.componente.datagrid.CondicaoVO;
import br.com.jcv.aluguerelaxe.client.componente.datagrid.EnvelopePaginacaoDataGridVO;
import br.com.jcv.aluguerelaxe.client.componente.paginacao.PaginacaoRPC;
import br.com.jcv.aluguerelaxe.client.imovel.EnvelopePaginacaoVO;
import br.com.jcv.aluguerelaxe.client.imovel.ImovelVO;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.TipoColaboracaoVO;
import br.com.jcv.aluguerelaxe.client.imovel.imagem.ImovelImagemVideoVO;
import br.com.jcv.aluguerelaxe.client.vo.EnderecoVO;
import br.com.jcv.aluguerelaxe.server.datatransferobject.DTOCondicaoImpl;
import br.com.jcv.aluguerelaxe.server.datatransferobject.DTOEnvelopePaginacaoDataGridImpl;
import br.com.jcv.aluguerelaxe.server.paginacao.BuscaPagina;
import br.com.jcv.aluguerelaxe.server.paginacao.BuscaPaginaFactory;
import br.com.jcv.aluguerelaxe.server.paginacao.EnvelopePaginacaoDTO;
import br.com.jcv.backend.datemanager.DateManager;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;
import br.com.jcv.backend.imovel.imagem.ImovelImagemVideoDTO;
import br.com.jcv.backend.paginacao.CondicaoDTO;
import br.com.jcv.backend.paginacao.EnvelopePaginacaoDataGridDTO;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * <h2>PaginacaoRPCImpl</h2>
 * <p>Classe que representa todas as chamadas RPC para controle de paginação e 
 * a comunicação com os serviços de backend.</p>
 * @author Júlio Vitorino
 */
public class PaginacaoRPCImpl extends RemoteServiceServlet 
		implements PaginacaoRPC {

	private static final long serialVersionUID = 6606204253078031981L;

	public EnvelopePaginacaoDataGridVO buscarPagina(String tabelaView, 
			List<String> campos, 
			Map<String,String> param,  
			List<CondicaoVO> condicao,
			List<String> lstOrderBy, 
			int indice, 
			int qtdePorPagina) {
		EnvelopePaginacaoDataGridVO retorno = null;

		//----------------------------------------------------------
		// Obtem o caminho root_fotos das imagens dentro do web.xml
		//----------------------------------------------------------
		//ServletContext context = getServletContext();
		//String root_fotos = context.getInitParameter(Constantes.ROOT_FOTOS);
		//logger.info("Path base para imagens:" + root_fotos);
		
		//----------------------------------------------------------
		// Devolve uma implementação de BuscaPagina para o
		// mapa correto.
		//----------------------------------------------------------
		BuscaPagina<?> bp = BuscaPaginaFactory.getInstance(param);
		
		// Converte o Map em List<CondicaoDTO>
		List<CondicaoDTO> paramWhere = null;
		if ((condicao != null) && (condicao.size() > 0)){
			paramWhere = new ArrayList<CondicaoDTO>();
			for (CondicaoVO vo : condicao){
				CondicaoDTO dto =  (new DTOCondicaoImpl()).getDataTransferObject(vo);
				paramWhere.add(dto);
				
			}
		}

		EnvelopePaginacaoDataGridDTO envelope = bp.execute(tabelaView, campos, paramWhere, lstOrderBy, indice, qtdePorPagina);
		if (envelope != null) {
			retorno = (new DTOEnvelopePaginacaoDataGridImpl()).getDataTransferObject(envelope);
		}

		return retorno;	
	}

	public EnvelopePaginacaoVO buscarPagina(Map<String,String> param, int indice, int qtdePorPagina) {
		EnvelopePaginacaoVO lstretorno = null;

		//----------------------------------------------------------
		// Obtem o caminho root_fotos das imagens dentro do web.xml
		//----------------------------------------------------------
		ServletContext context = getServletContext();
		String root_fotos = context.getInitParameter(Constantes.ROOT_FOTOS);
		//logger.info("Path base para imagens:" + root_fotos);
		
		//----------------------------------------------------------
		// Devolve uma implementação de BuscaPagina para o
		// mapa correto.
		//----------------------------------------------------------
		BuscaPagina bp = BuscaPaginaFactory.getInstance(param);

		EnvelopePaginacaoDTO<ImovelFichaCompletaDTO> envelope = bp.execute(param, indice, qtdePorPagina);
		if (envelope != null) {
			lstretorno = new EnvelopePaginacaoVO();
			lstretorno.totalRegistros = envelope.totalRegistros;
			lstretorno.indice = indice;
			if (envelope.lst != null){
				lstretorno.lst = new ArrayList<ImovelFichaCompletaVO>();
				
				for (ImovelFichaCompletaDTO ifcdto : envelope.lst) {
					ImovelFichaCompletaVO ifcvo = new ImovelFichaCompletaVO();
					obtemInformacoesImovel(ifcvo, ifcdto);
					obtemInformacoesTipoColaboracao(ifcvo, ifcdto);
					obtemInformacoesCliente(ifcvo, ifcdto);
					obtemImagensImovelTB(ifcvo, root_fotos, ifcdto);
					lstretorno.lst.add(ifcvo);
				}
			}
		}

		return lstretorno;	
	}


	private void obtemInformacoesTipoColaboracao(ImovelFichaCompletaVO ifcvo,
			ImovelFichaCompletaDTO ifcdto) {
		ifcvo.tipoColaboracao = new TipoColaboracaoVO();
		ifcvo.tipoColaboracao.indicadorTipoColaboracao = ifcdto.tipoColaboracao.indicadorTipoColaboracao;
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
		ifcvo.imovel.endereco.nomeuf = ifcdto.imovel.endereco.nomeuf;
		ifcvo.imovel.dataCadastro = ifcdto.imovel.dataCadastro;
		ifcvo.imovel.dataAtualizacao = ifcdto.imovel.dataAtualizacao;
		ifcvo.imovel.descricaoTituloAnuncio = ifcdto.imovel.descricaoTituloAnuncio;
		ifcvo.imovel.indicadorStatus = ifcdto.imovel.indicadorStatus;
		ifcvo.imovel.indicadorMostraTabelaPreco = ifcdto.imovel.indicadorMostraTabelaPreco;
		ifcvo.imovel.dataUltimaVisita = ifcdto.imovel.dataUltimaVisita;
		ifcvo.imovel.indicadorCondominio = ifcdto.imovel.indicadorCondominio;
		ifcvo.imovel.indicadorPermiteAlugarFestas = ifcdto.imovel.indicadorPermiteAlugarFestas;
		ifcvo.imovel.valorTarifaBasica = String.valueOf(ifcdto.imovel.valorTarifaBasica);
		
		try {
			ifcvo.imovel.dataCadastroStr = (DateManager.getDateManagerInstance(ifcdto.imovel.dataCadastro)).getDateCustom("dd/MM/yyyy");
		} catch (ParseException e) {
			ifcvo.imovel.dataCadastroStr = "00/00/0000";
		}
		
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

	

}

