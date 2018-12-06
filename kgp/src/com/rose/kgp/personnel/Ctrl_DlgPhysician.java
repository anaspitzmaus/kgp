package com.rose.kgp.personnel;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import com.rose.kgp.db.SQL_INSERT;
import com.rose.kgp.db.SQL_SELECT;
import com.rose.kgp.personnel.Ctrl_PnlPhysician.TitleModel;
import com.rose.kgp.ui.Ctrl_PnlSetDate;




public class Ctrl_DlgPhysician extends Ctrl_DlgStaff{
	private SetNewPhysicianListener setNewPhysicianListener;
	
	
	@SuppressWarnings("unchecked")
	public Ctrl_DlgPhysician() {
		
		dialog = new Dlg_Physician(getCtrlPnlSetOnsetDate().getPanel(), getCtrlPnlSetBirthDate().getPanel());//instantiate the Dialog
			
		staffMembers = SQL_SELECT.activePhysicians(LocalDate.now());
		tblPersonnelModel = new Tbl_PhysicianModel((ArrayList<Physician>) staffMembers);
		dialog.getTblPersonnel().setModel(tblPersonnelModel);
		dialog.getTblPersonnel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		ColumnDateRenderer dateRenderer = new ColumnDateRenderer();
//		dlgPhysician.getTblPersonnel().getColumnModel().getColumn(0).setCellRenderer(Physician.class, new PhysicianCellRenderer());
//		this.getColumnModel().getColumn(1).setCellRenderer(dateRenderer);
//		this.getColumnModel().getColumn(2).setCellRenderer(notationRenderer);	
		dialog.getTblPersonnel().setDefaultRenderer(Physician.class, new PhysicianCellRenderer());
		dialog.getTblPersonnel().setDefaultRenderer(LocalDate.class, new ColumnDateRenderer());
		sexModel = new SexModel();
		dialog.getPnlStaff().getComboSex().setModel(sexModel);
		//add the panel for a new physician to the dialog
		
		
		setListener();
	}
	
	
	
	@Override void setListener(){
		NewPhysicianListener newPhysicianListener = new NewPhysicianListener();
		dialog.addNewStaffListener(newPhysicianListener);
		TblRowSelectionListener tblRowSelectionListener = new TblRowSelectionListener();
		dialog.addRowSelectionListener(tblRowSelectionListener);
		setNewPhysicianListener = new SetNewPhysicianListener();
		dialog.getPnlStaff().addSetNewStaffListener(setNewPhysicianListener);
	}
	
	
	@Override
	protected void showSelectedStaff(Staff physician){
		super.showSelectedStaff(physician);
		//set the comboTitle enabled
		((Pnl_Physician)dialog.getPnlStaff()).getComboTitle().setEnabled(true);
		TitleModel model = (TitleModel) ((Pnl_Physician)ctrlPnlStaff.getPanel()).getComboTitle().getModel();
		//check the title of the selected physician and display at the comboBox
		for(int i = 0; i<model.getSize(); i++){
			if(model.getElementAt(i).equals(((Physician)physician).getTitle())){
				((Pnl_Physician)ctrlPnlStaff.getPanel()).getComboTitle().setSelectedIndex(i);
			}
		}		
	}
	
	class NewPhysicianListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Ctrl_DlgNewPhysician ctrlDlgNewPhysician = new Ctrl_DlgNewPhysician();
			ctrlDlgNewPhysician.showDialog(ModalityType.APPLICATION_MODAL);
						
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
	
	

	
	@Override
	void removeListener() {
		// TODO Auto-generated method stub
		
	}
	
	class SetNewPhysicianListener extends SetNewStaffListener{

		@Override
		public void actionPerformed(ActionEvent e) {	
			
			if(dataReadyToStore()){
				
				//insert into database
				Integer id = SQL_INSERT.Physician((Physician)staffMember, LocalDate.now());
					if(id != null){
						staffMember.setId(id);
//						setChanged();
//						notifyObservers(staffMembers);//notify the Controller of the Dialog 'Controller_DlgPhysician'
					}
					
					removeListener(); //remove all listeners
					//empty all input fields
					ctrlPnlStaff.getPanel().getTxtSurname().setText("");
					ctrlPnlStaff.getPanel().getTxtFirstname().setText("");
					ctrlPnlStaff.getPanel().getTxtAlias().setText("");
					ctrlPnlStaff.getPanel().getComboSex().setSelectedIndex(-1);
					ctrlPnlStaff.getPanel().getComboSex().repaint();
					((Pnl_Physician) ctrlPnlStaff.getPanel()).getComboTitle().setSelectedIndex(-1);
					((Pnl_Physician) ctrlPnlStaff.getPanel()).getComboTitle().repaint();
					//set the id of the staff to null
					//staff.setId(null);
					//add all listeners to the input fields
					setListener();
					
				
			}
		}		
	}

	
	
	
	
	
}
