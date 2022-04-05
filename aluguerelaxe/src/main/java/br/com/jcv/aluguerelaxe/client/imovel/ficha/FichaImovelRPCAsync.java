package br.com.jcv.aluguerelaxe.client.imovel.ficha;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.administracao.console.geolocalizacaowizard.GeoLocalizacaoVO;
import br.com.jcv.aluguerelaxe.client.administracao.console.publicidades.VendaPublicidadeVO;
import br.com.jcv.aluguerelaxe.client.charter.CharterVO;
import br.com.jcv.aluguerelaxe.client.imovel.ImovelPlanoFaturaVO;
import br.com.jcv.aluguerelaxe.client.imovel.imagem.ImovelImagemVideoVO;
import br.com.jcv.aluguerelaxe.client.imovel.local.ClassificacaoVO;
import br.com.jcv.aluguerelaxe.client.imovel.local.LocalItemVO;
import br.com.jcv.aluguerelaxe.client.imovel.local.LocalVO;
import br.com.jcv.aluguerelaxe.client.imovel.thread.ContatoAnuncianteThreadVO;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface FichaImovelRPCAsync {

	void pesquisarFichaCompletaImovel(long codigoImovel,AsyncCallback<ImovelFichaCompletaVO> callback);
	@Deprecated
	void apresentarFichaCompletaImovel(long codigoImovel,AsyncCallback<ImovelFichaCompletaVO> callback);
	void apresentarFichaCompletaImovel(long codigoImovel,String origemAcesso,AsyncCallback<ImovelFichaCompletaVO> callback);

	void entrarEmContatoComAnunciante(ContatoClienteVO ccvo,
			AsyncCallback<ContatoClienteVO> callback);

	void adicionarImovelCarteira(ImovelFichaCompletaVO ifcvo, AsyncCallback<ImovelFichaCompletaVO> callback);

	void atualizarImovelCarteira(ImovelFichaCompletaVO ifcvo, AsyncCallback<ImovelFichaCompletaVO> callback);
	void atualizarGeoLocalizacaoImovel(ImovelFichaCompletaVO ifcvo,AsyncCallback<VOPadrao> callback);
	void notificacaoGaleriaFotoVazia(ImovelFichaCompletaVO ifcvo,
			AsyncCallback<VOPadrao> callback);
	void indicarImovelAmigo(ImovelFichaCompletaVO ifcvo, IndicarAmigoVO iavo,
			AsyncCallback<VOPadrao> callback);
	void enviarImagensLixeira(long idImovel, List<ImovelImagemVideoVO> iivvo,
			AsyncCallback<VOPadrao> callback);
	void atualizarTarifaImovel(ImovelFichaCompletaVO ifcvo,
			AsyncCallback<VOPadrao> callback);
	void charterDistribuicaoVisitasImovel(ImovelFichaCompletaVO ifcvo, int ano,
			AsyncCallback<CharterVO> callback);
	void avaliarAnuncio(AvaliacaoAnuncioImovelVO avaliacao,
			AsyncCallback<VOPadrao> callback);
	void pesquisarUltimaFatura(long codigoImovel, String tipo,
			AsyncCallback<ImovelPlanoFaturaVO> callback);
	void listarLocal(AsyncCallback<List<LocalVO>> callback);
	void listarClassificacaoLocal(AsyncCallback<List<ClassificacaoVO>> callback);
	void listarLocalItem(long idUfCidade, 
			List<LocalVO> lstlocal,
			List<ClassificacaoVO> lstclass,
			GeoLocalizacaoVO geolocalizacao,
			AsyncCallback<List<LocalItemVO>> callback);
	void migrarPlanoImovel(long codImovel, long codNovoPlano,
			AsyncCallback<VOPadrao> callback);
	void adicionarVideoImovel(ImovelImagemVideoVO vo,
			AsyncCallback<VOPadrao> callback);
	void renovarPlano(long idImovel, AsyncCallback<ImovelPlanoFaturaVO> callback);
	void criarPublicidade(VendaPublicidadeVO vo,
			AsyncCallback<PublicidadeImovelVO> callback);
	void pesquisarFatura(long idFatura, AsyncCallback<ImovelPlanoFaturaVO> callback);
	void liquidarFatura(long idFatura, String tipo, AsyncCallback<ImovelPlanoFaturaVO> callback);
	void inativarAnuncio(long idImovel, AsyncCallback<VOPadrao> callback);
	void pesquisarContatoAnunciante(String idhash, AsyncCallback<ContatoClienteVO> callback);
	void enviarNovaPergunta(ContatoClienteVO ccvo, ContatoAnuncianteThreadVO vo, AsyncCallback<ContatoAnuncianteThreadVO> callback);
	void enviarResposta(ContatoClienteVO ccvo, ContatoAnuncianteThreadVO vo,
			AsyncCallback<ContatoAnuncianteThreadVO> callback);
	void prepararRealizarPagamento(long idImovel,
			AsyncCallback<PublicidadeImovelVO> callback);
	void listarComentarioImovel(long idImovel,AsyncCallback<List<ContatoClienteVO>> callback); 	

}
