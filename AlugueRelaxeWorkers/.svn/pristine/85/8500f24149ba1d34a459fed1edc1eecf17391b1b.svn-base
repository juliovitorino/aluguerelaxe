package br.com.jcv.backend.aprovacoespendentes;

import br.com.jcv.backend.chain.AbstractChain;
import br.com.jcv.backend.chain.Chain;
import br.com.jcv.backend.cliente.ClienteDTO;
import br.com.jcv.backend.robot.Robot;
import br.com.jcv.backend.workers.ContatoAnunciantePendenteTotal;


public class ContatoAnuncianteTotalPendenteSMSChain extends AbstractChain 
	implements Chain<ClienteDTO> {

	public void execute(ClienteDTO context) {
		// Obtem Robot que implementa a interface 
		Robot isp = new ContatoAnunciantePendenteTotal();
		isp.executar();
	}

}
