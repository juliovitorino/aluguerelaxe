package br.com.jcv.aluguerelaxe.client.publicidade;

import java.util.Date;

import br.com.jcv.aluguerelaxe.client.imovel.ImovelBaseVO;
import br.com.jcv.aluguerelaxe.client.imovel.imagem.ImovelImagemVideoVO;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * <h1>ImovelPublicidadeVO</h1> 
 * <p>Classe de transferência de dados</p>
 *
 * @author Júlio Vitorino
 * @version 1.10
 * @since 20 Nov 2009
 */
public class ImovelPublicidadeVO extends ImovelBaseVO implements IsSerializable {

	/**
	 * <h2>ImovelPublicidadeVO</h2>
	 * <p>Construtor padrÃ£o</p>
	 */
	public ImovelPublicidadeVO() {}
	
	/**
	 * <h2>idPublicidade</h2>
	 * <p>Identificador unico de publicidade do imovel.</p>
	 */
	public long idPublicidade;
	/**
	 * <h2>dataInicio</h2>
	 * <p>Data de inicio da vigencia da publicidade</p>
	 */
	public Date dataInicio;
	/**
	 * <h2>dataFim</h2>
	 * <p>Data de fim da vigencia da publicidade</p>
	 */
	public Date dataFim;
	/**
	 * <h2>tipoPublicidade</h2>
	 * <p>Tipo da publicidade</p>
	 */
	public Date tipoPublicidade;
	/**
	 * <h2>nomeImagemVideo</h2>
	 * <p>Nome da imagem ou video. Este nome Ã© o nome do arquivo que estarÃ¡ armazenado
	 * no provedor</p>
	 */
	public String nomeImagemVideo;
	/**
	 * <h2>descricaoUF</h2>
	 * <p>Nome da UF de localizacao do imovel em publicidade</p>
	 */
	public String descricaoUF;
	/**
	 * <h2>imagemPreferida</h2>
	 * <p>VO da imagem preferida de publicidade</p>
	 */
	public ImovelImagemVideoVO imagemPreferida;

}
