package com.rose.kgp.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import com.rose.kgp.allocator.Ctrl_DlgClinicalInstitution;
import com.rose.kgp.personnel.Ctrl_DlgNurse;
import com.rose.kgp.personnel.Ctrl_DlgPhysician;
import com.rose.kgp.personnel.Ctrl_PnlNurse;
import com.rose.kgp.personnel.Ctrl_PnlPhysician;

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
				//prepare to instantiate the panel for the new nurse
				ctrlPnlSetBirthDate = new Ctrl_PnlSetDate("dd.MM.yyyy", LocalDate.now(), LocalDate.now().minusYears(60));
				ctrlPnlSetOnsetDate = new Ctrl_PnlSetDate("dd.MM.yyyy", LocalDate.now(), LocalDate.now().minusDays(7));
				//instantiate the controller of the panel for the new nurse
				Ctrl_PnlNurse ctrlPnlNewNurse = new Ctrl_PnlNurse(ctrlPnlSetBirthDate, ctrlPnlSetOnsetDate);
				//instantiate the controller of the dialog
				Ctrl_DlgNurse conDlgNurse = new Ctrl_DlgNurse(ctrlPnlNewNurse);
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
