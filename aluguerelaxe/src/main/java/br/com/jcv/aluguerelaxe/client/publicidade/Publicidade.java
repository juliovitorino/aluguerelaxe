package br.com.jcv.aluguerelaxe.client.publicidade;

import java.util.ArrayList;
import java.util.List;

import br.com.jcv.aluguerelaxe.client.DateParser;
import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.imovel.listagem.uf.ListaImovelUF;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * <h1>Publicidade</h1>
 * <p>Classe responsável por gerenciar o painel de publicidades
 * na primeira página do site.
 * </p>
 * @author julio
 * @version 1.0
 */
public class Publicidade extends Timer implements EntryPoint {

	/**
	 * <h2>lstPublicidades</h2>
	 * <p>lista de publicidades de primeira pagina</p>
	 */
	private List<HTML> lstPublicidades = new ArrayList<HTML>();

	/**
	 * <h2>publicidadePrimeiraPagina</h2>
	 * <p>Objeto HTML para substituição dos itens da lista</p>
	 */
	private HTML publicidadePrimeiraPagina = new HTML();

	private int contador = 0; 
	private static boolean ativasch = false; 
	
	public static final String ID_HTML = "gwt-publicidade";
	
	/**
	 * <h2>onModuleLoad</h2>
	 * <p>Carregador principal do GWT para montar o painel de publicidade
	 * na pÃ¡gina principal.</p>
	 */
	public void onModuleLoad() {
		//==================================================================
		// Adiciona o painel na tag localizada no html
		//==================================================================
		RootPanel.get(ID_HTML).add(publicidadePrimeiraPagina);

		update();
	}
 
	private void update() {
		//==================================================================
		// Realiza a chamada ao serviÃ§o remoto para obter as publicidades
		//==================================================================
		PublicidadeRPCAsync rpc = ServicosRPC.getPublicidadeRPC();
		AsyncCallback<List<ImovelPublicidadeVO>> callback = new AsyncCallback<List<ImovelPublicidadeVO>>(){
			public void onSuccess(List<ImovelPublicidadeVO> result) {
				if (result.size() > 0) {
					//==================================================================
					// Alimenta cada tab da tabPanel com sua publicidade
					//==================================================================
					for (ImovelPublicidadeVO vo : result) {
						
						StringBuilder sb = new StringBuilder();
						HTML htmlPublicidade = new HTML();
						sb.append("<div id='headline'>");
						sb.append("<div class='label'>");
						sb.append("Publicidade");
						sb.append("</div>");
						sb.append("<div class='clearfloat'>");
						sb.append("<a href='/arweb/JXControllerSmartInterface?cmd=dtgFichaImovel&id="+vo.id+"&o=PP' rel='bookmark' title='Clique na imagem para maiores detalhes.'>");
						
						if (vo.imagemPreferida != null){
							sb.append("<img src='" + vo.imagemPreferida.nome + "'");
						} else {
							sb.append("<img src='" + vo.nomeImagemVideo + "'");
						}
						sb.append("alt='Incrí­vel imóvel !' class='left'  width='300px' height='225px'/></a>");		
						sb.append("<div class='title'>"); 
						sb.append("<a href='/arweb/JXControllerSmartInterface?cmd=dtgListaImovelUf&atvd="+ ListaImovelUF.COMANDO_LISTA_PAGINADA_UF + "&cidade=-1&uf="+vo.endereco.uf+"' rel='bookmark' title='Veja outros im\u00f3veis neste Estado.'>" + vo.descricaoUF + "</a>");
						sb.append("</div>");
						sb.append("<div class='meta'><a href='/arweb/JXControllerSmartInterface?cmd=dtgFichaImovel&id=" + vo.id + "&o=PP' rel='bookmark' class='title'>Local: " + vo.endereco.cidade + " - " + vo.endereco.uf + "</a></div>");
						sb.append("<div class='meta'>Cliente desde " + DateParser.formatador(vo.dataCadastro)); 
						sb.append("<br/>Este im\u00f3vel j\u00e1 teve " + vo.qtdeVisitas + " visitas</div>");
						if (vo.descricaoGeral.length() > 200) {
							sb.append("<p>" + vo.descricaoGeral.substring(0, 200) + "...</p>");
						} else {
							sb.append("<p>" + vo.descricaoGeral + "</p>");
						}
						sb.append("<a href='/arweb/JXControllerSmartInterface?cmd=dtgFichaImovel&id="+vo.id+"&o=PP' rel='bookmark' title='Veja maiores detalhes do im\u00f3vel.'>Clique aqui para maiores detalhes&nbsp;&raquo;</a>");
						sb.append("</div>");
						sb.append("</div>");
						htmlPublicidade.setHTML(sb.toString());
						lstPublicidades.add(htmlPublicidade);
					}
					//==================================================================
					// Define o temporizador
					//==================================================================
					Publicidade.this.schedule(1000);
				}
			}
			public void onFailure(Throwable caught) {
				// TODO fazer tratamento dos retornos de falhas
			}
		};
		rpc.getPainelPublicidade(callback);
	}

	/**
	 * <h2>run</h2>
	 * <p>Método para controlar a modificação entre as tabs do 
	 * painel de publicidade.</p>
	 */
	@Override
	public void run() {
		if (lstPublicidades.size() > 0) {
			publicidadePrimeiraPagina.setHTML(((HTML) lstPublicidades.get(contador)).getHTML());
			if ( ++contador == lstPublicidades.size()) {
				contador = 0;
			}
		}
		if (! ativasch) {
			this.scheduleRepeating(12000);
			ativasch = true;
		}
	}
	
}
