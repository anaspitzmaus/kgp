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

import com.rose.kgp.personnel.Ctrl_PnlNewPhysician.TitleModel;
import com.rose.kgp.useful.DateMethods;
import com.rose.kgp.useful.MyColor;

abstract class Ctrl_DlgStaff {

	protected Dlg_Staff dlgStaff;
	protected Tbl_PersonnelModel tblPersonnelModel;
	protected ArrayList<? extends Staff> staffMembers;
	protected Ctrl_PnlNewStaff ctrlPnlNewStaff;
	protected Boolean newStaff = false;
	protected enum Modus {NEW, UPDATE};
	protected Modus modus;
	protected Staff staffMember;
	
	public Ctrl_DlgStaff(Ctrl_PnlNewStaff ctrlPnlNewStaff, Dlg_Staff dlgStaff) {
		this.ctrlPnlNewStaff = ctrlPnlNewStaff;
		this.dlgStaff = dlgStaff;
		dlgStaff.contentPanel.add(ctrlPnlNewStaff.getPanel(), BorderLayout.SOUTH);
	}
	
	public void showDialog(){
		dlgStaff.setModal(true);
		dlgStaff.setVisible(true);
		dlgStaff.repaint();
	}
	
	abstract void setListener();
		
	
	
	abstract void removeListener();
	
	/**
	 * displays the selected staff member (selected in the table)
	 * @param staffMember
	 */
	protected void showSelectedStaff(Staff staffMember) {
		ctrlPnlNewStaff.staff = staffMember;
		//enable all textFields and dropDownFields
		ctrlPnlNewStaff.getPanel().getComboSex().setEnabled(true);		
		ctrlPnlNewStaff.getPanel().getTxtSurname().setEnabled(true);
		ctrlPnlNewStaff.getPanel().getTxtFirstname().setEnabled(true);	
		ctrlPnlNewStaff.getPanel().getTxtAlias().setEnabled(true);
		ctrlPnlNewStaff.getPanel().getBtnSetStaff().setEnabled(true);
		
		//fill in all textFields and comboBoxes
		Ctrl_PnlNewStaff.SexModel model = (Ctrl_PnlNewStaff.SexModel) ctrlPnlNewStaff.getPanel().getComboSex().getModel();
		//check the title of the selected physician and display at the comboBox
		for(int i = 0; i<model.getSize(); i++){
			if(model.getElementAt(i).equals(staffMember.getSex())){
				ctrlPnlNewStaff.getPanel().getComboSex().setSelectedIndex(i);
			}
		}	
		ctrlPnlNewStaff.getPanel().getTxtId().setText(staffMember.getId().toString());
		ctrlPnlNewStaff.getPanel().getTxtSurname().setText(staffMember.getSurname());
		ctrlPnlNewStaff.getPanel().getTxtFirstname().setText(staffMember.getFirstname());	
		ctrlPnlNewStaff.getPanel().getTxtAlias().setText(staffMember.getAlias());
		ctrlPnlNewStaff.ctrlPnlSetOnsetDate.setDate(staffMember.getOnset());		
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
			if(dlgStaff.getTblPersonnel().getSelectedRow() >= 0){				
				Staff staffSel = (Staff) dlgStaff.getTblPersonnel().getModel().getValueAt(dlgStaff.getTblPersonnel().getSelectedRow(), 0);
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
				staffMember.setBirthday(ctrlPnlNewStaff.getCtrlPnlSetBirthDate().getDate());
				staffMember.setOnset(ctrlPnlNewStaff.getCtrlPnlSetOnsetDate().getDate());				
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
			ctrlPnlNewStaff.getPanel().getTxtSurname().setBackground(new Color(myColor.getR(), myColor.getG(), myColor.getB()));
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
			ctrlPnlNewStaff.getPanel().getTxtFirstname().setBackground(new Color(myColor.getR(), myColor.getG(), myColor.getB()));
			return false;
			
		}
		
		
		
	}
	
	
	
}
