package com.rose.kgp.echo;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

public class CtrlPnlValves {

	PnlValves pnlValves;
	StenosisModel stenosisModel;
	StenosisListener stenosisListener;
	
	protected PnlValves getPnlValves() {
		return pnlValves;
	}


	public CtrlPnlValves() {
		pnlValves = new PnlValves();
		stenosisModel = new StenosisModel();
		pnlValves.setStenosisModel(stenosisModel);
		stenosisListener = new StenosisListener();
		pnlValves.addStenosisListener(stenosisListener);
	}
	
	
	class StenosisModel implements ComboBoxModel<String>{
		
		ArrayList<String> grades;
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
	
	class StenosisListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent event) {
			if(event.getStateChange() == ItemEvent.SELECTED) {
				System.out.println(event.getItem());
			}
			
		}
		
	}
}
