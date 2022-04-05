package br.com.jcv.aluguerelaxe.client.componente.grid;

import java.util.ArrayList;
import java.util.List;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.caracteristica.CaracteristicaRPCAsync;
import br.com.jcv.aluguerelaxe.client.caracteristica.CaracteristicaVO;
import br.com.jcv.aluguerelaxe.client.componente.checkbox.LabeledCheckBox;
import br.com.jcv.aluguerelaxe.client.imovel.caracteristica.ImovelCaracteristicaVO;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;

public class GridCaracteristica extends Composite implements AsyncCallback<List<CaracteristicaVO>>{
	
	private int colunas;
	private Grid grid = new Grid();
	private List<LabeledCheckBox> lst = new ArrayList<LabeledCheckBox>();
	
	public GridCaracteristica() {
		this(1);
	}

	public GridCaracteristica(int colunas) {
		this.colunas = colunas;
		carregarCaracteristicas();
		initWidget(this.grid);
	}

	private void carregarCaracteristicas() {
		CaracteristicaRPCAsync rpc = ServicosRPC.getCaracteristicaRPC();
		AsyncCallback<List<CaracteristicaVO>> callback = this;
		rpc.listarCaracteristicas(callback);
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

	public void onSuccess(List<CaracteristicaVO> result) {
		
		if (result != null){
			if (result.size() > 0) {
				int linhasGrid = result.size() / this.colunas + 1;
				this.grid.resize(linhasGrid, this.colunas);
				
				int l = 0;
				int c = 0;
				
				for (CaracteristicaVO vo : result) {
					LabeledCheckBox lcb = new LabeledCheckBox(vo.id, vo.nome);
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
	
	public List<ImovelCaracteristicaVO> getList() {
		List<ImovelCaracteristicaVO> lstvo = null;
		
		if ((this.lst != null) && 
			(this.lst.size() > 0) ) {
			lstvo = new ArrayList<ImovelCaracteristicaVO>();
			for (LabeledCheckBox item : this.lst) {
				ImovelCaracteristicaVO icvo = new ImovelCaracteristicaVO();
				icvo.caracteristica = new CaracteristicaVO();
				icvo.caracteristica.id = item.getId();
				lstvo.add(icvo);
			}
		}
		return lstvo;
	}

	/**
	 * Retorna uma Lista somente dos itens que foram marcados e seta
	 * a propriedade <code>indicadorCondominio</code> da instância de
	 * {@link ImovelCaracteristicaVO} para o valor informado no parâmetro
	 * como C ou I.
	 * @param indicadorCondominio
	 * @return Lista com objetos de {@link ImovelCaracteristicaVO} 
	 */
	public List<ImovelCaracteristicaVO> getList(String indicadorCondominio) {
		List<ImovelCaracteristicaVO> lstvo = null;
		
		if ((this.lst != null) && 
			(this.lst.size() > 0) ) {
			lstvo = new ArrayList<ImovelCaracteristicaVO>();
			for (LabeledCheckBox item : this.lst) {
				if (item.getCheckBox().getValue()) {
					ImovelCaracteristicaVO icvo = new ImovelCaracteristicaVO();
					icvo.indicadorCondominio = indicadorCondominio;
					icvo.caracteristica = new CaracteristicaVO();
					icvo.caracteristica.id = item.getId();
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
	
	
	public void setCheckBoxCaracteristica(List<ImovelCaracteristicaVO> icvo) {
		reset();
		if ( (icvo != null) && (icvo.size() > 0) ) {
			for (ImovelCaracteristicaVO itvo : icvo) {
				for (LabeledCheckBox lcb : this.lst) {
					if (itvo.caracteristica.id == lcb.getId()) {
						lcb.getCheckBox().setValue(true);
						break;
					}
				}
			}
		}
	}
	
}

