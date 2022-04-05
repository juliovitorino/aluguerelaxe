package br.com.jcv.aluguerelaxe.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.Constantes;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.LocatarioVO;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.ReservaRPC;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.ReservaVO;
import br.com.jcv.aluguerelaxe.client.administracao.console.tracker.TrackerVO;
import br.com.jcv.aluguerelaxe.client.imovel.reserva.AvaliacaoReservaVO;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;
import br.com.jcv.aluguerelaxe.server.datatransferobject.DTOAvaliacaoReservaImovelImpl;
import br.com.jcv.aluguerelaxe.server.datatransferobject.DTOContatoAnuncianteImpl;
import br.com.jcv.aluguerelaxe.server.datatransferobject.DTOLocatarioImpl;
import br.com.jcv.aluguerelaxe.server.datatransferobject.DTOReservaImpl;
import br.com.jcv.aluguerelaxe.server.datatransferobject.DTOReservaLightImpl;
import br.com.jcv.backend.cliente.ClienteDTO;
import br.com.jcv.backend.cliente.ClienteServiceImpl;
import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.interfaces.services.ClienteService;
import br.com.jcv.backend.interfaces.services.LocatarioService;
import br.com.jcv.backend.interfaces.services.ReservaService;
import br.com.jcv.backend.locatario.LocatarioDTO;
import br.com.jcv.backend.locatario.LocatarioServiceImpl;
import br.com.jcv.backend.reserva.ReservaDTO;
import br.com.jcv.backend.reserva.ReservaServiceImpl;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ReservaRPCImpl extends RemoteServiceServlet implements ReservaRPC {

	private static final long serialVersionUID = -6079781449905358421L;
	
	public ReservaVO getReservaChaveTracker(String chaveTracker)
			throws AlugueRelaxeFrontException {
		ReservaVO vo = null;
		ReservaService<ReservaDTO> is = new ReservaServiceImpl();
		try {
			ReservaDTO dto = is.procurarChaveTracker(chaveTracker);
			vo = (new DTOReservaImpl()).getDataTransferObject(dto);
		} catch (AlugueRelaxeException e) {
			if (e.getObjeto() instanceof List){
				throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem(), (List)e.getObjeto()); 
			}
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem()); 		
		}
		
		return vo;
	}

	public ReservaVO confirmarReserva(String chave, Date dataRealDeposito,
			double valorRealDeposito) throws AlugueRelaxeFrontException {
		ReservaVO vo = null;
		ReservaService<ReservaDTO> is = new ReservaServiceImpl();
		try {
			ReservaDTO dto = is.confirmarReserva(chave, dataRealDeposito, valorRealDeposito);
			vo = (new DTOReservaImpl()).getDataTransferObject(dto);
		} catch (AlugueRelaxeException e) {
			if (e.getObjeto() instanceof List){
				throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem(), (List)e.getObjeto()); 
			}
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem()); 		
		}
		
		return vo;
	}

	public LocatarioVO pesquisarLocatarioReserva(String email)
			throws AlugueRelaxeFrontException {
		LocatarioVO vo = null;
		LocatarioService<LocatarioDTO> is = new LocatarioServiceImpl();
		try {
			LocatarioDTO dto = is.pesquisar(email);
			vo = (new DTOLocatarioImpl()).getDataTransferObject(dto);
		} catch (AlugueRelaxeException e) {
			if (e.getObjeto() instanceof List){
				throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem(), (List)e.getObjeto()); 
			}
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem()); 		
		}
		
		return vo;
	}

	public ReservaVO criarPreReserva(ReservaVO vo)
			throws AlugueRelaxeFrontException {
		ReservaVO retorno = null;
		ReservaService<ReservaDTO> is = new ReservaServiceImpl();
		try {
			ReservaDTO dto = is.criarPreReserva((new DTOReservaImpl()).getDataTransferObjectCriarReserva(vo));
			retorno = (new DTOReservaImpl()).getDataTransferObject(dto);
		} catch (AlugueRelaxeException e) {
			if (e.getObjeto() instanceof List){
				throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem(), (List)e.getObjeto()); 
			}
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem()); 		
		}
		
		return retorno;
	}

	public ReservaVO aprovarPreReservaTracker(TrackerVO chave)
			throws AlugueRelaxeFrontException {
		ReservaVO retorno = null;
		ReservaService<ReservaDTO> is = new ReservaServiceImpl();
		try {
			ReservaDTO dto = is.aprovarPreReservaTracker(chave.codigo);
			retorno = (new DTOReservaLightImpl()).getDataTransferObject(dto);
		} catch (AlugueRelaxeException e) {
			if (e.getObjeto() instanceof List){
				throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem(), (List)e.getObjeto()); 
			}
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem()); 		
		}
		
		return retorno;
	}

	public ReservaVO reprovarPreReservaTracker(TrackerVO chave)
			throws AlugueRelaxeFrontException {
		ReservaVO retorno = null;
		ReservaService<ReservaDTO> is = new ReservaServiceImpl();
		try {
			ReservaDTO dto = is.reprovarPreReservaTracker(chave.codigo);
			retorno = (new DTOReservaLightImpl()).getDataTransferObject(dto);
		} catch (AlugueRelaxeException e) {
			if (e.getObjeto() instanceof List){
				throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem(), (List)e.getObjeto()); 
			}
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem()); 		
		}
		
		return retorno;
	}

	public ReservaVO solicitarLiberacaoDeposito(long idClienteSolicitante,
			String voucher, String tracking) throws AlugueRelaxeFrontException {
		ReservaVO retorno = null;
		ReservaService<ReservaDTO> is = new ReservaServiceImpl();
		try {
			ReservaDTO dto = is.solicitarLiberacaoDeposito(idClienteSolicitante, voucher, tracking);
			retorno = (new DTOReservaLightImpl()).getDataTransferObject(dto);
		} catch (AlugueRelaxeException e) {
			if (e.getObjeto() instanceof List){
				throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem(), (List)e.getObjeto()); 
			}
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem()); 		
		}
		
		return retorno;
	}

	public ReservaVO transferirDeposito(TrackerVO chave)
			throws AlugueRelaxeFrontException {
		ReservaVO retorno = null;
		ReservaService<ReservaDTO> is = new ReservaServiceImpl();
		try {
			ReservaDTO dto = is.transferirDeposito(chave.codigo);
			retorno = (new DTOReservaLightImpl()).getDataTransferObject(dto);
		} catch (AlugueRelaxeException e) {
			if (e.getObjeto() instanceof List){
				throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem(), (List)e.getObjeto()); 
			}
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem()); 		
		}
		
		return retorno;
	}

	public List<ReservaVO> listarTarefasPendentes(String fase)
			throws AlugueRelaxeFrontException {
		List<ReservaVO> lst = null;
		ReservaService<ReservaDTO> is = new ReservaServiceImpl();
		try {
			List<ReservaDTO> lstdto = is.listarTarefasPendentes(fase);
			if (lstdto != null){
				lst = new ArrayList<ReservaVO>();
				for (ReservaDTO dto : lstdto){
					lst.add((new DTOReservaLightImpl()).getDataTransferObject(dto));
				}
			}
		} catch (AlugueRelaxeException e) {
			if (e.getObjeto() instanceof List){
				throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem(), (List)e.getObjeto()); 
			}
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem()); 		
		}
		
		return lst;
	}

	public ReservaVO getReserva(String hashContatoAnunciante)
			throws AlugueRelaxeFrontException {
		//----------------------------------------------------------
		// Obtem o caminho root_fotos das imagens dentro do web.xml
		//----------------------------------------------------------
		ServletContext context = getServletContext();
		String root_fotos = context.getInitParameter(Constantes.ROOT_FOTOS);
		
		ReservaVO retorno = null;
		ReservaService<ReservaDTO> is = new ReservaServiceImpl();
		ClienteService<ClienteDTO> cs = new ClienteServiceImpl();
		try {
			ReservaDTO dto = is.pesquisarReserva(hashContatoAnunciante);
			dto.ifcdto.cliente = cs.pesquisarRegistro(dto.ifcdto.cliente);
			retorno = (new DTOReservaImpl()).getDataTransferObject(dto);
			retorno.imgVisitante = "/stream/thread/" + retorno.idContato + "/" + retorno.imgVisitante;
			retorno.contatoCliente = (new DTOContatoAnuncianteImpl(root_fotos)).getDataTransferObject(dto.contatoCliente);


		} catch (AlugueRelaxeException e) {
			if (e.getObjeto() instanceof List){
				throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem(), (List)e.getObjeto()); 
			}
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem()); 		
		}
		
		return retorno;
	}

	public VOPadrao enviarComentario(AvaliacaoReservaVO vo)
			throws AlugueRelaxeFrontException {
		VOPadrao retorno = null;
		ReservaService<ReservaDTO> is = new ReservaServiceImpl();
		try {
			DTOPadrao dto = is.criarComentario((new DTOAvaliacaoReservaImovelImpl()).getDataTransferObject(vo));
			retorno = new VOPadrao();
			retorno.msgcode = dto.msgcode;
			retorno.msgcodeString = dto.msgcodeString;
		} catch (AlugueRelaxeException e) {
			if (e.getObjeto() instanceof List){
				throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem(), (List)e.getObjeto()); 
			}
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem()); 		
		}
		
		return retorno;
	}

}
