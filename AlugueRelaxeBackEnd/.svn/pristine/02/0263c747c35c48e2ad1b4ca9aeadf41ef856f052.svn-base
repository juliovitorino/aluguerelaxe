package br.com.jcv.backend.interfacesdao;

import java.util.Date;
import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.publicidade.PublicidadeImovelDTO;

public interface ImovelPublicidadeDAO<DTO> extends DAO<DTO> {
	
	List<DTO> listImovelPublicidadePaginaPrincipal() throws AlugueRelaxeException;
	List<DTO> listImovelPublicidadePaginaDestaque() throws AlugueRelaxeException;
	List<DTO> listImovelSuperBanner() throws AlugueRelaxeException;
	List<PublicidadeImovelDTO> listPublicidadeDentroPrazo(String tipo) throws AlugueRelaxeException;
	int countGradePublicidade(Date d, String tipo) throws AlugueRelaxeException; 

}
