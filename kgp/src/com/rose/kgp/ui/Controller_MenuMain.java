package com.rose.kgp.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.rose.kgp.allocator.Ctrl_DlgClinicalInstitution;
import com.rose.kgp.personnel.Ctrl_DlgNurse;
import com.rose.kgp.personnel.Ctrl_DlgPhysician;



public class Controller_MenuMain {
	Pnl_MenuMain pnlMenuMain;
	Ctrl_PnlSetDate ctrlPnlSetBirthDate, ctrlPnlSetOnsetDate;
	
	public Controller_MenuMain() {
		pnlMenuMain = new Pnl_MenuMain();
		setListener();
	}
	
	private void setListener(){
		pnlMenuMain.addDoctorListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//prepare to instantiate the panel for the new physician
				//ctrlPnlSetBirthDate = new Ctrl_PnlSetDate("dd.MM.yyyy", LocalDate.now(), LocalDate.now().minusYears(60));
				//ctrlPnlSetOnsetDate = new Ctrl_PnlSetDate("dd.MM.yyyy", LocalDate.now(), LocalDate.now().minusDays(7));
				//instantiate the controller of the panel for the new physician
				
				//instantiate the controller of the dialog
				Ctrl_DlgPhysician ctrlDlgPhysician = new Ctrl_DlgPhysician();
				ctrlDlgPhysician.showDialog();
			}
		});
		
		pnlMenuMain.addNurseListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				//instantiate the controller of the dialog
				Ctrl_DlgNurse conDlgNurse = new Ctrl_DlgNurse();
				conDlgNurse.showDialog();
				
			}
			
		});
		
		pnlMenuMain.addAllocatorAddListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Ctrl_DlgClinicalInstitution ctrlDlgAllocator = new Ctrl_DlgClinicalInstitution();
				ctrlDlgAllocator.showDialog();
			}
			
		});
	}
	
	protected Pnl_MenuMain getPanel(){
		return this.pnlMenuMain;
	}
}
