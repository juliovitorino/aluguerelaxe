package br.com.jcv.aluguerelaxe.client.administracao.console.reserva;

import java.util.Date;

import br.com.jcv.aluguerelaxe.client.imovel.ficha.ContatoClienteVO;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ReservaVO extends VOPadrao implements IsSerializable {
	public long id;
	public LocatarioVO locatario;
	public Date dataCheckin;
	public Date dataCheckout;
	public String dataCheckinStr;
	public String dataCheckoutStr;
	public ImovelFichaCompletaVO ifcdto;
	public Date dataPrevistaDeposito;
	public double valorPrevistoDeposito;
	public Date dataCadastro;
	public String token;
	public String chaveLiberacaoDeposito;
	public Date dataEmailLiberacao;
	public Date dataValidacaoPreReserva;
	public Date dataRealDeposito;
	public double valorRealDeposito;
	public String urlContratoLocador;
	public String urlContratoLocatario;
	public String chaveLiberacaoCheck;
	public Date dataChaveLiberacaoCheck;
	public Date dataTranferenciaDeposito;
	public double valorTransferenciaDeposito;
	public String chaveTracker;
	public double valorAluguelNegociado;
	public String formaPagamento;
	public Date dataReprovacao;
	public double valorCaucao;
	public double valorTotalPagar;
	public double percentualComissao;
	public String avaliacao;
	public Date dataAvaliacao;
	public String indicadorAvaliacao;
	public double valorTaxaServico;
	public String htmlFormPagtoPagseguro;
	public String idContato;
	public ContatoClienteVO contatoCliente;
	public String imgVisitante;
	public int notaImovel;
	public int notaAnfitriao;

}
