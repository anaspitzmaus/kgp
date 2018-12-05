package com.rose.kgp.personnel;

import java.awt.Dialog;
import java.time.LocalDate;

import javax.swing.JDialog;

import com.rose.kgp.ui.Ctrl_PnlSetDate;

class Ctrl_DlgNewPhysician {
	private Dlg_NewPhysician dlgNewPhysician;
	private Ctrl_PnlPhysician ctrlPnlNewPhysician;
	private Ctrl_PnlSetDate ctrlPnlSetBirthDate, ctrlPnlSetOnsetDate;
	
	public Ctrl_DlgNewPhysician() 
	{
		ctrlPnlSetBirthDate = new Ctrl_PnlSetDate("dd.MM.yyyy", LocalDate.now(), LocalDate.now().minusYears(60));
		ctrlPnlSetOnsetDate = new Ctrl_PnlSetDate("dd.MM.yyyy", LocalDate.now(), LocalDate.now().minusDays(7));
		ctrlPnlNewPhysician = new Ctrl_PnlPhysician(ctrlPnlSetBirthDate.getPanel(), ctrlPnlSetOnsetDate.getPanel());
		dlgNewPhysician = new Dlg_NewPhysician((Pnl_Physician) ctrlPnlNewPhysician.getPanel());		
	} 
	
	protected void showDialog(Dialog.ModalityType modal) {
		dlgNewPhysician.setModalityType(modal);		
		dlgNewPhysician.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);		
		dlgNewPhysician.getPnlNewPhysician().getTxtSurname().setEnabled(true);
		dlgNewPhysician.getPnlNewPhysician().getTxtFirstname().setEnabled(true);
		dlgNewPhysician.getPnlNewPhysician().getTxtAlias().setEnabled(true);
		dlgNewPhysician.getPnlNewPhysician().getComboSex().setEnabled(true);
		dlgNewPhysician.getPnlNewPhysician().getComboTitle().setEnabled(true);
		dlgNewPhysician.pack();
		dlgNewPhysician.setVisible(true);
	}
}
