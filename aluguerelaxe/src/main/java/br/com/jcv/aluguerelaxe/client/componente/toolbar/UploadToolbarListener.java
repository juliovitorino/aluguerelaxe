package br.com.jcv.aluguerelaxe.client.componente.toolbar;

/**
 * Interface para eventos de Toolbar em Grid
 * @author julio
 *
 */
public interface UploadToolbarListener extends ToolbarListener {
	void onAdicionarClick();
	void onRemoverClick();
	void onUploadClick();
}
