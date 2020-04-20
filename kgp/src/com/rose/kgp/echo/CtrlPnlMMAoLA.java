package com.rose.kgp.echo;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.rose.heart.construct.Normal_Heart;

public class CtrlPnlMMAoLA {

	PnlMMAoLA pnlMMAoLA;
	AoRootListener aoRootListener;
	LAListener laListener;	
	AoAscListener aoAscListener;
	Normal_Heart heartDia;
	/*
	 * setter and getter
	 */
	protected PnlMMAoLA getPanel() {
		return pnlMMAoLA;
	}		
	
	public CtrlPnlMMAoLA(Normal_Heart heartDia) {
		this.heartDia = heartDia;
		pnlMMAoLA = new PnlMMAoLA();		
		setListener();		
	}
	
	private void setListener() {
		//listener
		aoRootListener = new AoRootListener();
		laListener = new LAListener();
		aoAscListener = new AoAscListener();
		pnlMMAoLA.getftxtLA().addPropertyChangeListener(laListener);
		pnlMMAoLA.getftxtAoRoot().addPropertyChangeListener(aoRootListener);
		pnlMMAoLA.getftxtAoAsc().addPropertyChangeListener(aoAscListener);
	}
	
	class AoAscListener implements PropertyChangeListener{

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			Long lVal;
			Double dVal;
			//cast input value to Double
			try {				
				lVal = (Long) pnlMMAoLA.getftxtAoAsc().getValue();
				dVal = lVal.doubleValue();				
			} catch (NullPointerException | ClassCastException e) {
				dVal = (Double) pnlMMAoLA.getftxtAoAsc().getValue();				
			}
			//set the value to the aortic root
			if(dVal instanceof Double) {
				heartDia.getAorta().getAscSeg().setDiameter(dVal); 
			}					
		}		
	}
	
	class AoRootListener implements PropertyChangeListener {

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
						
			Long lVal;
			Double dVal;
			//cast input value to Double
			try {				
				lVal = (Long) pnlMMAoLA.getftxtAoRoot().getValue();
				dVal = lVal.doubleValue();				
			} catch (NullPointerException | ClassCastException e) {
				dVal = (Double) pnlMMAoLA.getftxtAoRoot().getValue();				
			}
			//set the value to the aortic root
			if(dVal instanceof Double) {
				heartDia.getAorta().getAorticRoot().setDiameter(dVal); 
			}				
		}		
	}
	
	class LAListener implements PropertyChangeListener{

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			Long lVal;
			Double dVal;
			//cast input value to Double
			try {				
				lVal = (Long) pnlMMAoLA.getftxtLA().getValue();
				dVal = lVal.doubleValue();				
			} catch (NullPointerException | ClassCastException e) {
				dVal = (Double) pnlMMAoLA.getftxtLA().getValue();				
			}
			//set the value to the left atrium
			if(dVal instanceof Double) {
				heartDia.getLeftAtrium().setWidth(dVal); 
			}			
		}		
	}
}
