package br.com.jcv.backend.workers;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import br.com.jcv.backend.datemanager.DateManagerBase;
import br.com.jcv.backend.email.Email;
import br.com.jcv.backend.email.EmailFactory;
import br.com.jcv.backend.email.EmailRecord;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ImovelServiceImpl;
import br.com.jcv.backend.imovel.faturamento.ImovelPlanoFaturaDTO;
import br.com.jcv.backend.imovel.faturamento.ImovelPlanoFaturaServiceImpl;
import br.com.jcv.backend.imovel.publicidade.PublicidadeImovelDTO;
import br.com.jcv.backend.interfaces.services.ImovelPlanoFaturaService;
import br.com.jcv.backend.interfaces.services.ImovelService;
import br.com.jcv.backend.robot.CicloVidaRobot;
import br.com.jcv.backend.robot.Robot;
import br.com.jcv.backend.template.Template;
import br.com.jcv.backend.template.TemplateConstantes;
import br.com.jcv.backend.template.TemplateFactory;
import br.com.jcv.backend.utility.StringUtil;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;
import br.com.jcv.backend.workers.interfaces.IOfertaPublicidade;

public class OfertaPublicidadePD extends Robot implements IOfertaPublicidade {
	
	public static final String ROBOT_NOME = "OFERTA_PUBLICIDADE_PD";

	public OfertaPublicidadePD() {
		super(ROBOT_NOME, "PROMOTOR DE PUBLICIDADE PD");
	}
	
	public void executar() {
		// Nada a fazer
	}
	
	public void execute(PublicidadeImovelDTO pidto, String tipo) throws AlugueRelaxeException {
		this.setStatus(CicloVidaRobot.ROBOT_TRABALHANDO, "Criando publicidade ofertada " + pidto.publicidade.tipoPublicidade + " imovel #" + pidto.fichaImovel.imovel.id) ;
		try {
		
			//-----------------------------------------------------
			// Premissa e que o DTO ja venha previamente populado
			// pois somente as datas serao alteradas
			//------------------------------------------------------
			// Inclui o registro de publicidade para o periodo
			// com status PENDENTE de publicacao e PENDENTE de 
			// pagamento
			//------------------------------------------------------
			DateManagerBase dmbdi = DateManagerBase.getDateManagerInstance(pidto.publicidade.dataFim);
			dmbdi.add(1);
			int diasOferta = Integer.valueOf(VariavelCache.getInstance().getValor(VariavelConstantes.DIAS_OFERTA_PD));
			pidto.publicidade.dataInicio = dmbdi.getDate();
			pidto.publicidade.dataFim = DateManagerBase.getDateManagerInstance(dmbdi.getDate()).add(diasOferta);
			
			ImovelService is = new ImovelServiceImpl();
			PublicidadeImovelDTO pubCriado = is.criarPublicidade(pidto, pidto.plano.id);
			
			//------------------------------------------------------
			// Envia email para anunicante com a publicidade 
			// ofertada e o botao de pagamento do pagseguro
			// customizado para o pagamento
			//------------------------------------------------------
			ImovelPlanoFaturaService<ImovelPlanoFaturaDTO> ipfs = new ImovelPlanoFaturaServiceImpl();
			ImovelPlanoFaturaDTO ipfdto = ipfs.pesquisarUltimaFatura(pidto.fichaImovel.imovel.id, "D");
			this.notificar(pubCriado,ipfdto);
			
		
		} catch (AlugueRelaxeException e) {
			// TODO
		}
		this.setStatus(CicloVidaRobot.ROBOT_TERMINADO,"Publicidade ofertada " + 
						pidto.publicidade.tipoPublicidade + " imovel #" +
						pidto.fichaImovel.imovel.id + " processado com sucesso.");

	}
	
	public void notificar(PublicidadeImovelDTO pidto, ImovelPlanoFaturaDTO ipfdto) throws AlugueRelaxeException {
		DateManagerBase dmbdi = DateManagerBase.getDateManagerInstance(pidto.publicidade.dataInicio);
		DateManagerBase dmbdf = DateManagerBase.getDateManagerInstance(pidto.publicidade.dataFim);
		int dias = DateManagerBase.getDateManagerInstance().dataDiff(dmbdi.getDate(), dmbdf.getDate()) + 1;

		// Monta mapa de parametros do html customizado de pagamento
		Map<String,String> cps = new HashMap<String,String>();
		cps.put(TemplateConstantes.TAGC_EMAIL_ALUGUE_RELAXE, VariavelCache.getInstance().getValor(VariavelConstantes.EMAIL_ADMIN_ALUGUERELAXE));
		cps.put(TemplateConstantes.TAGC_CODIGO_PLANO, String.valueOf(pidto.plano.id));
		try {
			cps.put(TemplateConstantes.TAGC_DESCRICAO_DO_PLANO,pidto.plano.descricao + " - " + String.valueOf(dias) + " dias - " +
					DateManagerBase.getDateManagerInstance(pidto.publicidade.dataInicio).getDateCustom("dd/MM/yyyy") +
					" ate " +
					DateManagerBase.getDateManagerInstance(pidto.publicidade.dataFim).getDateCustom("dd/MM/yyyy"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cps.put(TemplateConstantes.TAGC_VALOR_TOTAL, StringUtil.valorCorreto(ipfdto.valorFatura,"."));
		cps.put(TemplateConstantes.TAGC_ID_FATURA,String.valueOf(ipfdto.id));
		cps.put(TemplateConstantes.TAGC_NOME_CLIENTE,pidto.fichaImovel.cliente.primeiroNome.toUpperCase());
		cps.put(TemplateConstantes.TAGC_EMAIL_CLIENTE,pidto.fichaImovel.cliente.email);
		
		// Monta template necessario para pagseguro
		Template tmpPagseguro = TemplateFactory.getInstance(TemplateConstantes.TEMPLATE_PGTO_PAGSEGURO,cps);
		
		// Monta mapa de parametros do e-mail a ser enviado ao anunciante com a oferta
		Map<String,String> conteudo = new HashMap<String,String>();
		try {
			String urlimg = VariavelCache.getInstance().getValor(VariavelConstantes.URL_VERIFICAR_IMAGEM_UPLOAD);
			urlimg = StringUtil.replaceStringNew(urlimg, "${clienteid}", String.valueOf(pidto.fichaImovel.cliente.id));
			urlimg = StringUtil.replaceStringNew(urlimg, "${imovelid}", String.valueOf(pidto.fichaImovel.imovel.id));
			urlimg = StringUtil.replaceStringNew(urlimg, "${imagemupload}", pidto.fichaImovel.imagensImovelXG.get(0).nome);
			
			conteudo.put(TemplateConstantes.TAGC_IMAGEM_IMOVEL, urlimg);
			conteudo.put(TemplateConstantes.TAGC_NOME_CLIENTE, pidto.fichaImovel.cliente.primeiroNome.toUpperCase());
			conteudo.put(TemplateConstantes.TAGC_TITULO_ANUNCIO,pidto.fichaImovel.imovel.descricaoTituloAnuncio);
			conteudo.put(TemplateConstantes.TAGC_CIDADE,pidto.fichaImovel.imovel.endereco.cidade);
			conteudo.put(TemplateConstantes.TAGC_DIAS,String.valueOf(dias));
			conteudo.put(TemplateConstantes.TAGC_UF,pidto.fichaImovel.imovel.endereco.uf);
			conteudo.put(TemplateConstantes.TAGC_TIPO_PUBLICIDADE,"OUTROS DESTAQUES");
			conteudo.put(TemplateConstantes.TAGC_DATA_INICIO_PUBLICIDADE, DateManagerBase.getDateManagerInstance(pidto.publicidade.dataInicio).getDateCustom("dd/MM/yyyy"));
			conteudo.put(TemplateConstantes.TAGC_DATA_FIM_PUBLICIDADE, DateManagerBase.getDateManagerInstance(pidto.publicidade.dataFim).getDateCustom("dd/MM/yyyy"));
			conteudo.put(TemplateConstantes.TAGC_VALOR_TOTAL,StringUtil.valorCorreto(ipfdto.valorFatura,"."));
			conteudo.put(TemplateConstantes.TAGC_BOTAO_PAGSEGURO,tmpPagseguro.getHtmlTemplate());
			DateManagerBase dmbdl = DateManagerBase.getDateManagerInstance(pidto.publicidade.dataInicio);
			dmbdl.add(-1);
			conteudo.put(TemplateConstantes.TAGC_DATA_LIMITE,dmbdl.getDateCustom("dd/MM/yyyy"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Monta destino do e-mail
		Email email = EmailFactory.getInstanceEmailOfertaPublicidadePD(conteudo);
		
		ArrayList<EmailRecord> lst = new ArrayList<EmailRecord>();
		lst.add(new EmailRecord(pidto.fichaImovel.cliente.email,pidto.fichaImovel.cliente.nome));

		email.enviar(lst, null, "[ALUGUERELAXE] - Oferta de Secao [Outros Destaques] imovel #" + pidto.fichaImovel.imovel.id, null);
	}
	
}
