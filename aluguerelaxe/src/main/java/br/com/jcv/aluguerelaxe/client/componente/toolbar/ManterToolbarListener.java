package br.com.jcv.aluguerelaxe.client.componente.toolbar;

/**
 * Interface base para toolbar com ações de manutenção de formulários
 * 
 * @author julio
 *
 */
public interface ManterToolbarListener extends ToolbarBaseListener {

	void onSalvarClick();
	void onRefreshClick();
}
