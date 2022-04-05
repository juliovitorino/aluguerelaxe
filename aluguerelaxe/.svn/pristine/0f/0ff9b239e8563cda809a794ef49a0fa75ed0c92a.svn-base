package br.com.jcv.aluguerelaxe.client.administracao.console.imovelwizard;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.administracao.console.imovelpropriedade.CaracteristicaFormComposite;
import br.com.jcv.aluguerelaxe.client.administracao.console.publicidades.RealizarPagtoFormComposite;
import br.com.jcv.aluguerelaxe.client.administracao.login.SessaoVO;
import br.com.jcv.aluguerelaxe.client.componente.form.EnderecoFormComposite;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.notificacao.AreaNotificacao;
import br.com.jcv.aluguerelaxe.client.componente.wizard.AbstractWizard;
import br.com.jcv.aluguerelaxe.client.componente.wizard.WizardListener;
import br.com.jcv.aluguerelaxe.client.imovel.ImovelPlanoVO;
import br.com.jcv.aluguerelaxe.client.imovel.ImovelVO;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.FichaImovelRPCAsync;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.PublicidadeImovelVO;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

/**
 *<h2>AssistenteNovoImovel</h2>
 *<p>Classe concreta para cria\u00e7\u00e4o de nova conta.
 *</p>
 * <h2>CSS Style Rules</h2>
 * <ul>
 * <li>.gwt-AbstractWizard {estilo primario}</li>
 * </ul>
 * @author Julio Vitorino
 */
 public class AssistenteNovoImovel 
 	extends AbstractWizard<FormComposite<?>, ImovelFichaCompletaVO > 
 		implements WizardListener, AsyncCallback<ImovelFichaCompletaVO>  {
	 
	private InstrucoesAssistenteNovoImovelFormComposite ianifc;
	private ImovelTituloFormComposite itfc;
	private ImovelDescritivoFormComposite idfc;
	private EnderecoFormComposite efc;
	private CaracteristicaFormComposite cfcim;
	private CaracteristicaFormComposite cfcco;
	private ImovelIndicadoresFormComposite iifc;
	private ImovelObservacaoFormComposite iofc;
	private ImovelQuantidadesFormComposite iqfc;
	private PlanoFormComposite pfc;
	private SessaoVO sesssaovo;
		
	public AssistenteNovoImovel() {
		super();
		this.setStylePrimaryName("gwt-AssistenteNovoImovel");
		addWizardListener(this);
		
		addWizard(ianifc, montaHeaderPasso("house.png", "Bem vindo ao Assistente de cria\u00e7\u00e3o"));
		addWizard(itfc, montaHeaderPasso("house.png", "T\u00edtulo do An\u00fancio"));
		addWizard(idfc, montaHeaderPasso("house.png", "Informa\u00e7\u00f5es Gerais"));
		addWizard(iqfc, montaHeaderPasso("house.png", "Quartos, Banheiros e Capacidade"));
		addWizard(efc, montaHeaderPasso("house.png", "Endere\u00e7o do Im\u00f3vel"));
		addWizard(cfcim, montaHeaderPasso("house.png", "Caracter\u00edsticas do Im\u00f3vel"));
		addWizard(cfcco, montaHeaderPasso("house.png", "Caracter\u00edsticas do Condom\u00ednio"));
		addWizard(iifc, montaHeaderPasso("house.png", "Indicadores"));
		addWizard(iofc, montaHeaderPasso("house.png", "Observa\u00e7\u00f5es Gerais"));
		addWizard(pfc, montaHeaderPasso("house.png", "Plano de Pagamento"));
		
		init();
	}

	@Override
	public ImovelFichaCompletaVO getVO() {
		// Obtem todos os VOs dos formularios
		ImovelVO imovelvoitfc = itfc.getVO();
		ImovelVO imovelvoidfc = idfc.getVO();
		ImovelVO imovelvoiifc = iifc.getVO();
		ImovelVO imovelvoiofc = iofc.getVO();
		ImovelVO imovelvoiqfc = iqfc.getVO();
		
		// Consolida os VOs acima do wizard em uma ImovelFichaCompletaVO
		ImovelFichaCompletaVO vo = new ImovelFichaCompletaVO();
		vo.imovel = new ImovelVO();
		vo.imovel.idCliente = this.sesssaovo.clientevo.id;
		vo.imovel.descricaoTituloAnuncio = imovelvoitfc.descricaoTituloAnuncio;
		vo.imovel.descricaoGeral = imovelvoidfc.descricaoGeral;
		vo.imovel.descricaoArredores = imovelvoidfc.descricaoArredores;
		vo.imovel.descricaoQuartos = imovelvoidfc.descricaoQuartos;
		vo.imovel.indicadorCondominio = imovelvoiifc.indicadorCondominio;
		vo.imovel.indicadorMostraTabelaPreco = imovelvoiifc.indicadorMostraTabelaPreco;
		vo.imovel.indicadorPermiteAlugarFestas = imovelvoiifc.indicadorPermiteAlugarFestas;
		vo.imovel.indicadorTipoPropriedade = imovelvoiifc.indicadorTipoPropriedade;
		vo.imovel.valorTarifaBasica = imovelvoiifc.valorTarifaBasica;
		vo.imovel.observacoes = imovelvoiofc.observacoes;
		vo.imovel.qtdeBanheiros = imovelvoiqfc.qtdeBanheiros;
		vo.imovel.qtdeCapacidade = imovelvoiqfc.qtdeCapacidade;
		vo.imovel.qtdeQuartos = imovelvoiqfc.qtdeQuartos;
		vo.imovel.qtdeSuites = imovelvoiqfc.qtdeSuites;
		vo.imovel.endereco = efc.getVO();
		vo.caracteristicaImovel = cfcim.getListImovel();
		vo.caracteristicaCondominio = cfcco.getListCondominio();
		vo.imovelPlano = pfc.getVO();
		
		return vo;
	}

	public void onVoltarClick() {
		// TODO Auto-generated method stub
		
	}

	public void onProximoClick() {
		// TODO Auto-generated method stub
		
	}

	public void onConcluirClick() {
		FichaImovelRPCAsync rpc = ServicosRPC.getFichaImovelRPC();
		AsyncCallback<ImovelFichaCompletaVO> callback = this;
		rpc.adicionarImovelCarteira(this.getVO(), callback);
	}

	public void onFailure(Throwable caught) {
		try {
			this.forcarVisibilidadeBotao(BOTAO_CONCLUIR, true);
			throw caught;
		} catch (IncompatibleRemoteServiceException e) {
			this.setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
		} catch (InvocationException e) {
			this.setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
		} catch (AlugueRelaxeFrontException e) {
			if ((e.getListaErros() != null) && (e.getListaErros().size() > 0)) {
				this.setMensagem(e.getListaErros(), AreaNotificacao.NOTIFICACAO_ERRO);
			} else {
				this.setMensagem(e.getMensagem(), AreaNotificacao.NOTIFICACAO_ERRO);
			}
		} catch (Throwable e) {
			this.setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
		}	
	}

	public void onSuccess(ImovelFichaCompletaVO result) {
		this.setMensagem(result.msgcodeString, AreaNotificacao.NOTIFICACAO_INFO);
		this.prepararRealizarPagamento(result);
	}
	
	private void prepararRealizarPagamento(ImovelFichaCompletaVO ifcvo) {
		FichaImovelRPCAsync rpc = ServicosRPC.getFichaImovelRPC();
		AsyncCallback<PublicidadeImovelVO> callback = new AsyncCallback<PublicidadeImovelVO>() {

			public void onFailure(Throwable caught) {
				try {
					AssistenteNovoImovel.this.forcarVisibilidadeBotao(BOTAO_CONCLUIR, true);
					throw caught;
				} catch (IncompatibleRemoteServiceException e) {
					AssistenteNovoImovel.this.setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
				} catch (InvocationException e) {
					AssistenteNovoImovel.this.setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
				} catch (AlugueRelaxeFrontException e) {
					if ((e.getListaErros() != null) && (e.getListaErros().size() > 0)) {
						AssistenteNovoImovel.this.setMensagem(e.getListaErros(), AreaNotificacao.NOTIFICACAO_ERRO);
					} else {
						AssistenteNovoImovel.this.setMensagem(e.getMensagem(), AreaNotificacao.NOTIFICACAO_ERRO);
					}
				} catch (Throwable e) {
					AssistenteNovoImovel.this.setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
				}	
			}

			public void onSuccess(PublicidadeImovelVO result) {
				AssistenteNovoImovel.this.realizarPagamento(result);
			}
						
		};
		
		rpc.prepararRealizarPagamento(ifcvo.imovel.id, callback);
		
	}
	
	

	private void realizarPagamento(PublicidadeImovelVO pivo) {
		if (pivo.plano.id == 1) {
			ContribuicaoPagtoFormComposite rpfc = new ContribuicaoPagtoFormComposite();
			this.showCompositeFinal(rpfc, this.montaHeaderPasso("cashier.png", "Realizar Donativo"));
			rpfc.update(pivo);
			
		} else {
			RealizarPagtoFormComposite rpfc = new RealizarPagtoFormComposite();
			this.showCompositeFinal(rpfc, this.montaHeaderPasso("cashier.png", "Realizar Pagamento"));
			rpfc.update(pivo);
		}
	}
	
	
	public void setSessao(SessaoVO vo) {
		this.sesssaovo = vo;
	}

	@Override
	public void initComposites() {
		ianifc = new InstrucoesAssistenteNovoImovelFormComposite();
		itfc = new ImovelTituloFormComposite();
		idfc = new ImovelDescritivoFormComposite();
		efc = new EnderecoFormComposite();
		cfcim = new CaracteristicaFormComposite();
		cfcco = new CaracteristicaFormComposite();
		iifc = new ImovelIndicadoresFormComposite();
		iofc = new ImovelObservacaoFormComposite();
		iqfc = new ImovelQuantidadesFormComposite();
		pfc = new PlanoFormComposite();
	}
}
