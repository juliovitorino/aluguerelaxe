package br.com.jcv.backend.interfaces.services;

import java.util.List;

import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.exception.AlugueRelaxeException;

/**
 * <h1>DepoimentoApplicationService</h1> 
 * <p>Herança da interface <code>ApplicationService</code> implementação de 
 * métodos de serviço para Depoimento.
 * </p>
 * @author Júlio Vitorino
 * @version 1.10
 * @since 07 May 2009
 */
public interface DepoimentoService<DTO> extends ApplicationService<DTO> {
	DTO getProximoDepoimento(Long id) throws AlugueRelaxeException;
	DTO getPrevDepoimento(Long id) throws AlugueRelaxeException;
	List<DTO> listarRegistros(int start) throws AlugueRelaxeException;
	DTO adicionarDepoimento(DTO dto) throws AlugueRelaxeException;
	DTOPadrao liberarDepoimento(String id, String acao) throws AlugueRelaxeException;
	
	/**
	 * retorna no backend services uma pagina com um certo numero
	 * definido por uma variavel configurada
	 */ 
	List<DTO> ListarPaginaDepoimentos() throws AlugueRelaxeException;
}
