package br.com.jcv.aluguerelaxe.client.mobile.main;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class MainEntryPoint implements EntryPoint {

	public void onModuleLoad() {
		RootPanel.get("gwt-main").add(new FiltroBase());

	}

}
