
package br.com.jcv.backend.robot.client;

import br.com.jcv.backend.cliente.ClienteDTO;
import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.utility.ValidadorAnotacaoObrigatorio;

public class ClienteDTOValidatorClient {

	public static void main(String[] args) {
		
		ClienteDTO dto = new ClienteDTO();
		//dto.nome = "Joaquim da Silva Xavier da Costa e Silva Santos da Cunha Bastos Cordeiro";
		dto.nome = "Joaquim";
		dto.email = "joaquim@puc";
		dto.indicadorAutorizaNotificacao = Constantes.SIM;
		dto.indicadorTipoAnunciante = "X";
		try {
			ValidadorAnotacaoObrigatorio.validar(dto);
		} catch (AlugueRelaxeException e) {
			System.out.println(e.getMensagem());
			e.printStackTrace();
		}

	}

}

