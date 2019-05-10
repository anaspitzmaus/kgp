package com.rose.kgp.administration;

import java.awt.Component;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;
import javax.swing.MutableComboBoxModel;
import javax.swing.WindowConstants;
import javax.swing.event.ListDataListener;

import com.rose.kgp.db.DB;
import com.rose.kgp.db.Dlg_DBSettings;
import com.rose.kgp.db.SQL_INSERT;
import com.rose.kgp.db.SQL_SELECT;
import com.rose.kgp.personnel.Physician;
import com.rose.kgp.ui.Ctrl_PnlSetDate;


public class CtrlPnlBillingCallService {
		
		// TODO Auto-generated constructor stub
	ArrayList<Physician> physicians;
	PnlBillingCallService pnlBillingCallService;
	PhysicianComboModel physicianComboModel;
	PhysicianRenderer physicianRenderer;
	Ctrl_PnlSetDate ctrlPnlSetDate;
	
	public static void main(String[] args) {
		new CtrlPnlBillingCallService(true);
	}
	
	/**
	 * constructor
	 * @param direct if true, the Panel is embedded in a JDialog
	 */
	public CtrlPnlBillingCallService(Boolean direct) {
		if(direct) {
			connectDB();
			if (DB.getConnection() != null) {
				getActivePhysicians();
				pnlBillingCallService = new PnlBillingCallService();
				setModels();
				setRenderer();
				pnlBillingCallService.getComboPhysician().setModel(physicianComboModel);
				pnlBillingCallService.getComboPhysician().setRenderer(physicianRenderer);
				ctrlPnlSetDate = new Ctrl_PnlSetDate(pnlBillingCallService.getPnlSetDate(), LocalDate.now(), LocalDate.now().minusDays(10)); 
				JDialog dialog = new JDialog(); 
				dialog.setVisible(true);	
				dialog.setContentPane(pnlBillingCallService);
				dialog.pack();
			}
		}
	}
	
	private void setModels() {
		physicianComboModel = new PhysicianComboModel(physicians);
		
	}
	
	private void setRenderer() {
		physicianRenderer = new PhysicianRenderer();
	}
	
	
	
	private void getActivePhysicians() {
		physicians = SQL_SELECT.activePhysicians(LocalDate.now());
	}
	
	private void connectDB() {
		if (DB.getConnection() == null) {		
			if(DB.createConnection() != null){			
				 //go further on only if a connection to the database could be established
				if(SQL_INSERT.Admin()){		//insert the administrator to database (necessary for first login)
					//start the login dialog
					try {
//						Dlg_LogIn dialog = new Dlg_LogIn();
//						dialog.setLocationRelativeTo(null); //show in the center of the screen
//						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						//dialog.setVisible(true);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(new JFrame(),
							    "The Application couldn't be started!", "Fatal Error",
							    JOptionPane.ERROR_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(new JFrame(),
						    "The Application couldn't be started!", "Fatal Error",
						    JOptionPane.ERROR_MESSAGE);
				}
			}else{ //if there couldn't be created a database connection
				//open the Dialog with the database settings
				Dlg_DBSettings dlgDBSettings = new Dlg_DBSettings();
				dlgDBSettings.setLocationRelativeTo(null);
				dlgDBSettings.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				dlgDBSettings.setModal(true);
				dlgDBSettings.setVisible(true);	
			}
		}
	}
	
	class PhysicianComboModel implements MutableComboBoxModel<Physician>{
		
		 int index=-1;
		 
		public PhysicianComboModel(ArrayList<Physician> physicians) {
			
		}

		@Override
		public Physician getSelectedItem() {
			if(index >= 0)
	        {
	            return ((Physician)physicians.get(index));
	        }
	        else
	        {
	            return null;
	        }
		}

		@Override
		public void setSelectedItem(Object physician) {
			 for(int i = 0; i< physicians.size(); i++)
		        {
		            if(((Physician)physicians.get(i)).equals(physician))
		                
		            {
		                index = i;
		                break;
		            }
		        }
			
		}

		@Override
		public void addListDataListener(ListDataListener arg0) {
			// TODO Auto-generated method stub
			
		}		

		@Override
		public int getSize() {
			return physicians.size();
		}

		@Override
		public void removeListDataListener(ListDataListener arg0) {
			// TODO Auto-generated method stub
			
		}

		

		

		@Override
		public void removeElement(Object arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void removeElementAt(int arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Physician getElementAt(int index) {
			return ((Physician)physicians.get(index));
		}

		@Override
		public void addElement(Physician physician) {
			if(!physicians.contains(physician))
	        { 
	            physicians.add(physician);
	           
	        }
			
		}

		@Override
		public void insertElementAt(Physician item, int index) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class PhysicianRenderer implements ListCellRenderer<Physician>{
		protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();
		@Override
		public Component getListCellRendererComponent(JList<? extends Physician> list, Physician value, int index,
				boolean isSelected, boolean cellHasFocus) {
			JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index,
			        isSelected, cellHasFocus);
			// TODO Auto-generated method stub
			if(value instanceof Physician){
				renderer.setText(((Physician)value).getFirstname() + " " + ((Physician)value).getSurname());
				
			}else {
				renderer.setText("");
			}
			return renderer;
		}
		
		
		
	}
}
