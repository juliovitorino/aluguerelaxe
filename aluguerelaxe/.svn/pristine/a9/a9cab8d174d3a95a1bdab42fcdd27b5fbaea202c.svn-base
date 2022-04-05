package br.com.jcv.aluguerelaxe.client.destaques;

import java.util.ArrayList;
import java.util.List;

import br.com.jcv.aluguerelaxe.client.DateParser;
import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.publicidade.ImovelPublicidadeVO;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * <h1>Destaques</h1>
 * <p>Classe responsável por gerenciar o painel de outros destaques de publicidades
 * na primeira página do site.
 * </p>
 * @author julio
 * @version 1.0
 */
public class Destaques extends Timer implements EntryPoint {
	
	public static final String GWT_DIV_FEATURED = "gwt-div-featured";

	/**
	 * <h2>lstPublicidades</h2>
	 * <p>lista de publicidades da pagina de destaques</p>
	 */
	private List<HTML> lstPublicidades = new ArrayList<HTML>();
	
	/**
	 * <h2>htmlDestaque</h2>
	 * <p>Objeto HTML para substituição da lista de destaque</p>
	 */
	private HTML htmlDestaque = new HTML();
	
	private int contador = 0;

	private static boolean ativasch = false;

	/**
	 * </h2>onModuleLoad</h2>
	 * <p>Método invocado automaticamente pelo GWT.
	 * </p>
	 */
	public void onModuleLoad() {
		// TODO Auto-generated method stub

		RootPanel.get(GWT_DIV_FEATURED).add(htmlDestaque);

		update();
	}

	private void update() {
		//==================================================================
		// Realiza a chamada ao serviço remoto para obter as publicidades
		//==================================================================
		DestaquesRPCAsync rpc = ServicosRPC.getDestaquesRPC();
		AsyncCallback<List<ImovelPublicidadeVO>> callback = new AsyncCallback<List<ImovelPublicidadeVO>>(){

			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			public void onSuccess(List<ImovelPublicidadeVO> result) {
				if (result.size() > 0) {
					//==================================================================
					// Alimenta a lista com cada propaganda do painel destaque
					//==================================================================
					for (ImovelPublicidadeVO vo : result) {
						StringBuilder sb = new StringBuilder();
						HTML htmlpublicidade = new HTML(); 
						sb.append("<li>");
						sb.append("<div>");
						sb.append("<a href='/arweb/JXControllerSmartInterface?cmd=dtgFichaImovel&id=" + vo.id + "&o=PD' rel='bookmark' title='Clique na foto para maiores detalhes'>");
						sb.append("<img src='"+vo.nomeImagemVideo+"' class='left' width='100px' height='65px'/></a>");
						sb.append("<div class='info'><a href='/arweb/JXControllerSmartInterface?cmd=dtgFichaImovel&id=" + vo.id + "&o=PD' rel='bookmark' class='title'>Local: " + vo.descricaoUF + "</a>");
						sb.append("<div class='meta'>Desde: " + DateParser.formatador(vo.dataCadastro) + " | " + vo.qtdeVisitas + " visitas</div>");
						sb.append("</div>");
						sb.append("</div>");
						sb.append("</li>");
						htmlpublicidade.setHTML(sb.toString());
						lstPublicidades.add(htmlpublicidade);
					}
					//==================================================================
					// Define o temporizador
					//==================================================================
					Destaques.this.schedule(1000);
					

				}
			}
		};
		rpc.getPainelDestaques(callback);
	}

	/**
	 * <h2>run</h2>
	 * <p>sobrecarga do método para realizar a montagem dos blocos
	 * de propaganda de destaque.</p>
	 */
	@Override
	public void run() {
		if (lstPublicidades.size() > 0) {
			StringBuilder sb = new StringBuilder();
			sb.append("<div id='featured'>");    
			sb.append("<div class='label'><a href='#'>Outros Destaques &raquo;</a></div>");
			sb.append("<div class='arthemia-carousel'>");
			sb.append("<ul>");
			for (int i=0; i < 3; i++){
				sb.append(((HTML) lstPublicidades.get(contador)).toString());
				if ( ++contador == lstPublicidades.size()) {
					contador = 0; 
					break;
				}
			}
			sb.append("</ul>");
			sb.append("</div>");
			sb.append("</div>");
			
			//sb.append("<div style='text-align:center;'>");
			//sb.append("<img class='prev' src='images/prev.png' style='cursor:pointer;width:17px;height:10px;margin-right:10px;'/>");
			//sb.append("<img class='next' src='images/next.png' style='cursor:pointer;width:17px;height:10px;'/>");    
			//sb.append("</div>");
			htmlDestaque.setHTML(sb.toString());
		}
		if (! ativasch ) {
			this.scheduleRepeating(12000);
			ativasch = true;
		}
	}
}
