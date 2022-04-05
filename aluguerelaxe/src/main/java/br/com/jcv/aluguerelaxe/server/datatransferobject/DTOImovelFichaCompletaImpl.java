package br.com.jcv.aluguerelaxe.server.datatransferobject;

import java.text.ParseException;
import java.util.ArrayList;

import br.com.jcv.aluguerelaxe.client.administracao.console.geolocalizacaowizard.GeoLocalizacaoVO;
import br.com.jcv.aluguerelaxe.client.caracteristica.CaracteristicaVO;
import br.com.jcv.aluguerelaxe.client.cliente.ClienteVO;
import br.com.jcv.aluguerelaxe.client.cliente.TelefoneVO;
import br.com.jcv.aluguerelaxe.client.imovel.ImovelPlanoVO;
import br.com.jcv.aluguerelaxe.client.imovel.ImovelVO;
import br.com.jcv.aluguerelaxe.client.imovel.caracteristica.ImovelCaracteristicaVO;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;
import br.com.jcv.aluguerelaxe.client.imovel.imagem.ImovelImagemVideoVO;
import br.com.jcv.aluguerelaxe.client.imovel.tabelapreco.TabelaPrecoVO;
import br.com.jcv.aluguerelaxe.client.plano.PlanoVO;
import br.com.jcv.aluguerelaxe.client.vo.EnderecoVO;
import br.com.jcv.backend.cliente.TelefoneDTO;
import br.com.jcv.backend.datatransfer.AbstractDataTransferObject;
import br.com.jcv.backend.datemanager.DateManager;
import br.com.jcv.backend.datemanager.DateManagerBase;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;
import br.com.jcv.backend.imovel.caracteristica.ImovelCaracteristicaDTO;
import br.com.jcv.backend.imovel.imagem.ImovelImagemVideoDTO;
import br.com.jcv.backend.imovel.tabelapreco.TabelaPrecoDTO;
import br.com.jcv.backend.utility.StringUtil;

public class DTOImovelFichaCompletaImpl
		implements
		AbstractDataTransferObject<ImovelFichaCompletaDTO, ImovelFichaCompletaVO> {
	
	private String path;
	
	public DTOImovelFichaCompletaImpl(String path){
		this.path = path;
	}

	public DTOImovelFichaCompletaImpl(){
	}

	public void setRootPath(String path){
		this.path = path;
	}
	
	public ImovelFichaCompletaVO getDataTransferObject(
			ImovelFichaCompletaDTO ifcdto) {
		
		ImovelFichaCompletaVO ifcvo = new ImovelFichaCompletaVO();
		
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
		ifcvo.imovel.indicadorCondominio = ifcdto.imovel.indicadorCondominio;
		ifcvo.imovel.valorTarifaBasica = String.valueOf(ifcdto.imovel.valorTarifaBasica);

		ifcvo.geolocalizacao = new GeoLocalizacaoVO();
		ifcvo.geolocalizacao.latitude = ifcdto.geolocalizacao.latitude;
		ifcvo.geolocalizacao.longitude = ifcdto.geolocalizacao.longitude;
		
		ifcvo.cliente = (new DTOClienteImpl()).getDataTransferObject(ifcdto.cliente);
		
/*		ifcvo.cliente = new ClienteVO();
		ifcvo.cliente.id = ifcdto.cliente.id;
		ifcvo.cliente.nome = ifcdto.cliente.nome;
		ifcvo.cliente.cpf = ifcdto.cliente.cpf;
		ifcvo.cliente.cgc = ifcdto.cliente.cgc;
		
		ifcvo.cliente.fotoPerfil = ifcdto.cliente.fotoPerfil;
		ifcvo.cliente.fotoChat = ifcdto.cliente.fotoChat;
		ifcvo.cliente.primeiroNome = ifcdto.cliente.primeiroNome;
		ifcvo.cliente.pathPerfil = ifcdto.cliente.pathPerfil;
		ifcvo.cliente.fotoPerfil = ifcdto.cliente.fotoPerfil;
		ifcvo.cliente.fotoChat = ifcdto.cliente.fotoChat;
		ifcvo.cliente.indicadorVerificado = ifcdto.cliente.indicadorVerificado;
		ifcvo.cliente.taxaResposta = ifcdto.cliente.taxaResposta;
		ifcvo.cliente.totalPergunta = ifcdto.cliente.totalPergunta;
		ifcvo.cliente.totalResposta = ifcdto.cliente.totalResposta;
		ifcvo.cliente.membroDesde = DateManagerBase.getDateManagerInstance(ifcdto.cliente.dataCadastro).getMonth() +
									"/" +
									DateManagerBase.getDateManagerInstance(ifcdto.cliente.dataCadastro).getYear();
		ifcvo.cliente.dataNascimento = ifcdto.cliente.dataNascimento;
		ifcvo.cliente.email = ifcdto.cliente.email;
		ifcvo.cliente.dataCadastro = ifcdto.cliente.dataCadastro;
		ifcvo.cliente.dataAtualizacao = ifcdto.cliente.dataAtualizacao;
		ifcvo.cliente.indicadorStatus = ifcdto.cliente.indicadorStatus;
		ifcvo.cliente.urlwww = ifcdto.cliente.urlwww;
		ifcvo.cliente.indicadorAutorizaNotificacao = ifcdto.cliente.indicadorAutorizaNotificacao;
		ifcvo.cliente.msn = ifcdto.cliente.msn;
		ifcvo.cliente.skype = ifcdto.cliente.skype;
		ifcvo.cliente.googleTalk = ifcdto.cliente.googleTalk;
		ifcvo.cliente.indicadorTipoAnunciante = ifcdto.cliente.indicadorTipoAnunciante;
		ifcvo.cliente.endereco = new EnderecoVO();
		ifcvo.cliente.endereco.nome = ifcdto.cliente.endereco.nome;
		ifcvo.cliente.endereco.numero = ifcdto.cliente.endereco.numero;
		ifcvo.cliente.endereco.complemento = ifcdto.cliente.endereco.complemento;
		ifcvo.cliente.endereco.bairro = ifcdto.cliente.endereco.bairro;
		ifcvo.cliente.endereco.cep = ifcdto.cliente.endereco.cep;
		if (ifcdto.cliente.telefones != null){
			if (ifcdto.cliente.telefones.size() > 0) {
				ifcvo.cliente.telefones = new ArrayList<TelefoneVO>();
				for (TelefoneDTO telefonedto : ifcdto.cliente.telefones) {
					TelefoneVO telefonevo = new TelefoneVO();
					telefonevo.nomeContato = telefonedto.nomeContato;
					telefonevo.ddd = telefonedto.ddd;
					telefonevo.telefone = telefonedto.telefone;
					telefonevo.indPermiteExibir = telefonedto.indPermiteExibir;
					telefonevo.indTipoTelefone = telefonedto.indTipoTelefone;
					ifcvo.cliente.telefones.add(telefonevo);
				}
			}
		}
*/
		if (ifcdto.caracteristicaImovel != null) {
			ifcvo.caracteristicaImovel = new ArrayList<ImovelCaracteristicaVO>();
			for (int i = 0; i < ifcdto.caracteristicaImovel.size(); i++){
				ImovelCaracteristicaDTO iivdto = (ImovelCaracteristicaDTO) ifcdto.caracteristicaImovel.get(i); 
				ImovelCaracteristicaVO iivvo = new ImovelCaracteristicaVO();
				iivvo.id = iivdto.id;
				iivvo.caracteristica = new CaracteristicaVO();
				iivvo.caracteristica.id = iivdto.caracteristica.id;
				iivvo.caracteristica.nome = iivdto.caracteristica.nome;
				iivvo.caracteristica.descricaoAnuncio = iivdto.caracteristica.descricaoAnuncio;
				ifcvo.caracteristicaImovel.add(iivvo);
			}
		}

		if (ifcdto.caracteristicaCondominio != null) {
			ifcvo.caracteristicaCondominio = new ArrayList<ImovelCaracteristicaVO>();
			for (int i = 0; i < ifcdto.caracteristicaCondominio.size(); i++){
				ImovelCaracteristicaDTO iivdto = (ImovelCaracteristicaDTO) ifcdto.caracteristicaCondominio.get(i); 
				ImovelCaracteristicaVO iivvo = new ImovelCaracteristicaVO();
				iivvo.id = iivdto.id;
				iivvo.caracteristica = new CaracteristicaVO();
				iivvo.caracteristica.id = iivdto.caracteristica.id;
				iivvo.caracteristica.nome = iivdto.caracteristica.nome;
				iivvo.caracteristica.descricaoAnuncio = iivdto.caracteristica.descricaoAnuncio;
				ifcvo.caracteristicaCondominio.add(iivvo);
			}
		}

		if (ifcdto.tabelaPreco != null) {
			ifcvo.tabelaPreco = new ArrayList<TabelaPrecoVO>();

			// Adiciona tarifa basica no primeiro elemento
			if (ifcdto.imovel.valorTarifaBasica > 0){
				TabelaPrecoVO iivvo = new TabelaPrecoVO();
				iivvo.periodo = "DIARIA BASICA";
				iivvo.valorTabela = ifcdto.imovel.valorTarifaBasica;
				ifcvo.tabelaPreco.add(iivvo);
			}

			for (int i = 0; i < ifcdto.tabelaPreco.size(); i++){
				TabelaPrecoDTO iivdto = (TabelaPrecoDTO) ifcdto.tabelaPreco.get(i); 
				TabelaPrecoVO iivvo = new TabelaPrecoVO();
				iivvo.id = iivdto.id;
				iivvo.dataFim = iivdto.dataFim;
				iivvo.dataInicio = iivdto.dataInicio;
				iivvo.observacao = iivdto.observacao;
				iivvo.minimoDe = iivdto.textoMinimoDe;
				iivvo.periodo = iivdto.textoPeriodo;
				iivvo.valorTabela = iivdto.valorTabela;
				ifcvo.tabelaPreco.add(iivvo);
				try {
					iivvo.dataInicioStr = DateManager.getDateManagerInstance(iivdto.dataInicio).getDateCustom("dd/MM/yyyy");
					iivvo.dataFimStr = DateManager.getDateManagerInstance(iivdto.dataFim).getDateCustom("dd/MM/yyyy");
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}

		ifcvo.imovelPlano = new ImovelPlanoVO();
		ifcvo.imovelPlano.plano = new PlanoVO();
		ifcvo.imovelPlano.id = ifcdto.imovelPlano.id;
		ifcvo.imovelPlano.plano.id = ifcdto.imovelPlano.plano.id;
		ifcvo.imovelPlano.plano.descricao = ifcdto.imovelPlano.plano.descricao;
		ifcvo.imovelPlano.plano.nome = ifcdto.imovelPlano.plano.nome;
		ifcvo.imovelPlano.plano.indicadorStatus = ifcdto.imovelPlano.plano.indicadorStatus;
		ifcvo.imovelPlano.plano.indicadorPeriodicidade = ifcdto.imovelPlano.plano.indicadorPeriodicidade;
		ifcvo.imovelPlano.plano.valor = ifcdto.imovelPlano.plano.valor;
		
		ifcvo.indCentralReserva = ifcdto.indCentralReserva;
		
		if (ifcdto.imagensImovelTB != null) {
			ifcvo.imagensImovelTB = new ArrayList<ImovelImagemVideoVO>();
			for (int i = 0; i < ifcdto.imagensImovelTB.size(); i++){
				ImovelImagemVideoDTO iivdto = (ImovelImagemVideoDTO) ifcdto.imagensImovelTB.get(i); 
				ImovelImagemVideoVO iivvo = new ImovelImagemVideoVO();
				iivvo.id = iivdto.id;
				iivvo.nome = path.concat(String.valueOf(ifcvo.cliente.id)).concat("/").concat(String.valueOf(ifcvo.imovel.id)).concat("/").concat(iivdto.nome);
				iivvo.tipo = iivdto.tipo;
				ifcvo.imagensImovelTB.add(iivvo);
			}
		}

		
		return ifcvo;
	}

	public ImovelFichaCompletaDTO getDataTransferObject(ImovelFichaCompletaVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

}
