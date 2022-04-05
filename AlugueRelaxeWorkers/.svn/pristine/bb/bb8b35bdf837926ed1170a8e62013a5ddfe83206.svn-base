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
* <h2>BloqueadorAnunciosPagosVencidos</h2>
* <p>Bloqueador de anuncios pagos vencidos </p>
* @author julio
*/
public class BloqueadorAnunciosPagosVencidos extends Robot {

	public static final String ROBOT_NOME = "FATURAS_PLANOS_PAGOS_A_VENCER";

	public BloqueadorAnunciosPagosVencidos() {
		super(ROBOT_NOME, "Bloqueador de anuncios pagos vencidos");
	}
	
	public void executar() {
	
		this.setStatus(CicloVidaRobot.ROBOT_TRABALHANDO,"Iniciando bloqueador de anuncios pagos vencidos");
		try {
			ImovelPlanoFaturaService<ImovelPlanoFaturaDTO> ipfs = new ImovelPlanoFaturaServiceImpl();
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
		
			// Obtem a lista imoveis com anuncios a vencer
			List<Long> lstPlanosAVencer = ipfs.listarPlanosPagosVencidos();
			if (lstPlanosAVencer != null){
				for( Long id : lstPlanosAVencer){
					// Obtem a ficha completa do imovel
					ImovelFichaCompletaDTO ifcdto = is.pesquisarFichaCompletaImovel(id);
					
					// Obtem os detalhes da ultima fatura do anuncio
					ImovelPlanoFaturaDTO ipfdto = ipfs.pesquisarUltimaFatura(id, Constantes.TIPO_PLANO_NORMAL);
					
					// Envia o email para cliente
					ipfs.notificaPlanoAnuncioBloqueado(ifcdto, ipfdto);
					
					// Inativa o anuncio do imovel
					is.atualizarStatusImovel(ifcdto.imovel.id, Constantes.IMOVEL_STATUS_INATIVO);
					ipfs.atualizarStatusFatura(ipfdto.id, Constantes.IMPF_STATUS_VENCIDO);

				}
			}
			this.setStatus(CicloVidaRobot.ROBOT_TERMINADO,"Lista de clientes status pendente processada com sucesso.");

		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
