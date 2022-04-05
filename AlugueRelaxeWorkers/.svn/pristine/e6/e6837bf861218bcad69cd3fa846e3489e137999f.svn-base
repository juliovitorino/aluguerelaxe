package br.com.jcv.backend.workers;

import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ImovelDTO;
import br.com.jcv.backend.imovel.ImovelServiceImpl;
import br.com.jcv.backend.interfaces.services.ImovelService;
import br.com.jcv.backend.robot.CicloVidaRobot;
import br.com.jcv.backend.robot.Robot;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

/**
*
* <h2>AtualizadorSaldoPatrocinador</h2>
* <p>Verificador de imovel  patrocinador para realizar debito diario da cota</p>
* <p>Como funciona:</p>
* <p>Será obtido uma lista de imovel com campo IMOV_IN_TIPO_COLABORACAO = 'P' e IMOV_VL_TIPO_COLABORACAO > -1.
* O valor em IMOV_VL_TIPO_COLABORACAO será debitado do valor armazenado na VariavelConstantes.VALOR_ABATIMENTO_PATROCINADOR
* </p>
* @author julio
*/
public class AtualizadorSaldoPatrocinador extends Robot {

	public static final String ROBOT_NOME = "AUALIZADOR_SALDO_PATROCINADOR";

	public AtualizadorSaldoPatrocinador() {
		super(ROBOT_NOME, "Atualizador de saldo patrocinador");
	}
	
	public void executar() {
	
		this.setStatus(CicloVidaRobot.ROBOT_TRABALHANDO,"Iniciando atualizador de saldo patrocinador");
		try {
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
		
			// Obtem a lista de imovel patrocinador
			List<Long> lst = is.listarImoveisPatrocinadores();
			
			if (lst != null){
				for( Long id : lst){
				
					// Obtem valor de abatimento diario
					double vlr = Double.valueOf(VariavelCache.getInstance().getValor(VariavelConstantes.VALOR_ABATIMENTO_PATROCINADOR));

					// realiza atualizacao do saldo do imovel patrocinador
					is.atualizaSaldoFuraFila(id, vlr);
				}
			}
			this.setStatus(CicloVidaRobot.ROBOT_TERMINADO,"atualizador de saldo patrocinador processada com sucesso.");

		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
