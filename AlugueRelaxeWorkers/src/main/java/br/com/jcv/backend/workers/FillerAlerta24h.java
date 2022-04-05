package br.com.jcv.backend.workers;

import java.util.ArrayList;
import java.util.List;

import br.com.jcv.backend.alerta24h.AlertaDTO;
import br.com.jcv.backend.alerta24h.AlertaServiceImpl;
import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.contatoanunciante.ContatoAnuncianteServiceImpl;
import br.com.jcv.backend.datemanager.DateManagerBase;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ContatoClienteDTO;
import br.com.jcv.backend.imovel.ImovelDTO;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;
import br.com.jcv.backend.imovel.ImovelServiceImpl;
import br.com.jcv.backend.imovel.faturamento.ImovelPlanoFaturaDTO;
import br.com.jcv.backend.imovel.faturamento.ImovelPlanoFaturaServiceImpl;
import br.com.jcv.backend.interfaces.services.AlertaService;
import br.com.jcv.backend.interfaces.services.ContatoAnuncianteService;
import br.com.jcv.backend.interfaces.services.ImovelPlanoFaturaService;
import br.com.jcv.backend.interfaces.services.ImovelService;
import br.com.jcv.backend.interfaces.services.PlanoService;
import br.com.jcv.backend.interfaces.services.TipoAlertaService;
import br.com.jcv.backend.plano.PlanoDTO;
import br.com.jcv.backend.plano.PlanoRecursos;
import br.com.jcv.backend.plano.PlanoServicesImpl;
import br.com.jcv.backend.robot.CicloVidaRobot;
import br.com.jcv.backend.robot.Robot;
import br.com.jcv.backend.tipoalerta.TipoAlertaDTO;
import br.com.jcv.backend.tipoalerta.TipoAlertaServiceImpl;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

/**
*
* <h2>FillerAlerta24h</h2>
* <p>Captador de contatos com anunciantes pendentes para enfileiramento no processo do Alerta 24H</p>
* @author julio
*/
public class FillerAlerta24h extends Robot {

	public static final String ROBOT_NOME = "FILLER_FILA_ALERTA_24H";


	public FillerAlerta24h() {
		super(ROBOT_NOME, "Filler Alerta 24H");
	}
	
	public void executar() {
	
		this.setStatus(CicloVidaRobot.ROBOT_TRABALHANDO,"Iniciando enfileirador de contatos com anunciantes - Alerta24h");
		try {
			//------------------------------------------------------------------------------
			// Otbem todos os tipos de alertas que podem ser enfileirados
			//------------------------------------------------------------------------------
			TipoAlertaService tas = new TipoAlertaServiceImpl();
			List<TipoAlertaDTO> lsttadto = tas.listarTiposAlerta();
			
			//------------------------------------------------------------------------------
			// Obtem os contatos pendentes (IMOVEL_CONTATO_ANUNCIANTE) de serem enfileirados
			// Observacao: se o cliente deste imovel tiver plano gratuito
			// SOMENTE ENFILEIRA PARA ELE SE A VARIAVEL
			// --> LIBERA_ALERTA24H_IMOVEL_GRATUITO = ON
			// --> CENTRAL_RESERVAS_ATIVO = OFF
			//------------------------------------------------------------------------------
			boolean liberado_ig = VariavelCache.getInstance().getValor(VariavelConstantes.LIBERA_ALERTA24H_IMOVEL_GRATUITO).equals("ON");
			boolean liberado_centralReserva = VariavelCache.getInstance().getValor(VariavelConstantes.CENTRAL_RESERVAS_ATIVO).equals("ON");
			ContatoAnuncianteService cas = new ContatoAnuncianteServiceImpl();
			List<ContatoClienteDTO> lstpendentes = cas.listarContatosPendentesAlerta24h();
			
			//------------------------------------------------------------------------------
			// Para cada contato com anunciante pendente, obtem a lista de imoveis
			// pelo menos na mesma regiao (uf x cidade)
			//------------------------------------------------------------------------------
			List<AlertaDTO> lstfila = null;
			if ((lstpendentes != null) && (lstpendentes.size() > 0)) {
			
				ImovelService<ImovelDTO> is = new ImovelServiceImpl();
				lstfila = new ArrayList<AlertaDTO>();
				
				for (ContatoClienteDTO ccdto : lstpendentes) {
				
					// Obtem toda a ficha do imovel visitado
					ImovelFichaCompletaDTO ifcdto = is.pesquisarFichaCompletaImovel(ccdto.idImovel);

					// Busca todos os imoveis da regiao prospectada
					List<Long> lstIdImovel = is.listarIDImoveis(ifcdto.imovel.endereco.uf, ifcdto.imovel.endereco.cidade);
					
					if ((lstIdImovel != null) && (lstIdImovel.size() > 0)) {
						
						for (Long id : lstIdImovel) {
							Boolean incluir = true;
							
							ImovelFichaCompletaDTO ifcdtoAlerta24h = is.pesquisarFichaCompletaImovel(id);
							
							// Verifica se o plano do imovel eh gratuito e 
							// LIBERA_ALERTA24H_IMOVEL_GRATUITO = "ON" e
							// CENTRAL_RESERVAS_ATIVO = OFF
							// insere na fila para alerta24h
							if (ifcdtoAlerta24h.imovelPlano.plano.id == Constantes.CODIGO_ESPECIAL_PLANO_GRATUITO) {
								if (liberado_centralReserva) {
									incluir = false;
								} else {
									if (! liberado_ig) {
										incluir = false;
									}
								}
							} else {
								// o plano eh pago, verifica se a matriz de recurso permite o ALERTA 24H
								PlanoService<PlanoDTO> ps = new PlanoServicesImpl();
								PlanoDTO pdto = ps.pesquisarRegistro(ifcdtoAlerta24h.imovelPlano.plano.id);
								if (
									(pdto == null) || 
										(
											(pdto != null) && 
											(! pdto.recurso[PlanoRecursos.RECURSO_ALERTA_24H].substring(0, 1).equals(Constantes.SIM))
										)
									) {
									incluir = false;
								}
								
								// plano eh pago, somente enfileira o Alerta 24h se ultima fatura estiver paga e nao vencida.
								ImovelPlanoFaturaService<ImovelPlanoFaturaDTO> ipfs = new ImovelPlanoFaturaServiceImpl();
								ImovelPlanoFaturaDTO ipfdto = ipfs.pesquisarUltimaFatura(id, Constantes.TIPO_PLANO_NORMAL);
								
								// Se data pagamento nula --> incluir = false
								if ((ipfdto != null) && (ipfdto.dataPagamento == null)) {
									incluir = false;
								} else {
								// Se data pagamento preenchida e status = "L" e data de hoje > data vencimento anuncio --> incluir =  false
									DateManagerBase hoje = DateManagerBase.getDateManagerInstance();
									DateManagerBase dv = DateManagerBase.getDateManagerInstance(ipfdto.dataVencimento);
									if (hoje.getTimeInMillis() > dv.getTimeInMillis()) {
										incluir = false;
									}
								}
								
							}

							// Cria o alerta para cada tipo e o prepara para insercao na lista
							if (incluir) {
								for (TipoAlertaDTO tadto : lsttadto) {
									AlertaDTO adto = new AlertaDTO();
									adto.tipoAlerta = tadto;
									adto.ifcdto = ifcdtoAlerta24h;
									adto.contato = ccdto;
									lstfila.add(adto);
								}
							}
						}
						
						// Marca "S" no contato com anunciante indicando que foi enfileirado
						cas.atualizarFilaAlerta24h(ccdto.id, Constantes.SIM);
					}
				}
			}
			
			//------------------------------------------------------------------------------
			// 1 - Cria um registro na fila para cada tipo de alerta para cada clien
			// 2 - Enfileira FILA_SMS um SMS avisando da pro-atividade do Aluguerelaxe
			//------------------------------------------------------------------------------
			if ((lstfila != null) && (lstfila.size() > 0)) {
				AlertaService as = new AlertaServiceImpl();
				for (AlertaDTO dto: lstfila) {
					as.enfileirarAlerta(dto);
					//cas.atualizarFilaAlerta24h(dto.contato.id, Constantes.SIM);
					this.addContador();
					
					//??? Enfileira um SMS para o visitante avisando a pro-atividade do aluguerelaxe ???
				}
			}
			
			this.setStatus(CicloVidaRobot.ROBOT_TERMINADO,"Enfileirador de contatos com anunciantes - Alerta24h processado com sucesso. Total de " + this.getContador() + " alertas enfileirados.");

		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
