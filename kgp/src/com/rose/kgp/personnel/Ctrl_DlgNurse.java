package com.rose.kgp.personnel;


import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;







import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import com.rose.kgp.db.SQL_SELECT;
import com.rose.kgp.db.SQL_UPDATE;
import com.rose.kgp.ui.Ctrl_PnlSetDate;


public class Ctrl_DlgNurse extends Ctrl_DlgStaff {

	
	@SuppressWarnings("unchecked")
	public Ctrl_DlgNurse() {
		
		
		dialog = new Dlg_Nurse(getCtrlPnlSetOnsetDate().getPanel(), getCtrlPnlSetBirthDate().getPanel());//instantiate the Dialog
		
		staffMembers = SQL_SELECT.activeNurses(LocalDate.now());
		tblPersonnelModel = new Tbl_NurseModel((ArrayList<Nurse>) staffMembers);
		dialog.getTblPersonnel().setModel(tblPersonnelModel);
		dialog.getTblPersonnel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
//		ColumnDateRenderer dateRenderer = new ColumnDateRenderer();
//		dlgPhysician.getTblPersonnel().getColumnModel().getColumn(0).setCellRenderer(Physician.class, new PhysicianCellRenderer());
//		this.getColumnModel().getColumn(1).setCellRenderer(dateRenderer);
//		this.getColumnModel().getColumn(2).setCellRenderer(notationRenderer);	
		dialog.getTblPersonnel().setDefaultRenderer(Nurse.class, new NurseCellRenderer());
		dialog.getTblPersonnel().setDefaultRenderer(LocalDate.class, new ColumnDateRenderer());
		dialog.getPnlStaff().getComboSex().setModel(sexModel);
		dialog.setSexComboRenderer(sexComboRenderer);//need to be set here, as the super class is abstract (the renderer is initialized in the super class)
		updateStaffMemberListener = new UpdateNurseListener();		
		setListener();
		
	}
	
	
	
	
	
		
//	class NewNurseListener implements ActionListener{
//
//		@Override
//		public void actionPerformed(ActionEvent arg0) {
//			((Ctrl_PnlNurse)ctrlPnlStaff).prepareForNewNurse();	
//			setModus(Modus.NEW);
//		}
//		
//	}
	
		
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
					if(nurse.getId() != null){
						setText(nurse.getId().toString());
					}else{
						setText("null");
					}
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
		        	
		           //set the table rows with alternating background colors
		            	if((row % 2) == 0){
		            		setBackground(Color.WHITE);
		            	}else{
		            		setBackground(Color.LIGHT_GRAY);
		            	}
		            
		        }
	        
	         
	        return this;
	    }
	}
	
	class UpdateNurseListener extends UpdateStaffMemberListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(dataReadyToStore()){
				
				//update the database
				SQL_UPDATE.Nurse((Nurse)staffMemberUpdate);
				tblPersonnelModel.fireTableDataChanged();
				removeListener(); //remove all listeners
				//empty all input fields
				dialog.getPnlStaff().getTxtSurname().setText("");
				dialog.getPnlStaff().getTxtFirstname().setText("");
				dialog.getPnlStaff().getTxtAlias().setText("");
				dialog.getPnlStaff().getComboSex().setSelectedIndex(-1);
				dialog.getPnlStaff().getComboSex().repaint();
				getCtrlPnlSetBirthDate().setDate(null);
				getCtrlPnlSetOnsetDate().setDate(null);
				
				
				setListener();
				setFieldsDisabled();
				
			}
			
		}
		
	}
	
	

}
