package com.rose.kgp.personnel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import com.rose.kgp.db.SQL_SELECT;
import com.rose.kgp.personnel.Ctrl_DlgStaff.Modus;
import com.rose.kgp.ui.Ctrl_PnlSetDate;
import com.rose.kgp.useful.DateMethods;

public class Ctrl_DlgNurse extends Ctrl_DlgStaff {

	//Dlg_Nurse dlgNurse;
	//ArrayList<Nurse> nurses;
	//Controller_PnlNewNurse conPnlNewNurse;
	Ctrl_PnlSetDate conPnlSetBirthDate, conPnlSetOnsetDate;
	//Tbl_NurseModel tblNurseModel;
	
	@SuppressWarnings("unchecked")
	public Ctrl_DlgNurse(Ctrl_PnlNewNurse ctrlPnlNewNurse) {
		super(ctrlPnlNewNurse, new Dlg_Nurse());
		
		
		staffMembers = SQL_SELECT.activeNurses(LocalDate.now());
		tblPersonnelModel = new Tbl_NurseModel((ArrayList<Nurse>) staffMembers);
		dlgStaff.getTblPersonnel().setModel(tblPersonnelModel);
		dlgStaff.getTblPersonnel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
//		ColumnDateRenderer dateRenderer = new ColumnDateRenderer();
//		dlgPhysician.getTblPersonnel().getColumnModel().getColumn(0).setCellRenderer(Physician.class, new PhysicianCellRenderer());
//		this.getColumnModel().getColumn(1).setCellRenderer(dateRenderer);
//		this.getColumnModel().getColumn(2).setCellRenderer(notationRenderer);	
		dlgStaff.getTblPersonnel().setDefaultRenderer(Nurse.class, new NurseCellRenderer());
		dlgStaff.getTblPersonnel().setDefaultRenderer(LocalDate.class, new ColumnDateRenderer());
		
		//add the panel for a new nurse to the dialog
		conPnlSetBirthDate = new Ctrl_PnlSetDate("dd.MM.yyyy", LocalDate.now(), LocalDate.now().minusYears(60));
		conPnlSetOnsetDate = new Ctrl_PnlSetDate("dd.MM.yyyy", LocalDate.now(), LocalDate.now().minusDays(7));
		ctrlPnlNewStaff = new Ctrl_PnlNewNurse(this.conPnlSetBirthDate, this.conPnlSetOnsetDate);
		dlgStaff.contentPanel.add(ctrlPnlNewStaff.getPanel(), BorderLayout.SOUTH);
		
		setListener();
		
	}
	
	void setListener(){
		NewNurseListener newNurseListener = new NewNurseListener();
		dlgStaff.addNewStaffListener(newNurseListener);
		TblRowSelectionListener tblRowSelectionListener = new TblRowSelectionListener();
		dlgStaff.addRowSelectionListener(tblRowSelectionListener);	
	}
	
	void removeListener(){
		
	}
	
	
	
	class NewNurseListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			((Ctrl_PnlNewNurse)ctrlPnlNewStaff).prepareForNewNurse();	
			setModus(Modus.NEW);
		}
		
	}
	
		
	class NurseCellRenderer extends DefaultTableCellRenderer{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -875474522275935839L;

		public Component getTableCellRendererComponent(JTable table, Object value,
	            boolean isSelected, boolean hasFocus, int row, int column) {
			if (value instanceof Nurse) {
	            Nurse nurse = (Nurse) value;
	            switch (column) {
				case 0:
					setText(nurse.getId().toString());
					break;
				case 1:
					setText(nurse.getSurname());
					break;
				case 2:
					setText(nurse.getFirstname());
					break;	
				default:
					setText("");
					break;
				}
	            
	        }
	         
	        
	        if (isSelected) {
	            setBackground(table.getSelectionBackground());
	        } else {
	        	if(row == 1){//change to the member logged in
	            	setBackground(Color.YELLOW); //the logged in member is to be shown with yellow back color
	            }else{ //set the table rows with alternating background colors
	            	if((row % 2) == 0){
	            		setBackground(Color.WHITE);
	            	}else{
	            		setBackground(Color.LIGHT_GRAY);
	            	}
	            }
	        }
	        
	         
	        return this;
	    }
	}
	
	

}
