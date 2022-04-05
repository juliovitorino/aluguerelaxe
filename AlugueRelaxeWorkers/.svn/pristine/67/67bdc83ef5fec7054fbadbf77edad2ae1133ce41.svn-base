package br.com.jcv.backend.workers;

import java.util.List;

import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.datemanager.DateManagerBase;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ImovelDTO;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;
import br.com.jcv.backend.imovel.ImovelServiceImpl;
import br.com.jcv.backend.imovel.imagem.ImovelImagemVideoDTO;
import br.com.jcv.backend.interfaces.services.ImovelService;
import br.com.jcv.backend.robot.CicloVidaRobot;
import br.com.jcv.backend.robot.Robot;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

/**
*
* <h2>NegativadorAnuncios</h2>
* <p>Negativador de anuncios de imoveis</p>
* @author julio
*/
public class NegativadorAnuncios extends Robot {

	public static final String ROBOT_NOME = "NEGATIVADOR_ANUNCIOS";
	private static final String STATUS_PENDENTE = "P";
	private static final int NIVEL_MINIMO_NEGATIVACAO = -1;


	public NegativadorAnuncios() {
		super(ROBOT_NOME, "Negativador de Anuncios");
	}
	
	public void executar() {
	
		this.setStatus(CicloVidaRobot.ROBOT_TRABALHANDO,"Iniciando negativador de anuncios");
		try {
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
		
			// Obtem a lista de imoveis em estado de processo de negativação
			List<Long> lstIdImoveis = is.listarImoveisParaNegativacao();
			
			if (lstIdImoveis != null){
				for( Long id : lstIdImoveis){
					ImovelFichaCompletaDTO dto = is.pesquisarFichaCompletaImovel(id);
					
					// Inicializa valor da negativacao
					int nPesoNegativacao = NIVEL_MINIMO_NEGATIVACAO;
					
					// Realiza o processo de negativação
					nPesoNegativacao -= negativarImovelSemFotos(dto);
					nPesoNegativacao -= negativarImovelSemTabelaPreco(dto);
					nPesoNegativacao -= negativarImovelSemAtualizacao3m(dto);
					nPesoNegativacao -= negativarImovelSemAtualizacaoFotos(dto);
					nPesoNegativacao -= negativarImovelComMenos10Fotos(dto);
					nPesoNegativacao -= negativarImovelSemVideo(dto);
					nPesoNegativacao -= negativarImovelComDescricaoBaixa(dto);
					nPesoNegativacao -= negativarImovelSemMapa(dto);
					nPesoNegativacao -= negativarImovelNaoVerificado(dto);
					nPesoNegativacao -= negativarImovelSemCaracteristicas(dto);
					nPesoNegativacao -= negativarImovelSemTelefoneContato(dto);
					nPesoNegativacao -= negativarAnuncianteSemFotoPerfil(dto);
					nPesoNegativacao -= negativarAnuncianteSemDiariaBase(dto);
					nPesoNegativacao -= negativarAnuncianteNaoVerificado(dto);
					nPesoNegativacao -= negativarTaxaResposta(dto);

					
					// Atualiza negativacao
					is.negativarImovel(dto.imovel.id, nPesoNegativacao);

				}
			}
			
			this.setStatus(CicloVidaRobot.ROBOT_TERMINADO,"Negativador de anuncios processado com sucesso.");

		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private int negativarAnuncianteSemDiariaBase(ImovelFichaCompletaDTO ifcdto) throws AlugueRelaxeException{
		int pontosRetirar = 0;
		if (ifcdto.imovel.valorTarifaBasica == 0) {
			pontosRetirar = Integer.valueOf(VariavelCache.getInstance().getValor(VariavelConstantes.PNEG_ANUNCIANTE_SEM_DIARIA_BASE));
		}
		return pontosRetirar;
	}
	
	private int negativarAnuncianteSemFotoPerfil(ImovelFichaCompletaDTO ifcdto) throws AlugueRelaxeException{
		int pontosRetirar = 0;
		if ((ifcdto.cliente != null) && (ifcdto.cliente.fotoPerfil.length() == 0)) {
			pontosRetirar = Integer.valueOf(VariavelCache.getInstance().getValor(VariavelConstantes.PNEG_ANUNCIANTE_SEM_FOTO_PERFIL));
		}
		return pontosRetirar;
	}

	private int negativarAnuncianteNaoVerificado(ImovelFichaCompletaDTO ifcdto) throws AlugueRelaxeException{
		int pontosRetirar = 0;
		if ((ifcdto.cliente != null) && (ifcdto.cliente.indicadorVerificado.equals(Constantes.NAO))) {
			pontosRetirar = Integer.valueOf(VariavelCache.getInstance().getValor(VariavelConstantes.PNEG_ANUNCIANTE_NAO_VERIFICADO));
		}
		return pontosRetirar;
	}

	private int negativarTaxaResposta(ImovelFichaCompletaDTO ifcdto) throws AlugueRelaxeException{
		int pontosRetirar = 0;
		if (ifcdto.cliente != null){
			double taxamin = Double.valueOf(VariavelCache.getInstance().getValor(VariavelConstantes.TAXA_RESPOSTA_MINIMA));
			if ( (ifcdto.cliente.totalResposta / ifcdto.cliente.totalPergunta ) < taxamin ){
				pontosRetirar = Integer.valueOf(VariavelCache.getInstance().getValor(VariavelConstantes.PNEG_TAXA_RESPOSTA_BAIXA));
			}
		}
		return pontosRetirar;
	}
	
	private int negativarImovelSemTelefoneContato(ImovelFichaCompletaDTO ifcdto) throws AlugueRelaxeException{
		int pontosRetirar = 0;
		if (ifcdto.cliente.telefones == null){
			pontosRetirar = Integer.valueOf(VariavelCache.getInstance().getValor(VariavelConstantes.PNEG_IMOVEL_SEM_TELEFONE));
		}
		return pontosRetirar;
	}
	
	private int negativarImovelSemCaracteristicas(ImovelFichaCompletaDTO ifcdto) throws AlugueRelaxeException{
		int pontosRetirar = 0;
		if (ifcdto.caracteristicaImovel == null){
			pontosRetirar = Integer.valueOf(VariavelCache.getInstance().getValor(VariavelConstantes.PNEG_IMOVEL_SEM_CARACTERISTICAS));
		} else if(ifcdto.caracteristicaImovel.size() == 0) {
			pontosRetirar = Integer.valueOf(VariavelCache.getInstance().getValor(VariavelConstantes.PNEG_IMOVEL_SEM_CARACTERISTICAS));
		}
		return pontosRetirar;
	}
	
	private int negativarImovelNaoVerificado(ImovelFichaCompletaDTO ifcdto) throws AlugueRelaxeException{
		int pontosRetirar = 0;
		// fazer ...
		return pontosRetirar;
	}
	
	private int negativarImovelSemMapa(ImovelFichaCompletaDTO ifcdto) throws AlugueRelaxeException{
		int pontosRetirar = 0;
		if (ifcdto.geolocalizacao == null){
			pontosRetirar = Integer.valueOf(VariavelCache.getInstance().getValor(VariavelConstantes.PNEG_IMOVEL_SEM_MAPA));
		} else if((ifcdto.geolocalizacao.latitude == 0) && (ifcdto.geolocalizacao.longitude == 0)) {
			pontosRetirar = Integer.valueOf(VariavelCache.getInstance().getValor(VariavelConstantes.PNEG_IMOVEL_SEM_MAPA));
		}
		return pontosRetirar;
	}
	
	private int negativarImovelSemVideo(ImovelFichaCompletaDTO ifcdto) throws AlugueRelaxeException{
		int pontosRetirar = 0;
		if (ifcdto.videoImovel == null){
			pontosRetirar = Integer.valueOf(VariavelCache.getInstance().getValor(VariavelConstantes.PNEG_IMOVEL_SEM_VIDEO));
		} 
		return pontosRetirar;
	}
	
	private int negativarImovelComDescricaoBaixa(ImovelFichaCompletaDTO ifcdto) throws AlugueRelaxeException{
		int pontosRetirar = 0;
		if (ifcdto.imovel.descricaoGeral.length() < 100){
			pontosRetirar = Integer.valueOf(VariavelCache.getInstance().getValor(VariavelConstantes.PNEG_IMOVEL_BAIXA_DESCRICAO));
		}
		return pontosRetirar;
	}
	
	private int negativarImovelComMenos10Fotos(ImovelFichaCompletaDTO ifcdto) throws AlugueRelaxeException{
		int pontosRetirar = 0;
		if (ifcdto.imagensImovelTB != null){
			if(ifcdto.imagensImovelTB.size() < 10){
				pontosRetirar = Integer.valueOf(VariavelCache.getInstance().getValor(VariavelConstantes.PNEG_IMOVEL_MENOS_10_FOTOS));
			}
		}
		return pontosRetirar;
	}
	
	private int negativarImovelSemAtualizacaoFotos(ImovelFichaCompletaDTO ifcdto) throws AlugueRelaxeException{
		int pontosRetirar = 0;
		DateManagerBase hoje = DateManagerBase.getDateManagerInstance();
		if((ifcdto.imagensImovelTB != null) && (ifcdto.imagensImovelTB.size() > 0)){
			for(ImovelImagemVideoDTO iivdto : ifcdto.imagensImovelTB ){
				DateManagerBase dtAtualizacao = DateManagerBase.getDateManagerInstance(iivdto.dataCadastro.getTime());
				dtAtualizacao.add(90);
				
				if (hoje.getTimeInMillis() > dtAtualizacao.getTimeInMillis()){
					pontosRetirar = Integer.valueOf(VariavelCache.getInstance().getValor(VariavelConstantes.PNEG_IMOVEL_SEM_UPD_FOTOS));
					break;
				}
			}
		}
		return pontosRetirar;
	}
	
	private int negativarImovelSemAtualizacao3m(ImovelFichaCompletaDTO ifcdto) throws AlugueRelaxeException{
		int pontosRetirar = 0;
		DateManagerBase hoje = DateManagerBase.getDateManagerInstance();
		DateManagerBase dtAtualizacao = DateManagerBase.getDateManagerInstance(ifcdto.imovel.dataAtualizacao.getTime());
		dtAtualizacao.add(90);
		
		if (hoje.getTimeInMillis() > dtAtualizacao.getTimeInMillis()){
			pontosRetirar = Integer.valueOf(VariavelCache.getInstance().getValor(VariavelConstantes.PNEG_IMOVEL_SEM_UPD_3_MESES));
		}
		return pontosRetirar;
	}
	
	private int negativarImovelSemTabelaPreco(ImovelFichaCompletaDTO ifcdto) throws AlugueRelaxeException{
		int pontosRetirar = 0;
		if (ifcdto.tabelaPreco == null){
			pontosRetirar = Integer.valueOf(VariavelCache.getInstance().getValor(VariavelConstantes.PNEG_IMOVEL_SEM_TAB_PRECO));
		}
		return pontosRetirar;
	}
	
	private int negativarImovelSemFotos(ImovelFichaCompletaDTO ifcdto) throws AlugueRelaxeException{
		int pontosRetirar = 0;
		if (ifcdto.imagensImovelTB == null){
			pontosRetirar = Integer.valueOf(VariavelCache.getInstance().getValor(VariavelConstantes.PNEG_IMOVEL_SEM_FOTOS));
		}
		return pontosRetirar;
	}
}
