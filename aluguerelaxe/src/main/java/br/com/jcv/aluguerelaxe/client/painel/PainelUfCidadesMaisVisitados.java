package br.com.jcv.aluguerelaxe.client.painel;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.imovel.listagem.uf.ListaImovelUF;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class PainelUfCidadesMaisVisitados implements EntryPoint {
	private static final String SMART_LINK_CIDADE_DEFAULT = "/arweb/JXControllerSmartInterface?cmd=dtgListaImovelUf&atvd="+ ListaImovelUF.COMANDO_LISTA_PAGINADA_CIDADE + "&uf=XX&cidade=";
	private static final String SMART_LINK_UF_DEFAULT = "/arweb/JXControllerSmartInterface?cmd=dtgListaImovelUf&atvd="+ ListaImovelUF.COMANDO_LISTA_PAGINADA_UF + "&cidade=-1&uf=";

	private static final String GWT_LABEL_UF0 = "gwt-label-mv-uf0";
	private static final String GWT_LABEL_UF1 = "gwt-label-mv-uf1";
	private static final String GWT_LABEL_UF2 = "gwt-label-mv-uf2";
	private static final String GWT_LABEL_UF3 = "gwt-label-mv-uf3";
	
	private static final String GWT_LABEL_QTDE_UF0 = "gwt-label-mv-qtde-uf0";
	private static final String GWT_LABEL_QTDE_UF1 = "gwt-label-mv-qtde-uf1";
	private static final String GWT_LABEL_QTDE_UF2 = "gwt-label-mv-qtde-uf2";
	private static final String GWT_LABEL_QTDE_UF3 = "gwt-label-mv-qtde-uf3";
	
	private static final String GWT_LABEL_CIDADE0 = "gwt-label-mv-uf0-cidade0";
	private static final String GWT_LABEL_CIDADE1 = "gwt-label-mv-uf0-cidade1";
	private static final String GWT_LABEL_CIDADE2 = "gwt-label-mv-uf0-cidade2";
	private static final String GWT_LABEL_CIDADE3 = "gwt-label-mv-uf0-cidade3";
	private static final String GWT_LABEL_CIDADE4 = "gwt-label-mv-uf0-cidade4";
	private static final String GWT_LABEL_CIDADE5 = "gwt-label-mv-uf0-cidade5";

	private static final String GWT_LABEL_CIDADE6 = "gwt-label-mv-uf1-cidade0";
	private static final String GWT_LABEL_CIDADE7 = "gwt-label-mv-uf1-cidade1";
	private static final String GWT_LABEL_CIDADE8 = "gwt-label-mv-uf1-cidade2";
	private static final String GWT_LABEL_CIDADE9 = "gwt-label-mv-uf1-cidade3";
	private static final String GWT_LABEL_CIDADE10 = "gwt-label-mv-uf1-cidade4";
	private static final String GWT_LABEL_CIDADE11 = "gwt-label-mv-uf1-cidade5";

	private static final String GWT_LABEL_CIDADE12 = "gwt-label-mv-uf2-cidade0";
	private static final String GWT_LABEL_CIDADE13 = "gwt-label-mv-uf2-cidade1";
	private static final String GWT_LABEL_CIDADE14 = "gwt-label-mv-uf2-cidade2";
	private static final String GWT_LABEL_CIDADE15 = "gwt-label-mv-uf2-cidade3";
	private static final String GWT_LABEL_CIDADE16 = "gwt-label-mv-uf2-cidade4";
	private static final String GWT_LABEL_CIDADE17 = "gwt-label-mv-uf2-cidade5";

	private static final String GWT_LABEL_CIDADE18 = "gwt-label-mv-uf3-cidade0";
	private static final String GWT_LABEL_CIDADE19 = "gwt-label-mv-uf3-cidade1";
	private static final String GWT_LABEL_CIDADE20 = "gwt-label-mv-uf3-cidade2";
	private static final String GWT_LABEL_CIDADE21 = "gwt-label-mv-uf3-cidade3";
	private static final String GWT_LABEL_CIDADE22 = "gwt-label-mv-uf3-cidade4";
	private static final String GWT_LABEL_CIDADE23 = "gwt-label-mv-uf3-cidade5";

	public void onModuleLoad() {
		final HTML lblUf0 = new HTML();
		//final Label lblQtdeUf0 = new Label();
		final HTML lblCidade0 = new HTML();
		final HTML lblCidade1 = new HTML();
		final HTML lblCidade2 = new HTML();
		final HTML lblCidade3 = new HTML();
		final HTML lblCidade4 = new HTML();
		final HTML lblCidade5 = new HTML();

		final HTML lblUf1 = new HTML();
		//final Label lblQtdeUf1 = new Label();
		final HTML lblCidade6 = new HTML();
		final HTML lblCidade7 = new HTML();
		final HTML lblCidade8 = new HTML();
		final HTML lblCidade9 = new HTML();
		final HTML lblCidade10 = new HTML();
		final HTML lblCidade11 = new HTML();

		final HTML lblUf2 = new HTML();
		//final Label lblQtdeUf2 = new Label();
		final HTML lblCidade12 = new HTML();
		final HTML lblCidade13 = new HTML();
		final HTML lblCidade14 = new HTML();
		final HTML lblCidade15 = new HTML();
		final HTML lblCidade16 = new HTML();
		final HTML lblCidade17 = new HTML();

		final HTML lblUf3 = new HTML();
		//final Label lblQtdeUf3 = new Label();
		final HTML lblCidade18 = new HTML();
		final HTML lblCidade19 = new HTML();
		final HTML lblCidade20 = new HTML();
		final HTML lblCidade21 = new HTML();
		final HTML lblCidade22 = new HTML();
		final HTML lblCidade23 = new HTML();

		RootPanel.get(GWT_LABEL_UF0).add(lblUf0);
		//RootPanel.get(GWT_LABEL_QTDE_UF0).add(lblQtdeUf0);
		RootPanel.get(GWT_LABEL_CIDADE0).add(lblCidade0);
		RootPanel.get(GWT_LABEL_CIDADE1).add(lblCidade1);
		RootPanel.get(GWT_LABEL_CIDADE2).add(lblCidade2);
		RootPanel.get(GWT_LABEL_CIDADE3).add(lblCidade3);
		RootPanel.get(GWT_LABEL_CIDADE4).add(lblCidade4);
		RootPanel.get(GWT_LABEL_CIDADE5).add(lblCidade5);

		RootPanel.get(GWT_LABEL_UF1).add(lblUf1);
		//RootPanel.get(GWT_LABEL_QTDE_UF1).add(lblQtdeUf1);
		RootPanel.get(GWT_LABEL_CIDADE6).add(lblCidade6);
		RootPanel.get(GWT_LABEL_CIDADE7).add(lblCidade7);
		RootPanel.get(GWT_LABEL_CIDADE8).add(lblCidade8);
		RootPanel.get(GWT_LABEL_CIDADE9).add(lblCidade9);
		RootPanel.get(GWT_LABEL_CIDADE10).add(lblCidade10);
		RootPanel.get(GWT_LABEL_CIDADE11).add(lblCidade11);

		RootPanel.get(GWT_LABEL_UF2).add(lblUf2);
		//RootPanel.get(GWT_LABEL_QTDE_UF2).add(lblQtdeUf2);
		RootPanel.get(GWT_LABEL_CIDADE12).add(lblCidade12);
		RootPanel.get(GWT_LABEL_CIDADE13).add(lblCidade13);
		RootPanel.get(GWT_LABEL_CIDADE14).add(lblCidade14);
		RootPanel.get(GWT_LABEL_CIDADE15).add(lblCidade15);
		RootPanel.get(GWT_LABEL_CIDADE16).add(lblCidade16);
		RootPanel.get(GWT_LABEL_CIDADE17).add(lblCidade17);

		RootPanel.get(GWT_LABEL_UF3).add(lblUf3);
		//RootPanel.get(GWT_LABEL_QTDE_UF3).add(lblQtdeUf3);
		RootPanel.get(GWT_LABEL_CIDADE18).add(lblCidade18);
		RootPanel.get(GWT_LABEL_CIDADE19).add(lblCidade19);
		RootPanel.get(GWT_LABEL_CIDADE20).add(lblCidade20);
		RootPanel.get(GWT_LABEL_CIDADE21).add(lblCidade21);
		RootPanel.get(GWT_LABEL_CIDADE22).add(lblCidade22);
		RootPanel.get(GWT_LABEL_CIDADE23).add(lblCidade23);
		
		PainelUfCidadesRPCAsync rpc = ServicosRPC.getPainelUfCidadesRPC();
		AsyncCallback<List<PainelUfCidadesVO>> callback = new AsyncCallback<List<PainelUfCidadesVO>>(){
			public void onSuccess(List<PainelUfCidadesVO> result) {
				if (result.size() > 0) {
					//------------------------------------------------------
					// Cidade 0 = Primeira coluna de UF e Cidades
					//------------------------------------------------------
					PainelUfCidadesVO vo0 = (PainelUfCidadesVO)result.get(0);
					lblUf0.setHTML("<a href=" + SMART_LINK_UF_DEFAULT + vo0.getId() + ">" + vo0.getDescricao() + "</a>");

					//lblQtdeUf0.setText(String.valueOf(vo0.getQuantidade()).concat(" Imóveis"));
					if (vo0.listaCidades != null) {
						//------------------------------------------------------
						// Obtem a lista de cidades - Somente sera apresentado
						// 6 cidades no painel
						//------------------------------------------------------
						
						for (int i = 0; i < vo0.listaCidades.size(); i++){
							if (i == 0) {
								PainelUfCidadesVO cidade0 = (PainelUfCidadesVO) vo0.listaCidades.get(i); 
								lblCidade0.setHTML("&raquo;&nbsp;<a href=" + SMART_LINK_CIDADE_DEFAULT + cidade0.getId() + ">" + cidade0.getDescricao() + "</a>");
							} else if (i == 1) {
								PainelUfCidadesVO cidade1 = (PainelUfCidadesVO) vo0.listaCidades.get(i); 
								lblCidade1.setHTML("&raquo;&nbsp;<a href=" + SMART_LINK_CIDADE_DEFAULT + cidade1.getId() + ">" + cidade1.getDescricao() + "</a>");
							} else if (i == 2) {
								PainelUfCidadesVO cidade2 = (PainelUfCidadesVO) vo0.listaCidades.get(i); 
								lblCidade2.setHTML("&raquo;&nbsp;<a href=" + SMART_LINK_CIDADE_DEFAULT + cidade2.getId() + ">" + cidade2.getDescricao() + "</a>");
							} else if (i == 3) {
								PainelUfCidadesVO cidade3 = (PainelUfCidadesVO) vo0.listaCidades.get(i); 
								lblCidade3.setHTML("&raquo;&nbsp;<a href=" + SMART_LINK_CIDADE_DEFAULT + cidade3.getId() + ">" + cidade3.getDescricao() + "</a>");
							} else if (i == 4) {
								PainelUfCidadesVO cidade4 = (PainelUfCidadesVO) vo0.listaCidades.get(i); 
								lblCidade4.setHTML("&raquo;&nbsp;<a href=" + SMART_LINK_CIDADE_DEFAULT + cidade4.getId() + ">" + cidade4.getDescricao() + "</a>");
							} else if (i == 5) {
								PainelUfCidadesVO cidade5 = (PainelUfCidadesVO) vo0.listaCidades.get(i); 
								lblCidade5.setHTML("&raquo;&nbsp;<a href=" + SMART_LINK_CIDADE_DEFAULT + cidade5.getId() + ">" + cidade5.getDescricao() + "</a>");
							} // if(...)
						} // for (...)
					}
					
					//------------------------------------------------------
					// Cidade 1 = Segunda coluna de UF e Cidades
					//------------------------------------------------------
					PainelUfCidadesVO vo1 = (PainelUfCidadesVO)result.get(1);
					lblUf1.setHTML("<a href=" + SMART_LINK_UF_DEFAULT + vo1.getId() + ">" + vo1.getDescricao() + "</a>");
					//lblQtdeUf1.setText(String.valueOf(vo1.getQuantidade()).concat(" Imóveis"));
					if (vo1.listaCidades != null) {
						//------------------------------------------------------
						// Obtem a lista de cidades - Somente sera apresentado
						// 6 cidades no painel
						//------------------------------------------------------
						for (int i = 0; i < vo1.listaCidades.size(); i++){
							if (i == 0) {
								PainelUfCidadesVO cidade0 = (PainelUfCidadesVO) vo1.listaCidades.get(i); 
								lblCidade6.setHTML("&raquo;&nbsp;<a href=" + SMART_LINK_CIDADE_DEFAULT + cidade0.getId() + ">" + cidade0.getDescricao() + "</a>");
							} else if (i == 1) {
								PainelUfCidadesVO cidade1 = (PainelUfCidadesVO) vo1.listaCidades.get(i); 
								lblCidade7.setHTML("&raquo;&nbsp;<a href=" + SMART_LINK_CIDADE_DEFAULT + cidade1.getId() + ">" + cidade1.getDescricao() + "</a>");
							} else if (i == 2) {
								PainelUfCidadesVO cidade2 = (PainelUfCidadesVO) vo1.listaCidades.get(i); 
								lblCidade8.setHTML("&raquo;&nbsp;<a href=" + SMART_LINK_CIDADE_DEFAULT + cidade2.getId() + ">" + cidade2.getDescricao() + "</a>");
							} else if (i == 3) {
								PainelUfCidadesVO cidade3 = (PainelUfCidadesVO) vo1.listaCidades.get(i); 
								lblCidade9.setHTML("&raquo;&nbsp;<a href=" + SMART_LINK_CIDADE_DEFAULT + cidade3.getId() + ">" + cidade3.getDescricao() + "</a>");
							} else if (i == 4) {
								PainelUfCidadesVO cidade4 = (PainelUfCidadesVO) vo1.listaCidades.get(i); 
								lblCidade10.setHTML("&raquo;&nbsp;<a href=" + SMART_LINK_CIDADE_DEFAULT + cidade4.getId() + ">" + cidade4.getDescricao() + "</a>");
							} else if (i == 5) {
								PainelUfCidadesVO cidade5 = (PainelUfCidadesVO) vo1.listaCidades.get(i); 
								lblCidade11.setHTML("&raquo;&nbsp;<a href=" + SMART_LINK_CIDADE_DEFAULT + cidade5.getId() + ">" + cidade5.getDescricao() + "</a>");
							}
						}
					}
					
					//------------------------------------------------------
					// Cidade 3 = 1 Coluna da Segunda linha de UF e Cidades
					//------------------------------------------------------
					PainelUfCidadesVO vo2 = (PainelUfCidadesVO)result.get(2);
					lblUf2.setHTML("<a href=" + SMART_LINK_UF_DEFAULT + vo2.getId() + ">" + vo2.getDescricao() + "</a>");
					//lblQtdeUf2.setText(String.valueOf(vo2.getQuantidade()).concat(" Imóveis"));
					if (vo2.listaCidades != null) {
						//------------------------------------------------------
						// Obtem a lista de cidades - Somente sera apresentado
						// 6 cidades no painel
						//------------------------------------------------------
						for (int i = 0; i < vo2.listaCidades.size(); i++){
							if (i == 0) {
								PainelUfCidadesVO cidade0 = (PainelUfCidadesVO) vo2.listaCidades.get(i); 
								lblCidade12.setHTML("&raquo;&nbsp;<a href=" + SMART_LINK_CIDADE_DEFAULT + cidade0.getId() + ">" + cidade0.getDescricao() + "</a>");
							} else if (i == 1) {
								PainelUfCidadesVO cidade1 = (PainelUfCidadesVO) vo2.listaCidades.get(i); 
								lblCidade13.setHTML("&raquo;&nbsp;<a href=" + SMART_LINK_CIDADE_DEFAULT + cidade1.getId() + ">" + cidade1.getDescricao() + "</a>");
							} else if (i == 2) {
								PainelUfCidadesVO cidade2 = (PainelUfCidadesVO) vo2.listaCidades.get(i); 
								lblCidade14.setHTML("&raquo;&nbsp;<a href=" + SMART_LINK_CIDADE_DEFAULT + cidade2.getId() + ">" + cidade2.getDescricao() + "</a>");
							} else if (i == 3) {
								PainelUfCidadesVO cidade3 = (PainelUfCidadesVO) vo2.listaCidades.get(i); 
								lblCidade15.setHTML("&raquo;&nbsp;<a href=" + SMART_LINK_CIDADE_DEFAULT + cidade3.getId() + ">" + cidade3.getDescricao() + "</a>");
							} else if (i == 4) {
								PainelUfCidadesVO cidade4 = (PainelUfCidadesVO) vo2.listaCidades.get(i); 
								lblCidade16.setHTML("&raquo;&nbsp;<a href=" + SMART_LINK_CIDADE_DEFAULT + cidade4.getId() + ">" + cidade4.getDescricao() + "</a>");
							} else if (i == 5) {
								PainelUfCidadesVO cidade5 = (PainelUfCidadesVO) vo2.listaCidades.get(i); 
								lblCidade17.setHTML("&raquo;&nbsp;<a href=" + SMART_LINK_CIDADE_DEFAULT + cidade5.getId() + ">" + cidade5.getDescricao() + "</a>");
							}
						}
					}

					//------------------------------------------------------
					// Cidade 4 = 2 Coluna da Segunda linha de UF e Cidades
					//------------------------------------------------------
					PainelUfCidadesVO vo3 = (PainelUfCidadesVO)result.get(3);
					lblUf3.setHTML("<a href=" + SMART_LINK_UF_DEFAULT + vo3.getId() + ">" + vo3.getDescricao() + "</a>");
					//lblQtdeUf3.setText(String.valueOf(vo3.getQuantidade()).concat(" Imóveis"));
					if (vo3.listaCidades != null) {
						//------------------------------------------------------
						// Obtem a lista de cidades - Somente sera apresentado
						// 6 cidades no painel
						//------------------------------------------------------
						for (int i = 0; i < vo3.listaCidades.size(); i++){
							if (i == 0) {
								PainelUfCidadesVO cidade0 = (PainelUfCidadesVO) vo3.listaCidades.get(i); 
								lblCidade18.setHTML("&raquo;&nbsp;<a href=" + SMART_LINK_CIDADE_DEFAULT + cidade0.getId() + ">" + cidade0.getDescricao() + "</a>");
							} else if (i == 1) {
								PainelUfCidadesVO cidade1 = (PainelUfCidadesVO) vo3.listaCidades.get(i); 
								lblCidade19.setHTML("&raquo;&nbsp;<a href=" + SMART_LINK_CIDADE_DEFAULT + cidade1.getId() + ">" + cidade1.getDescricao() + "</a>");
							} else if (i == 2) {
								PainelUfCidadesVO cidade2 = (PainelUfCidadesVO) vo3.listaCidades.get(i); 
								lblCidade20.setHTML("&raquo;&nbsp;<a href=" + SMART_LINK_CIDADE_DEFAULT + cidade2.getId() + ">" + cidade2.getDescricao() + "</a>");
							} else if (i == 3) {
								PainelUfCidadesVO cidade3 = (PainelUfCidadesVO) vo3.listaCidades.get(i); 
								lblCidade21.setHTML("&raquo;&nbsp;<a href=" + SMART_LINK_CIDADE_DEFAULT + cidade3.getId() + ">" + cidade3.getDescricao() + "</a>");
							} else if (i == 4) {
								PainelUfCidadesVO cidade4 = (PainelUfCidadesVO) vo3.listaCidades.get(i); 
								lblCidade22.setHTML("&raquo;&nbsp;<a href=" + SMART_LINK_CIDADE_DEFAULT + cidade4.getId() + ">" + cidade4.getDescricao() + "</a>");
							} else if (i == 5) {
								PainelUfCidadesVO cidade5 = (PainelUfCidadesVO) vo3.listaCidades.get(i); 
								lblCidade23.setHTML("&raquo;&nbsp;<a href=" + SMART_LINK_CIDADE_DEFAULT + cidade5.getId() + ">" + cidade5.getDescricao() + "</a>");
							}
						}
					}
				}
			}
			public void onFailure(Throwable caught) {
				// TODO fazer tratamento dos retornos de falhas
			}
		};
		rpc.listarEstadosMaisVisitados(callback);
	}

}
