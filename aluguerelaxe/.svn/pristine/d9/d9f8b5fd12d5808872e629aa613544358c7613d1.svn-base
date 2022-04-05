package br.com.jcv.aluguerelaxe.client.administracao.console.desktop;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.cliente.ClienteRPCAsync;
import br.com.jcv.aluguerelaxe.client.cliente.ClienteVO;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ContatoClienteVO;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 *<h2>DesktopContatoAnuncianteFormComposite</h2>
 *<p>FormComposite para apresentacao dos contatos de clientes para o anunciante
 *</p>
 * <h2>CSS Style Rules</h2>
 * <ul>
 * <li>.gwt-DesktopContatoAnuncianteFormComposite {estilo primario}</li>
 * </ul>
 * @author Julio Vitorino
 */
public class DesktopContatoAnuncianteFormComposite extends FormComposite<ClienteVO> {

	private ContatoAnuncianteGridDataEntry cagde;
	private HTML htmlmsg;
	
	public DesktopContatoAnuncianteFormComposite() {
		super();
		//this.setStylePrimaryName("gwt-DesktopContatoAnuncianteFormComposite");
	}

	@Override
	public ClienteVO getVO() {
		// Nao Aplicada neste contexto
		return null;
	}

	@Override
	public void update(ClienteVO vo) {
		// Chama RPC para buscar todos os contatos de clientes para o anunciante
		// e atualizar a ContatoAnuncianteGridDataEntry
		ClienteRPCAsync rpc = ServicosRPC.getClienteRPC();
		AsyncCallback<List<ContatoClienteVO>> callback = new AsyncCallback<List<ContatoClienteVO>>() {

			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			public void onSuccess(List<ContatoClienteVO> result) {
				if (result != null){
					DesktopContatoAnuncianteFormComposite.this.cagde.update(result);
				} else {
					DesktopContatoAnuncianteFormComposite.this.cagde.setVisible(false);
					DesktopContatoAnuncianteFormComposite.this.htmlmsg.setVisible(true);
				}
			}
		};
		rpc.listarContatosCliente(vo, callback);
	}

	@Override
	public Widget render() {
		VerticalPanel vp = new VerticalPanel();
		vp.add(this.htmlmsg);
		vp.add(this.cagde);
		return vp;
	}

	@Override
	public void init() {
		cagde = new ContatoAnuncianteGridDataEntry();
		htmlmsg = new HTML("Nenhum contato registrado.");
		htmlmsg.setVisible(false);
	}

	@Override
	public void clear() {
		// Nao Aplicada neste contexto
	}

	@Override
	public void notifier(ClienteVO vo) {
		// TODO Auto-generated method stub
		
	}

}
