package br.com.jcv.aluguerelaxe.server;

import java.util.ArrayList;
import java.util.List;

import br.com.jcv.aluguerelaxe.client.plano.PlanoVO;
import br.com.jcv.aluguerelaxe.client.plano.PlanosRPC;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.interfaces.services.PlanoService;
import br.com.jcv.backend.plano.PlanoDTO;
import br.com.jcv.backend.plano.PlanoServicesImpl;
import br.com.jcv.backend.utility.StringUtil;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class PlanosRPCImpl extends RemoteServiceServlet implements PlanosRPC {

	private static final long serialVersionUID = -5432235634354967154L;

	@SuppressWarnings("unchecked")
	public List<PlanoVO> listarPlanosAtivos() {
		List<PlanoVO> lstvo = null;
		try {
			
			PlanoService ufs = new PlanoServicesImpl();
			List<PlanoDTO> lstdto = ufs.listarRegistros();
			
			if (lstdto.size() > 0) {
				lstvo = new ArrayList<PlanoVO>();
				for (PlanoDTO pdto : lstdto) {
					lstvo.add(this.getVO(pdto));
				}
			}
		} catch (AlugueRelaxeException e) {
			// TODO: handle exception
		}
		
		return lstvo;

	}

	public List<PlanoVO> listarPlanosPorTipo(String tipo) {
		List<PlanoVO> lstvo = null;
		try {
			
			PlanoService ufs = new PlanoServicesImpl();
			List<PlanoDTO> lstdto = ufs.listarRegistros(tipo);
			
			if (lstdto.size() > 0) {
				lstvo = new ArrayList<PlanoVO>();
				for (PlanoDTO pdto : lstdto) {
					lstvo.add(this.getVO(pdto));
				}
			}
		} catch (AlugueRelaxeException e) {
			// TODO: handle exception
		}
		
		return lstvo;

	}
	public PlanoVO pesquisarPlano(long idPlano) {
		PlanoVO vo = null;
		try {
			
			PlanoService<PlanoDTO> ufs = new PlanoServicesImpl();
			PlanoDTO dto = ufs.pesquisarRegistro(idPlano);
			
			if (dto != null) {
				vo = this.getVO(dto);
			}
		} catch (AlugueRelaxeException e) {
			// TODO: handle exception
		}
		
		return vo;

	}

	private PlanoVO getVO(PlanoDTO pdto) {
		PlanoVO pvo = new PlanoVO();

		pvo.id = pdto.id;
		pvo.nome = pdto.nome;
		pvo.descricao = pdto.descricao;
		pvo.valor = pdto.valor - pdto.valorDesconto; // Valor final para o cliente
		pvo.valorstr = StringUtil.valorCorreto(pvo.valor); // Valor final para o cliente
		pvo.indicadorStatus = pdto.indicadorStatus;
		pvo.dataCadastro = pdto.dataCadastro;
		pvo.dataAtivacao = pdto.dataAtivacao;
		pvo.dataCancelamento = pdto.dataCancelamento;
		pvo.indicadorPeriodicidade = pdto.indicadorPeriodicidade;
		pvo.indicadorTipoCompra = pdto.indicadorTipoCompra;
		//pvo.periodicidadeTraducao = pdto.periodicidadeTraducao;
		pvo.numeroDiasCalculo = pdto.numeroDiasCalculo;
		return pvo;
	}

}

