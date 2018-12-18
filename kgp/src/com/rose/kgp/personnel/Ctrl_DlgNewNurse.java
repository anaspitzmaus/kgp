package com.rose.kgp.personnel;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JDialog;

import com.rose.kgp.db.SQL_INSERT;



public class Ctrl_DlgNewNurse extends Ctrl_DlgNewStaff {
	private Pnl_Nurse pnlNurse;
	
	
	public Ctrl_DlgNewNurse(Tbl_NurseModel tblModel) {
		this.tblModel = tblModel;
		staffMember = new Nurse(null, null);
		pnlNurse = new Pnl_Nurse();
		pnlNurse.addBirthDatePanel(ctrlPnlSetBirthDate.getPanel());
		pnlNurse.addOnsetDatePanel(ctrlPnlSetOnsetDate.getPanel());
		dialog = new Dlg_NewNurse(pnlNurse);
		dialog.getPnlStaff().getComboSex().setRenderer(sexComboRenderer);
		dialog.getPnlStaff().getComboSex().setModel(sexModel);
		setListener();
	}
	
	protected void showDialog(Dialog.ModalityType modal) {
		dialog.setModalityType(modal);		
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);		
		dialog.getPnlStaff().getTxtSurname().setEnabled(true);
		dialog.getPnlStaff().getTxtFirstname().setEnabled(true);
		dialog.getPnlStaff().getTxtAlias().setEnabled(true);
		dialog.getPnlStaff().getComboSex().setEnabled(true);
		dialog.pack();
		dialog.setVisible(true);
	}
	
	@Override
	void setListener() {
		sexListener = new SexListener();
		dialog.getPnlStaff().addSexListener(sexListener);
		surnameListener = new SurnameListener(staffMember);
		dialog.getPnlStaff().addSurnameListener(surnameListener);
		firstnameListener = new FirstnameListener();
		dialog.getPnlStaff().addFirstnameListener(firstnameListener);
		aliasListener = new AliasListener();
		dialog.getPnlStaff().addAliasListener(aliasListener);
		newStaffMemberListener = new NewNurseListener();
		dialog.getPnlStaff().addBtnStaffMemberListener(newStaffMemberListener);
	}
	
	class NewNurseListener extends NewStaffMemberListener{

		
		@SuppressWarnings("unchecked")
		@Override
		public void actionPerformed(ActionEvent e) {	
			
			if(dataReadyToStore()){				
				//update the database
				Integer id = SQL_INSERT.Nurse((Nurse)staffMember, LocalDate.now());
				if(id != null){
					((ArrayList<Nurse>)tblModel.getStaffMembers()).add((Nurse) staffMember);
					tblModel.fireTableDataChanged();
					dialog.dispose();
				}
			}
		}		
	}

}
