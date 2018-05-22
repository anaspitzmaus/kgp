package com.rose.kgp.personnel;

import java.awt.Component;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.JTable;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

import com.rose.kgp.useful.DateMethods;

abstract class Controller_DlgStaff {

	protected Dlg_Staff dlgStaff;
	protected Tbl_PersonnelModel tblPersonnelModel;
	protected ArrayList<? extends Staff> staff;
	protected Controller_PnlNewStaff conPnlNewStaff;
	
	public void showDialog(){
		dlgStaff.setModal(true);
		dlgStaff.setVisible(true);
		dlgStaff.repaint();
	}
	
	abstract void setListener();
		
	
	
	abstract void removeListener();
	
	protected void showSelectedStaff(Staff staff) {
		conPnlNewStaff.staff = staff;
		conPnlNewStaff.getPanel().getTxtId().setText(staff.getId().toString());
		conPnlNewStaff.getPanel().getComboSex().setEnabled(true);		
		conPnlNewStaff.getPanel().getTxtSurname().setEnabled(true);
		conPnlNewStaff.getPanel().getTxtFirstname().setEnabled(true);
		conPnlNewStaff.conPnlSetOnsetDate.setPnlEnabled(true);
		conPnlNewStaff.getPanel().getTxtSurname().setText(staff.getSurname());
		conPnlNewStaff.getPanel().getTxtFirstname().setText(staff.getFirstname());		
		conPnlNewStaff.conPnlSetOnsetDate.setDate(staff.getOnset());
		
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
	
	
	
}
