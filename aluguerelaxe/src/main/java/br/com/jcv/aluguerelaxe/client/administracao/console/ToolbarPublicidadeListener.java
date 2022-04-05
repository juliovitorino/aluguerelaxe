package br.com.jcv.aluguerelaxe.client.administracao.console;

import br.com.jcv.aluguerelaxe.client.componente.toolbar.ToolbarListener;

public interface ToolbarPublicidadeListener extends ToolbarListener {
	void onPatrocinioClick();
	void onFuraFilaClick();
	void onPrimeiraPaginaClick();
	void onPainelDestaqueClick();
	void onBannerClick();
	void onSMSClick();
	void onEmailClick();
	void onSuperBannerClick();
	void onFacebookClick();
	void onMeuDesktopPublicidadeClick();
}