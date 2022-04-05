package br.com.jcv.aluguerelaxe.client.componente.grid;

import java.util.ArrayList;
import java.util.List;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.componente.checkbox.LabeledCheckBox;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.FichaImovelRPCAsync;
import br.com.jcv.aluguerelaxe.client.imovel.local.LocalVO;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;

public class GridLocal extends Composite implements AsyncCallback<List<LocalVO>>{
	
	private int colunas;
	private Grid grid = new Grid();
	private List<LabeledCheckBox> lst = new ArrayList<LabeledCheckBox>();
	
	public GridLocal() {
		this(1);
	}

	public GridLocal(int colunas) {
		this.colunas = colunas;
		carregarCaracteristicas();
		initWidget(this.grid);
		this.setStylePrimaryName("grid-local");
	}

	private void carregarCaracteristicas() {
		FichaImovelRPCAsync rpc = ServicosRPC.getFichaImovelRPC();
		AsyncCallback<List<LocalVO>> callback = this;
		rpc.listarLocal(callback);
	}

	public void onFailure(Throwable caught) {
		try {
			throw caught;
		} catch (IncompatibleRemoteServiceException e) {
			// TODO: handle exception
		} catch (InvocationException e) {
        	// TODO: handle exception
        } catch (AlugueRelaxeFrontException e) {
    		// TODO: handle exception
    	} catch (Throwable e) {
        	// TODO: handle exception
        }
	}

	public void onSuccess(List<LocalVO> result) {
		
		if (result != null){
			if (result.size() > 0) {
				int linhasGrid = result.size() / this.colunas + 1;
				this.grid.resize(linhasGrid, this.colunas);
				
				int l = 0;
				int c = 0;
				
				for (LocalVO vo : result) {
					LabeledCheckBox lcb = new LabeledCheckBox(vo.id, vo.descricao);
					this.grid.setWidget(l, c, lcb);
					this.lst.add(lcb);
					if (c == this.colunas - 1) {
						c = 0;
						l++;
					} else {
						c++;
					}
				}
			}
		}
	}
	
	@Deprecated
	public List<LocalVO> getList(int n) {
		List<LocalVO> lstvo = null;
		
		if ((this.lst != null) && 
			(this.lst.size() > 0) ) {
			lstvo = new ArrayList<LocalVO>();
			for (LabeledCheckBox item : this.lst) {
				LocalVO icvo = new LocalVO();
				icvo.id = item.getId();
				lstvo.add(icvo);
			}
		}
		return lstvo;
	}

	/**
	 * Retorna uma Lista somente dos itens que foram marcados
	 * @return List<LocalVO>   
	 */
	public List<LocalVO> getList() {
		List<LocalVO> lstvo = null;
		
		if ((this.lst != null) && 
			(this.lst.size() > 0) ) {
			lstvo = new ArrayList<LocalVO>();
			for (LabeledCheckBox item : this.lst) {
				if (item.getCheckBox().getValue()) {
					LocalVO icvo = new LocalVO();
					icvo.id = item.getId();
					lstvo.add(icvo);
				}
			}
		}
		return lstvo;
	}

	public void reset() {
		for (LabeledCheckBox lcb : this.lst) {
			lcb.getCheckBox().setValue(false);
		}
	}

	
}

