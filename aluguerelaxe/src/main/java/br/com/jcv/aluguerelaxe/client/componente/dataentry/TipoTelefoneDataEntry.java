package br.com.jcv.aluguerelaxe.client.componente.dataentry;

import br.com.jcv.aluguerelaxe.client.cliente.TelefoneVO;
import br.com.jcv.aluguerelaxe.client.componente.listbox.ComponenteListBox;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public class TipoTelefoneDataEntry extends Composite {
	
	private ComponenteListBox tipoTelefone = new ComponenteListBox();
	private TelefoneDataEntry telefoneDataEntry = new TelefoneDataEntry();
	
	public TipoTelefoneDataEntry() {
		initTipoTelefone();
		initWidget(montaTelefoneDataEntry());
	}

	private void initTipoTelefone() {
		tipoTelefone.addItem("Residencial", "R");
		tipoTelefone.addItem("Celular", "C");
		tipoTelefone.addItem("Fax", "F");
	}

	private Widget montaTelefoneDataEntry() {
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(tipoTelefone);
		hp.add(telefoneDataEntry);
		return hp;
	}
	
	public String getTipoTelefone() {
		return tipoTelefone.getValue(tipoTelefone.getSelectedIndex());
	}

	public String getTipoTelefoneTexto() {
		return tipoTelefone.getItemText(tipoTelefone.getSelectedIndex());
	}
	
	public void setTipoTelefone(String tipo) {
		this.tipoTelefone.setSelectedIndex(tipoTelefone.getSelectedItemValue(tipo));
	}

	public TelefoneDataEntry getTelefoneDataEntry() {
		return telefoneDataEntry;
	}

	public void setTelefoneDataEntry(TelefoneDataEntry telefoneDataEntry) {
		this.telefoneDataEntry = telefoneDataEntry;
	}
	
	public void update(TelefoneVO vo) {
		this.tipoTelefone.setSelectedIndex(this.tipoTelefone.getSelectedItemValue(vo.indTipoTelefone));
		this.telefoneDataEntry.setDDD(vo.ddd);
		this.telefoneDataEntry.setTelefone(vo.telefone);
	}

}
