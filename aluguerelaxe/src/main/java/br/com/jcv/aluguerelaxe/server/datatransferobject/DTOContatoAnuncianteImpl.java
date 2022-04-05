package br.com.jcv.aluguerelaxe.server.datatransferobject;

import java.text.ParseException;
import java.util.ArrayList;

import br.com.jcv.aluguerelaxe.client.imovel.ficha.ContatoClienteVO;
import br.com.jcv.aluguerelaxe.client.imovel.thread.ContatoAnuncianteThreadVO;
import br.com.jcv.backend.datatransfer.AbstractDataTransferObject;
import br.com.jcv.backend.datemanager.DateManagerBase;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ContatoClienteDTO;
import br.com.jcv.backend.imovel.thread.ContatoAnuncianteThreadDTO;
import br.com.jcv.backend.template.TemplateConstantes;
import br.com.jcv.backend.template.TemplateFactory;

public class DTOContatoAnuncianteImpl
		implements
		AbstractDataTransferObject<ContatoClienteDTO, ContatoClienteVO> {
	
	private String path;
	
	public DTOContatoAnuncianteImpl(String path){
		this.path = path;
	}

	public ContatoClienteVO getDataTransferObject(
			ContatoClienteDTO dto) {
		ContatoClienteVO vo = null;
		if (dto != null){
			vo = new ContatoClienteVO();
			vo.ifcvo = (new DTOImovelFichaCompletaImpl(path)).getDataTransferObject(dto.ifcdto);
			vo.chegadaPrevista = dto.chegadaPrevista;
			vo.partidaPrevista = dto.partidaPrevista;
			vo.diasTemporada = DateManagerBase.getDateManagerInstance().dataDiff(vo.chegadaPrevista, vo.partidaPrevista);
			vo.cidade = dto.cidade;
			vo.ddd = dto.ddd;
			vo.telefone = dto.telefone;
			vo.email = dto.email;
			vo.id = dto.id;
			vo.idImovel = dto.idImovel;
			vo.informacoesAdicionais = dto.informacoesAdicionais;
			vo.nome = dto.proposto;
			vo.primeiroNome = dto.primeiroNome;
			vo.pais = dto.pais;
			vo.qtdeAdultos = dto.qtdeAdultos;
			vo.qtdeCriancas = dto.qtdeCriancas;
			vo.uf = dto.uf;
			vo.codigoOMCThreadAdmin = dto.codigoOMCThreadAdmin;
			vo.codigoOMCThreadAnunciante = dto.codigoOMCThreadAnunciante;
			vo.codigoOMCThreadVisitante = dto.codigoOMCThreadVisitante;
			vo.imgvisitante = dto.imgVisitante;
			vo.msgcode = dto.msgcode;
			vo.msgcodeString = dto.msgcodeString;
			vo.taxaComissao = dto.taxaComissao;
			vo.threadStatus = dto.threadStatus;
			
			if (dto.reserva != null){
				vo.reserva = (new DTOReservaImpl()).getDataTransferObject(dto.reserva);
			}
			
			try {
				vo.chegadaPrevistaStr = (DateManagerBase.getDateManagerInstance(vo.chegadaPrevista)).getDateCustom("dd/MM/yyyy");
				vo.partidaPrevistaStr = (DateManagerBase.getDateManagerInstance(vo.partidaPrevista)).getDateCustom("dd/MM/yyyy");
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				DateManagerBase dc = DateManagerBase.getDateManagerInstance(dto.dataCadastro);
				vo.dataCadastro = dc.getDateTimeFull();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if ((dto.lstComentarios != null) && (dto.lstComentarios.size() > 0)) {
				vo.lstComentarios = new ArrayList<ContatoAnuncianteThreadVO>();
				for ( ContatoAnuncianteThreadDTO catdto	: dto.lstComentarios) {
					vo.lstComentarios.add ((new DTOContatoAnuncianteThreadImpl()).getDataTransferObject(catdto));
				}
			}
			

			if (vo.lstThread == null){
				vo.lstThread = new ArrayList<ContatoAnuncianteThreadVO>();
			}
			// Alimenta a thread de boas vindas
			ContatoAnuncianteThreadVO catboasvindas = new ContatoAnuncianteThreadVO();
			catboasvindas.origem = "M";
			catboasvindas.status = "A";
			catboasvindas.modo = "PR";
			try {
				catboasvindas.thread = TemplateFactory.getInstance(TemplateConstantes.TEMPLATE_THREAD_BOAS_VINDAS, null).getHtmlTemplate();
			} catch (AlugueRelaxeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catboasvindas.threadEditada = catboasvindas.thread;
			DateManagerBase hoje = DateManagerBase.getDateManagerInstance();
			catboasvindas.dataCadastro = hoje.getDate();
			try {
				catboasvindas.dataCadastroStr = hoje.getDateTimeFull();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			vo.lstThread.add(catboasvindas);
			
			if ((dto.lstThread != null) && (dto.lstThread.size() > 0)) {
					//vo.lstThread = new ArrayList<ContatoAnuncianteThreadVO>();
				for ( ContatoAnuncianteThreadDTO catdto	: dto.lstThread) {
					vo.lstThread.add ((new DTOContatoAnuncianteThreadImpl()).getDataTransferObject(catdto));
				}
			}			
/*			
			if ((dto.lstThread != null) && (dto.lstThread.size() > 0)) {
					vo.lstThread = new ArrayList<ContatoAnuncianteThreadVO>();
				for ( ContatoAnuncianteThreadDTO catdto	: dto.lstThread) {
					vo.lstThread.add ((new DTOContatoAnuncianteThreadImpl()).getDataTransferObject(catdto));
				}
			}
*/			
		}
		return vo;
	}

	public ContatoClienteDTO getDataTransferObject(ContatoClienteVO vo) {
		ContatoClienteDTO dto = null;
		if (vo != null){
			dto = new ContatoClienteDTO();
			dto.chegadaPrevista = vo.chegadaPrevista;
			dto.partidaPrevista = vo.partidaPrevista;
			dto.cidade = vo.cidade;
			dto.ddd = vo.ddd;
			dto.telefone = vo.telefone;
			dto.email = vo.email;
			dto.id = vo.id;
			dto.idImovel = vo.idImovel;
			dto.informacoesAdicionais = vo.informacoesAdicionais;
			dto.proposto = vo.nome;
			dto.pais = vo.pais;
			dto.qtdeAdultos = vo.qtdeAdultos;
			dto.qtdeCriancas = vo.qtdeCriancas;
			dto.uf = vo.uf;
		}
		return dto;
	}

	
}
