package br.com.jcv.aluguerelaxe.client.componente.grid;

import java.util.ArrayList;
import java.util.List;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.componente.checkbox.LabeledCheckBox;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.FichaImovelRPCAsync;
import br.com.jcv.aluguerelaxe.client.imovel.local.ClassificacaoVO;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;

public class GridClassificacao extends Composite implements AsyncCallback<List<ClassificacaoVO>>{
	
	private int colunas;
	private Grid grid = new Grid();
	private List<LabeledCheckBox> lst = new ArrayList<LabeledCheckBox>();
	
	public GridClassificacao() {
		this(1);
	}

	public GridClassificacao(int colunas) {
		this.colunas = colunas;
		carregarCaracteristicas();
		initWidget(this.grid);
		this.setStylePrimaryName("grid-classificacao");
	}

	private void carregarCaracteristicas() {
		FichaImovelRPCAsync rpc = ServicosRPC.getFichaImovelRPC();
		AsyncCallback<List<ClassificacaoVO>> callback = this;
		rpc.listarClassificacaoLocal(callback);
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

	public void onSuccess(List<ClassificacaoVO> result) {
		
		if (result != null){
			if (result.size() > 0) {
				int linhasGrid = result.size() / this.colunas + 1;
				this.grid.resize(linhasGrid, this.colunas);
				
				int l = 0;
				int c = 0;
				
				for (ClassificacaoVO vo : result) {
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
	public List<ClassificacaoVO> getList(int n) {
		List<ClassificacaoVO> lstvo = null;
		
		if ((this.lst != null) && 
			(this.lst.size() > 0) ) {
			lstvo = new ArrayList<ClassificacaoVO>();
			for (LabeledCheckBox item : this.lst) {
				ClassificacaoVO icvo = new ClassificacaoVO();
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
	public List<ClassificacaoVO> getList() {
		List<ClassificacaoVO> lstvo = null;
		
		if ((this.lst != null) && 
			(this.lst.size() > 0) ) {
			lstvo = new ArrayList<ClassificacaoVO>();
			for (LabeledCheckBox item : this.lst) {
				if (item.getCheckBox().getValue()) {
					ClassificacaoVO icvo = new ClassificacaoVO();
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

