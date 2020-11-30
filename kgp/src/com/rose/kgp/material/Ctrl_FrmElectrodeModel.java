package com.rose.kgp.material;


import java.awt.EventQueue;

import javax.swing.JButton;

import javax.swing.JFrame;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.rose.kgp.db.DB;
import com.rose.kgp.db.Dlg_DBSettings;
import com.rose.kgp.db.SQL_INSERT;


public class Ctrl_FrmElectrodeModel {

	FrmElectrodeModel frame;
	TabbedPaneListener tabbedPaneListener;
	
	
	
	
	
	
	CtrlPnlElectrodes ctrlPnlElectrodes;
	CtrlPnlElectrodeModel ctrlPnlElectrodeModel;
	
	
	
	
	public static void main(String[] args) {
		connectDB();
		if (DB.getConnection() != null) {
			new Ctrl_FrmElectrodeModel();
		}
	}
	
	public Ctrl_FrmElectrodeModel() {	
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new FrmElectrodeModel();
					frame.setVisible(true);					
					tabbedPaneListener = new TabbedPaneListener();					
					
					ctrlPnlElectrodes = new CtrlPnlElectrodes(frame.btnDelete, frame.tglMode);
					ctrlPnlElectrodeModel = new CtrlPnlElectrodeModel(frame.btnDelete, frame.tglMode);
					frame.createNewTab("Elektrodenmodelle", ctrlPnlElectrodeModel.getPanel());
					frame.createNewTab("Elektroden", ctrlPnlElectrodes.getPanel());
					
					setListener();
					frame.setTabSelected(0); //select the first tab
					ctrlPnlElectrodeModel.removeModeListener();
					ctrlPnlElectrodeModel.addModeListener();
					ctrlPnlElectrodeModel.changeDeleteListener();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			private void setListener() {
				frame.addTabChangeListener(tabbedPaneListener);
				
			}
		});
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	private static void connectDB() {
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
	
	class TabbedPaneListener implements ChangeListener{

		@Override
		public void stateChanged(ChangeEvent event) {
			 JTabbedPane sourceTabbedPane = (JTabbedPane) event.getSource();
			 int index = sourceTabbedPane.getSelectedIndex();
			 switch (sourceTabbedPane.getTitleAt(index)) {
				 case "Elektroden":
					ctrlPnlElectrodes.removeModeListener();
					ctrlPnlElectrodes.addModeListener();
					ctrlPnlElectrodes.changeDeleteListener();
					ctrlPnlElectrodes.getTypeModel().actualize();
					break;
				 case "Elektrodenmodelle":
					 ctrlPnlElectrodeModel.removeModeListener();
					 ctrlPnlElectrodeModel.addModeListener();
					 ctrlPnlElectrodeModel.changeDeleteListener();
					 break;
				 default:
					break;
			}
			 
		}
		
	}
	
	
}
