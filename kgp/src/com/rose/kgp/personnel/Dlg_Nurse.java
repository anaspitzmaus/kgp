package com.rose.kgp.personnel;

import java.awt.BorderLayout;

import com.rose.kgp.ui.Pnl_SetDate;


public class Dlg_Nurse extends Dlg_Staff {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6742296257558247049L;

	/**
	 * Create the dialog.
	 */
	public Dlg_Nurse(Pnl_SetDate birthPanel, Pnl_SetDate onsetPanel) {
		setPnlStaff(new Pnl_Nurse());
		getPnlStaff().addBirthDatePanel(birthPanel);
		getPnlStaff().addOnsetDatePanel(onsetPanel);
		contentPanel.add(getPnlStaff(), BorderLayout.SOUTH);
		btnNewStaff.setText("Schwester anlegen");
	}
	
	

}
