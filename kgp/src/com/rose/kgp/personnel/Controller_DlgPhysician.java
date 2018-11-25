package com.rose.kgp.personnel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import javax.swing.table.DefaultTableCellRenderer;

import com.rose.kgp.db.SQL_SELECT;
import com.rose.kgp.ui.Controller_PnlSetDate;



public class Controller_DlgPhysician extends Controller_DlgStaff implements Observer{
	//Dlg_Physician dlgPhysician;
	//ArrayList<Physician> physicians;
	//Controller_PnlNewPhysician conPnlNewPhysician;
	Controller_PnlSetDate conPnlSetBirthDate, conPnlSetOnsetDate;
	//Tbl_PhysicianModel tblPhysicianModel;
	
	
	@SuppressWarnings("unchecked")
	public Controller_DlgPhysician() {
		dlgStaff = new Dlg_Physician();
		staff = SQL_SELECT.activePhysicians(LocalDate.now());
		tblPersonnelModel = new Tbl_PhysicianModel((ArrayList<Physician>) staff);
		dlgStaff.getTblPersonnel().setModel(tblPersonnelModel);
		dlgStaff.getTblPersonnel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		ColumnDateRenderer dateRenderer = new ColumnDateRenderer();
//		dlgPhysician.getTblPersonnel().getColumnModel().getColumn(0).setCellRenderer(Physician.class, new PhysicianCellRenderer());
//		this.getColumnModel().getColumn(1).setCellRenderer(dateRenderer);
//		this.getColumnModel().getColumn(2).setCellRenderer(notationRenderer);	
		dlgStaff.getTblPersonnel().setDefaultRenderer(Physician.class, new PhysicianCellRenderer());
		dlgStaff.getTblPersonnel().setDefaultRenderer(LocalDate.class, new ColumnDateRenderer());
		
		//add the panel for a new physician to the dialog
		conPnlSetBirthDate = new Controller_PnlSetDate("dd.MM.yyyy", LocalDate.now(), LocalDate.now().minusYears(60));
		conPnlSetOnsetDate = new Controller_PnlSetDate("dd.MM.yyyy", LocalDate.now(), LocalDate.now().minusDays(7));
		conPnlNewStaff = new Controller_PnlNewPhysician(this.conPnlSetBirthDate, this.conPnlSetOnsetDate);
		dlgStaff.contentPanel.add(conPnlNewStaff.getPanel(), BorderLayout.SOUTH);
		conPnlNewStaff.addObserver(this); //add the Controller of this dialog as an observer to the controller of the included panel
		setListener();
	}
	
	
	
	@Override void setListener(){
		NewPhysicianListener newPhysicianListener = new NewPhysicianListener();
		dlgStaff.addNewStaffListener(newPhysicianListener);
		TblRowSelectionListener tblRowSelectionListener = new TblRowSelectionListener();
		dlgStaff.addRowSelectionListener(tblRowSelectionListener);		
	}
	
	
	
	protected void showSelectedPhysician(Physician physician){
		super.showSelectedStaff(physician);
		((Pnl_NewPhysician)conPnlNewStaff.getPanel()).getComboTitle().setEnabled(true);		
	}
	
	class NewPhysicianListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			((Controller_PnlNewPhysician)conPnlNewStaff).prepareForNewPhysician();
			setModus(Modus.NEW);
			newStaff = true;
		}
		
	}
	
	
	
	public class PhysicianCellRenderer extends DefaultTableCellRenderer{
		
		private static final long serialVersionUID = 858752410581979184L;

		public Component getTableCellRendererComponent(JTable table, Object value,
	            boolean isSelected, boolean hasFocus, int row, int column) {
			if (value instanceof Physician) {
	            Physician physician = (Physician) value;
	            switch (column) {
				case 0:
					if(physician.getId() != null){
						setText(physician.getId().toString());
					}else{
						setText("null");
					}
					break;
				case 1:
					setText(physician.getTitle());
					break;
				case 2:
					setText(physician.getSurname());
					break;
				case 3:
					setText(physician.getFirstname());
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
	
	

	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable o, Object physician) {
		//Tbl_PhysicianModel model = (Tbl_PhysicianModel) dlgPhysician.getTblPersonnel().getModel();
		((ArrayList<Physician>)tblPersonnelModel.getStaff()).add((Physician)physician);
		tblPersonnelModel.fireTableDataChanged();
		
	}



	@Override
	void removeListener() {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
	
}
