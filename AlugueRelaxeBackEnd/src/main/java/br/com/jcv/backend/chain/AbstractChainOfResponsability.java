package br.com.jcv.backend.chain;

import java.util.ArrayList;
import java.util.List;

import br.com.jcv.backend.factory.DAOFactory;

public abstract class AbstractChainOfResponsability<C> {
	
	private List<Chain> lst;
	private DAOFactory daofactory;
	protected C pdto;

	public abstract void setContext(C pdto);
	
	public void addChain(Chain item){
		if (lst == null){
			lst = new ArrayList<Chain>();
		}
		lst.add(item);
	}
	
	public void setContextDAO(DAOFactory daofactory) {
		this.daofactory = daofactory;
	}

	public void execute() {
		if (lst != null){
			for (Chain c : lst) {
				c.setContextDAO(daofactory);
				c.execute(pdto);
			}
		}
	}
}
