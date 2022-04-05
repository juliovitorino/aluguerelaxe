package br.com.jcv.backend.interfacesdao;

import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;

public interface ImovelImagemVideoDAO<DTO> extends DAO<DTO> {
	
	List<DTO> buscarListaImagens(long codigoImovel, String tipoImagem) throws AlugueRelaxeException; 
	void deleteImagensMITB(long codigoImovel, String radical) throws AlugueRelaxeException; 
	DTO loadVideoImovel(long codigoImovel) throws AlugueRelaxeException;
}
