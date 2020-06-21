package com.rose.kgp.echo;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.rose.heart.construct.Normal_Heart;
import com.rose.heart.structures.DIAMKIND;
import com.rose.heart.structures.Wall.Part;


public class CtrlPnlMMLV {
	
	Normal_Heart heart;
	protected PnlMMLV pnlMMLV;
	IVSsysListener ivsSysListener;
	IVSdiaListener ivsDiaListener;
	LVIDsysListener lvidSysListener;
	LVIDdiaListener lvidDiaListener;
	LVPWsysListener lvpwSysListener;
	LVPWdiaListener lvpwDiaListener;
	ArrayList<Study> studies;
	DefaultListModel<Study> studyListModel;
	
	//getter
	protected PnlMMLV getPanel(){
		return pnlMMLV;
	}
	
	//constructor
	
	public CtrlPnlMMLV(Normal_Heart heart) {		
		
		this.heart = heart;
		studies = new ArrayList<Study>();
		pnlMMLV = new PnlMMLV();
		setListener();
		
		studies.add(createStudy());
		studyListModel = new DefaultListModel<Study>();
		studyListModel.addElement(studies.get(0));
		pnlMMLV.getStudyList().setModel(studyListModel);
		StudyRenderer studyRenderer = new StudyRenderer();
		pnlMMLV.getStudyList().setCellRenderer(studyRenderer);
		pnlMMLV.getStudyList().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				Study study = (Study) pnlMMLV.getStudyList().getModel().getElementAt(pnlMMLV.getStudyList().getSelectedIndex());
				pnlMMLV.getFtxtIVSd().setValue(study.getMeasurements().get(0).getValue());
				pnlMMLV.getFtxtIVSs().setValue(study.getMeasurements().get(1).getValue());
				
			}
		});
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
	
	private Study createStudy() {
		Study study = null;
		LinkedList<EchoMeasurement> list;
		list = new LinkedList<EchoMeasurement>();
		EchoMeasurement ivsd = new EchoMeasurement(ProbeLocation.PLAX, Modus.M_MODE, DIAMKIND.WIDTH, heart.getDiastolicState().getLeftVentricle().getWall().getPart("septal").getSubPart("basal"));
		ivsd.setValue(12.2);
		list.add(ivsd);
		
		EchoMeasurement ivss = new EchoMeasurement(ProbeLocation.PLAX, Modus.M_MODE, DIAMKIND.WIDTH, heart.getSystolicState().getLeftVentricle().getWall().getPart("septal").getSubPart("basal"));
		ivss.setValue(20.2);
		list.add(ivss);
		
		study = new Study(StudyType.LV_Study, list);
		return study;
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
				Object source = evt.getSource();
				ProbeLocation probeLoc = null;
				if (source == pnlMMLV.getFtxtLVIDs()) {
		        	probeLoc = ProbeLocation.PLAX;
		        }//else if (source == pnlMMLV.getFtxtLVIDs()){
		        	//probeLoc = ProbeLocation.PSAX;
		        //}
				EchoMeasurement measurement = new EchoMeasurement(probeLoc, Modus.M_MODE, DIAMKIND.WIDTH);
				measurement.setValue(dVal);				 
				heart.getSystolicState().getLeftVentricle().getMeasurements().add(measurement);			
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
	
	class LVEFListener implements PropertyChangeListener{

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			Long lVal;
			Integer iVal;
			//cast input value to Integer
			try {				
				lVal = (Long) pnlMMLV.getFtxtLVPWd().getValue();
				iVal = lVal.intValue();				
			} catch (NullPointerException | ClassCastException e) {
				iVal = (Integer) pnlMMLV.getFtxtLVPWd().getValue();				
			}
			//set the value to the  variable
			if(iVal instanceof Integer) {
				Object source = evt.getSource();
				ProbeLocation probeLoc = null;
				Modus modus = null;
				if (source == pnlMMLV.getFtxtEFTeich()) {
		        	probeLoc = ProbeLocation.PLAX;
		        	modus = Modus.M_MODE;
		        }else if(source == pnlMMLV.getFtxtLVEFSimpson()) {
		        	probeLoc = ProbeLocation.APICAL_2C;
		        	modus = Modus.B_MODE;
		        }
					
			}			
		}			
		
	}
	
	class LVVolDiaListener implements PropertyChangeListener{

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			Long lVal;
			Integer iVal;
			//cast input value to Integer
			try {				
				lVal = (Long) pnlMMLV.getFtxtVolDia().getValue();
				iVal = lVal.intValue();				
			} catch (NullPointerException | ClassCastException e) {
				iVal = (Integer) pnlMMLV.getFtxtVolDia().getValue();				
			}
			//set the value to the  variable
			if(iVal instanceof Integer) {
				Object source = evt.getSource();
				ProbeLocation probeLoc = null;
				Modus modus = null;
				if (source == pnlMMLV.getFtxtEFTeich()) {
		        	probeLoc = ProbeLocation.PLAX;
		        	modus = Modus.M_MODE;
		        }else if(source == pnlMMLV.getFtxtLVEFSimpson()) {
		        	probeLoc = ProbeLocation.APICAL_2C;
		        	modus = Modus.B_MODE;
		        }
					
			}			
		}		
	}
	
	/**
	 * renderer for the JList that shows the sudies
	 * @author Ekki
	 *
	 */
	
	class StudyRenderer extends JCheckBox implements ListCellRenderer<Study>{

		/**
		 * 
		 */
		private static final long serialVersionUID = 2379518573596777770L;

		@Override
		public Component getListCellRendererComponent(JList<? extends Study> list, Study study, int index,
				boolean isSelected, boolean cellHasFocus) {
			setText(study.type.toString());
			setOpaque(false);
			setSelected(true);
			return this;
		}
		
	}
	

	
	
	
}
