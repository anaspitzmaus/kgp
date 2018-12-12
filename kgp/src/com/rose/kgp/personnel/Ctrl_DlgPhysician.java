package com.rose.kgp.personnel;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListDataListener;
import javax.swing.table.DefaultTableCellRenderer;

import com.rose.kgp.db.SQL_INSERT;
import com.rose.kgp.db.SQL_SELECT;
import com.rose.kgp.db.SQL_UPDATE;





public class Ctrl_DlgPhysician extends Ctrl_DlgStaff{
	private TitleModel titleModel;
	private TitleListener titleListener;
	
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
		dialog.getPnlStaff().getComboSex().setModel(sexModel);
		titleModel = new TitleModel();
		((Pnl_Physician)dialog.getPnlStaff()).getComboTitle().setModel(titleModel);
		dialog.setSexComboRenderer(sexComboRenderer);//need to be set here, as the super class is abstract (the renderer is initialized in the super class)
		titleListener = new TitleListener();		
		setListener();
	}
	
	@Override
	protected void setFieldsDisabled(){
		dialog.getPnlStaff().getComboSex().setEnabled(false);
		((Pnl_Physician)dialog.getPnlStaff()).getComboTitle().setEnabled(false);
		dialog.getPnlStaff().getTxtAlias().setEnabled(false);
		dialog.getPnlStaff().getTxtFirstname().setEnabled(false);
		dialog.getPnlStaff().getTxtSurname().setEnabled(false);
		dialog.getPnlStaff().getTxtId().setEnabled(false);
		getCtrlPnlSetBirthDate().setPnlEnabled(false);
		getCtrlPnlSetOnsetDate().setPnlEnabled(false);
	}
	
	
	
	@Override
	protected void setListener(){
		super.setListener();//set the basic listeners
		//add the extra listeners of the dialog
		
		((Pnl_Physician)dialog.getPnlStaff()).addTitleListener(titleListener);
		TblRowSelectionListener tblRowSelectionListener = new TblRowSelectionListener();
		dialog.addRowSelectionListener(tblRowSelectionListener);		
		UpdateStaffMemberListener updateStaffMemberListener = new UpdatePhysicianListener();		
		dialog.getPnlStaff().addUpdateStaffMemberListener(updateStaffMemberListener);
		
	}
	
	
	
	@Override
	protected void showSelectedStaff(){
		super.showSelectedStaff();
		//set the comboTitle enabled
		((Pnl_Physician)dialog.getPnlStaff()).getComboTitle().setEnabled(true);
		TitleModel model = (TitleModel) ((Pnl_Physician)dialog.getPnlStaff()).getComboTitle().getModel();
		//check the title of the selected physician and display at the comboBox
		for(int i = 0; i<model.getSize(); i++){
			if(model.getElementAt(i).equals(((Physician)staffMemberSel).getTitle())){
				((Pnl_Physician)dialog.getPnlStaff()).getComboTitle().setSelectedIndex(i);
			}
		}		
	}
	
//	class NewPhysicianListener implements ActionListener{
//
//		@Override
//		public void actionPerformed(ActionEvent arg0) {
//			Ctrl_DlgNewPhysician ctrlDlgNewPhysician = new Ctrl_DlgNewPhysician();
//			ctrlDlgNewPhysician.showDialog(ModalityType.APPLICATION_MODAL);
//						
//		}		
//	}
	
	
	
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
		dialog.getPnlStaff().removeSurnameListener(surnameListener);
		dialog.getPnlStaff().removeFirstnameListener(firstnameListener);
		dialog.getPnlStaff().removeAliasListener(aliasListener);
		dialog.getPnlStaff().removeSexListener(sexListener);
		((Pnl_Physician)dialog.getPnlStaff()).removeTitleListener(titleListener);
	}
	
	class UpdatePhysicianListener extends UpdateStaffMemberListener{

		@Override
		public void actionPerformed(ActionEvent e) {	
			
			if(dataReadyToStore()){
				
				//insert into database
				SQL_UPDATE.Physician((Physician)staffMemberUpdate);
				tblPersonnelModel.fireTableDataChanged();
				removeListener(); //remove all listeners
				//empty all input fields
				dialog.getPnlStaff().getTxtSurname().setText("");
				dialog.getPnlStaff().getTxtFirstname().setText("");
				dialog.getPnlStaff().getTxtAlias().setText("");
				dialog.getPnlStaff().getComboSex().setSelectedIndex(-1);
				dialog.getPnlStaff().getComboSex().repaint();
				((Pnl_Physician) dialog.getPnlStaff()).getComboTitle().setSelectedIndex(-1);
				((Pnl_Physician) dialog.getPnlStaff()).getComboTitle().repaint();
				getCtrlPnlSetBirthDate().setDate(null);
				getCtrlPnlSetOnsetDate().setDate(null);
				//set the id of the staff to null
				//staff.setId(null);
				//add all listeners to the input fields
				staffMemberSel = null;
				staffMemberUpdate = null;
				setListener();
				setFieldsDisabled();
				
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
	
	
	class TitleListener implements ItemListener{

		@SuppressWarnings("unchecked")
		@Override
		public void itemStateChanged(ItemEvent evt) {
			JComboBox<String> comboTitle;
			if(evt.getSource() instanceof JComboBox<?>){
				comboTitle = (JComboBox<String>) evt.getSource();
				((Physician) staffMemberSel).setTitle((String) comboTitle.getModel().getSelectedItem());
			}			
		}		
	}	

	
	
	
	
	
}
