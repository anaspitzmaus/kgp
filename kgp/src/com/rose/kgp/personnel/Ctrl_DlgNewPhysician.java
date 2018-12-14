package com.rose.kgp.personnel;

import java.awt.Dialog;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;

import javax.swing.JComboBox;
import javax.swing.JDialog;

import com.rose.kgp.ui.Ctrl_PnlSetDate;

class Ctrl_DlgNewPhysician extends Ctrl_DlgNewStaff{
	private Dlg_NewPhysician dlgNewPhysician;	
	private Pnl_Physician pnlPhysician;
	private TitleListener titleListener;
	
	public Ctrl_DlgNewPhysician() 
	{
		ctrlPnlSetBirthDate = new Ctrl_PnlSetDate("dd.MM.yyyy", LocalDate.now(), LocalDate.now().minusYears(60));
		ctrlPnlSetOnsetDate = new Ctrl_PnlSetDate("dd.MM.yyyy", LocalDate.now(), LocalDate.now().minusDays(7));
		pnlPhysician = new Pnl_Physician();
		pnlPhysician.addBirthDatePanel(ctrlPnlSetBirthDate.getPanel());
		pnlPhysician.addOnsetDatePanel(ctrlPnlSetOnsetDate.getPanel());
		setListener();
		dlgNewPhysician = new Dlg_NewPhysician(pnlPhysician);		
	} 
	
	protected void showDialog(Dialog.ModalityType modal) {
		dlgNewPhysician.setModalityType(modal);		
		dlgNewPhysician.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);		
		dlgNewPhysician.getPnlStaff().getTxtSurname().setEnabled(true);
		dlgNewPhysician.getPnlStaff().getTxtFirstname().setEnabled(true);
		dlgNewPhysician.getPnlStaff().getTxtAlias().setEnabled(true);
		dlgNewPhysician.getPnlStaff().getComboSex().setEnabled(true);
		((Pnl_Physician)dlgNewPhysician.getPnlStaff()).getComboTitle().setEnabled(true);
		dlgNewPhysician.pack();
		dlgNewPhysician.setVisible(true);
	}

	@Override
	void setListener() {
		sexListener = new SexListener();
		dlgNewPhysician.getPnlStaff().addSexListener(sexListener);
		titleListener = new TitleListener();
		((Pnl_Physician)dlgNewPhysician.getPnlStaff()).addTitleListener(titleListener);
		surnameListener = new SurnameListener();
		dlgNewPhysician.getPnlStaff().addSurnameListener(surnameListener);
		firstnameListener = new FirstnameListener();
		dlgNewPhysician.getPnlStaff().addFirstnameListener(firstnameListener);
		aliasListener = new AliasListener();
		dlgNewPhysician.getPnlStaff().addAliasListener(aliasListener);
	}
	
	class TitleListener implements ItemListener{

		@SuppressWarnings("unchecked")
		@Override
		public void itemStateChanged(ItemEvent evt) {
			JComboBox<String> comboTitle;
			if(evt.getSource() instanceof JComboBox<?>){
				comboTitle = (JComboBox<String>) evt.getSource();
				((Physician) staffMember).setTitle((String) comboTitle.getModel().getSelectedItem());
			}			
		}		
	}
}
