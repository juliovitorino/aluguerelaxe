package br.com.jcv.backend.interfacesdao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import br.com.jcv.backend.charter.CharterSequence;
import br.com.jcv.backend.cliente.ClienteDTO;
import br.com.jcv.backend.dto.CidadeUFDTO;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ContatoClienteDTO;
import br.com.jcv.backend.imovel.GeoLocalizacaoDTO;
import br.com.jcv.backend.imovel.ImovelDTO;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;
import br.com.jcv.backend.imovel.ImovelPlanoDTO;
import br.com.jcv.backend.imovel.TipoColaboracaoDTO;
import br.com.jcv.backend.imovel.faturamento.ImovelPlanoFaturaDTO;
import br.com.jcv.backend.imovel.publicidade.PublicidadeImovelDTO;
import br.com.jcv.backend.imovel.tabelapreco.TabelaPrecoDTO;

/**
 * @author Julio
 * @version 1.0
 * @created 10-nov-2009 00:15:22
 */
public interface ImovelDAO<DTO> extends DAO<DTO> {
	
	long count(int cidade) throws AlugueRelaxeException;
	int count(long idCliente) throws AlugueRelaxeException;
	List<ImovelFichaCompletaDTO> list(int cidade, int pagina, int regPagina) throws AlugueRelaxeException;
	long count(Map<String,String> param) throws AlugueRelaxeException;
	List<ImovelFichaCompletaDTO> list(Map<String,String> param, int pagina, int regPagina) throws AlugueRelaxeException;
	long count(String uf) throws AlugueRelaxeException;
	List<ImovelFichaCompletaDTO> list(String uf, int pagina, int regPagina) throws AlugueRelaxeException;
	List<ImovelDTO> list(ClienteDTO clientedto) throws AlugueRelaxeException;
	List<ImovelDTO> list(String indPatrocinadorColaborador) throws AlugueRelaxeException;
	void atualizaVisitas(long codigoImovel) throws AlugueRelaxeException;
	List<CidadeUFDTO> listarQtdeImoveisCidadeUF() throws AlugueRelaxeException;
	ContatoClienteDTO gravarContatoComAnunciante(ContatoClienteDTO ccdto) throws AlugueRelaxeException;
	List<ContatoClienteDTO> listarContatosAnunciantesPorStatus(String status) throws AlugueRelaxeException;
	ContatoClienteDTO procurarContatoAnunciante(String id) throws AlugueRelaxeException;
	void mudaStatusContatoAnunciante(String id, String acao) throws AlugueRelaxeException;
	void mudaStatusThread(String id, String status) throws AlugueRelaxeException;
	ImovelFichaCompletaDTO update(ImovelFichaCompletaDTO dto) throws AlugueRelaxeException;
	ImovelPlanoDTO insert(long codigoImovel, ImovelPlanoDTO dto) throws AlugueRelaxeException;
	ImovelPlanoDTO procurarPlanoFinanceiro(long codigoImovel) throws AlugueRelaxeException;
	List<ImovelDTO> listarUltimosImoveisCadastrados(int qtdeAnuncios) throws AlugueRelaxeException;
	void update(long codigoImovel, GeoLocalizacaoDTO dto) throws AlugueRelaxeException;
	GeoLocalizacaoDTO procurarGeoLocalizacao(long codigoImovel) throws AlugueRelaxeException;
	ImovelPlanoFaturaDTO insert(long idImovelPlano, ImovelPlanoFaturaDTO dto) throws AlugueRelaxeException;
	void updateDataPgtoFatura(long idFatura) throws AlugueRelaxeException;
	void update(long idImovel, List<TabelaPrecoDTO> lst) throws AlugueRelaxeException;
	List<CharterSequence> listarSumarizadoImoveis(ImovelFichaCompletaDTO ifcdto, int ano) throws AlugueRelaxeException;
	List<Long> listarImoveisParaSorteio() throws AlugueRelaxeException;
	List<Long> listarImoveisParaSorteioPD() throws AlugueRelaxeException;
	Date obterMaiorDataPublicidade(String tipo) throws AlugueRelaxeException;
	void insert(PublicidadeImovelDTO pub) throws AlugueRelaxeException;
	void update(PublicidadeImovelDTO pub) throws AlugueRelaxeException;
	void update(long idFatura) throws AlugueRelaxeException;
	void update(long idImovel, String novoStatus) throws AlugueRelaxeException;
	void updatePD(long idImovel, String novoStatus) throws AlugueRelaxeException;
	TipoColaboracaoDTO loadIndicador(long idImovel) throws AlugueRelaxeException;
	void updateStatus(long codigoImovel, String status) throws AlugueRelaxeException;
	List<Long> listImoveisParaNegativacao() throws AlugueRelaxeException;
	void updateNotaAnuncioImovel(long idImovel, double pesoNegativacao) throws AlugueRelaxeException; 
	List<Long> listImoveisColaborador(String tipo) throws AlugueRelaxeException;
	List<Long> listImoveisTipoColaboracao(String tipo) throws AlugueRelaxeException;
	void updateSaldoFuraFila(long idImovel, double vlr) throws AlugueRelaxeException;
	long countImoveisSorteioSB(String statusSorteio) throws AlugueRelaxeException;
	long countImoveisSorteioPP(String statusSorteio) throws AlugueRelaxeException;
	long countImoveisSorteioPD(String statusSorteio) throws AlugueRelaxeException;
	List<Long> listImoveis(String status) throws AlugueRelaxeException;
	void updateStatusColaborador(long codigoImovel, String status, double valor) throws AlugueRelaxeException;
	List<Long> listColaboradorInconsistente() throws AlugueRelaxeException;
	List<Long> listIDImoveis(String uf, String cidade) throws AlugueRelaxeException;
	long loadIDProximoOferecimento(String uf, String cidade) throws AlugueRelaxeException;
	void updateOferecimento(long id, long seq) throws AlugueRelaxeException;
	List<Long> listImoveisPorCidadeUf(long idCidadeUf, int pagina, int regPagina) throws AlugueRelaxeException;
	long countImoveisPorCidadeUf(long idCidadeUf) throws AlugueRelaxeException;

}