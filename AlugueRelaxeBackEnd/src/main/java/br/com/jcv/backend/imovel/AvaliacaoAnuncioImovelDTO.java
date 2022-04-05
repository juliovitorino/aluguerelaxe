package br.com.jcv.backend.imovel;

import java.io.Serializable;

import br.com.jcv.backend.dto.ClassificadorDTO;

public class AvaliacaoAnuncioImovelDTO implements Serializable {
	private static final long serialVersionUID = -174461685100750323L;
	public long idImovelAvaliado;
	public ClassificadorDTO classFotografia;
	public ClassificadorDTO classQualidadeTexto;
	public ClassificadorDTO classInformacaoRelevante;
}