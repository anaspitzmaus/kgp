package com.rose.kgp.personnel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

import com.rose.kgp.personnel.Ctrl_PnlPhysician.TitleModel;
import com.rose.kgp.useful.DateMethods;
import com.rose.kgp.useful.MyColor;

abstract class Ctrl_DlgStaff {

	protected Dlg_Staff dialog;
	protected Tbl_PersonnelModel tblPersonnelModel;
	protected ArrayList<? extends Staff> staffMembers;
	protected Ctrl_PnlStaff ctrlPnlStaff;
	protected Boolean newStaff = false;
	protected enum Modus {NEW, UPDATE};
	protected Modus modus;
	protected Staff staffMember;
	
	protected void setDialog(Dlg_Staff dialog){
		this.dialog = dialog;
		dialog.contentPanel.add(this.ctrlPnlStaff.getPanel(), BorderLayout.SOUTH);
	}
	
	public Ctrl_DlgStaff(Ctrl_PnlStaff ctrlPnlNewStaff) {
		this.ctrlPnlStaff = ctrlPnlNewStaff;		
	}
	
	public void showDialog(){
		if(this.dialog instanceof Dlg_Staff){
			dialog.setModal(true);
			dialog.setVisible(true);
			dialog.repaint();
		}
	}
	
	abstract void setListener();
		
	
	
	abstract void removeListener();
	
	/**
	 * displays the selected staff member (selected in the table)
	 * @param staffMember
	 */
	protected void showSelectedStaff(Staff staffMember) {
		ctrlPnlStaff.staff = staffMember;
		//enable all textFields and dropDownFields
		ctrlPnlStaff.getPanel().getComboSex().setEnabled(true);		
		ctrlPnlStaff.getPanel().getTxtSurname().setEnabled(true);
		ctrlPnlStaff.getPanel().getTxtFirstname().setEnabled(true);	
		ctrlPnlStaff.getPanel().getTxtAlias().setEnabled(true);
		ctrlPnlStaff.getPanel().getBtnSetStaff().setEnabled(true);
		
		//fill in all textFields and comboBoxes
		Ctrl_PnlStaff.SexModel model = (Ctrl_PnlStaff.SexModel) ctrlPnlStaff.getPanel().getComboSex().getModel();
		//check the title of the selected physician and display at the comboBox
		for(int i = 0; i<model.getSize(); i++){
			if(model.getElementAt(i).equals(staffMember.getSex())){
				ctrlPnlStaff.getPanel().getComboSex().setSelectedIndex(i);
			}
		}	
		ctrlPnlStaff.getPanel().getTxtId().setText(staffMember.getId().toString());
		ctrlPnlStaff.getPanel().getTxtSurname().setText(staffMember.getSurname());
		ctrlPnlStaff.getPanel().getTxtFirstname().setText(staffMember.getFirstname());	
		ctrlPnlStaff.getPanel().getTxtAlias().setText(staffMember.getAlias());
		//ctrlPnlNewStaff.ctrlPnlSetOnsetDate.setDate(staffMember.getOnset());		
	}
	
	/**
	 * set the modus of the Dialog 
	 * modus can be to create a new staff member (NEW)
	 * or to update an already existing staff member (UPDATE)
	 * @param modus
	 */
	protected void setModus(Modus modus) {
		this.modus = modus;		
	}
	
	@SuppressWarnings("unchecked")
	public void addStaff(Staff staff) {
		((ArrayList<Staff>)tblPersonnelModel.getStaff()).add((Staff)staff);
		tblPersonnelModel.fireTableDataChanged();
		
	}
	/**
	 * listener class for selecting a table row
	 * @author Ekkehard Rose
	 *
	 */
	class TblRowSelectionListener implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			if(dialog.getTblPersonnel().getSelectedRow() >= 0){				
				Staff staffSel = (Staff) dialog.getTblPersonnel().getModel().getValueAt(dialog.getTblPersonnel().getSelectedRow(), 0);
				showSelectedStaff(staffSel);
				setModus(Modus.UPDATE);			
			 }
			
		}	
		
	}
	
	/**
	 * renderer for the columns that show a date
	 * @author Ekkehard Rose
	 *
	 */
	class ColumnDateRenderer extends DefaultTableCellRenderer {
		
		private static final long serialVersionUID = 3498636126051341094L;
		SimpleDateFormat simpleDateFormat;
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
			 
			 simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
			 if(value != null){
				 value = simpleDateFormat.format(DateMethods.ConvertLocalDateToDate((LocalDate)value));
			 }			 
			 return super.getTableCellRendererComponent(table, value, isSelected,
		                hasFocus, row, column);
		}
	}
	
	abstract class SetNewStaffListener implements ActionListener{
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			
		}
		/**
		 * check if data of new staff member are valid for being stored in database
		 * a new staff member must not have an id and need to have a valid surname and a valid firstname
		 * @return true if data are valid, false if not
		 */
		protected Boolean dataReadyToStore(){
			if(checkSurname() && checkFirstname()){//if a new staff member has to be added
				staffMember.setBirthday(ctrlPnlStaff.getCtrlPnlSetBirthDate().getDate());
				staffMember.setOnset(ctrlPnlStaff.getCtrlPnlSetOnsetDate().getDate());				
				return true;					
			}else{
				return false;
			}
		}
		/**
		 * check if surname of the new staff member is valid (need to have at least 1 character)
		 * @return true if surname is valid, else return false
		 */
		private Boolean checkSurname(){
			if(staffMember.getSurname().length() > 0){
				return true;
			}
			MyColor myColor = MyColor.RED;
			ctrlPnlStaff.getPanel().getTxtSurname().setBackground(new Color(myColor.getR(), myColor.getG(), myColor.getB()));
			return false;
			
		}
		
		/**
		 * check if firstname of the new staff member is valid (need to have at least 1 character)
		 * @return true if firstname is valid, else return false
		 */
		private Boolean checkFirstname(){
			if(staffMember.getFirstname().length() > 0){
				return true;
			}
			MyColor myColor = MyColor.RED;
			ctrlPnlStaff.getPanel().getTxtFirstname().setBackground(new Color(myColor.getR(), myColor.getG(), myColor.getB()));
			return false;
			
		}
		
		
		
	}
	
	
	
}
