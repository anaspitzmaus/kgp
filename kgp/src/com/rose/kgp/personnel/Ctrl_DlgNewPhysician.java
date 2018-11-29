package com.rose.kgp.personnel;

import java.awt.Dialog;
import java.time.LocalDate;

import javax.swing.JDialog;

import com.rose.kgp.ui.Ctrl_PnlSetDate;

class Ctrl_DlgNewPhysician {
	private Dlg_NewPhysician dlgNewPhysician;
	private Ctrl_PnlNewPhysician ctrlPnlNewPhysician;
	private Ctrl_PnlSetDate ctrlPnlSetBirthDate, ctrlPnlSetOnsetDate;
	
	public Ctrl_DlgNewPhysician() 
	{
		ctrlPnlSetBirthDate = new Ctrl_PnlSetDate("dd.MM.yyyy", LocalDate.now(), LocalDate.now().minusYears(60));
		ctrlPnlSetOnsetDate = new Ctrl_PnlSetDate("dd.MM.yyyy", LocalDate.now(), LocalDate.now().minusDays(7));
		ctrlPnlNewPhysician = new Ctrl_PnlNewPhysician(ctrlPnlSetBirthDate, ctrlPnlSetOnsetDate);
		dlgNewPhysician = new Dlg_NewPhysician((Pnl_NewPhysician) ctrlPnlNewPhysician.getPanel());
		
	} 
	
	protected void showDialog(Dialog.ModalityType modal) {
		dlgNewPhysician.setModalityType(modal);
		dlgNewPhysician.setVisible(true);
		dlgNewPhysician.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dlgNewPhysician.pack();
	}
}
