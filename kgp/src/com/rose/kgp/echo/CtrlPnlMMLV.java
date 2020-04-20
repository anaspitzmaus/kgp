package com.rose.kgp.echo;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.prefs.Preferences;

import com.rose.heart.construct.Normal_Heart;
import com.rose.heart.structures.Wall.Part;

public class CtrlPnlMMLV {
	Preferences prefs;
	Normal_Heart heart;
	protected PnlMMLV pnlMMLV;
	IVSsysListener ivsSysListener;
	IVSdiaListener ivsDiaListener;
	LVIDsysListener lvidSysListener;
	LVIDdiaListener lvidDiaListener;
	LVPWsysListener lvpwSysListener;
	LVPWdiaListener lvpwDiaListener;
	
	//getter
	protected PnlMMLV getPanel(){
		return pnlMMLV;
	}
	
	//constructor
	public CtrlPnlMMLV(Normal_Heart heart) {
		prefs = Preferences.userRoot().node(this.getClass().getName());
		
		String IVSdMNormal = "IVSdMNormal";
		String IVSdMMild = "IVSdMMild";
		String IVSdMModerate = "IVSdMModerate";
		String IVSdMSevere = "IVSdMSevere";
		
		prefs.putDouble(IVSdMNormal, 6.0);
		prefs.putDouble(IVSdMMild, 10.5);
		prefs.putDouble(IVSdMModerate, 13.5);
		prefs.putDouble(IVSdMSevere, 16.5);
		
		String LVPWdMNormal = "LVPWdMNormal";
		String LVPWdMMild = "LVPWdMMild";
		String LVPWdMModerate = "LVPWdMModerate";
		String LVPWdMSevere = "LVPWdMSevere";
		
		prefs.putDouble(LVPWdMNormal, 6.0);
		prefs.putDouble(LVPWdMMild, 10.5);
		prefs.putDouble(LVPWdMModerate, 13.5);
		prefs.putDouble(LVPWdMSevere, 16.5);
		
		String IVSdFNormal = "IVSdFNormal";
		String IVSdFMild = "IVSdFMild";
		String IVSdFModerate = "IVSdFModerate";
		String IVSdFSevere = "IVSdFSevere";
		
		prefs.putDouble(IVSdFNormal, 6.0);
		prefs.putDouble(IVSdFMild, 10.5);
		prefs.putDouble(IVSdFModerate, 13.5);
		prefs.putDouble(IVSdFSevere, 16.5);
		
		String LVPWdFNormal = "LVPWdFNormal";
		String LVPWdFMild = "LVPWdFMild";
		String LVPWdFModerate = "LVPWdFModerate";
		String LVPWdFSevere = "LVPWdFSevere";
		
		prefs.putDouble(LVPWdFNormal, 6.0);
		prefs.putDouble(LVPWdFMild, 10.5);
		prefs.putDouble(LVPWdFModerate, 13.5);
		prefs.putDouble(LVPWdFSevere, 16.5);
		
		
		this.heart = heart;
		
		pnlMMLV = new PnlMMLV();
		setListener();
	}
	
	private void setListener() {
		ivsSysListener = new IVSsysListener();
		ivsDiaListener = new IVSdiaListener();
		lvidSysListener = new LVIDsysListener();
		lvidDiaListener = new LVIDdiaListener();
		lvpwSysListener = new LVPWsysListener();
		lvpwDiaListener = new LVPWdiaListener();
		
		pnlMMLV.getFtxtIVSs().addPropertyChangeListener(ivsSysListener);
		pnlMMLV.getFtxtIVSd().addPropertyChangeListener(ivsDiaListener);
		pnlMMLV.getFtxtLVIDs().addPropertyChangeListener(lvidSysListener);
		pnlMMLV.getFtxtLVIDd().addPropertyChangeListener(lvidDiaListener);
		pnlMMLV.getFtxtLVPWs().addPropertyChangeListener(lvpwSysListener);
		pnlMMLV.getFtxtLVPWd().addPropertyChangeListener(lvpwDiaListener);
	}
	
	public void setIVSSys(Double ivsSys) {
		pnlMMLV.getFtxtIVSs().setValue(ivsSys);
	}
	
	private Double calculateLVMass() {
		Double lvMass = null;
		if(!(getIVSDiaWidth() == null) && !(heart.getDiastolicState().getLeftVentricle().getWidth() == null) && !(getLVPWDiaWidth() == null)) {
			lvMass = (0.8*(1.04*(Math.pow((getIVSDiaWidth()/10 + heart.getDiastolicState().getLeftVentricle().getWidth()/10 + getLVPWDiaWidth()/10), 3) 
					- Math.pow(heart.getDiastolicState().getLeftVentricle().getWidth()/10, 3)))) + 0.6;
				
		}
		pnlMMLV.getFtxtLVMass().setValue(lvMass);
		return lvMass;
	}
		
		
	public Double getIVSSysWidth() {
		for(Part part : heart.getSystolicState().getLeftVentricle().getWall().getParts()) {
			if(part.getNotation() == "septal") {
				if(part.getWidth() instanceof Double) {
					return part.getWidth();
				}
			}
		}
		return null;
	}
	
	public Double getIVSDiaWidth() {
		for(Part part : heart.getDiastolicState().getLeftVentricle().getWall().getParts()) {
			if(part.getNotation() == "septal") {
				if(part.getWidth() instanceof Double) {
					return part.getWidth();
				}
			}
		}
		
		return null;
	}
	
	public Double getLVPWSysWidth() {
		for(Part part : heart.getSystolicState().getLeftVentricle().getWall().getParts()) {
			if(part.getNotation() == "posterior") {
				if(part.getWidth() instanceof Double) {
					return part.getWidth();
				}
			}
		}
		return null;
	}
	
	public Double getLVPWDiaWidth() {
		for(Part part : heart.getDiastolicState().getLeftVentricle().getWall().getParts()) {
			if(part.getNotation() == "posterior") {
				if(part.getWidth() instanceof Double) {
					return part.getWidth();
				}
			}
		}
		
		return null;
	}
	
	class IVSsysListener implements PropertyChangeListener {

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			Long lVal;
			Double dVal;
			//cast input value to Double
			try {				
				lVal = (Long) pnlMMLV.getFtxtIVSs().getValue();
				dVal = lVal.doubleValue();				
			} catch (NullPointerException | ClassCastException e) {
				dVal = (Double) pnlMMLV.getFtxtIVSs().getValue();				
			}
			//set the value to the IVSsys variable
			if(dVal instanceof Double) {
				for(Part part : heart.getSystolicState().getLeftVentricle().getWall().getParts()) {
					if(part.getNotation() == "septal") {
						part.setWidth(dVal);						
					}
				} 
			}	
			
		}		
	}
	
	class IVSdiaListener implements PropertyChangeListener{

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			Long lVal;
			Double dVal;
			//cast input value to Double
			try {				
				lVal = (Long) pnlMMLV.getFtxtIVSd().getValue();
				dVal = lVal.doubleValue();				
			} catch (NullPointerException | ClassCastException e) {
				dVal = (Double) pnlMMLV.getFtxtIVSd().getValue();				
			}
			//set the value to the IVSdia variable
			if(dVal instanceof Double) {
				for(Part part : heart.getDiastolicState().getLeftVentricle().getWall().getParts()) {
					if(part.getNotation() == "septal") {
						part.setWidth(dVal);
						calculateLVMass();
					}
				} 
			}	
			
		}		
	}
	
	class LVIDsysListener implements PropertyChangeListener{

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			Long lVal;
			Double dVal;
			//cast input value to Double
			try {				
				lVal = (Long) pnlMMLV.getFtxtLVIDs().getValue();
				dVal = lVal.doubleValue();				
			} catch (NullPointerException | ClassCastException e) {
				dVal = (Double) pnlMMLV.getFtxtLVIDs().getValue();				
			}
			//set the value to the LVIDSys variable
			if(dVal instanceof Double) {
				heart.getSystolicState().getLeftVentricle().setWidth(dVal);			
			}	
			
		}		
	}
	
	class LVIDdiaListener implements PropertyChangeListener{

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			Long lVal;
			Double dVal;
			//cast input value to Double
			try {				
				lVal = (Long) pnlMMLV.getFtxtLVIDd().getValue();
				dVal = lVal.doubleValue();				
			} catch (NullPointerException | ClassCastException e) {
				dVal = (Double) pnlMMLV.getFtxtLVIDd().getValue();				
			}
			//set the value to the variable of the diastolic LV Diameter
			if(dVal instanceof Double) {
				heart.getDiastolicState().getLeftVentricle().setWidth(dVal);
				calculateLVMass();
			}	
			
		}			
	}
	
	class LVPWsysListener implements PropertyChangeListener{

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			Long lVal;
			Double dVal;
			//cast input value to Double
			try {				
				lVal = (Long) pnlMMLV.getFtxtLVPWs().getValue();
				dVal = lVal.doubleValue();				
			} catch (NullPointerException | ClassCastException e) {
				dVal = (Double) pnlMMLV.getFtxtLVPWs().getValue();				
			}
			//set the value to the IVSdia variable
			if(dVal instanceof Double) {
				for(Part part : heart.getSystolicState().getLeftVentricle().getWall().getParts()) {
					if(part.getNotation() == "posterior") {
						part.setWidth(dVal);
					}
				} 
			}	
			
		}			
	}
	
	class LVPWdiaListener implements PropertyChangeListener{

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			Long lVal;
			Double dVal;
			//cast input value to Double
			try {				
				lVal = (Long) pnlMMLV.getFtxtLVPWd().getValue();
				dVal = lVal.doubleValue();				
			} catch (NullPointerException | ClassCastException e) {
				dVal = (Double) pnlMMLV.getFtxtLVPWd().getValue();				
			}
			//set the value to the IVSdia variable
			if(dVal instanceof Double) {
				for(Part part : heart.getDiastolicState().getLeftVentricle().getWall().getParts()) {
					if(part.getNotation() == "posterior") {
						part.setWidth(dVal);
						calculateLVMass();
					}
				} 
			}	
			
		}			
	}
	
	
	
}
