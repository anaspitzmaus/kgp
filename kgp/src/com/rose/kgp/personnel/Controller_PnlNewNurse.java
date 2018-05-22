package com.rose.kgp.personnel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import com.rose.kgp.db.SQL_INSERT;

import com.rose.kgp.personnel.Controller_PnlNewPhysician.TitleListener;
import com.rose.kgp.ui.Controller_PnlSetDate;

public class Controller_PnlNewNurse extends Controller_PnlNewStaff{
	
	private SetNewNurseListener setNewNurseListener;
	
	public Controller_PnlNewNurse(Controller_PnlSetDate conPnlSetBirthDate, Controller_PnlSetDate conPnlSetOnsetDate) {
		super(conPnlSetBirthDate, conPnlSetOnsetDate, new Pnl_NewNurse());
		
		this.pnlNewStaff.add(this.conPnlSetBirthDate.getPanel(), "cell 1 4,growx,aligny top");
		this.pnlNewStaff.add(this.conPnlSetOnsetDate.getPanel(), "cell 1 5,growx,aligny top");
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
				
		pnlNewStaff.addSetNewStaffListener(setNewNurseListener);
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
				staff.setBirthday(conPnlSetBirthDate.getDate());
				staff.setOnset(conPnlSetOnsetDate.getDate());
				//insert into database
				Integer id = SQL_INSERT.Nurse((Nurse)staff, LocalDate.now());
					if(id != null){
						staff.setId(id);
						setChanged();
						notifyObservers(staff);//notify the Controller of the Dialog 'Controller_DlgPhysician'
					}
					
					removeListener(); //remove all listeners
					//empty all input fields
					pnlNewStaff.getTxtSurname().setText("");
					pnlNewStaff.getTxtFirstname().setText("");
					pnlNewStaff.getTxtAlias().setText("");
					pnlNewStaff.getComboSex().setSelectedIndex(-1);
					pnlNewStaff.getComboSex().repaint();
					
					//add all listeners to the input fields
					setExtraListener();
				
			}
		}		
	}
}
