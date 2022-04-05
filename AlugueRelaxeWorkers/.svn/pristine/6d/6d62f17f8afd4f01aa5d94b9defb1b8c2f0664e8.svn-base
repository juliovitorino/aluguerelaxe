package br.com.jcv.backend.workers.interfaces;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.faturamento.ImovelPlanoFaturaDTO;
import br.com.jcv.backend.imovel.publicidade.PublicidadeImovelDTO;

public interface IOfertaPublicidade {
	void execute(PublicidadeImovelDTO pidto, String tipo) throws AlugueRelaxeException;
	void notificar(PublicidadeImovelDTO pidto, ImovelPlanoFaturaDTO ipfdto) throws AlugueRelaxeException;
}
