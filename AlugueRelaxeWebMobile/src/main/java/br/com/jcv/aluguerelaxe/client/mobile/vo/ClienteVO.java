package br.com.jcv.aluguerelaxe.client.mobile.vo;

import java.util.Date;
import java.util.List;

import br.com.jcv.ui.client.vo.VOPadrao;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ClienteVO extends VOPadrao implements IsSerializable {
	public long id;
	public String nome;
	public String cpf;
	public String cgc;
	public Date dataNascimento;
	public String email;
	public String senha;
	public Date dataCadastro;
	public Date dataAtualizacao;
	public String indicadorStatus;
	public String urlwww;
	public String indicadorAutorizaNotificacao;
	public String msn;
	public String skype;
	public String googleTalk;
	public String indicadorTipoAnunciante;
	public EnderecoVO endereco;
	public List<TelefoneVO> telefones;
	public DadosBancariosVO db;
	public ModoPublicidadeVO modoPublicidade;
	public String fotoPerfil;
	public String fotoChat;
	public String primeiroNome;
	public String pathPerfil;
	public String indicadorVerificado;
	public int taxaResposta;
	public double totalPergunta;
	public double totalResposta;
	public String membroDesde;


}
