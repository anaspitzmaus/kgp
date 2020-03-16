package com.rose.kgp.echo;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CtrlPnlMMAoLA {

	PnlMMAoLA pnlMMAoLA;
	IntegerListener integerListener;
	Integer aoRoot;
	/*
	 * setter and getter
	 */
	protected PnlMMAoLA getPanel() {
		return pnlMMAoLA;
	}
	
	protected Integer getDiamAoRoot() {
		return aoRoot;
	}
	
	
	
	public CtrlPnlMMAoLA() {
		pnlMMAoLA = new PnlMMAoLA();
		//listener
		integerListener = new IntegerListener();
		pnlMMAoLA.getftxtAoRoot().addPropertyChangeListener(integerListener);
	}
	
	class IntegerListener implements PropertyChangeListener {

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			try {
				aoRoot = ((Long)pnlMMAoLA.getftxtAoRoot().getValue()).intValue();
				if(aoRoot < 20 | aoRoot > 80) {
					pnlMMAoLA.getftxtAoRoot().setValue(null);
				}
			} catch (NullPointerException e) {
				
			}	
			
		}
		
	}
}
