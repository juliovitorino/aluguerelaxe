package br.com.jcv.backend.template;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import br.com.jcv.backend.exception.AlugueRelaxeException;


/**
 * TODO Comentar aqui.
 *
 * @author elmt
 *
 */
public class TemplateFactory {
	
	private TemplateFactory() {
		
	}
	/**
	 * Cria uma nova instância das implementações sobre os templates de e-mail.
	 *
	 * @param int
	 * @param Map
	 * @param DAOFactory
	 * @return Comentar aqui.
	 * @throws AlugueRelaxeException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws SecurityException 
	 * @throws  
	 */
	public static Template getInstance(Map<String,String> conteudo, int idTemplate) throws AlugueRelaxeException {
	
		Template retorno = null;
		
		// Busca informacoes do template no cache identificado pelo idTemplate
		TemplateDTO tdto = TemplateCache.getInstance().getTemplate(String.valueOf(idTemplate));
		tdto.conteudo = conteudo;
		
		if (tdto != null) {
			try {
				// Instancia a classe concreta que vai resolver o template 
				Class<?> c = Class.forName(tdto.classe);
				retorno = (Template) c.getConstructor(TemplateDTO.class).newInstance(tdto);  
			} catch (ClassNotFoundException x) {
				x.printStackTrace();
			} catch (InstantiationException x) {
				x.printStackTrace();
			} catch (IllegalAccessException x) {
				x.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
		return retorno;
	}
	
	/**
	 * Cria uma nova instância das implementações sobre os templates de e-mail.
	 *
	 * @param idTemplate
	 * @param templateHtml
	 * @param conteudo
	 * @return Comentar aqui.
	 * @throws AlugueRelaxeException 
	 */
	@Deprecated
	public static Template getInstance(int idTemplate,Map<String,String> conteudo) throws AlugueRelaxeException {
		
		switch(idTemplate) {
		case TemplateConstantes.TEMPLATE_CADASTRO_CLIENTE_IMCOMPLETO:
			return new TemplateCadastroClienteImcompleto(conteudo);
		case TemplateConstantes.TEMPLATE_CONTATO_ANUNCIANTE_PENDENTE:
			return new TemplateContatoAnuniciantePendente(conteudo);
		case TemplateConstantes.TEMPLATE_CONTATO_ANUNCIANTE_LIBERADO:
			return new TemplateContatoAnunicianteLiberado(conteudo);
		case TemplateConstantes.TEMPLATE_ATIVAR_NOVA_CONTA:
			return new TemplateAtivarNovaConta(conteudo);
		case TemplateConstantes.TEMPLATE_CONTA_ATIVADA:
			return new TemplateContaAtivada(conteudo);
		case TemplateConstantes.TEMPLATE_CONTEUDO_DEPOIMENTO:
			return new TemplateConteudoDepoimento(conteudo);
		case TemplateConstantes.TEMPLATE_CONTEUDO_UPLOAD:
			return new TemplateConteudoUpload(conteudo);
		case TemplateConstantes.TEMPLATE_GALERIA_FOTOS_VAZIA:
			return new TemplateNotiticacaoGaleriaFotosVazia(conteudo);
		case TemplateConstantes.TEMPLATE_INDICAR_AMIGO:
			return new TemplateIndicarImovelAmigo(conteudo);
		case TemplateConstantes.TEMPLATE_FALE_CONOSCO:
			return new TemplateFaleConosco(conteudo);
		case TemplateConstantes.TEMPLATE_NOVO_CODIGO_ACESSO:
			return new TemplateNovoCodigoAcesso(conteudo);
		case TemplateConstantes.TEMPLATE_COMUNICADO_SORTEIO:
			return new TemplateComunicadoSorteio(conteudo);
		case TemplateConstantes.TEMPLATE_AVALIACAO_IMOVEL:
			return new TemplateAvaliacaoAnuncio(conteudo);
		case TemplateConstantes.TEMPLATE_ASSINANTES:
			return new TemplateInscricaoPromocao(conteudo);
		case TemplateConstantes.TEMPLATE_AMIGO_INDICA_AMIGO:
			return new TemplateAmigoIndicaAmigo(conteudo);
		case TemplateConstantes.TEMPLATE_PROMOCAO_ATIVADA_SUCESSO:
			return new TemplatePromocaoAtivada(conteudo);
		case TemplateConstantes.TEMPLATE_IMOVEL_PENDENTE_PGTO:
			return new TemplateImovelPendentePgto(conteudo);
		case TemplateConstantes.TEMPLATE_NOTIFICACAO_ALUGUERELAXE:
			return new TemplateNotificarCadastroAlteracaoImovel(conteudo);
		case TemplateConstantes.TEMPLATE_CONTATO_ANUNCIANTE_LIBERADO_SIMPLES:
			return new TemplateContatoAnunicianteLiberadoSimples(conteudo);
		case TemplateConstantes.TEMPLATE_VALIDAR_PRE_RESERVA:
			return new TemplateValidarPreReserva(conteudo);
		case TemplateConstantes.TEMPLATE_PRE_RESERVA_APROVADA:
			return new TemplateAguadarPgtoPreReserva(conteudo);
		case TemplateConstantes.TEMPLATE_CTR_LOCACAO_TEMPORADA_LOCATARIO:
			return new TemplateContratoTemporadaLocatario(conteudo);
		case TemplateConstantes.TEMPLATE_CONFIRMACAO_PGTO_RESERVA:
			return new TemplateConfirmacaoPgtoReserva(conteudo);
		case TemplateConstantes.TEMPLATE_VOUCHER:
			return new TemplateVoucher(conteudo);
		case TemplateConstantes.TEMPLATE_CTR_LOCACAO_TEMPORADA_LOCADOR_SEM_CAUCAO:
			return new TemplateContratoTemporadaLocatarioSemCaucao(conteudo);
		case TemplateConstantes.TEMPLATE_INSTRUCOES_CAPTAR_AUTORIZACAO:
			return new TemplateInstrucoesCaptarAutorizacao(conteudo);
		case TemplateConstantes.TEMPLATE_EXECUTAR_TRANSFERENCIA_DEPOSITO:
			return new TemplateExecutarTransferenciaDeposito(conteudo);
		case TemplateConstantes.TEMPLATE_DEPOSITO_TRANSFERIDO:
			return new TemplateDepositoTransferido(conteudo);
		case TemplateConstantes.TEMPLATE_PROMOCAO_PADRAO:
			return new TemplatePromocaoPadrao(conteudo);
		case TemplateConstantes.TEMPLATE_DESKTOP:
			return new TemplateDesktop(conteudo);
		case TemplateConstantes.TEMPLATE_NOTIFICACAO_ANUNCIOS_A_VENCER:
			return new TemplateNotificacaoAnunciosAVencer(conteudo);
		case TemplateConstantes.TEMPLATE_NOTIFICA_PLANO_RENOVADO:
			return new TemplateNotificaPlanoRenovado(conteudo);
		case TemplateConstantes.TEMPLATE_NOTIFICA_PLANO_BLOQUEADO:
			return new TemplateNotificaPlanoBloqueado(conteudo);
		case TemplateConstantes.TEMPLATE_NOTIFICA_PLANO_RENOVADO_PENDENTE:
			return new TemplateNotificaPlanoPendente(conteudo);
		case TemplateConstantes.TEMPLATE_NOTIFICACAO_INATIVACAO:
			return new TemplateNotificarInativacaoImovel(conteudo);
		case TemplateConstantes.TEMPLATE_ALERTA_24H:
			return new TemplateEmailAlerta24h(conteudo);
		case TemplateConstantes.TEMPLATE_OFERECIMENTO:
			return new TemplateOferecimento(conteudo);
		case TemplateConstantes.TEMPLATE_THREAD_CRIADA:
			return new TemplateThreadCriada(conteudo);
		case TemplateConstantes.TEMPLATE_THREAD_VISITANTE:
			return new TemplateThreadParaVisitante(conteudo);
		case TemplateConstantes.TEMPLATE_THREAD_ANUNCIANTE:
			return new TemplateThreadParaAnunciante(conteudo);
		case TemplateConstantes.TEMPLATE_THREAD_PENDENCIA_APROVACAO:
			return new TemplateThreadPendenciaAprovacao(conteudo);
		case TemplateConstantes.TEMPLATE_THREAD_EDITADA:
			return new TemplateThreadEditada(conteudo);
		case TemplateConstantes.TEMPLATE_THREAD_ANUNCIANTE_RESPOSTA:
			return new TemplateThreadParaAnuncianteResposta(conteudo);
		case TemplateConstantes.TEMPLATE_THREAD_VISITANTE_RESPOSTA:
			return new TemplateThreadParaVisitanteResposta(conteudo);
		case TemplateConstantes.TEMPLATE_PGTO_PAGSEGURO:
			return new TemplatePgtoPagSeguro(conteudo);
		case TemplateConstantes.TEMPLATE_VALIDAR_PRE_RESERVA_VISITANTE:
			return new TemplateValidarPreReservaVisitante(conteudo);
		case TemplateConstantes.TEMPLATE_THREAD_BOAS_VINDAS:
			return new TemplateThreadBoasVindas(conteudo);
		case TemplateConstantes.TEMPLATE_THREAD_COMENTARIO_ANUNCIANTE:
			return new TemplateThreadComentarioParaAnunciante(conteudo);
		case TemplateConstantes.TEMPLATE_ORIENTACOES_RESGATE_RESERVA:
			return new TemplateOrientacoesResgateReserva(conteudo);
		case TemplateConstantes.TEMPLATE_AVALIACAO_RESERVA:
			return new TemplateAvaliacaoReserva(conteudo);
		case TemplateConstantes.TEMPLATE_OFERTA_PUBLICIDADE_PP:
			return new TemplateOfertaPublicidadePP(conteudo);
		case TemplateConstantes.TEMPLATE_OFERTA_PUBLICIDADE_PD:
			return new TemplateOfertaPublicidadePD(conteudo);
		case TemplateConstantes.TEMPLATE_PROPERTA_INDEX:
			return new TemplatePropertaIndex(conteudo);
		case TemplateConstantes.TEMPLATE_PROPERTA_FICHA_IMOVEL:
			return new TemplatePropertaFichaImovel(conteudo);
		case TemplateConstantes.TEMPLATE_PROPERTA_PROPRIEDADES_CLIENTE:
			return new TemplatePropertaPropriedadesCliente(conteudo);
		default:
			return null;
		}
	}

}

