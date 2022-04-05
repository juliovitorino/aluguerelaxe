package br.com.jcv.backend.filasms;

import java.util.ArrayList;
import java.util.List;

import br.com.jcv.backend.cliente.ClienteBusinessImpl;
import br.com.jcv.backend.cliente.ClienteDTO;
import br.com.jcv.backend.cliente.ClienteServiceImpl;
import br.com.jcv.backend.cliente.TelefoneDTO;
import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfaces.business.ClienteBusiness;
import br.com.jcv.backend.interfaces.business.FilaSMSBusiness;
import br.com.jcv.backend.interfaces.services.ClienteService;
import br.com.jcv.backend.interfaces.services.FilaSMSService;

public class FilaSMSFactory {

	private FilaSMSFactory() {}
	
	public static final FilaSMSDTO getDTO(String celular, String msg, String modo, int prioridade) {
		FilaSMSDTO dto = new FilaSMSDTO();
		dto.celular = celular;
		dto.msg = msg;
		dto.modo = modo;
		dto.prioridade = prioridade;
		return dto;
	}
	
	public static final FilaSMSDTO getDTO(String celular, String msg, String modo) {
		FilaSMSDTO dto = new FilaSMSDTO();
		dto.celular = celular;
		dto.msg = msg;
		dto.modo = modo;
		return dto;
	}
	
	public static final FilaSMSDTO getDTO(String celular, String msg) {
		FilaSMSDTO dto = new FilaSMSDTO();
		dto.celular = celular;
		dto.msg = msg;
		dto.modo = Constantes.SMS_MODO_ENVIO_GATEWAY;
		return dto;
	}
	
	public static final List<FilaSMSDTO> getDTOCliente(long idCliente, String msg) throws AlugueRelaxeException {
		List<FilaSMSDTO> lst = null;
		ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
		ClienteDTO cdto = new ClienteDTO();
		cdto.id = idCliente;
		cdto = cs.pesquisarRegistro(cdto);
		if (cdto != null) {
			if ((cdto.telefones != null) && (cdto.telefones.size() > 0)) {
				lst = new ArrayList<FilaSMSDTO>();
				for ( TelefoneDTO cel : cdto.telefones){
					if (cel.indTipoTelefone.equals("C")){
						FilaSMSDTO dto = new FilaSMSDTO();
						dto.celular = cel.ddd + cel.telefone;
						dto.msg = msg;
						dto.modo = Constantes.SMS_MODO_ENVIO_GATEWAY;
						lst.add(dto);
					}
				}
			}
		}
		
		return lst;
	}
	
	public static final List<FilaSMSDTO> getDTOCliente(long idCliente, String msg, DAOFactory daofactory) throws AlugueRelaxeException {
		List<FilaSMSDTO> lst = null;
		ClienteBusiness<ClienteDTO> cs = new ClienteBusinessImpl();
		ClienteDTO cdto = new ClienteDTO();
		cdto.id = idCliente;
		cdto = cs.procurar(daofactory,cdto);
		if (cdto != null) {
			cdto.telefones = cs.obtemTelefonesCliente(daofactory,idCliente);
		}
		if (cdto != null) {
			if ((cdto.telefones != null) && (cdto.telefones.size() > 0)) {
				lst = new ArrayList<FilaSMSDTO>();
				for ( TelefoneDTO cel : cdto.telefones){
					if (cel.indTipoTelefone.equals("C")){
						FilaSMSDTO dto = new FilaSMSDTO();
						dto.celular = cel.ddd + cel.telefone;
						dto.msg = msg;
						dto.modo = Constantes.SMS_MODO_ENVIO_GATEWAY;
						lst.add(dto);
					}
				}
			}
		}
		
		return lst;
	}
	
	public static final void enviarSMS(FilaSMSDTO dto, DAOFactory daofactory) throws AlugueRelaxeException {
		if (dto != null){
			FilaSMSBusiness fs = new FilaSMSBusinessImpl();
			fs.incluir(daofactory, dto);
		}
	}
	
	public static final void enviarSMS(FilaSMSDTO dto) throws AlugueRelaxeException {
		if (dto != null){
			FilaSMSService fs = new FilaSMSServiceImpl();
			fs.gravarRegistro(dto);
		}
	}
	
	public static final void enviarSMS(List<FilaSMSDTO> lst) throws AlugueRelaxeException {
		if (lst != null) {
			for ( FilaSMSDTO sms : lst){
				FilaSMSFactory.enviarSMS(sms);
			}
		}
	}
	
	public static final void enviarSMS(List<FilaSMSDTO> lst, DAOFactory daofactory) throws AlugueRelaxeException {
		if (lst != null) {
			for ( FilaSMSDTO sms : lst){
				FilaSMSFactory.enviarSMS(sms,daofactory);
			}
		}
	}
	
}