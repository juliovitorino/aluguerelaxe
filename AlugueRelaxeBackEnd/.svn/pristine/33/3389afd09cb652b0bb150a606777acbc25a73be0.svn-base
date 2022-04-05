package br.com.jcv.backend.imovel;

import java.io.Serializable;
import java.util.List;

import br.com.jcv.backend.cliente.ClienteDTO;
import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.imovel.caracteristica.ImovelCaracteristicaDTO;
import br.com.jcv.backend.imovel.imagem.ImovelImagemVideoDTO;
import br.com.jcv.backend.imovel.tabelapreco.TabelaPrecoDTO;

/**
 * <h1>ImovelFichaCompletaDTO</h1>
 * <p>Classe de transferência para os dados da ficha
 * completa do imóvel.</p>
 * @author julio
 *
 */
public class ImovelFichaCompletaDTO extends DTOPadrao implements Serializable {

	private static final long serialVersionUID = 5848367774270864651L;

	/**
	 * <h2>imovel</h2>
	 * <p>Informações referentes ao imóvel</p>
	 */
	public ImovelDTO imovel;
	
	/**
	 * <h2>cliente</h2>
	 * <p>Informações referentes ao proprietário ou custodiante do imóvel</p>
	 */
	public ClienteDTO cliente;
	
	/**
	 * <h2>imagensImovelTB</h2>
	 * <p>Lista das imagens do imóvel do tipo TB</p>
	 */
	public List<ImovelImagemVideoDTO> imagensImovelTB;
	
	/**
	 * <h2>imagensImovelMI</h2>
	 * <p>Lista das imagens do imóvel do tipo MI</p>
	 */
	public List<ImovelImagemVideoDTO> imagensImovelMI;
	
	/**
	 * <h2>imagensImovelXG</h2>
	 * <p>Lista das imagens do imóvel do tipo XG</p>
	 */
	public List<ImovelImagemVideoDTO> imagensImovelXG;

	/**
	 * <h2>tabelaPreco</h2>
	 * <p>Tabela de Preço do imóvel praticada</p>
	 */
	public List<TabelaPrecoDTO> tabelaPreco;
	
	/**
	 * <h2>caracteristicaImovel</h2>
	 * <p>Características do imóvel</p>
	 */
	public List<ImovelCaracteristicaDTO> caracteristicaImovel;
	/**
	 * <h2>caracteristicaCondominio</h2>
	 * <p>Características do condomínio onde encontra-se o imóvel</p>
	 */
	public List<ImovelCaracteristicaDTO> caracteristicaCondominio;

	/**
	 * <h2>imovelPlano</h2>
	 * <p>Plano financeiro do imovel</p>
	 */
	public ImovelPlanoDTO imovelPlano;
	
	public GeoLocalizacaoDTO geolocalizacao;
	
	public TipoColaboracaoDTO tipoColaboracao;
	
	/**
	 * <h2>videoImovel</h2>
	 * <p>Video do Imovel - Origem Youtube</p>
	 */
	public ImovelImagemVideoDTO videoImovel;
	/**
	 * <h2>indCentralReserva</h2>
	 * <p>Indicador de ativacao da Central de Reservas</p>
	 */
	public boolean indCentralReserva;

	/*
	public String urlHomeMobile;
	public String urlHomeWWW;
	public String urlFichaImovelMobile;
	public String urlFichaImovelWWW;
	*/
	
	public String urlFichaImovelEstatica;

}
