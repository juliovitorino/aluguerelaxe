package br.com.jcv.backend.sorteio;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import br.com.jcv.backend.datemanager.DateManagerBase;
import br.com.jcv.backend.email.Email;
import br.com.jcv.backend.email.EmailFactory;
import br.com.jcv.backend.email.EmailRecord;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ImovelDTO;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;
import br.com.jcv.backend.imovel.ImovelServiceImpl;
import br.com.jcv.backend.imovel.publicidade.PublicidadeImovelDTO;
import br.com.jcv.backend.interfaces.services.ImovelService;
import br.com.jcv.backend.robot.Robot;
import br.com.jcv.backend.template.TemplateConstantes;

public abstract class Sorteio implements ISorteio {
	
	private Robot robot = null;
	private int sorteados = 0;
	
	protected List<ImovelFichaCompletaDTO> getSortudos(List< Long> lstimv, int maxImoveisSorteio) throws AlugueRelaxeException{
		List<ImovelFichaCompletaDTO> lst = null;
		
		if ((lstimv != null) && (lstimv.size() > 0)) {
			int maximoRND = lstimv.size();
			Random rndgen = new Random();
			lst = new ArrayList<ImovelFichaCompletaDTO>();
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			for(int i = 0; i < maxImoveisSorteio; i++) {
				int n = rndgen.nextInt(maximoRND);
				Long dto = lstimv.get(n);
				lstimv.remove(n);
				maximoRND--;
				ImovelFichaCompletaDTO ifcdto = is.pesquisarFichaCompletaImovel(dto.longValue());
				lst.add(ifcdto);
				if (maximoRND == 0){
					break;
				}
			}
		}

		return lst;
	}
	
	public void setSorteados(int n){
		this.sorteados = n;
	}
	
	public int getSorteados() {
		return this.sorteados;
	}
	
	public void setRobot(Robot robot) {
		this.robot = robot;
	}
	
	public Robot getRobot() {
		return this.robot;
	}
	

	public void enviarNotificacaoSorteio(PublicidadeImovelDTO dto) throws AlugueRelaxeException{
		
		DateManagerBase df = DateManagerBase.getDateManagerInstance(dto.publicidade.dataFim);
		DateManagerBase di = DateManagerBase.getDateManagerInstance(dto.publicidade.dataInicio);
		
		Map<String,String> conteudo = new HashMap<String, String>();
		try {
			conteudo.put(TemplateConstantes.TCS_TAG_DATA_FINAL_DE_PUBLICIDADE, df.getDateCustom("dd/MM/yyyy"));
			conteudo.put(TemplateConstantes.TCS_TAG_DATA_INICIO_PUBLICIDADE, di.getDateCustom("dd/MM/yyyy"));
		} catch (ParseException e) {
			conteudo.put(TemplateConstantes.TCS_TAG_DATA_FINAL_DE_PUBLICIDADE, dto.publicidade.dataFim.toString());
			conteudo.put(TemplateConstantes.TCS_TAG_DATA_INICIO_PUBLICIDADE, dto.publicidade.dataInicio.toString());
		}
		conteudo.put(TemplateConstantes.TCS_TAG_ID_IMOVEL, String.valueOf(dto.fichaImovel.imovel.id));
		conteudo.put(TemplateConstantes.TCS_TAG_NOME_CLIENTE, dto.fichaImovel.cliente.nome);
		conteudo.put(TemplateConstantes.TCS_TAG_SECAO_PUBLICIDADE, (dto.publicidade.tipoPublicidade.equals("PP") ? "1a Pagina" : "Outros Destaques")) ;
		conteudo.put(TemplateConstantes.TCS_TAG_TITULO_ANUNCIO, dto.fichaImovel.imovel.descricaoTituloAnuncio);
		
		Email email = EmailFactory.getInstanceEmailComunicadoSorteio(conteudo);
		
		ArrayList<EmailRecord> lst = new ArrayList<EmailRecord>();
		lst.add(new EmailRecord(dto.fichaImovel.cliente.email,dto.fichaImovel.cliente.nome));

		email.enviar(lst, null, "AlugueRelaxe - Publicidade Gratuita", null);
		
	}

}