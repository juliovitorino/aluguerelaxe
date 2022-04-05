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
* <h2>AtualizadorSaldoFuraFila</h2>
* <p>Verificador de imoveis com fura-fila para realizar debito diario da cota</p>
* <p>Como funciona:</p>
* <p>Será obtido uma lista de imovel com campo IMOV_IN_TIPO_COLABORACAO = 'C' e IMOV_VL_TIPO_COLABORACAO > -1.
* O valor em IMOV_VL_TIPO_COLABORACAO será debitado do valor armazenado na VariavelConstantes.VALOR_ABATIMENTO_FURA_FILA
* </p>
* @author julio
*/
public class AtualizadorSaldoFuraFila extends Robot {

	public static final String ROBOT_NOME = "ATUALIZADOR_SALDO_FURA_FILA";

	public AtualizadorSaldoFuraFila() {
		super(ROBOT_NOME, "Atualizador de saldo de fura-fila");
	}
	
	public void executar() {
	
		this.setStatus(CicloVidaRobot.ROBOT_TRABALHANDO,"Iniciando atualizador de saldo de fura-fila");
		try {
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
		
			// Obtem a lista de imoveis em fura-fila
			List<Long> lst = is.listarImoveisFuraFila();
			
			if (lst != null){
				for( Long id : lst){
				
					// Obtem valor de abatimento diario
					double vlr = Double.valueOf(VariavelCache.getInstance().getValor(VariavelConstantes.VALOR_ABATIMENTO_FURA_FILA));

					// realiza atualizacao do saldo do imovel em fura-fila
					is.atualizaSaldoFuraFila(id, vlr);
				}
			}
			this.setStatus(CicloVidaRobot.ROBOT_TERMINADO,"atualizador de saldo de fura-fila processada com sucesso.");

		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
