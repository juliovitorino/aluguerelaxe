package br.com.jcv.aluguerelaxe.server;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.logodinamico.LogoDinamicoRPC;
import br.com.jcv.aluguerelaxe.client.logodinamico.LogoDinamicoVO;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.variavel.VariavelCache;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class LogoDinamicoRPCImpl extends RemoteServiceServlet implements LogoDinamicoRPC {
	
	private static final long serialVersionUID = -5728044501063862237L;
	private static final String LOGO_ATIVO = "LOGO_ATIVO";

	public LogoDinamicoVO getLogoDinamico() throws AlugueRelaxeFrontException {
		
		LogoDinamicoVO ldvo = null;
		
		// Obtem o ponteiro da variavel para o logo ativo
		try {
			String logoAtivo = VariavelCache.getInstance().getValor(LOGO_ATIVO);
			
			// Obtem o nome do arquivo com base no ponteiro obtido
			ldvo = new LogoDinamicoVO();
			ldvo.pathCompleto = VariavelCache.getInstance().getValor(logoAtivo);
			ldvo.hint = ".";
		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return ldvo;
	}



}
