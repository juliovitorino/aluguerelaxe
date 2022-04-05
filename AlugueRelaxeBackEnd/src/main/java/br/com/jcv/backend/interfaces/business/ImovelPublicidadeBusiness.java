package br.com.jcv.backend.interfaces.business;

import java.util.Date;
import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.imovel.publicidade.PublicidadeImovelDTO;

/**
 * <h1>ImovelPublicidadeBusiness</h1>
 * <p>Interface responsável por contratos que a Implementação de 
 * negócio de Caracterí­sticas deve realizar.
 * </p>
 * @author julio
 * @version 1.0
 */
public interface ImovelPublicidadeBusiness<DTO> extends BusinessObject<DTO> {
	List<? extends DTO> listImovelPublicidadePaginaPrincipal(DAOFactory daofactory) throws AlugueRelaxeException;
	List<? extends DTO> listImovelPublicidadePaginaDestaque(DAOFactory daofactory) throws AlugueRelaxeException;
	List<? extends DTO> listImovelSuperBanner(DAOFactory daofactory) throws AlugueRelaxeException;
	List<PublicidadeImovelDTO> listPublicidadeDentroPrazo(DAOFactory daofactory, String tipo) throws AlugueRelaxeException;
	int contarGradePublicidade(DAOFactory daofactory, Date d, String tipo) throws AlugueRelaxeException;

}
