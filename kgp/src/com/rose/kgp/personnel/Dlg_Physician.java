package com.rose.kgp.personnel;

import java.awt.BorderLayout;

import com.rose.kgp.ui.Pnl_SetDate;



public class Dlg_Physician extends Dlg_Staff {

	
	private static final long serialVersionUID = 8014828254680084526L;
	/**
	 * Create the dialog.
	 */
	protected Dlg_Physician(Pnl_SetDate birthPanel, Pnl_SetDate onsetPanel) {	
		setPnlStaff(new Pnl_Physician());
		getPnlStaff().addBirthDatePanel(birthPanel);
		getPnlStaff().addOnsetDatePanel(onsetPanel);
		contentPanel.add(getPnlStaff(), BorderLayout.SOUTH);	
		btnNewStaff.setText("Arzt anlegen");		
	}
	
	
	
	
	
	

}
