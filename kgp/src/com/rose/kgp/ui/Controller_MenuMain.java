package com.rose.kgp.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.rose.kgp.personnel.Controller_DlgNurse;
import com.rose.kgp.personnel.Controller_DlgPhysician;

public class Controller_MenuMain {
	Pnl_MenuMain pnlMenuMain;
	
	public Controller_MenuMain() {
		pnlMenuMain = new Pnl_MenuMain();
		setListener();
	}
	
	private void setListener(){
		pnlMenuMain.addDoctorListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Controller_DlgPhysician conDlgPhysician = new Controller_DlgPhysician();
				conDlgPhysician.showDialog();
			}
		});
		
		pnlMenuMain.addNurseListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller_DlgNurse conDlgNurse = new Controller_DlgNurse();
				conDlgNurse.showDialog();
				
			}
			
		});
	}
	
	protected Pnl_MenuMain getPanel(){
		return this.pnlMenuMain;
	}
}
