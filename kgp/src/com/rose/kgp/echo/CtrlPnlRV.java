package com.rose.kgp.echo;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.rose.heart.construct.Normal_Heart;

public class CtrlPnlRV {

	PnlRV pnlRV;
	Normal_Heart heart;
	RVBaseDiamListener rvBaseDiamListener;
	RVMidDiamListener rvMidDiamListener;
	RVLongDiamListener rvLongDiamListener;
	RVOT_PLAX_Listener rvotPlaxListener;
	TAPSEListener tapseListener;
	
	protected PnlRV getPanel() {
		return this.pnlRV;
	}
	
	public CtrlPnlRV(Normal_Heart heart) {
		this.heart = heart;
		pnlRV = new PnlRV();
		setListener();
	}
	
	private void setListener() {
		rvBaseDiamListener = new RVBaseDiamListener();
		rvMidDiamListener = new RVMidDiamListener();
		rvLongDiamListener = new RVLongDiamListener();
		rvotPlaxListener = new RVOT_PLAX_Listener();
		tapseListener = new TAPSEListener();
		
		pnlRV.addRVBasalDiamListener(rvBaseDiamListener);
		pnlRV.addRVMidDiamListener(rvMidDiamListener);
		pnlRV.addRVLongDiamListener(rvLongDiamListener);
		pnlRV.addRVOT_PLAX_Listener(rvotPlaxListener);
		pnlRV.addTAPSEListener(tapseListener);
	}
	
	class RVBaseDiamListener implements PropertyChangeListener {

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			Long lVal;
			Double dVal;
			//cast input value to Double
			try {				
				lVal = (Long) pnlRV.getRVBasalDiamValue();
				dVal = lVal.doubleValue();				
			} catch (NullPointerException | ClassCastException e) {
				dVal = (Double) pnlRV.getRVBasalDiamValue();			
			}
			//set the value to the RV basal diameter variable
			if(dVal instanceof Double) {
				heart.getDiastolicState().getRightVentricle().setBasalDiam(dVal);
			}	
			
		}		
	}
	
	class RVMidDiamListener implements PropertyChangeListener {

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			Long lVal;
			Double dVal;
			//cast input value to Double
			try {				
				lVal = (Long) pnlRV.getRVMidDiamValue();
				dVal = lVal.doubleValue();				
			} catch (NullPointerException | ClassCastException e) {
				dVal = (Double) pnlRV.getRVMidDiamValue();			
			}
			//set the value to the RV mid diameter variable
			if(dVal instanceof Double) {
				heart.getDiastolicState().getRightVentricle().setMidDiam(dVal);
			}	
			
		}		
	}
	
	class RVLongDiamListener implements PropertyChangeListener {

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			Long lVal;
			Double dVal;
			//cast input value to Double
			try {				
				lVal = (Long) pnlRV.getRVLongDiamValue();
				dVal = lVal.doubleValue();				
			} catch (NullPointerException | ClassCastException e) {
				dVal = (Double) pnlRV.getRVLongDiamValue();			
			}
			//set the value to the RV long diameter variable
			if(dVal instanceof Double) {
				heart.getDiastolicState().getRightVentricle().setHeight(dVal);
			}	
			
		}		
	}
	
	class RVOT_PLAX_Listener implements PropertyChangeListener {

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			Long lVal;
			Double dVal;
			//cast input value to Double
			try {				
				lVal = (Long) pnlRV.getRVOT_PLAXValue();
				dVal = lVal.doubleValue();				
			} catch (NullPointerException | ClassCastException e) {
				dVal = (Double) pnlRV.getRVOT_PLAXValue();			
			}
			//set the value to the enddiastolic RVOT diameter variable
			if(dVal instanceof Double) {
				heart.getDiastolicState().getRightVentricle().setWidth(dVal);
			}	
			
		}		
	}
	
	class TAPSEListener implements PropertyChangeListener {

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			Long lVal;
			Double dVal;
			//cast input value to Double
			try {				
				lVal = (Long) pnlRV.getTAPSEValue();
				dVal = lVal.doubleValue();				
			} catch (NullPointerException | ClassCastException e) {
				dVal = (Double) pnlRV.getTAPSEValue();			
			}
			//set the value to the TAPSE variable
			if(dVal instanceof Double) {
				//heart.getDiastolicState().getRightVentricle().setWidth(dVal);
			}	
			
		}		
	}

	
	
}
