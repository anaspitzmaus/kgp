package com.rose.kgp.personnel;

import java.awt.Dialog;
import java.time.LocalDate;

import javax.swing.JDialog;

import com.rose.kgp.ui.Ctrl_PnlSetDate;

class Ctrl_DlgNewPhysician {
	private Dlg_NewPhysician dlgNewPhysician;
	private Ctrl_PnlSetDate ctrlPnlSetBirthDate, ctrlPnlSetOnsetDate;
	private Pnl_Physician pnlPhysician;
	
	public Ctrl_DlgNewPhysician() 
	{
		ctrlPnlSetBirthDate = new Ctrl_PnlSetDate("dd.MM.yyyy", LocalDate.now(), LocalDate.now().minusYears(60));
		ctrlPnlSetOnsetDate = new Ctrl_PnlSetDate("dd.MM.yyyy", LocalDate.now(), LocalDate.now().minusDays(7));
		pnlPhysician = new Pnl_Physician();
		pnlPhysician.addBirthDatePanel(ctrlPnlSetBirthDate.getPanel());
		pnlPhysician.addOnsetDatePanel(ctrlPnlSetOnsetDate.getPanel());
		dlgNewPhysician = new Dlg_NewPhysician(pnlPhysician);		
	} 
	
	protected void showDialog(Dialog.ModalityType modal) {
		dlgNewPhysician.setModalityType(modal);		
		dlgNewPhysician.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);		
		dlgNewPhysician.getPnlPhysician().getTxtSurname().setEnabled(true);
		dlgNewPhysician.getPnlPhysician().getTxtFirstname().setEnabled(true);
		dlgNewPhysician.getPnlPhysician().getTxtAlias().setEnabled(true);
		dlgNewPhysician.getPnlPhysician().getComboSex().setEnabled(true);
		dlgNewPhysician.getPnlPhysician().getComboTitle().setEnabled(true);
		dlgNewPhysician.pack();
		dlgNewPhysician.setVisible(true);
	}
}
