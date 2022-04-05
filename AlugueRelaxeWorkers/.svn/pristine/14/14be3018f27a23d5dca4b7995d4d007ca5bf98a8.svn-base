package br.com.jcv.backend.workers;

import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ImovelDTO;
import br.com.jcv.backend.imovel.ImovelServiceImpl;
import br.com.jcv.backend.imovel.publicidade.PublicidadeImovelDTO;
import br.com.jcv.backend.interfaces.services.ImovelService;
import br.com.jcv.backend.robot.CicloVidaRobot;
import br.com.jcv.backend.robot.Robot;

/**
*
* <h2>VerificadorPublicidadesDentroPrazoFF</h2>
* <p>Verifica de publicidades do tipo FF se estao dentro do prazo e pagas</p>
* @author julio
*/
public class VerificadorPublicidadesDentroPrazoFF extends Robot {

	public static final String ROBOT_NOME = "VERIFICADOR_PUBLICIDADES_FF";

	public VerificadorPublicidadesDentroPrazoFF() {
		super(ROBOT_NOME, "Verificador de Publicidades Dentro do Prazo tipo FF");
	}
	
	public void executar() {
	
		this.setStatus(CicloVidaRobot.ROBOT_TRABALHANDO,"Iniciando verificação de publicidades pagas");
		try {
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
		
			// Obtem a lista de publicidades do tipo a vencer
			List<PublicidadeImovelDTO> lst = is.listPublicidadeDentroPrazo("FF");
			if (lst != null){
				for( PublicidadeImovelDTO dto : lst){
					// Obtem a ficha completa do imovel
					is.atualizarStatusImovelColaborador(dto.fichaImovel.imovel.id, "C", dto.fatura.valorFatura);
				}
			}
			this.setStatus(CicloVidaRobot.ROBOT_TERMINADO,"Verificação de publicidades pagas processada com sucesso.");

		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
