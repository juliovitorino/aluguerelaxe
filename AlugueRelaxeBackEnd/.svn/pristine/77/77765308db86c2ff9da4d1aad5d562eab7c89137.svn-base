package br.com.jcv.backend.plano;

import br.com.jcv.backend.chain.AbstractChain;
import br.com.jcv.backend.chain.Chain;
import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.datemanager.DateManagerBase;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ImovelBusinessImpl;
import br.com.jcv.backend.imovel.ImovelDTO;
import br.com.jcv.backend.imovel.publicidade.PublicidadeDTO;
import br.com.jcv.backend.imovel.publicidade.PublicidadeImovelDTO;
import br.com.jcv.backend.interfaces.business.ImovelBusiness;

public class FuraFilaPlanoRecursos extends AbstractChain implements
		Chain<PublicidadeImovelDTO> {

	public void execute(PublicidadeImovelDTO context) {
		if(context.plano.recurso[PlanoRecursos.RECURSO_FURA_FILA].substring(0, 1).equals(Constantes.SIM)){
			// Obtem a quantidade do recurso
			int qtd = Integer.valueOf(context.plano.recurso[PlanoRecursos.RECURSO_FURA_FILA].substring(1, 4));
			String und = context.plano.recurso[PlanoRecursos.RECURSO_FURA_FILA].substring(4, 5);

			if(und.equals("D")){
				DateManagerBase amanha = DateManagerBase.getDateManagerInstance();
				amanha.add(1);
				
				DateManagerBase fim = DateManagerBase.getDateManagerInstance();
				fim.add(qtd);
				
				// complementa o contexto com as informacoes da nova publicidade
				context.publicidade = new PublicidadeDTO();
				context.publicidade.tipoPublicidade = "FF";
				context.publicidade.dataInicio = amanha.getDate();
				context.publicidade.dataFim = fim.getDate();
				
				ImovelBusiness<ImovelDTO> ib = new ImovelBusinessImpl();
				try {
					PublicidadeImovelDTO pubCriado = ib.criarPublicidade(this.getContextDAO(), context, context.plano.id, true);
					ib.pagarFatura(this.getContextDAO(), pubCriado.fatura.id);
					ib.liberarPublicidade(this.getContextDAO(), pubCriado.fatura.id);
				} catch (AlugueRelaxeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if(und.equals("X")){
				
				DateManagerBase amanha = DateManagerBase.getDateManagerInstance();
				amanha.add(1);
				
				DateManagerBase fim = DateManagerBase.getDateManagerInstance(context.fatura.dataVencimento);
				
				// complementa o contexto com as informacoes da nova publicidade
				context.publicidade = new PublicidadeDTO();
				context.publicidade.tipoPublicidade = "FF";
				context.publicidade.dataInicio = amanha.getDate();
				context.publicidade.dataFim = fim.getDate();
				
				ImovelBusiness<ImovelDTO> ib = new ImovelBusinessImpl();
				try {
					PublicidadeImovelDTO pubCriado = ib.criarPublicidade(this.getContextDAO(), context, context.plano.id, true);
					ib.pagarFatura(this.getContextDAO(), pubCriado.fatura.id);
					ib.liberarPublicidade(this.getContextDAO(), pubCriado.fatura.id);
				} catch (AlugueRelaxeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}		
	}

}
