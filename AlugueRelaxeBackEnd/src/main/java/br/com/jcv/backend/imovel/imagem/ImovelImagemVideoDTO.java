package br.com.jcv.backend.imovel.imagem;

import java.io.Serializable;
import java.sql.Timestamp;

import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.imovel.ImovelDTO;

/**
 * <h1>ImovelImagemVideo</h1>
 * <p>
 * Classe de transferencia de dados
 * </p>
 * 
 * @author julio
 * 
 */
public class ImovelImagemVideoDTO extends DTOPadrao implements Serializable {

	private static final long serialVersionUID = 3231918093940947557L;

	public long id;
	public String nome;
	public String tipo;
	public ImovelDTO imovel;
	public String radical;
	public long ordem;
	public String hash;
	public Timestamp dataCadastro;

}
