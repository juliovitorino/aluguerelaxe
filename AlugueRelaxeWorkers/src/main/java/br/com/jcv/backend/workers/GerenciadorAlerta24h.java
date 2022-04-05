package br.com.jcv.backend.workers;

import java.util.List;

import br.com.jcv.backend.alerta24h.AlertaDTO;
import br.com.jcv.backend.alerta24h.AlertaServiceImpl;
import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.contatoanunciante.ContatoAnuncianteServiceImpl;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ImovelDTO;
import br.com.jcv.backend.imovel.ImovelServiceImpl;
import br.com.jcv.backend.interfaces.services.AlertaService;
import br.com.jcv.backend.interfaces.services.ContatoAnuncianteService;
import br.com.jcv.backend.interfaces.services.ImovelService;
import br.com.jcv.backend.interfaces.services.TipoAlertaService;
import br.com.jcv.backend.robot.CicloVidaRobot;
import br.com.jcv.backend.robot.Robot;
import br.com.jcv.backend.tipoalerta.TipoAlertaServiceImpl;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;
import br.com.jcv.backend.workers.interfaces.IAlerta24H;

/**
*
* <h2>GerenciadorAlerta24h</h2>
* <p>Enviador de alertas para usuarios Alerta 24H</p>
* @author julio
*/
public class GerenciadorAlerta24h extends Robot {

	public static final String ROBOT_NOME = "GERENCIADOR_ALERTA_24H";


	public GerenciadorAlerta24h() {
		super(ROBOT_NOME, "GERENCIADOR ALTERA 24H");
	}
	
	public void executar() {
	
		this.setStatus(CicloVidaRobot.ROBOT_TRABALHANDO,"Iniciando gerenciador de alerta 24H");
		
		boolean chaveOn = false;
		try {
			chaveOn = VariavelCache.getInstance().getValor(VariavelConstantes.CHAVE_GERAL_GERENCIADOR_ALERTA_24H).equals(Constantes.ON);
		} catch (AlugueRelaxeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (chaveOn) {
		
			try {
				//------------------------------------------------------------------------------
				// Obtem os todos alertas pendentes na fila e emite um a um
				// de acordo com seu tipo
				//------------------------------------------------------------------------------
				AlertaService as = new AlertaServiceImpl();
				List<AlertaDTO> lst = as.listarAlertasPendentes();
				
				//------------------------------------------------------------------------------
				// Para cada tipo de alerta existe um worker para executar o trabalho
				//------------------------------------------------------------------------------
				if ((lst != null) && (lst.size() > 0)) {
				
					// Recruta servicos
					TipoAlertaService tas = new TipoAlertaServiceImpl();
					ImovelService<ImovelDTO> is = new ImovelServiceImpl();
					ContatoAnuncianteService ccs = new ContatoAnuncianteServiceImpl();
					
					for (AlertaDTO dto : lst) {
						// Popula informacoes no DTO
						dto.ifcdto = is.pesquisarFichaCompletaImovel(dto.ifcdto.imovel.id);
						dto.tipoAlerta = tas.pesquisarRegistro(dto.tipoAlerta);
						dto.contato = ccs.pesquisarRegistro(dto.contato);
						
						IAlerta24H worker = null;
						try {
							worker = (IAlerta24H) Class.forName(dto.tipoAlerta.worker).newInstance();
							worker.executar(dto);
							as.atualizarStatusEmitido(dto, Constantes.SIM);
							this.addContador();
						} catch (InstantiationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
				this.setStatus(CicloVidaRobot.ROBOT_TERMINADO,"Gerenciador de Alerta 24H processado com sucesso. Total de " + this.getContador() + " processados.");

			} catch (AlugueRelaxeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			this.setStatus(CicloVidaRobot.ROBOT_TERMINADO,"Nada foi processado. Variavel CHAVE_GERAL_GERENCIADOR_ALERTA_24H = OFF");
		}
		
		
	}
}
