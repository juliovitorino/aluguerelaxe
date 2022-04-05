package br.com.jcv.backend.workers;

import java.util.List;

import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ImovelDTO;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;
import br.com.jcv.backend.imovel.ImovelServiceImpl;
import br.com.jcv.backend.imovel.faturamento.ImovelPlanoFaturaDTO;
import br.com.jcv.backend.imovel.faturamento.ImovelPlanoFaturaServiceImpl;
import br.com.jcv.backend.interfaces.services.ImovelPlanoFaturaService;
import br.com.jcv.backend.interfaces.services.ImovelService;
import br.com.jcv.backend.robot.CicloVidaRobot;
import br.com.jcv.backend.robot.Robot;

/**
*
* <h2>VerificadorAnunciosPlanosGratuitosAVencer</h2>
* <p>faturas de planos gratuitos de anunciantes a vencer anuncio dentro do 7 dias </p>
* @author julio
*/
public class VerificadorAnunciosPlanosGratuitosAVencer extends Robot {

	public static final String ROBOT_NOME = "FATURAS_PLANOS_PAGOS_A_VENCER";

	public VerificadorAnunciosPlanosGratuitosAVencer() {
		super(ROBOT_NOME, "Verificador de anuncios de planos gratuitos a vencer");
	}
	
	public void executar() {
	
		this.setStatus(CicloVidaRobot.ROBOT_TRABALHANDO,"Iniciando verifica��o de planos gratuitos a vencer");
		try {
			ImovelPlanoFaturaService<ImovelPlanoFaturaDTO> ipfs = new ImovelPlanoFaturaServiceImpl();
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
		
			// Obtem a lista imoveis com anuncios a vencer
			List<Long> lstPlanosAVencer = ipfs.listarPlanosGratuitosAVencer();
			if (lstPlanosAVencer != null){
				for( Long id : lstPlanosAVencer){
					// Obtem a ficha completa do imovel
					ImovelFichaCompletaDTO ifcdto = is.pesquisarFichaCompletaImovel(id);
					
					// Obtem os detalhes da ultima fatura do anuncio
					ImovelPlanoFaturaDTO ipfdto = ipfs.pesquisarUltimaFatura(id, Constantes.TIPO_PLANO_NORMAL);
					
					// Envia o email para cliente
					ipfs.notificaVencimentoAnuncio(ifcdto, ipfdto);
				}
			}
			this.setStatus(CicloVidaRobot.ROBOT_TERMINADO,"Lista de clientes status pendente processada com sucesso.");

		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}