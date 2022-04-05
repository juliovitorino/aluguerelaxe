package br.com.jcv.aluguerelaxe.server;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.depoimento.DepoimentoRPC;
import br.com.jcv.aluguerelaxe.client.depoimento.DepoimentoVO;
import br.com.jcv.backend.datemanager.DateManager;
import br.com.jcv.backend.depoimento.DepoimentoDTO;
import br.com.jcv.backend.depoimento.DepoimentoServiceImpl;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.interfaces.services.DepoimentoService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class DepoimentoRPCImpl extends RemoteServiceServlet implements
		DepoimentoRPC {

	private static final Logger logger = Logger.getLogger(DepoimentoRPCImpl.class);

	private static final long serialVersionUID = 2686329891648297637L;

	public List<DepoimentoVO> ListarPaginaDepoimentos() throws AlugueRelaxeFrontException {
		List<DepoimentoVO> lst = null;
		try {
			
			//-------------------------------------------
			// Busca uma pagina de depoimentos
			// e carrega a lista de retorno
			//-------------------------------------------
			DepoimentoService<DepoimentoDTO> ds = new DepoimentoServiceImpl();
			List<DepoimentoDTO> lstdto = ds.ListarPaginaDepoimentos();
			if (lstdto != null){
				lst = new ArrayList<DepoimentoVO>();
				for (DepoimentoDTO depodto : lstdto){
					DepoimentoVO depovo = new DepoimentoVO();
					depovo.id = depodto.id;
					depovo.depoimento = depodto.depoimento;
					depovo.nome = depodto.nome;
					try {
						depovo.dataDepoimento = DateManager.getDateManagerInstance(depodto.dataCadastro).getDateCustom("dd/MM/yyyy hh:mm");
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					lst.add(depovo);
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

	public void criarNovoDepoimento(DepoimentoVO dvo) throws AlugueRelaxeFrontException{
		try {
			
			DepoimentoDTO ddto = new DepoimentoDTO();
			ddto.nome = dvo.nome;
			ddto.depoimento = dvo.depoimento;
			
			DepoimentoService<DepoimentoDTO> ds = new DepoimentoServiceImpl();
			ddto = ds.adicionarDepoimento(ddto);
			
		} catch (AlugueRelaxeException e) {
			if (e.getObjeto() instanceof List){
				throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem(), (List)e.getObjeto()); 
			}
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem());
		}

	}
	
	public DepoimentoVO getProximoDepoimento(long id) {
		logger.debug("buscando id superior ao codigo: " + id);
		
		DepoimentoVO vo = null;
		try {
			DepoimentoService<DepoimentoDTO> ds = new DepoimentoServiceImpl();
			DepoimentoDTO dto = ds.getProximoDepoimento(id);
			if (dto != null) {
				vo = new DepoimentoVO();
				vo.id = dto.getId();
				vo.nome = dto.getNome();
				vo.depoimento = dto.getDepoimento();

				logger.debug("        id: " + vo.id);
				logger.debug("      nome: " + vo.nome);
				logger.debug("depoimento: " + vo.depoimento);
			}

		} catch (AlugueRelaxeException e) {
			// TODO: handle exception
		}
		return vo;
	}
	
	public DepoimentoVO getPrevDepoimento(long id) {
		logger.debug("buscando id superior ao codigo: " + id);
		
		DepoimentoVO vo = null;
		try {
			DepoimentoService<DepoimentoDTO> ds = new DepoimentoServiceImpl();
			DepoimentoDTO dto = ds.getPrevDepoimento(id);
			vo = new DepoimentoVO();
			vo.id = dto.getId();
			vo.nome = dto.getNome();
			vo.depoimento = dto.getDepoimento();

			logger.debug("        id: " + vo.id);
			logger.debug("      nome: " + vo.nome);
			logger.debug("depoimento: " + vo.depoimento);
		} catch (AlugueRelaxeException e) {
			// TODO: handle exception
		}
		return vo;
	}
	

}
