package br.com.jcv.aluguerelaxe.client.superpanel;

import br.com.jcv.aluguerelaxe.client.superpanel.superbanner.SuperBannerVO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SuperPanelRPCAsync {
	void getSuperBannerPP(AsyncCallback<SuperBannerVO> callback);
	void getSuperBannerDD(AsyncCallback<SuperBannerVO> callback);
}