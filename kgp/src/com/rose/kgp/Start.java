package com.rose.kgp;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import com.rose.kgp.db.DB;
import db.SQL_INSERT;
import com.rose.kgp.db.Dlg_DBSettings;

/**
 * entry point of the application 
 * first: a connection to the database / schema is built
 * second: the administrator is inserted to database (necessary for first login)
 * third: the login-dialog is opened, that starts the main frame
 * @author Ekkehard Rose
 * @version 1.0
 *
 */
public class Start {

	public static void main(String[] args) {
		checkDBConnection();		
	}
	
	/**
	 * get the connection tom the database, insert the administrator to the appropriate schema of that database and opens the login-dialog
	 */
	public static void checkDBConnection(){
		if(DB.createConnection() != null){			
		 //go further on only if a connection to the database could be established
			if(SQL_INSERT.Admin()){		//insert the administrator to database (necessary for first login)
				//start the login dialog
				try {
					Dlg_LogIn dialog = new Dlg_LogIn();
					dialog.setLocationRelativeTo(null); //show in the center of the screen
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
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
