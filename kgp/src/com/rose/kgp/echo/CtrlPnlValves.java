package com.rose.kgp.echo;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.MutableComboBoxModel;
import javax.swing.event.ListDataListener;

import com.rose.heart.structures.AV_Valve;
import com.rose.heart.structures.SL_Valve;
import com.rose.heart.structures.StenosisValve;
import com.rose.heart.structures.Valve;

public class CtrlPnlValves {

	PnlValves pnlValves;
	StenosisModel stenosisModel;
	RegurgitationModel regModel;
	StenosisListener stenosisListenerMV, stenosisListenerAV;
	MOAListener moaListener;
	PGMeanListener pgMeanListener;
	SL_Valve aorticValve;
	AV_Valve mitralValve;
	ArrayList<String> grades;
	Double PGMean, MOA;
	
	
	protected PnlValves getPnlValves() {
		return pnlValves;
	}


	public CtrlPnlValves(SL_Valve aorticValve, AV_Valve mitralValve) {
		pnlValves = new PnlValves();
		this.aorticValve = aorticValve;
		this.mitralValve = mitralValve;
		this.mitralValve.setStenosis(new StenosisValve(this.mitralValve));
		this.mitralValve.getStenosis().setGrading(0);
		this.aorticValve.setStenosis(new StenosisValve(this.aorticValve));
		this.aorticValve.getStenosis().setGrading(0);
		stenosisModel = new StenosisModel();
		pnlValves.setStenosisModel(stenosisModel);
		stenosisListenerMV = new StenosisListener(this.mitralValve);
		stenosisListenerAV = new StenosisListener(this.aorticValve);
		pnlValves.addStenosisListenerMV(stenosisListenerMV);
		pnlValves.addStenosisListenerAV(stenosisListenerAV);//still has to be implemented
		
		moaListener = new MOAListener();		
		pnlValves.addMOAListener(moaListener);
		pgMeanListener = new PGMeanListener();
		pnlValves.addPGMeanListener(pgMeanListener);
		
		regModel = new RegurgitationModel();
		pnlValves.getCbxRegGradingMV().setModel(regModel);
	}
	
	class RegurgitationModel implements MutableComboBoxModel<String>{
		ArrayList<String> regItems;
		int indexSel = -1;
		
		public RegurgitationModel() {
			regItems = new ArrayList<String>();
			
			addElement("kein");
			addElement("gering");
			addElement("gering bis mittel");
			addElement("mittel");
			addElement("mittel bis schwer");
			addElement("schwer");
			
			
		}
		
		@Override
		public Object getSelectedItem() {
			if(indexSel >= 0)
	        {
	            return regItems.get(indexSel);
	        }
	        else
	        {
	            return "";
	        }
		}

		@Override
		public void setSelectedItem(Object item) {
			for(int i = 0; i<regItems.size(); i++) {
				if(regItems.get(i).equals(item)){
					indexSel = i;
					break;
				}
			}	
			
		}

		@Override
		public void addListDataListener(ListDataListener arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public String getElementAt(int i) {
			return regItems.get(i);
		}

		@Override
		public int getSize() {
			// TODO Auto-generated method stub
			return this.regItems.size();
		}

		@Override
		public void removeListDataListener(ListDataListener arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void addElement(String item) {
			regItems.add(item);
			
		}

		@Override
		public void insertElementAt(String item, int i) {
			regItems.add(i, item);
			
		}

		@Override
		public void removeElement(Object obj) {
			regItems.remove(obj);
			
		}

		@Override
		public void removeElementAt(int i) {
			regItems.remove(i);
			
		}
		
	}
	
	
	class StenosisModel implements ComboBoxModel<String>{		
		
		int index=-1;
		
		public StenosisModel() {
			grades = new ArrayList<String>();
			grades.add("kein");
			grades.add("gering");
			grades.add("gering bis mittel");
			grades.add("mittel");
			grades.add("mittel bis schwer");
			grades.add("schwer");
		}
		
		@Override
		public void addListDataListener(ListDataListener arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public String getElementAt(int index) {
			return grades.get(index);
		}

		@Override
		public int getSize() {
			// TODO Auto-generated method stub
			return grades.size();
		}

		@Override
		public void removeListDataListener(ListDataListener arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Object getSelectedItem() {
			if(index >= 0)
	        {
	            return grades.get(index);
	        }
	        else
	        {
	            return "";
	        }
		}	

		@Override
		public void setSelectedItem(Object item) {
			for(int i = 0; i<grades.size(); i++) {
				if(grades.get(i).equals(item)){
					index = i;
					break;
				}
			}			
		}		
	}
	
	
	class StenosisListener  implements ItemListener{
		Valve valve;
		
		public StenosisListener(Valve valve) {
			this.valve = valve;
		}
		
		@Override
		public void itemStateChanged(ItemEvent event) {
			if(event.getStateChange() == ItemEvent.SELECTED) {
				for(int i = 0; i<grades.size(); i++) {
					if(grades.get(i).equals(event.getItem())){
						valve.getStenosis().setGrading(i);
						break;
					}
				}				
			}			
		}		
	}
	
	/**
	 * listener of formatted textField of mitral orifice area
	 * @author ekki
	 *
	 */
	class MOAListener implements PropertyChangeListener{

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			Long lVal;
			Double dVal;
			//if there is no input
			if(pnlValves.getFtxtMOA().getValue() != null && pnlValves.getFtxtMOA().getText().equals("")) {
				pnlValves.getFtxtMOA().setValue(null);
			}
			//cast input value to Double
			try {				
				lVal = (Long) pnlValves.getFtxtMOA().getValue();
				dVal = lVal.doubleValue();				
			} catch (NullPointerException | ClassCastException e) {
				dVal = (Double) pnlValves.getFtxtMOA().getValue();				
			}
			MOA = dVal;
			calculateMitralStenosis();		
		}			
	}
	
	class PGMeanListener implements PropertyChangeListener{

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			Long lVal;
			Double dVal;
			//if there is no input
			if(pnlValves.getFtxtPGMean().getValue() != null && pnlValves.getFtxtPGMean().getText().equals("")) {
				pnlValves.getFtxtPGMean().setValue(null);
			}
			//cast input value to Double
			try {				
				lVal = (Long) pnlValves.getFtxtPGMean().getValue();
				dVal = lVal.doubleValue();				
			} catch (NullPointerException | ClassCastException e) {
				dVal = (Double) pnlValves.getFtxtPGMean().getValue();				
			}
			
			PGMean = dVal;
			calculateMitralStenosis();
			//set the value to the variable of the mitral PGmean
			//adapted to: https://www.asecho.org/wp-content/uploads/2014/05/2009_Echo-Assessment-of-Valve-Stenosis.pdf
			
			
		}
		
	}
	
	private void calculateMitralStenosis() {
		//set the value to the variable of the mitral orifice area
		//adapted to: https://www.asecho.org/wp-content/uploads/2014/05/2009_Echo-Assessment-of-Valve-Stenosis.pdf
		Integer grdMOA = null;
		Integer grdPGMean = null;
		
		if(MOA instanceof Double) {
			if(MOA <1.0) {
				grdMOA = 5;
			}else if(MOA == 1.0) {
				grdMOA = 4;
			}else if(MOA >1.0 && MOA< 1.5) {					
				grdMOA = 3;
			}else if(MOA ==1.5) {
				grdMOA = 2;
			}else if(MOA >1.5 && MOA < 4.0) {
				grdMOA = 1;					
			}else{
				grdMOA = 0;
			}
		}
		
		if(PGMean instanceof Double) {
			if(PGMean <4.0) {
				grdPGMean = 0;
			}else if(PGMean >=4.0 && PGMean <5.0) {
				grdPGMean = 1;
			}else if(PGMean == 5.0) {					
				grdPGMean = 2;
			}else if(PGMean >5.0 && PGMean <10.0) {
				grdPGMean = 3;
			}else if(PGMean == 10.0) {
				grdPGMean = 4;					
			}else{
				grdPGMean = 5;
			}
		}
		
		if(grdPGMean != null && grdMOA != null) {			
			mitralValve.getStenosis().setGrading(Math.round(3*grdMOA + grdPGMean)/4);			
			((StenosisModel)pnlValves.getCbxStenGradingMV().getModel()).setSelectedItem(grades.get(Math.round(3*grdMOA + grdPGMean)/4));			
		}else if(grdPGMean != null) {
			mitralValve.getStenosis().setGrading(grdPGMean);
			((StenosisModel)pnlValves.getCbxStenGradingMV().getModel()).setSelectedItem(grades.get(grdPGMean));
		}else if(grdMOA != null) {
			mitralValve.getStenosis().setGrading(grdMOA);
			((StenosisModel)pnlValves.getCbxStenGradingMV().getModel()).setSelectedItem(grades.get(grdMOA));
		}
		
		pnlValves.getCbxStenGradingMV().revalidate();
		pnlValves.getCbxStenGradingMV().repaint();
	}
	
	
}
