package com.rose.kgp.personnel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import com.rose.kgp.db.SQL_INSERT;


import com.rose.kgp.ui.Ctrl_PnlSetDate;
/**
 * control class of the panel, a new nurse can be created
 * @author Administrator
 *
 */



public class Ctrl_PnlNurse extends Ctrl_PnlStaff{
	
	private SetNewNurseListener setNewNurseListener;
	private Ctrl_PnlSetDate ctrlPnlSetBirthDate, ctrlPnlSetOnsetDate;
	
		
	protected Ctrl_PnlSetDate getCtrlPnlSetBirthDate() {
		return ctrlPnlSetBirthDate;
	}

	protected Ctrl_PnlSetDate getCtrlPnlSetOnsetDate() {
		return ctrlPnlSetOnsetDate;
	}

	public Ctrl_PnlNurse(Ctrl_PnlSetDate ctrlPnlSetBirthDate, Ctrl_PnlSetDate ctrlPnlSetOnsetDate) {
		this.panel = new Pnl_Nurse();
		this.ctrlPnlSetBirthDate = ctrlPnlSetBirthDate;
		this.ctrlPnlSetOnsetDate = ctrlPnlSetOnsetDate;
		//add the date panels to the basic panel
//		addPnlSetOnsetDate(this.ctrlPnlSetOnsetDate.getPanel());
//		addPnlSetBirthDate(this.ctrlPnlSetBirthDate.getPanel());
		
		initializeExtraListener();
		setExtraListener();
	}
	
	/**
	 * initializes all listeners of input fields of that panel that are not initialized by the super class
	 */
	
	private void initializeExtraListener(){		
			
		setNewNurseListener = new SetNewNurseListener();
	}
	
	/**
	 * add all listeners of input fields of that panel
	 */
	private void setExtraListener(){				
				
		panel.addSetNewStaffListener(setNewNurseListener);
	}

	/**
	 * prepares the panel before a new physician is to be created
	 * therefore the data fields are enabled and the right dates are set
	 */
	protected void prepareForNewNurse(){
		super.prepareForNewStaff(new Nurse(""));	
	}
	
	class SetNewNurseListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {	
			
			if(staff.getId() == null){//if a new physician has to be added
				staff.setBirthday(ctrlPnlSetBirthDate.getDate());
				staff.setOnset(ctrlPnlSetOnsetDate.getDate());
				//insert into database
				Integer id = SQL_INSERT.Nurse((Nurse)staff, LocalDate.now());
					if(id != null){
						staff.setId(id);
//						setChanged();
//						notifyObservers(staff);//notify the Controller of the Dialog 'Controller_DlgPhysician'
					}
					
//					removeListener(); //remove all listeners
					//empty all input fields
					panel.getTxtSurname().setText("");
					panel.getTxtFirstname().setText("");
					panel.getTxtAlias().setText("");
					panel.getComboSex().setSelectedIndex(-1);
					panel.getComboSex().repaint();
					
					//add all listeners to the input fields
					setExtraListener();
				
			}
		}		
	}
}
