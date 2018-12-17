package com.rose.kgp.personnel;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.event.ListDataListener;

import com.rose.kgp.db.SQL_INSERT;
import com.rose.kgp.db.SQL_UPDATE;
import com.rose.kgp.personnel.Ctrl_DlgStaff.UpdateStaffMemberListener;
import com.rose.kgp.ui.Ctrl_PnlSetDate;

class Ctrl_DlgNewPhysician extends Ctrl_DlgNewStaff{
	private Pnl_Physician pnlPhysician;
	private TitleListener titleListener;
	protected Tbl_PhysicianModel tblModel;
	private TitleModel titleModel;
	
	public Ctrl_DlgNewPhysician(Tbl_PhysicianModel tblModel) 
	{	
		this.tblModel = tblModel;
		staffMember = new Physician(null, null);
		pnlPhysician = new Pnl_Physician();
		pnlPhysician.addBirthDatePanel(ctrlPnlSetBirthDate.getPanel());
		pnlPhysician.addOnsetDatePanel(ctrlPnlSetOnsetDate.getPanel());
		dialog = new Dlg_NewPhysician(pnlPhysician);
		dialog.getPnlStaff().getComboSex().setRenderer(sexComboRenderer);
		dialog.getPnlStaff().getComboSex().setModel(sexModel);
		titleModel = new TitleModel();
		((Pnl_Physician)dialog.getPnlStaff()).getComboTitle().setModel(titleModel);
		setListener();
	} 
	
	protected void showDialog(Dialog.ModalityType modal) {
		dialog.setModalityType(modal);		
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);		
		dialog.getPnlStaff().getTxtSurname().setEnabled(true);
		dialog.getPnlStaff().getTxtFirstname().setEnabled(true);
		dialog.getPnlStaff().getTxtAlias().setEnabled(true);
		dialog.getPnlStaff().getComboSex().setEnabled(true);
		((Pnl_Physician)dialog.getPnlStaff()).getComboTitle().setEnabled(true);
		dialog.pack();
		dialog.setVisible(true);
	}

	@Override
	void setListener() {
		sexListener = new SexListener();
		dialog.getPnlStaff().addSexListener(sexListener);
		titleListener = new TitleListener();
		((Pnl_Physician)dialog.getPnlStaff()).addTitleListener(titleListener);
		surnameListener = new SurnameListener(staffMember);
		dialog.getPnlStaff().addSurnameListener(surnameListener);
		firstnameListener = new FirstnameListener();
		dialog.getPnlStaff().addFirstnameListener(firstnameListener);
		aliasListener = new AliasListener();
		dialog.getPnlStaff().addAliasListener(aliasListener);
		newStaffMemberListener = new NewPhysicianListener();
		dialog.getPnlStaff().addBtnStaffMemberListener(newStaffMemberListener);
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
	
	/**
	 * model for the comboBox that shows the titles of a new physician
	 * @author Ekkehard Rose
	 *
	 */
	class TitleModel extends AbstractListModel<String> implements ComboBoxModel<String>{

		/**
		 * 
		 */
		private static final long serialVersionUID = -5723909430414256587L;
		String[] titles = {"Dr.", "Dr. med.", "Prof."};
		String selection = null;
		
		@Override
		public void addListDataListener(ListDataListener arg0) {
			// TODO Auto-generated method stub			
		}

		@Override
		public String getElementAt(int index) {
			return titles[index];
		}

		@Override
		public int getSize() {
			return titles.length;
		}

		@Override
		public void removeListDataListener(ListDataListener arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Object getSelectedItem() {
			return selection;
		}

		@Override
		public void setSelectedItem(Object title) {
			selection= (String) title;
			
		}
		
	}	
	
	class NewPhysicianListener extends NewStaffMemberListener{

				
		@SuppressWarnings("unchecked")
		@Override
		public void actionPerformed(ActionEvent e) {	
			
			if(dataReadyToStore()){				
				//update the database
				Integer id = SQL_INSERT.Physician((Physician)staffMember, LocalDate.now());
				if(id != null){
					((ArrayList<Physician>)tblModel.getStaffMembers()).add((Physician) staffMember);
					tblModel.fireTableDataChanged();
					dialog.dispose();
				}
				//empty all input fields
				
//				dialog.getPnlStaff().getTxtSurname().setText("");
//				dialog.getPnlStaff().getTxtFirstname().setText("");
//				dialog.getPnlStaff().getTxtAlias().setText("");
//				dialog.getPnlStaff().getComboSex().setSelectedIndex(-1);
//				dialog.getPnlStaff().getComboSex().repaint();
//				((Pnl_Physician) dialog.getPnlStaff()).getComboTitle().setSelectedIndex(-1);
//				((Pnl_Physician) dialog.getPnlStaff()).getComboTitle().repaint();
//				ctrlPnlSetBirthDate.setDate(null);
//				ctrlPnlSetOnsetDate.setDate(null);	
//				staffMember = new Physician(null);
			}
		}		
	}
}
