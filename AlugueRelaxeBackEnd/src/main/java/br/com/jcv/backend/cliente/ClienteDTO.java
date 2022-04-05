package br.com.jcv.backend.cliente;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import br.com.jcv.backend.anotacoes.Obrigatorio;
import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.dto.EnderecoDTO;
import br.com.jcv.backend.interfaces.business.ClienteBusiness;
import br.com.jcv.backend.modopublicidade.ModoPublicidadeDTO;

public class ClienteDTO extends DTOPadrao implements Serializable {

	private static final long serialVersionUID = 8745328341248299715L;

	public long id;
	
	public String idhash;

	@Obrigatorio(tamanho=40, notNull=true) 
	public String nome;
	
	@Obrigatorio(tamanho=11) 
	public String cpf;
	
	@Obrigatorio(tamanho=18) 
	public String cgc;
	
	public Date dataNascimento;
	
	@Obrigatorio(tamanho=100, notNull=true) 
	public String email;
	
	public String senha;
	
	public String codigoAcesso;
	
	public java.sql.Timestamp dataCadastro;
	
	public java.sql.Timestamp dataAtualizacao;
	
	@Obrigatorio(tamanho=1, notNull=true,
			dominio={ClienteBusinessImpl.CONTA_STATUS_ATIVA,
			ClienteBusiness.CONTA_STATUS_BLOQUEADA,
			ClienteBusiness.CONTA_STATUS_INATIVA,
			ClienteBusiness.CONTA_STATUS_PENDENTE,
			ClienteBusiness.CONTA_STATUS_REPROVADO,
			ClienteBusiness.CONTA_STATUS_VERIFICAR}
			)
	public String indicadorStatus;
	
	@Obrigatorio(tamanho=500)
	public String urlwww;
	
	@Obrigatorio(tamanho=1, notNull=true, dominio={Constantes.SIM, Constantes.NAO})
	public String indicadorAutorizaNotificacao;
	
	@Obrigatorio(tamanho=100)
	public String msn;
	
	@Obrigatorio(tamanho=100)
	public String skype;
	
	@Obrigatorio(tamanho=100)
	public String googleTalk;
	
	@Obrigatorio(tamanho=1, notNull=true, dominio={
			ClienteBusinessImpl.TIPO_ANUNCIANTE_PROPRIETARIO,
			ClienteBusinessImpl.TIPO_ANUNCIANTE_IMOBILIARIA,
			ClienteBusinessImpl.TIPO_ANUNCIANTE_PATROCINADOR,
			ClienteBusinessImpl.TIPO_ANUNCIANTE_T
	})
	public String indicadorTipoAnunciante;
	
	@Obrigatorio(tamanho=40)
	public String codigoHashAtivacao;
	
	public EnderecoDTO endereco;
	public List<TelefoneDTO> telefones;
	
	public String banco;
	public String agencia;
	public String contacorrente;
	
	public ModoPublicidadeDTO modoPublicidade;
	
	public String fotoPerfil;
	public String fotoChat;
	
	public String primeiroNome;
	public String pathPerfil;
	
	public String indicadorVerificado;
	public int taxaResposta;
	public double totalPergunta;
	public double totalResposta;
	
}
