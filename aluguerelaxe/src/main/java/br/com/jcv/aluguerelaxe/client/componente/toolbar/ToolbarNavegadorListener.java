package br.com.jcv.aluguerelaxe.client.componente.toolbar;

public interface ToolbarNavegadorListener extends ToolbarListener {
	void onMoverParaInicio();
	void onMoverParaFinal();
	void onMoverParaAnterior(int indice);
	void onMoverParaProximo(int indice);
	void onItensPorPaginaChange(int item);
}

