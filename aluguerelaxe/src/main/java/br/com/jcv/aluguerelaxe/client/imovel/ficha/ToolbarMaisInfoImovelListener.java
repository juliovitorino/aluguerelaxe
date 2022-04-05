package br.com.jcv.aluguerelaxe.client.imovel.ficha;

import br.com.jcv.aluguerelaxe.client.componente.toolbar.ToolbarListener;

public interface ToolbarMaisInfoImovelListener extends ToolbarListener {
	void onMaisDadosImovelClick();
	void onLocalizacaoClick();
	void onGaleriaFotosClick();
	void onCaracteristicasImovelClick();
	void onCaracteristicasCondominioClick();
	void onTarifasClick();
	void onContatoAnuncianteClick();
	void onDisponibilidadeClick();
	void onObservacoesGeraisClick();
	void onIndicarAmigoClick();
	void onVideoImovelClick();
}