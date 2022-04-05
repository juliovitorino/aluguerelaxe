package br.com.jcv.backend.workers;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.contatoanunciante.ContatoAnuncianteServiceImpl;
import br.com.jcv.backend.datemanager.DateManagerBase;
import br.com.jcv.backend.email.Email;
import br.com.jcv.backend.email.EmailFactory;
import br.com.jcv.backend.email.EmailRecord;
import br.com.jcv.backend.email.ServidorSMTP;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ContatoClienteDTO;
import br.com.jcv.backend.imovel.ImovelDTO;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;
import br.com.jcv.backend.imovel.ImovelServiceImpl;
import br.com.jcv.backend.imovel.faturamento.ImovelPlanoFaturaDTO;
import br.com.jcv.backend.imovel.faturamento.ImovelPlanoFaturaServiceImpl;
import br.com.jcv.backend.interfaces.services.ContatoAnuncianteService;
import br.com.jcv.backend.interfaces.services.ImovelPlanoFaturaService;
import br.com.jcv.backend.interfaces.services.ImovelService;
import br.com.jcv.backend.robot.CicloVidaRobot;
import br.com.jcv.backend.robot.Robot;
import br.com.jcv.backend.template.TemplateConstantes;
import br.com.jcv.backend.utility.StringUtil;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

/**
*
* <h2>GerenciadorOferecimento</h2>
* <p>Oferece imoveis aos visitantes registrados em IMOVEL_CONTATO_ANUNCIANTE</p>
* @author julio
*/
public class GerenciadorOferecimento extends Robot {

	public static final String ROBOT_NOME = "GERENCIADOR_OFERECIMENTO";
	public static final String MSG_PADRAO_SUCESSO = "Gerenciador de oferecimentos processado com sucesso. ";


	public GerenciadorOferecimento() {
		super(ROBOT_NOME, "Gerenciador Oferecimento");
	}
	
	public void executar() {
	
		this.setStatus(CicloVidaRobot.ROBOT_TRABALHANDO,"Iniciando gerenciador de oferecimentos");
		String MsgCustomizada = "";
		try {
		
			//---------------------------------------------------------------------------------
			// Obtem o menor registro de anuncio em IMOVEL_CONTATO_ANUNCIANTE com base no campo
			// IMCA_SQ_OFERECIMENTO
			//---------------------------------------------------------------------------------
			ContatoAnuncianteService cas = new ContatoAnuncianteServiceImpl();
			ContatoClienteDTO cadto = cas.pesquisarProximoOferecimento();

			//---------------------------------------------------------------------------------
			// Verificar o plano do imovel e aplicar as seguintes regras:
			// Se plano do imovel GRATUITO, verificar a variavel 
			// OFERECIMENTO_PLANOS_GRATUITOS = ON
			//---------------------------------------------------------------------------------
			boolean liberado_ig = VariavelCache.getInstance().getValor(VariavelConstantes.OFERECIMENTO_PLANOS_GRATUITOS).equals("ON");

			// Assume sempre envio OK.
			boolean lEnviar = true;
			
			//---------------------------------------------------------------------------------
			// Obtem o menor valor com base no campo IMOV_SQ_OFERECIMENTO 
			// em IMOVEL e retorna o idImovel  obedecendo as
			// seguintes regras: ( Nota: As restricoes poderao vir pela query)
			// 1) STATUS DO IMOVEL = A 
			// 2) STATUS DO CLIENTE = A
			// 3) IMOVEL DEVE PERTENCER A MESMA REGIAO (CIDADE X UF) do IMOVEL_CONTATO_ANUNCIANTE
			//---------------------------------------------------------------------------------
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();

			//---------------------------------------------------------------------------------
			// Obtem informacoes do imovel ja visitado anteriormente pra definir a regiao
			// a pesquisar imovel para oferecimento
			//---------------------------------------------------------------------------------
			ImovelFichaCompletaDTO ifcdtovisitado = is.pesquisarFichaCompletaImovel(cadto.idImovel);
			ImovelFichaCompletaDTO ifcdto = null;
			long id = -1;
			if (liberado_ig) {
				
				if (ifcdtovisitado != null){
					// proximo imovel ativo plano gratuito ou pago
					id = is.ImovelIDProximoOferecimento(ifcdtovisitado.imovel.endereco.uf,ifcdtovisitado.imovel.endereco.cidade);
					if (id > 0){
						ifcdto = is.pesquisarFichaCompletaImovel(id);
					}
				}
			} else {
				// proximo imove plano somente pago
				//ifcdto = is.pesquisarProximoOferecimentoPlano(cidade, uf);
			}
			
			if(ifcdto != null) {
				//---------------------------------------------------------------------------------
				// Atualiza sequencial da fila de oferecimento em imovel
				//---------------------------------------------------------------------------------
				is.atualizarOferecimento(ifcdto.imovel.id);

				//---------------------------------------------------------------------------------
				// Atualiza sequencial da fila de oferecimento
				//---------------------------------------------------------------------------------
				cas.atualizarOferecimento(cadto.id);
				
				if (ifcdto.imovelPlano.plano.id == Constantes.CODIGO_ESPECIAL_PLANO_GRATUITO) {
					if (! liberado_ig) {
						lEnviar = false;
						MsgCustomizada = "Envio Negado: Motivo - Plano do Imovel #" + ifcdto.imovel.id + "Gratuito. OFERECIMENTO_PLANOS_GRATUITOS = OFF";
					}
				} else {
													
					//---------------------------------------------------------------------------------
					// plano eh pago, somente enfileira o Alerta 24h 
					// se ultima fatura estiver paga e nao vencida.
					//---------------------------------------------------------------------------------
					ImovelPlanoFaturaService<ImovelPlanoFaturaDTO> ipfs = new ImovelPlanoFaturaServiceImpl();
					ImovelPlanoFaturaDTO ipfdto = ipfs.pesquisarUltimaFatura(ifcdto.imovel.id, Constantes.TIPO_PLANO_NORMAL);
					
					// Se data pagamento nula --> incluir = false
					if ((ipfdto != null) && (ipfdto.dataPagamento == null)) {
						lEnviar = false;
						MsgCustomizada = "Envio Negado: Motivo - Plano do Imovel #" + ifcdto.imovel.id + " em debito.";
					} else {
					//---------------------------------------------------------------------------------
					// Se data pagamento preenchida e status = "L" 
					// e data de hoje > data vencimento anuncio --> incluir =  false
					//---------------------------------------------------------------------------------
						DateManagerBase hoje = DateManagerBase.getDateManagerInstance();
						DateManagerBase dv = DateManagerBase.getDateManagerInstance(ipfdto.dataVencimento);
						if (hoje.getTimeInMillis() > dv.getTimeInMillis()) {
							lEnviar = false;
							try {
								MsgCustomizada = "Envio Negado: Motivo - Plano do Imovel #" + ifcdto.imovel.id + " vencido em " + dv.getDateCustom("DD/MM/YYYY")+ ".";
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			} else {
				lEnviar = false;
				MsgCustomizada = "Envio Negado: Motivo - Imovel #" + ifcdto.imovel.id + " invalido.";
			}
			
			// Envia as notificacoes
			if (lEnviar){
			
				//---------------------------------------------------------------------------------
				// Somente envia email se imovel tiver imagem, caso contrario nao marca
				// o registro do visitante com sequenciador de oferecimento
				//---------------------------------------------------------------------------------
				if ((ifcdto.imagensImovelTB != null) && (ifcdto.imagensImovelTB.size() > 0)) {
					// Envia EMAIL para o visitante
					DateManagerBase dVisita =  DateManagerBase.getDateManagerInstance(cadto.dataCadastro);
					String urlImagemImovel = VariavelCache.getInstance().getValor(VariavelConstantes.URL_VERIFICAR_IMAGEM_UPLOAD);
					urlImagemImovel = StringUtil.replaceStringNew(urlImagemImovel, "${clienteid}", String.valueOf(ifcdto.cliente.id));
					urlImagemImovel = StringUtil.replaceStringNew(urlImagemImovel, "${imovelid}", String.valueOf(ifcdto.imovel.id));
					urlImagemImovel = StringUtil.replaceStringNew(urlImagemImovel, "${imagemupload}", String.valueOf(ifcdto.imagensImovelTB.get(0).nome));
					
					String linkImovel = VariavelCache.getInstance().getValor(VariavelConstantes.LINK_OFERECIMENTO);
					linkImovel = StringUtil.replaceStringNew(linkImovel, "${id}", String.valueOf(ifcdto.imovel.id));
					
					Map<String, String> conteudo = new HashMap<String,String>();
					conteudo.put(TemplateConstantes.TAGC_ID_IMOVEL, String.valueOf(ifcdto.imovel.id));
					conteudo.put(TemplateConstantes.TAGC_TITULO_ANUNCIO, ifcdto.imovel.descricaoTituloAnuncio);
					conteudo.put(TemplateConstantes.TAGC_DESCRICAO_GERAL_IMOVEL, ifcdto.imovel.descricaoGeral);
					conteudo.put(TemplateConstantes.TAGC_IMAGEM_IMOVEL, urlImagemImovel);
					conteudo.put(TemplateConstantes.TAGC_LINK_IMOVEL, linkImovel);
					conteudo.put(TemplateConstantes.TAGC_EMAIL_VISITANTE, cadto.email);
					conteudo.put(TemplateConstantes.TAGC_TELEFONE_VISITANTE, cadto.telefone);
					conteudo.put(TemplateConstantes.TAGC_NOME_VISITANTE, cadto.proposto.toUpperCase());
					conteudo.put(TemplateConstantes.TAGC_UF, ifcdto.imovel.endereco.uf);
					conteudo.put(TemplateConstantes.TAGC_CIDADE, ifcdto.imovel.endereco.cidade);
					try {
						conteudo.put(TemplateConstantes.TAGC_DATA_VISITA, dVisita.getDateTimeFull());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					conteudo.put(TemplateConstantes.TAGC_NOME_ANUNCIANTE, cadto.proposto.toUpperCase());
					
					// Prepara a lista de destinatarios
					List<EmailRecord> lstDestinatarios = new ArrayList<EmailRecord>();
					lstDestinatarios.add(new EmailRecord(cadto.email, cadto.proposto));
					lstDestinatarios.add(new EmailRecord(VariavelCache.getInstance().getValor(VariavelConstantes.EMAIL_ADMIN_ALUGUERELAXE), "AlugueRelaxe"));
					
					Email email = EmailFactory.getInstanceEmailOferecimento(conteudo);
					
					// Todos os robots ao enviar email precisam reconfigurar a porta SMTP
					ServidorSMTP smtp = new ServidorSMTP(VariavelCache.getInstance().getValor(VariavelConstantes.SMTP_SERVER),
															Integer.valueOf(VariavelCache.getInstance().getValor(VariavelConstantes.SMTP_PORT_EXTRANET)));
					email.enviar(null,lstDestinatarios, null, "AlugueRelaxe Oferecimento: Aluguel de Temporada :: " + ifcdto.imovel.endereco.cidade + " - " + ifcdto.imovel.endereco.uf ,null, smtp);

				}
			}
				
			if (lEnviar){
				if (ifcdto.imagensImovelTB == null){
					this.setStatus(CicloVidaRobot.ROBOT_TERMINADO, MSG_PADRAO_SUCESSO + "Oferecimento não enviado. Motivo: Imovel #" + ifcdto.imovel.id + " sem imagens.");
				} else {
					this.setStatus(CicloVidaRobot.ROBOT_TERMINADO,MSG_PADRAO_SUCESSO + "Destino: " + cadto.proposto.toUpperCase() + " (" + cadto.email + ").");
				}
			} else {
				this.setStatus(CicloVidaRobot.ROBOT_TERMINADO,MSG_PADRAO_SUCESSO + MsgCustomizada);
			}

		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
