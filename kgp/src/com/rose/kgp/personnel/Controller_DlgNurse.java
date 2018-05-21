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

import com.rose.kgp.ui.Controller_PnlSetDate;
import com.rose.kgp.useful.DateMethods;

public class Controller_DlgNurse extends Controller_DlgStaff implements Observer{

	Dlg_Nurse dlgNurse;
	ArrayList<Nurse> nurses;
	Controller_PnlNewNurse conPnlNewNurse;
	Controller_PnlSetDate conPnlSetBirthDate, conPnlSetOnsetDate;
	Tbl_NurseModel tblNurseModel;
	
	@SuppressWarnings("unchecked")
	public Controller_DlgNurse() {
		dlgNurse = new Dlg_Nurse();
		nurses = SQL_SELECT.activeNurses(LocalDate.now());
		tblPersonnelModel = new Tbl_NurseModel((ArrayList<Nurse>) staff);
		dlgStaff.getTblPersonnel().setModel(tblPersonnelModel);
		dlgStaff.getTblPersonnel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
//		ColumnDateRenderer dateRenderer = new ColumnDateRenderer();
//		dlgPhysician.getTblPersonnel().getColumnModel().getColumn(0).setCellRenderer(Physician.class, new PhysicianCellRenderer());
//		this.getColumnModel().getColumn(1).setCellRenderer(dateRenderer);
//		this.getColumnModel().getColumn(2).setCellRenderer(notationRenderer);	
		dlgNurse.getTblPersonnel().setDefaultRenderer(Nurse.class, new NurseCellRenderer());
		dlgNurse.getTblPersonnel().setDefaultRenderer(LocalDate.class, new ColumnDateRenderer());
		
		//add the panel for a new nurse to the dialog
		conPnlSetBirthDate = new Controller_PnlSetDate("dd.MM.yyyy", LocalDate.now(), LocalDate.now().minusYears(60));
		conPnlSetOnsetDate = new Controller_PnlSetDate("dd.MM.yyyy", LocalDate.now(), LocalDate.now().minusDays(7));
		conPnlNewNurse = new Controller_PnlNewNurse(this.conPnlSetBirthDate, this.conPnlSetOnsetDate);
		dlgStaff.contentPanel.add(conPnlNewNurse.getPanel(), BorderLayout.SOUTH);
		conPnlNewNurse.addObserver(this); //add the Controller of this dialog as an observer to the controller of the included panel
		setListener();
		
	}
	
	void setListener(){
		NewNurseListener newNurseListener = new NewNurseListener();
		dlgNurse.addNewStaffListener(newNurseListener);
		TblRowSelectionListener tblRowSelectionListener = new TblRowSelectionListener();
		dlgStaff.addRowSelectionListener(tblRowSelectionListener);	
	}
	
	void removeListener(){
		
	}
	
	public void showDialog(){
		dlgNurse.setModal(true);
		dlgNurse.setVisible(true);
		dlgNurse.repaint();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
	class NewNurseListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			conPnlNewNurse.prepareForNewPhysician();			
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
